/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glassbox.webinvoice.client.service;

import com.glassbox.webinvoice.client.ui.controller.Main;
import com.glassbox.webinvoice.shared.entity.Client;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.List;

/**
 *
 * @author msushil
 */
public class ClientServiceClientImpl implements  ClientServiceClientInt {
    private ClientService service;
    
    public ClientServiceClientImpl(String url) {
        System.out.print(url);
        this.service = GWT.create(ClientService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
        endpoint.setServiceEntryPoint(url);
    }
    
    public List<Client> getAllClients() {
        return this.service.getClients();
    }    
}
