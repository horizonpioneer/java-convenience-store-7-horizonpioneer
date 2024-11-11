package store.domain;

import store.dto.PurchaseItemDto;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<PurchaseItemDto> items;

    public ShoppingCart(List<PurchaseItemDto> shoppingCart) {
        this.items = shoppingCart;
    }

    public List<PurchaseItemDto> getItems() {
        return new ArrayList<>(items);
    }

    public int calculateNonPromotionTotalPrice(TotalProducts totalProducts) {
        return items.stream()
                .filter(item -> !item.isExcluded())
                .filter(item -> !totalProducts.findProduct(item.getName()).hasPromotion())
                .mapToInt(item -> item.getQuantity() * totalProducts.findProduct(item.getName()).getPrice())
                .sum();
    }
}
