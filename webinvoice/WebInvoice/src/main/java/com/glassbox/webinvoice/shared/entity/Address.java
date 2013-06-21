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
	@JoinColumn(name = "suburb")
	private Suburb suburb;
        
        // bi-directional many-to-one association to State
	@Column(name = "state")
	@Enumerated(value = EnumType.STRING)
	private State state;

        @Column(name = "postcode")
        private String postcode;
                
	public Address() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Suburb getSuburb() {
		return suburb;
	}

	public void setSuburb(Suburb suburb) {
		this.suburb = suburb;
	}

}