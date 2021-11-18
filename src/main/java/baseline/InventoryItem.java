/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

// just stores, fetches, and updates data for an item
public class InventoryItem {
    private String serialNumber;
    private String itemName;
    private double price;

    public void setPrice(double price) {
        this.price = price;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }


}
