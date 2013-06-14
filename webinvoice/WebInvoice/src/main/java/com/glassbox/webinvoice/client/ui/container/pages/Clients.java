/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glassbox.webinvoice.client.ui.container.pages;

import com.glassbox.webinvoice.shared.Constants.CwConstants;
import com.glassbox.webinvoice.shared.model.ClientInfo;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author msushil
 */
public class Clients extends Composite {
    
    private static ClientsUiBinder uiBinder = GWT.create(ClientsUiBinder.class);
    /**
     * The main CellTable.
     */
    @UiField AnchorElement newclient;
    
    /**
     * The pager used to change the range of data.
     */
    //@UiField(provided = true)
    //        SimplePager pager;
    
    /**
     * An instance of the constants.
     */
    private static CwConstants constants;
    
    interface ClientsUiBinder extends UiBinder<Widget, Clients> {
    }
    
    public Clients() {
        List lst = new ArrayList();
        newclient.setClassName("buttons");
        //initWidget(uiBinder.createAndBindUi(this));
        // Create a CellTable.
        
        // Set a key provider that provides a unique key for each contact. If key is
        // used to identify contacts when fields (such as the name and address)
        // change.
        //cellTable = new CellTable<ClientInfo>(
        //        ClientInfo.KEY_PROVIDER);
        //cellTable.setWidth("100%", true);
        
        // Do not refresh the headers and footers every time the data is updated.
        //cellTable.setAutoHeaderRefreshDisabled(true);
        //cellTable.setAutoFooterRefreshDisabled(true);
        
        // Attach a column sort handler to the ListDataProvider to sort the list.
        ListHandler<ClientInfo> sortHandler = new ListHandler<ClientInfo>(
                lst);
        //cellTable.addColumnSortHandler(sortHandler);
        
        // Create a Pager to control the table.
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        //pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
        //pager.setDisplay(cellTable);
        
        // Add a selection model so we can select cells.
        final SelectionModel<ClientInfo> selectionModel = new MultiSelectionModel<ClientInfo>(
                ClientInfo.KEY_PROVIDER);
        //cellTable.setSelectionModel(selectionModel,
        //        DefaultSelectionEventManager.<ClientInfo> createCheckboxManager());
        
        // Initialize the columns.
        initTableColumns(selectionModel, sortHandler);
        
        // Add the CellList to the adapter in the database.
        //ContactDatabase.get().addDataDisplay(cellTable);
        
        // Create the UiBinder.
        Widget widget = uiBinder.createAndBindUi(this);
        
        initWidget(widget);
    }
    
    /**
     * Add the columns to the table.
     */
    private void initTableColumns(
            final SelectionModel<ClientInfo> selectionModel,
            ListHandler<ClientInfo> sortHandler) {
        // Checkbox column. This table will uses a checkbox column for selection.
        // Alternatively, you can call cellTable.setSelectionEnabled(true) to enable
        // mouse selection.
        Column<ClientInfo, Boolean> checkColumn = new Column<ClientInfo, Boolean>(
                new CheckboxCell(true, false)) {
                    @Override
                    public Boolean getValue(ClientInfo object) {
                        // Get the value from the selection model.
                        return selectionModel.isSelected(object);
                    }
                };
        //cellTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
        //cellTable.setColumnWidth(checkColumn, 40, Unit.PX);
        
        // First name.
        Column<ClientInfo, String> firstNameColumn = new Column<ClientInfo, String>(
                new EditTextCell()) {
                    @Override
                    public String getValue(ClientInfo object) {
                        return object.getFirstName();
                    }
                };
        firstNameColumn.setSortable(true);
        sortHandler.setComparator(firstNameColumn, new Comparator<ClientInfo>() {
            @Override
            public int compare(ClientInfo o1, ClientInfo o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });
        /*cellTable.addColumn(firstNameColumn, constants.cwCellTableColumnFirstName());
         * firstNameColumn.setFieldUpdater(new FieldUpdater<ClientInfo, String>() {
         * @Override
         * public void update(int index, ClientInfo object, String value) {
         * // Called when the user changes the value.
         * object.setFirstName(value);
         * //ContactDatabase.get().refreshDisplays();
         * }
         * });
         */
        //cellTable.setColumnWidth(firstNameColumn, 20, Unit.PCT);
        
        // Last name.
        Column<ClientInfo, String> lastNameColumn = new Column<ClientInfo, String>(
                new EditTextCell()) {
                    @Override
                    public String getValue(ClientInfo object) {
                        return object.getLastName();
                    }
                };
        lastNameColumn.setSortable(true);
        sortHandler.setComparator(lastNameColumn, new Comparator<ClientInfo>() {
            @Override
            public int compare(ClientInfo o1, ClientInfo o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        /*
         * cellTable.addColumn(lastNameColumn, constants.cwCellTableColumnLastName());
         * lastNameColumn.setFieldUpdater(new FieldUpdater<ClientInfo, String>() {
         * @Override
         * public void update(int index, ClientInfo object, String value) {
         * // Called when the user changes the value.
         * object.setLastName(value);
         * //ContactDatabase.get().refreshDisplays();
         * }
         * });
         */
        //cellTable.setColumnWidth(lastNameColumn, 20, Unit.PCT);
        
        // Address.
        Column<ClientInfo, String> addressColumn = new Column<ClientInfo, String>(
                new TextCell()) {
                    @Override
                    public String getValue(ClientInfo object) {
                        return object.getAddress();
                    }
                };
        addressColumn.setSortable(true);
        addressColumn.setDefaultSortAscending(false);
        sortHandler.setComparator(addressColumn, new Comparator<ClientInfo>() {
            @Override
            public int compare(ClientInfo o1, ClientInfo o2) {
                return o1.getAddress().compareTo(o2.getAddress());
            }
        });
        //cellTable.addColumn(addressColumn, constants.cwCellTableColumnAddress());
        //cellTable.setColumnWidth(addressColumn, 60, Unit.PCT);
    }
    
}