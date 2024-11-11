package store.domain;

import store.dto.PurchaseItemDto;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<PurchaseItemDto> items;

    public ShoppingCart(List<PurchaseItemDto> shoppingCart) {
        this.items = shoppingCart;
    }

    public void addItem(PurchaseItemDto item) {
        this.items.add(item);
    }

    public void removeItem(PurchaseItemDto item) {
        this.items.remove(item);
    }

    public List<PurchaseItemDto> getItems() {
        return new ArrayList<>(items);
    }

    public int calculateTotalPrice(TotalProducts totalProducts) {
        return items.stream()
                .filter(item -> !item.isExcluded())
                .mapToInt(item -> totalProducts.findProduct(item.getName()).getPrice() * item.getQuantity())
                .sum();
    }

    public int calculateNonPromotionTotalPrice(TotalProducts totalProducts) {
        return items.stream()
                .filter(item -> !item.isExcluded())
                .filter(item -> !totalProducts.findProduct(item.getName()).hasPromotion())
                .mapToInt(item -> item.getQuantity() * totalProducts.findProduct(item.getName()).getPrice())
                .sum();
    }
}
