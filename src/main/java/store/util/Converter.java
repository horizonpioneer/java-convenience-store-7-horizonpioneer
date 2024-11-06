package store.util;

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
}
