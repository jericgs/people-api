package br.com.peopleapi.peopleapi.api.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonOutDTO {

    private String name;
    private LocalDate dateBirth;
}
