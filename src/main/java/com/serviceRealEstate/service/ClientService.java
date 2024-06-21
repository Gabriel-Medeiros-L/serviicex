package com.serviceRealEstate.service;

import com.serviceRealEstate.domain.Client;
import com.serviceRealEstate.domain.RealEstate;
import com.serviceRealEstate.domain.dto.ClientDTO;
import com.serviceRealEstate.repository.ClientRepository;
import com.serviceRealEstate.repository.RealEstateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clienteRepository;

    @Autowired
    private RealEstateRepository realEstateRepository;

    public Client findById(Long id) {
        Optional<Client> client = clienteRepository.findById(id);
        return client.orElse(null); // Retorna null se não encontrar o cliente
    }

    public List<Client> findAll() {
        return clienteRepository.findAll();
    }

    public Client create(ClientDTO clientDto) {
        clientDto.setId(null);
        RealEstate realEstate = realEstateRepository.findById(clientDto.getRealEstateId())
                .orElseThrow(() -> new RuntimeException("Hospital com ID " + clientDto.getRealEstateId() + " não encontrado."));
        Client client = new Client(clientDto);
        if (realEstate != null) {
            client.setRealEstateId(realEstate);
        }
        return clienteRepository.save(client);
    }

    @Transactional
    public Client update(Client client) {
        return clienteRepository.save(client);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}