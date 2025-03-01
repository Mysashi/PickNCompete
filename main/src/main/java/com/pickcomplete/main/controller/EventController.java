package com.pickcomplete.main.controller;
import com.pickcomplete.helpers.JsoupLiParser;
import com.pickcomplete.helpers.RandomString;
import com.pickcomplete.main.model.Criteria;
import com.pickcomplete.main.model.Event;
import com.pickcomplete.main.model.Room;
import com.pickcomplete.main.properties.PropertiesClass;
import com.pickcomplete.main.repo.CriteriaRepository;
import com.pickcomplete.main.repo.EventRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CriteriaRepository criteriaRepository;


    @GetMapping("/event")
    public String event(Model model) {
        model.addAttribute("title", "PickAndCompete");
        return "event";
    }

    @PostMapping("/event")
    public String postEvent(@RequestParam Map<String,String> allParams, Model model) {
        System.out.println(allParams);
        String linkCode = RandomString.getRandomString(10);
        String juryCode = RandomString.getRandomString(10);
        String adminName = allParams.get("adminName");
        String eventName = allParams.get("eventName");
        String password = allParams.get("password");
        int juryNum = Integer.parseInt(allParams.get("juryNum"));
        int teamNum = Integer.parseInt(allParams.get("teamNum"));
        Event sentEvent = sendEvent(eventName, adminName, password, juryNum, teamNum, linkCode, juryCode);
        int index = 0;
        while (allParams.get(String.format("criteria[%d][initial]", index)) != null) {
            String criteriaInitial = allParams.get(String.format("criteria[%d][initial]", index));
            int criteriaFrom = Integer.parseInt(allParams.get(String.format("criteria[%d][from]", index)));
            int criteriaTo = Integer.parseInt(allParams.get(String.format("criteria[%d][to]", index)));
            sendCriteria(sentEvent, criteriaInitial, criteriaTo, criteriaFrom);
            index++;
        }

        System.out.println("sentSUCCESFULY");
        return String.format("redirect:/event/%s", linkCode);
    }




    @GetMapping("event/{code}")
    public String process(@PathVariable(value = "code") String linkCode, Model model) {
        Event eventwithCode = eventRepository.findByCode(linkCode);
        ArrayList<Criteria> criterion = new ArrayList<>();
        for (Criteria e: criteriaRepository.findAll()) {
            if (eventwithCode.getEventId() == e.getEvent().getEventId()) {
                criterion.add(e);
            }
        }

        model.addAttribute("teams", eventwithCode.getTeamNum());
        model.addAttribute("criterion", criterion);


        if (eventwithCode == null) {
            return "eventNotFound";
        }
        else {
            return "process";
        }

    }


    private Event sendEvent(String eventName, String adminName, String password, int juryNum, int teamNum, String linkCode, String juryCode) {
        Event event = new Event(eventName,
                adminName,
                password,
                juryNum,
                teamNum, linkCode, juryCode);
        Event e = eventRepository.save(event);
        return e;
    }

    private void sendCriteria(Event event, String initial, int to, int from) {
        Criteria criteria = new Criteria(event, from, to, initial);
        criteriaRepository.save(criteria);
    }
}
