package com.kafka.test.service.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.kafka.test.dto.request.PersonCreateDto;
import com.kafka.test.dto.response.PersonDto;
import com.kafka.test.service.event.message.PersonCreatedEvent;
import com.kafka.test.service.event.template.PersonCreatedKafkaTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class PersonService {

    private final InMemoryDb inMemoryDb;

    private final PersonCreatedKafkaTemplate personCreatedKafkaTemplate;

    public PersonDto create(PersonCreateDto personCreate) {
        PersonDto personToBeSaved = PersonDto.builder()
                .name(personCreate.getName())
                .build();

        PersonDto savedPerson = this.inMemoryDb.createPerson(personToBeSaved);

        publishCreatedEvent(savedPerson);

        return savedPerson;
    }

    private void publishCreatedEvent(PersonDto person) {
        PersonCreatedEvent event = PersonCreatedEvent.builder()
                .uuid(person.getUuid())
                .name(person.getName())
                .build();
        ListenableFuture<SendResult<String, PersonCreatedEvent>> send = this.personCreatedKafkaTemplate
                .send(PersonCreatedEvent.EVENT_NAME, event);
        send.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Failed to issue event={} for person={}", event, person, throwable);
            }

            @Override
            public void onSuccess(SendResult<String, PersonCreatedEvent> result) {
                log.info("Succeeded in issue event={} for person={} with result={}", event, person, result);
            }
        });
    }

}
