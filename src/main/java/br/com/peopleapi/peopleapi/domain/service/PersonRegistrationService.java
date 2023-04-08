package br.com.peopleapi.peopleapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.peopleapi.peopleapi.domain.model.Person;
import br.com.peopleapi.peopleapi.domain.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PersonRegistrationService {

    private PersonRepository personRepository;

    @Transactional
    public Person register(final Person person) {

        return personRepository.save(person);
    }

    @Transactional
    public Person update(final Person person, final Long personId) {
        Person existingPerson = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        existingPerson.setName(person.getName());
        existingPerson.setDateBirth(person.getDateBirth());

        return personRepository.save(existingPerson);
    }

}
