package br.com.peopleapi.peopleapi.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.peopleapi.peopleapi.api.model.PersonInputDTO;
import br.com.peopleapi.peopleapi.api.model.PersonOutDTO;
import br.com.peopleapi.peopleapi.domain.model.Person;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PersonAssembler {

    private ModelMapper modelMapper;

    public Person toPerson(final PersonInputDTO personInputDTO) {
        return modelMapper.map(personInputDTO, Person.class);
    }

    public PersonOutDTO toDto(final Person person) {
        return modelMapper.map(person, PersonOutDTO.class);
    }

    public List<PersonOutDTO> toCollectionDto(final List<Person> people) {
        return people.stream().map(this::toDto).collect(Collectors.toList());
    }

}
