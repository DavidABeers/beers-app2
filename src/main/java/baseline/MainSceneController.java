/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainSceneController {
    private Stage primaryStage;
    private Scene primaryScene;
    private Scene newItemScene;
    private Inventory inventory = new Inventory();
    private ControllerActions ca = new ControllerActions();
    public void setStage(Stage stage){
        // get the stage because it's needed for file choosers
        primaryStage = stage;
    }
    public void setPrimaryScene(Scene scene){
        primaryScene = scene;
    }
    public void setNewItemScene(Scene scene){
        newItemScene = scene;
    }
    // change the active scene
    public void changeToNewItemScene(){
        primaryStage.setScene(newItemScene);
    }
    public void changeToPrimaryScene(){
        primaryStage.setScene(primaryScene);
    }

    @FXML
    private Button newItem;
    @FXML
    private Button removeItem;
    @FXML
    private Button clearAll;
    @FXML
    private Button saveInventory;
    @FXML
    private Button loadInventory;

    // set up the table view to display info for each item
    @FXML
    private TableView<InventoryItem> inventoryView;
    @FXML
    private TableColumn<InventoryItem, String> snColumn;
    @FXML
    private TableColumn<InventoryItem, String> nameColumn;
    @FXML
    private TableColumn<InventoryItem, String> priceColumn;

    @FXML
    private TextField snSearch;
    @FXML
    private TextField nameSearch;

    private Label priceError = new Label();

    public void showPriceError(){
        priceError.setText("Invalid Price");
    }

    public void initialize(){
        // set table view as editable
        inventoryView.setEditable(true);
        // sets tableview to see inventory. Might not be necessary, but how listview worked
        inventoryView.setItems(inventory.getIneventory());

        // validate input to serial number textfield
        snColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        snColumn.setOnEditCommit(event -> {
            if(ca.validSerialNumber(event.getNewValue())){
                InventoryItem item = event.getRowValue();
                item.setSerialNumber(event.getNewValue());
            }
            else{
                Label wrongSerialNumber = new Label();
                wrongSerialNumber.setText("Invalid serial number");
            }
        });
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        // check for name length between 2 and 256 characters
        nameColumn.setOnEditCommit(event -> {
            InventoryItem item = event.getRowValue();
            item.setItemName(event.getNewValue());
        });
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setOnEditCommit(event -> {
            InventoryItem item = event.getRowValue();
            item.setPrice(event.getNewValue());
        });
    }

    @FXML
    private void addNewItem(ActionEvent event){
        // call a helper method to open the scene for adding a new item
        ControllerActions actions = new ControllerActions();
        actions.addItem(inventory);
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
    private void savePriceChange(TableColumn.CellEditEvent<InventoryItem, String> event){
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
        // save data as tab separated, html, or json file.
        // opens a file chooser
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save Todo List");
        // tab separated text file, json, and html filters
        FileChooser.ExtensionFilter textFiles = new FileChooser.ExtensionFilter("Tab-separated (*.txt)", "*.txt");
        FileChooser.ExtensionFilter jsonFiles = new FileChooser.ExtensionFilter("JSON (*.json)", "*.json");
        FileChooser.ExtensionFilter htmlFiles = new FileChooser.ExtensionFilter("Hyper-Text Markup Language (*.html)", "*.html");
        fileSaver.getExtensionFilters().add(textFiles);
        fileSaver.getExtensionFilters().add(jsonFiles);
        fileSaver.getExtensionFilters().add(htmlFiles);
        // make a file object with the file chooser
        File saveFile = fileSaver.showSaveDialog(primaryStage);
        // save the inventory depending on format
        if(saveFile.getName().substring(saveFile.getName().length()-4).equals("txt")){
            ca.saveAsText(saveFile, inventory);
        }
        else if(saveFile.getName().substring(saveFile.getName().length()-4).equals("son")){
            ca.saveAsJson(saveFile, inventory);
        }
        else {
            ca.saveAsHTML(saveFile, inventory);
        }
    }
    @FXML
    private void openLoadChooser(ActionEvent event){
        // call helper method for opening a file chooser to pick a tab separated, html, or json file.
    }
    @FXML
    public void deleteItem(ActionEvent event) {
        // remove current item from inventory
        ca.removeItem(inventory, inventoryView.getSelectionModel().getSelectedItem());
    }
    @FXML
    public void clearInventory(ActionEvent event){
        // delete everything from the inventory
        ca.removeAll(inventory);
    }
}
