package com.mnr.java.intellij.idea.plugin.base64helper.test;

import com.mnr.java.intellij.idea.plugin.base64helper.*;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * @author m.rahimi
 */
public class AbstractpopupItemTest extends AbstractTestData {

    @Test
    public void testEncodeDecode() throws Exception {
        EncoderPopupItem encoderPopupItem = new EncoderPopupItem();
        assertNotNull(encoderPopupItem);
        assertTrue(encoderPopupItem.isSelectable(BASE64_STRING_1));
        assertTrue(encoderPopupItem.isSelectable(TEST_STRING));
        String base64String = encoderPopupItem.encodeDecode(TEST_STRING);
        assertNotNull(base64String);
        assertEquals(BASE64_STRING_1, base64String);

        DecoderPopupItem decoderPopupItem = new DecoderPopupItem();
        assertNotNull(decoderPopupItem);
        assertTrue(decoderPopupItem.isSelectable(BASE64_STRING_1));
        assertFalse(decoderPopupItem.isSelectable(TEST_STRING));
        String textString = decoderPopupItem.encodeDecode(BASE64_STRING_1);
        assertNotNull(textString);
        assertEquals(TEST_STRING, textString);

        assertTrue(encoderPopupItem.isSelectable(TEST_STRING_WITH_LINE_FEED));
        base64String = encoderPopupItem.encodeDecode(TEST_STRING_WITH_LINE_FEED);
        assertNotNull(base64String);
        assertEquals(BASE64_STRING_2, base64String);

        assertTrue(decoderPopupItem.isSelectable(BASE64_STRING_2));
        assertFalse(decoderPopupItem.isSelectable(TEST_STRING_WITH_LINE_FEED));
        textString = decoderPopupItem.encodeDecode(BASE64_STRING_2);
        assertNotNull(textString);
        assertEquals(TEST_STRING_WITH_LINE_FEED, textString);

        HexEncoderPopupItem hexEncoderPopupItem = new HexEncoderPopupItem();
        assertNotNull(hexEncoderPopupItem);
        assertTrue(hexEncoderPopupItem.isSelectable(HEX_TEST_STRING));
        assertFalse(hexEncoderPopupItem.isSelectable(TEST_STRING));
        base64String = hexEncoderPopupItem.encodeDecode(HEX_TEST_STRING);
        assertNotNull(base64String);
        assertEquals(BASE64_STRING_3, base64String);

        HexDecoderPopupItem hexDecoderPopupItem = new HexDecoderPopupItem();
        assertNotNull(hexDecoderPopupItem);
        assertTrue(hexDecoderPopupItem.isSelectable(BASE64_STRING_1));
        assertFalse(hexDecoderPopupItem.isSelectable(TEST_STRING));
        String hexString = hexDecoderPopupItem.encodeDecode(BASE64_STRING_3);
        assertNotNull(hexString);
        assertEquals("0" + HEX_TEST_STRING.toUpperCase(), hexString);
    }
}
