package store.domain;

import store.util.Converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

public class ProductLoader {

    private static final String COMMA = ",";

    public static List<Product> loadProducts(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // 첫 줄 건너뜀
            loadProducts(reader, products);
        } catch (IOException e) {
            throw new UncheckedIOException("파일로부터 상품 정보를 불러오는데 실패했습니다.", e);
        }
        return products;
    }

    private static void loadProducts(BufferedReader reader, List<Product> products) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            products.add(getProduct(line));
        }
    }

    private static Product getProduct(String line) {
        String[] values = line.split(COMMA);
        String name = values[0];
        int price = Converter.convertStringToInt(values[1]);
        int quantity = Converter.convertStringToInt(values[2]);
        String promotion = parsePromotion(values);

        return new Product(name, price, quantity, promotion);
    }

    private static String parsePromotion(String[] values) {
        if (values.length > 3 && !values[3].equals("null")) {
            return values[3];
        }
        return "null";
    }
}
