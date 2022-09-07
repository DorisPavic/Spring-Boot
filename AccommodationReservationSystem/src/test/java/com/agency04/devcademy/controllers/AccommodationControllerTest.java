package com.agency04.devcademy.controllers;

import com.agency04.devcademy.services.AccommodationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccommodationControllerTest {

    @Mock
    AccommodationService accommodationService;

    @InjectMocks
    AccommodationController accommodationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accommodationController = new AccommodationController();
    }

    @Test
    void getRecommendation() {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(accommodationController).build();
       ReflectionTestUtils.setField(accommodationController,"accommodationService", accommodationService);
        try {
            mockMvc.perform(get("/api/accommodation/recommendation"))
                    .andExpect(status().isOk());
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}