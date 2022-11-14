package com.ccsw.tutorial.client;

import java.util.List;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

public interface ClientService {

    List<Client> findAll();

    void save(Long id, ClientDto dto) throws NameAlreadyExistsException;

    void delete(Long id);

    Client get(Long id);

}
