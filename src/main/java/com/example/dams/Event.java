package com.example.dams;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "events")
public class Event {
//    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, length = 45)
    public Long id;
//    @GeneratedValue(generator = "system-uuid")
//    @Column(nullable = false, unique = true, length = 45)
//    public String id = Integer.toString(ID_GENERATOR.addAndGet(1)/4);

    @Column(nullable = false, length = 20)
    public String status;

    @Column(nullable = false, length = 20)
    public Integer zip;

    @Column(nullable = false, length = 86)
    public String address;

    @Column(nullable = false, length = 20)
    public String start;

    @Column(nullable = false, length = 20)
    public String end;

    @Column(nullable = false, length = 45)
    public String description;

    public Event(Long id, String status, Integer zip, String address, String start, String end, String description) {
        this.id = id;
        this.status = status;
        this.zip = zip;
        this.address = address;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public Event() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String event) {
        this.description = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
