package com.glassbox.webinvoice.client.ui.controller;

import com.glassbox.webinvoice.client.ui.container.Container;
import com.glassbox.webinvoice.client.ui.container.Container.ContainerType;
import com.glassbox.webinvoice.client.ui.footer.Footer;
import com.glassbox.webinvoice.client.ui.header.Header;
import com.glassbox.webinvoice.client.ui.menu.Menu;
import com.glassbox.webinvoice.client.ui.menu.Menu.MenuType;
import com.glassbox.webinvoice.shared.entity.Client;
import com.glassbox.webinvoice.shared.model.AuthenticationResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;

public class Main extends Composite {
    
    private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
    private Header hdr;
    private Menu mnu;
    private Container container;
    private Footer footer;
    
    @UiField
            HTMLPanel MainPanel;
    
    public Main() {
        initWidget(uiBinder.createAndBindUi(this));
        initStandardSession();
    }
    
        public Header getHdr() {
        return hdr;
    }

    public void setHdr(Header hdr) {
        this.hdr = hdr;
    }

    public Menu getMnu() {
        return mnu;
    }

    public void setMnu(Menu mnu) {
        this.mnu = mnu;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    public HTMLPanel getMainPanel() {
        return MainPanel;
    }

    public void setMainPanel(HTMLPanel MainPanel) {
        this.MainPanel = MainPanel;
    }  
    
    private void initStandardSession() {
        //initialize
        hdr = new Header();
        mnu = new Menu(this, MenuType.StandardMenu);
        container = new Container(this, ContainerType.StandardContainer);
        footer = new Footer();
        //add items to the main panel
        this.MainPanel.add(hdr);
        this.MainPanel.add(mnu);
        this.MainPanel.add(container);
        this.MainPanel.add(footer);        
    }
     
    public void UpdateLoginDialog(AuthenticationResult result) {
        if (result.isAuthenticated()) {
            this.container.HideLoginDialog();
            this.MainPanel.clear();
            this.ChangeContextToAuthenticatedUser();
        }
        else {
            this.container.UpdateLoginDialog(result);
        }
    }
    
    public void UpdateClients(List<Client> clients) {
        this.container.UpdateClients(clients);        
    }

    // <editor-fold defaultstate="collapsed" desc="Context switching based on authentication">
    public void ChangeContextToAuthenticatedUser() {
        this.MainPanel.clear();
        mnu.setMenuType(MenuType.AuthenticatedMenu);
        container.setContainerType(ContainerType.AuthenticatedContainer);
        this.MainPanel.add(mnu);
        this.MainPanel.add(container);
        this.MainPanel.add(footer);
    }
    
    public void ChangeContextToNonAuthenticatedUser() {
        this.MainPanel.clear();
        mnu.setMenuType(MenuType.StandardMenu);
        container.setContainerType(ContainerType.StandardContainer);
        this.MainPanel.add(hdr);
        this.MainPanel.add(mnu);
        this.MainPanel.add(container);
        this.MainPanel.add(footer);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="View handlers">
    public void ShowLoginDialog() {
        this.container.ShowLoginDialog();
    }
    
    public void ShowHome() {
        this.container.ShowHome();
    }
    
    public void ShowAboutUs() {
        this.container.ShowAboutUs();
    }
    
    public void ShowContactUs() {
        this.container.ShowContactUs();
    }
    
    public void ShowServices() {
        this.container.ShowServices();
    }

    public void ShowClients() {
        //temporary workaround until we implement session management.
        this.container.setContainerType(ContainerType.AuthenticatedContainer);
        this.container.ShowClients();
    }

    public void ShowDashboard() {
        //temporary workaround until we implement session management.
        this.container.setContainerType(ContainerType.AuthenticatedContainer);
        this.container.ShowDashboard();
    }
    // </editor-fold>
    
    interface MainUiBinder extends UiBinder<Widget, Main> {
    }
}
