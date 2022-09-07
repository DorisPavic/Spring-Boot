package com.agency04.devcademy.dto;

import com.agency04.devcademy.model.ReservationHistory;
import com.agency04.devcademy.model.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Long id;
    @NotNull
    private Long accommodationId;
    @NotNull
    private ReservationType type;
    @NotNull
    private Long userId;
    @Future
    private java.util.Date checkIn;
    @Future
    private java.util.Date checkOut;
    @Min(1)
    private Integer personCount;
    @Column(columnDefinition = "boolean default false")
    private boolean submitted;
    private Set<ReservationHistory> reservationHistory = new HashSet<>();
}
