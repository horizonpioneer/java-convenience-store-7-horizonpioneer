package store.dto;

public class AdditionalBonusDto {
    private final String name;
    private final int quantity;
    private final int discountAmount;

    public AdditionalBonusDto(String name, int quantity, int discountAmount) {
        this.name = name;
        this.quantity = quantity;
        this.discountAmount = discountAmount;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
