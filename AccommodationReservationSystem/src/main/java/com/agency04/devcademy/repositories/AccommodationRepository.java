package com.agency04.devcademy.repositories;

import com.agency04.devcademy.model.Accommodation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccommodationRepository extends CrudRepository <Accommodation, Long> {

    Optional<List<Accommodation>> findAllByCategorizationEqualsAndPersonCountGreaterThanEqual(Integer categorisation, Integer personCount);
}
