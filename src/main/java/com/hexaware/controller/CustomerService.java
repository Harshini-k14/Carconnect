package com.hexaware.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import com.hexaware.dao.*;
import com.hexaware.dao.CustomerDao;
import com.hexaware.model.Customer;

import com.google.protobuf.TextFormat.ParseException;

public abstract class CustomerService implements ICustomerService {

    private List<Customer> customers;
    private CustomerDao customerDAO;

    public CustomerService(CustomerDao customerDAO) {
        this.customerDAO = customerDAO;
        this.customers = new ArrayList<>();
    }

    public void runCustomerManagement() throws ParseException, java.text.ParseException {
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
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter customer details:");
                    System.out.print("Customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); 
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
                    System.out.print("Registration Date (format: yyyy-MM-dd): ");
                    String registrationDateString = scanner.nextLine();
                    Date registrationDate = parseDate(registrationDateString);

                    Customer newCustomer = new Customer(customerId, firstName, lastName, email, phoneNumber, address, username, password, registrationDate);
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
                    scanner.nextLine();
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

    private static Date parseDate(String dateString) throws ParseException, java.text.ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }

    @Override
    public Customer GetCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customerId) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer GetCustomerByUsername(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null; 
    }

    @Override
    public void RegisterCustomer(Customer customerData) {
        customers.add(customerData);
    }

    @Override
    public void UpdateCustomer(Customer customerData) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerID() == customerData.getCustomerID()) {
                customers.set(i, customerData);
                return;
            }
        }
        System.out.println("Customer with ID " + customerData.getCustomerID() + " not found.");
    }

    @Override
    public void DeleteCustomer(int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerID() == customerId) {
                customers.remove(i);
                return;
            }
        }
        System.out.println("Customer with ID " + customerId + " not found.");
    }
}

/*import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.hexaware.model.*;
import com.hexaware.dao.*;
public abstract class CustomerService implements ICustomerService {

    // In-memory database to store customer data
    private List<Customer> customers;

    public CustomerService() {
        this.customers = new ArrayList<>();
    }

    
    public Customer GetCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customerId) {
                return customer;
            }
        }
        return null; // Customer not found
    }

   
    public Customer GetCustomerByUsername(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null; // Username not found
    }

    
    public void RegisterCustomer(Customer customerData) {
        customers.add(customerData);
    }

    
    public void UpdateCustomer(Customer customerData) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerID() == customerData.getCustomerID()) {
                customers.set(i, customerData);
                return;
            }
        }
        System.out.println("Customer with ID " + customerData.getCustomerID() + " not found.");
    }

   
    public void DeleteCustomer(int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerID() == customerId) {
                customers.remove(i);
                return;
            }
        }
        System.out.println("Customer with ID " + customerId + " not found.");
    }


	
}
*/