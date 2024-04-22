package com.ddimitko.projectwarehouse.utilities;

import com.ddimitko.projectwarehouse.models.FXMLStages;
import javafx.application.Platform;
import java.util.Objects;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StageManager {

    private static final Logger LOG = LoggerFactory.getLogger(StageManager.class);
    private final Stage primaryStage;
    private final SpringFXMLLoader springFXMLLoader;

    public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage) {
        this.springFXMLLoader = springFXMLLoader;
        this.primaryStage = stage;
    }

    public void switchScene(final FXMLStages stage) {
        Parent viewRootNodeHierarchy = this.loadViewNodeHierarchy(stage.getFxmlFile());
        this.show(viewRootNodeHierarchy, stage.getTitle());
    }

    private void show(final Parent rootnode, String title) {
        Scene scene = this.prepareScene(rootnode);
        this.primaryStage.setTitle(title);
        this.primaryStage.setScene(scene);
        this.primaryStage.sizeToScene();
        this.primaryStage.centerOnScreen();
        this.primaryStage.setResizable(false);

        try {
            this.primaryStage.show();
        } catch (Exception var5) {
            this.logAndExit("Unable to show scene for title" + title, var5);
        }

    }

    private Scene prepareScene(Parent rootnode) {
        Scene scene = this.primaryStage.getScene();
        if (scene == null) {
            scene = new Scene(rootnode);
        }

        scene.setRoot(rootnode);
        return scene;
    }

    public Scene getCurrentScene() {
        return this.primaryStage.getScene();
    }

    private Parent loadViewNodeHierarchy(String fxmlFilePath) {
        Parent rootNode = null;

        try {
            rootNode = this.springFXMLLoader.load(fxmlFilePath);
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (Exception var4) {
            this.logAndExit("Unable to load FXML view" + fxmlFilePath, var4);
        }

        return rootNode;
    }

    private void logAndExit(String errorMsg, Exception exception) {
        LOG.error(errorMsg, exception, exception.getCause());
        Platform.exit();
    }

}
