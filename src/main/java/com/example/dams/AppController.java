package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private EventService eRepo;

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

    @GetMapping("/list_users")
    public String viewUserList(Model model){
        List<User> listUsers = repo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
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

}
