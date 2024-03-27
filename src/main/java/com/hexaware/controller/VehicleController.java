package com.hexaware.controller;
import com.hexaware.exception.*;
import com.hexaware.dao.VehicleDao;
import com.hexaware.model.Vehicle;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class VehicleController  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/carconnect"; 
        String username = "root"; 
        String password = "1423";

        try {
            VehicleDao vehicleDao = new VehicleDao(url, username, password);
            boolean exit = false;

            while (!exit) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Get Vehicle by ID");
                System.out.println("2. Get Available Vehicles");
                System.out.println("3. Add Vehicle");
                System.out.println("4. Update Vehicle");
                System.out.println("5. Remove Vehicle");
                System.out.println("6. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                    	 System.out.println("Enter vehicle ID:");
                    	    int vehicleId = scanner.nextInt();
                    	    scanner.nextLine();
                    	    try {
                    	        Vehicle vehicleById = vehicleDao.getVehicleById(vehicleId);
                    	        if (vehicleById == null) {
                    	            throw new VehicleNotFoundException("Vehicle with ID " + vehicleId + " not found.");
                    	        }
                    	        System.out.println("Vehicle found: " + vehicleById);
                    	    } catch (VehicleNotFoundException e) {
                    	        System.out.println("Vehicle not found: " + e.getMessage());
                    	    } catch (SQLException e) {
                    	        System.out.println("Error retrieving vehicle: " + e.getMessage());
                    	        e.printStackTrace();
                    	    }
                    	    break;
                    
                    case 2:
                        try {
                            List<Vehicle> availableVehicles = vehicleDao.getAvailableVehicles();
                            System.out.println("Available Vehicles:");
                            for (Vehicle vehicle : availableVehicles) {
                                System.out.println(vehicle); 
                            }
                        } catch (SQLException e) {
                            System.out.println("Error retrieving available vehicles: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                          
                            System.out.println("Enter vehicle details:");
                            System.out.print("Vehicle ID: ");
                            int newVehicleId = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.print("Model: ");
                            String model = scanner.nextLine();
                            System.out.print("Make: ");
                            String make = scanner.nextLine();
                            System.out.print("Year: ");
                            int year = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.print("Color: ");
                            String color = scanner.nextLine();
                            System.out.print("Registration Number: ");
                            String regNumber = scanner.nextLine();
                            System.out.print("Availability (true/false): ");
                            boolean availability = scanner.nextBoolean();
                            System.out.print("Daily Rate: ");
                            double dailyRate = scanner.nextDouble();

                            Vehicle newVehicle = new Vehicle(newVehicleId, model, make, year, color, regNumber, availability, dailyRate);

                           
                            vehicleDao.addVehicle(newVehicle);
                            System.out.println("Vehicle added successfully!");
                        } catch (SQLException e) {
                            System.out.println("Error adding new vehicle: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                           
                            System.out.println("Enter updated vehicle details:");
                            System.out.print("Vehicle ID: ");
                            int updateVehicleId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Model: ");
                            String updatedModel = scanner.nextLine();
                            System.out.print("Make: ");
                            String updatedMake = scanner.nextLine();
                            System.out.print("Year: ");
                            int updatedYear = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.print("Color: ");
                            String updatedColor = scanner.nextLine();
                            System.out.print("Registration Number: ");
                            String updatedRegNumber = scanner.nextLine();
                            System.out.print("Availability (true/false): ");
                            boolean updatedAvailability = scanner.nextBoolean();
                            System.out.print("Daily Rate: ");
                            double updatedDailyRate = scanner.nextDouble();

                           
                            Vehicle updatedVehicle = new Vehicle(updateVehicleId, updatedModel, updatedMake, updatedYear, updatedColor, updatedRegNumber, updatedAvailability, updatedDailyRate);

                            vehicleDao.updateVehicle(updatedVehicle);
                            System.out.println("Vehicle updated successfully!");
                        } catch (SQLException e) {
                            System.out.println("Error updating vehicle: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        try {
                            System.out.print("Enter vehicle ID to remove: ");
                            int removeVehicleId = scanner.nextInt();
                            scanner.nextLine(); 

                            vehicleDao.removeVehicle(removeVehicleId);
                            System.out.println("Vehicle removed successfully!");
                        } catch (SQLException e) {
                            System.out.println("Error removing vehicle: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        exit = true;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 6.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

