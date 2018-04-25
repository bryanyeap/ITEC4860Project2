package com.example.vehicleproject2;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Component
public class MyTasks {

    public int id = 1;
    public int row = 1;

    @Scheduled(fixedRate = 1500)
    public void automatedPost() {
        String[] makeModels = {"Toyota Camry", "Honda Civic", "Nissan Altima", "Nissan Sentra", "Ford Mustang", "Honda Accord"};
        Integer[] years = {1990, 2001, 2018, 2077, 1964, 2000};
        Double[] retail = {20161.20, 10930.00, 8399.99, 25000.00, 3600.99, 63212.00};
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/addVehicle";
        Random rand = new Random();
        Vehicle vehicle = new Vehicle(
                makeModels[rand.nextInt(6)], years[rand.nextInt(6)], retail[rand.nextInt(6)]);
        restTemplate.postForObject(url, vehicle, Vehicle.class);
    }

    @Scheduled(fixedRate = 2500)
    public void automatedPut() {
        String[] makeModels = {"Toyota Camry", "Honda Civic", "Nissan Altima", "Nissan Sentra", "Ford Mustang", "Honda Accord"};
        Integer[] years = {1990, 2001, 2018, 2077, 1964, 2000};
        Double[] retail = {20161.20, 10930.00, 8399.99, 25000.00, 3600.99, 63212.00};

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/updateVehicle/" + id;
        Random rand = new Random();
        Vehicle vehicle = new Vehicle(
                makeModels[rand.nextInt(6)], years[rand.nextInt(6)], retail[rand.nextInt(6)]);
        restTemplate.put(url, vehicle, Vehicle.class);
        id++;
    }

    @Scheduled(fixedRate = 5000)
    public void automatedDelete() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/deleteVehicle/" + row;
        restTemplate.delete(url, Vehicle.class);
        row++;
    }

    @Scheduled(fixedRate = 5000)
    public void automatedLatest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/getLatestVehicles";
        List<Vehicle> vehicles = restTemplate.getForObject(url, List.class);
        System.out.println(vehicles.get(0));
    }
}
