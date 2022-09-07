package com.agency04.devcademy.converters;

import com.agency04.devcademy.dto.ReservationDTO;
import com.agency04.devcademy.model.Reservation;
import com.agency04.devcademy.model.ReservationHistory;
import io.micrometer.core.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReservationConverterReservationToDTO implements Converter<Reservation, ReservationDTO> {

    @Synchronized
    @Nullable
    @Override
    public ReservationDTO convert(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        ReservationDTO reservationDto = new ReservationDTO();
        reservationDto.setId(reservation.getId());
        reservationDto.setAccommodationId(reservation.getAccommodation().getId());
        reservationDto.setUserId(reservation.getUser().getId());
        reservationDto.setType(reservation.getType());
        reservationDto.setPersonCount(reservation.getPersonCount());
        reservationDto.setSubmitted(reservation.isSubmitted());

        if (reservation.getReservationHistory() != null && reservation.getReservationHistory().size() > 0) {
            reservation.getReservationHistory()
                    .forEach((ReservationHistory reservationHistory) -> reservationDto.getReservationHistory().add(reservationHistory));
        }

        return reservationDto;
    }

}
