package baseline;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ControllerActionsTest {
    ControllerActions ca = new ControllerActions();

    @Test
    void addItem() {
        Inventory inventory = new Inventory();
        ca.addItem(inventory);
        assertEquals(1,inventory.getInventory().size());
    }

    @Test
    void removeItem() {
        Inventory inventory = new Inventory();
        InventoryItem item = new InventoryItem();
        inventory.addItem(item);
        InventoryItem item2 = new InventoryItem();
        inventory.addItem(item2);
        ca.removeItem(inventory, item);
        assertEquals(1,inventory.getInventory().size());
    }

    @Test
    void removeAll() {
        Inventory inventory = new Inventory();
        InventoryItem item = new InventoryItem();
        inventory.addItem(item);
        InventoryItem item2 = new InventoryItem();
        inventory.addItem(item2);
        ca.removeAll(inventory);
        assertEquals(0,inventory.getInventory().size());
    }

    @Test
    void validSerialNumber() {
        Inventory inventory = new Inventory();
        boolean shouldBeTrue = ca.validSerialNumber("d-9g4-fhu-oki", inventory);
        boolean shouldBeFalse = ca.validSerialNumber("3-9fr-fhu-oki", inventory);
        assertTrue(shouldBeTrue);
        assertFalse(shouldBeFalse);
    }

    @Test
    void validateName() {
        boolean shouldBeTrue = ca.validateName("hello");
        boolean shouldBeFalse = ca.validateName("h");
        assertTrue(shouldBeTrue);
        assertFalse(shouldBeFalse);
    }

    @Test
    void validatePrice() {
        boolean shouldBeTrue = ca.validatePrice("9.00");
        boolean shouldBeFalse = ca.validatePrice("-23");
        assertTrue(shouldBeTrue);
        assertFalse(shouldBeFalse);
    }

    @Test
    void saveAsText() {
        Inventory inventory = new Inventory();
        InventoryItem item = new InventoryItem();
        item.setItemName("thing1");
        item.setPrice("8.00");
        item.setSerialNumber("a-aaa-aaa-aaa");
        inventory.addItem(item);
        InventoryItem item2 = new InventoryItem();
        item2.setItemName("thing2");
        item2.setPrice("12.00");
        item2.setSerialNumber("z-zzz-zzz-zzz");
        inventory.addItem(item2);
        File testText = new File("TestDocs/textTestSave.txt");

        ca.saveAsText(testText, inventory);
        File findFile = new File(testText.getPath());
        assertEquals(testText.getPath(), findFile.getPath());
    }

    @Test
    void saveAsJson() {
        Inventory inventory = new Inventory();
        InventoryItem item = new InventoryItem();
        item.setItemName("thing1");
        item.setPrice("8.00");
        item.setSerialNumber("a-aaa-aaa-aaa");
        inventory.addItem(item);
        InventoryItem item2 = new InventoryItem();
        item2.setItemName("thing2");
        item2.setPrice("12.00");
        item2.setSerialNumber("z-zzz-zzz-zzz");
        inventory.addItem(item2);
        File testJson = new File("TestDocs/jsonTestSave.json");

        ca.saveAsText(testJson, inventory);
        File findFile = new File(testJson.getPath());
        assertEquals(testJson.getPath(), findFile.getPath());
    }

    @Test
    void saveAsHTML() {
        Inventory inventory = new Inventory();
        InventoryItem item = new InventoryItem();
        item.setItemName("thing1");
        item.setPrice("8.00");
        item.setSerialNumber("a-aaa-aaa-aaa");
        inventory.addItem(item);
        InventoryItem item2 = new InventoryItem();
        item2.setItemName("thing2");
        item2.setPrice("12.00");
        item2.setSerialNumber("z-zzz-zzz-zzz");
        inventory.addItem(item2);
        File testHtml = new File("TestDocs/htmlTestSave.html");

        ca.saveAsText(testHtml, inventory);
        File findFile = new File(testHtml.getPath());
        assertEquals(testHtml.getPath(), findFile.getPath());
    }

    @Test
    void loadTabSeparatedText() {
        Inventory trueInventory = new Inventory();
        Inventory expectedInventory = new Inventory();
        InventoryItem item = new InventoryItem();
        item.setItemName("thing1");
        item.setPrice("8.00");
        item.setSerialNumber("a-aaa-aaa-aaa");
        expectedInventory.addItem(item);
        InventoryItem item2 = new InventoryItem();
        item2.setItemName("thing2");
        item2.setPrice("12.00");
        item2.setSerialNumber("z-zzz-zzz-zzz");
        expectedInventory.addItem(item2);
        File testText = new File("TestDocs/textTestSave2.txt");

        ca.saveAsText(testText, expectedInventory);
        ca.loadTabSeparatedText(testText, trueInventory);
        assertEquals(expectedInventory.getInventory().size(), trueInventory.getInventory().size());
    }

    @Test
    void loadJson() {
        Inventory trueInventory = new Inventory();
        Inventory expectedInventory = new Inventory();
        InventoryItem item = new InventoryItem();
        item.setItemName("thing1");
        item.setPrice("8.00");
        item.setSerialNumber("a-aaa-aaa-aaa");
        expectedInventory.addItem(item);
        InventoryItem item2 = new InventoryItem();
        item2.setItemName("thing2");
        item2.setPrice("12.00");
        item2.setSerialNumber("z-zzz-zzz-zzz");
        expectedInventory.addItem(item2);
        File testJson = new File("TestDocs/jsonTestSave2.json");

        ca.saveAsText(testJson, expectedInventory);
        ca.loadJson(testJson, trueInventory);
        assertEquals(expectedInventory.getInventory().size(), trueInventory.getInventory().size());
    }

    @Test
    void loadHTML() {
        Inventory trueInventory = new Inventory();
        Inventory expectedInventory = new Inventory();
        InventoryItem item = new InventoryItem();
        item.setItemName("thing1");
        item.setPrice("8.00");
        item.setSerialNumber("a-aaa-aaa-aaa");
        expectedInventory.addItem(item);
        InventoryItem item2 = new InventoryItem();
        item2.setItemName("thing2");
        item2.setPrice("12.00");
        item2.setSerialNumber("z-zzz-zzz-zzz");
        expectedInventory.addItem(item2);
        File testHTML = new File("TestDocs/htmlTestSave2.html");


        ca.saveAsText(testHTML, expectedInventory);
        ca.loadHTML(testHTML, trueInventory);
        assertEquals(expectedInventory.getInventory().size(), trueInventory.getInventory().size());
    }
}