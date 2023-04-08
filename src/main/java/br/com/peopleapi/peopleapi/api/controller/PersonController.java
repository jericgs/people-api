package br.com.peopleapi.peopleapi.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.peopleapi.peopleapi.api.model.PersonInputDTO;
import br.com.peopleapi.peopleapi.api.model.PersonOutDTO;
import br.com.peopleapi.peopleapi.assembler.PersonAssembler;
import br.com.peopleapi.peopleapi.domain.service.PersonRegistrationService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/people")
public class PersonController {

    private PersonAssembler personAssembler;
    private PersonRegistrationService personRegistrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutDTO add(@RequestBody final PersonInputDTO personInputDTO) {

        return personAssembler.toDto(personRegistrationService.register(personAssembler.toPerson(personInputDTO)));

    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonOutDTO> update(@PathVariable final Long personId,
            @RequestBody final PersonInputDTO personInputDTO) {

        return ResponseEntity
                .ok(personAssembler
                        .toDto(personRegistrationService.update(personAssembler.toPerson(personInputDTO), personId)));

    }

}
