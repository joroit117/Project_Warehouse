package com.ddimitko.projectwarehouse.controllers;

import com.ddimitko.projectwarehouse.models.*;
import com.ddimitko.projectwarehouse.services.UserService;
import com.ddimitko.projectwarehouse.services.WarehouseService;
import com.ddimitko.projectwarehouse.utilities.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainController implements Initializable {

    @Autowired
    @Lazy
    private StageManager stageManager;

    //This field ensures only a single user is logged in at a time.
    private UserHolder userHolder = UserHolder.getInstance();
    private User user;

    //Get controller access to Warehouse
    private Warehouse warehouse;

    //Database access
    @Autowired
    private UserService userService;
    @Autowired
    private WarehouseService warehouseService;

    //Labels
    @FXML
    private Label userName;
    @FXML
    private Label cashRegister;

    //Buttons
    @FXML
    private Button addUserButton;
    @FXML
    private Button inquiryButton;
    @FXML
    private Button logoutButton;

    public MainController() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.user = this.userHolder.getUser();
        this.userName.setText(this.user.getUsername());
        this.warehouse = this.warehouseService.initializeAtStartup();
        this.cashRegister.setText(warehouse.getCashRegister().toString());
        if (this.user.getUserType() != UserType.Administrator) {
            this.addUserButton.setDisable(true);
        }

    }

    public void addUser(String username, String password, String userType) {
        this.userService.addUser(username, password, userType);
    }

    public void logOut() {
        this.userHolder.setUser((User)null);
        this.stageManager.switchScene(FXMLStages.LOGIN);
    }

    public void logoutButtonAction(ActionEvent actionEvent) {
        this.logOut();
    }

    public void addUserButtonAction(ActionEvent actionEvent) {
        Dialog<String> dialog = new Dialog();
        dialog.setTitle("Add a User");
        dialog.setHeaderText("Add a User here.");
        dialog.getDialogPane().getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
        ObservableList<Enum> choiceList = FXCollections.observableArrayList(UserType.values());
        ChoiceBox<Enum> typeSelection = new ChoiceBox();
        typeSelection.setItems(choiceList);
        typeSelection.setValue(UserType.Operator);
        GridPane grid = new GridPane();
        grid.setHgap(10.0);
        grid.setVgap(10.0);
        grid.setPadding(new Insets(20.0, 150.0, 10.0, 10.0));
        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);
        grid.add(new Label("Select user type:"), 2, 0);
        grid.add(typeSelection, 2, 1);
        dialog.getDialogPane().setContent(grid);
        dialog.setResizable(false);
        dialog.show();
        Button addButton = (Button)dialog.getDialogPane().lookupButton(ButtonType.OK);
        addButton.setOnMousePressed((event) -> {
            this.addUser(username.getText(), password.getText(), String.valueOf(typeSelection.getSelectionModel().getSelectedItem()));
        });
    }
}
