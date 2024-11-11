package store.controller;

import camp.nextstep.edu.missionutils.Console;
import store.domain.*;
import store.dto.AdditionalBonusDto;
import store.dto.PurchaseItemDto;
import store.service.FileLoadService;
import store.util.Converter;
import store.view.InputView;
import store.view.OutputView;

import java.util.ArrayList;
import java.util.List;


public class ConvenienceController {

    private final FileLoadService fileLoadService = new FileLoadService();

    public void run() {
        Promotions promotions = fileLoadService.loadPromotionFile();
        TotalProducts totalProducts = fileLoadService.loadProductFile();

        boolean continueShopping = true;
        while (continueShopping) {
            OutputView.printWelcomeMessage();
            OutputView.printProductInfo(totalProducts);

            OutputView.requestProductAndQuantity();
            String productInfo = InputView.inputProductNameAndQuantity();

            ShoppingCart shoppingCart = addPurchaseItemDtos(productInfo);

            List<AdditionalBonusDto> bonusDtos = new ArrayList<>();

            processPromotionsForCartItems(shoppingCart, totalProducts, promotions, bonusDtos);

            // 멤버십 할인 적용 여부
            int membershipDiscount = 0;
            OutputView.askForMembershipDiscount();
            String response = InputView.askIfApplyMembershipDiscount();
            if (response.equalsIgnoreCase("Y")) {
                int totalPrice = shoppingCart.calculateNonPromotionTotalPrice(totalProducts);
                membershipDiscount = calculateMembershipDiscount(totalPrice);
            }

            // 재고 감소 및 재고 부족 예외 처리
            try {
                for (PurchaseItemDto item : shoppingCart.getItems()) {
                    totalProducts.extractProduct(item.getName(), item.getQuantity());
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            OutputView.printTotalReceipt(shoppingCart, totalProducts, bonusDtos, membershipDiscount);

            OutputView.askIfBuyOtherProducts();
            String otherBuyResponse = Console.readLine();
            continueShopping = otherBuyResponse.equalsIgnoreCase("Y");
        }
    }

    private void processPromotionsForCartItems(ShoppingCart shoppingCart, TotalProducts totalProducts, Promotions promotions, List<AdditionalBonusDto> bonusDtos) {
        for (PurchaseItemDto purchaseItem : shoppingCart.getItems()) {
            Product product = totalProducts.findProduct(purchaseItem.getName());
            Promotion promotion = promotions.getPromotionConditionByName(product.getPromotionName());

            if (!product.isEqualPromotionName("null") && promotion.validateTodayInRange()) {
                int additionalBonusCount = promotion.calculateAdditionalBonus(purchaseItem.getQuantity());
                confirmBonusPromotion(bonusDtos, purchaseItem, product, additionalBonusCount);
                confirmNonDiscountedPurchase(purchaseItem, product, promotion);
            }
        }
    }

    private void confirmBonusPromotion(List<AdditionalBonusDto> bonusDtos, PurchaseItemDto purchaseItem, Product product, int additionalBonusCount) {
        // 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우
        if (product.getPromotionStock() > purchaseItem.getQuantity() + additionalBonusCount) {
            System.out.printf("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)%n",
                    product.getName(), additionalBonusCount);
            String response = Console.readLine();

            if (response.equalsIgnoreCase("Y")) {
                int discountAmount = product.getPrice() * additionalBonusCount;
                bonusDtos.add(new AdditionalBonusDto(product.getName(), additionalBonusCount, discountAmount));
            }
        }
    }

    private void confirmNonDiscountedPurchase(PurchaseItemDto purchaseItem, Product product, Promotion promotion) {
        // 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우
        if (product.getPromotionStock() < purchaseItem.getQuantity()) {
            int nonDiscountedQuantity = getNonDiscountedQuantity(product, promotion, purchaseItem);
            System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)%n",
                    product.getName(), nonDiscountedQuantity);
            String response = Console.readLine();

            if (!response.equalsIgnoreCase("Y")) {
                purchaseItem.excludeFromPurchase(true);
            }
        }
    }

    private int getNonDiscountedQuantity(Product product, Promotion promotion, PurchaseItemDto purchaseItem) {
        return product.getPromotionStock() % (promotion.getBuyCondition() + promotion.getGetCondition()) + (purchaseItem.getQuantity() - product.getPromotionStock());
    }

    private ShoppingCart addPurchaseItemDtos(String productInfo) {
        String[] items = productInfo.split(",");
        return addPurchaseProducts(items);
    }

    private ShoppingCart addPurchaseProducts(String[] items) {
        List<PurchaseItemDto> shoppingCart = new ArrayList<>();

        for (String item : items) {
            String name = item.substring(item.indexOf('[') + 1, item.indexOf('-')).trim();
            int quantity = Converter.convertStringToInt(item.substring(item.indexOf('-') + 1, item.indexOf(']')).trim());
            shoppingCart.add(new PurchaseItemDto(name, quantity));
        }
        return new ShoppingCart(shoppingCart);
    }

    private int calculateMembershipDiscount(int nonPromotionTotalPrice) {
        int discount = (nonPromotionTotalPrice * 30) / 100;

        return Math.min(discount, 8000);
    }
}

