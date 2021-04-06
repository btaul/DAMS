package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private EventService eRepo;

    @Autowired
    private DonationService donationService;

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



    // display list of donations
    @GetMapping("/donation")
    public String viewHomePage(Model model) {
        return findPaginated(1, "eventId", "asc", model);
    }

    @GetMapping("/showNewDonationForm")
    public String showNewDonationForm(Model model) {
        // create model attribute to bind form data
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        return "new_donation";
    }

    @GetMapping("/backtoDonation")
    public String backtoDonation(Model model) {
        // create model attribute to bind form data
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        return "redirect:/donation";
    }

    @PostMapping("/saveDonation")
    public String saveDonation(@ModelAttribute("donation") Donation donation) {
        // save donation to database
        donationService.saveDonation(donation);
        return "redirect:/donation";
    }



    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        // get donation from the service
        Donation donation = donationService.getDonationById(id);

        // set donation as a model attribute to pre-populate the form
        model.addAttribute("donation", donation);
        return "update_donation";
    }

    @GetMapping("/deleteDonation/{id}")
    public String deleteDonation(@PathVariable (value = "id") long id) {

        // call delete donation method
        this.donationService.deleteDonationById(id);
        return "redirect:/donation";
    }



    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Donation> page = donationService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Donation> listDonations = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listDonations", listDonations);
        return "donation";
    }















}
