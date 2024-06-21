package com.serviceRealEstate.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serviceRealEstate.domain.dto.ClientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    @JsonIgnoreProperties("hospital")
    private RealEstate realEstateId;


    public Client(ClientDTO clientDto) {
        this.name = clientDto.getName();
        this.contact = clientDto.getContact();
        if (clientDto.getRealEstateId() != null) {
            this.realEstateId = new RealEstate();
            this.realEstateId.setId(clientDto.getRealEstateId());
        }
    }
}

