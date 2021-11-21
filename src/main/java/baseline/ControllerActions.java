/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ControllerActions {

    JsonToInventory jti;

    // add blank item to inventory
    public void addItem(Inventory inventory){
        InventoryItem newItem = new InventoryItem();
        inventory.addItem(newItem);
    }
    // remove item from inventory
    public void removeItem(Inventory inventory, InventoryItem item){
        inventory.removeItem(item);
    }
    // clear an inventory of all items it stores
    public void removeAll(Inventory inventory){
        inventory.clearList();
    }

    // ensures the serial number meets all requirements
    public boolean validSerialNumber(String sn){
        // checks for incorrect formatting on characters 0, 1, 5, and 9
        if(sn.length()!=13 || sn.substring(0,1).toLowerCase().compareTo("a") < 0 ||
                sn.substring(0,1).toLowerCase().compareTo("z") > 0 || sn.charAt(1) != '-' ||
                sn.charAt(5) != '-' || sn.charAt(9) != '-'){
            return false;
        }
        // checks for invalid characters between the first two dashes
        for(int i = 2;i<5;i++){
            if((sn.substring(i, (i+1)).compareTo("0")<0 ||
                    (sn.substring(i, i+1).compareTo("9")>0 && sn.substring(i, i+1).compareTo("A")<0) ||
                    (sn.substring(i, i+1).compareTo("Z")>0 && sn.substring(i, i+1).compareTo("a")<0) ||
                    sn.substring(i, i+1).compareTo("z")>0)){
                return false;
            }
        }
        // checks for invalid characters between the second and third dashes
        for(int i = 6;i<9;i++){
            if((sn.substring(i, (i+1)).compareTo("0")<0 ||
                    (sn.substring(i, i+1).compareTo("9")>0 && sn.substring(i, i+1).compareTo("A")<0) ||
                    (sn.substring(i, i+1).compareTo("Z")>0 && sn.substring(i, i+1).compareTo("a")<0) ||
                    sn.substring(i, i+1).compareTo("z")>0)){
                return false;
            }
        }
        // checks for invalid characters after the last dash
        for(int i = 10;i<13;i++){
            if((sn.substring(i, (i+1)).compareTo("0")<0 ||
                    (sn.substring(i, i+1).compareTo("9")>0 && sn.substring(i, i+1).compareTo("A")<0) ||
                    (sn.substring(i, i+1).compareTo("Z")>0 && sn.substring(i, i+1).compareTo("a")<0) ||
                    sn.substring(i, i+1).compareTo("z")>0)){
                return false;
            }
        }
        return true;
    }

    public void saveAsText(File saveFile, Inventory inventory) {
        try{
            if(saveFile.createNewFile()){
                // I did close the resource, Sonarlint is dumb
                FileWriter writer = new FileWriter(saveFile);
                for(InventoryItem item: inventory.getInventory()){
                    writer.write(item.getSerialNumber() +"\t");
                    writer.write(item.getItemName() +"\t");
                    writer.write(item.getPrice() +"\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAsJson(File saveFile, Inventory inventory) {
        Gson gson = new GsonBuilder().create();
        try{
            FileWriter writer = new FileWriter(saveFile);
            gson.toJson(inventory, writer);
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveAsHTML(File saveFile, Inventory inventory) {
        try{
            if(saveFile.createNewFile()){
                String endTableCell = "</td>\n";
                // I did close the resource, Sonarlint is dumb
                FileWriter writer = new FileWriter(saveFile);
                // create a title and body field
                writer.write("<title></title>\n<body>\n<table>\n");
                // set up item data in a row for each item
                for(InventoryItem item: inventory.getInventory()){
                    writer.write("<tr>\n");
                    writer.write("<td>" + item.getSerialNumber() +endTableCell);
                    writer.write("<td>" + item.getItemName() +endTableCell);
                    writer.write("<td>" + item.getPrice() +endTableCell);
                    writer.write("</tr>\n");
                }
                // close tags
                writer.write("</table>\n</body>\n");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTabSeparatedText(File loadFile, Inventory inventory) {
        try{
            // overwrite currently loaded list
            inventory.clearList();
            Scanner reader = new Scanner(loadFile);
            while(reader.hasNextLine()){
                String itemData = reader.nextLine();
                String sn = itemData.substring(0,12);
                // get position of the last tab
                short tabPos = 14;
                while(itemData.charAt(tabPos)!='\t'){
                    tabPos++;
                }
                // get name and price of the item
                String name = itemData.substring(14,tabPos);
                String price = itemData.substring(tabPos+1);
                // add loaded item to inventory
                InventoryItem item = new InventoryItem(sn, name, price);
                inventory.addItem(item);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadJson(File loadFile, Inventory inventory) {
        try {
            inventory.clearList();
            // open a reader
            Reader inputReader = Files.newBufferedReader(Paths.get(loadFile.getPath()));
            // convert json to inventory object
            jti = new Gson().fromJson(inputReader, JsonToInventory.class);
            InventoryItem[] inventoryArray = jti.getInventoryItems();
            for(InventoryItem item: inventoryArray){
                inventory.addItem(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHTML(File loadFile, Inventory inventory) {
        try{
            // overwrite currently loaded list
            inventory.clearList();
            Scanner reader = new Scanner(loadFile);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                // get information from each table row
                if(line.contains("<tr>")){
                    String sn = reader.nextLine().substring(4,16);
                    String nameLine = reader.nextLine();
                    String name = nameLine.substring(4,nameLine.length());
                    String priceLine = reader.nextLine();
                    String price =priceLine.substring(4,priceLine.length());
                    // add loaded item to inventory
                    InventoryItem item = new InventoryItem(sn, name, price);
                    inventory.addItem(item);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
