package com.glassbox.webinvoice.shared.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String description;

	private String type;

	// bi-directional many-to-one association to client
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientId")
	private Client client;

	// bi-directional many-to-many association to Item
	@ManyToMany
	@JoinTable(name = "invoice_item", joinColumns = { @JoinColumn(name = "invoice_id") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
	private List<Item> items = new ArrayList<Item>();

	public Invoice() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}