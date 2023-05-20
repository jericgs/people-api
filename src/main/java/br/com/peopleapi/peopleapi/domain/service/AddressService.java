package br.com.peopleapi.peopleapi.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.peopleapi.peopleapi.domain.exception.EntityNotFoundException;
import br.com.peopleapi.peopleapi.domain.model.Address;
import br.com.peopleapi.peopleapi.domain.repository.AddressRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class AddressService {

    private AddressRepository addressRepository;

    @Transactional
    public Address register(final Address address) {

        return addressRepository.save(address);
    }

    @Transactional
    public List<Address> list() {

        return addressRepository.findAll();
    }

    @Transactional
    public List<Address> mainAddress(final Long personId) {

        return addressRepository.findByPersonIdAndIsPrimary(personId, true);

    }

    @Transactional
    public void removeAddress(final Long addressId) {
        try {
            addressRepository.deleteById(addressId);
        } catch (EntityNotFoundException e) {
            log.error("Erro ao remover o endereço com ID: {}", addressId);
            log.error("Erro: {}", e.getMessage());
            e.printStackTrace();
        }
    }

}
