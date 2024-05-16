package com.ddimitko.projectwarehouse.controllers;

import com.ddimitko.projectwarehouse.models.Product;
import com.ddimitko.projectwarehouse.models.Supplier;
import com.ddimitko.projectwarehouse.services.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ProductListTabController implements Initializable {

    @Autowired
    private ProductService productService;

    @FXML
    private TextField searchField;
    @FXML
    private Button buyBtn;
    @FXML
    private Button sellBtn;
    @FXML
    private Button refreshBtn;

    @FXML
    private TableView<Product> productTable;

    //Table Columns
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn manufacturerColumn;
    @FXML
    private TableColumn supplierColumn;
    @FXML
    private TableColumn entryDateColumn;
    @FXML
    private TableColumn listPriceColumn;
    @FXML
    private TableColumn quantityColumn;

    ObservableList<Product> productObservable;
    List<Product> productList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productObservable = FXCollections.observableArrayList();

        refreshTable();
    }

    public void refreshTable(){
        productList = productService.findAllByInWarehouseIsTrue();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        entryDateColumn.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        listPriceColumn.setCellValueFactory(new PropertyValueFactory<>("listPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productObservable.clear();
        productObservable.addAll(productList);
        productTable.setItems(productObservable);
    }
}
