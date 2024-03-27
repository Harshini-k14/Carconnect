package carconnect;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import com.hexaware.controller.*;
import com.hexaware.dao.*;
import com.hexaware.model.Vehicle;
import com.hexaware.util.AuthenticationService;
import com.hexaware.controller.CustomerService;
import com.hexaware.controller.VehicleService;
import com.hexaware.dao.*;
public class CarTest {
    private CustomerService customerService;
    private VehicleService vehicleService;

    @Before
    public void setUp() {
        customerService = new CustomerService();
        vehicleService = new VehicleService();
    }

    @Test
    public void testCustomerAuthenticationWithInvalidCredentials() {
        // Arrange
        String invalidUsername = "invalid_username";
        String invalidPassword = "invalid_password";

        // Act
        boolean isAuthenticated = AuthenticationService.isValidPassword(invalidUsername);

        // Assert
        assertFalse(isAuthenticated);
    }


    @Test
    public void testGettingListOfAvailableVehicles() {
        // Act
        List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();

        // Assert
        assertNotNull(availableVehicles);
        assertFalse(availableVehicles.isEmpty());
    }

    @Test
    public void testGettingListOfAllVehicles() {
        // Act
        List<Vehicle> allVehicles = vehicleService.getAvailableVehicles();

        // Assert
        assertNotNull(allVehicles);
        assertFalse(allVehicles.isEmpty());
    }
}


