package com.tbp.extractor.support;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringSupportTest {

    StringSupport stringSupport;

    @Before
    public void before() {
        stringSupport = new StringSupport();
    }

    @Test
    public void prepare() {
        assertNull(stringSupport.prepare(null));
        assertNull(stringSupport.prepare(""));
        assertNull(stringSupport.prepare("  "));
        assertNotNull(stringSupport.prepare("abc"));
        assertEquals("abc", stringSupport.prepare("abc"));
    }

}
