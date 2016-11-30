package com.socialNetwork.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Class to get special format for date
 * 
 * @author UJM's students
 */
public class DateOperation {
    
    /**
     * Method to return string format date : 27/06/06 21:36
     * 
     * @param date
     * 
     * @return String date
     */
    public static String shortFormat(Date date) {
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        return shortDateFormat.format(date);
    }
    
    /**
     * Method to return a date with format : 27/06/06 21:36
     * 
     * @param dateString
     * 
     * @return Date format 27/06/06 21:36
     * 
     * @throws ParseException 
     */
    public static Date shortFormat(String dateStr) throws ParseException {
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        return shortDateFormat.parse(dateStr);
    }
}
