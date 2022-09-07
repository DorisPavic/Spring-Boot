package com.agency04.devcademy.services;

import com.agency04.devcademy.model.ReservationHistory;

import java.util.List;

public interface ReservationHistoryService {
    ReservationHistory findReservationHistoryById(Long id);

    List<ReservationHistory> getAllReservationHistory();
}
