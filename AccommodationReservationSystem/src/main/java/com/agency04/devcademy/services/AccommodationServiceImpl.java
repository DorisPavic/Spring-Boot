package com.agency04.devcademy.services;

import com.agency04.devcademy.exceptions.NotFoundException;
import com.agency04.devcademy.model.Accommodation;
import com.agency04.devcademy.model.Location;
import com.agency04.devcademy.repositories.LocationRepository;
import com.agency04.devcademy.repositories.AccommodationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AccommodationServiceImpl implements AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public void saveImageFile(Long accommodationId, MultipartFile file) {
        try {
            Accommodation accommodation = accommodationRepository.findById(accommodationId).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            accommodation.setImage(byteObjects);
            accommodationRepository.save(accommodation);

        } catch (IOException ioException) {
            log.error("Error occurred while saving image file", ioException);
            ioException.printStackTrace();
        }
    }

    @Override
    public List<Accommodation> getRecommendation() {
        List<Accommodation> recommendationList = (List<Accommodation>) accommodationRepository.findAll();
        Collections.shuffle(recommendationList);
        return recommendationList.subList(0, 10);
    }

    @Override
    public List<Accommodation> getAccommodationByLocation(Long location_id) {
        Location locationById = locationRepository.findById(location_id).orElseThrow(() -> new NotFoundException("Location not found"));
        return new ArrayList<>(locationById.getAccommodations());
    }

    @Override
    public List<Accommodation> getAccommodationByCategorisationAndPersonCount(Integer categorisation, Integer personCount) {
        List<Accommodation> AccommodationFiltredList = accommodationRepository.findAllByCategorizationEqualsAndPersonCountGreaterThanEqual(categorisation,personCount).orElseThrow(() -> new NotFoundException("Accommodation not found"));
        return AccommodationFiltredList;
    }

    @Override
    public Accommodation findAccommodationById(Long id) {
        return accommodationRepository.findById(id).orElseThrow(() -> new NotFoundException("Accommodation not found"));
    }

    @Override
    public List<Accommodation> getAllAccommodation() {
        return (List<Accommodation>) accommodationRepository.findAll();
    }

    @Override
    public Accommodation addAccommodation(Accommodation newAccommodation) {
        return accommodationRepository.save(newAccommodation);
    }

    @Override
    public Accommodation updateAccommodation(Accommodation newAccommodation, Long id) {

        return accommodationRepository.findById(id)
                .map(accommodation -> {
                    accommodation.setTitle(newAccommodation.getTitle());
                    accommodation.setSubtitle(newAccommodation.getSubtitle());
                    accommodation.setDescription(newAccommodation.getDescription());
                    accommodation.setType(newAccommodation.getType());
                    accommodation.setCategorization(newAccommodation.getCategorization());
                    accommodation.setPersonCount(newAccommodation.getPersonCount());
                    accommodation.setImage(newAccommodation.getImage());
                    accommodation.setFreeCancelation(newAccommodation.isFreeCancelation());
                    accommodation.setPrice(newAccommodation.getPrice());
                    accommodation.setLocation(newAccommodation.getLocation());
                    return accommodationRepository.save(accommodation);
                })
                .orElseThrow(() -> new NotFoundException("Accommodation not found"));

    }

    @Override
    public ResponseEntity<Accommodation> deleteAccommodation(Long id) {
        Accommodation existingAccommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new NotFoundException("Accommodation not found"));
        this.accommodationRepository.delete(existingAccommodation);
        return ResponseEntity.ok().build();
    }

}

