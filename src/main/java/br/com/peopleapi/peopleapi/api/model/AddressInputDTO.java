package br.com.peopleapi.peopleapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInputDTO {

    private PersonBaseDTO person;
    private String street;
    private String zipCode;
    private String number;
    private String city;
    private Boolean isPrimary;

}
