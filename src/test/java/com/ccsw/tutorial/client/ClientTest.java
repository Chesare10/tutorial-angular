package com.ccsw.tutorial.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

/**
 * @author ccsw
 */
@ExtendWith(MockitoExtension.class)
public class ClientTest {

    public static final Long EXIST_CLIENT_ID = 1L;
    public static final Long NOT_EXIST_CLIENT_ID = 9L;
    public static final String NEW_CLIENT_NAME = "CLI";

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void findAllShouldReturnAllClients() {
        List<Client> list = new ArrayList<>();

        list.add(mock(Client.class));

        when(clientRepository.findAll()).thenReturn(list);

        List<Client> clients = clientService.findAll();

        assertNotNull(clients);
        assertEquals(1, clients.size());

    }

    @Test
    public void saveNotExistsClientIdShouldCreate() {
        ClientDto dto = new ClientDto();
        dto.setName(NEW_CLIENT_NAME);

        Client client = mock(Client.class);
        when(clientRepository.findById(NOT_EXIST_CLIENT_ID)).thenReturn(Optional.of(client));

        try {
            clientService.save(NOT_EXIST_CLIENT_ID, dto);
        } catch (NameAlreadyExistsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        verify(clientRepository).save(client);
    }

    @Test
    public void saveExistsClientIdShouldUpdate() {
        ClientDto dto = new ClientDto();
        dto.setName(NEW_CLIENT_NAME);

        ArgumentCaptor<Client> client = ArgumentCaptor.forClass(Client.class);

        try {
            clientService.save(null, dto);
        } catch (NameAlreadyExistsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        verify(clientRepository).save(client.capture());

        assertEquals(NEW_CLIENT_NAME, client.getValue().getName());
    }

    @Test
    public void deleteClientExistsIdShouldDelete() {
        clientService.delete(EXIST_CLIENT_ID);

        verify(clientRepository).deleteById(EXIST_CLIENT_ID);
    }
}
