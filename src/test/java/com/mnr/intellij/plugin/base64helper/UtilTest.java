/*
 * Copyright 2014 Mansour Rahimi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mnr.intellij.plugin.base64helper;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * @author m.rahimi
 */
public class UtilTest extends AbstractTestData {

    @Test
    public void testIsHex() {
        assertTrue(Util.isHex(HEX_TEST_STRING));
        assertFalse(Util.isHex(TEST_STRING));
    }

    @Test
    public void testMakeEvenHexDigit() {
        assertEquals("0" + HEX_TEST_STRING, Util.makeEvenHexDigit(HEX_TEST_STRING));
        assertEquals(TEST_STRING, Util.makeEvenHexDigit(TEST_STRING));
    }
}
