/**
 * Copyright 2017 BhaShiNi Digitization Services Pvt. Ltd.
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
package com.bhashiniservices.g2p;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class G2P_BaseTest {

    @Test
    public void testGetContentsOfUnicodeFile() throws IOException {
        String baseDir = "src/test/resources/TestEncodings/";
        String expectedText = "\u0C95\u0CA8\u0CCD\u0CA8\u0CA1";
        String str1 = new String(G2P_Base.getContentsOfUnicodeFile(baseDir + "Test_UTF8.txt"));
        assertTrue(str1.equals(expectedText));
        String str2 = new String(G2P_Base.getContentsOfUnicodeFile(baseDir + "Test_UTF16_BigEndian.txt"));
        assertTrue(str2.equals(expectedText));
        String str3 = new String(G2P_Base.getContentsOfUnicodeFile(baseDir + "Test_UTF16_LittleEndian.txt"));
        assertTrue(str3.equals(expectedText));
        String str4 = new String(G2P_Base.getContentsOfUnicodeFile(baseDir
                + "Test_UTF16_BigEndian_WithoutBOM.txt"));
        assertTrue(str4.equals(expectedText));
        String str5 = new String(G2P_Base.getContentsOfUnicodeFile(baseDir
                + "Test_UTF16_LittleEndian_WithoutBOM.txt"));
        assertTrue(str5.equals(expectedText));
    }

}
