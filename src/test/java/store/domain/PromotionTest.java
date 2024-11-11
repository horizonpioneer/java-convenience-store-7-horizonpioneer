package store.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PromotionTest {

    private Promotion promotion;

    @BeforeEach
    void setUp() {
        promotion = new Promotion("탄산2+1", 2, 1, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 30));
    }

    @Test
    @DisplayName("구매 수량에 따른 추가 보너스 계산이 올바르게 수행되는지 확인")
    void calculateAdditionalBonus() {
        // 구매 수량이 프로모션 조건을 충족할 때
        assertEquals(1, promotion.calculateAdditionalBonus(2), "구매 수량 2 -> 1 보너스 기대");

        // 구매 수량이 프로모션 조건을 여러 번 충족할 때
        assertEquals(2, promotion.calculateAdditionalBonus(4), "구매 수량 4 -> 2 보너스 기대");

        // 구매 수량이 프로모션 조건을 충족하지 않을 때
        assertEquals(0, promotion.calculateAdditionalBonus(1), "구매 수량 1 -> 보너스 없음 기대");
    }

    @Test
    @DisplayName("프로모션 기간 내에 오늘 날짜가 포함되는지 확인")
    void validateTodayInRange() {
        // 오늘 날짜를 프로모션 기간에 포함하여 테스트
        Promotion todayPromotion = new Promotion("오늘의 프로모션", 1, 1, LocalDate.now(), LocalDate.now());
        assertTrue(todayPromotion.validateTodayInRange(), "오늘 날짜가 프로모션 기간에 포함될 것으로 기대");

        // 오늘 날짜가 프로모션 기간 시작 전일 때
        Promotion futurePromotion = new Promotion("미래 프로모션", 1, 1, LocalDate.now().plusDays(1), LocalDate.now().plusDays(10));
        assertFalse(futurePromotion.validateTodayInRange(), "오늘 날짜가 미래의 프로모션 기간에 포함되지 않음 기대");

        // 오늘 날짜가 프로모션 기간 종료 후일 때
        Promotion pastPromotion = new Promotion("과거 프로모션", 1, 1, LocalDate.now().minusDays(10), LocalDate.now().minusDays(1));
        assertFalse(pastPromotion.validateTodayInRange(), "오늘 날짜가 과거의 프로모션 기간에 포함되지 않음 기대");
    }

}
