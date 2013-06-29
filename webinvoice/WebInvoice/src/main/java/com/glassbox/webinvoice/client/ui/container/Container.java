package com.glassbox.webinvoice.client.ui.container;

import com.glassbox.webinvoice.shared.model.AuthenticationResult;
import com.glassbox.webinvoice.client.ui.container.pages.AboutUs;
import com.glassbox.webinvoice.client.ui.container.pages.Clients;
import com.glassbox.webinvoice.client.ui.container.pages.ContactUs;
import com.glassbox.webinvoice.client.ui.container.pages.Dashboard;
import com.glassbox.webinvoice.client.ui.container.pages.HomePage;
import com.glassbox.webinvoice.client.ui.container.pages.LoginBox;
import com.glassbox.webinvoice.client.ui.container.pages.Services;
import com.glassbox.webinvoice.client.ui.controller.Main;
import com.glassbox.webinvoice.shared.entity.Client;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;

public class Container extends Composite {
    
    private static ContainerUiBinder uiBinder = GWT
            .create(ContainerUiBinder.class);
    private HomePage home;
    private AboutUs aboutus;
    private ContactUs contactus;
    private Services services;
    private LoginBox login;
    private Dashboard dashboard;
    private Clients clients;
    private Object mainPanel;

    public HomePage getHome() {
        return home;
    }

    public void setHome(HomePage home) {
        this.home = home;
    }

    public AboutUs getAboutus() {
        return aboutus;
    }

    public void setAboutus(AboutUs aboutus) {
        this.aboutus = aboutus;
    }

    public ContactUs getContactus() {
        return contactus;
    }

    public void setContactus(ContactUs contactus) {
        this.contactus = contactus;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public LoginBox getLogin() {
        return login;
    }

    public void setLogin(LoginBox login) {
        this.login = login;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public Object getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(Object mainPanel) {
        this.mainPanel = mainPanel;
    }

    public HTMLPanel getContainerPanel() {
        return ContainerPanel;
    }

    public void setContainerPanel(HTMLPanel ContainerPanel) {
        this.ContainerPanel = ContainerPanel;
    }

    
    public void UpdateClients(List<Client> clients) {

    }
    
    public enum ContainerType {
        StandardContainer, AuthenticatedContainer
    }
    
    
    interface ContainerUiBinder extends UiBinder<Widget, Container> {
    }
    
    @UiField
            HTMLPanel ContainerPanel;
    
    public Container(Object mainPanel, ContainerType type) {
        this.mainPanel = mainPanel;
        
        if (type == ContainerType.StandardContainer) {
            home = new HomePage();
            initWidget(uiBinder.createAndBindUi(this));
            ContainerPanel.add(home);
        }
        else if (type == ContainerType.AuthenticatedContainer) {
            dashboard = new Dashboard();
            initWidget(uiBinder.createAndBindUi(this));
            ContainerPanel.add(dashboard);
        }
    }
    
    public void setContainerType(ContainerType type) {
        if (type == ContainerType.StandardContainer) {
            home = new HomePage();
            this.ContainerPanel.clear();
            this.ContainerPanel.add(home);
        }
        else if (type == ContainerType.AuthenticatedContainer) {
            dashboard = new Dashboard();
            this.ContainerPanel.clear();
            this.ContainerPanel.add(dashboard);
        }        
    }
    
    public void ShowHome() {
        this.ContainerPanel.clear();
        this.ContainerPanel.add(home);
    }
    
    public void ShowServices() {
        this.ContainerPanel.clear();
        this.ContainerPanel.add(services);
    }
    
    public void ShowContactUs() {
        this.ContainerPanel.clear();
        this.ContainerPanel.add(contactus);
    }
    
    public void ShowAboutUs() {
        this.ContainerPanel.clear();
        this.ContainerPanel.add(aboutus);
    }
    
    public void ShowLoginDialog() {
        login = new LoginBox(mainPanel);
        this.login.show();
        this.login.setLoginFocus();
    }
    
    public void HideLoginDialog() {
        this.login.hide();
    }
    
    public void UpdateLoginDialog(AuthenticationResult result) {
        this.login.UpdateLoginDialog(result);
    }

    public void ShowClients() {
        clients = new Clients(mainPanel);
        this.ContainerPanel.clear();
        this.ContainerPanel.add(clients);
    }

    public void ShowDashboard() {
        dashboard = new Dashboard();
        this.ContainerPanel.clear();
        this.ContainerPanel.add(dashboard);
    }
}
