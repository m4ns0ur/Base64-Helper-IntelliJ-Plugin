package com.mnr.java.intellij.idea.plugin.base64helper;

/**
 * @author m.rahimi
 */
public interface PopupItem {
    String getText();
    Boolean isSelectable(String selectedText);
    String encodeDecode(String selectedText);
}
