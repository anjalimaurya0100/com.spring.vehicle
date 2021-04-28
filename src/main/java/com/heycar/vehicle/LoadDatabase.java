package com.heycar.vehicle;

import com.heycar.vehicle.model.Vehicle;
import com.heycar.vehicle.respository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            log.info("Preloading " + vehicleRepository.save(
                    new Vehicle(
                            "REN-MEG",
                            "renault",
                            "megane",
                            132,
                            2014,
                            "red",
                            13990
                    )
            ));
            log.info("Preloading " + vehicleRepository.save(
                    new Vehicle(
                            "REN-DUS",
                            "renault",
                            "duster",
                            136,
                            2016,
                            "maroon",
                            10990
                    )
            ));
        };
    }
}
