package br.com.peopleapi.peopleapi.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import br.com.peopleapi.peopleapi.api.model.PersonInputDTO;
import br.com.peopleapi.peopleapi.api.model.PersonOutDTO;
import br.com.peopleapi.peopleapi.domain.model.Person;

@RunWith(MockitoJUnitRunner.class)
public class PersonAssemblerTest {

    private static final int BIRTH_YEAR = 1980;
    private static final int BIRTH_MONTH = 3;
    private static final int BIRTH_DAY = 10;

    @Mock
    private ModelMapper mockModelMapper;

    @InjectMocks
    private PersonAssembler personAssembler;

    private PersonInputDTO personInputDTO;

    private Person personExpected;

    private Person person;

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockModelMapper);
    }

    @Before
    public void setUp() {

        personInputDTO = new PersonInputDTO();
        personInputDTO.setName("name");
        personInputDTO.setDateBirth(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY));

        personExpected = new Person();
        personExpected.setName("name");
        personExpected.setDateBirth(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY));

        person = new Person();
        person.setId(1L);
        person.setName("name");
        person.setDateBirth(LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY));
        person.setCreatedAt(OffsetDateTime.now());
        person.setUpdatedAt(OffsetDateTime.now());
        person.setDeletedAt(OffsetDateTime.now());

    }

    @Test
    public void toPerson() {

        when(mockModelMapper.map(personInputDTO, Person.class)).thenReturn(personExpected);

        Person personActual = personAssembler.toPerson(personInputDTO);

        assertEquals(personExpected.getName(), personActual.getName());
        assertEquals(personExpected.getDateBirth(), personActual.getDateBirth());

        verify(mockModelMapper, times(1)).map(personInputDTO, Person.class);

    }

    @Test
    public void toDto() {

        PersonOutDTO personOutDTOExpected = new PersonOutDTO();
        personOutDTOExpected.setName(person.getName());
        personOutDTOExpected.setDateBirth(person.getDateBirth());

        when(mockModelMapper.map(person, PersonOutDTO.class)).thenReturn(personOutDTOExpected);

        PersonOutDTO personOutDTOActual = personAssembler.toDto(person);

        assertEquals(personOutDTOExpected.getName(), personOutDTOActual.getName());
        assertEquals(personOutDTOExpected.getDateBirth(), personOutDTOActual.getDateBirth());

        verify(mockModelMapper, times(1)).map(person, PersonOutDTO.class);

    }

    @Test
    public void toCollectionDto() {

        List<Person> personList = new ArrayList<>();
        personList.add(person);

        PersonOutDTO personOutDTOExpected = new PersonOutDTO();
        personOutDTOExpected.setName(person.getName());
        personOutDTOExpected.setDateBirth(person.getDateBirth());

        List<PersonOutDTO> personOutDTOListExpected = new ArrayList<>();
        personOutDTOListExpected.add(personOutDTOExpected);

        when(mockModelMapper.map(person, PersonOutDTO.class)).thenReturn(personOutDTOExpected);

        List<PersonOutDTO> personOutDTOListActual = personAssembler.toCollectionDto(personList);

        assertEquals(personOutDTOListExpected, personOutDTOListActual);

        verify(mockModelMapper, times(personList.size())).map(any(Person.class), eq(PersonOutDTO.class));

    }

}
