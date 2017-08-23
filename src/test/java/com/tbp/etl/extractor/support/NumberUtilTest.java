package com.tbp.etl.extractor.support;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NumberUtilTest {

    NumberUtil numberUtil;

    @Before
    public void before() {
        numberUtil = new NumberUtil();
    }

    @Test
    public void toLong() {
        assertNull(numberUtil.toLong(null));
        assertNull(numberUtil.toLong(""));
        assertNull(numberUtil.toLong(" "));
        assertNull(numberUtil.toLong("abc"));
        assertNull(numberUtil.toLong("1qza"));
        assertNotNull(numberUtil.toLong("1"));
        assertNotNull(numberUtil.toLong(" 1 "));
        assertNotNull(numberUtil.toLong(" 1100 "));
        assertNull(numberUtil.toLong(" 11 0 "));
    }

    @Test
    public void toInteger() {
        assertNull(numberUtil.toInteger(null));
        assertNull(numberUtil.toInteger(""));
        assertNull(numberUtil.toInteger(" "));
        assertNull(numberUtil.toInteger("abc"));
        assertNull(numberUtil.toInteger("1qza"));
        assertNotNull(numberUtil.toInteger("1"));
        assertNotNull(numberUtil.toInteger(" 1 "));
        assertNotNull(numberUtil.toInteger(" 1100 "));
        assertNull(numberUtil.toInteger(" 11 0 "));
    }



}
