package com.hexaware.model;

import java.util.Date;

public class Reservation {
    private int reservationID;
    private int customerID;
    private int vehicleID;
    private Date startDate;
    private Date endDate;
    private double totalCost;
    private String status;

    public Reservation(int reservationID, int customerID, int vehicleID, Date startDate, Date endDate, double totalCost, String status) {
        this.reservationID = reservationID;
        this.customerID = customerID;
        this.vehicleID = vehicleID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.status = status;
    }

    public int getReservationID() {
        return reservationID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void calculateTotalCost() {
    }
    @Override
    public String toString() {
        return "Reservation ID: " + reservationID + 
               ", Customer ID: " + customerID + 
               ", Vehicle ID: " + vehicleID + 
               ", Start Date: " + startDate + 
               ", End Date: " + endDate + 
               ", Total Cost: " + totalCost + 
               ", Status: " + status;
    }
}
