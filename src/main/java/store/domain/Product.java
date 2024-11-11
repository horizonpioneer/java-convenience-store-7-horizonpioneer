package store.domain;

import store.enums.ErrorMessage;

public class Product {

    private final String name;
    private final int price;
    private int stock;
    private int promotionStock;
    private final String promotionName;

    public Product(String name, int price, String promotionName) {
        this.name = name;
        this.price = price;
        this.stock = 0;
        this.promotionStock = 0;
        this.promotionName = promotionName;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getPromotionStock() {
        return promotionStock;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void addStock(int quantity, boolean isPromotion) {
        if (isPromotion) {
            promotionStock += quantity;
            return;
        }
        stock += quantity;
    }

    public boolean hasStock() {
        return stock > 0;
    }

    public boolean hasPromotion() {
        return !promotionName.equals("null");
    }

    public boolean hasPromotionStock() {
        return promotionStock > 0;
    }

    public boolean isEqualName(String name) {
        return this.name.equals(name);
    }

    public boolean isEqualPromotionName(String name) {
        return this.promotionName.equals(name);
    }

    public void decreaseStock(int amount) {
        if (promotionStock + stock < amount) {
            throw new IllegalArgumentException(ErrorMessage.STOCK_EXCEEDED.getMessage());
        }
        amount = decreasePromotionStock(amount);
        decreaseRegularStock(amount);
    }

    private int decreasePromotionStock(int amount) {
        if (promotionStock >= amount) {
            promotionStock -= amount;
            return 0;
        }
        int remainingAmount = amount - promotionStock;
        promotionStock = 0;
        return remainingAmount;
    }

    private void decreaseRegularStock(int amount) {
        stock -= amount;
    }
}
