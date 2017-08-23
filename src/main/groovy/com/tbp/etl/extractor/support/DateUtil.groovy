package com.tbp.etl.extractor.support

import org.springframework.stereotype.Component

import java.text.ParseException
import java.text.SimpleDateFormat

@Component
class DateUtil {

    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    Date toDate(String s) {
        if(s == null || s.trim().length() == 0) {
            return null
        }
        String date = s.replace('T',' ');
        try {
            return dt.parse(date)
        } catch (ParseException e) {
            return null;
        }
    }

}
