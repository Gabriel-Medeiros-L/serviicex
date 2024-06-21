package com.serviceRealEstate.domain.dto;

import com.serviceRealEstate.domain.RealEstate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RealEstateDTO {

    private Long id;

    private String name;

    private String address;

    public RealEstateDTO(RealEstate hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.address = hospital.getAddress();
    }
}
