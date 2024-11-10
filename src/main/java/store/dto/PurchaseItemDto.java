package store.dto;

import store.domain.Product;
import store.domain.TotalProducts;

public class PurchaseItemDto {

    private final String name;
    private int quantity;

    public PurchaseItemDto(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int additionalQuantity) {
        quantity++;
    }

    public int calculatePrice(TotalProducts totalProducts) {
        Product product = totalProducts.findProduct(name);
        return product.getPrice() * quantity;
    }
}
