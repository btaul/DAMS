package com.example.dams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EventRepository extends JpaRepository<com.example.dams.Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.event = ?1")
    com.example.dams.Event findById(String event);

    @Transactional
    @Modifying
    @Query("DELETE FROM Event e where e.event=:event")
    void dEvent(String event);
}
