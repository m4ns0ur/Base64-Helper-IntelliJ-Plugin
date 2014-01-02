package com.mnr.java.intellij.idea.plugin.base64helper;

import org.apache.commons.codec.binary.Base64;

/**
 * @author mnr
 */
public class ContextMenuItemDecoder extends AbstractEncoderDecoder {
    public ContextMenuItemDecoder() {
        super();
    }

    @Override
    public String encodeDecode(String selectedText) {
        return new String(Base64.decodeBase64(selectedText));
    }

    @Override
    public boolean checkEnableCondition(String selectedText) {
        return super.checkEnableCondition(selectedText) && Base64.isBase64(selectedText);
    }
}
