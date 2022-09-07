package com.agency04.devcademy.services;

import com.agency04.devcademy.exceptions.ExceededNumberofPersonsException;
import com.agency04.devcademy.exceptions.ReservationNotPossibleException;
import com.agency04.devcademy.model.Reservation;
import com.agency04.devcademy.dto.ReservationDTO;

import java.util.List;


public interface ReservationService {
    List<Reservation> getAllReservations();
    ReservationDTO addReservation(ReservationDTO reservationDto) throws ExceededNumberofPersonsException;
    ReservationDTO updateReservation(ReservationDTO reservationDto, Long id) throws ReservationNotPossibleException, ExceededNumberofPersonsException;
    ReservationDTO confirmReservation(ReservationDTO reservationDto, Long id);
}
