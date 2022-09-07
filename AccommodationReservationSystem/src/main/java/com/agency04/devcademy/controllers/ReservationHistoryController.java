package com.agency04.devcademy.controllers;

import com.agency04.devcademy.model.ReservationHistory;
import com.agency04.devcademy.services.ReservationHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reservationHistory")
public class ReservationHistoryController {

    @Autowired
    private ReservationHistoryService reservationHistoryService;

    @Operation(tags= "Checking Reservation History", summary = "Gives list of all reservation history in system")
    @GetMapping("/all")
    ResponseEntity<List<ReservationHistory>> getAllReservationHistory() {
        log.info("Getting all reservation history");
        return ResponseEntity.ok(reservationHistoryService.getAllReservationHistory());
    }

    @Operation(tags= "Checking Reservation History", summary = "Finds reservation history by id")
    @GetMapping("/{id}")
    ResponseEntity<ReservationHistory> findReservationHistoryById(@PathVariable String id) {
        log.info("Finding reservation history by id");
        return ResponseEntity.ok(reservationHistoryService.findReservationHistoryById(Long.parseLong(id)));
    }
}
