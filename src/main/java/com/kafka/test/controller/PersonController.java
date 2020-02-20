package com.kafka.test.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.test.dto.request.PersonCreateDto;
import com.kafka.test.dto.response.PersonDto;
import com.kafka.test.service.business.PersonService;

@RestController
@RequestMapping({ "/v1" })
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping(value = "/persons", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(@Validated @RequestBody PersonCreateDto request) {
        return this.personService.create(request);
    }
}
