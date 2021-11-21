/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class newItemSceneController {
    @FXML
    Button saveNewItem;
    @FXML
    Button cancelNewItem;


    public void validateAndSave(ActionEvent actionEvent) {
        // call function validating the new item then add it to the list if valid
        // if invalid, display appropriate error
    }

    public void returnToMainScene(ActionEvent actionEvent) {
        // change scenes
        MainSceneController mainController = new MainSceneController();
        mainController.changeToPrimaryScene();
    }
}
