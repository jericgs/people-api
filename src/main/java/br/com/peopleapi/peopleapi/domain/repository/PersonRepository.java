package br.com.peopleapi.peopleapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.peopleapi.peopleapi.domain.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
