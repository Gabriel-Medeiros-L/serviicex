package com.serviceRealEstate.controller;

import com.serviceRealEstate.domain.RealEstate;
import com.serviceRealEstate.domain.dto.RealEstateDTO;
import com.serviceRealEstate.service.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/realestate")
public class RealEstateController {
    @Autowired
    private RealEstateService realEstateService;

    @GetMapping
    public ResponseEntity<List<RealEstateDTO>> findAll(){
        List<RealEstate> realEstate = realEstateService.getAllRealEstate();
        List<RealEstateDTO> realEstatetDTOS = realEstate.stream()
                .map(RealEstateDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(realEstatetDTOS);
    }

    @PostMapping
    public ResponseEntity<RealEstate> save(@RequestBody RealEstateDTO carrosDto) {
        RealEstate realEstate = realEstateService.create(carrosDto);
        return new ResponseEntity<>(realEstate, HttpStatus.CREATED);
    }
}
