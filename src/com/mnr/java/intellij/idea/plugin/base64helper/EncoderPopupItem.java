package com.mnr.java.intellij.idea.plugin.base64helper;

import org.apache.commons.codec.binary.Base64;

/**
 * @author m.rahimi
 */
public class EncoderPopupItem extends AbstractPopupItem {

    public static final String TEXT = "Base64 String Encoder";

    @Override
    public String getText() {
        return TEXT;
    }

    @Override
    public String encodeDecode(String selectedText) {
        return Base64.encodeBase64String(selectedText.getBytes());
    }
}
