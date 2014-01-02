package com.mnr.java.intellij.idea.plugin.base64helper;

import org.apache.commons.codec.binary.Base64;

/**
 * @author mnr
 */
public class ContextMenuItemEncoder extends AbstractEncoderDecoder {
    public ContextMenuItemEncoder() {
        super();
    }

    @Override
    public String encodeDecode(String selectedText) {
        return Base64.encodeBase64String(selectedText.getBytes());
    }
}
