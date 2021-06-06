package fi.liike.rest.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat urlParamTimestampFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final SimpleDateFormat urlParamDateFormatter = new SimpleDateFormat("yyyy-MM-dd");


    public static String dateToStr(Date date) {
        if (date == null) {
            return null;
        }
        return dateFormatter.format(date);
    }

    public static Date strToDate(String date) {
        if (date == null || date.equals("")) {
            return null;
        }

        int dateLength = date.length();

        if (dateLength == 9 || dateLength == 10) {
            try {
                return new Date(dateFormatter.parse(date.substring(0, dateLength)).getTime());
            } catch (ParseException e) {
                throw new RuntimeException("Date format is invalid: " + date);
            }
        }
        return null;
    }

    public static Date urlParamStrToDate(String date) throws ParseException {
        if (date == null || date.isEmpty() || date.equals("null")) {
            return null;
        }
        Boolean isDateFormat = date.length() == urlParamDateFormatter.toPattern().length();

        if (isDateFormat) {
            return new Date(urlParamDateFormatter.parse(date).getTime());
        } else {
            return new Date(urlParamTimestampFormatter.parse(date).getTime());
        }
    }

    public static String dateToUrlParam(Date date) {
        if (date == null) {
            return null;
        }
        return urlParamTimestampFormatter.format(date);
    }

    public static String dateToUrlParam(Timestamp date) {
        if (date == null) {
            return null;
        }
        return urlParamTimestampFormatter.format(date);
    }
}
