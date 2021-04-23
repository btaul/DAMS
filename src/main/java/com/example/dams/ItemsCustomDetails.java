package com.example.dams;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ItemsCustomDetails implements ItemsDetails{
    private Items item;

    public ItemsCustomDetails(Items item){
        this.item = item;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Long getId() {
        return this.getId();
    }
}
