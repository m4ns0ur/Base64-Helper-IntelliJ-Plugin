package com.mnr.java.intellij.idea.plugin.base64helper.test;

import com.mnr.java.intellij.idea.plugin.base64helper.ContextMenuItemHexDecoder;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author m.rahimi
 */
public class ContextMenuItemHexDecoderTest extends AbstractTestData {
    @Test
    public void testEncodeDecode() throws Exception {
        ContextMenuItemHexDecoder contextMenuItemHexDecoder = new ContextMenuItemHexDecoder();
        assertNotNull(contextMenuItemHexDecoder);

        boolean enableCondition = contextMenuItemHexDecoder.checkEnableCondition(BASE64_STRING_1);
        assertTrue(enableCondition);
        enableCondition = contextMenuItemHexDecoder.checkEnableCondition(TEST_STRING);
        assertFalse(enableCondition);

        String hexString = contextMenuItemHexDecoder.encodeDecode(BASE64_STRING_2);
        assertNotNull(hexString);
        assertEquals("0" + HEX_TEST_STRING.toUpperCase(), hexString);
    }
}
