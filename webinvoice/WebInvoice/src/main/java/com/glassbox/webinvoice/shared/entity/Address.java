package com.glassbox.webinvoice.shared.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the address database table.
 *
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "address1")
    private String address1;
    
    @Column(name = "address2")
    private String address2;
    
    // bi-directional many-to-one association to Suburb
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinColumn(name = "suburbId")
    private Suburb suburb;
    
    public Address() {
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Suburb getSuburb() {
        return suburb;
    }
    
    public void setSuburb(Suburb suburb) {
        this.suburb = suburb;
    }
    
    public String getAddress1() {
        return address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    
    public String getAddress2() {
        return address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }   
}