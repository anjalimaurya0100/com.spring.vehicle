package com.heycar.vehicle.controller;

import com.heycar.vehicle.model.Vehicle;
import com.heycar.vehicle.respository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping("/vehicle")
    public Vehicle saveVehicle(@Valid @RequestBody Vehicle vehicle) {
        return this.vehicleRepository.save(vehicle);
    }

    @GetMapping("/vehicles")
    List<Vehicle> listVehicles() {
        return this.vehicleRepository.findAll();
    }

    @GetMapping("/vehicle/{code}")
    Optional<Vehicle> getVehicle(@PathVariable(name = "code") String code) {
        return this.vehicleRepository.findById(code);
    }

}
