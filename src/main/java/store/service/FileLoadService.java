package store.service;

import store.domain.*;

import java.util.List;

public class FileLoadService {

    public TotalProducts loadProductFile() {
        String productFilePath = "src/main/resources/products.md";
        List<Product> products = ProductLoader.loadProducts(productFilePath);
        return new TotalProducts(products);
    }

    public Promotions loadPromotionFile() {
        String promotionFilePath = "src/main/resources/promotions.md";
        List<Promotion> promotionList = PromotionLoader.loadPromotions(promotionFilePath);
        return new Promotions(promotionList);
    }
}
