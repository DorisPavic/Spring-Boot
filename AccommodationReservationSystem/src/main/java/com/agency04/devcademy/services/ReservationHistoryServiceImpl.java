package com.agency04.devcademy.services;

import com.agency04.devcademy.exceptions.NotFoundException;
import com.agency04.devcademy.model.ReservationHistory;
import com.agency04.devcademy.repositories.ReservationHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ReservationHistoryServiceImpl implements ReservationHistoryService {

    @Autowired
    public ReservationHistoryRepository reservationHistoryRepository;

    @Override
    public ReservationHistory findReservationHistoryById(Long id) {
        return reservationHistoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation history not found"));
    }

    @Override
    public List<ReservationHistory> getAllReservationHistory() {
        return (List<ReservationHistory>) reservationHistoryRepository.findAll();
    }
}
