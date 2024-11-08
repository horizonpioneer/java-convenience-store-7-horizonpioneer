package store.domain;

import java.util.List;
import java.util.stream.Collectors;

public class AmountCalculator {

    public int calculateTotalPurchaseAmount(List<Product> products) {
        int totalPurchaseAmount = 0;
        for (Product product : products) {
            totalPurchaseAmount += product.getPrice() * product.getQuantity();
        }
        return totalPurchaseAmount;
    }

    public int calculatePromotionDiscount(int purchaseAmount, Product product, Promotion promotion) {
        if (product.getPromotionName().equals(promotion.getName())) {
            int freeItemCount = purchaseAmount / promotion.getGetCondition();
            return product.getPrice() * freeItemCount;
        }
        return 0;
    }

    private List<Product> filterProductsWithoutPromotion(List<Product> products) {
        List<Product> productWithNoPromo = products.stream()
                .filter(product -> product.getPromotionName().equals("null"))
                .toList();

        return productWithNoPromo.stream()
                .filter(product -> products.stream()
                        .noneMatch(otherProduct -> !otherProduct.getPromotionName().equals("null") && otherProduct.getName().equals(product.getName())))
                .collect(Collectors.toList());
    }
}
