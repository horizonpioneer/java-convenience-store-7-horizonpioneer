package store.domain;

import java.util.List;

public class AmountCalculator {

    public int calculateTotalPurchaseAmount(List<Product> products) {
        int totalPurchaseAmount = 0;
        for (Product product : products) {
            totalPurchaseAmount += product.getPrice() * product.getQuantity();
        }
        return totalPurchaseAmount;
    }
}
