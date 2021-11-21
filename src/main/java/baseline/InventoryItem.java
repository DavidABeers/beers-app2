/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

// just stores, fetches, and updates data for an item
public class InventoryItem {
    private String serialNumber = "A-XXX-XXX-XXX";
    private String itemName = "new item";
    private String price = "$ 0.00";

    public InventoryItem(){

    }

    public InventoryItem(String sn, String name, String price){
        this.serialNumber = sn;
        this.itemName = name;
        this.price = price;
    }

    public void setPrice(String price) {
        try{
            double priceDouble = Double.parseDouble(price);
            if(priceDouble<0){
                // give an error somewhere

            }

        }
        catch(Exception e){
            MainSceneController mc = new MainSceneController();
            mc.showPriceError();
        }
        this.price = price;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }


}
