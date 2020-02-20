package com.kafka.test.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.test.dto.response.EmailDto;
import com.kafka.test.service.business.EmailService;

@RestController
@RequestMapping({ "/v1" })
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping(value = "/persons/{personUuid}/emails", consumes = "application/json")
    public List<EmailDto> create(@PathVariable(value = "personUuid") UUID personUuid) {
        return this.emailService.findEmailByPersonUuid(personUuid);
    }
}
