package com.glassbox.webinvoice.server.dataaccess;

import org.springframework.stereotype.Repository;
import com.glassbox.webinvoice.shared.entity.Client;
import java.util.List;
import org.hibernate.Hibernate;

@Repository
public class ClientDAO extends BaseDAO<Client,Long>{

	public ClientDAO() {
		super(Client.class);
	}
        
        @SuppressWarnings("unchecked")
	public List<Client> getAllClientsInfo() {
            List<Client> clientList = null;

		try {
                    clientList = (List) sf.getCurrentSession()
                                               .createQuery(" from Client ").list();
                    for(Client c : clientList)
                        Hibernate.initialize(c.getAddress());                  
		} catch (Exception e) {

		}

		return clientList;
	}

}
