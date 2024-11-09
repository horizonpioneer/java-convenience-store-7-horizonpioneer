package store.domain;

import java.util.List;

public class Promotions {

    private final List<Promotion> promotions;

    public Promotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Promotion getPromotionConditionByName(String promotionName) {
        for (Promotion promotion : promotions) {
            if (promotion.getName().equals(promotionName)) {
                return promotion;
            }
        }
        throw new IllegalArgumentException("해당 프로모션은 진행중인 프로모션이 아닙니다.");
    }


}
