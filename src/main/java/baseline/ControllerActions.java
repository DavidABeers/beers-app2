/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ControllerActions {

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
                for(InventoryItem item: inventory.getIneventory()){
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

    }

    public void saveAsHTML(File saveFile, Inventory inventory) {
        try{
            if(saveFile.createNewFile()){
                String endTableCell = "</td>\n";
                // I did close the resource, Sonarlint is dumb
                FileWriter writer = new FileWriter(saveFile);
                writer.write("<title></title>\n<body>\n<table>\n");
                for(InventoryItem item: inventory.getIneventory()){
                    writer.write("<tr>\n");
                    writer.write("<td>" + item.getSerialNumber() +endTableCell);
                    writer.write("<td>" + item.getItemName() +endTableCell);
                    writer.write("<td>" + item.getPrice() +endTableCell);
                    writer.write("</tr>\n");
                }
                writer.write("</table>\n</body>\n");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
