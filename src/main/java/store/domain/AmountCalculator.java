package store.domain;

import java.util.List;
import java.util.stream.Collectors;

public class AmountCalculator {

    public int calculateTotalPurchaseAmount(List<Product> products) {
        int totalPurchaseAmount = 0;
        for (Product product : products) {
            totalPurchaseAmount += product.getPrice() * (product.getStock() + product.getPromotionStock());
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

    public int calculateMembershipDiscount(List<Product> products) {
        List<Product> productsWithoutPromotion = filterProductsWithoutPromotion(products);
        int totalAmount = calculateTotalPurchaseAmount(productsWithoutPromotion);
        int discount = (totalAmount * 30) / 100;

        return Math.min(discount, 8000);
    }

    public int calculateAmountToPay(List<Product> products, Promotion promotion) {
        int totalPurchaseAmount = calculateTotalPurchaseAmount(products);
        int totalDiscount = 0;
        for (Product product : products) {
            int promotionDiscount = calculatePromotionDiscount(totalPurchaseAmount, product, promotion);
            totalDiscount += promotionDiscount;
        }
        totalDiscount += calculateMembershipDiscount(products);

        return totalPurchaseAmount - totalDiscount;
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
