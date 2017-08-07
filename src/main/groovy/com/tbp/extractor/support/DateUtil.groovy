package com.tbp.extractor.support

import org.springframework.stereotype.Component

import java.text.SimpleDateFormat

@Component
class DateUtil {

    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    Date toDate(String s) {
        if(s == null || s.trim().length() == 0) {
            return null
        }
        String date = s.replace('T',' ');
        return dt.parse(date)
    }

}
