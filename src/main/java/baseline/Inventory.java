package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private final ObservableList<InventoryItem> inventory = FXCollections.observableArrayList();

    public void addItem(InventoryItem item){
        inventory.add(item);
    }
    public void removeItem(InventoryItem item){
        inventory.remove(item);
    }
    public void clearList(){
        inventory.clear();
    }
    public InventoryItem getItem(int index){
        return inventory.get(index);
    }
    public ObservableList<InventoryItem> getItemsList(){
        return this.inventory;
    }
}