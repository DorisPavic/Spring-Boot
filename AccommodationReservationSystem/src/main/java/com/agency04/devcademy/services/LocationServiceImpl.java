package com.agency04.devcademy.services;

import com.agency04.devcademy.exceptions.NotFoundException;
import com.agency04.devcademy.model.Location;
import com.agency04.devcademy.repositories.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location findLocationById(Long id) {
        return locationRepository.findById(id).orElseThrow(()-> new NotFoundException("Location not found"));
    }

    @Override
    public List<Location> getAllLocation() {
        return (List<Location>) locationRepository.findAll();
    }

    @Override
    public Location addLocation(Location newLocation) {
        return locationRepository.save(newLocation);
    }

    @Override
    public Location updateLocation(Location newLocation, Long id) {
        return locationRepository.findById(id)
                .map(location -> {
                    location.setTitle(newLocation.getTitle());
                    location.setSubtitle(newLocation.getSubtitle());
                    location.setPostalCode(newLocation.getPostalCode());
                    return locationRepository.save(location);
                })
                .orElseThrow(() -> new NotFoundException("Location not found"));
    }

    @Override
    public ResponseEntity<Location> deleteLocation(Long id) {
        Location existingLocation=this.locationRepository.findById(id).orElseThrow(() -> new NotFoundException("Location not found"));
        return ResponseEntity.ok().build();
    }
}
