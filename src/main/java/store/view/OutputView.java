package store.view;

import store.domain.Product;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OutputView {

    private static final String SPACE = " ";

    public static void printWelcomeMessage() {
        System.out.println("안녕하세요. W편의점입니다.");
    }

    public static void printProductInfo(List<Product> products) {
        System.out.println("현재 보유하고 있는 상품입니다.\n");
        for (Product product : products) {
            System.out.print(formatProductInfo(product));
        }
    }

    public static void requestProductAndQuantity() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }

    public static void askForMembershipDiscount() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
    }

    private static String formatProductInfo(Product product) {
        return formatPromotionStockInfo(product) + formatRegularStockInfo(product);
    }

    private static String formatPromotionStockInfo(Product product) {
        if (product.hasPromotionStock()) {
            return formatPromotionAvailable(product);
        }

        if (hasPromotionButNoStock(product)) {
            return formatPromotionUnavailable(product);
        }

        return "";
    }

    private static boolean hasPromotionButNoStock(Product product) {
        return !product.hasPromotionStock() && !product.getPromotionName().equals("null");
    }

    private static String formatPromotionUnavailable(Product product) {
        return String.format("- %s %s원 재고 없음 %s%n",
                product.getName(),
                formatPrice(product.getPrice()),
                product.getPromotionName());
    }

    private static String formatPromotionAvailable(Product product) {
        return String.format("- %s %s원 %d개 %s%n",
                product.getName(),
                formatPrice(product.getPrice()),
                product.getPromotionStock(),
                product.getPromotionName());
    }

    private static String formatRegularStockInfo(Product product) {
        if (product.hasStock()) {
            return String.format("- %s %s원 %d개%n",
                    product.getName(),
                    formatPrice(product.getPrice()),
                    product.getStock());
        }
        return String.format("- %s %s원 재고 없음%n",
                product.getName(),
                formatPrice(product.getPrice()));
    }

    private static String formatPrice(int price) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
        return formatter.format(price);
    }
}
