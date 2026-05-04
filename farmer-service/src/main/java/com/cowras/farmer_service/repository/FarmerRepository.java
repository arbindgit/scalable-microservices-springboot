package com.cowras.farmer_service.repository;

import com.cowras.farmer_service.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}