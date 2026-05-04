package com.cowras.farmer_service.controller;

import com.cowras.farmer_service.model.Farmer;
import com.cowras.farmer_service.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @GetMapping
    public List<Farmer> getAllFarmers() {
        return farmerService.getAll();
    }

    @GetMapping("/{id}")
    public Farmer getFarmerById(@PathVariable Long id) {
        return farmerService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Farmer> createFarmer(@RequestBody Farmer farmer) {

        Farmer farmerreturn = farmerService.create(farmer);
        return ResponseEntity.ok(farmerreturn);
    }


    @GetMapping("/test")
    public String test() {
        return "Farmer Service Running";
    }
}
