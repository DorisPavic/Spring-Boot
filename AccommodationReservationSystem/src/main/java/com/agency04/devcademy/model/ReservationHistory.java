package com.agency04.devcademy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class ReservationHistory{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date entryTimestamp;
    @Enumerated(EnumType.STRING)
    private ReservationType fromType;
    @Enumerated(EnumType.STRING)
    private ReservationType toType;
    @ManyToOne
    @JoinColumn(name="reservation_id")
    private Reservation reservation;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationHistory)) return false;
        ReservationHistory that = (ReservationHistory) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
