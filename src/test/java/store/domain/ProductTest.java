package store.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.enums.ErrorMessage;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("콜라", 1000, "탄산2+1");
    }

    @Test
    @DisplayName("일반 재고 추가 시 stock이 증가하는지 확인")
    void addStockRegular() {
        product.addStock(10, false);
        Assertions.assertThat(10).isEqualTo(product.getStock());
    }

    @Test
    @DisplayName("프로모션 재고 추가 시 promotionStock이 증가하는지 확인")
    void addStockPromotion() {
        product.addStock(5, true);
        Assertions.assertThat(5).isEqualTo(product.getPromotionStock());
    }

    @Test
    @DisplayName("재고가 충분한 경우 decreaseStock 호출 시 stock과 promotionStock이 감소하는지 확인")
    void decreaseStockSufficient() {
        product.addStock(5, false);
        product.addStock(3, true);

        product.decreaseStock(7);

        Assertions.assertThat(1).isEqualTo(product.getStock() + product.getPromotionStock());
    }

    @Test
    @DisplayName("재고가 부족할 때 decreaseStock 호출 시 예외 발생 확인")
    void decreaseStockInsufficient() {
        product.addStock(2, false);
        product.addStock(2, true);

        Assertions.assertThatThrownBy(() -> product.decreaseStock(5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.STOCK_EXCEEDED.getMessage());
    }
}
