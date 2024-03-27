/*package com.hexaware.view;
import java.util.Scanner;
import java.text.ParseException;

import com.hexaware.controller.AdminController;
import com.hexaware.controller.CustomerController;
import com.hexaware.controller.ReservationController;
import com.hexaware.controller.VehicleController;
import com.hexaware.dao.CustomerDao;
import com.hexaware.dao.ReservationDao;
import com.hexaware.dao.VehicleDao;
import com.hexaware.util.*;

public class Dupmain {
    public static void main(String[] args) throws ParseException, java.text.ParseException {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose a service:");
            System.out.println("1. Customer Service");
            System.out.println("2. Vehicle Service");
            System.out.println("3. Reservation Service");
            System.out.println("4. Admin Service");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Initialize AuthenticationService
            AuthenticationService authService = new AuthenticationService();

            // Prompt for authentication
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            // Authenticate user
            if (!authService.authenticateUser(username, password)) {
                System.out.println("Authentication failed. Please try again or choose another service.");
                continue; // Re-prompt for authentication
            }

            // User authenticated, proceed to service
            switch (choice) {
                case 1:
                    CustomerDao customerDAO = new CustomerDao();
                    CustomerController customerController = new CustomerController(customerDAO);
                   
                case 2:
                    // Implement Vehicle Service
                    System.out.println("Vehicle Service selected.");
                   // VehicleDao vehicleDao = new VehicleDao();
                    VehicleController vehicleController = new VehicleController();
                    vehicleController.main(null);
                    break;

                case 3:
                    // Implement Reservation Service
                    System.out.println("Reservation Service selected.");
                    ReservationDao reservationDao = new ReservationDao();
                    ReservationController reservationController = new ReservationController();
                    reservationController.main(null);
                    break;

                case 4:
                    System.out.println("Admin Service selected.");
                    AdminController adminController = new AdminController();
                    adminController.main(null);
                    break;

                case 5:
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
        scanner.close();
    }
}

/*import java.util.Scanner;
import com.hexaware.controller.*;
import com.hexaware.model.*;
import com.hexaware.dao.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Dupmain {
    public static void main(String[] args) {
        CustomerDao customerDAO = new CustomerDao();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Insert Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Get Customer By ID");
            System.out.println("4. Get Customer By Username");
            System.out.println("5. Update Customer");
            System.out.println("6. Delete Customer");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter customer details:");
                    System.out.print("Customer ID: ");
                    int customerId = scanner.nextInt();
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    // Assuming Registration Date is current date
                    Date registrationDate = new Date();
                    Customer newCustomer = new Customer(0, firstName, lastName, email, phoneNumber, address, username, password, registrationDate);
                    customerDAO.insertCustomer(newCustomer);
                    System.out.println("Customer inserted successfully.");
                    break;

                case 2:
                    List<Customer> allCustomers = customerDAO.viewAllCustomers();
                    for (Customer customer : allCustomers) {
                        System.out.println(customer);
                    }
                    break;

                case 3:
                    System.out.print("Enter Customer ID: ");
                    int customerId1 = scanner.nextInt();
                    Customer customerById = customerDAO.getCustomerById(customerId1);
                    if (customerById != null) {
                        System.out.println(customerById);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Username: ");
                    String usernameInput = scanner.nextLine();
                    Customer customerByUsername = customerDAO.getCustomerByUsername(usernameInput);
                    if (customerByUsername != null) {
                        System.out.println(customerByUsername);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Customer ID to update: ");
                    int customerIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Customer existingCustomer = customerDAO.getCustomerById(customerIdToUpdate);
                    if (existingCustomer != null) {
                        System.out.println("Enter updated details:");
                        System.out.print("First Name: ");
                        firstName = scanner.nextLine();
                        System.out.print("Last Name: ");
                        lastName = scanner.nextLine();
                        System.out.print("Email: ");
                        email = scanner.nextLine();
                        System.out.print("Phone Number: ");
                        phoneNumber = scanner.nextLine();
                        System.out.print("Address: ");
                        address = scanner.nextLine();
                        System.out.print("Username: ");
                        username = scanner.nextLine();
                        System.out.print("Password: ");
                        password = scanner.nextLine();
                        // Assuming Registration Date is not updated
                        Customer updatedCustomer = new Customer(customerIdToUpdate, firstName, lastName, email, phoneNumber, address, username, password, existingCustomer.getRegistrationDate());
                        customerDAO.updateCustomer(updatedCustomer);
                        System.out.println("Customer updated successfully.");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter Customer ID to delete: ");
                    int customerIdToDelete = scanner.nextInt();
                    customerDAO.deleteCustomer(customerIdToDelete);
                    System.out.println("Customer deleted successfully.");
                    break;

                case 7:
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
        scanner.close();
    }
}*/

