package com.agency04.devcademy.services;

import com.agency04.devcademy.model.Accommodation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AccommodationService {

    void saveImageFile(Long accommodationId, MultipartFile file);

    List<Accommodation> getRecommendation();

    List<Accommodation> getAccommodationByLocation(Long location_id);

    List<Accommodation> getAccommodationByCategorisationAndPersonCount(Integer categorisation, Integer personCount);

    Accommodation findAccommodationById(Long id);

    List<Accommodation> getAllAccommodation();

    Accommodation addAccommodation(Accommodation newAccommodation);

    Accommodation updateAccommodation(Accommodation newAccommodation, Long id);

    ResponseEntity<Accommodation> deleteAccommodation(Long id);


}
