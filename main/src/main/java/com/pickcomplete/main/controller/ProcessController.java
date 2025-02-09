package com.pickcomplete.main.controller;

import com.pickcomplete.main.model.Criteria;
import com.pickcomplete.main.model.Event;
import com.pickcomplete.main.repo.CriteriaRepository;
import com.pickcomplete.main.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

public class ProcessController {

    @Autowired
    private EventRepository eventRepository;

}
