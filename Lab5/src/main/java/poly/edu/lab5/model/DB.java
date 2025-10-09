package poly.edu.lab5.model;

import java.util.HashMap;
import java.util.Map;

public class DB {
    public static Map<Integer, Item> items = new HashMap<>();
    static {
        items.put(1, new Item(1, "Chuột Logitech", 250_000, 0));
        items.put(2, new Item(2, "Bàn phím cơ Keychron", 1_200_000, 0));
        items.put(3, new Item(3, "Tai nghe Sony", 950_000, 0));
        items.put(4, new Item(4, "Camera", 50_000, 0));
        items.put(5, new Item(5, "USB", 15_000, 0));
    }
}
