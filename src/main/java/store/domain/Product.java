package store.domain;

public class Product {

    private final String name;
    private final int price;
    private int quantity;
    private final String promotionName;

    public Product(String name, int price, int quantity, String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void decreaseStock(int amount) {
        if (!hasStock(amount)) {
            throw new IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
        quantity -= amount;
    }

    public boolean isEqualPromotion(Promotion promotion) {
        return promotionName.equals(promotion.getName());
    }

    private boolean hasStock(int purchaseQuantity) {
        return quantity > purchaseQuantity;
    }
}
