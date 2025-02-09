package com.pickcomplete.main.repo;

import com.pickcomplete.main.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM event WHERE linking_code =?", nativeQuery = true)
    Event findByCode(String code);
}
