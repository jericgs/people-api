package br.com.peopleapi.peopleapi.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Field {
    private String name;
    private String message;
}
