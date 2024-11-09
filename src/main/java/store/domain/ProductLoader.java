package store.domain;

import store.util.Converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductLoader {
    private static final String COMMA = ",";

    public static List<Product> loadProducts(String filePath, List<Promotion> promotions) {
        Map<String, Product> productMap = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line, productMap, promotions);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("상품 목록으로부터 상품 정보를 읽어올 수 없습니다.", e);
        }
        return new ArrayList<>(productMap.values());
    }

    private static void processLine(String line, Map<String, Product> productMap, List<Promotion> promotions) {
        String[] values = line.split(COMMA);
        String name = values[0];
        int price = Converter.convertStringToInt(values[1]);
        int quantity = Converter.convertStringToInt(values[2]);
        String promotionName = values[3];

        Product product = productMap.computeIfAbsent(name, k -> new Product(name, price, promotionName));
        addStock(promotionName, product, quantity);
    }

    private static void addStock(String promotionName, Product product, int quantity) {
        boolean isPromotionStock = !promotionName.equals("null");
        product.addStock(quantity, isPromotionStock);
    }
}
