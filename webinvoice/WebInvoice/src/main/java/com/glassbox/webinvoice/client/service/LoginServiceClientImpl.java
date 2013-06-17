package com.glassbox.webinvoice.client.service;

import com.glassbox.webinvoice.shared.model.AuthenticationResult;
import com.glassbox.webinvoice.client.ui.controller.Main;
import com.glassbox.webinvoice.client.ui.alert.Alert;
import com.glassbox.webinvoice.client.ui.alert.AlertLevel;
import com.glassbox.webinvoice.shared.FieldVerifier;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author mon2
 */
public class LoginServiceClientImpl implements LoginServiceClientInt {
    private LoginServiceAsync service;
    private Main mainui;
    
    public LoginServiceClientImpl(String url, Main mainui) {
        System.out.print(url);
        this.service = GWT.create(LoginService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
        endpoint.setServiceEntryPoint(url);
        this.mainui = mainui;
    }
    
    public void authenticateUser(String name, String password) {
        //validation
        AuthenticationResult result = new AuthenticationResult();
        
        if (FieldVerifier.isBlankUserName(name)) {
            result.setAuthenticated(false);
            result.setEmail(null);
            result.setUsername(null);
            result.setMessage("Username cannot be blank.");
            result.setTagname("login");
            mainui.UpdateLoginDialog((AuthenticationResult)result);
            return;
        }
                
       if (FieldVerifier.isBlankPassword(password)) {
            result.setAuthenticated(false);
            result.setEmail(null);
            result.setUsername(null);
            result.setMessage("Password cannot be blank.");
            result.setTagname("password");
            mainui.UpdateLoginDialog((AuthenticationResult)result);
            return;
        }
       
        if (!FieldVerifier.isValidUserName(name)) {
            result.setAuthenticated(false);
            result.setEmail(null);
            result.setUsername(null);
            result.setMessage("Username should be atleast 3 characters.");
            result.setTagname("login");
            mainui.UpdateLoginDialog((AuthenticationResult)result);
            return;
        }
       
       this.service.authenticateUser(name, password, new LoginCallback());
    }
    
    private class LoginCallback implements AsyncCallback {
        public void onFailure(Throwable caught) {
            Alert.show(caught.getMessage(), AlertLevel.ERROR);
        }
        public void onSuccess(Object result) {
            mainui.UpdateLoginDialog((AuthenticationResult)result);
        }
    }
}
