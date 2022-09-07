package com.agency04.devcademy.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "location", uniqueConstraints = {@UniqueConstraint(name = "UniqueTitleSubtitleAndPostalCode", columnNames = {"title", "subtitle", "postalCode"})})
public class Location extends TitleSubtitleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 5)
    private Integer postalCode;

    @OneToMany(mappedBy = "location")
    @ToString.Exclude
    private Set<Accommodation> accommodations;


    public Location addAccommodation(Accommodation accommodation) {
        accommodation.setLocation(this);
        this.accommodations.add(accommodation);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(getId(), location.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
