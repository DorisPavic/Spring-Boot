package com.agency04.devcademy.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "accommodation")
public class Accommodation extends TitleSubtitleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;
    @Enumerated(value = EnumType.STRING)
    private AccommodationType type;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer categorization;
    @Min(1)
    @NotNull
    private Integer personCount;
    @NotNull
    private double price;
    @Lob
    private Byte[] image;
    @Column(columnDefinition = "boolean default true")
    private boolean freeCancelation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accommodation")
    @ToString.Exclude
    private Set<Reservation> reservations = new HashSet<>();

    @Builder
    public Accommodation(String title, String subtitle, String description, AccommodationType type, Integer categorization, Integer personCount, Byte[] image, boolean freeCancelation, double price) {
        super(title, subtitle);
        this.description = description;
        this.type = type;
        this.categorization = categorization;
        this.personCount = personCount;
        this.image = image;
        this.freeCancelation = freeCancelation;
        this.price = price;
    }

    public Accommodation addReservation(Reservation reservation) {
        reservation.setAccommodation(this);
        this.reservations.add(reservation);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accommodation)) return false;
        Accommodation that = (Accommodation) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
