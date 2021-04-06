package com.example.dams;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;

public class CustomEventDetails implements EventDetails{
    private Event event;

    public CustomEventDetails(Event event){
        this.event = event;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getId(){
        return this.getId();
    }

    @Override
    public void deleteEvent() {

    }
}
