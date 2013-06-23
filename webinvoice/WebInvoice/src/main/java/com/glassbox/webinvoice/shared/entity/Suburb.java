package com.glassbox.webinvoice.shared.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the suburb database table.
 *
 */
@Entity
@Table(name = "suburb")
public class Suburb implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private int postcode;
    
    private String suburb;
    
    private String state;
    
    public Suburb() {
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public int getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
    
    public String getSuburb() {
        return this.suburb;
    }
    
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }
    
}