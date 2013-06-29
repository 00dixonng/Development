package com.glassbox.webinvoice.server.dataaccess;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.glassbox.webinvoice.shared.entity.Address;
@Repository
public class AddressDAO extends BaseDAO<Address, Long> {

	public AddressDAO() {
		super(Address.class);
		// TODO Auto-generated constructor stub
	}

}
