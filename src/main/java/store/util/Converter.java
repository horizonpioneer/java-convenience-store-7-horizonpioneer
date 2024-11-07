package store.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Converter {

    private Converter() {

    }

    public static int convertStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 숫자여야 합니다.");
        }
    }

    public static LocalDate convertStringToLocalDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        formatter = formatter.withLocale(Locale.KOREA);
        return LocalDate.parse(input, formatter);
    }
}
