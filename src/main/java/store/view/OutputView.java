package store.view;

import store.domain.Product;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    private static final String SPACE = " ";
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###");

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
}
