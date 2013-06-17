package com.glassbox.webinvoice.client.service;

import com.glassbox.webinvoice.client.ui.alert.Alert;
import com.glassbox.webinvoice.client.ui.alert.AlertLevel;
import com.glassbox.webinvoice.client.ui.controller.Main;
import com.glassbox.webinvoice.shared.entity.Client;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.List;

/**
 *
 * @author msushil
 */
public class ClientServiceClientImpl implements  ClientServiceClientInt {
    private ClientServiceAsync service;
    private Main mainui;
    
    public ClientServiceClientImpl(String url, Main mainui) {
        System.out.print(url);
        this.service = GWT.create(ClientService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
        endpoint.setServiceEntryPoint(url);
        this.mainui = mainui;
        
    }
    
    public void getAllClients() {
        this.service.getClients(new ClientCallback());
    }  
    
        private class ClientCallback implements AsyncCallback {
        public void onFailure(Throwable caught) {
            Alert.show(caught.getMessage(), AlertLevel.ERROR);
        }
        public void onSuccess(Object result) {
            mainui.UpdateClients((List<Client>)result);
        }
    }
}
