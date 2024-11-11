package store.domain;

public class AmountCalculator {

    public int calculatePromotionDiscount(int purchaseAmount, Product product, Promotion promotion) {
        if (product.isEqualPromotionName(promotion.getName())) {
            int freeItemCount = getFreeItemCount(purchaseAmount, promotion);
            return product.getPrice() * freeItemCount;
        }
        return 0;
    }

    private int getFreeItemCount(int purchaseAmount, Promotion promotion) {
        return purchaseAmount / promotion.getGetCondition();
    }

    public int calculateMembershipDiscount(int nonPromotionTotalPrice) {
        int discount = (nonPromotionTotalPrice * 30) / 100;

        return Math.min(discount, 8000);
    }
}
