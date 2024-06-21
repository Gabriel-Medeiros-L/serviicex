package com.serviceRealEstate.controller;

import com.serviceRealEstate.domain.Client;
import com.serviceRealEstate.domain.RealEstate;
import com.serviceRealEstate.domain.dto.ClientDTO;
import com.serviceRealEstate.service.ClientService;
import com.serviceRealEstate.service.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientController {


    @Autowired
    private ClientService clientService;

    @Autowired
    private RealEstateService realEstateService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> clients = clientService.findAll();
        List<ClientDTO> clientDTOS = clients.stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientDTOS);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente não encontrado com o ID: " + id);
        }
        return ResponseEntity.ok().body(new ClientDTO(client));
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody ClientDTO clientDto) {
        Client client = clientService.create(clientDto);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClientDTO clientDto) {
        Client clientToUpdate = clientService.findById(id);
        if (clientToUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente não encontrado com o ID: " + id);
        }
        RealEstate realEstate = realEstateService.findById(clientDto.getRealEstateId());
        if (realEstate == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Imovel não encontrado com o ID: " + clientDto.getRealEstateId());
        }
        Client updatedClient = new Client(clientDto);
        updatedClient.setId(clientToUpdate.getId());
        clientService.update(updatedClient);
        return ResponseEntity.ok().body("Cliente atualizado com sucesso");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Client clientToDelete = clientService.findById(id);
        if (clientToDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente não encontrado com o ID: " + id);
        }
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente removido com sucesso");
    }
}