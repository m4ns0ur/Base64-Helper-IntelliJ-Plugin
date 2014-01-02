package com.mnr.java.intellij.idea.plugin.base64helper.test;

import com.mnr.java.intellij.idea.plugin.base64helper.Util;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author m.rahimi
 */
public class UtilTest extends AbstractTestData {
    @Test
    public void testIsHex() throws Exception {
        assertTrue(Util.isHex(HEX_TEST_STRING));
        assertFalse(Util.isHex(TEST_STRING));
    }

    @Test
    public void testMakeEvenHexDigit() throws Exception {
        assertEquals("0" + HEX_TEST_STRING, Util.makeEvenHexDigit(HEX_TEST_STRING));
        assertEquals(TEST_STRING, Util.makeEvenHexDigit(TEST_STRING));
    }
}
