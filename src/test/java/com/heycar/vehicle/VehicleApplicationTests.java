package com.heycar.vehicle;

import com.heycar.vehicle.model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VehicleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VehicleApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
//    @Value("${local.server.port}")
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
    public void testGetVehicleByCodeNotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity(getRootUrl() + "/vehicles/REN-ME", String.class);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
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

    @Test
    public void testToStringMethodOfVehicleEntity() {
        String expected = "Vehicle{code='REN-DUS', make='renault', model='duster', kw=136, year=2016, color='maroon', price=10990}";
        Vehicle vehicle = new Vehicle(
                "REN-DUS",
                "renault",
                "duster",
                136,
                2016,
                "maroon",
                10990
        );
        Assertions.assertEquals(expected, vehicle.toString());
    }
}
