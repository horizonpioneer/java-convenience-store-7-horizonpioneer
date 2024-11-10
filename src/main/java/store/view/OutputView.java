package store.view;

import store.domain.Product;
import store.domain.ShoppingCart;
import store.domain.TotalProducts;
import store.dto.AdditionalBonusDto;
import store.dto.PurchaseItemDto;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OutputView {

    private static final String SPACE = " ";

    public static void printWelcomeMessage() {
        System.out.println("안녕하세요. W편의점입니다.");
    }

    public static void printProductInfo(TotalProducts totalProducts) {
        System.out.println("현재 보유하고 있는 상품입니다.\n");
        for (Product product : totalProducts.getTotalProducts()) {
            System.out.print(formatProductInfo(product));
        }
        System.out.println();
    }

    public static void requestProductAndQuantity() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }

    public static void askForMembershipDiscount() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
    }

    public static void printPurchaseReceipt(ShoppingCart shoppingCart, TotalProducts totalProducts) {

        System.out.println("==============W 편의점================");
        System.out.printf("%-10s\t%-7s\t%10s%n", "상품명", "수량", "금액");
        for (PurchaseItemDto item : shoppingCart.getItems()) {
            System.out.printf("%-15s\t%d\t%,15d%n", item.getName(), item.getQuantity(), item.calculatePrice(totalProducts));
        }
    }

    public static void printGiftProductInfo(List<AdditionalBonusDto> dtos) {
        System.out.println("=============증\t\t정===============");
        for (AdditionalBonusDto dto : dtos) {
            System.out.printf("%-10s\t%-5d%n", dto.getName(), dto.getQuantity());
        }
    }

    public static void printTotalPurchaseAmount(ShoppingCart shoppingCart, TotalProducts totalProducts) {
        System.out.println("====================================");
        int totalQuantity = 0;
        int totalPrice = 0;
        for (PurchaseItemDto item : shoppingCart.getItems()) {
            totalQuantity += item.getQuantity();
            totalPrice += item.calculatePrice(totalProducts);
        }
        System.out.println("총구매액\t\t" + totalQuantity + "\t" + totalPrice);
        System.out.println();
    }

    public static void printEventDiscount(List<AdditionalBonusDto> dtos) {
        int totalDiscount = 0;
        for (AdditionalBonusDto dto : dtos) {
            totalDiscount += dto.getDiscountAmount();
        }
        System.out.println("행사할인\t\t\t" + "-" + totalDiscount);
    }

    public static void printMembershipDiscount(int membershipDiscount) {
        System.out.println("멤버십할인\t\t\t" + "-" + membershipDiscount);
    }

    public static void askIfBuyOtherProducts() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
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
