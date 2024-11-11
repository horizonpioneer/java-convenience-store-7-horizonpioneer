package store.domain;

import java.time.LocalDate;
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
        return new Promotion("null", 0, 0, LocalDate.MIN, LocalDate.MAX);
    }


}
