package com.example.dams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<com.example.dams.Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.id = ?1")
    com.example.dams.Event findById(String id);

    @Query("SELECT e FROM Event e WHERE e.id = ?1")
    Optional<Event> findById(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Event e where e.id=:event")
    void dEvent(Long event);
}
