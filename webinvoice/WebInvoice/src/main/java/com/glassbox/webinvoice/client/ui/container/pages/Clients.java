/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glassbox.webinvoice.client.ui.container.pages;

import com.glassbox.webinvoice.client.service.ClientServiceClientImpl;
import com.glassbox.webinvoice.client.ui.controller.Main;
import com.glassbox.webinvoice.shared.Constants;
import com.glassbox.webinvoice.shared.model.ClientInfo;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.List;

/**
 *
 * @author msushil
 */
public class Clients extends Composite {   
    private static ClientsUiBinder uiBinder = GWT.create(ClientsUiBinder.class);
    private ClientServiceClientImpl clientservice;
    private Main main;
    @UiField AnchorElement newclient;
    @UiField HTMLPanel container;
    
    interface ClientsUiBinder extends UiBinder<Widget, Clients> {
    }
    
    public Clients(Object main) {
        this.clientservice = new ClientServiceClientImpl(GWT.getModuleBaseURL() + "services/client", this.main);
        this.main = (Main)main;
        initWidget(uiBinder.createAndBindUi(this));
        
        VerticalPanel panel = new VerticalPanel();
        panel.setBorderWidth(1);
        panel.setWidth("100%");
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        panel.add(createClientsTable());
        
        // Add the widgets to the root panel.
        container.add(panel);
    }
    
    private CellTable createClientsTable(){
        CellTable<ClientInfo> table = new CellTable<ClientInfo>();
        table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
        
        //checkbox
        Column<ClientInfo, Boolean> checkColumn;
        checkColumn = new Column<ClientInfo, Boolean>(
                new CheckboxCell(true, false)) {
                    @Override
                    public Boolean getValue(ClientInfo object) {
                        return true;
                    }
                };
        table.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
        table.setColumnWidth(checkColumn, 40, Unit.PX);
        
        // Add a text column to show the firstname.
        TextColumn<ClientInfo> firstName =
                new TextColumn<ClientInfo>() {
                    @Override
                    public String getValue(ClientInfo object) {
                        return object.getFirstName();
                    }
                };
        table.addColumn(firstName, "Firstname");
        
        // Add a text column to show the lastname.
        TextColumn<ClientInfo> lastName =
                new TextColumn<ClientInfo>() {
                    @Override
                    public String getValue(ClientInfo object) {
                        return object.getLastName();
                    }
                };
        table.addColumn(firstName, "Lastname");
        
        // Add a text column to show the address.
        TextColumn<ClientInfo> addressColumn
                = new TextColumn<ClientInfo>() {
                    @Override
                    public String getValue(ClientInfo object) {
                        return object.getAddress();
                    }
                };
        table.addColumn(addressColumn, "Address");
        
        
        AsyncDataProvider<ClientInfo> provider = new AsyncDataProvider<ClientInfo>() {
            @Override
            protected void onRangeChanged(HasData<ClientInfo> display) {
                final int start = display.getVisibleRange().getStart();
                int length = display.getVisibleRange().getLength();
                AsyncCallback<List<ClientInfo>> callback = new AsyncCallback<List<ClientInfo>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(caught.getMessage());
                    }
                    @Override
                    public void onSuccess(List<ClientInfo> result) {
                        updateRowData(start, result);
                    }
                };
                //call remote service to fetch the data.
                clientservice.getAllClients(start, length, callback);
            }
        };
        
        provider.addDataDisplay(table);       
        SimplePager pager = new SimplePager();
        pager.setDisplay(table);
        
        // Add a selection model to handle user selection.
        final SingleSelectionModel<ClientInfo> selectionModel
                = new SingleSelectionModel<ClientInfo>();
        table.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        ClientInfo selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            Window.alert("You selected: " + selected.getFirstName());
                        }
                    }
                });
        table.setWidth(Constants.STANDARD_GRID_WIDTH, true);
        table.setPageSize(Constants.STANDARD_GRID_PAGESIZE);
        return table;
    }
    
}