package com.mnr.java.intellij.idea.plugin.base64helper;

/**
 * @author m.rahimi
 */
public abstract class AbstractPopupItem implements PopupItem {
    @Override
    public abstract String getText();

    @Override
    public Boolean isSelectable(String selectedText) {
        return (selectedText != null) && !selectedText.isEmpty();
    }

    @Override
    public abstract String encodeDecode(String selectedText);
}
