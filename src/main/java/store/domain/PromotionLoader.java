package store.domain;

import store.util.Converter;
import store.util.DateUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PromotionLoader {

    private static final String COMMA = ",";

    public static List<Promotion> loadPromotions(String filePath) {
        List<Promotion> promotions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            readPromotions(reader, promotions);
        } catch (IOException e) {
            throw new UncheckedIOException("파일로부터 상품 정보를 불러오는데 실패했습니다.", e);
        }
        return promotions;
    }

    private static void readPromotions(BufferedReader reader, List<Promotion> promotions) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (getPromotion(line) != null) {
                promotions.add(getPromotion(line));
            }
        }
    }

    private static Promotion getPromotion(String line) {
        String[] values = line.split(COMMA);
        String name = values[0];
        int buyCondition = Converter.convertStringToInt(values[1]);
        int getCondition = Converter.convertStringToInt(values[2]);

        if (validateTodayInRange(values)) {
            return new Promotion(name, buyCondition, getCondition);
        }
        return null;
    }

    private static boolean validateTodayInRange(String[] values) {
        LocalDate startDate = Converter.convertStringToLocalDate(values[3]);
        LocalDate endDate = Converter.convertStringToLocalDate(values[4]);

        return DateUtil.isTodayInRange(startDate, endDate);
    }
}
