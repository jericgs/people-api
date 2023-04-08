package br.com.peopleapi.peopleapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressOutDTO extends AddressBaseDTO {

    private String street;
    private String zipCode;
    private String number;
    private String city;
    private Boolean isPrimary;

}
