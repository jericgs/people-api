package br.com.peopleapi.peopleapi.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.peopleapi.peopleapi.api.model.AddressInputDTO;
import br.com.peopleapi.peopleapi.api.model.AddressOutDTO;
import br.com.peopleapi.peopleapi.assembler.AddressAssembler;
import br.com.peopleapi.peopleapi.domain.model.Address;
import br.com.peopleapi.peopleapi.domain.service.AddressService;

@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

    @Mock
    private AddressAssembler mockAddressAssembler;

    @Mock
    private AddressService mockAddressService;

    @InjectMocks
    private AddressController addressController;

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockAddressAssembler);
        verifyNoMoreInteractions(mockAddressService);
    }

    @Test
    public void addAddress() {

        AddressInputDTO addressInputDTO = new AddressInputDTO();
        Address address = new Address();
        AddressOutDTO addressOutDTO = new AddressOutDTO();

        when(mockAddressAssembler.toAddress(addressInputDTO)).thenReturn(address);
        when(mockAddressService.register(address)).thenReturn(address);
        when(mockAddressAssembler.toDto(address)).thenReturn(addressOutDTO);

        AddressOutDTO result = addressController.add(addressInputDTO);

        assertEquals(addressOutDTO, result);
        verify(mockAddressAssembler).toAddress(addressInputDTO);
        verify(mockAddressService).register(address);
        verify(mockAddressAssembler).toDto(address);
    }

    @Test
    public void listAddresses() {

        List<Address> addresses = Arrays.asList(new Address(), new Address());
        List<AddressOutDTO> addressOutDTOs = Arrays.asList(new AddressOutDTO(), new AddressOutDTO());

        when(mockAddressService.list()).thenReturn(addresses);
        when(mockAddressAssembler.toCollectionDto(addresses)).thenReturn(addressOutDTOs);

        List<AddressOutDTO> results = addressController.list();

        assertEquals(addressOutDTOs, results);
        verify(mockAddressService).list();
        verify(mockAddressAssembler).toCollectionDto(addresses);
    }

    @Test
    public void getMainAddress() {

        Long personId = 1L;
        List<Address> addresses = Arrays.asList(new Address(), new Address());
        List<AddressOutDTO> addressOutDTOs = Arrays.asList(new AddressOutDTO(), new AddressOutDTO());

        when(mockAddressService.mainAddress(personId)).thenReturn(addresses);
        when(mockAddressAssembler.toCollectionDto(addresses)).thenReturn(addressOutDTOs);

        List<AddressOutDTO> results = addressController.getMethodName(personId);

        assertEquals(addressOutDTOs, results);
        verify(mockAddressService).mainAddress(personId);
        verify(mockAddressAssembler).toCollectionDto(addresses);
    }
}
