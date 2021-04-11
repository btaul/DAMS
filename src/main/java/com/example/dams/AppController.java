package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private EventRepository eRepo;

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/register")
    public String processRegistration(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setStatus("active");
        repo.save(user);
        return "register_success";
    }

    @GetMapping("/create")
    public String showCreateEvent(Model model){
        model.addAttribute("event", new Event());
        return "create_event";
    }

    @PostMapping("/create")
    public String createEvent(Event event){
        event.setStatus("active");
        event.setRemaining(event.getVolume());
        eRepo.save(event);
        return "event_created";
    }

    @GetMapping("/list_users")
    public String viewUserList(Model model){
        List<User> listUsers = repo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

//    @RequestMapping(value = "/list_events", method = RequestMethod.GET)
    @GetMapping("/list_events")
    public String viewEventsList(Model model){
        List<Event> listEvents = eRepo.findAll();
        model.addAttribute("listEvents", listEvents);
        return "events";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteEvent(Model model, @PathVariable(value = "id") String id){
//        return "delete_confirm";
//    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable(value = "id") Long id){
        eRepo.dEvent(id);
        return "delete_done";
    }

//    @GetMapping("/update/{id}")
//    public String updateEvent(@PathVariable (value = "id") Long id, Model model){
//        Event event = eRepo.findById(Long.toString(id));
//        model.addAttribute("event", event);
//        return "update_event";
//    }

    @PostMapping("/update/{id}")
    public String updateEvent(@PathVariable (value = "id") Long id, Model model){
//        Event event = eRepo.findById(Long.toString(id));
        Optional<Event> optional = eRepo.findById(id);
        Event event = null;
        if (optional.isPresent()) {
            event = optional.get();
        } else {
            throw new RuntimeException(" Event not found for id :: " + id);
        }
        model.addAttribute("event", event);
        return "update_event";
    }

    @PostMapping("update")
    public String eventUpdated(Event event) {
        this.eRepo.save(event);
        return "event_updated";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Event> page = this.eRepo.findAll(pageable);

        List<Event> listEvents = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEvents", listEvents);
        return "events";
    }

}
