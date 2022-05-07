package com.projet.commerce.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private static  ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){this.clientRepository = clientRepository;}



    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client addClient(Client client){
        return clientRepository.save(client);
    }

    public Client updateClient(Client client){
        return clientRepository.save(client);
    }

    public static Client findClientById(Long id){
        return clientRepository.findClientByCin(id);
    }

}
