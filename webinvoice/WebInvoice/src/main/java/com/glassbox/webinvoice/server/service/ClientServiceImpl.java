/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glassbox.webinvoice.server.service;

import com.glassbox.webinvoice.client.service.ClientService;
import com.glassbox.webinvoice.server.dataaccess.ClientDAO;
import com.glassbox.webinvoice.shared.entity.Client;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
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
    
    public List<Client> getClients() {
        return clientDAO.findAll();
    }
    
}
