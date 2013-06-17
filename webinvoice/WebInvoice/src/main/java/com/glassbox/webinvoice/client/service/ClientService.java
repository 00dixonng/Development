package com.glassbox.webinvoice.client.service;

import com.glassbox.webinvoice.shared.entity.Client;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author mon2
 */
@RemoteServiceRelativePath("/webinvoice/services/client")
public interface ClientService extends RemoteService {
    public List<Client> getClients();        
}
