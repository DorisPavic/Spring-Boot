package com.agency04.devcademy.repositories;

import com.agency04.devcademy.model.ReservationHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationHistoryRepository extends CrudRepository<ReservationHistory, Long> {
}
