package com.pickcomplete.main.controller;
import com.pickcomplete.helpers.RandomString;
import com.pickcomplete.main.model.Event;
import com.pickcomplete.main.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;


    @GetMapping("/event")
    public String event(Model model) {
        model.addAttribute("title", "PickAndCompete");
        return "event";
    }

    @PostMapping("/event")
    public String postEvent(@RequestParam String eventName,@RequestParam String adminName, @RequestParam String password, @RequestParam int juryNum,
                            @RequestParam int teamNum, Model model) {
        String linkCode = RandomString.getRandomString(10);
        String juryCode = RandomString.getRandomString(10);
        Event event = new Event(eventName, adminName, password, juryNum, teamNum, linkCode, juryCode);
        System.out.println("hey");
        eventRepository.save(event);
        return String.format("redirect:/event/%s", linkCode);
    }

    @GetMapping("event/{code}")
    public String process(@PathVariable(value = "code") String linkCode, Model model) {
        Event eventwithCode = eventRepository.findByCode(linkCode);
        if (eventwithCode == null) {
            return "eventNotFound";
        }
        else {
            return "process";
        }
    }
}
