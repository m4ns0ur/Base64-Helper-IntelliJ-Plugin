package com.mnr.java.intellij.idea.plugin.base64helper.test;

import com.mnr.java.intellij.idea.plugin.base64helper.ContextMenuItemHexEncoder;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author m.rahimi
 */
public class ContextMenuItemHexEncoderTest extends AbstractTestData {
    @Test
    public void testEncodeDecode() throws Exception {
        ContextMenuItemHexEncoder contextMenuItemHexEncoder = new ContextMenuItemHexEncoder();
        assertNotNull(contextMenuItemHexEncoder);

        boolean enableCondition = contextMenuItemHexEncoder.checkEnableCondition(HEX_TEST_STRING);
        assertTrue(enableCondition);
        enableCondition = contextMenuItemHexEncoder.checkEnableCondition(TEST_STRING);
        assertFalse(enableCondition);

        String base64String = contextMenuItemHexEncoder.encodeDecode(HEX_TEST_STRING);
        assertNotNull(base64String);
        assertEquals(BASE64_STRING_2, base64String);
    }
}
