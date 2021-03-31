package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        System.out.println(user);
        System.out.println(username);
        if (user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new com.example.dams.CustomUserDetails(user);
    }
}
