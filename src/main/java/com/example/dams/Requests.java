package com.example.dams;
import javax.persistence.*;

@Entity
@Table(name = "requests")
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long requestID;

    @Column(nullable = false, length = 45)
    public String eventID;

    @Column(nullable = false, length = 45)
    public String requester;

    @Column(nullable = false, length = 45)
    public String status;

    @Column(nullable = false, length = 45)
    public String item;

    @Column(nullable = false, length = 45)
    public Integer volume;

    @Column(nullable = false, length = 45)
    public Integer remaining;

    @Column(nullable = false, length = 45)
    public Integer zip;

    @Column(nullable = false, length = 45)
    public String expire;

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public Requests(){
        this.requestID = null;
        this.eventID = null;
        this.requester = null;
        this.status = null;
        this.item = null;
        this.volume = null;
        this.remaining = null;
        this.zip = null;
        this.expire = null;
    }
    public Requests(long requestID, String eventID, String requester,String status, String item, int volume, int remaining, int zip, String expire) {
        this.requestID = requestID;
        this.eventID = eventID;
        this.requester = requester;
        this.status = status;
        this.item = item;
        this.volume = volume;
        this.remaining = remaining;
        this.zip = zip;
        this.expire = expire;
    }

    public Long getRequestsID() {
        return requestID;
    }

    public void setRequestsID(Long requestsID) {
        this.requestID = requestsID;
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

    public String getEventsID() {
        return eventID;
    }

    public void setEventsID(String eventsID) {
        this.eventID = eventsID;
    }
}

