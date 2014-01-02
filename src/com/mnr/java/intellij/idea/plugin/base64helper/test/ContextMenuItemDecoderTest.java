package com.mnr.java.intellij.idea.plugin.base64helper.test;

import com.mnr.java.intellij.idea.plugin.base64helper.ContextMenuItemDecoder;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author m.rahimi
 */
public class ContextMenuItemDecoderTest extends AbstractTestData {
    @Test
    public void testEncodeDecode() throws Exception {
        ContextMenuItemDecoder contextMenuItemDecoder = new ContextMenuItemDecoder();
        assertNotNull(contextMenuItemDecoder);

        boolean enableCondition = contextMenuItemDecoder.checkEnableCondition(BASE64_STRING_1);
        assertTrue(enableCondition);
        enableCondition = contextMenuItemDecoder.checkEnableCondition(TEST_STRING);
        assertFalse(enableCondition);

        String textString = contextMenuItemDecoder.encodeDecode(BASE64_STRING_1);
        assertNotNull(textString);
        assertEquals(TEST_STRING, textString);
    }
}
