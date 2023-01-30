package hibernate.cruddemo.student.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    // The date formatter
    // - dd: day in month (number)
    // - MM: month in year (number)
    // - yyyy: year
    //
    // See this link for details:
    // https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");

    // Read a string format and parse it into a date
    public static Date parseDate(String date) throws ParseException {
        Date theDate = formatter.parse(date);

        return theDate;
    }

    // Read a date and parse it into a string
    public static String formatDate(Date theDate){
        String result = null;

        if (theDate != null) {
            result = formatter.format(theDate);
        }

        return result;
    }
}
