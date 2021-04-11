package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private EventService eRepo;

    @Autowired
    private RequestsRepository rRepo;

    @ModelAttribute("user")
    public User userDto() {
        return new User();
    }

    @ModelAttribute("request")
    public Requests requestDto() {
        return new Requests();
    }

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(){
        return "signup_form";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user, Model model){
        User checkUserValid = repo.findByUsername(user.getUsername());
        if(checkUserValid != null){
            model.addAttribute("inUse", user.getUsername());
            return "signup_form";
        }
        ValidPassword2 uvp = new ValidPassword2();
        if(uvp.hasErrors(user.getPassword()) && user.getPassword() != null){
            model.addAttribute("errors", uvp.getErrors());
            return "signup_form";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setStatus("active");
        repo.save(user);
        return "register_success";
    }

    @GetMapping("/list_users")
    public String viewUserList(Model model){
        List<Requests> listRequests = rRepo.findAll();
        User loggedInUser = getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        model.addAttribute("requester",listRequests);
        return "users";
    }

    @GetMapping("/request_items")
    public String recipientRequest(Model model){
        List<Event> listEvents = eRepo.findAll();
        model.addAttribute("listEvents", listEvents);
        User loggedInUser = getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        return "requestEventsTable";
    }


    @PostMapping("/request_items")
    public String requestSuccessful(@ModelAttribute("request") Requests request){
        request.setStatus("active");
        request.setRemaining(request.getVolume());
        User loggedInUser = getLoggedInUser();
        request.setRequester(loggedInUser.getUsername());
        request.setZip(loggedInUser.getZipcode());
        rRepo.save(request);
        return "request_success";
    }

    /*@GetMapping("/list_events")
    public String viewEventsList(Model model){
        List<Event> eventList = eRepo.findAll();
        model.addAttribute("listEvents", eventList);
        return "events";
    }*/

//    @RequestMapping(value = "/list_events", method = RequestMethod.GET)
    @GetMapping("/list_events")
    public String viewEventsList(Model model){
        List<Event> listEvents = eRepo.findAll();
        model.addAttribute("listEvents", listEvents);
        return "events";
    }

    public User getLoggedInUser(){
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return repo.findByUsername(username);
    }

}
