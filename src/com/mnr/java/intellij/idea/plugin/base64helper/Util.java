package com.mnr.java.intellij.idea.plugin.base64helper;

/**
 * @author m.rahimi
 */
public class Util {
    public static boolean isHex(String text) {
        return text.matches("[0-9a-fA-F]+");
    }

    public static String makeEvenHexDigit(String hexString) {
        if (!isHex(hexString)) {
            return hexString;
        }

        return ((((hexString.length() % 2) == 0) || (!isHex(hexString))) ? hexString : ("0" + hexString));
    }
}
