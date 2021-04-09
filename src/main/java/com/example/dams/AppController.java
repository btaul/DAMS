package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private EventService eRepo;

    @Autowired
    private DonationService donationService;

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
    public String processRegistration(@ModelAttribute("user") User user){
        ValidPassword2 uvp = new ValidPassword2();
        if(uvp.hasErrors(user.getPassword())){
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

    @GetMapping("/donate_items")
    public String donorRequest(Model model){

        List<Event> listEvents = eRepo.findAll();
        model.addAttribute("listEvents", listEvents);
        User loggedInUser = getLoggedInUser();
        model.addAttribute("user", loggedInUser);

        return "donation";
    }


    @GetMapping("/list_events")
    public String viewEventsList(Model model){
        List<Event> listEvents = eRepo.findAll();
        model.addAttribute("listEvents", listEvents);
        User loggedInUser = getLoggedInUser();
        if (loggedInUser!=null) model.addAttribute("user", loggedInUser);
        return "events";
    }



    // display list of donations
    @GetMapping("/donation")
    public String viewDonationPage(Model model) {

        return findPaginated(1, "donorId", "asc", model);
    }

    @GetMapping("/showNewDonationForm")
    public String showNewDonationForm(Model model) {
        // create model attribute to bind form data
        Donation donation = new Donation();
        donation.setPledge("Y");
        model.addAttribute("donation", donation);
        return "new_pledge";
    }

    @GetMapping("/responseToRequest")
    public String responseToRequest(Model model) {
        // create model attribute to bind form data
        Donation donation = new Donation();
        donation.setPledge("N");
        model.addAttribute("donation", donation);
        List<Event> listEvents = eRepo.findAll();
        model.addAttribute("listEvents", listEvents);

        List<Requests> listRequests = rRepo.findAll();
        model.addAttribute("requester",listRequests);
        return "new_response";
    }


    @GetMapping("/backtoDonation")
    public String backtoDonation(Model model) {
        // create model attribute to bind form data
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        return "redirect:/donation";
    }

    @PostMapping("/savePledge")
    public String saveDonation(@ModelAttribute("donation") Donation donation) {
        // save donation to database
        User loggedInUser = getLoggedInUser();
        donation.setDonorId(loggedInUser.getUsername());
        donation.setEventId("Wait to be matched");
        donation.setPledge("Y");
        donationService.saveDonation(donation);
        return "redirect:/donation";
    }
    @PostMapping("/saveResponse")
    public String saveResponse(@ModelAttribute("donation") Donation donation) {
        // save donation to database
        User loggedInUser = getLoggedInUser();
        donation.setDonorId(loggedInUser.getUsername());
        donation.setPledge("N");
        donationService.saveDonation(donation);
        return "redirect:/donation";
    }

    @PostMapping("/continueResponse")
    public String continueResponse(@ModelAttribute("donation") Donation donation) {
        // save donation to database

        return "/new_response1";
    }






    @PostMapping("/saveUpdate")
    public String saveUpdate(@ModelAttribute("donation") Donation donation) {
        // save donation to database
        User loggedInUser = getLoggedInUser();
        donation.setDonorId(loggedInUser.getUsername());
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

        User loggedInUser = getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        return "donation";
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
