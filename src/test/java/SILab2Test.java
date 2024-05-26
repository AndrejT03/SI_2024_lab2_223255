import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    public void EveryBranchTest() {
        // Branch: 1-2, 2-19
        List<Item> allItems1 = new ArrayList<>();
        assertTrue(SILab2.checkCart(allItems1, 0));

        // Branch: 1-3.1, 3.1-3.2, 3.2-4, 4-5, 5-19
        List<Item> allItems2 = new ArrayList<>();
        allItems2.add(new Item("", "12345", 100, 0));
        assertTrue(SILab2.checkCart(allItems2, 100));

        // Branch: 1-3.1, 3.1-3.2, 3.2-4, 4-6, 6-13, 13-19
        List<Item> allItems3 = new ArrayList<>();
        allItems3.add(new Item("Item1", null, 100, 0));
        Exception exception3 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(allItems3, 100);
        });
        assertEquals("No barcode!", exception3.getMessage());

        // Branch: 1-3.1, 3.1-3.2, 3.2-4, 4-6, 6-13, 6-7.1, 7.1-7.2, 7.2-8, 8-9, 9-13
        List<Item> allItems4 = new ArrayList<>();
        allItems4.add(new Item("Item1", "012x4", 100, 0));
        Exception exception4 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(allItems4, 100);
        });
        assertEquals("Invalid character in item barcode!", exception4.getMessage());

        // Branch: 1-3.1, 3.1-3.2, 3.2-4, 4-6, 6-13, 6-7.1, 7.1-7.2, 7.2-8, 8-7.3, 7.3-7.2, 7.2-10, 10-11, 10-12, 11-14, 12-14, 14-15, 14-3.3, 15-3.3, 3.3-3.2, 3.2-16, 16-17, 17-19
        List<Item> allItems5 = new ArrayList<>();
        allItems5.add(new Item("Item1", "12345", 100, 0));
        allItems5.add(new Item("Item2", "02345", 400, 1));
        assertTrue(SILab2.checkCart(allItems5, 1000));

        // Branch: 1-3.1, 3.1-3.2, 3.2-4, 4-6, 6-13, 6-7.1, 7.1-7.2, 7.2-8, 8-7.3, 7.3-7.2, 7.2-10, 10-11, 10-12, 11-14, 12-14, 14-15, 14-3.3, 15-3.3, 3.3-3.2, 3.2-16, 16-18, 18-19
        List<Item> allItems6 = new ArrayList<>();
        allItems6.add(new Item("Item1", "12345", 100, 0));
        allItems6.add(new Item("Item2", "02345", 400, 1));
        assertFalse(SILab2.checkCart(allItems6, 100));
    }

    @Test
    public void MultipleConditionTest() {
        // T X X
        List<Item> items1 = new ArrayList<>();
        items1.add(new Item("Item1", "012345", 100, 1));
        assertTrue(SILab2.checkCart(items1, 1000));

        // T F X
        List<Item> items2 = new ArrayList<>();
        items2.add(new Item("Item1", "02345", 301, 0));
        assertTrue(SILab2.checkCart(items2, 1000));

        // T T F
        List<Item> items3 = new ArrayList<>();
        items3.add(new Item("Item1", "12345", 500, 5));
        assertFalse(SILab2.checkCart(items3, 1000));

        // T T T
        List<Item> items4 = new ArrayList<>();
        items4.add(new Item("Item1", "02345", 430, 1));
        assertTrue(SILab2.checkCart(items4, 1000));
    }
}