package carconnect;

import java.util.ArrayList;
import java.util.List;

import com.hexaware.dao.IVehicleService;
import com.hexaware.model.Vehicle;

public abstract class MockVehicleService implements IVehicleService {
    @Override
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        availableVehicles.add(new Vehicle(1, "Toyota", "Camry", 2020, "Red", "ABC123", true, 50.0));
        availableVehicles.add(new Vehicle(2, "Honda", "Accord", 2019, "Blue", "XYZ789", true, 60.0));

        
        return availableVehicles;
    }
}
