package moorcommon.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MoorDateUtility {

    public static String dateToFormattedString(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        return df.format(date);
    }

    public static String getOnlyStringDateFromDateAsMySQLFormat(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        return df.format(date);
    }

    public static String getOnlyFormattedStringDateFromDate(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        return df.format(date);
    }

    public static String getOnlyMonthAndYearFromDate(Date date) {
        DateFormat df = new SimpleDateFormat("MM/yyyy", Locale.ENGLISH);

        return df.format(date);
    }

    public static String getOnlyYearFromDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        return df.format(date);
    }


    public static Date stringToDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            Date date1 = format.parse(date);
            return date1;
        } catch (ParseException e) {
            try {
                DateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
                return format1.parse(date);
            } catch (Exception e1) {
                try {
                    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
                    return format1.parse(date);
                } catch (Exception e2) {
                    try {
                        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
                        return format1.parse(date);
                    } catch (Exception e3) {
                        try {
                            DateFormat format1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
                            return format1.parse(date);
                        } catch (Exception e4) {
                        } try{
                            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                            return format1.parse(date);
                        } catch (Exception e5){
                            try {
                                DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                return format1.parse(date);
                            }catch (Exception e6){
                                DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                                return format1.parse(date);
                            }
                        }
                    }
                }
            }
        }
    }

    public static String toShow(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);

        return format.format(date);
    }

    public static String toQuery(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:00", Locale.ENGLISH);
        return format.format(date);
    }


    public static String dateToStringAsMySQLFormatDayStart(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.ENGLISH);
        return format.format(date);
    }

    public static String dateToStringAsMySQLFormatDayEnd(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59", Locale.ENGLISH);

        return format.format(date);
    }


    public static String dateToStringAsMySQLDatabaseFormat(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        return df.format(date);
    }
}
