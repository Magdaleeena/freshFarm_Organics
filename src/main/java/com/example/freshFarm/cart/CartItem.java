package com.example.freshFarm.cart;

import com.example.freshFarm.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String name;
    private BigDecimal unitPrice;
    private int qty;
    private String imageUrl;

    public CartItem(Product p, int qty) {
        this.productId = p.getProductId();
        this.name = p.getProductName();
        this.unitPrice = p.getUnitPrice();
        this.qty = qty;
        this.imageUrl = p.getImageUrl();
    }

    public BigDecimal getLineTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(qty));
    }
}

