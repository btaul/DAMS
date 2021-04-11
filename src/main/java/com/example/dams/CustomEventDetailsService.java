package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

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
    public void dEvent(Long id) throws Exception {
        Event event = repo.findById(Long.toString(id));
        if (event==null){
            throw new Exception("Unable to find event to delete");
        }
        repo.dEvent(id);
    }

    @Override
    public Page<Event> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repo.findAll(pageable);
    }

}
