package com.blazejprzyluski.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.HashSet;
import java.util.Set;

public class App implements EntryPoint
{
    private Set<Customer> customers = new HashSet<>();
    private FlexTable customerTable = new FlexTable();
    private Button addButton = new Button("Add");
    private TextBox firstNameBox = new TextBox();
    private TextBox lastNameBox = new TextBox();
    private TextBox ageBox = new TextBox();
    private VerticalPanel addPanel = new VerticalPanel();
    private VerticalPanel mainPanel = new VerticalPanel();

    @Override
    public void onModuleLoad()
    {
        //create customer table
        customerTable.setText(0,0,"First Name");
        customerTable.setText(0,1,"Last Name");
        customerTable.setText(0,2,"Age");
        //add customer panel
        addPanel.add(new Label("First Name"));
        addPanel.add(firstNameBox);
        addPanel.add(new Label("Last Name"));
        addPanel.add(lastNameBox);
        addPanel.add(new Label("Age"));
        addPanel.add(ageBox);
        addPanel.add(addButton);
        //assemble main panel
        mainPanel.add(customerTable);
        mainPanel.add(addPanel);

        RootPanel.get("customerList").add(mainPanel);

        //listeners for adding customers
        addButton.addClickHandler(e -> addCustomer());

    }

    private void addCustomer()
    {
        String firstName = firstNameBox.getText();
        String lastName = lastNameBox.getText();
        Integer age = 0;
        try
        {
            age = Integer.parseInt(ageBox.getText());
        }catch (Exception e)
        {
            Window.alert("'" + ageBox.getText() + "' is not a age");
        }

        if(!firstName.matches("^[A-Za-z]{1,25}$"))
        {
            Window.alert("'" + firstName + "' is not a valid first name");
            firstNameBox.selectAll();
            return;
        }

        if(!lastName.matches("^[A-Za-z]{1,25}$"))
        {
            Window.alert("'" + lastName + "' is not a valid last name");
            lastNameBox.selectAll();
            return;
        }

        Customer c = new Customer(firstName,lastName,age);
        if(customers.contains(c))
        {
            Window.alert("User already in database");
            return;
        }

        int row = customerTable.getRowCount();
        customers.add(c);
        customerTable.setText(row,0,c.getFirstName());
        customerTable.setText(row,1,c.getLastName());
        customerTable.setText(row,2,c.getAge().toString());

        Button removeCustomer = new Button("x");
        removeCustomer.addClickHandler(e ->{
           customers.remove(c);
           customerTable.removeRow(row);
        });
        customerTable.setWidget(row, 3, removeCustomer);
    }
}
