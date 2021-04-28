package com.heycar.vehicle;

import com.heycar.vehicle.model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VehicleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VehicleApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/v1";
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetAllVehicles() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.getForEntity(
                getRootUrl() + "/vehicles",
                String.class
        );
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    public void testGetVehicleByCode() {
        Vehicle vehicle = restTemplate.getForObject(getRootUrl() + "/vehicles/REN-MEG", Vehicle.class);
        Assertions.assertNotNull(vehicle);
    }

    @Test
    public void testCreateVehicle() {
        Vehicle vehicle = new Vehicle(
                "REN-DUS",
                "renault",
                "duster",
                136,
                2016,
                "maroon",
                10990
        );

        ResponseEntity<Vehicle> postResponse = restTemplate.postForEntity(getRootUrl() + "/vehicles", vehicle, Vehicle.class);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
    }
}
