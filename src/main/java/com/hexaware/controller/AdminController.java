package com.hexaware.controller;

import com.hexaware.dao.AdminDao;
import com.hexaware.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.util.Scanner;

public class AdminController {
    public static void main(String[] args) {
        AdminDao adminDao = new AdminDao();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Get Admin by ID");
            System.out.println("2. Get Admin by Username");
            System.out.println("3. Register Admin");
            System.out.println("4. Update Admin");
            System.out.println("5. Delete Admin");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin ID: ");
                    int adminId = scanner.nextInt();
                    Admin adminById = adminDao.getAdminById(adminId);
                    if (adminById != null) {
                        System.out.println("Admin found: " + adminById);
                    } else {
                        System.out.println("Admin not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();
                    Admin adminByUsername = adminDao.getAdminByUsername(username);
                    if (adminByUsername != null) {
                        System.out.println("Admin found: " + adminByUsername);
                    } else {
                        System.out.println("Admin not found.");
                    }
                    break;
                case 3:
                    System.out.println("Registering a new admin...");
                    System.out.print("Enter Admin ID: ");
                    int newAdminId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Enter Role: ");
                    String role = scanner.nextLine();
                    System.out.print("Enter Join Date (YYYY-MM-DD): ");
                    String joinDateString = scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date joinDate = null;
				try {
					joinDate = dateFormat.parse(joinDateString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    Admin newAdmin = new Admin(newAdminId, firstName, lastName, email, phoneNumber, newUsername, newPassword, role, joinDate);
                    boolean registered = adminDao.registerAdmin(newAdmin);
                    if (registered) {
                        System.out.println("New admin registered successfully.");
                    } else {
                        System.out.println("Failed to register new admin.");
                    }
                    break;
                case 4:
                    System.out.println("Updating an admin...");
                    System.out.print("Enter Admin ID to update: ");
                    int adminIdToUpdate = scanner.nextInt();
                    Admin existingAdmin = adminDao.getAdminById(adminIdToUpdate);
                    if (existingAdmin != null) {
                        System.out.print("Enter New First Name: ");
                        String updatedFirstName = scanner.nextLine();
                        System.out.print("Enter New Last Name: ");
                        String updatedLastName = scanner.nextLine();
                        System.out.print("Enter New Email: ");
                        String updatedEmail = scanner.nextLine();
                        System.out.print("Enter New Phone Number: ");
                        String updatedPhoneNumber = scanner.nextLine();
                        System.out.print("Enter New Password: ");
                        String updatedPassword = scanner.nextLine();
                        System.out.print("Enter New Role: ");
                        String updatedRole = scanner.nextLine();
                        existingAdmin.setFirstName(updatedFirstName);
                        existingAdmin.setLastName(updatedLastName);
                        existingAdmin.setEmail(updatedEmail);
                        existingAdmin.setPhoneNumber(updatedPhoneNumber);
                        existingAdmin.setPassword(updatedPassword);
                        existingAdmin.setRole(updatedRole);
                        boolean updated = adminDao.updateAdmin(existingAdmin);
                        if (updated) {
                            System.out.println("Admin details updated successfully.");
                        } else {
                            System.out.println("Failed to update admin details.");
                        }
                    } else {
                        System.out.println("Admin with ID " + adminIdToUpdate + " not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Admin ID to delete: ");
                    int adminIdToDelete = scanner.nextInt();
                    boolean deleted = adminDao.deleteAdmin(adminIdToDelete);
                    if (deleted) {
                        System.out.println("Admin with ID " + adminIdToDelete + " deleted successfully.");
                    } else {
                        System.out.println("Failed to delete admin with ID " + adminIdToDelete + ".");
                    }
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        System.out.println("Exiting program.");
        scanner.close();
    }
}
