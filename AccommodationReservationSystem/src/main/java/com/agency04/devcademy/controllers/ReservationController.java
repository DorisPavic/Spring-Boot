package com.agency04.devcademy.controllers;

import com.agency04.devcademy.exceptions.ExceededNumberofPersonsException;
import com.agency04.devcademy.exceptions.ReservationNotPossibleException;
import com.agency04.devcademy.model.Reservation;
import com.agency04.devcademy.dto.ReservationDTO;
import com.agency04.devcademy.services.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("api/reservation")
@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Operation(tags = "Checking Reservation", summary = "Gives list of all reservations in system")
    @GetMapping("/all")
    ResponseEntity<List<Reservation>> getAllReservations() {
        log.info("Getting all reservations");
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @Operation(tags = "Saving Reservation", summary = "Updates existing reservation")
    @PutMapping("/update/{id}")
    ResponseEntity<ReservationDTO> updateReservation(@RequestBody @Valid ReservationDTO ReservationDto, @PathVariable String id, BindingResult bindingResult) throws ReservationNotPossibleException, ExceededNumberofPersonsException {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            throw new ReservationNotPossibleException("Updating reservation not possible");
        } else {
            log.info("Getting reservation by id");
            return new ResponseEntity<>(reservationService.updateReservation(ReservationDto, Long.valueOf(id)), HttpStatus.ACCEPTED);
        }
    }

    @Operation(tags = "Saving Reservation", summary = "Creates new reservation")
    @PostMapping("/add")
    ResponseEntity<ReservationDTO> addReservation(@RequestBody @Valid ReservationDTO newReservationDTO, BindingResult bindingResult) throws ReservationNotPossibleException, ExceededNumberofPersonsException {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            throw new ReservationNotPossibleException("Reservation not possible");
        } else {
            log.info("Adding new reservation");
            return new ResponseEntity<>(reservationService.addReservation(newReservationDTO), HttpStatus.CREATED);
        }
    }

    @Operation(tags = "Saving Reservation", summary = "Confirms reservation")
    @PutMapping("/confirm/{id}")
    ResponseEntity<ReservationDTO> confirmReservation(@RequestBody @Valid ReservationDTO ReservationDto, @PathVariable String id) {
        log.info("Confirming reservation");
        return new ResponseEntity<>(reservationService.confirmReservation(ReservationDto, Long.valueOf(id)), HttpStatus.OK);
    }

}
