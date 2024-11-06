package store.view;

import store.domain.Product;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    private static final String SPACE = " ";
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###");

    public static void printWelcomeMessage() {
        System.out.println("안녕하세요. W편의점입니다.");
    }

    public static void printProducts(List<Product> products) {
        System.out.println("현재 보유하고 있는 상품입니다.\n");
        for (Product product : products) {
            String formattedPrice = PRICE_FORMAT.format(product.getPrice());
            String promotionText = "";
            if (!product.getPromotion().equals("null")) {
                promotionText = SPACE + product.getPromotion();
            }
            System.out.println("- " + product.getName() + SPACE + formattedPrice + "원 " + product.getQuantity() + "개" + promotionText);
        }
    }

    public static void requestProductAndQuantity() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }
}
