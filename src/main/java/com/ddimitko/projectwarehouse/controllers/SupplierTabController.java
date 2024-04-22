package com.ddimitko.projectwarehouse.controllers;

import com.ddimitko.projectwarehouse.models.Supplier;
import com.ddimitko.projectwarehouse.services.SupplierService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class SupplierTabController implements Initializable {

    @Autowired
    private SupplierService supplierService;

    private Supplier supplier;

    @FXML
    private Button addSupplierBtn;
    @FXML
    private Button deleteSupplierBtn;
    @FXML
    private Button refreshBtn;

    @FXML
    private TableView<Supplier> supplierTable;
    @FXML
    private TableColumn nameColumn;
    ObservableList<Supplier> supplierObservable;
    List<Supplier> supplierList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        supplierObservable = FXCollections.observableArrayList();

        refreshTable();

    }

    public void refreshTable(){
        supplierList = supplierService.findAllSuppliers();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        supplierObservable.clear();
        supplierObservable.addAll(supplierList);
        supplierTable.setItems(supplierObservable);
    }

    public void deleteSupplier() {

        if (supplierTable.getSelectionModel().getSelectedItem() != null) {
            supplier = supplierTable.getSelectionModel().getSelectedItem();

            supplierService.deleteSupplier(supplier);
            supplierObservable.remove(supplier);
            supplierList.remove(supplier);

            refreshTable();
        }
    }

    public void addSupplier(String name){
        if(supplierService.findByName(name) == null) {
            this.supplierService.addSupplier(name);
        }
    }

    public void addSupplierDialog(){
        Dialog<String> dialog = new Dialog();
        dialog.setTitle("Add a Supplier");
        dialog.getDialogPane().getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});

        GridPane grid = new GridPane();
        grid.setHgap(10.0);
        grid.setVgap(10.0);
        grid.setPadding(new Insets(20.0, 150.0, 10.0, 10.0));
        TextField name = new TextField();
        name.setPromptText("Supplier's name");
        grid.add(new Label("Name:"), 0, 0);
        grid.add(name, 1, 0);

        dialog.getDialogPane().setContent(grid);
        dialog.setResizable(false);
        dialog.show();
        Button addButton = (Button)dialog.getDialogPane().lookupButton(ButtonType.OK);
        addButton.setOnMousePressed((event) -> {
            if(name.getText().trim().length() > 0) {
                this.addSupplier(name.getText());

                refreshTable();
            }
        });
    }
}
