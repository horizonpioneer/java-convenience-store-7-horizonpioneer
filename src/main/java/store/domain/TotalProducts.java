package store.domain;

import java.util.List;

public class TotalProducts {

    private final List<Product> totalProducts;

    public TotalProducts(List<Product> totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Product extractProduct(String name, int quantity) {
        Product product = findProduct(name);
        reduceProductQuantity(product, quantity);
        return product;
    }

    private Product findProduct(String name) {
        return totalProducts.stream()
                .filter(product -> product.isEqualName(name) && !product.isEqualName("null"))
                .findFirst()
                .or(() -> totalProducts.stream()
                        .filter(product -> product.isEqualName(name) && product.isEqualPromotionName("null"))
                        .findFirst()
                )
                .orElseThrow(() -> new IllegalArgumentException("구매를 원하시는 상품이 매장에 존재하지 않습니다."));
    }

    private void reduceProductQuantity(Product product, int amount) {
        product.decreaseStock(amount);
    }
}
