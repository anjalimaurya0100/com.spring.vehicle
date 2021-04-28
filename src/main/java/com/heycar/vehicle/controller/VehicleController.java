package com.heycar.vehicle.controller;

import com.heycar.vehicle.exception.ResourceNotFoundException;
import com.heycar.vehicle.model.Vehicle;
import com.heycar.vehicle.respository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping("/vehicles")
    public Vehicle saveVehicle(@Valid @RequestBody Vehicle vehicle) {
        return this.vehicleRepository.save(vehicle);
    }

    @GetMapping("/vehicles")
    public List<Vehicle> listVehicles() {
        return this.vehicleRepository.findAll();
    }

    @GetMapping("/vehicles/{code}")
    public ResponseEntity<Vehicle> getVehicleByCode(@PathVariable(value = "code") String code) throws ResourceNotFoundException {
        Optional<Vehicle> vehicle = this.vehicleRepository.findById(code);
        if (!vehicle.isPresent()) {
            throw new ResourceNotFoundException("vehicle not found for code " + code);
        } else {
            return ResponseEntity.ok(vehicle.get());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
