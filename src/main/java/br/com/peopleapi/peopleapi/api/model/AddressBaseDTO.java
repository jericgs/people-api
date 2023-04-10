package br.com.peopleapi.peopleapi.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressBaseDTO {

    @NotNull
    private Long id;
}
