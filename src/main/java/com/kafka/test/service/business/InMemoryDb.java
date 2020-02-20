package com.kafka.test.service.business;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kafka.test.dto.response.EmailDto;
import com.kafka.test.dto.response.PersonDto;

@Component
class InMemoryDb {

    private Map<UUID, PersonDto> persons = new HashMap<>();
    private Map<UUID, List<EmailDto>> emailPerPersonUuid = new HashMap<>();

    PersonDto createPerson(PersonDto person) {
        Objects.requireNonNull(person);

        person.setUuid(UUID.randomUUID());
        person.setCreateDate(OffsetDateTime.now());
        person.setUpdateDate(person.getCreateDate());
        this.persons.put(person.getUuid(), person);
        return person;
    }

    Optional<PersonDto> findPersonByUuid(UUID uuid) {
        Objects.requireNonNull(uuid);
        PersonDto person = this.persons.get(uuid);
        return Optional.ofNullable(person);
    }

    void addEmailToPersonUuid(UUID personUuid, EmailDto email) {
        Objects.requireNonNull(personUuid);
        Objects.requireNonNull(email);

        email.setUuid(UUID.randomUUID());
        email.setCreateDate(OffsetDateTime.now());
        email.setUpdateDate(email.getCreateDate());

        Optional<PersonDto> personOpt = this.findPersonByUuid(personUuid);
        if (personOpt.isEmpty()) {
            throw new RuntimeException("No person for uuid="+personUuid);
        }

        List<EmailDto> emails = this.emailPerPersonUuid.computeIfAbsent(personUuid, k -> new ArrayList<>());
        emails.add(email);
    }

    List<EmailDto> findEmailByPersonUuid(UUID personUuid) {
        Objects.requireNonNull(personUuid);

        Optional<PersonDto> personOpt = this.findPersonByUuid(personUuid);
        if (personOpt.isEmpty()) {
            throw new RuntimeException("No person for uuid="+personUuid);
        }

        return this.emailPerPersonUuid.computeIfAbsent(personUuid, k -> new ArrayList<>());
    }

}
