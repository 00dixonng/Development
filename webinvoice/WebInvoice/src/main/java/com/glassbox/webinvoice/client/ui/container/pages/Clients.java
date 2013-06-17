/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glassbox.webinvoice.client.ui.container.pages;

import com.glassbox.webinvoice.client.service.ClientServiceClientImpl;
import com.glassbox.webinvoice.client.service.LoginServiceClientImpl;
import com.glassbox.webinvoice.client.ui.controller.Main;
import com.glassbox.webinvoice.shared.Constants;
import com.glassbox.webinvoice.shared.entity.Client;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author msushil
 */
public class Clients extends Composite {
    
    private static ClientsUiBinder uiBinder = GWT.create(ClientsUiBinder.class);
    private ClientServiceClientImpl clientservice;
    /**
     * The main CellTable.
     */
    @UiField AnchorElement newclient;
    /**
     * The main DataGrid.
     */
    @UiField(provided = true)
            DataGrid<Client> dataGrid;
    
    /**
     * The pager used to change the range of data.
     */
    @UiField(provided = true)
            SimplePager pager;
    
    interface ClientsUiBinder extends UiBinder<Widget, Clients> {
    }
    
    public Clients(Object main) {
        this.clientservice = new ClientServiceClientImpl(GWT.getModuleBaseURL() + "services/client", (Main)main);
        clientservice.getAllClients();
      
        // Create the UiBinder.
        Widget widget = uiBinder.createAndBindUi(this);
        initWidget(widget);
    }
    
  public void initGrid(List<Client> data) {
    // Create a DataGrid.

    /*
     * Set a key provider that provides a unique key for each contact. If key is
     * used to identify contacts when fields (such as the name and address)
     * change.
     */
    dataGrid = new DataGrid<Client>(Client.KEY_PROVIDER);
    dataGrid.setWidth("100%");

    /*
     * Do not refresh the headers every time the data is updated. The footer
     * depends on the current data, so we do not disable auto refresh on the
     * footer.
     */
    dataGrid.setAutoHeaderRefreshDisabled(true);

    // Set the message to display when the table is empty.
    dataGrid.setEmptyTableWidget(new Label(Constants.EMPTY_DATATABLE_MESSAGE));

    // Attach a column sort handler to the ListDataProvider to sort the list.
    ListHandler<Client> sortHandler = new ListHandler<Client>(data);
    dataGrid.addColumnSortHandler(sortHandler);

    // Create a Pager to control the table.
    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
    pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
    pager.setDisplay(dataGrid);

    // Add a selection model so we can select cells.
    final SelectionModel<Client> selectionModel =
        new MultiSelectionModel<Client>(Client.KEY_PROVIDER);
    dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager
        .<Client> createCheckboxManager());

    // Initialize the columns.
    initTableColumns(selectionModel, sortHandler);

    // Add the CellList to the adapter in the database.
    //clientservice.get().addDataDisplay(dataGrid);

  }
    
  /**
   * Add the columns to the table.
   */
  private void initTableColumns(final SelectionModel<Client> selectionModel,
      ListHandler<Client> sortHandler) {
    // Checkbox column. This table will uses a checkbox column for selection.
    // Alternatively, you can call dataGrid.setSelectionEnabled(true) to enable
    // mouse selection.
    Column<Client, Boolean> checkColumn =
        new Column<Client, Boolean>(new CheckboxCell(true, false)) {
          @Override
          public Boolean getValue(Client object) {
            // Get the value from the selection model.
            return selectionModel.isSelected(object);
          }
        };
    dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
    dataGrid.setColumnWidth(checkColumn, 40, Unit.PX);

    // First name.
    Column<Client, String> firstNameColumn =
        new Column<Client, String>(new EditTextCell()) {
          @Override
          public String getValue(Client object) {
            return object.getFirstname();
          }
        };
    firstNameColumn.setSortable(true);
    sortHandler.setComparator(firstNameColumn, new Comparator<Client>() {
      @Override
      public int compare(Client o1, Client o2) {
        return o1.getFirstname().compareTo(o2.getFirstname());
      }
    });
    dataGrid.addColumn(firstNameColumn, "First Name");
    firstNameColumn.setFieldUpdater(new FieldUpdater<Client, String>() {
      @Override
      public void update(int index, Client object, String value) {
        // Called when the user changes the value.
        object.setFirstname(value);
        //clientservice.get().refreshDisplays();
      }
    });
    dataGrid.setColumnWidth(firstNameColumn, 20, Unit.PCT);

    // Last name.
    Column<Client, String> lastNameColumn =
        new Column<Client, String>(new EditTextCell()) {
          @Override
          public String getValue(Client object) {
            return object.getLastname();
          }
        };
    lastNameColumn.setSortable(true);
    sortHandler.setComparator(lastNameColumn, new Comparator<Client>() {
      @Override
      public int compare(Client o1, Client o2) {
        return o1.getLastname().compareTo(o2.getLastname());
      }
    });
    dataGrid.addColumn(lastNameColumn, "Last Name");
    lastNameColumn.setFieldUpdater(new FieldUpdater<Client, String>() {
      @Override
      public void update(int index, Client object, String value) {
        // Called when the user changes the value.
        object.setLastname(value);
        //clientservice.get().refreshDisplays();
      }
    });
    dataGrid.setColumnWidth(lastNameColumn, 20, Unit.PCT);
  }
    
}