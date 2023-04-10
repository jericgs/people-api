package br.com.peopleapi.peopleapi.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.peopleapi.peopleapi.domain.model.Address;
import br.com.peopleapi.peopleapi.domain.repository.AddressRepository;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository mockAddressRepository;

    private AddressService addressService;

    @Before
    public void setup() {
        addressService = new AddressService(mockAddressRepository);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockAddressRepository);
    }

    @Test
    public void register() {
        Address address = new Address();
        when(mockAddressRepository.save(any(Address.class))).thenReturn(address);

        Address result = addressService.register(address);

        assertNotNull(result);
        assertEquals(address, result);

        verify(mockAddressRepository, times(1)).save(any(Address.class));
    }

    @Test
    public void list() {
        List<Address> addresses = new ArrayList<>();
        when(mockAddressRepository.findAll()).thenReturn(addresses);

        List<Address> result = addressService.list();

        assertNotNull(result);
        assertEquals(addresses, result);

        verify(mockAddressRepository, times(1)).findAll();
    }

    @Test
    public void mainAddress() {
        Long personId = 1L;
        List<Address> addresses = new ArrayList<>();
        when(mockAddressRepository.findByPersonIdAndIsPrimary(personId, true)).thenReturn(addresses);

        List<Address> result = addressService.mainAddress(personId);

        assertNotNull(result);
        assertEquals(addresses, result);

        verify(mockAddressRepository, times(1)).findByPersonIdAndIsPrimary(personId, true);
    }

}
