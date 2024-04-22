package com.ddimitko.projectwarehouse;

import com.ddimitko.projectwarehouse.utilities.StartConfig;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StartApp{

    public StartApp() {
    }

    public static void main(final String[] args) {
        Application.launch(StartConfig.class, args);
    }

}