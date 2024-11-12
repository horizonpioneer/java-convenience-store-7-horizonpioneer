package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.enums.ErrorMessage;

public class InputView {

    private InputView() {

    }

    public static String inputProductNameAndQuantity() {
        String input = readLine();
        validateProductInputFormat(input);
        return input;
    }

    public static String inputWhetherToAddBonusQuantity() {
        return readLine();
    }

    public static String inputWhetherToPayFullPriceForSome() {
        return readLine();
    }

    public static String inputWhetherToApplyMembership() {
        return readLine();
    }

    public static String inputWhetherToContinueShopping() {
        return readLine();
    }

    private static String readLine() {
        String input = Console.readLine();
        validateNonBlank(input);
        return input;
    }

    private static void validateNonBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_INPUT.getMessage());
        }
    }

    private static void validateProductInputFormat(String input) {
        for (String item : input.split(",")) {
            validateSingleProductFormat(item.trim());
        }
    }

    private static void validateSingleProductFormat(String item) {
        validateWrappedSpuareBrackets(item);

        String content = item.substring(1, item.length() - 1);
        String[] parts = content.split("-");
        validateDistinguishedByHyphen(parts);

        String productName = parts[0].trim();
        String quantityStr = parts[1].trim();

        validateNotEmptyProductName(productName);
        validateQuantity(quantityStr);
    }

    private static void validateNotEmptyProductName(String productName) {
        if (productName.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }

    private static void validateDistinguishedByHyphen(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }

    private static void validateWrappedSpuareBrackets(String item) {
        if (!item.startsWith("[") || !item.endsWith("]")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }

    private static void validateQuantity(String quantityStr) {
        try {
            int quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }
}
