package com.hexaware.dao;
import com.hexaware.controller.*;
import com.hexaware.model.*;
import java.util.List;


public interface IReservationService {
    Reservation getReservationById(int reservationId);

    List<Reservation> getReservationsByCustomerId(int customerId);

    void createReservation(Reservation reservationData);

    void updateReservation(Reservation reservationData);

    void cancelReservation(int reservationId);
}

