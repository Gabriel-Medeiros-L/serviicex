package com.serviceRealEstate.repository;

import com.serviceRealEstate.domain.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstateRepository extends JpaRepository<RealEstate,Long> {
}
