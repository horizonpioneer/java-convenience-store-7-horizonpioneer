package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.enums.ErrorMessage;

public class InputView {

    public static String inputProductNameAndQuantity() {
        return readLine();
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
}
