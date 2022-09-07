package com.agency04.devcademy.controllers;

import com.agency04.devcademy.services.LocationService;
import com.agency04.devcademy.model.Location;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Operation(tags= "Checking Location", summary = "Gives list of all locations in system")
    @GetMapping("/all")
    ResponseEntity<List<Location>> getAllLocation() {
        log.info("Getting all locations");
        return ResponseEntity.ok(locationService.getAllLocation());
    }

    @Operation(tags= "Checking Location", summary = "Finds location by id")
    @GetMapping("/{id}")
    ResponseEntity<Location> findLocationById(@PathVariable String id) {
        log.info("Finding location by id");
        return ResponseEntity.ok(locationService.findLocationById(Long.parseLong(id)));
    }

    @Operation(tags= "Saving Location", summary = "Creates new location")
    @PostMapping("/add")
    ResponseEntity<Location> addLocation(@RequestBody @Valid Location newLocation) {
        log.info("Creating new location.");
        return new ResponseEntity<>(locationService.addLocation(newLocation), HttpStatus.CREATED);
    }

    @Operation(tags= "Saving Location", summary = "Updates existing location")
    @PutMapping("/update/{id}")
    ResponseEntity<Location> updateLocation(@RequestBody @Valid Location newLocation, @PathVariable String id) {
        log.info("Updating location.");
        return ResponseEntity.ok(locationService.updateLocation(newLocation, Long.valueOf(id)));
    }

    @Operation(tags= "Saving Location", summary = "Deletes location")
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseEntity<Location>> deleteLocation(@PathVariable String id) {
        log.info("Deleting location.");
        return ResponseEntity.ok(locationService.deleteLocation(Long.valueOf(id)));
    }
}
