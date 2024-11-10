package store.dto;

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
}
