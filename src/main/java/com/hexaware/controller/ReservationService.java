package com.hexaware.controller;
import com.hexaware.model.*;
import com.hexaware.dao.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationService implements IReservationService {

    private List<Reservation> reservations;

    public ReservationService() {
        this.reservations = new ArrayList<>();
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    @Override
    public List<Reservation> getReservationsByCustomerId(int customerId) {
        List<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomerID() == customerId) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    @Override
    public void createReservation(Reservation reservationData) {
        reservations.add(reservationData);
    }

    @Override
    public void updateReservation(Reservation reservationData) {
        int index = reservations.indexOf(getReservationById(reservationData.getReservationID()));
        if (index != -1) {
            reservations.set(index, reservationData);
        }
    }

    @Override
    public void cancelReservation(int reservationId) {
        Reservation reservation = getReservationById(reservationId);
        if (reservation != null) {
            reservation.setStatus("Cancelled");
        }
    }
}
