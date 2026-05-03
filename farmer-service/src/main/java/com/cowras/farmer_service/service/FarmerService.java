package com.cowras.farmer_service.service;

import com.cowras.farmer_service.model.Farmer;
import com.cowras.farmer_service.repository.FarmerRepository;
import java.util.List;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository repository;
    public Farmer create(Farmer farmer) {
        return repository.save(farmer);
    }

    public List<Farmer> getAll() {
        return repository.findAll();
    }

    public Farmer getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }
}