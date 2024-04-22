package com.ddimitko.projectwarehouse.utilities;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringFXMLLoader {

    private final ResourceBundle resourceBundle;
    private final ApplicationContext context;

    @Autowired
    public SpringFXMLLoader(ApplicationContext context, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        this.context = context;
    }

    public Parent load(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        ApplicationContext var10001 = this.context;
        Objects.requireNonNull(var10001);
        loader.setControllerFactory(var10001::getBean);
        loader.setResources(this.resourceBundle);
        loader.setLocation(this.getClass().getResource(fxmlPath));
        return (Parent)loader.load();
    }

}
