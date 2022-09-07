package com.agency04.devcademy.controllers;

import com.agency04.devcademy.model.Accommodation;
import com.agency04.devcademy.services.AccommodationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/accommodation")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;


    @Operation(tags= "Saving Accommodation", summary = "Saves accommodation's image")
    @PostMapping("/{id}/image")
    public ResponseEntity<Void> handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
        accommodationService.saveImageFile(Long.valueOf(id), file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(tags= "Checking Accommodations",summary = "Gives list of 10 random accommodations")
    @GetMapping("/recommendation")
    ResponseEntity<List<Accommodation>> getRecommendation() {
        log.info("Getting recommendations");
        try {
            return ResponseEntity.ok(accommodationService.getRecommendation());
        } catch (IndexOutOfBoundsException ex) {
            log.error("Not enough accommodation for shuffled list of 10!");
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(tags= "Checking Accommodations",summary = "Gives list of all accommodations for chosen location")
    @GetMapping("/location?locationId={location_id}")
    ResponseEntity<List<Accommodation>> getAccommodationByLocation(@PathVariable String location_id) {
        log.info("Getting all accommodations by location");
        return ResponseEntity.ok(accommodationService.getAccommodationByLocation(Long.parseLong(location_id)));
    }

    @GetMapping("/cat{categorisation}/people{personCount}")
    ResponseEntity<List<Accommodation>> findAccommodationByCategorisationAndPersonCount(@PathVariable String categorisation, @PathVariable String personCount) {
        log.info("Getting all accommodations by location");
        return ResponseEntity.ok(accommodationService.getAccommodationByCategorisationAndPersonCount(Integer.valueOf(categorisation),Integer.valueOf(personCount)));
    }

    @Operation(tags= "Checking Accommodations", summary = "Gives list of all accommodations in system")
    @GetMapping("/all")
    ResponseEntity<List<Accommodation>> getAllAccommodation() {
        log.info("Getting all accommodations");
        return ResponseEntity.ok(accommodationService.getAllAccommodation());
    }

    @Operation(tags= "Checking Accommodations",summary = "Finds accommodation by id")
    @GetMapping("/{id}")
    ResponseEntity<Accommodation> findAccommodationById(@PathVariable String id) {
        log.info("Finding accommodation by id");
        return ResponseEntity.ok(accommodationService.findAccommodationById(Long.parseLong(id)));
    }

    @Operation(tags= "Saving Accommodation",summary = "Creates new accommodation")
    @PostMapping("/add")
    ResponseEntity<Accommodation> addAccommodation(@RequestBody @Valid Accommodation newAccommodation) {
        log.info("Creating new accommodation.");
        return new ResponseEntity<>(accommodationService.addAccommodation(newAccommodation), HttpStatus.CREATED);
    }

    @Operation(tags= "Saving Accommodation", summary = "Updates existing accommodation")
    @PutMapping("/update/{id}")
    ResponseEntity<Accommodation> updateAccommodation(@RequestBody @Valid Accommodation newAccommodation, @PathVariable String id) {
        log.info("Updating accommodation.");
        return ResponseEntity.ok(accommodationService.updateAccommodation(newAccommodation, Long.valueOf(id)));
    }

    @Operation(tags= "Saving Accommodation",summary = "Deletes accommodation")
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseEntity<Accommodation>> deleteAccommodation(@PathVariable String id) {
        log.info("Deleting accommodation.");
        return ResponseEntity.ok(accommodationService.deleteAccommodation(Long.valueOf(id)));
    }
}


