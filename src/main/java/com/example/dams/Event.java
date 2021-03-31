package com.example.dams;
import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true, length = 45)
    public String requester;

    @Column(nullable = false, length = 20)
    public String status;

    @Column(nullable = false, length = 40)
    public String item;

    @Column(nullable = false, length = 20)
    public Integer volume;

    @Column(nullable = false, length = 20)
    public Integer remaining;

    @Column(nullable = false, length = 20)
    public Integer zip;

    @Column(nullable = false, length = 86)
    public String address;

    @Column(nullable = false, length = 20)
    public String start;

    @Column(nullable = false, length = 20)
    public String end;

    public Event(Long id, String requester, String status, String item, Integer volume, Integer remaining, Integer zip, String address, String start, String end) {
        this.id = id;
        this.requester = requester;
        this.status = status;
        this.item = item;
        this.volume = volume;
        this.remaining = remaining;
        this.zip = zip;
        this.address = address;
        this.start = start;
        this.end = end;
    }

    public Event() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
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
