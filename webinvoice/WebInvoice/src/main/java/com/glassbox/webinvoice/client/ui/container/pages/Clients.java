/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glassbox.webinvoice.client.ui.container.pages;

import com.glassbox.webinvoice.client.service.ClientServiceClientImpl;
import com.glassbox.webinvoice.client.ui.controller.Main;
import com.glassbox.webinvoice.shared.Constants;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.DateCell;
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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author msushil
 */
public class Clients extends Composite {
    public static class Contact {
        private final String address;
        private final Date birthday;
        private final String name;
        
        public Contact(String name, Date birthday, String address) {
            this.name = name;
            this.birthday = birthday;
            this.address = address;
        }
    }
    
    @SuppressWarnings("deprecation")
    private static final List<Contact> CONTACTS = Arrays.asList(
            new Contact("John", new Date(80, 4, 12), "123 Fourth Avenue"),
            new Contact("Joe", new Date(85, 2, 22), "22 Lance Ln"),
            new Contact("George",new Date(46, 6, 6),"1600 Pennsylvania Avenue"));
    
    
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
        panel.setBorderWidth(0);
        panel.setWidth("100%");
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        panel.add(createClientsTable());
        
        // Add the widgets to the root panel.
        container.add(panel);
    }
    
    private CellTable createClientsTable(){
        CellTable<Contact> table = new CellTable<Contact>();
        table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
        
        //checkbox
        Column<Contact, Boolean> checkColumn;
        checkColumn = new Column<Contact, Boolean>(
                new CheckboxCell(true, false)) {
                    @Override
                    public Boolean getValue(Contact object) {
                        return true;
                    }
                };
        table.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
        table.setColumnWidth(checkColumn, 40, Unit.PX);
        
        // Add a text column to show the name.
        TextColumn<Contact> nameColumn =
                new TextColumn<Contact>() {
                    @Override
                    public String getValue(Contact object) {
                        return object.name;
                    }
                };
        table.addColumn(nameColumn, "Name");
        
        // Add a date column to show the birthday.
        DateCell dateCell = new DateCell();
        Column<Contact, Date> dateColumn
                = new Column<Contact, Date>(dateCell) {
                    @Override
                    public Date getValue(Contact object) {
                        return object.birthday;
                    }
                };
        table.addColumn(dateColumn, "Birthday");
        
        // Add a text column to show the address.
        TextColumn<Contact> addressColumn
                = new TextColumn<Contact>() {
                    @Override
                    public String getValue(Contact object) {
                        return object.address;
                    }
                };
        table.addColumn(addressColumn, "Address");
        
        AsyncDataProvider<Contact> provider = new AsyncDataProvider<Contact>() {
            @Override
            protected void onRangeChanged(HasData<Contact> display) {
                int start = display.getVisibleRange().getStart();
                int end = start + display.getVisibleRange().getLength();
                end = end >= CONTACTS.size() ? CONTACTS.size() : end;
                List<Contact> sub = CONTACTS.subList(start, end);
                updateRowData(start, sub);
            }
        };
        provider.addDataDisplay(table);
        provider.updateRowCount(CONTACTS.size(), true);
        
        SimplePager pager = new SimplePager();
        pager.setDisplay(table);
        
        // Add a selection model to handle user selection.
        final SingleSelectionModel<Contact> selectionModel
                = new SingleSelectionModel<Contact>();
        table.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        Contact selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            Window.alert("You selected: " + selected.name);
                        }
                    }
                });
        
        // Set the total row count. This isn't strictly necessary,
        // but it affects paging calculations, so its good habit to
        // keep the row count up to date.
        table.setRowCount(CONTACTS.size(), true);
        table.setWidth("100", true);
        table.setHeight(Constants.STANDARD_GRID_HEIGHT);
        
        // Push the data into the widget.
        //table.setRowData(0, CONTACTS);
        return table;
    }
    
}