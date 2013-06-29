/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glassbox.webinvoice.server.service;

import com.glassbox.webinvoice.client.service.ClientService;
import com.glassbox.webinvoice.server.dataaccess.ClientDAO;
import com.glassbox.webinvoice.shared.entity.Client;
import com.glassbox.webinvoice.shared.model.ClientInfo;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mon2
 */
@SuppressWarnings("serial")
@Transactional(rollbackFor = RuntimeException.class)
@Service("client")
public class ClientServiceImpl extends RemoteServiceServlet implements
        ClientService {
    @Autowired
    private ClientDAO clientDAO;
    
    public List<ClientInfo> getAllClientsInfo(int start, int length) {
        List<ClientInfo> allClientsInfo = new ArrayList<ClientInfo>();
        List<Client> clientList;
        ClientInfo info;
        
        clientList = clientDAO.getAllClientsInfo();
        
        for(Client client : clientList) {
            info = new ClientInfo();
            info.setFirstName(client.getFirstname());
            info.setLastName(client.getLastname());
            info.setAddress(client.getAddress().getAddress1() + " " + 
                    client.getAddress().getSuburb().getSuburb() + " " +
                    client.getAddress().getSuburb().getState() + " " +
                    Integer.toString(client.getAddress().getSuburb().getPostcode()));
            allClientsInfo.add(info);
        } 
        return allClientsInfo;
    }    
}
