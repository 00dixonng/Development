package com.glassbox.webinvoice.client.service;

import com.glassbox.webinvoice.shared.entity.Client;
import com.glassbox.webinvoice.shared.model.ClientInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author mon2
 */
public interface ClientServiceAsync {

    public void getAllClientsInfo(int start, int length, AsyncCallback<List<ClientInfo>> asyncCallback);
}
