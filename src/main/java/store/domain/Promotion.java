package store.domain;

public class Promotion {

    private final String name;
    private final int buyCondition;
    private final int getCondition;

    public Promotion(String name, int buyCondition, int getCondition) {
        this.name = name;
        this.buyCondition = buyCondition;
        this.getCondition = getCondition;
    }

    public String getName() {
        return name;
    }

    public int getBuyCondition() {
        return buyCondition;
    }

    public int getGetCondition() {
        return getCondition;
    }

    public int calculateBonus(int purchaseQuantity) {
        if (purchaseQuantity < buyCondition) {
            return 0;
        }
        return (purchaseQuantity / buyCondition) * getCondition;
    }

}
