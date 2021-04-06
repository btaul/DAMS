package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        event.setStart("today");
        event.setEnd("tomorrow");
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

    @GetMapping("/delete/{id}")
    public String deleteEvent(Model model, @PathVariable(value = "id") String id){
        return "delete_confirm";
    }
//    public static void DeleteRow(String name) {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/team10", "root", "Burinbobandtom!5");
//            PreparedStatement st = connection.prepareStatement("DELETE FROM Events WHERE event = ?");
//            st.setString(1,name);
//            st.executeUpdate();
//        } catch(Exception e) {
//            System.out.println(e);
//        }
//    }
    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable(value = "id") String id){
//        Event e = eRepo.findById(id);
//        System.out.println("reached " + id);
//        Event e = eRepo.findById(id);
        eRepo.dEvent(id);
        return "delete_done";
    }

//    @Query("DELETE FROM Event e where e.event=:event")
//    public String deleteEvent(@PathVariable String id){
//        Optional<Event> e = eRepo.findById(id);
//        if(e.isPresent()){
//            eRepo.delete(e.get());
//            return "Event is deleted with id " + id;
//        } else {
//            throw new RuntimeException("Event not found for the id " + id);
//        }
//    }
}
