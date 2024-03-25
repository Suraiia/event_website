package de.gpb.eventgo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    private EventRepository repository;

    public EventController(@Autowired EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String events(Model model) {
        model.addAttribute("events", repository.findFutureEvents());
        return "events";
    }

    @GetMapping("/create-event")
    public String showEventCreationForm(Event event) {
        return "createEvent";
    }

    @PostMapping("/create-event")
    public String addEvent(@Valid Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "createEvent";
        }
        repository.save(event);
        return "redirect:/";
    }

    @GetMapping("/events-created")
    public String showAllEvents(Model model) {
        model.addAttribute("events", repository.findAll());
        return "eventsCreated";
    }
}


