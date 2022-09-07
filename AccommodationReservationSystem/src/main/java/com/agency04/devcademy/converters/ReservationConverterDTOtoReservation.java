package com.agency04.devcademy.converters;

import com.agency04.devcademy.dto.ReservationDTO;
import com.agency04.devcademy.exceptions.NotFoundException;
import com.agency04.devcademy.model.Accommodation;
import com.agency04.devcademy.model.Reservation;
import com.agency04.devcademy.model.User;
import com.agency04.devcademy.repositories.AccommodationRepository;
import com.agency04.devcademy.repositories.UserRepository;
import io.micrometer.core.lang.Nullable;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;

@Component
public class ReservationConverterDTOtoReservation implements Converter<ReservationDTO, Reservation> {

    @Autowired
    AccommodationRepository accommodationRepository;
    @Autowired
    UserRepository userRepository;

    @Synchronized
    @Nullable
    @Override
    public Reservation convert(ReservationDTO reservationDto) {
        if (reservationDto == null) {
            return null;
        }

        final Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setType(reservationDto.getType());
        reservation.setPersonCount(reservationDto.getPersonCount());
        reservation.setSubmitted(reservationDto.isSubmitted());
        Accommodation accommodation = accommodationRepository.findById(reservationDto.getAccommodationId()).orElseThrow(() -> new NotFoundException("Accommodation with id:" + reservationDto.getAccommodationId() + " does not exist"));
        accommodation.addReservation(reservation);
        User user = userRepository.findById(reservationDto.getUserId()).orElseThrow(() -> new NotFoundException("User with id:" + reservationDto.getUserId() + " does not exist"));
        user.addReservation(reservation);
        return reservation;

    }
}
