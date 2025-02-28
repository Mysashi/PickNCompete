package com.pickcomplete.main.repo;

import com.pickcomplete.main.model.Criteria;
import com.pickcomplete.main.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
    
}
