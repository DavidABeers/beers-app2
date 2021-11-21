/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class InventoryManagementApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainInventoryScene.fxml")));


        Scene scene = new Scene(root);
        stage.setScene(scene);
        Scene newItemScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("newItemScene.fxml"))));
        stage.setTitle("Inventory Manager");
        MainSceneController controller = new MainSceneController();
        controller.setStage(stage);
        controller.setPrimaryScene(scene);
        controller.setNewItemScene(newItemScene);
        stage.show();
    }
}
