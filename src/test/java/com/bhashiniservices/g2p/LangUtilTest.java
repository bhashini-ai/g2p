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

import org.junit.Test;

public class LangUtilTest {
    @Test
    public void testRecognizeLanguage() {
        String str1 = "\u0C95\u0CA8\u0CCD\u0CA8\u0CA1"; // Kannada text
        assertTrue(LangUtil.recognizeLanguage(str1) == LangUtil.LANGUAGE_KANNADA);
        String str2 = "\u0BA4\u0BAE\u0B87\u0BB4"; // Tamil text
        assertTrue(LangUtil.recognizeLanguage(str2) == LangUtil.LANGUAGE_TAMIL);
        String str3 = "English";
        assertTrue(LangUtil.recognizeLanguage(str3) == LangUtil.LANGUAGE_UNKNOWN);

        String str4 = "2011\u0C95\u0CA8\u0CCD\u0CA8\u0CA1"; // Kannada text with ASCII numbers
        assertTrue(LangUtil.recognizeLanguage(str4) == LangUtil.LANGUAGE_KANNADA);
        String str5 = "2011\u0BA4\u0BAE\u0B87\u0BB4"; // Tamil text with ASCII numbers
        assertTrue(LangUtil.recognizeLanguage(str5) == LangUtil.LANGUAGE_TAMIL);
        String str6 = "2011English";
        assertTrue(LangUtil.recognizeLanguage(str6) == LangUtil.LANGUAGE_TAMIL);

        String str7 = "2011\u0BA4\u0BAE\u0B87\u0BB4\u0C95"; // Tamil text along with a Kannada character
        assertTrue(LangUtil.recognizeLanguage(str7) == LangUtil.LANGUAGE_TAMIL);
        String str8 = "2011\u0C95\u0CA8\u0CCD\u0CA8\u0CA1\u0BA4"; // Kannada text along with a Tamil character
        assertTrue(LangUtil.recognizeLanguage(str8) == LangUtil.LANGUAGE_KANNADA);
        String str9 = "2011English*77";
        assertTrue(LangUtil.recognizeLanguage(str9) == LangUtil.LANGUAGE_KANNADA);
    }

}
