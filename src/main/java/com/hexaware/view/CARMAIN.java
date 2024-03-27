package com.hexaware.view;


import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.sql.SQLException;
import java.text.ParseException;

import com.hexaware.controller.AdminController;
import com.hexaware.controller.CustomerController;
import com.hexaware.controller.ReservationController;
import com.hexaware.controller.VehicleController;
import com.hexaware.controller.VehicleService;
import com.hexaware.dao.AdminDao;
import com.hexaware.dao.CustomerDao;
import com.hexaware.dao.ReservationDao;
import com.hexaware.dao.VehicleDao;
import com.hexaware.util.AuthenticationService;
//import com.hexaware.model.AdminController;
//import com.hexaware.util.ParseException;

public class CARMAIN {
    public static void main(String[] args) throws ParseException, java.text.ParseException, InputMismatchException, SQLException {
        Scanner scanner = new Scanner(System.in);
        AuthenticationService authService = new AuthenticationService();
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose a service:");
            System.out.println("1. Customer Service");
            System.out.println("2. Vehicle Service");
            System.out.println("3. Reservation Service");
            System.out.println("4. Admin Service");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
           scanner.nextLine();
           
           
         

            switch (choice) {
                case 1:
                    CustomerDao customerDAO = new CustomerDao();
                    CustomerController customerController = new CustomerController(customerDAO);
				try {
					customerController.runCustomerManagement();
				} catch (com.google.protobuf.TextFormat.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    break;

                case 2:
                    // Implement Vehicle Service
                   System.out.println("Vehicle Service selected.");
                   VehicleController vehicleController = new VehicleController();
                   vehicleController.main(null);
                    break;

                case 3:
                    // Implement Reservation Service
                    System.out.println("Reservation Service selected.");
                    ReservationController reservationController = new ReservationController();
                    reservationController.main(null);
                    break;

               /* case 3:
                    // Implement Reservation Service
                    System.out.println("Reservation Service selected.");
                    ReservationController reservationController = new ReservationController();
                    reservationController.runReservationManagement();
                    break;*/

                case 4:
                	/* System.out.println("Admin Service selected.");
                     System.out.print("Enter admin username: ");
                     String username = scanner.nextLine();
                     System.out.print("Enter admin password: ");
                     String password = scanner.nextLine();

                     if (authService.authenticateUser(username, password)) {
                         AdminController adminController = new AdminController();
                         adminController.main(null);
                     } else {
                         System.out.println("Authentication failed. Please try again.");
                     }
                     break;*/
                   System.out.println("Admin Service selected.");
                //    AdminDao adminDao = new AdminDao();
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

import com.google.protobuf.TextFormat.ParseException;
import com.hexaware.controller.*;
import com.hexaware.dao.*;
import com.hexaware.model.*;
import com.hexaware.util.*;

public class Sumamain {
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

            switch (choice) {
                case 1:
                    CustomerDao customerDAO = new CustomerDao();
                    CustomerController customerController = new CustomerController(customerDAO);
                    customerController.runCustomerManagement();
                    break;

                case 2:
                    // Implement Vehicle Service
                    System.out.println("Vehicle Service selected.");
                    VehicleDao vehicleDao = new VehicleDao();
                     VehicleController vehicleController = new VehicleController(vehicleDao);
                     vehicleController.runVehicleManagement();
                    break;

                case 3:
                	 System.out.println("Admin Service selected.");
                     AdminDao adminDao = new AdminDao(); // Assuming you have AdminDao class
                     AdminController adminController = new AdminController(adminDao);
                     adminController.runAdminManagement();
                     break;
                case 4:
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
        scanner.close();
    }
}
*/