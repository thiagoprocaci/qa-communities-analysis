package com.tbp.extractor.support

import java.text.SimpleDateFormat


class DateUtil {

    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    Date toDate(String s) {
        if(s == null) {
            return null
        }
        String date = s.replace('T',' ');
        return dt.parse(date)
    }

}
