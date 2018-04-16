package com.example.vehicleproject2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private VehicleDao vehicleDao;

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        return vehicleDao.create(newVehicle);
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) throws IOException {
        return vehicleDao.getById(id);
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeVehicle(@PathVariable("id") int id) throws IOException {
        Vehicle vehicle = vehicleDao.getById(id);
        if(vehicle != null) {
            vehicleDao.delete(vehicle);
            return new ResponseEntity<>("Deleted Vehicle", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid Vehicle Id", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/updateVehicle/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateVehicle(@PathVariable("id") int id, @RequestBody Vehicle vehicle) throws IOException {
        Vehicle findVehicle = vehicleDao.getById(id);
        if(findVehicle != null) {
            findVehicle.setMakeModel(vehicle.getMakeModel());
            findVehicle.setRetailPrice(vehicle.getRetailPrice());
            findVehicle.setYear(vehicle.getYear());

            vehicleDao.update(findVehicle);
            return new ResponseEntity<>("Updated Vehicle", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid vehicle id", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException {
        return vehicleDao.getLatest();
    }
}
