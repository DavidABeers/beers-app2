/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainSceneController {
    private Stage primaryStage;
    public void setStage(Stage stage){
        // get the stage because it's needed for file choosers
        primaryStage = stage;
    }
    @FXML
    Button newItem;
    @FXML
    Button removeItem;
    @FXML
    Button clearAll;
    @FXML
    Button saveInventory;
    @FXML
    Button loadInventory;

    // set up the table view to display info for each item
    @FXML
    TableView<InventoryItem> inventoryView;
    @FXML
    TableColumn<InventoryItem, String> snColumn;
    @FXML
    TableColumn<InventoryItem, String> nameColumn;
    @FXML
    TableColumn<InventoryItem, Double> priceColumn;

    @FXML
    TextField snSearch;
    @FXML
    TextField nameSearch;

    public void initialize(){
        // set table view as editable

    }

    @FXML
    private void goToNewItemScene(ActionEvent event){
        // call a helper method to open the scene for adding a new item
    }

    @FXML
    private void saveSNChange(TableColumn.CellEditEvent<InventoryItem, String> event){
        // update the sn of the selected item
    }

    @FXML
    private void saveNameChange(TableColumn.CellEditEvent<InventoryItem, String> event){
        // update the name of the selected item
    }
    @FXML
    private void savePriceChange(TableColumn.CellEditEvent<InventoryItem, Double> event){
        // update the name of the selected item
    }

    @FXML
    private void filterBySN(ActionEvent event){
        // set the table view to only show items with an sn containing the sequence in the snSearch field
    }
    @FXML
    private void filterByName(ActionEvent event){
        // set the table view to only show items with a name containing the sequence in the nameSearch field
    }

    @FXML
    private void openSaveChooser(ActionEvent event){
        // call helper method to open a file chooser that saves data as tab separated, html, or json file.
    }
    @FXML
    private void openLoadChooser(ActionEvent event){
        // call helper method for opening a file chooser to pick a tab separated, html, or json file.
    }
    @FXML
    public void deleteItem(ActionEvent event) {
        // remove current item from inventory
    }
    @FXML
    public void clearInventory(ActionEvent event){
        // delete everything from the inventory
    }
}
