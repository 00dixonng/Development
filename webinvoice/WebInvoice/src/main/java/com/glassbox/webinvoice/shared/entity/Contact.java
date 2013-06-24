package com.glassbox.webinvoice.shared.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name = "contact")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
        @Column(name = "contacttype")
        private ContactType contacttype;
        
        @Column(name = "contactvalue")
        private String contactvalue;

	// bi-directional many-to-one association to Suburb
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "clientId")
	private Client client;
                     
	public Contact() {
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContactType getContacttype() {
        return contacttype;
    }

    public void setContacttype(ContactType contacttype) {
        this.contacttype = contacttype;
    }

    public String getContactvalue() {
        return contactvalue;
    }

    public void setContactvalue(String contactvalue) {
        this.contactvalue = contactvalue;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
