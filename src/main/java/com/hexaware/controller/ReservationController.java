package com.hexaware.controller;


import com.hexaware.controller.*;
import com.hexaware.dao.*;
import com.hexaware.model.*;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.Date; // Import Date class
import java.util.List;
import java.text.SimpleDateFormat; // Import SimpleDateFormat class

public class ReservationController {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ReservationDao reservationDAO = new ReservationDao();

        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Get Reservation by ID");
            System.out.println("2. Get Reservations by Customer ID");
            System.out.println("3. Create Reservation");
            System.out.println("4. Update Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Enter Reservation ID:");
                    int reservationId = scanner.nextInt();
                    scanner.nextLine(); 
                    try {
                        Reservation reservation = reservationDAO.getReservationById(reservationId);
                        if (reservation != null) {
                            System.out.println(reservation);
                        } else {
                            System.out.println("Reservation not found.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
               
                    try {
                        System.out.println("Enter Customer ID:");
                        int createCustomerId = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.println("Enter Vehicle ID:");
                        int createVehicleId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Start Date (yyyy-MM-dd):");
                        String createStartDateString = scanner.nextLine();
                        Date createStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(createStartDateString);
                        System.out.println("Enter End Date (yyyy-MM-dd):");
                        String createEndDateString = scanner.nextLine();
                        Date createEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(createEndDateString);
                        System.out.println("Enter Total Cost:");
                        double createTotalCost = scanner.nextDouble();
                        scanner.nextLine(); 
                        System.out.println("Enter Status:");
                        String createStatus = scanner.nextLine();

                        Reservation newReservation = new Reservation(0, createCustomerId, createVehicleId, createStartDate, createEndDate, createTotalCost, createStatus);
                        reservationDAO.createReservation(newReservation);
                        System.out.println("Reservation created successfully with ID: " + newReservation.getReservationID());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                 
                    try {
                        System.out.println("Enter Reservation ID to update:");
                        int updateReservationId = scanner.nextInt();
                        scanner.nextLine(); 
                        Reservation existingReservation = reservationDAO.getReservationById(updateReservationId);
                        if (existingReservation != null) {
                            System.out.println("Enter Customer ID:");
                            int updateCustomerId = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.println("Enter Vehicle ID:");
                            int updateVehicleId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter Start Date (yyyy-MM-dd):");
                            String updateStartDateString = scanner.nextLine();
                            Date updateStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(updateStartDateString);
                            System.out.println("Enter End Date (yyyy-MM-dd):");
                            String updateEndDateString = scanner.nextLine();
                            Date updateEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(updateEndDateString);
                            System.out.println("Enter Total Cost:");
                            double updateTotalCost = scanner.nextDouble();
                            scanner.nextLine(); 
                            System.out.println("Enter Status:");
                            String updateStatus = scanner.nextLine();

                            Reservation updatedReservation = new Reservation(updateReservationId, updateCustomerId, updateVehicleId, updateStartDate, updateEndDate, updateTotalCost, updateStatus);
                            reservationDAO.updateReservation(updatedReservation);
                            System.out.println("Reservation updated successfully.");
                        } else {
                            System.out.println("Reservation not found.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    try {
                        System.out.println("Enter Reservation ID to cancel:");
                        int cancelReservationId = scanner.nextInt();
                        scanner.nextLine(); 
                        Reservation reservationToCancel = reservationDAO.getReservationById(cancelReservationId);
                        if (reservationToCancel != null) {
                            reservationDAO.cancelReservation(cancelReservationId);
                            System.out.println("Reservation cancelled successfully.");
                        } else {
                            System.out.println("Reservation not found.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }
}
}







/*public class ReservationController {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationDao reservationDAO = new ReservationDao();

        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Get Reservation by ID");
            System.out.println("2. Get Reservations by Customer ID");
            System.out.println("3. Create Reservation");
            System.out.println("4. Update Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
            case 1:
                System.out.println("Enter Reservation ID:");
                int reservationId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                try {
                    Reservation reservation = reservationDAO.getReservationById(reservationId);
                    if (reservation != null) {
                        System.out.println("Reservation ID: " + reservation.getReservationID());
                        System.out.println("Customer ID: " + reservation.getCustomerID());
                        System.out.println("Vehicle ID: " + reservation.getVehicleID());
                        System.out.println("Start Date: " + reservation.getStartDate());
                        System.out.println("End Date: " + reservation.getEndDate());
                        System.out.println("Total Cost: " + reservation.getTotalCost());
                        System.out.println("Status: " + reservation.getStatus());
                    } else {
                        System.out.println("Unable to find reservation with ID: " + reservationId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

                case 2:
                    System.out.println("Enter Customer ID:");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    try {
                        List<Reservation> reservations = reservationDAO.getReservationsByCustomerId(customerId);
                        for (Reservation reservation : reservations) {
                            System.out.println(reservation);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // Code to create reservation
                    try {
                        System.out.println("Enter Customer ID:");
                        int createCustomerId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter Vehicle ID:");
                        int createVehicleId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter Start Date (yyyy-MM-dd):");
                        String createStartDateString = scanner.nextLine();
                        Date createStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(createStartDateString);
                        System.out.println("Enter End Date (yyyy-MM-dd):");
                        String createEndDateString = scanner.nextLine();
                        Date createEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(createEndDateString);
                        System.out.println("Enter Total Cost:");
                        double createTotalCost = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter Status:");
                        String createStatus = scanner.nextLine();

                        Reservation newReservation = new Reservation(0, createCustomerId, createVehicleId, createStartDate, createEndDate, createTotalCost, createStatus);
                        reservationDAO.createReservation(newReservation);
                        System.out.println("Reservation created successfully with ID: " + newReservation.getReservationID());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    // Code to update reservation
                    try {
                        System.out.println("Enter Reservation ID to update:");
                        int updateReservationId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Reservation existingReservation = reservationDAO.getReservationById(updateReservationId);
                        if (existingReservation != null) {
                            System.out.println("Enter Customer ID:");
                            int updateCustomerId = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.println("Enter Vehicle ID:");
                            int updateVehicleId = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.println("Enter Start Date (yyyy-MM-dd):");
                            String updateStartDateString = scanner.nextLine();
                            Date updateStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(updateStartDateString);
                            System.out.println("Enter End Date (yyyy-MM-dd):");
                            String updateEndDateString = scanner.nextLine();
                            Date updateEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(updateEndDateString);
                            System.out.println("Enter Total Cost:");
                            double updateTotalCost = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            System.out.println("Enter Status:");
                            String updateStatus = scanner.nextLine();

                            Reservation updatedReservation = new Reservation(updateReservationId, updateCustomerId, updateVehicleId, updateStartDate, updateEndDate, updateTotalCost, updateStatus);
                            reservationDAO.updateReservation(updatedReservation);
                            System.out.println("Reservation updated successfully.");
                        } else {
                            System.out.println("Reservation not found.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    // Code to cancel reservation
                    try {
                        System.out.println("Enter Reservation ID to cancel:");
                        int cancelReservationId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Reservation reservationToCancel = reservationDAO.getReservationById(cancelReservationId);
                        if (reservationToCancel != null) {
                            reservationDAO.cancelReservation(cancelReservationId);
                            System.out.println("Reservation cancelled successfully.");
                        } else {
                            System.out.println("Reservation not found.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }

        System.out.println("Exiting...");
        scanner.close();
    }
}*/


