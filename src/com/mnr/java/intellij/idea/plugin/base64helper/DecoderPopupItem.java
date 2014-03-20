package com.mnr.java.intellij.idea.plugin.base64helper;

import org.apache.commons.codec.binary.Base64;

/**
 * @author m.rahimi
 */
public class DecoderPopupItem extends AbstractPopupItem {

    public static final String TEXT = "Base64 String Decoder";

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
        return new String(Base64.decodeBase64(selectedText));
    }
}
