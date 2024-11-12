package store.domain;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Promotion {

    private final String name;
    private final int buyCondition;
    private final int getCondition;
    private final LocalDate startDate;
    private final LocalDate endDate;


    public Promotion(String name, int buyCondition, int getCondition,
                     LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buyCondition = buyCondition;
        this.getCondition = getCondition;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public int calculateAdditionalBonus(int purchaseQuantity) {
        if (purchaseQuantity < buyCondition) {
            return 0;
        }
        return (buyCondition + getCondition) * (purchaseQuantity / buyCondition) - purchaseQuantity;
    }

    public boolean validateTodayInRange() {

        return isTodayInRange(startDate, endDate);
    }

    private boolean isTodayInRange(LocalDate startDate, LocalDate endDate) {
        LocalDateTime todayDateTime = DateTimes.now();
        LocalDate today = todayDateTime.toLocalDate();

        return (today.isEqual(startDate) || today.isAfter(startDate)) &&
                (today.isEqual(endDate) || today.isBefore(endDate));
    }
}
