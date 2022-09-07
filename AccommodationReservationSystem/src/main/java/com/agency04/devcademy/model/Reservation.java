package com.agency04.devcademy.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;
    @Enumerated(EnumType.STRING)
    private ReservationType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Temporal(TemporalType.DATE)
    private java.util.Date checkIn;
    @Temporal(TemporalType.DATE)
    private java.util.Date checkOut;
    @Min(1)
    private Integer personCount;
    private boolean submitted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    @ToString.Exclude
    private Set<ReservationHistory> reservationHistory;

    public Reservation addReservationHistory(ReservationHistory reservationHistory) {
        reservationHistory.setReservation(this);
        this.reservationHistory.add(reservationHistory);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
