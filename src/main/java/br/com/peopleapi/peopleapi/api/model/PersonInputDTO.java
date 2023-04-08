package br.com.peopleapi.peopleapi.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonInputDTO {

    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateBirth;
}
