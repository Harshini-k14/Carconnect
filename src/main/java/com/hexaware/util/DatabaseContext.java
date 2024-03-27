package com.hexaware.util;
import java.sql.*;
import com.hexaware.exception.*;

public class DatabaseContext {
    private static final String URL = "jdbc:mysql://localhost:3306/carconnect";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1423";

    public static Connection getConnection() throws DatabaseConnectionException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error connecting to the database", e);
        }
        return connection;
    }
}




/*
public class DatabaseContext {
	static Connection con;
	public static Connection getConnection()
	{
		try {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carconnect", "root", "1423");
		}
		catch(SQLException sep)
		{
			sep.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args)
	{
		System.out.println(getConnection());
	}
	
}
*/