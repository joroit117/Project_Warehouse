package com.ddimitko.projectwarehouse.models;

import java.util.ResourceBundle;

public enum FXMLStages {

    MAIN {
        public String getTitle() {
            return this.getStringFromResourceBundle("user.title");
        }

        public String getFxmlFile() {
            return "/fxml/main-screen.fxml";
        }
    },
    LOGIN {
        public String getTitle() {
            return this.getStringFromResourceBundle("login.title");
        }

        public String getFxmlFile() {
            return "/fxml/login-stage.fxml";
        }
    };

    FXMLStages(){

    }

    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
