package com.mnr.java.intellij.idea.plugin.base64helper.test;

import com.mnr.java.intellij.idea.plugin.base64helper.ContextMenuItemDecoder;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author m.rahimi
 */
public class ContextMenuItemDecoderTest extends AbstractTestData {
    @Test
    public void testEncodeDecode() throws Exception {
        ContextMenuItemDecoder contextMenuItemDecoder = new ContextMenuItemDecoder();
        assertNotNull(contextMenuItemDecoder);
        String textString = contextMenuItemDecoder.encodeDecode(BASE64_STRING);
        assertNotNull(textString);
        assertEquals(TEST_STRING, textString);
    }
}
