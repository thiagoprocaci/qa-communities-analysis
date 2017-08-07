package com.tbp.extractor.support


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
