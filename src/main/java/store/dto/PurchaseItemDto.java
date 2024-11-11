package store.dto;

public class PurchaseItemDto {

    private final String name;
    private int quantity;
    private boolean excludeFromPurchase = false;

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

    public boolean isExcluded() {
        return excludeFromPurchase;
    }

    public void excludeFromPurchase(boolean exclude) {
        this.excludeFromPurchase = exclude;
    }
}
