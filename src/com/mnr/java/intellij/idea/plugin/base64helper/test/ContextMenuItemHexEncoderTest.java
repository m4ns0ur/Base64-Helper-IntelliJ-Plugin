package com.mnr.java.intellij.idea.plugin.base64helper.test;

import com.mnr.java.intellij.idea.plugin.base64helper.ContextMenuItemHexEncoder;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * @author m.rahimi
 */
public class ContextMenuItemHexEncoderTest {

    public static final String TEST_HEX_STRING = "aBcdEf";

    @Test
    public void testEncodeDecode() throws Exception {
        ContextMenuItemHexEncoder contextMenuItemHexEncoder = new ContextMenuItemHexEncoder();
        assertNotNull(contextMenuItemHexEncoder);
        String base64String = contextMenuItemHexEncoder.encodeDecode(TEST_HEX_STRING);
    }
}
