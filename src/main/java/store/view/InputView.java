package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String inputProductNameAndQuantity() {
        return Console.readLine();
    }

    public static String askIfAddRemainingQuantityForPromotion() {
        return Console.readLine();
    }

    public static String askIfPayFullPriceForSomeQuantity() {
        return Console.readLine();
    }

    public static String askIfApplyMembershipDiscount() {
        return Console.readLine();
    }

    public static String askIfWantToBuyMore() {
        return Console.readLine();
    }
}
