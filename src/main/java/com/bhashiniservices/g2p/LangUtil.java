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

import java.lang.Character.UnicodeBlock;
import java.text.BreakIterator;

public class LangUtil {
    static final int LANGUAGE_UNKNOWN = 0;
    static final int LANGUAGE_KANNADA = 1;
    static final int LANGUAGE_TAMIL = 2;
    static int PREVIOUS_LANGUAGE = LANGUAGE_UNKNOWN;

    public static int recognizeLanguage(String text) {
        int kanCount = 0;
        int tamCount = 0;
        int digitCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            UnicodeBlock ub = UnicodeBlock.of(c);
            if (ub == UnicodeBlock.KANNADA) {
                kanCount++;
            } else if (ub == UnicodeBlock.TAMIL) {
                tamCount++;
            } else if (Character.isDigit(c)) {
                digitCount++;
            }
        }
        if (kanCount == 0 && tamCount == 0) {
            if (digitCount > 0) {
                return PREVIOUS_LANGUAGE;
            }
            return LANGUAGE_UNKNOWN;
        }
        if (tamCount > kanCount) {
            PREVIOUS_LANGUAGE = LANGUAGE_TAMIL;
            return LANGUAGE_TAMIL;
        } else {
            PREVIOUS_LANGUAGE = LANGUAGE_KANNADA;
            return LANGUAGE_KANNADA;
        }
    }

    public static String expandNumbers(String inputText) {
        int language = recognizeLanguage(inputText);
        G2P_Base g2p = null;
        if (language == LANGUAGE_KANNADA) {
            g2p = G2P_Kan.getInstance();
        } else if (language == LANGUAGE_TAMIL) {
            g2p = G2P_Tam.getInstance();
        }
        if (g2p != null) {
            return g2p.expandNumbers(inputText);
        }
        return inputText;
    }

    public static String expandPunctuation(String inputText) {
        int language = recognizeLanguage(inputText);
        G2P_Base g2p = null;
        if (language == LANGUAGE_KANNADA) {
            g2p = G2P_Kan.getInstance();
        } else if (language == LANGUAGE_TAMIL) {
            g2p = G2P_Tam.getInstance();
        }
        if (g2p != null) {
            return g2p.expandPunctuation(inputText);
        }
        return inputText;
    }

    public static Phoneme[] performG2P(String inputText) {
        int language = recognizeLanguage(inputText);
        G2P_Base g2p = null;
        if (language == LANGUAGE_KANNADA) {
            g2p = G2P_Kan.getInstance();
        } else if (language == LANGUAGE_TAMIL) {
            g2p = G2P_Tam.getInstance();
        }
        if (g2p != null) {
            return g2p.performG2P(inputText);
        }
        return null;
    }

    public static boolean isVowel(int language, int codePoint) {
        G2P_Base g2p = null;
        if (language == LANGUAGE_KANNADA) {
            g2p = G2P_Kan.getInstance();
        } else if (language == LANGUAGE_TAMIL) {
            g2p = G2P_Tam.getInstance();
        }
        if (g2p != null) {
            return g2p.isVowel(codePoint);
        }
        return false;
    }

    public void getG2POutput(String inputText) {
        StringBuffer buffer = new StringBuffer();
        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance();
        sentenceIterator.setText(inputText);
        int start = sentenceIterator.first();
        for (int end = sentenceIterator.next(); end != BreakIterator.DONE; start = end, end = sentenceIterator.next()) {
            String sentenceText = inputText.substring(start, end).trim();
            sentenceText = expandNumbers(sentenceText);
            BreakIterator wordIterator = BreakIterator.getWordInstance();
            wordIterator.setText(sentenceText);
            int start2 = wordIterator.first();
            for (int end2 = wordIterator.next(); end2 != BreakIterator.DONE; start2 = end2, end2 = wordIterator
                    .next()) {
                String wordText = sentenceText.substring(start2, end2).trim();
                if (wordText.length() > 0) {
                    boolean isPunctuation = true;
                    for (int i = 0; i < wordText.length(); i++) {
                        int codePoint = wordText.codePointAt(i);
                        if (Character.isAlphabetic(codePoint)) {
                            isPunctuation = false;
                            break;
                        }
                    }
                    if (!isPunctuation) {
                        Phoneme[] phonemes = performG2P(wordText);
                        for (int p = 0; p < phonemes.length; p++) {
                            buffer.append(phonemes[p].phoneme);
                        }
                    } else {
                        if (buffer.length() > 0) {
                            buffer.deleteCharAt(buffer.length() - 1);
                        }
                        buffer.append(wordText + " ");
                        continue;
                    }
                    if (wordText.equals(".")) {
                    } else {
                    }
                    buffer.append(" ");
                }
            }
            buffer.append("\n");
        }
    }

}
