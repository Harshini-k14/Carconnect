package com.hexaware.dao;

import com.hexaware.model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/carconnect";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1423";

    private static final String SELECT_BY_ID = "SELECT * FROM reservation WHERE ReservationId = ?";
    private static final String SELECT_BY_CUSTOMER_ID = "SELECT * FROM reservation WHERE CustomerId = ?";
    private static final String INSERT_RESERVATION = "INSERT INTO reservation (CustomerId, VehicleId, StartDate, EndDate, TotalCost, Status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_RESERVATION = "UPDATE reservation SET CustomerId = ?, VehicleId = ?, StartDate = ?, EndDate = ?, TotalCost = ?, Status = ? WHERE ReservationId = ?";
    private static final String CANCEL_RESERVATION = "UPDATE reservation SET Status = 'Cancelled' WHERE ReservationId = ?";

    public Reservation getReservationById(int reservationId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, reservationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractReservationFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    public List<Reservation> getReservationsByCustomerId(int customerId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(SELECT_BY_CUSTOMER_ID)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    reservations.add(extractReservationFromResultSet(resultSet));
                }
            }
        }
        return reservations;
    }
 /*   public void createReservation(Reservation reservation) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(INSERT_RESERVATION, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, reservation.getCustomerID());
            statement.setInt(2, reservation.getVehicleID());
            statement.setDate(3, new java.sql.Date(reservation.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(reservation.getEndDate().getTime()));
            statement.setDouble(5, reservation.getTotalCost());
            statement.setString(6, reservation.getStatus());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int reservationId = generatedKeys.getInt(1);
                    reservation.setReservationID(reservationId);
                } else {
                    throw new SQLException("Creating reservation failed, no ID obtained.");
                }
            }
        }
    }*/
///this below
 /* public void createReservation(Reservation reservation) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(INSERT_RESERVATION, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, reservation.getCustomerID());
            statement.setInt(2, reservation.getVehicleID());
            statement.setDate(3, new java.sql.Date(reservation.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(reservation.getEndDate().getTime()));
            statement.setDouble(5, reservation.getTotalCost());
            statement.setString(6, reservation.getStatus());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setReservationID(generatedKeys.getInt(1));
                }
            }
        }
    }*/

    public void createReservation(Reservation reservation) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(INSERT_RESERVATION, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, reservation.getCustomerID());
            statement.setInt(2, reservation.getVehicleID());
            statement.setDate(3, new java.sql.Date(reservation.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(reservation.getEndDate().getTime()));
            statement.setDouble(5, reservation.getTotalCost());
            statement.setString(6, reservation.getStatus());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setReservationID(generatedKeys.getInt(1));
                    // Update vehicle availability
                    updateVehicleAvailability(conn, reservation.getVehicleID(), false); // Assuming reservation makes the vehicle unavailable
                }
            }
        }
    }

    private void updateVehicleAvailability(Connection conn, int vehicleId, boolean isAvailable) throws SQLException {
        String query = "UPDATE vehicles SET Availability = ? WHERE VehicleId = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setBoolean(1, isAvailable);
            statement.setInt(2, vehicleId);
            statement.executeUpdate();
        }
    }

  
  
  
  
  
    public void updateReservation(Reservation reservation) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(UPDATE_RESERVATION)) {
            statement.setInt(1, reservation.getCustomerID());
            statement.setInt(2, reservation.getVehicleID());
            statement.setDate(3, new java.sql.Date(reservation.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(reservation.getEndDate().getTime()));
            statement.setDouble(5, reservation.getTotalCost());
            statement.setString(6, reservation.getStatus());
            statement.setInt(7, reservation.getReservationID());
            statement.executeUpdate();
        }
    }

    public void cancelReservation(int reservationId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(CANCEL_RESERVATION)) {
            statement.setInt(1, reservationId);
            statement.executeUpdate();
        }
    }

    private Reservation extractReservationFromResultSet(ResultSet resultSet) throws SQLException {
        int reservationId = resultSet.getInt("ReservationId");
        int customerId = resultSet.getInt("CustomerId");
        int vehicleId = resultSet.getInt("VehicleId");
        Date startDate = resultSet.getDate("StartDate");
        Date endDate = resultSet.getDate("EndDate");
        double totalCost = resultSet.getDouble("TotalCost");
        String status = resultSet.getString("Status");
        return new Reservation(reservationId, customerId, vehicleId, startDate, endDate, totalCost, status);
    }
}
