package com.example.dams;
import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

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
    public String event;

    public Event(Long id, String status, Integer zip, String address, String start, String end, String event) {
        this.id = id;
        this.status = status;
        this.zip = zip;
        this.address = address;
        this.start = start;
        this.end = end;
        this.event = event;
    }

    public Event() {

    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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
