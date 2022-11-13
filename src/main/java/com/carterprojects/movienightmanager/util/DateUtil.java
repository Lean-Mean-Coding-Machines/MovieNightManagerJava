package com.carterprojects.movienightmanager.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtil {

    public static LocalDateTime getDateTimeUtc() {
        return LocalDateTime.now(ZoneId.of("UTC"));
    }

}
