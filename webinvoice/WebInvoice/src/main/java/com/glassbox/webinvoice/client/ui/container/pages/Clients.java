/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glassbox.webinvoice.client.ui.container.pages;

import com.glassbox.webinvoice.shared.Constants;
import com.glassbox.webinvoice.shared.Constants.CwConstants;
import com.glassbox.webinvoice.shared.model.ClientInfo;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.TextCell;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.xml.bind.Binder;

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
     * The main DataGrid.
     */
    @UiField(provided = true)
            DataGrid<ClientInfo> dataGrid;
    
    /**
     * The pager used to change the range of data.
     */
    @UiField(provided = true)
            SimplePager pager;
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
   * Initialize this example.
   */
  @Override
  public Widget onInitialize() {
    // Create a DataGrid.

    /*
     * Set a key provider that provides a unique key for each contact. If key is
     * used to identify contacts when fields (such as the name and address)
     * change.
     */
    dataGrid = new DataGrid<ClientInfo>(ClientInfo.KEY_PROVIDER);
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
    ListHandler<ClientInfo> sortHandler =
        new ListHandler<ClientInfo>(.getDataProvider().getList());
    dataGrid.addColumnSortHandler(sortHandler);

    // Create a Pager to control the table.
    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
    pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
    pager.setDisplay(dataGrid);

    // Add a selection model so we can select cells.
    final SelectionModel<ClientInfo> selectionModel =
        new MultiSelectionModel<ClientInfo>(ContactDatabase.ContactInfo.KEY_PROVIDER);
    dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager
        .<ClientInfo> createCheckboxManager());

    // Initialize the columns.
    initTableColumns(selectionModel, sortHandler);

    // Add the CellList to the adapter in the database.
    ContactDatabase.get().addDataDisplay(dataGrid);

    // Create the UiBinder.
    Binder uiBinder = GWT.create(Binder.class);
    return uiBinder.createAndBindUi(this);
  }
    
  /**
   * Add the columns to the table.
   */
  private void initTableColumns(final SelectionModel<ClientInfo> selectionModel,
      ListHandler<ClientInfo> sortHandler) {
    // Checkbox column. This table will uses a checkbox column for selection.
    // Alternatively, you can call dataGrid.setSelectionEnabled(true) to enable
    // mouse selection.
    Column<ClientInfo, Boolean> checkColumn =
        new Column<ClientInfo, Boolean>(new CheckboxCell(true, false)) {
          @Override
          public Boolean getValue(ClientInfo object) {
            // Get the value from the selection model.
            return selectionModel.isSelected(object);
          }
        };
    dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
    dataGrid.setColumnWidth(checkColumn, 40, Unit.PX);

    // First name.
    Column<ClientInfo, String> firstNameColumn =
        new Column<ClientInfo, String>(new EditTextCell()) {
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
    dataGrid.addColumn(firstNameColumn, constants.cwDataGridColumnFirstName());
    firstNameColumn.setFieldUpdater(new FieldUpdater<ClientInfo, String>() {
      @Override
      public void update(int index, ClientInfo object, String value) {
        // Called when the user changes the value.
        object.setFirstName(value);
        ContactDatabase.get().refreshDisplays();
      }
    });
    dataGrid.setColumnWidth(firstNameColumn, 20, Unit.PCT);

    // Last name.
    Column<ClientInfo, String> lastNameColumn =
        new Column<ClientInfo, String>(new EditTextCell()) {
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
    dataGrid.addColumn(lastNameColumn, constants.cwDataGridColumnLastName());
    lastNameColumn.setFieldUpdater(new FieldUpdater<ClientInfo, String>() {
      @Override
      public void update(int index, ClientInfo object, String value) {
        // Called when the user changes the value.
        object.setLastName(value);
        ContactDatabase.get().refreshDisplays();
      }
    });
    dataGrid.setColumnWidth(lastNameColumn, 20, Unit.PCT);

    // Age.
    Column<ContactInfo, Number> ageColumn = new Column<ContactInfo, Number>(new NumberCell()) {
      @Override
      public Number getValue(ContactInfo object) {
        return object.getAge();
      }
    };
    ageColumn.setSortable(true);
    sortHandler.setComparator(ageColumn, new Comparator<ContactInfo>() {
      @Override
      public int compare(ContactInfo o1, ContactInfo o2) {
        return o1.getBirthday().compareTo(o2.getBirthday());
      }
    });
    Header<String> ageFooter = new Header<String>(new TextCell()) {
      @Override
      public String getValue() {
        List<ContactInfo> items = dataGrid.getVisibleItems();
        if (items.size() == 0) {
          return "";
        } else {
          int totalAge = 0;
          for (ContactInfo item : items) {
            totalAge += item.getAge();
          }
          return "Avg: " + totalAge / items.size();
        }
      }
    };
    dataGrid.addColumn(ageColumn, new SafeHtmlHeader(SafeHtmlUtils.fromSafeConstant(constants
        .cwDataGridColumnAge())), ageFooter);
    dataGrid.setColumnWidth(ageColumn, 7, Unit.EM);

    // Category.
    final Category[] categories = ContactDatabase.get().queryCategories();
    List<String> categoryNames = new ArrayList<String>();
    for (Category category : categories) {
      categoryNames.add(category.getDisplayName());
    }
    SelectionCell categoryCell = new SelectionCell(categoryNames);
    Column<ContactInfo, String> categoryColumn = new Column<ContactInfo, String>(categoryCell) {
      @Override
      public String getValue(ContactInfo object) {
        return object.getCategory().getDisplayName();
      }
    };
    dataGrid.addColumn(categoryColumn, constants.cwDataGridColumnCategory());
    categoryColumn.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
      @Override
      public void update(int index, ContactInfo object, String value) {
        for (Category category : categories) {
          if (category.getDisplayName().equals(value)) {
            object.setCategory(category);
          }
        }
        ContactDatabase.get().refreshDisplays();
      }
    });
    dataGrid.setColumnWidth(categoryColumn, 130, Unit.PX);

    // Address.
    Column<ContactInfo, String> addressColumn = new Column<ContactInfo, String>(new TextCell()) {
      @Override
      public String getValue(ContactInfo object) {
        return object.getAddress();
      }
    };
    addressColumn.setSortable(true);
    sortHandler.setComparator(addressColumn, new Comparator<ContactInfo>() {
      @Override
      public int compare(ContactInfo o1, ContactInfo o2) {
        return o1.getAddress().compareTo(o2.getAddress());
      }
    });
    dataGrid.addColumn(addressColumn, constants.cwDataGridColumnAddress());
    dataGrid.setColumnWidth(addressColumn, 60, Unit.PCT);
  }
    
}