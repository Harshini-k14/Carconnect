package com.hexaware.controller;
import com.hexaware.model.*;
import com.hexaware.dao.*;

import java.util.ArrayList;
import java.util.List;

public abstract class VehicleService implements IVehicleService {
    private List<Vehicle> vehicles;

    public VehicleService() {
        vehicles = new ArrayList<>();
    }

   
    public Vehicle getVehicleById(int i) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleID() == i) {
                return vehicle;
            }
        }
        return null;
    }


    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailability()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

  
    public void addVehicle(Vehicle vehicleData) {
        vehicles.add(vehicleData);
    }

    
    public void updateVehicle(Vehicle vehicleData) {
        Vehicle existingVehicle = getVehicleById(vehicleData.getVehicleID());
        if (existingVehicle != null) {
            existingVehicle.setModel(vehicleData.getModel());
            existingVehicle.setMake(vehicleData.getMake());
            existingVehicle.setYear(vehicleData.getYear());
            existingVehicle.setColor(vehicleData.getColor());
            existingVehicle.setRegistrationNumber(vehicleData.getRegistrationNumber());
            existingVehicle.setAvailability(vehicleData.isAvailability());
            existingVehicle.setDailyRate(vehicleData.getDailyRate());
        }
    }

 
}
