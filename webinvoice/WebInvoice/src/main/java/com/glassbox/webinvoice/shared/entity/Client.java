package com.glassbox.webinvoice.shared.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.view.client.ProvidesKey;

/**
 * The persistent class for the customer database table.
 *
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String firstname;
    
    private String lastname;
    
    // bi-directional many-to-one association to Address
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "addressId")
    private Address address;
    
    // bi-directional many-to-one association to Invoice
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Invoice> invoices = new ArrayList<Invoice>();
    
    @OneToMany(mappedBy = "customer", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Email> emails = new ArrayList<Email>();
    
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @OneToOne
    private User userId;
    
    public List<Email> getEmails() {
        return emails;
    }
    
    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
    
    public Client() {
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public Address getAddress() {
        return this.address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public List<Invoice> getInvoices() {
        return this.invoices;
    }
    
    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
        
    /**
     * The key provider that provides the unique ID of a contact.
     */
    public static final ProvidesKey<Client> KEY_PROVIDER = new ProvidesKey<Client>() {
        @Override
        public Object getKey(Client item) {
            return item == null ? null : item.getId();
        }
    };
}