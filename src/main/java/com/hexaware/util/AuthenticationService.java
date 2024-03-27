package com.hexaware.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.hexaware.controller.*;
import com.hexaware.dao.*;

public class AuthenticationService {
	private Map<String, String> userCredentials; 

    public AuthenticationService() {
        userCredentials = new HashMap<>();
        userCredentials.put("username1", "password1");
        userCredentials.put("username2", "password2");
    }

    public boolean authenticateUser(String username, String password) {
        if (userCredentials.containsKey(username)) {
            String storedPassword = userCredentials.get(username);
            if (password.equals(storedPassword)) {
                return true;
            }
        }
        return false;
    }

	public static boolean isValidPassword(String invalidUsername) {
		// TODO Auto-generated method stub
		return false;
	}

}


   /* // Method to authenticate a customer
    public boolean authenticateCustomer(String username, String password) {
        try (Connection connection = DatabaseContext.getConnection()) {
            String query = "SELECT COUNT(*) FROM Customers WHERE Username = ? AND Password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return false;
    }

    // Method to authenticate an admin
    public boolean authenticateAdmin(String username, String password) {
        try (Connection connection = DatabaseContext.getConnection()) {
            String query = "SELECT COUNT(*) FROM Admins WHERE Username = ? AND Password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return false;
    }
}*/

