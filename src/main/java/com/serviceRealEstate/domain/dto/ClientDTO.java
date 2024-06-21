package com.serviceRealEstate.domain.dto;

import com.serviceRealEstate.domain.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDTO {

    private  Long id;

    private  String name;

    private  String contact;

    private Long realEstateId;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.contact = client.getContact();
        if (client.getRealEstateId() != null) {
            this.realEstateId = client.getRealEstateId().getId();
        }
    }
}

