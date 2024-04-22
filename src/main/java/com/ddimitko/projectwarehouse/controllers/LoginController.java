package com.ddimitko.projectwarehouse.controllers;

import com.ddimitko.projectwarehouse.models.FXMLStages;
import com.ddimitko.projectwarehouse.models.User;
import com.ddimitko.projectwarehouse.models.UserHolder;
import com.ddimitko.projectwarehouse.models.UserType;
import com.ddimitko.projectwarehouse.services.UserService;
import com.ddimitko.projectwarehouse.utilities.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LoginController implements Initializable{

    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox choiceBox;
    @Autowired
    private UserService userService;
    @Autowired
    @Lazy
    private StageManager stageManager;

    public LoginController() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Enum> choiceList = FXCollections.observableArrayList(UserType.values());
        this.choiceBox.setItems(choiceList);
        this.choiceBox.setValue(UserType.Operator);
    }

    public void loginButtonAction(ActionEvent event) {
        if (!this.usernameField.getText().isBlank() && !this.usernameField.getText().isEmpty() && !this.passwordField.getText().isBlank() && !this.passwordField.getText().isEmpty()) {
            this.validateLogin();
        } else {
            this.loginMessageLabel.setText("Login Failed.");
        }

    }

    private void validateLogin() {
        if (this.userService.authenticate(this.usernameField.getText(), this.passwordField.getText())) {
            User user = this.userService.findByUsernameAndPassword(this.usernameField.getText(), this.passwordField.getText());
            UserHolder userHolder;
            if (this.choiceBox.getSelectionModel().getSelectedItem() == UserType.Operator) {
                if (user.getUserType() != UserType.Operator) {
                    this.loginMessageLabel.setText("Login Failed! Insufficient rights!");
                } else {
                    this.loginMessageLabel.setText("Login Success! Logging in as: " + String.valueOf(user.getUserType()));
                    userHolder = UserHolder.getInstance();
                    userHolder.setUser(user);
                    this.stageManager.switchScene(FXMLStages.MAIN);
                }
            }

            if (this.choiceBox.getSelectionModel().getSelectedItem() == UserType.Administrator) {
                if (user.getUserType() == UserType.Administrator) {
                    this.loginMessageLabel.setText("Login Success! Logging in as: " + String.valueOf(user.getUserType()));
                    userHolder = UserHolder.getInstance();
                    userHolder.setUser(user);
                    this.stageManager.switchScene(FXMLStages.MAIN);
                } else {
                    this.loginMessageLabel.setText("Login Failed! Insufficient rights!");
                }
            }
        } else {
            this.loginMessageLabel.setText("Please, fill in both fields.");
        }

    }

}
