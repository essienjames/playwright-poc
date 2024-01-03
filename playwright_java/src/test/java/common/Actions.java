package common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Actions {

    public Actions() {}

    public static String timeStampName() {
        // Generate a unique trace name using date/time stamp
        return DateTimeFormatter.ofPattern("dd-MM-yyyy/HH:mm:ss_", Locale.ENGLISH).format(LocalDateTime.now());
    }
}
