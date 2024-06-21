package com.serviceRealEstate.domain;

import com.serviceRealEstate.domain.dto.RealEstateDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    public RealEstate(RealEstateDTO hospitalDTO) {
        super();
        this.id = hospitalDTO.getId();
        this.name = hospitalDTO.getName();
        this.address = hospitalDTO.getAddress();
    }
}
