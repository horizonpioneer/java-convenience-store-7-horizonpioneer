package store.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductLoaderTest {

    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("products", ".md");

        String content = "name,price,quantity,promotion\n" +
                "콜라,1000,10,탄산2+1\n" +
                "감자칩,1500,5,null\n";
        Files.writeString(tempFile, content);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    @DisplayName("상품 목록 파일에서 상품 정보를 올바르게 로드하는지 확인")
    void loadProducts() {
        List<Product> products = ProductLoader.loadProducts(tempFile.toString());

        assertEquals(2, products.size(), "로딩된 상품 수 확인");

        Product cola = products.get(0);
        assertEquals("콜라", cola.getName());
        assertEquals(1000, cola.getPrice());
        assertEquals(10, cola.getPromotionStock());
        assertEquals("탄산2+1", cola.getPromotionName());

        Product soda = products.get(1);
        assertEquals("감자칩", soda.getName());
        assertEquals(1500, soda.getPrice());
        assertEquals(5, soda.getStock());
        assertEquals("null", soda.getPromotionName());
    }
}
