package br.com.peopleapi.peopleapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.peopleapi.peopleapi.domain.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
