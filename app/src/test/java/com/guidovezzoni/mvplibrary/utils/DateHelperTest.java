package com.guidovezzoni.mvplibrary.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static com.guidovezzoni.mvplibrary.utils.DateHelper.TIME_FORMAT;
import static junit.framework.Assert.assertEquals;

/**
 * Created by guido on 01/05/17.
 */
public class DateHelperTest {
    private static final Date REF_DATE = DateHelper.getASpecificDate(2016, Calendar.OCTOBER, 4, 10, 15, 37);


    @Test
    public void getFormattedDate() throws Exception {
        // null string test
        assertEquals("", DateHelper.getFormattedDate(TIME_FORMAT, null));

        assertEquals("4 October at 10:15", DateHelper.getFormattedDate(TIME_FORMAT, REF_DATE));
    }


    @Test
    public void getASpecificDate() throws Exception {
        Date date = DateHelper.getASpecificDate(2016, Calendar.OCTOBER, 4, 10, 15, 37);

        assertEquals("Tue Oct 04 10:15:37 BST 2016", date.toString());
    }

}