package com.tbp.extractor.support

import org.springframework.stereotype.Component

@Component
class NumberUtil {

    Long toLong(String s) {
        if(s == null) {
            return null;
        }
        try {
            return Long.parseLong(s.trim())
        } catch (NumberFormatException e) {
            return null
        }
    }

    Integer toInteger(String s) {
        if(s == null) {
            return null;
        }
        try {
            return Integer.parseInt(s.trim())
        } catch (NumberFormatException e) {
            return null
        }
    }

}
