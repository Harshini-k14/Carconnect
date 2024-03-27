package com.hexaware.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.hexaware.dao.*;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.model.*;
import com.hexaware.util.*;

public class ReportGenerator {

   
    public void generateCustomerReservationReport(int customerId) throws DatabaseConnectionException {
        try (Connection connection = DatabaseContext.getConnection()) {
            String query = "SELECT * FROM Reservations WHERE CustomerID = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, customerId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    System.out.println("Reservation Report for Customer ID: " + customerId);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("ReservationID | VehicleID | StartDate    | EndDate      | TotalCost");
                    System.out.println("--------------------------------------------------------------------");
                    while (resultSet.next()) {
                        int reservationId = resultSet.getInt("ReservationID");
                        int vehicleId = resultSet.getInt("VehicleID");
                        Date startDate = resultSet.getDate("StartDate");
                        Date endDate = resultSet.getDate("EndDate");
                        double totalCost = resultSet.getDouble("TotalCost");
                        
                      
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedStartDate = dateFormat.format(startDate);
                        String formattedEndDate = dateFormat.format(endDate);
                        
                      
                        System.out.printf("%-13d | %-9d | %-12s | %-12s | %.2f%n", reservationId, vehicleId, formattedStartDate, formattedEndDate, totalCost);
                    }
                    System.out.println("--------------------------------------------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void generateAvailableVehiclesReport() throws DatabaseConnectionException {
        try (Connection connection = DatabaseContext.getConnection()) {
            String query = "SELECT * FROM Vehicles WHERE Availability = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "Available");
                try (ResultSet resultSet = statement.executeQuery()) {
                    System.out.println("Available Vehicles Report");
                    System.out.println("----------------------------------------------");
                    System.out.println("VehicleID | Model    | Make    | Year | Color");
                    System.out.println("----------------------------------------------");
                    while (resultSet.next()) {
                        int vehicleId = resultSet.getInt("VehicleID");
                        String model = resultSet.getString("Model");
                        String make = resultSet.getString("Make");
                        int year = resultSet.getInt("Year");
                        String color = resultSet.getString("Color");
                        
                      
                        System.out.printf("%-10d | %-8s | %-6s | %-4d | %-6s%n", vehicleId, model, make, year, color);
                    }
                    System.out.println("----------------------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}

