package com.mnr.java.intellij.idea.plugin.base64helper.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author m.rahimi
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContextMenuItemEncoderTest.class,
        ContextMenuItemDecoderTest.class,
        ContextMenuItemHexEncoderTest.class,
        ContextMenuItemHexDecoderTest.class,
        UtilTest.class
})
public class AllTests {
}
