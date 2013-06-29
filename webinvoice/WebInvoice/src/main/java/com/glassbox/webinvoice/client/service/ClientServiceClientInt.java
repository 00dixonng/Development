package com.glassbox.webinvoice.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author msushil
 */
public interface ClientServiceClientInt {
    void getAllClients(int start, int length, AsyncCallback callback);
}
