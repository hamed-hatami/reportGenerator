package ir.dotin.core.utils;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarUtil {

    private static final int[] STYLES;
    public static final TimeZone TIME_ZONE;

    static {
        STYLES = new int[]{DateFormat.DEFAULT, DateFormat.SHORT, DateFormat.MEDIUM, DateFormat.LONG, DateFormat.FULL};
        TIME_ZONE = TimeZone.getTimeZone(Configuration.getProperty("timezone"));
    }

    public static String getDate(Locale locale) {
        return getDate(new Date(), locale);
    }

    public static String getDate(Date date) {
        return getDate(date, null);
    }

    public static Date getDate(String date) {
        return getDate(date, null);
    }

    public static String getDateWithSlash(Date date, Locale locale) {
        if (date == null)
            return null;

        SimpleDateFormat sdf;
        Calendar calendar;

        if (locale != null && locale.equals(LangUtils.LOCALE_FARSI)) {
            calendar = new PersianCalendar(locale);
            calendar.setTime(date);

            sdf = (SimpleDateFormat) calendar.getDateTimeFormat(STYLES[1], STYLES[1], locale);
            sdf.applyPattern("yyyy/MM/dd");

            return sdf.format(calendar.getTime());
        } else {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(date);
        }
    }

    public static String getDaysBeforeDate(String date, int days) {
        String currentdate = "";
        date = date.replace("/", "");

        int date_year = Integer.valueOf(date.substring(0, 2));
        int date_month = Integer.valueOf(date.substring(2, 4));
        int date_day = Integer.valueOf(date.substring(4, 6));

        PersianCalendar calendar = new PersianCalendar(new Locale("fa"));
        calendar.set(date_year, date_month, date_day);
        calendar.add(Calendar.DATE, -1);

        String now_year = String.valueOf(calendar.get(Calendar.YEAR));
        String now_month = String.valueOf(calendar.get(Calendar.MONTH));
        String now_day = String.valueOf(calendar.get(Calendar.DATE));

        if (now_month.length() != 2) {
            now_month = "0" + now_month;
        }
        if (now_day.length() != 2) {
            now_day = "0" + now_day;
        }

        currentdate = now_year + now_month + now_day;

        return currentdate;
    }

    public static String addDate(String date, int value, int operation) {
        String currentdate = "";
        date = date.replaceAll("/", "");

        int date_year = 0;
        int date_month = 0;
        int date_day = 0;
        if (date.length() == 6) {
            date_year = Integer.valueOf(date.substring(0, 2));
            date_month = Integer.valueOf(date.substring(2, 4));
            date_day = Integer.valueOf(date.substring(4, 6));
        } else if (date.length() == 8) {
            date_year = Integer.valueOf(date.substring(0, 4));
            date_month = Integer.valueOf(date.substring(4, 6));
            date_day = Integer.valueOf(date.substring(6, 8));
        }

        PersianCalendar calendar = new PersianCalendar(new Locale("fa"));
        calendar.set(date_year, date_month, date_day);

        switch (operation) {
            case 1:
                calendar.add(Calendar.DATE, value);
                break;
            case 2:
                calendar.add(Calendar.WEEK_OF_MONTH, value);
                break;
            case 3:
                calendar.add(Calendar.MONTH, value);
                break;
            default:
        }

        String now_year = String.valueOf(calendar.get(Calendar.YEAR));
        String now_month = String.valueOf(calendar.get(Calendar.MONTH));
        String now_day = String.valueOf(calendar.get(Calendar.DATE));

        if (now_month.length() != 2) {
            now_month = "0" + now_month;
        }

        if (now_month.equalsIgnoreCase("00")) {
            now_month = "12";
            now_year = String.valueOf(Integer.valueOf(now_year) - 1);
        }

        if (now_day.length() != 2) {
            now_day = "0" + now_day;
        }

        currentdate = now_year + now_month + now_day;

        return currentdate.trim();
    }

    public static long getDurationTime(String firstDate, String secondDate) {

        int firstDate_year = Integer.valueOf(firstDate.substring(0, 2));
        int firstDate_month = Integer.valueOf(firstDate.substring(2, 4));
        int firstDate_day = Integer.valueOf(firstDate.substring(4, 6));

        int secondDate_year = Integer.valueOf(secondDate.substring(0, 2));
        int secondDate_month = Integer.valueOf(secondDate.substring(2, 4));
        int secondDate_day = Integer.valueOf(secondDate.substring(4, 6));

        PersianCalendar firstCalendar = new PersianCalendar(new Locale("fa"));
        firstCalendar.set(firstDate_year, firstDate_month, firstDate_day);

        PersianCalendar secondCalendar = new PersianCalendar(new Locale("fa"));
        secondCalendar.set(secondDate_year, secondDate_month, secondDate_day);

        Date firstProcessedDate = firstCalendar.getTime();
        Date secondProcessedDate = secondCalendar.getTime();

        return secondProcessedDate.getTime() - firstProcessedDate.getTime();

    }

    public static int getDaysBetween(String firstDate, String secondDate) {

        int firstDate_year = Integer.valueOf(firstDate.substring(0, 2));
        int firstDate_month = Integer.valueOf(firstDate.substring(2, 4));
        int firstDate_day = Integer.valueOf(firstDate.substring(4, 6));

        int secondDate_year = Integer.valueOf(secondDate.substring(0, 2));
        int secondDate_month = Integer.valueOf(secondDate.substring(2, 4));
        int secondDate_day = Integer.valueOf(secondDate.substring(4, 6));

        PersianCalendar firstCalendar = new PersianCalendar(new Locale("fa"));
        firstCalendar.set(firstDate_year, firstDate_month, firstDate_day);

        PersianCalendar secondCalendar = new PersianCalendar(new Locale("fa"));
        secondCalendar.set(secondDate_year, secondDate_month, secondDate_day);

        Date firstProcessedDate = firstCalendar.getTime();
        Date secondProcessedDate = secondCalendar.getTime();

        return (int) ((secondProcessedDate.getTime() - firstProcessedDate.getTime()) / (1000 * 60 * 60 * 24));
    }


    public static Date getDate(String date, Locale locale) {

        if (date == null)
            return null;

        if (locale != null && locale.equals(LangUtils.LOCALE_FARSI)) {
            String[] splitDate = date.split("/");
            SimplePersianCalendar spc = new SimplePersianCalendar();
            spc.setDateFields(Integer.parseInt(splitDate[0]), (Integer.parseInt(splitDate[1]) - 1), Integer.parseInt(splitDate[2]));
            return new Date(spc.getTimeInMillis());
        } else {
            String[] splitDate = date.split("/");
            GregorianCalendar gc = new GregorianCalendar();
            gc.set(Integer.parseInt(splitDate[2]), (Integer.parseInt(splitDate[1]) - 1), Integer.parseInt(splitDate[0]));
            return new Date(gc.getTimeInMillis());
        }
    }

    public static Date getDateWithoutSlash(String date, Locale locale) {

        if (date == null)
            return null;

        if (locale != null && locale.equals(LangUtils.LOCALE_FARSI)) {
            SimplePersianCalendar spc = new SimplePersianCalendar();
            spc.setDateFields(Integer.parseInt(date.substring(0, 4)), (Integer.parseInt(date.substring(4, 6)) - 1), Integer.parseInt(date.substring(6)));
            return new Date(spc.getTimeInMillis());
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            gc.set(Integer.parseInt(date.substring(0, 2)), (Integer.parseInt(date.substring(2, 4)) - 1), Integer.parseInt(date.substring(4)));
            return new Date(gc.getTimeInMillis());
        }
    }

    public static Date getDateByPattern(String date, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDate(Date date, Locale locale) {
        if (date == null)
            return null;

        SimpleDateFormat sdf;
        Calendar calendar;

        if (locale != null && locale.equals(LangUtils.LOCALE_FARSI)) {
            calendar = new PersianCalendar(locale);
            calendar.setTime(date);
            sdf = (SimpleDateFormat) calendar.getDateTimeFormat(STYLES[1], STYLES[1], locale);
            return sdf.format(calendar.getTime());
        } else {
            sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return sdf.format(date);
        }
    }

    public static String getTime(Date date, Locale locale) {

        if (date == null)
            return null;

        SimpleDateFormat sdf;
        Calendar calendar;


        if (locale != null && locale.equals(LangUtils.LOCALE_FARSI)) {
            calendar = new PersianCalendar(locale);
            calendar.setTime(date);

            sdf = (SimpleDateFormat) calendar.getDateTimeFormat(STYLES[1], STYLES[1], locale);
            sdf.applyPattern("HH:mm:ss");

            return sdf.format(calendar.getTime());
        } else {
            sdf = new SimpleDateFormat("HH:mm:ss");
            return sdf.format(date);
        }
    }


    public static String getTimeStamp(Date date, Locale locale) {

        if (date == null)
            return null;

        SimpleDateFormat sdf;
        Calendar calendar;

        if (locale != null && locale.equals(LangUtils.LOCALE_FARSI)) {
            calendar = new PersianCalendar(locale);
            calendar.setTime(date);

            sdf = (SimpleDateFormat) calendar.getDateTimeFormat(STYLES[1], STYLES[1], locale);
            sdf.applyPattern("yyMMddHHmmss");

            return sdf.format(calendar.getTime());
        } else {
            sdf = new SimpleDateFormat("ddMMyyHHmmss");
            return sdf.format(date);
        }

    }

    public static String getTimeStamp(Date date, Locale locale, String pattern) {

        if (date == null)
            return null;

        SimpleDateFormat sdf;
        Calendar calendar;

        if (locale != null && locale.equals(LangUtils.LOCALE_FARSI)) {
            calendar = new PersianCalendar(locale);
            calendar.setTime(date);

            sdf = (SimpleDateFormat) calendar.getDateTimeFormat(STYLES[1], STYLES[1], locale);
            sdf.applyPattern(pattern);

            return sdf.format(calendar.getTime());
        } else {
            sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }
    }


    public static String getDateWithoutSlash(Date date, Locale locale) {

        if (date == null)
            return null;

        SimpleDateFormat sdf;
        Calendar calendar;

        if (locale != null && locale.equals(LangUtils.LOCALE_FARSI)) {
            calendar = new PersianCalendar(locale);
            calendar.setTime(date);

            sdf = (SimpleDateFormat) calendar.getDateTimeFormat(STYLES[1], STYLES[1], locale);
            sdf.applyPattern("yyyyMMddHHmm");

            return sdf.format(calendar.getTime());
        } else {
            sdf = new SimpleDateFormat("ddMMyyyyHHmm");
            return sdf.format(date);
        }
    }

    public static String getDateWithoutSlash(Date date, Locale locale, String pattern) {

        if (date == null)
            return null;

        SimpleDateFormat sdf;
        Calendar calendar;

        if (locale != null && locale.equals(LangUtils.LOCALE_FARSI)) {
            calendar = new PersianCalendar(locale);
            calendar.setTime(date);

            sdf = (SimpleDateFormat) calendar.getDateTimeFormat(STYLES[1], STYLES[1], locale);
            sdf.applyPattern(pattern);

            return sdf.format(calendar.getTime());
        } else {
            sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }
    }

    public static String convertGregorianToPersian(String date) {
        int year, month, day;
        DateFields t;
        String[] splitDate = date.split("/");
        String value = "";
        try {
            try {
                year = Integer.parseInt(splitDate[2]);
            } catch (Exception nfe) {
                year = 0;
            }
            try {
                month = Integer.parseInt(splitDate[1]) - 1;
            } catch (Exception nfe) {
                month = 0;
            }
            try {
                day = Integer.parseInt(splitDate[0]);
            } catch (Exception nfe) {
                day = 0;
            }

            SimplePersianCalendar c = new SimplePersianCalendar();
            c.set(c.YEAR, year);
            c.set(c.MONTH, month);
            c.set(c.DAY_OF_MONTH, day);
            t = c.getDateFields();

            String shamsiYear = Long.toString(t.getYear());
            String shamsiMonth = Long.toString(t.getMonth() + 1);
            String shamsiDay = Long.toString(t.getDay());
            value = shamsiYear + String.valueOf(File.separatorChar) + shamsiMonth + String.valueOf(File.separatorChar) + shamsiDay;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String convertGregorianToPersianWithoutSlash(String date) {
        int year, month, day;
        DateFields t;
        String[] splitDate = date.split("/");
        String value = "";
        try {
            try {
                year = Integer.parseInt(splitDate[2]);
            } catch (Exception nfe) {
                year = 0;
            }
            try {
                month = Integer.parseInt(splitDate[1]) - 1;
            } catch (Exception nfe) {
                month = 0;
            }
            try {
                day = Integer.parseInt(splitDate[0]);
            } catch (Exception nfe) {
                day = 0;
            }

            SimplePersianCalendar c = new SimplePersianCalendar();
            c.set(c.YEAR, year);
            c.set(c.MONTH, month);
            c.set(c.DAY_OF_MONTH, day);
            t = c.getDateFields();

            String shamsiYear = Long.toString(t.getYear());
            String shamsiMonth = t.getMonth() < 10 ? '0' + Long.toString(t.getMonth() + 1) : Long.toString(t.getMonth() + 1);
            String shamsiDay = t.getDay() < 10 ? '0' + Long.toString(t.getDay()) : Long.toString(t.getDay());
            value = shamsiYear + shamsiMonth + shamsiDay;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String convertPersianToGregorian(String date) {
        int year, month, day;
        DateFields t;
        String[] splitDate = date.split("/");
        String value = "";
        try {
            try {
                year = Integer.parseInt(splitDate[0]);
            } catch (Exception nfe) {
                year = 0;
            }
            try {
                month = Integer.parseInt(splitDate[1]) - 1;
            } catch (Exception nfe) {
                month = 0;
            }
            try {
                day = Integer.parseInt(splitDate[2]);
            } catch (Exception nfe) {
                day = 0;
            }

            SimplePersianCalendar c = new SimplePersianCalendar();
            c.setDateFields(year, month, day);
            String miladiYear = Long.toString(c.get(c.ERA) == c.AD ? c.get(c.YEAR) : -(c.get(c.YEAR) - 1));
            String miladiMonth = Long.toString(c.get(c.MONTH) + 1);
            String miladiDay = Long.toString(c.get(c.DAY_OF_MONTH));
            value = miladiDay + String.valueOf(File.separatorChar) + miladiMonth + String.valueOf(File.separatorChar) + miladiYear;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String convertPersianToGregorianWithout(String date) {
        int year, month, day;
        DateFields t;
        String value = "";
        try {
            try {
                year = Integer.parseInt(date.substring(0, 4));
            } catch (Exception nfe) {
                year = 0;
            }
            try {
                month = Integer.parseInt(date.substring(4, 6)) - 1;
            } catch (Exception nfe) {
                month = 0;
            }
            try {
                day = Integer.parseInt(date.substring(6, 8));
            } catch (Exception nfe) {
                day = 0;
            }

            SimplePersianCalendar c = new SimplePersianCalendar();
            c.setDateFields(year, month, day);
            String miladiYear = Long.toString(c.get(c.ERA) == c.AD ? c.get(c.YEAR) : -(c.get(c.YEAR) - 1));
            String miladiMonth = Long.toString(c.get(c.MONTH) + 1);
            String miladiDay = Long.toString(c.get(c.DAY_OF_MONTH));
            value = miladiDay + "/" + (miladiMonth.length() == 2 ? miladiMonth : "0" + miladiMonth) + "/" + miladiYear;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getDateWithoutTime(Date date, Locale locale) {
        if (date == null)
            return null;

        SimpleDateFormat sdf;
        Calendar calendar;

        if (locale != null && locale.equals(LangUtils.LOCALE_FARSI)) {
            calendar = new PersianCalendar(locale);
            calendar.setTime(date);

            sdf = (SimpleDateFormat) calendar.getDateTimeFormat(STYLES[1], STYLES[1], locale);
            sdf.applyPattern("yyyyMMdd");

            return sdf.format(calendar.getTime());
        } else {
            sdf = new SimpleDateFormat("ddMMyyyy");
            return sdf.format(date);
        }
    }

    public static String getDateWithoutTime(Locale locale) {
        return getDateWithoutTime(new Date(), locale);
    }

    public static String getStringDateWithoutSlash(String date) {
        if (!date.contains("/")) {
            return date;
        }
        String[] split = date.split("/");
        if (split[0].length() > 2) {
            split[0] = split[0].substring(2);
        }
        return split[0] + split[1] + split[2];
    }

    public static String getFullDate(int number) {
        String fullDate = "";
        if (number < 10) {
            fullDate = "0" + String.valueOf(number) + "0000";
        } else {
            fullDate = String.valueOf(number) + "0000";
        }

        return fullDate;
    }
}