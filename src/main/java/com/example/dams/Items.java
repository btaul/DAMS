package com.example.dams;

import javax.persistence.*;
@Entity
@Table(name = "items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, length = 45)
    public Long iditems;

    @Column(nullable = false, length = 45)
    public Long eventid;

    @Column(nullable = false, length = 45)
    public String item;

    public Items(long iditems, long eventid, String item) {
        this.iditems = iditems;
        this.eventid = eventid;
        this.item = item;
    }

    public Items() {

    }

    public Long getIditems() {
        return iditems;
    }

    public void setIditems(Long iditems) {
        this.iditems = iditems;
    }

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
