package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomEventDetailsService implements EventDetailsService{

    @Autowired
    EventRepository repo;

    @Override
    public EventDetails loadEventByID(String id) throws Exception {
        Event event = repo.findById(id);
        if (event==null){
            throw new Exception("Event not found");
        }
        return new com.example.dams.CustomEventDetails(event);
    }

    @Override
    public void dEvent(String id) throws Exception {
        Event event = repo.findById(id);
        if (event==null){
            throw new Exception("Unable to find event to delete");
        }
        repo.dEvent(id);
    }
}
