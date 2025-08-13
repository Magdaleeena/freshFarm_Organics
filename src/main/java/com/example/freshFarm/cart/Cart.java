package com.example.freshFarm.cart;

import com.example.freshFarm.model.Product;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private final Map<Long, CartItem> items = new LinkedHashMap<>();

    public Map<Long, CartItem> getItems() { return items; }

    public void add(Product p, int qty) {
        final int q = Math.max(1, qty);
        items.compute(p.getProductId(), (id, item) -> {
            if (item == null) return new CartItem(p, q);
            item.setQty(item.getQty() + q);
            return item;
        });
    }

    public void updateQty(Long productId, int qty) {
        CartItem it = items.get(productId);
        if (it == null) return;
        if (qty <= 0) items.remove(productId);
        else it.setQty(qty);
    }

    public void remove(Long productId) {
        items.remove(productId);
    }

    public BigDecimal getSubtotal() {
        return items.values().stream()
                .map(CartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clear() {
        items.clear();
    }
}
