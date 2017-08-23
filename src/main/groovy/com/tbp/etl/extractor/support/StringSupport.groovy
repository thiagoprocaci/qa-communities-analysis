package com.tbp.etl.extractor.support

import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class StringSupport {

    String prepare(String s) {
        if(StringUtils.hasText(s)) {
            return s
        }
        return null
    }

}
