package com.serviceRealEstate.service;

import com.serviceRealEstate.domain.RealEstate;
import com.serviceRealEstate.domain.dto.RealEstateDTO;
import com.serviceRealEstate.repository.ClientRepository;
import com.serviceRealEstate.repository.RealEstateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RealEstateService {
    @Autowired
    private RealEstateRepository realEstateRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<RealEstate> getAllRealEstate() {
        return realEstateRepository.findAll();
    }

    public RealEstate findById(Long id) {
        Optional<RealEstate> optionalHospital = realEstateRepository.findById(id);
        return optionalHospital.orElse(null);
    }

    public RealEstate create(RealEstateDTO realEstateDTO) {
        realEstateDTO.setId(null);
        RealEstate realEstate = new RealEstate(realEstateDTO);
        return realEstateRepository.save(realEstate);
    }

    public RealEstate updateRealEstate(Long id, RealEstate realEstate) {
        Optional<RealEstate> optionalRealEstate = realEstateRepository.findById(id);
        if (optionalRealEstate.isPresent()) {
            RealEstate responseRealEstate = optionalRealEstate.get();
            BeanUtils.copyProperties(realEstate, responseRealEstate);
            return realEstateRepository.save(responseRealEstate);
        } else {
            return null;
        }
    }

    public boolean deleteRealEstate(Long id) {
        Optional<RealEstate> optionalRealEstate = realEstateRepository.findById(id);
        if (optionalRealEstate.isPresent()) {
            realEstateRepository.delete(optionalRealEstate.get());
            return true;
        } else {
            return false;
        }
    }
}
