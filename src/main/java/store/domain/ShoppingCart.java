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

    public int calculateNonPromotionTotalPrice(TotalProducts totalProducts) {
        return items.stream()
                .filter(item -> !totalProducts.findProduct(item.getName()).hasPromotion()) // 프로모션이 없는 상품만 선택
                .mapToInt(item -> item.getQuantity() * totalProducts.findProduct(item.getName()).getPrice())
                .sum();
    }
}
