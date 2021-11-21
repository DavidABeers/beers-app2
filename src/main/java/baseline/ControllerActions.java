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

    public void addItem(Inventory inventory){
        InventoryItem newItem = new InventoryItem();
        inventory.addItem(newItem);
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

}
