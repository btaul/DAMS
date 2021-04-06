package com.example.dams;

public interface EventDetailsService {
    EventDetails loadEventByID(String var1) throws Exception;

    void dEvent(String id) throws Exception;
}
