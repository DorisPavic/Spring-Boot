package com.agency04.devcademy.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table (name="users")
public class User extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 200)
    @Size(max= 200)
    @NotBlank
    private String firstName;
    @Size(max = 200)
    @Size(max= 200)
    @NotBlank
    private String lastName;
    @Column(unique = true, nullable = false)
    @Email
    @NotBlank
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;
    @Column(unique = true, nullable = false)
    @NotBlank
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @ToString.Exclude
    private Set<Reservation> userReservations=new HashSet<>();

    public User(String firstName, String lastName, String email, Set<Reservation> userReservations, Role role, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userReservations = userReservations;
        this.role = role;
        this.password = password;
    }

    public User addReservation(Reservation reservation) {
        reservation.setUser(this);
        this.userReservations.add(reservation);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
