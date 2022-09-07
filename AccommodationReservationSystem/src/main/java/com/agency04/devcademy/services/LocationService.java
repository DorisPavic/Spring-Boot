package com.agency04.devcademy.services;

import com.agency04.devcademy.model.Location;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationService {

    Location findLocationById(Long id);

    List<Location> getAllLocation();

    Location addLocation(Location newLocation);

    Location updateLocation(Location newLocation,Long id);

    ResponseEntity<Location> deleteLocation(Long id);
}
