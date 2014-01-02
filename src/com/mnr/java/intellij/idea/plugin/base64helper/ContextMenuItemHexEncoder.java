package com.mnr.java.intellij.idea.plugin.base64helper;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * @author mnr
 */
public class ContextMenuItemHexEncoder extends AbstractEncoderDecoder {
    public ContextMenuItemHexEncoder() {
        super();
    }

    @Override
    public String encodeDecode(String selectedText) {
        selectedText = Util.makeEvenHexDigit(selectedText);

        byte[] decodeHex;

        try {
            decodeHex = Hex.decodeHex(selectedText.toCharArray());
        } catch (DecoderException e) {
            e.printStackTrace();
            return null;
        }

        return Base64.encodeBase64String(decodeHex);
    }

    @Override
    public boolean checkEnableCondition(String selectedText) {
        return super.checkEnableCondition(selectedText) && Util.isHex(selectedText);
    }
}
