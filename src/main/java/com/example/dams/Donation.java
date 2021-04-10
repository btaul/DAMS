package com.example.dams;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "donation")
public class Donation {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name = "zip")
    private Integer zip;

    @Column(name = "donorId")
    private String donorId;

    @Column(name = "item")
    private String item;

    @Column(name = "donationVolume")
    private Integer donationVolume;

    @Column(name = "eventId")
    private String eventId;

    @Column(name = "pledge")
    private String pledge;



    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Integer getZip() {
        return zip;
    }
    public void setZip(Integer zip) {
        this.zip = zip;
    }
    public String getDonorId() {
        return donorId;
    }
    public void setDonorId(String donarId) {
        this.donorId = donarId;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }

    public Integer getDonationVolume() {
        return donationVolume;
    }
    public void setDonationVolume(Integer donationVolume) {
        this.donationVolume = donationVolume;
    }

    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getPledge() {
        return pledge;
    }
    public void setPledge(String pledge) {
        this.pledge = pledge;
    }

}
