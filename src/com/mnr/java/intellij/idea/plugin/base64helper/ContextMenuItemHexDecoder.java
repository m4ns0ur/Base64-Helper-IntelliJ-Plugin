package com.mnr.java.intellij.idea.plugin.base64helper;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * @author mnr
 */
public class ContextMenuItemHexDecoder extends AbstractEncoderDecoder {
    public ContextMenuItemHexDecoder() {
        super();
    }

    @Override
    public String encodeDecode(String selectedText) {
        return Hex.encodeHexString(Base64.decodeBase64(selectedText)).toUpperCase();
    }

    @Override
    public boolean checkEnableCondition(String selectedText) {
        return super.checkEnableCondition(selectedText) && Base64.isBase64(selectedText);
    }
}
