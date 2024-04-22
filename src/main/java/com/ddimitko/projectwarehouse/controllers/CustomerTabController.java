package com.ddimitko.projectwarehouse.controllers;

import com.ddimitko.projectwarehouse.models.Customer;
import com.ddimitko.projectwarehouse.services.CustomerService;
import com.ddimitko.projectwarehouse.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.converter.LongStringConverter;
import javafx.util.converter.NumberStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class CustomerTabController implements Initializable {

    @Autowired
    private CustomerService customerService;

    private Customer customer;

    @FXML
    private Button addCustomerBtn;
    @FXML
    private Button deleteCustomerBtn;
    @FXML
    private Button refreshBtn;

    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn firstNameCol;
    @FXML
    private TableColumn lastNameCol;
    @FXML
    private TableColumn pinCol;

    ObservableList<Customer> customerObservable;
    List<Customer> customerList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerObservable = FXCollections.observableArrayList();

        refreshTable();
    }

    public void refreshTable() {
        customerList = customerService.findAllCustomers();

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        pinCol.setCellValueFactory(new PropertyValueFactory<>("PIN"));

        customerObservable.clear();
        customerObservable.addAll(customerList);
        customerTable.setItems(customerObservable);
    }

    public void deleteCustomer(){
        if(customerTable.getSelectionModel().getSelectedItem() != null){
            customer = customerTable.getSelectionModel().getSelectedItem();

            customerService.deleteCustomer(customer);
            customerObservable.remove(customer);
            customerList.remove(customer);

            refreshTable();
        }
    }

    public void addCustomer(String firstName, String lastName, String PIN){
        if(customerService.findByPIN(PIN) == null){
            this.customerService.addCustomer(firstName, lastName, PIN);
        }
    }

    public void addCustomerDialog(){
        Dialog<String> dialog = new Dialog();
        dialog.setTitle("Add a Customer");
        dialog.getDialogPane().getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});

        GridPane grid = new GridPane();
        grid.setHgap(10.0);
        grid.setVgap(10.0);
        grid.setPadding(new Insets(20.0, 150.0, 10.0, 10.0));
        TextField firstName = new TextField();
        firstName.setPromptText("Customer's first name");
        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstName, 1, 0);
        TextField lastName = new TextField();
        lastName.setPromptText("Customer's last name");
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastName, 1, 1);
        TextField PIN = new TextField();
        PIN.setTextFormatter(new TextFormatter<>(new LongStringConverter()));
        PIN.setPromptText("Customer's PIN");
        grid.add(new Label("Personal Identification Number:"), 2, 0);
        grid.add(PIN, 2, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.setResizable(false);
        dialog.show();
        Button addButton = (Button)dialog.getDialogPane().lookupButton(ButtonType.OK);
        addButton.setOnMousePressed((event) -> {
            if(firstName.getText().trim().length() != 0 && lastName.getText().trim().length() != 0 && PIN.getText().trim().length() != 0) {
                if(PIN.getText().trim().length() == 9) {
                    this.addCustomer(firstName.getText(), lastName.getText(), PIN.getText());

                    refreshTable();
                }
            }
        });
    }
}
