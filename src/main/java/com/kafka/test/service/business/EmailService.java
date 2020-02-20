package com.kafka.test.service.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kafka.test.dto.response.EmailDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private final InMemoryDb inMemoryDb;

    public void addEmailToPersonUuid(UUID personUuid, String content) {
        EmailDto email = EmailDto.builder()
                .content(content)
                .build();
        this.inMemoryDb.addEmailToPersonUuid(personUuid, email);
        log.info("Saved email={} to personUuid={}.", email, personUuid);
    }

    public List<EmailDto> findEmailByPersonUuid(UUID personUuid) {
        return this.inMemoryDb.findEmailByPersonUuid(personUuid);
    }

}
