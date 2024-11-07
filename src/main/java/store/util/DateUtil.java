package store.util;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {

    public static boolean isTodayInRange(LocalDate startDate, LocalDate endDate) {
        LocalDateTime todayDateTime = DateTimes.now();
        LocalDate today = todayDateTime.toLocalDate();

        return (today.isEqual(startDate) || today.isAfter(startDate)) &&
                (today.isEqual(endDate) || today.isBefore(endDate));
    }
}
