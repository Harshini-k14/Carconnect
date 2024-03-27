package com.hexaware.dao;
import com.hexaware.controller.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.hexaware.model.*;

public class AdminDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/carconnect";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1423";

    private static final String GET_ADMIN_BY_ID_QUERY = "SELECT * FROM admin WHERE adminid=?";
    private static final String GET_ADMIN_BY_USERNAME_QUERY = "SELECT * FROM admin WHERE username=?";
    private static final String INSERT_ADMIN_QUERY = "INSERT INTO admin (adminid, firstname, lastname, email, phonenumber, username, password, role, joindate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admin SET firstname=?, lastname=?, email=?, phonenumber=?, password=?, role=? WHERE adminid=?";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admin WHERE adminid=?";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public Admin getAdminById(int adminId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ADMIN_BY_ID_QUERY)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractAdminFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Admin getAdminByUsername(String username) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ADMIN_BY_USERNAME_QUERY)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractAdminFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registerAdmin(Admin adminData) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ADMIN_QUERY)) {
            statement.setInt(1, adminData.getAdminID());
            statement.setString(2, adminData.getFirstName());
            statement.setString(3, adminData.getLastName());
            statement.setString(4, adminData.getEmail());
            statement.setString(5, adminData.getPhoneNumber());
            statement.setString(6, adminData.getUsername());
            statement.setString(7, adminData.getPassword());
            statement.setString(8, adminData.getRole());
            statement.setDate(9, new Date(adminData.getJoinDate().getTime()));
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAdmin(Admin adminData) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            statement.setString(1, adminData.getFirstName());
            statement.setString(2, adminData.getLastName());
            statement.setString(3, adminData.getEmail());
            statement.setString(4, adminData.getPhoneNumber());
            statement.setString(5, adminData.getPassword());
            statement.setString(6, adminData.getRole());
            statement.setInt(7, adminData.getAdminID());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAdmin(int adminId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, adminId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Admin extractAdminFromResultSet(ResultSet resultSet) throws SQLException {
        int adminID = resultSet.getInt("adminid");
        String firstName = resultSet.getString("firstname");
        String lastName = resultSet.getString("lastname");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phonenumber");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");
        Date joinDate = resultSet.getDate("joindate");
        return new Admin(adminID, firstName, lastName, email, phoneNumber, username, password, role, joinDate);
    }
}