package br.com.peopleapi.peopleapi.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.peopleapi.peopleapi.domain.model.Person;
import br.com.peopleapi.peopleapi.domain.repository.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    private static final int BIRTH_YEAR = 1980;
    private static final int BIRTH_MONTH = 3;
    private static final int BIRTH_DAY = 10;

    @Mock
    private PersonRepository mockPersonRepository;

    @InjectMocks
    private PersonService personService;

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockPersonRepository);
    }

    @Test
    public void register() {
        Person person = new Person();
        person.setName("John Doe");
        person.setDateBirth(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY));
        Person expectedPerson = new Person();

        expectedPerson.setId(1L);
        expectedPerson.setName("John Doe");
        expectedPerson.setDateBirth(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY));

        when(mockPersonRepository.save(person)).thenReturn(expectedPerson);
        Person savedPerson = personService.register(person);
        assertEquals(expectedPerson, savedPerson);
        verify(mockPersonRepository, times(1)).save(person);
    }

    @Test
    public void update() {
        Long personId = 1L;
        Person existingPerson = new Person();
        existingPerson.setId(personId);
        existingPerson.setName("John Doe");
        existingPerson.setDateBirth(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY));

        Person updatedPerson = new Person();
        updatedPerson.setId(personId);
        updatedPerson.setName("Jane Doe");
        updatedPerson.setDateBirth(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY));

        when(mockPersonRepository.findById(personId)).thenReturn(Optional.of(existingPerson));
        when(mockPersonRepository.save(existingPerson)).thenReturn(updatedPerson);
        Person savedPerson = personService.update(updatedPerson, personId);
        assertEquals(updatedPerson, savedPerson);
        verify(mockPersonRepository, times(1)).findById(personId);
        verify(mockPersonRepository, times(1)).save(existingPerson);
    }

    @Test
    public void search() {
        Long personId = 1L;
        Person expectedPerson = new Person();
        expectedPerson.setId(personId);
        expectedPerson.setName("John Doe");
        expectedPerson.setDateBirth(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY));

        when(mockPersonRepository.findById(personId)).thenReturn(Optional.of(expectedPerson));
        Person foundPerson = personService.search(personId);
        assertEquals(expectedPerson, foundPerson);
        verify(mockPersonRepository, times(1)).findById(personId);

    }

    @Test
    public void list() {

        Person person1 = new Person();
        person1.setId(0L);
        person1.setName("John Doe");
        person1.setDateBirth(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY));

        Person person2 = new Person();
        person2.setId(1L);
        person2.setName("Jane Doe");
        person2.setDateBirth(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY));

        Person person3 = new Person();
        person3.setId(2L);
        person3.setName("Bob Smith");
        person3.setDateBirth(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY));

        List<Person> expectedList = Arrays.asList(person1, person2, person3);
        when(mockPersonRepository.findAll()).thenReturn(expectedList);

        List<Person> actualList = personService.list();
        assertEquals(expectedList.size(), actualList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i), actualList.get(i));
        }

        verify(mockPersonRepository, times(1)).findAll();
    }

}
