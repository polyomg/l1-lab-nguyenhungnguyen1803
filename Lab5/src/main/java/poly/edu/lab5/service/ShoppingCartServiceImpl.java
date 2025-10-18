package poly.edu.lab5.service;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import poly.edu.lab5.model.Item;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    // Lưu danh sách mặt hàng trong giỏ
    Map<Integer, Item> map = new HashMap<>();


    private Item mockItem(Integer id) {
        switch (id) {
            case 1: return new Item(1, "Chuột Logitech", 250_000, 1);
            case 2: return new Item(2, "Bàn phím cơ Keychron", 1_200_000, 1);
            case 3: return new Item(3, "Tai nghe Sony", 950_000, 1);
            case 4: return new Item(4, "Camera", 50_000, 1);
            default: return new Item(id, "USB" + id, 15_000, 1);
        }
    }

    @Override
    public Item add(Integer id) {
        Item item = map.get(id);
        if (item == null) {
            item = mockItem(id); // lấy sản phẩm mô phỏng
            map.put(id, item);
        } else {
            item.setQty(item.getQty() + 1);
        }
        return item;
    }

    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        Item item = map.get(id);
        if (item != null) {
            item.setQty(qty);
        }
        return item;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return map.values();
    }

    @Override
    public int getCount() {
        return map.values().stream()
                .mapToInt(Item::getQty)
                .sum();
    }

    @Override
    public double getAmount() {
        return map.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQty())
                .sum();
    }

    public String getAmountVND() {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(getAmount());}

}
