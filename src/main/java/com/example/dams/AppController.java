package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private EventRepository eRepo;

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

    @GetMapping("/create")
    public String showCreateEvent(Model model){
        model.addAttribute("event", new Event());
        return "create_event";
    }

    @PostMapping("/create")
    public String createEvent(Event event, Model model){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(event.getStart(),dtf);
        LocalDate endDate = LocalDate.parse(event.getEnd(),dtf);
        LocalDate localDate = LocalDate.now();
        boolean isBeforeLocal = startDate.isBefore(localDate);
        if(isBeforeLocal){
            model.addAttribute("errorsLocal", "Start Date starts before Local Date");
            return "create_event";
        }
        boolean isBeforeStart = endDate.isBefore(startDate);
        if (isBeforeStart){
            model.addAttribute("errorsStart", "End Date starts before Start Date");
            return "create_event";
        }
        event.setStatus("active");
        event.setId(Integer.toUnsignedLong(14));
        eRepo.save(event);
        return "event_created";
    }

    @GetMapping("/list_users")
    public String viewUserList(Model model){
        List<Requests> listRequests = rRepo.findAll();
        User loggedInUser = getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        model.addAttribute("requester",listRequests);
        return "users";
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

    @GetMapping("/request_items")
    public String recipientRequest(Model model){
        List<Event> listEvents = eRepo.findAll();
        model.addAttribute("listEvents", listEvents);
        User loggedInUser = getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        return "requestEventsTable";
    }

    @PostMapping("/request_items")
    public String requestSuccessful(@ModelAttribute("request") Requests request, Model model){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expiredDate = LocalDate.parse(request.getExpire(),dtf);
        boolean isBefore = expiredDate.isBefore(localDate);
        if (isBefore){
            List<Event> listEvents = eRepo.findAll();
            model.addAttribute("listEvents", listEvents);
            User loggedInUser = getLoggedInUser();
            model.addAttribute("user", loggedInUser);
            model.addAttribute("errors", "Invalid Expiration Date");
            return "requestEventsTable";
        }
        request.setStatus("active");
        request.setRemaining(request.getVolume());
        User loggedInUser = getLoggedInUser();
        request.setRequester(loggedInUser.getUsername());
        request.setZip(loggedInUser.getZipcode());
        rRepo.save(request);
        return "request_success";
    }

    @GetMapping("/expire_items")
    public String expireManual( Model model){
        List<Requests> items = rRepo.findAll();
        model.addAttribute("listRequests", items);
        User loggedInUser = getLoggedInUser();
        model.addAttribute("user",loggedInUser);
        return "expire_items";
    }

    @PostMapping("/expire_items/{id}")
    public String expireAdmin(@PathVariable(value = "id") Long id){
        rRepo.eRequest(id);
        return "expire_done";
    }

    @GetMapping("/donate_items")
    public String donorRequest(Model model){

        List<Event> listEvents = eRepo.findAll();
        model.addAttribute("listEvents", listEvents);
        User loggedInUser = getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        List<Requests> listRequests = rRepo.findAll();
        model.addAttribute("requester",listRequests);


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


    // display list of donations
    @GetMapping("/donation")
    public String viewDonationPage(Model model) {

        return findPaginatedDonation(1, "donorId", "asc", model);
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

        return "new_response";
    }


    @GetMapping("/backtoDonation")
    public String backtoDonation(Model model) {
        // create model attribute to bind form data
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
//        List<Donation> list
        List<Requests> listRequests = rRepo.findAll();
        model.addAttribute("requester",listRequests);
        return "redirect:/donation";
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "index";
    }

    @PostMapping("/savePledge")
    public String saveDonation(@ModelAttribute("donation") Donation donation, Model model) {
        // save donation to database
        User loggedInUser = getLoggedInUser();
        donation.setDonorId(loggedInUser.getUsername());
        donation.setEventId("Wait to be matched");
        donation.setPledge("Y");
        donationService.saveDonation(donation);
        List<Requests> listRequests = rRepo.findAll();
        model.addAttribute("requester",listRequests);
        return "redirect:/donation";
    }
    @PostMapping("/saveResponse")
    public String saveResponse(@ModelAttribute("donation") Donation donation, Model model) {
        // save donation to database
        User loggedInUser = getLoggedInUser();
        donation.setDonorId(loggedInUser.getUsername());
        donation.setPledge("N");
        donationService.saveDonation(donation);

        List<Requests> listRequests = rRepo.findAll();
        saveDonationToRequest(donation, listRequests);
        model.addAttribute("requester",listRequests);

        return "redirect:/donation";
    }

    @PostMapping("/continueResponse")
    public String continueResponse(@ModelAttribute("donation") Donation donation, Model model) {
        // save donation to database
        List<Requests> listRequests = rRepo.findAll();
        model.addAttribute("requester",listRequests);


        return "/new_response1";
    }

    @PostMapping("/continueResponse2")
    public String continueResponse2(@ModelAttribute("donation") Donation donation, Model model) {
        // save donation to database
        List<Requests> listRequests = rRepo.findAll();
        model.addAttribute("requester",listRequests);

        for (int i = 0; i < listRequests.size(); i++) {
            if (listRequests.get(i).getEventsID().equals(donation.getEventId()) &&
                    listRequests.get(i).getItem().equals(donation.getItem())) {
                model.addAttribute("req",listRequests.get(i));
            }
        }

        return "/new_response2";
    }






    @PostMapping("/saveUpdate")
    public String saveUpdate(@ModelAttribute("donation") Donation donation, Model model) {
        // save donation to database
        User loggedInUser = getLoggedInUser();
        donation.setDonorId(loggedInUser.getUsername());
        donationService.saveDonation(donation);
        List<Requests> listRequests = rRepo.findAll();
        if(donation.getPledge().equals("N")) {
            saveDonationToRequest(donation, listRequests);
        }
        model.addAttribute("requester",listRequests);
        return "redirect:/donation";
    }

    private void saveDonationToRequest(@ModelAttribute("donation") Donation donation, List<Requests> listRequests) {
        for (int i = 0; i < listRequests.size(); i++) {
            if (listRequests.get(i).getEventsID().equals(donation.getEventId()) &&
                    listRequests.get(i).getItem().equals(donation.getItem())) {
                Integer volume = listRequests.get(i).getRemaining();
                Integer donated = donation.getDonationVolume();
                if(volume > donated) listRequests.get(i).setRemaining(volume - donated);
                else listRequests.get(i).setRemaining(0);
                rRepo.save(listRequests.get(i));
            }
        }
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        // get donation from the service
        Donation donation = donationService.getDonationById(id);

        // set donation as a model attribute to pre-populate the form
        model.addAttribute("donation", donation);
        List<Requests> listRequests = rRepo.findAll();

        //before update, recover the previous remainVolume in Request table
        returnRemainingToRequestTable(model, listRequests, donation);


        for (int i = 0; i < listRequests.size(); i++) {
            if (listRequests.get(i).getEventsID().equals(donation.getEventId()) &&
                    listRequests.get(i).getItem().equals(donation.getItem())) {
                model.addAttribute("req",listRequests.get(i));
            }
        }

        return "update_donation";
    }

    @GetMapping("/deleteDonation/{id}")
    public String deleteDonation(@PathVariable (value = "id") long id, Model model) {

        // call delete donation method
        List<Requests> listRequests = rRepo.findAll();


        // get donation from the service
        Donation donation = donationService.getDonationById(id);

        // set donation as a model attribute to pre-populate the form
//        model.addAttribute("donation", donation);


        //return the previous donationVolume
        returnRemainingToRequestTable(model, listRequests, donation);


        this.donationService.deleteDonationById(id);

        return "redirect:/donation";
    }

    private void returnRemainingToRequestTable(Model model, List<Requests> listRequests, Donation donation) {
        for (int i = 0; i < listRequests.size(); i++) {

            if (listRequests.get(i).getEventsID().equals(donation.getEventId()) &&
                    listRequests.get(i).getItem().equals(donation.getItem())) {
                Integer remain = listRequests.get(i).getRemaining();
                Integer prevDonateVolume = donation.getDonationVolume();
                listRequests.get(i).setRemaining(remain+prevDonateVolume);
                rRepo.save(listRequests.get(i));
            }
        }

        model.addAttribute("requester",listRequests);
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginatedDonation(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Donation> page = donationService.findPaginatedDonation(pageNo, pageSize, sortField, sortDir);
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
        List<Requests> listRequests = rRepo.findAll();
        model.addAttribute("requester",listRequests);
        return "donation";
    }


//    public User getLoggedInUser1(){
//        String username;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        } else {
//            throw new RuntimeException(" Event not found for id :: " + id);
//        }
//        model.addAttribute("event", event);
//        return "update_event";
//    }

    @PostMapping("update")
    public String eventUpdated(Event event) {
        this.eRepo.save(event);
        return "event_updated";
    }

    @GetMapping("match")
    public String match(Model model){
//        List<Event> listEvents = eRepo.findAll();
//        model.addAttribute("listEvents", listEvents);
        User loggedInUser = getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        List<Donation> listDonations = donationService.getAllDonations();
        model.addAttribute("listDonations", listDonations);
        return "match_pledge";
    }

    @GetMapping("matchPledge/{id}")
    public String matchToEvent(Model model, @PathVariable (value = "id") long id){
        User loggedInUser = getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        List<Requests> listRequests = rRepo.findAll();
        model.addAttribute("listRequests", listRequests);
        Donation donation = donationService.getDonationById(id);
        model.addAttribute("donation", donation);
        return "match_request";
    }

    @GetMapping("matchPledge/{donationId}/{eventId}")
    public String matched(Model model, @PathVariable (value = "donationId") long donationId,
                          @PathVariable (value = "eventId") String eventId){
        Donation donation = donationService.getDonationById(donationId);
        donation.setEventId(eventId);
        donation.setPledge("N");
//        donationService.saveDonation(donation);
//        return "redirect:/list_events";
        // save donation to database
        donationService.saveDonation(donation);
        List<Requests> listRequests = rRepo.findAll();
        if(donation.getPledge().equals("N")) {
            saveDonationToRequest(donation, listRequests);
        }
//        model.addAttribute("requester",listRequests);
        return "redirect:/list_events";
    }

//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model) {
//        int pageSize = 5;
//
//        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//                Sort.by(sortField).descending();
//
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//        Page<Event> page = this.eRepo.findAll(pageable);
//
//        List<Event> listEvents = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//        model.addAttribute("listEvents", listEvents);
//        return "events";
//    }

}
