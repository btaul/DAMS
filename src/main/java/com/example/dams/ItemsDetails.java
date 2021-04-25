package com.example.dams;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;


public interface ItemsDetails  extends Serializable{
        Collection<? extends GrantedAuthority> getAuthorities();

        Long getId();
}
