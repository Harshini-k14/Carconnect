package com.hexaware.dao;

import com.hexaware.model.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private Connection connection;

    public VehicleDao(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public Vehicle getVehicleById(int vehicleId) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE VehicleId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vehicleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractVehicleFromResultSet(resultSet);
            }
        }
        return null;
    }

    public List<Vehicle> getAvailableVehicles() throws SQLException {
        List<Vehicle> availableVehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE Availability = true";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                availableVehicles.add(extractVehicleFromResultSet(resultSet));
            }
        }
        return availableVehicles;
    }

    public void addVehicle(Vehicle vehicle) throws SQLException {
        String query = "INSERT INTO vehicles (VehicleId, Model, Make, Year, Color, RegistrationNumber, Availability, DailyRate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vehicle.getVehicleID());
            statement.setString(2, vehicle.getModel());
            statement.setString(3, vehicle.getMake());
            statement.setInt(4, vehicle.getYear());
            statement.setString(5, vehicle.getColor());
            statement.setString(6, vehicle.getRegistrationNumber());
            statement.setBoolean(7, vehicle.isAvailability());
            statement.setDouble(8, vehicle.getDailyRate());
            statement.executeUpdate();
        }
    }

    public void updateVehicle(Vehicle vehicle) throws SQLException {
        String query = "UPDATE vehicles SET Model = ?, Make = ?, Year = ?, Color = ?, " +
                "RegistrationNumber = ?, Availability = ?, DailyRate = ? WHERE VehicleId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, vehicle.getModel());
            statement.setString(2, vehicle.getMake());
            statement.setInt(3, vehicle.getYear());
            statement.setString(4, vehicle.getColor());
            statement.setString(5, vehicle.getRegistrationNumber());
            statement.setBoolean(6, vehicle.isAvailability());
            statement.setDouble(7, vehicle.getDailyRate());
            statement.setInt(8, vehicle.getVehicleID());
            statement.executeUpdate();
        }
    }

    public void removeVehicle(int removeVehicleId) throws SQLException {
        String query = "DELETE FROM vehicles WHERE VehicleId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, removeVehicleId);
            statement.executeUpdate();
        }
    }
    public void updateVehicleAvailability(int vehicleId, boolean isAvailable) throws SQLException {
        String query = "UPDATE vehicles SET Availability = ? WHERE VehicleId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, isAvailable);
            statement.setInt(2, vehicleId);
            statement.executeUpdate();
        }
    }

    private Vehicle extractVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        int vehicleID = resultSet.getInt("VehicleId");
        String model = resultSet.getString("Model");
        String make = resultSet.getString("Make");
        int year = resultSet.getInt("Year");
        String color = resultSet.getString("Color");
        String registrationNumber = resultSet.getString("RegistrationNumber");
        boolean availability = resultSet.getBoolean("Availability");
        double dailyRate = resultSet.getDouble("DailyRate");
        return new Vehicle(vehicleID, model, make, year, color, registrationNumber, availability, dailyRate);
    }
}
