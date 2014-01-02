package com.mnr.java.intellij.idea.plugin.base64helper.test;

import com.mnr.java.intellij.idea.plugin.base64helper.ContextMenuItemEncoder;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author m.rahimi
 */
public class ContextMenuItemEncoderTest extends AbstractTestData {
    @Test
    public void testEncodeDecode() throws Exception {
        ContextMenuItemEncoder contextMenuItemEncoder = new ContextMenuItemEncoder();
        assertNotNull(contextMenuItemEncoder);
        String base64String = contextMenuItemEncoder.encodeDecode(TEST_STRING);
        assertNotNull(base64String);
        assertEquals(BASE64_STRING_1, base64String);
    }
}
