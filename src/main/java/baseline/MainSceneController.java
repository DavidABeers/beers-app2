/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainSceneController {
    private Stage primaryStage;
    private Scene primaryScene;
    private Scene newItemScene;
    private final Inventory inventory = new Inventory();
    private final ControllerActions ca = new ControllerActions();
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
    @FXML
    private Label errorLabel;

    public void showPriceError(){
        errorLabel.setText("Invalid Price");
    }

    public void initialize(){
        // set table view as editable
        inventoryView.setEditable(true);
        // sets tableview to see inventory. Might not be necessary, but how listview worked
        inventoryView.setItems(inventory.getInventory());

        // validate input to serial number text field
        snColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        snColumn.setOnEditCommit(event -> {
            if(ca.validSerialNumber(event.getNewValue(), inventory)){
                InventoryItem item = event.getRowValue();
                item.setSerialNumber(event.getNewValue());
                errorLabel.setText("");
            }
            else{
                errorLabel.setText("Invalid serial number");
                inventoryView.refresh();
            }
        });
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        // check for name length between 2 and 256 characters
        nameColumn.setOnEditCommit(event -> {
            if(ca.validateName(event.getNewValue())){
                InventoryItem item = event.getRowValue();
                item.setItemName(event.getNewValue());
                errorLabel.setText("");
            }
            else{
                errorLabel.setText("Invalid name");
                inventoryView.refresh();
            }
        });
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setOnEditCommit(event -> {
            if(ca.validatePrice(event.getNewValue())){
                InventoryItem item = event.getRowValue();
                item.setPrice(event.getNewValue());
                errorLabel.setText("");
            }
            else{
                errorLabel.setText("Invalid price");
                inventoryView.refresh();
            }
        });
        inventoryView.getSortOrder().addListener((ListChangeListener<? super TableColumn<InventoryItem, ?>>) observable -> {
            if(snColumn.getSortType().equals(TableColumn.SortType.ASCENDING)){
                snColumn.setSortType(TableColumn.SortType.DESCENDING);
            }
            else{
                snColumn.setSortType(TableColumn.SortType.ASCENDING);
            }
        });
    }

    private void displayItem(){
        // display the item in the cells properly
        snColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

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
        if(saveFile.getName().substring(saveFile.getName().length()-3).equals("txt")){
            ca.saveAsText(saveFile, inventory);
        }
        else if(saveFile.getName().substring(saveFile.getName().length()-3).equals("son")){
            ca.saveAsJson(saveFile, inventory);
        }
        else {
            ca.saveAsHTML(saveFile, inventory);
        }
    }
    @FXML
    private void openLoadChooser(ActionEvent event) {
        //opening a file chooser
        // opens a file chooser
        FileChooser fileLoader = new FileChooser();
        fileLoader.setTitle("Save Todo List");
        // filters to tab separated text, json, and html files for loading
        FileChooser.ExtensionFilter textFiles = new FileChooser.ExtensionFilter("Tab-separated (*.txt)", "*.txt");
        FileChooser.ExtensionFilter jsonFiles = new FileChooser.ExtensionFilter("JSON (*.json)", "*.json");
        FileChooser.ExtensionFilter htmlFiles = new FileChooser.ExtensionFilter("Hyper-Text Markup Language (*.html)", "*.html");
        fileLoader.getExtensionFilters().add(textFiles);
        fileLoader.getExtensionFilters().add(jsonFiles);
        fileLoader.getExtensionFilters().add(htmlFiles);
        // get the file selected by the file chooser
        File loadFile = fileLoader.showOpenDialog(primaryStage);
        // load the inventory depending on format
        if(loadFile.getName().substring(loadFile.getName().length()-3).equals("txt")){
            ca.loadTabSeparatedText(loadFile, inventory);
        }
        else if(loadFile.getName().substring(loadFile.getName().length()-3).equals("son")){
            ca.loadJson(loadFile, inventory);
        }
        else {
            ca.loadHTML(loadFile, inventory);
        }
        // whatever
        for(InventoryItem ignored : inventory.getInventory()){
            displayItem();
        }

    }
    @FXML
    private void deleteItem(ActionEvent event) {
        // remove current item from inventory
        ca.removeItem(inventory, inventoryView.getSelectionModel().getSelectedItem());
    }
    @FXML
    private void clearInventory(ActionEvent event){
        // delete everything from the inventory
        ca.removeAll(inventory);
    }
}
