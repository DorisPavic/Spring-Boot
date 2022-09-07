package com.agency04.devcademy.services;

import com.agency04.devcademy.exceptions.ExceededNumberofPersonsException;
import com.agency04.devcademy.exceptions.NotFoundException;
import com.agency04.devcademy.model.Reservation;
import com.agency04.devcademy.model.ReservationHistory;
import com.agency04.devcademy.repositories.ReservationHistoryRepository;
import com.agency04.devcademy.repositories.ReservationRepository;
import com.agency04.devcademy.converters.ReservationConverterDTOtoReservation;
import com.agency04.devcademy.converters.ReservationConverterReservationToDTO;
import com.agency04.devcademy.dto.ReservationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationHistoryRepository reservationHistoryRepository;
    @Autowired
    private ReservationConverterDTOtoReservation reservationConverterDTOtoReservation;
    @Autowired
    private ReservationConverterReservationToDTO reservationConverterReservationToDTO;

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservationList = new ArrayList<>();
        reservationRepository.findAll().iterator().forEachRemaining(reservationList::add);
        if (reservationList.isEmpty()) {
            throw new NotFoundException("No reservations in system");
        }
        return reservationList;
    }

    @Override
    public ReservationDTO addReservation(ReservationDTO reservationDto) throws ExceededNumberofPersonsException {
        Reservation newReservation = reservationConverterDTOtoReservation.convert(reservationDto);
        assert newReservation != null;
        if (newReservation.getPersonCount() != null && newReservation.getPersonCount() > newReservation.getAccommodation().getPersonCount()) {
            log.error("Selected person count bigger then accommodation person count");
            throw new ExceededNumberofPersonsException("Selected person count is bigger then permitted number of people for accommodation.");
        } else {
            Reservation savedReservation = reservationRepository.save(newReservation);
            log.info("Reservation saved with id:" + savedReservation.getId());
            return reservationConverterReservationToDTO.convert(savedReservation);
        }
    }

    @Override
    @Transactional
    public ReservationDTO updateReservation(ReservationDTO reservationDto, Long id) throws ExceededNumberofPersonsException {
        Reservation reservationFound = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
        reservationFound.setCheckIn(reservationDto.getCheckIn());
        reservationFound.setCheckOut(reservationDto.getCheckOut());

        if (reservationDto.getPersonCount() != null && reservationDto.getPersonCount() > reservationFound.getAccommodation().getPersonCount()) {
            log.error("Selected person count bigger then accommodation person count");
            throw new ExceededNumberofPersonsException("Selected person count is bigger then permitted number of people for accommodation.");
        } else {
            reservationFound.setPersonCount(reservationDto.getPersonCount());
        }

        if (!reservationFound.getType().equals(reservationDto.getType())) {
            ReservationHistory reservationHistory = new ReservationHistory();
            reservationHistory.setFromType(reservationFound.getType());
            reservationHistory.setToType(reservationDto.getType());

            reservationFound.setType(reservationDto.getType());

            reservationHistory.setEntryTimestamp(new Date());
            reservationFound.addReservationHistory(reservationHistory);

            log.debug("Reservation with id:" + reservationFound.getId() + " saved in reservation history");
            reservationHistoryRepository.save(reservationHistory);
        }
        reservationRepository.save(reservationFound);
        log.debug("Reservation updated with id:" + reservationFound.getId());

        return reservationConverterReservationToDTO.convert(reservationFound);
    }

    @Override
    public ReservationDTO confirmReservation(ReservationDTO reservationDto, Long id) {
        Reservation reservationFound = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
        reservationFound.setSubmitted(true);
        reservationRepository.save(reservationFound);
        log.debug("Reservation with id:" + reservationFound.getId()+" confirmed!");
        return reservationConverterReservationToDTO.convert(reservationFound);
    }
}


