package store.handler;

import store.view.InputView;

import java.util.function.Function;
import java.util.function.Supplier;

public class InputHandler {

    private InputHandler() {

    }

    public static String receiveValidatedPurchaseInfo() {
        return receiveValidatedInput(InputView::inputProductNameAndQuantity, Function.identity());
    }

    private static <T> T receiveValidatedInput(Supplier<String> inputView, Function<String, T> conversion) {
        while (true) {
            try {
                String input = inputView.get();
                return conversion.apply(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
