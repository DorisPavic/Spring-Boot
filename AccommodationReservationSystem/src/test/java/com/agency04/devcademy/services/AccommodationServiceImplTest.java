package com.agency04.devcademy.services;

import com.agency04.devcademy.model.Accommodation;
import com.agency04.devcademy.model.AccommodationType;
import com.agency04.devcademy.repositories.AccommodationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccommodationServiceImplTest {

    @Mock
    private AccommodationRepository accommodationRepository;


    AccommodationServiceImpl accommodationServiceImpl;

   @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accommodationServiceImpl = new AccommodationServiceImpl();

    }

    @Test
    void getAllAccommodation() {
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

        List accommodationData = new ArrayList();
        accommodationData.add(accommodation1);
        accommodationData.add(accommodation2);
        accommodationData.add(accommodation3);

        when(accommodationRepository.findAll()).thenReturn(accommodationData);

        ReflectionTestUtils.setField(accommodationServiceImpl,"accommodationRepository", accommodationRepository);

        List<Accommodation> accommodationList = accommodationServiceImpl.getAllAccommodation();
        assertEquals(accommodationList.size(), 3);
        assertEquals(accommodationList.get(0).getTitle(), "A1");
        verify(accommodationRepository, times(1)).findAll();

    }
}