package com.example.dams;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public interface EventDetails extends Serializable {
    Collection<? extends GrantedAuthority> getAuthorities();

    String getId();

    void deleteEvent();
}
