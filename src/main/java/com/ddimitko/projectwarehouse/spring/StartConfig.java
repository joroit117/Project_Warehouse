package com.ddimitko.projectwarehouse.spring;

import com.ddimitko.projectwarehouse.StartApp;
import com.ddimitko.projectwarehouse.models.FXMLStages;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class StartConfig extends Application {

    protected ConfigurableApplicationContext springContext;
    protected StageManager stageManager;

    public StartConfig() {
    }

    public void init() throws Exception {
        this.springContext = this.springBootApplicationContext();
    }

    public void start(Stage stage) throws Exception{
        this.stageManager = (StageManager) this.springContext.getBean(StageManager.class, new Object[]{stage});
        this.displayInitialScene();
    }

    public void stop() throws Exception{
        this.springContext.close();
    }

    protected void displayInitialScene() {
        this.stageManager.switchScene(FXMLStages.LOGIN);
    }

    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(new Class[]{StartApp.class});
        String[] args = (String[])this.getParameters().getRaw().stream().toArray((x$0) -> new String[x$0]);
        return builder.run(args);
    }

}
