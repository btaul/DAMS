package com.example.dams;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "events")
public class Event {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    public Long event;
//    @GeneratedValue(generator = "system-uuid")
    @Column(nullable = false, unique = true, length = 45)
    public String event = Integer.toString(ID_GENERATOR.addAndGet(1)/4);
//    @Id @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
//    String event;
//    @Column(nullable = false)
//    String text;

    @Column(nullable = true, unique = true, length = 45)
    public String requester;

    @Column(nullable = true, length = 20)
    public String status;

    @Column(nullable = true, length = 40)
    public String item;

    @Column(nullable = true, length = 20)
    public Integer volume;

    @Column(nullable = true, length = 20)
    public Integer remaining;

    @Column(nullable = true, length = 20)
    public Integer zip;

    @Column(nullable = true, length = 86)
    public String address;

    @Column(nullable = true, length = 20)
    public String start;

    @Column(nullable = true, length = 20)
    public String end;

    public Event(String event, String requester, String status, String item, Integer volume, Integer remaining, Integer zip, String address, String start, String end) {
        this.event = event;
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
//        this.event = Long.getLong("test");
//        this.requester = "requester";
//        this.status = "status";
//        this.item = "item";
//        this.volume = -10;
//        this.remaining = -10;
//        this.zip = -10;
//        this.address = "address";
//        this.start = "start";
//        this.end = "end";
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String id) {
        this.event = id;
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
