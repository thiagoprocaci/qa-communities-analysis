package com.tbp.extractor.support;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

public class DateUtilTest {

    DateUtil dateUtil;

    @Before
    public void before() {
        dateUtil = new DateUtil();
    }

    @Test
    public void toDate() {
        assertNull(dateUtil.toDate(null));
        assertNull(dateUtil.toDate(""));
        assertNull(dateUtil.toDate(" "));
        assertNull(dateUtil.toDate("abc"));
        assertNotNull(dateUtil.toDate("2016-01-11 22:16:50.167"));
        assertNotNull(dateUtil.toDate("2016-01-11T22:16:50.167"));

        Date d = dateUtil.toDate("2016-01-11T22:16:50.167");
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        assertEquals(2016, c.get(Calendar.YEAR));
        assertEquals(Calendar.JANUARY, c.get(Calendar.MONTH));
        assertEquals(11, c.get(Calendar.DAY_OF_MONTH));
        assertEquals(22, c.get(Calendar.HOUR_OF_DAY));
        assertEquals(16, c.get(Calendar.MINUTE));
        assertEquals(50, c.get(Calendar.SECOND));
        assertEquals(167, c.get(Calendar.MILLISECOND));

        d = dateUtil.toDate("2016-01-11 22:16:50.167");
        c = Calendar.getInstance();
        c.setTime(d);
        assertEquals(2016, c.get(Calendar.YEAR));
        assertEquals(Calendar.JANUARY, c.get(Calendar.MONTH));
        assertEquals(11, c.get(Calendar.DAY_OF_MONTH));
        assertEquals(22, c.get(Calendar.HOUR_OF_DAY));
        assertEquals(16, c.get(Calendar.MINUTE));
        assertEquals(50, c.get(Calendar.SECOND));
        assertEquals(167, c.get(Calendar.MILLISECOND));

    }


}
