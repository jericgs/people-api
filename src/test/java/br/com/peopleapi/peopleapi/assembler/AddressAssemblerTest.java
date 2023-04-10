package br.com.peopleapi.peopleapi.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

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

import br.com.peopleapi.peopleapi.api.model.AddressInputDTO;
import br.com.peopleapi.peopleapi.api.model.AddressOutDTO;
import br.com.peopleapi.peopleapi.api.model.PersonBaseDTO;
import br.com.peopleapi.peopleapi.domain.model.Address;
import br.com.peopleapi.peopleapi.domain.model.Person;

@RunWith(MockitoJUnitRunner.class)
public class AddressAssemblerTest {

    @Mock
    private ModelMapper mockModelMapper;

    @InjectMocks
    private AddressAssembler addressAssembler;

    private AddressInputDTO addressInputDTO;

    private Address addressExpected;

    private Person person;

    private Address address;

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockModelMapper);
    }

    @Before
    public void setUp() {

        PersonBaseDTO personBaseDTO = new PersonBaseDTO();
        personBaseDTO.setId(1L);

        addressInputDTO = new AddressInputDTO();
        addressInputDTO.setPerson(personBaseDTO);
        addressInputDTO.setStreet("street");
        addressInputDTO.setZipCode("zipCode");
        addressInputDTO.setNumber("123");
        addressInputDTO.setCity("city");
        addressInputDTO.setIsPrimary(true);

        person = new Person();
        person.setId(1L);

        addressExpected = new Address();
        addressExpected.setPerson(person);
        addressExpected.setStreet("street");
        addressExpected.setZipCode("zipCode");
        addressExpected.setNumber("123");
        addressExpected.setCity("city1");
        addressExpected.setIsPrimary(true);

        address = new Address();
        address.setPerson(person);
        address.setStreet("street");
        address.setZipCode("zipCode");
        address.setNumber("123");
        address.setCity("city1");
        address.setIsPrimary(true);

    }

    @Test
    public void toAddress() {

        when(mockModelMapper.map(addressInputDTO, Address.class)).thenReturn(addressExpected);

        Address addressActual = addressAssembler.toAddress(addressInputDTO);

        assertEquals(addressExpected.getStreet(), addressActual.getStreet());
        assertEquals(addressExpected.getNumber(), addressActual.getNumber());
        assertEquals(addressExpected.getCity(), addressActual.getCity());
        assertEquals(addressExpected.getZipCode(), addressActual.getZipCode());
        assertEquals(addressExpected.getPerson().getId(), addressActual.getPerson().getId());

        verify(mockModelMapper, times(1)).map(addressInputDTO, Address.class);
    }

    @Test
    public void toDto() {

        AddressOutDTO addressDTOExpected = new AddressOutDTO();
        addressDTOExpected.setStreet(address.getStreet());
        addressDTOExpected.setZipCode(address.getZipCode());
        addressDTOExpected.setNumber(address.getNumber());
        addressDTOExpected.setCity(address.getCity());
        addressDTOExpected.setIsPrimary(address.getIsPrimary());

        when(mockModelMapper.map(address, AddressOutDTO.class)).thenReturn(addressDTOExpected);

        AddressOutDTO addressOutDTOActual = addressAssembler.toDto(address);

        assertEquals(addressDTOExpected.getStreet(), addressOutDTOActual.getStreet());
        assertEquals(addressDTOExpected.getNumber(), addressOutDTOActual.getNumber());
        assertEquals(addressDTOExpected.getCity(), addressOutDTOActual.getCity());
        assertEquals(addressDTOExpected.getZipCode(), addressOutDTOActual.getZipCode());
        assertEquals(addressDTOExpected.getIsPrimary(), addressOutDTOActual.getIsPrimary());

        verify(mockModelMapper, times(1)).map(address, AddressOutDTO.class);

    }

    @Test
    public void toCollectionDto() {

        List<Address> addressList = new ArrayList<>();
        addressList.add(address);

        AddressOutDTO addressOutDTOExpected = new AddressOutDTO();
        addressOutDTOExpected.setStreet(address.getStreet());
        addressOutDTOExpected.setZipCode(address.getZipCode());
        addressOutDTOExpected.setNumber(address.getNumber());
        addressOutDTOExpected.setCity(address.getCity());
        addressOutDTOExpected.setIsPrimary(address.getIsPrimary());

        List<AddressOutDTO> addressOutDTOListExpected = new ArrayList<>();
        addressOutDTOListExpected.add(addressOutDTOExpected);

        when(mockModelMapper.map(address, AddressOutDTO.class)).thenReturn(addressOutDTOExpected);

        List<AddressOutDTO> addressOutDTOListActual = addressAssembler.toCollectionDto(addressList);

        assertEquals(addressOutDTOListExpected, addressOutDTOListActual);

        verify(mockModelMapper, times(addressList.size())).map(any(Address.class), eq(AddressOutDTO.class));
    }

}
