package ir.dotin.core.utils;


import org.apache.commons.lang3.StringUtils;

public class DateUtil {

    public static String getDateWithSlash(String date) {
        String value = date;
        if (value != null && !StringUtils.isEmpty(value) && !value.contains("/") && !value.equalsIgnoreCase("null")) {
            if (value.length() == 12)
                value = value.substring(0, 4) + "/" + value.substring(4, 6) + "/" + value.substring(6, 8) + " " + value.substring(8, 10) + ":" + value.substring(10);
            if (value.length() == 8)
                value = value.substring(0, 4) + "/" + value.substring(4, 6) + "/" + value.substring(6);
            if (value.length() == 6)
                value = value.substring(0, 2) + "/" + value.substring(2, 4) + "/" + value.substring(4, 6);
        }
        return value;
    }
}
