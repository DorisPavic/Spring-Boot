package com.agency04.devcademy.repositories;

import com.agency04.devcademy.model.Accommodation;
import com.agency04.devcademy.model.AccommodationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class AccommodationRepositoryIntegTest {

    @Autowired
    AccommodationRepository accommodationRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void injectedRepositoryIsNotNull() {
        assertNotNull(accommodationRepository);
    }

    @Test
    void findAllByCategorizationEqualsAndPersonCountGreaterThanEqual() {

        Accommodation accommodation1 = Accommodation.builder()
                .title("A1")
                .subtitle("SB1")
                .type(AccommodationType.APARTMENT)
                .categorization(3)
                .personCount(7)
                .build();
        Accommodation accommodation2 = Accommodation.builder()
                .title("A2")
                .subtitle("SB2")
                .type(AccommodationType.APARTMENT)
                .categorization(3)
                .personCount(7)
                .build();
        Accommodation accommodation3 = Accommodation.builder()
                .title("A3")
                .subtitle("SB3")
                .type(AccommodationType.APARTMENT)
                .categorization(4)
                .personCount(8)
                .build();
        accommodationRepository.save(accommodation1);
        accommodationRepository.save(accommodation2);
        accommodationRepository.save(accommodation3);

        Optional<List<Accommodation>> optionalAccommodationList = accommodationRepository.findAllByCategorizationEqualsAndPersonCountGreaterThanEqual(3, 5);
        assertEquals(2, (long) optionalAccommodationList.get().size());
        List<Accommodation> accommodationFilteredList = optionalAccommodationList.get();
        assertEquals(2, accommodationFilteredList.size());
        assertEquals(accommodationFilteredList.get(0).getTitle(), "A1");
        for (Accommodation accommodation : accommodationFilteredList) {
            assertEquals(3, accommodation.getCategorization());
        }
    }
}
