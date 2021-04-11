package com.example.dams;

import org.springframework.data.domain.Page;

public interface EventDetailsService {
    EventDetails loadEventByID(String var1) throws Exception;

//    void dEvent(String id) throws Exception;

    void dEvent(Long id) throws Exception;

    Page<Event> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
