package de.gpb.eventgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class ParticipantController {

    private ParticipantsRepository participantRepository;
    private EventRepository eventRepository;

    public ParticipantController(@Autowired ParticipantsRepository participantRepository, @Autowired EventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/add-participant")
    public String openDetailDescr(@RequestParam Long eventId, Participant participant, Model model) {
        Optional<Event> maybeEvent = eventRepository.findById(eventId);
        Event event = maybeEvent.orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        model.addAttribute("event", event);
        return "addParticipant";
    }

    @PostMapping("/add-participant")
    public String addParticipant(@RequestParam Long eventId,
                                 Participant participant, BindingResult result, Model model) {

        Optional<Event> maybeEvent = eventRepository.findById(eventId);
        Event event = maybeEvent.orElseThrow(() -> new ResponseStatusException(NOT_FOUND)); //404
        if (result.hasErrors()) {
            return "addParticipant";
        }
        participantRepository.save(participant);
        event.getParticipant().add(participant);
        eventRepository.save(event);
        return "redirect:/ ";
    }
}
