package com.hexaware.dao;
import java.util.List;
import com.hexaware.model.*;

public interface IVehicleService {
    Vehicle getVehicleById(String vehicleId);
    List<Vehicle> getAvailableVehicles();
    void addVehicle(Vehicle vehicleData);
    void updateVehicle(Vehicle vehicleData);
    void removeVehicle(String vehicleId);
}


