package com.example.dams;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<com.example.dams.Event, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    com.example.dams.Event findByEmail(String email);
}
