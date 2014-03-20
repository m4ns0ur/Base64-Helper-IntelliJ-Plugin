package com.mnr.java.intellij.idea.plugin.base64helper;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * @author m.rahimi
 */
public class HexDecoderPopupItem extends AbstractPopupItem {

    public static final String TEXT = "Base64 Hex String Decoder";

    @Override
    public String getText() {
        return TEXT;
    }

    @Override
    public Boolean isSelectable(String selectedText) {
        return super.isSelectable(selectedText) && Base64.isBase64(selectedText);
    }

    @Override
    public String encodeDecode(String selectedText) {
        return Hex.encodeHexString(Base64.decodeBase64(selectedText)).toUpperCase();
    }
}
