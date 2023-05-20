package br.com.peopleapi.peopleapi.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.peopleapi.peopleapi.api.model.AddressInputDTO;
import br.com.peopleapi.peopleapi.api.model.AddressOutDTO;
import br.com.peopleapi.peopleapi.assembler.AddressAssembler;
import br.com.peopleapi.peopleapi.domain.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private AddressAssembler addressAssembler;
    private AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressOutDTO add(@RequestBody final @Valid AddressInputDTO addressInputDTO) {

        return addressAssembler.toDto(addressService.register(addressAssembler.toAddress(addressInputDTO)));

    }

    @GetMapping
    public List<AddressOutDTO> list() {
        return addressAssembler.toCollectionDto(addressService.list());
    }

    @GetMapping("/{personId}")
    public List<AddressOutDTO> getMethodName(@PathVariable final Long personId) {

        return addressAssembler.toCollectionDto(addressService.mainAddress(personId));

    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable final Long addressId) {
        addressService.removeAddress(addressId);
    }

}
