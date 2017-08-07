package com.tbp.extractor.support

import org.springframework.stereotype.Component

@Component
class NumberUtil {

    Long toLong(String s) {
        try {
            return Long.parseLong(s)
        } catch (NumberFormatException e) {
            return null
        }
    }

    Integer toInteger(String s) {
        try {
            return Integer.parseInt(s)
        } catch (NumberFormatException e) {
            return null
        }
    }

}
