package ir.dotin.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by h.safiary on 4/23/2016.
 */
public class TimeUtil {

    public static String getTimeFormated(String time) {
        if (time != null && !time.contains(":") && !time.equalsIgnoreCase("null")) {
            if (time.length() == 4)
                return time.substring(0, 2) + ":" + time.substring(2, 4);
        }
        return "";
    }

}
