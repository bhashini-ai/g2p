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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class G2P_Tam extends G2P_Base {

    private static G2P_Tam uniqueInstance = null;

    private G2P_Tam() {
        // Singleton class => private constructor
        initialize_g2pMappings("TamilGraphemeToPhonemeMappings.xml");
        loadNumberExpansionProperties(NUMBER_EXPANSION_CONF_FILE);
        initializeVallinamPhonemesMap();
        initializeMellinamPhonemesSet();
        initializeItaiyinamPhonemesSet();
    }

    public static synchronized G2P_Tam getInstance() {
        // http://www.ibm.com/developerworks/java/library/j-dcl/index.html
        if (uniqueInstance == null) {
            uniqueInstance = new G2P_Tam();
        }
        return uniqueInstance;
    }

    protected static String NUMBER_EXPANSION_CONF_FILE = "Tamil_NumberExpansion.xml";

    // Reference: http://unicode.org/charts/PDF/U0B80.pdf
    protected static G2P_Pair TAM_SIGN_ANUSVARA = new G2P_Pair(0X0B82, "ANUSVARA");
    protected static G2P_Pair TAM_SIGN_VISARGA = new G2P_Pair(0X0B83);

    protected static G2P_Pair TAM_LETTER_A = new G2P_Pair(0X0B85, "A");
    protected static G2P_Pair TAM_LETTER_AA = new G2P_Pair(0X0B86, "AA");
    protected static G2P_Pair TAM_LETTER_I = new G2P_Pair(0X0B87, "I");
    protected static G2P_Pair TAM_LETTER_II = new G2P_Pair(0X0B88, "II");
    protected static G2P_Pair TAM_LETTER_U = new G2P_Pair(0X0B89, "U");
    protected static G2P_Pair TAM_LETTER_UU = new G2P_Pair(0X0B8A, "UU");

    protected static G2P_Pair TAM_LETTER_E = new G2P_Pair(0X0B8E, "E");
    protected static G2P_Pair TAM_LETTER_EE = new G2P_Pair(0X0B8F, "EE");
    protected static G2P_Pair TAM_LETTER_AI = new G2P_Pair(0X0B90, "AI");

    protected static G2P_Pair TAM_LETTER_O = new G2P_Pair(0X0B92, "O");
    protected static G2P_Pair TAM_LETTER_OO = new G2P_Pair(0X0B93, "OO");
    protected static G2P_Pair TAM_LETTER_AU = new G2P_Pair(0X0B94, "AU");

    protected static G2P_Pair TAM_LETTER_K = new G2P_Pair(0X0B95, "KA");
    protected static G2P_Pair TAM_LETTER_NG = new G2P_Pair(0X0B99, "NGA");

    protected static G2P_Pair TAM_LETTER_C = new G2P_Pair(0X0B9A, "CA");
    protected static G2P_Pair TAM_LETTER_J = new G2P_Pair(0X0B9C, "JA");
    protected static G2P_Pair TAM_LETTER_NY = new G2P_Pair(0X0B9E, "NYA");

    protected static G2P_Pair TAM_LETTER_TT = new G2P_Pair(0X0B9F, "TTA");
    protected static G2P_Pair TAM_LETTER_NN = new G2P_Pair(0X0BA3, "NNA");

    protected static G2P_Pair TAM_LETTER_T = new G2P_Pair(0X0BA4, "TA");
    protected static G2P_Pair TAM_LETTER_N = new G2P_Pair(0X0BA8, "NA");
    protected static G2P_Pair TAM_LETTER_NNN = new G2P_Pair(0X0BA9, "NNNA");

    protected static G2P_Pair TAM_LETTER_P = new G2P_Pair(0X0BAA, "PA");
    protected static G2P_Pair TAM_LETTER_M = new G2P_Pair(0X0BAE, "MA");

    protected static G2P_Pair TAM_LETTER_Y = new G2P_Pair(0X0BAF, "YA");
    protected static G2P_Pair TAM_LETTER_R = new G2P_Pair(0X0BB0, "RA");
    protected static G2P_Pair TAM_LETTER_RR = new G2P_Pair(0X0BB1, "RRA");
    protected static G2P_Pair TAM_LETTER_L = new G2P_Pair(0X0BB2, "LA");
    protected static G2P_Pair TAM_LETTER_LL = new G2P_Pair(0X0BB3, "LLA");
    protected static G2P_Pair TAM_LETTER_LLL = new G2P_Pair(0X0BB4, "LLLA");
    protected static G2P_Pair TAM_LETTER_V = new G2P_Pair(0X0BB5, "VA");
    protected static G2P_Pair TAM_LETTER_SH = new G2P_Pair(0X0BB6, "SHA");
    protected static G2P_Pair TAM_LETTER_SS = new G2P_Pair(0X0BB7, "SSA");
    protected static G2P_Pair TAM_LETTER_S = new G2P_Pair(0X0BB8, "SA");
    protected static G2P_Pair TAM_LETTER_H = new G2P_Pair(0X0BB9, "HA");

    protected static G2P_Pair TAM_VOWEL_SIGN_AA = new G2P_Pair(0X0BBE, "AA");
    protected static G2P_Pair TAM_VOWEL_SIGN_I = new G2P_Pair(0X0BBF, "I");
    protected static G2P_Pair TAM_VOWEL_SIGN_II = new G2P_Pair(0X0BC0, "II");
    protected static G2P_Pair TAM_VOWEL_SIGN_U = new G2P_Pair(0X0BC1, "U");
    protected static G2P_Pair TAM_VOWEL_SIGN_UU = new G2P_Pair(0X0BC2, "UU");

    protected static G2P_Pair TAM_VOWEL_SIGN_E = new G2P_Pair(0X0BC6, "E");
    protected static G2P_Pair TAM_VOWEL_SIGN_EE = new G2P_Pair(0X0BC7, "EE");
    protected static G2P_Pair TAM_VOWEL_SIGN_AI = new G2P_Pair(0X0BC8, "AI");

    protected static G2P_Pair TAM_VOWEL_SIGN_O = new G2P_Pair(0X0BCA, "O");
    protected static G2P_Pair TAM_VOWEL_SIGN_OO = new G2P_Pair(0X0BCB, "OO");
    protected static G2P_Pair TAM_VOWEL_SIGN_AU = new G2P_Pair(0X0BCC, "AU");

    protected static G2P_Pair TAM_SIGN_HALANT = new G2P_Pair(0X0BCD);

    protected static G2P_Pair TAM_OM = new G2P_Pair(0X0BD0, "");

    protected static G2P_Pair TAM_AU_LENGTH_MARK = new G2P_Pair(0X0BD7, "");

    protected static G2P_Pair TAM_DIGIT_ZERO = new G2P_Pair(0X0BE6, "0");
    protected static G2P_Pair TAM_DIGIT_ONE = new G2P_Pair(0X0BE7, "1");
    protected static G2P_Pair TAM_DIGIT_TWO = new G2P_Pair(0X0BE8, "2");
    protected static G2P_Pair TAM_DIGIT_THREE = new G2P_Pair(0X0BE9, "3");
    protected static G2P_Pair TAM_DIGIT_FOUR = new G2P_Pair(0X0BEA, "4");
    protected static G2P_Pair TAM_DIGIT_FIVE = new G2P_Pair(0X0BEB, "5");
    protected static G2P_Pair TAM_DIGIT_SIX = new G2P_Pair(0X0BEC, "6");
    protected static G2P_Pair TAM_DIGIT_SEVEN = new G2P_Pair(0X0BED, "7");
    protected static G2P_Pair TAM_DIGIT_EIGHT = new G2P_Pair(0X0BEE, "8");
    protected static G2P_Pair TAM_DIGIT_NINE = new G2P_Pair(0X0BEF, "9");

    protected static G2P_Pair TAM_NUMBER_TEN = new G2P_Pair(0X0BF0, "");
    protected static G2P_Pair TAM_NUMBER_ONE_HUNDRED = new G2P_Pair(0X0BF1, "");
    protected static G2P_Pair TAM_NUMBER_ONE_THOUSAND = new G2P_Pair(0X0BF2, "");

    protected static G2P_Pair TAM_DAY_SIGN = new G2P_Pair(0X0BF3, "");
    protected static G2P_Pair TAM_MONTH_SIGN = new G2P_Pair(0X0BF4, "");
    protected static G2P_Pair TAM_YEAR_SIGN = new G2P_Pair(0X0BF5, "");
    protected static G2P_Pair TAM_DEBIT_SIGN = new G2P_Pair(0X0BF6, "");
    protected static G2P_Pair TAM_CREDIT_SIGN = new G2P_Pair(0X0BF7, "");
    protected static G2P_Pair TAM_AS_ABOVE_SIGN = new G2P_Pair(0X0BF8, "");
    protected static G2P_Pair TAM_RUPEEE_SIGN = new G2P_Pair(0X0BF9, "");
    protected static G2P_Pair TAM_NUMBER_SIGN = new G2P_Pair(0X0BFA, "");

    HashMap<String, String> vallinamPhonemesMap = new HashMap<String, String>();
    HashSet<String> mellinamPhonemesSet = new HashSet<String>();
    HashSet<String> itaiyinamPhonemesSet = new HashSet<String>();

    protected void initializeVallinamPhonemesMap() {
        vallinamPhonemesMap.put(TAM_LETTER_K.phoneme, "GA");
        vallinamPhonemesMap.put(TAM_LETTER_C.phoneme, "JA");
        vallinamPhonemesMap.put(TAM_LETTER_TT.phoneme, "DDA");
        vallinamPhonemesMap.put(TAM_LETTER_T.phoneme, "DA");
        vallinamPhonemesMap.put(TAM_LETTER_P.phoneme, "BA");
    }

    protected void initializeMellinamPhonemesSet() {
        mellinamPhonemesSet.add(TAM_LETTER_NG.phoneme);
        mellinamPhonemesSet.add(TAM_LETTER_NY.phoneme);
        mellinamPhonemesSet.add(TAM_LETTER_NN.phoneme);
        mellinamPhonemesSet.add(TAM_LETTER_N.phoneme);
        mellinamPhonemesSet.add(TAM_LETTER_NNN.phoneme);
        mellinamPhonemesSet.add(TAM_LETTER_M.phoneme);
    }

    protected void initializeItaiyinamPhonemesSet() {
        itaiyinamPhonemesSet.add(TAM_LETTER_Y.phoneme);
        itaiyinamPhonemesSet.add(TAM_LETTER_R.phoneme);
        // itaiyinamPhonemes.add(TAM_LETTER_RR.phoneme);
        itaiyinamPhonemesSet.add(TAM_LETTER_L.phoneme);
        itaiyinamPhonemesSet.add(TAM_LETTER_LL.phoneme);
        itaiyinamPhonemesSet.add(TAM_LETTER_LLL.phoneme);
        itaiyinamPhonemesSet.add(TAM_LETTER_V.phoneme);
    }

    @Override
    public int getLanguageDigitZero() {
        return TAM_DIGIT_ZERO.grapheme;
    }

    @Override
    public int getLanguageDigitNine() {
        return TAM_DIGIT_NINE.grapheme;
    }

    @Override
    char getEnglishEquivalentOfLanguageDigit(char scriptDigit) {
        return g2pMappings.get(new Integer(scriptDigit)).charAt(0);
    }

    static void replaceUnicodeAlternates(String str) {
        // TAM_LETTER_O + TAM_AU_LENGTH_MARK = TAM_LETTER_AU
    }

    @Override
    public Phoneme[] performG2P(String input) {
        char[] inputChars = input.toCharArray();
        ArrayList<Phoneme> outputPhonemes = new ArrayList<Phoneme>();
        for (int i = 0; i < inputChars.length; i++) {
            int currentCharacter = inputChars[i];
            if (currentCharacter == TAM_LETTER_K.grapheme || currentCharacter == TAM_LETTER_C.grapheme
                    || currentCharacter == TAM_LETTER_TT.grapheme || currentCharacter == TAM_LETTER_T.grapheme
                    || currentCharacter == TAM_LETTER_P.grapheme) {
                String currentPhoneme = g2pMappings.get(new Integer(currentCharacter));
                if (outputPhonemes.size() == 0) {
                    if (currentCharacter == TAM_LETTER_C.grapheme) {
                        currentPhoneme = "s";
                    }
                } else {
                    Phoneme prevPhoneme = outputPhonemes.get(outputPhonemes.size() - 1);
                    if (prevPhoneme.phoneme.equals(TAM_LETTER_R.phoneme) && currentPhoneme.equals(TAM_LETTER_TT.phoneme)
                            && i <= inputChars.length - 2 && inputChars[i + 1] == TAM_SIGN_HALANT.grapheme) {
                        // retain "T" as is (don't replace it with "D"). Example: "k O r T"
                    } else if (mellinamPhonemesSet.contains(prevPhoneme.phoneme)
                            || itaiyinamPhonemesSet.contains(prevPhoneme.phoneme)) {
                        currentPhoneme = vallinamPhonemesMap.get(currentPhoneme);
                    } else if (prevPhoneme.type == Phoneme.VOWEL) {
                        if (i < inputChars.length - 1 && inputChars[i + 1] == TAM_SIGN_HALANT.grapheme) {
                            // Retain the unvoiced consonant as is (don't replace with voiced equivalent)
                        } else {
                            if (currentCharacter == TAM_LETTER_C.grapheme) {
                                currentPhoneme = "s";
                            } else {
                                currentPhoneme = vallinamPhonemesMap.get(currentPhoneme);
                            }
                        }
                    }
                }
                outputPhonemes.add(new Phoneme(currentPhoneme, Phoneme.CONSONANT));
                outputPhonemes.add(new Phoneme(TAM_LETTER_A.phoneme, Phoneme.VOWEL));
            } else if (currentCharacter == TAM_SIGN_VISARGA.grapheme) {
                // Handle Visarga
                if (i < inputChars.length - 1 && inputChars[i + 1] == TAM_LETTER_P.grapheme) {
                    outputPhonemes.add(new Phoneme("F", Phoneme.CONSONANT));
                    i++;
                } else {
                    // Ex. "nama:" -> "namaha", "guru:" -> "guruhu"
                    // The logic here is to put an "h" and follow it by a vowel.
                    // The vowel to be put is decided by the vowel that is found prior to the visarga
                    Phoneme prevPhoneme = null;
                    if (outputPhonemes.size() > 0) {
                        prevPhoneme = outputPhonemes.get(outputPhonemes.size() - 1);
                    }
                    outputPhonemes.add(new Phoneme(TAM_LETTER_H.phoneme, Phoneme.CONSONANT));
                    if (prevPhoneme != null && prevPhoneme.type == Phoneme.VOWEL) {
                        outputPhonemes.add(new Phoneme(prevPhoneme.phoneme, Phoneme.VOWEL));
                    } else {
                        outputPhonemes.add(new Phoneme(TAM_LETTER_A.phoneme, Phoneme.VOWEL));
                    }
                }
            } else if (currentCharacter == TAM_SIGN_HALANT.grapheme) {
                // Handle Halant (Pulli)
                if (i >= 1 && i <= inputChars.length - 2 && inputChars[i - 1] == inputChars[i + 1]) {
                    // Same consonants on either side of halant
                    // ex: k-k put kl (kk); m-m put ml (mm);
                    // insert "l" in between the consonant and "a"
                    if (outputPhonemes.size() >= 2) {
                        Phoneme prevPhoneme = outputPhonemes.get(outputPhonemes.size() - 2);
                        if (inputChars[i - 1] == TAM_LETTER_RR.grapheme) {
                            prevPhoneme.phoneme = "TR";
                        } else if (inputChars[i - 1] == TAM_LETTER_C.grapheme) {
                            prevPhoneme.phoneme = "cl";
                        } else {
                            prevPhoneme.phoneme = prevPhoneme.phoneme + GEMINATE_SUFFIX;
                        }
                    } // else ignore the current halant as a spurious one
                    i++; // next grapheme is handled
                } else {
                    // Delete "a" from the previous consonant
                    if (outputPhonemes.size() > 0) {
                        outputPhonemes.remove(outputPhonemes.size() - 1);
                    } // else ignore the current halant as a spurious one
                }
            } else if (currentCharacter >= TAM_VOWEL_SIGN_AA.grapheme
                    && currentCharacter <= TAM_VOWEL_SIGN_AU.grapheme) {
                // First apply the following three Unicode rules:
                // 1) TAM_VOWEL_SIGN_E + TAM_VOWEL_SIGN_AA = TAM_VOWEL_SIGN_O
                // 2) TAM_VOWEL_SIGN_E + TAM_AU_LENGTH_MARK = TAM_VOWEL_SIGN_AU
                if (currentCharacter == TAM_VOWEL_SIGN_E.grapheme && i <= inputChars.length - 2) {
                    if (inputChars[i + 1] == TAM_VOWEL_SIGN_AA.grapheme) {
                        currentCharacter = TAM_VOWEL_SIGN_O.grapheme;
                        i++;
                    } else if (inputChars[i + 1] == TAM_AU_LENGTH_MARK.grapheme) {
                        currentCharacter = TAM_VOWEL_SIGN_AU.grapheme;
                        i++;
                    }
                }
                // 3) TAM_VOWEL_SIGN_EE + TAM_VOWEL_SIGN_AA = TAM_VOWEL_SIGN_OO
                else if (currentCharacter == TAM_VOWEL_SIGN_EE.grapheme && i <= inputChars.length - 2
                        && inputChars[i + 1] == TAM_VOWEL_SIGN_AA.grapheme) {
                    currentCharacter = TAM_VOWEL_SIGN_OO.grapheme;
                    i++;
                }
                // Now handle the dependent vowel:
                // Remove the previous 'a' and put the appropriate vowel phoneme.
                String newVowel = g2pMappings.get(new Integer(currentCharacter));
                if (outputPhonemes.size() > 0) {
                    Phoneme prevPhoneme = outputPhonemes.get(outputPhonemes.size() - 1);
                    if (prevPhoneme.type == Phoneme.VOWEL) {
                        prevPhoneme.phoneme = newVowel;
                    }
                } // else ignore the current dependent vowel as a spurious one
            } else {
                // Handle Unicode rule: TAM_LETTER_O + TAM_AU_LENGTH_MARK = TAM_LETTER_AU
                if (currentCharacter == TAM_LETTER_O.grapheme && i <= inputChars.length - 2
                        && inputChars[i + 1] == TAM_AU_LENGTH_MARK.grapheme) {
                    currentCharacter = TAM_LETTER_AU.grapheme;
                    i++;
                }
                // Handle characters for which there is a direct mapping from grapheme to phoneme
                String phoneme = g2pMappings.get(new Integer(currentCharacter));
                if (phoneme != null && phoneme.length() > 0) {
                    if (currentCharacter >= TAM_LETTER_A.grapheme && currentCharacter <= TAM_LETTER_AU.grapheme) {
                        outputPhonemes.add(new Phoneme(phoneme, Phoneme.VOWEL));
                    } else if (currentCharacter >= TAM_LETTER_K.grapheme && currentCharacter <= TAM_LETTER_H.grapheme) {
                        outputPhonemes.add(new Phoneme(phoneme, Phoneme.CONSONANT));
                        outputPhonemes.add(new Phoneme(TAM_LETTER_A.phoneme, Phoneme.VOWEL));
                    }
                } else {
                    outputPhonemes.add(new Phoneme(currentCharacter + "", Phoneme.PUNCTUATION));
                }
            }
        }
        Phoneme[] output = new Phoneme[outputPhonemes.size()];
        for (int i = 0; i < outputPhonemes.size(); i++) {
            output[i] = outputPhonemes.get(i);
        }
        return output;
    }

    @Override
    public boolean isVowel(int codePoint) {
        if (codePoint >= 0x0B85 && codePoint <= 0x0B94) {
            return true;
        }
        return false;
    }

}
