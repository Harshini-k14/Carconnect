package com.hexaware.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hexaware.model.Customer;
//import com.hexaware.util.DbConnection;
import com.hexaware.util.*;
import com.hexaware.view.*;


import java.sql.*;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static final String URL = "jdbc:mysql://localhost:3306/carconnect";
    private static final String USER = "root";
    private static final String PASSWORD = "1423";

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private void connect() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void disconnect() throws SQLException {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }

    public void insertCustomer(Customer customer) {
    	 try {
    	        connect();
    	        String query = "INSERT INTO Customer (CustomerID, FirstName, LastName, Email, PhoneNumber, Address, Username, Password, RegistrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	        statement = connection.prepareStatement(query);
    	        statement.setInt(1, customer.getCustomerID());
    	        statement.setString(2, customer.getFirstName());
    	        statement.setString(3, customer.getLastName());
    	        statement.setString(4, customer.getEmail());
    	        statement.setString(5, customer.getPhoneNumber());
    	        statement.setString(6, customer.getAddress());
    	        statement.setString(7, customer.getUsername());
    	        statement.setString(8, customer.getPassword());
    	        statement.setDate(9, new Date(customer.getRegistrationDate().getTime()));
    	        statement.executeUpdate();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    } finally {
    	        try {
    	            disconnect();
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }
    }

    public List<Customer> viewAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            connect();
            String query = "SELECT * FROM Customer";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int customerId = resultSet.getInt("CustomerID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String address = resultSet.getString("Address");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                Date registrationDate = resultSet.getDate("RegistrationDate");
                customers.add(new Customer(customerId, firstName, lastName, email, phoneNumber, address, username, password, registrationDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        try {
            connect();
            String query = "SELECT * FROM Customer WHERE CustomerID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, customerId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String address = resultSet.getString("Address");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                Date registrationDate = resultSet.getDate("RegistrationDate");
                customer = new Customer(customerId, firstName, lastName, email, phoneNumber, address, username, password, registrationDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    public Customer getCustomerByUsername(String username) {
        Customer customer = null;
        try {
            connect();
            String query = "SELECT * FROM Customer WHERE Username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int customerId = resultSet.getInt("CustomerID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String address = resultSet.getString("Address");
                String password = resultSet.getString("Password");
                Date registrationDate = resultSet.getDate("RegistrationDate");
                customer = new Customer(customerId, firstName, lastName, email, phoneNumber, address, username, password, registrationDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    public void updateCustomer(Customer customer) {
        try {
            connect();
            String query = "UPDATE Customer SET FirstName=?, LastName=?, Email=?, PhoneNumber=?, Address=?, Username=?, Password=?, RegistrationDate=? WHERE CustomerID=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhoneNumber());
            statement.setString(5, customer.getAddress());
            statement.setString(6, customer.getUsername());
            statement.setString(7, customer.getPassword());
            statement.setDate(8, new Date(customer.getRegistrationDate().getTime()));
            statement.setInt(9, customer.getCustomerID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCustomer(int customerId) {
        try {
            connect();
            String query = "DELETE FROM Customer WHERE CustomerID=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, customerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
