package com.mnr.java.intellij.idea.plugin.base64helper;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * @author m.rahimi
 */
public class HexEncoderPopupItem extends AbstractPopupItem {

    public static final String TEXT = "Base64 Hex String Encoder";

    @Override
    public String getText() {
        return TEXT;
    }

    @Override
    public Boolean isSelectable(String selectedText) {
        return super.isSelectable(selectedText) && Util.isHex(selectedText);
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
}
