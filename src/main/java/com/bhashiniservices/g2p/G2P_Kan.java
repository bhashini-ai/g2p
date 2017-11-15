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

public class G2P_Kan extends G2P_Base {

    private static G2P_Kan uniqueInstance = null;

    private G2P_Kan() {
        // Singleton class => private constructor
        initialize_g2pMappings("KannadaGraphemeToPhonemeMappings.xml");
        loadNumberExpansionProperties(NUMBER_EXPANSION_CONF_FILE);
        loadPunctuationExpansionProperties(PUNCTUATION_EXPANSION_CONF_FILE);
    }

    public static synchronized G2P_Kan getInstance() {
        // http://www.ibm.com/developerworks/java/library/j-dcl/index.html
        if (uniqueInstance == null) {
            uniqueInstance = new G2P_Kan();
        }
        return uniqueInstance;
    }

    protected static String NUMBER_EXPANSION_CONF_FILE = "Kannada_NumberExpansion.xml";
    protected static String PUNCTUATION_EXPANSION_CONF_FILE = "Kannada_PunctuationExpansion.xml";

    // Reference: http://unicode.org/charts/PDF/U0C80.pdf
    protected static G2P_Pair KAN_SIGN_ANUSVARA = new G2P_Pair(0X0C82);
    protected static G2P_Pair KAN_SIGN_VISARGA = new G2P_Pair(0X0C83);

    protected static G2P_Pair KAN_LETTER_A = new G2P_Pair(0X0C85, "A");
    protected static G2P_Pair KAN_LETTER_AA = new G2P_Pair(0X0C86, "AA");
    protected static G2P_Pair KAN_LETTER_I = new G2P_Pair(0X0C87, "I");
    protected static G2P_Pair KAN_LETTER_II = new G2P_Pair(0X0C88, "II");
    protected static G2P_Pair KAN_LETTER_U = new G2P_Pair(0X0C89, "U");
    protected static G2P_Pair KAN_LETTER_UU = new G2P_Pair(0X0C8A, "UU");
    protected static G2P_Pair KAN_LETTER_VOCALIC_R = new G2P_Pair(0X0C8B, "VOCALIC_R"); // TODO
    protected static G2P_Pair KAN_LETTER_VOCALIC_L = new G2P_Pair(0X0C8C, "VOCALIC_L"); // TODO

    protected static G2P_Pair KAN_LETTER_E = new G2P_Pair(0X0C8E, "E");
    protected static G2P_Pair KAN_LETTER_EE = new G2P_Pair(0X0C8F, "EE");
    protected static G2P_Pair KAN_LETTER_AI = new G2P_Pair(0X0C90, "AI");

    protected static G2P_Pair KAN_LETTER_O = new G2P_Pair(0X0C92, "O");
    protected static G2P_Pair KAN_LETTER_OO = new G2P_Pair(0X0C93, "OO");
    protected static G2P_Pair KAN_LETTER_AU = new G2P_Pair(0X0C94, "AU");

    protected static G2P_Pair KAN_LETTER_K = new G2P_Pair(0X0C95, "KA");
    protected static G2P_Pair KAN_LETTER_KH = new G2P_Pair(0X0C96, "KHA");
    protected static G2P_Pair KAN_LETTER_G = new G2P_Pair(0X0C97, "GA");
    protected static G2P_Pair KAN_LETTER_GH = new G2P_Pair(0X0C98, "GHA");
    protected static G2P_Pair KAN_LETTER_NG = new G2P_Pair(0X0C99, "NGA");

    protected static G2P_Pair KAN_LETTER_C = new G2P_Pair(0X0C9A, "CA");
    protected static G2P_Pair KAN_LETTER_CH = new G2P_Pair(0X0C9B, "CHA");
    protected static G2P_Pair KAN_LETTER_J = new G2P_Pair(0X0C9C, "JA");
    protected static G2P_Pair KAN_LETTER_JH = new G2P_Pair(0X0C9D, "JHA");
    protected static G2P_Pair KAN_LETTER_NY = new G2P_Pair(0X0C9E, "NYA");

    protected static G2P_Pair KAN_LETTER_TT = new G2P_Pair(0X0C9F, "TTA");
    protected static G2P_Pair KAN_LETTER_TTH = new G2P_Pair(0X0CA0, "TTHA");
    protected static G2P_Pair KAN_LETTER_DD = new G2P_Pair(0X0CA1, "DDA");
    protected static G2P_Pair KAN_LETTER_DDH = new G2P_Pair(0X0CA2, "DDHA");
    protected static G2P_Pair KAN_LETTER_NN = new G2P_Pair(0X0CA3, "NNA");

    protected static G2P_Pair KAN_LETTER_T = new G2P_Pair(0X0CA4, "TA");
    protected static G2P_Pair KAN_LETTER_TH = new G2P_Pair(0X0CA5, "THA");
    protected static G2P_Pair KAN_LETTER_D = new G2P_Pair(0X0CA6, "DA");
    protected static G2P_Pair KAN_LETTER_DH = new G2P_Pair(0X0CA7, "DHA");
    protected static G2P_Pair KAN_LETTER_N = new G2P_Pair(0X0CA8, "NA");

    protected static G2P_Pair KAN_LETTER_P = new G2P_Pair(0X0CAA, "PA");
    protected static G2P_Pair KAN_LETTER_PH = new G2P_Pair(0X0CAB, "PHA");
    protected static G2P_Pair KAN_LETTER_B = new G2P_Pair(0X0CAC, "BA");
    protected static G2P_Pair KAN_LETTER_BH = new G2P_Pair(0X0CAD, "BHA");
    protected static G2P_Pair KAN_LETTER_M = new G2P_Pair(0X0CAE, "MA");

    protected static G2P_Pair KAN_LETTER_Y = new G2P_Pair(0X0CAF, "YA");
    protected static G2P_Pair KAN_LETTER_R = new G2P_Pair(0X0CB0, "RA");
    protected static G2P_Pair KAN_LETTER_RR = new G2P_Pair(0X0CB1, "RRA"); // TODO
    protected static G2P_Pair KAN_LETTER_L = new G2P_Pair(0X0CB2, "LA");
    protected static G2P_Pair KAN_LETTER_LL = new G2P_Pair(0X0CB3, "LLA");

    protected static G2P_Pair KAN_LETTER_V = new G2P_Pair(0X0CB5, "VA");
    protected static G2P_Pair KAN_LETTER_SH = new G2P_Pair(0X0CB6, "SHA");
    protected static G2P_Pair KAN_LETTER_SS = new G2P_Pair(0X0CB7, "SSA");
    protected static G2P_Pair KAN_LETTER_S = new G2P_Pair(0X0CB8, "SA");
    protected static G2P_Pair KAN_LETTER_H = new G2P_Pair(0X0CB9, "HA");

    protected static G2P_Pair KAN_SIGN_NUKTA = new G2P_Pair(0X0CBC);
    protected static G2P_Pair KAN_SIGN_AVAGRAHA = new G2P_Pair(0X0CBD);

    protected static G2P_Pair KAN_VOWEL_SIGN_AA = new G2P_Pair(0X0CBE, "AA");
    protected static G2P_Pair KAN_VOWEL_SIGN_I = new G2P_Pair(0X0CBF, "I");
    protected static G2P_Pair KAN_VOWEL_SIGN_II = new G2P_Pair(0X0CC0, "II");
    protected static G2P_Pair KAN_VOWEL_SIGN_U = new G2P_Pair(0X0CC1, "U");
    protected static G2P_Pair KAN_VOWEL_SIGN_UU = new G2P_Pair(0X0CC2, "UU");
    protected static G2P_Pair KAN_VOWEL_SIGN_VOCALIC_R = new G2P_Pair(0X0CC3, "VOCALIC_R"); // TODO
    protected static G2P_Pair KAN_VOWEL_SIGN_VOCALIC_RR = new G2P_Pair(0X0CC4, "VOCALIC_RR"); // TODO

    protected static G2P_Pair KAN_VOWEL_SIGN_E = new G2P_Pair(0X0CC6, "E");
    protected static G2P_Pair KAN_VOWEL_SIGN_EE = new G2P_Pair(0X0CC7, "EE");
    protected static G2P_Pair KAN_VOWEL_SIGN_AI = new G2P_Pair(0X0CC8, "AI");

    protected static G2P_Pair KAN_VOWEL_SIGN_O = new G2P_Pair(0X0CCA, "O");
    protected static G2P_Pair KAN_VOWEL_SIGN_OO = new G2P_Pair(0X0CCB, "OO");
    protected static G2P_Pair KAN_VOWEL_SIGN_AU = new G2P_Pair(0X0CCC, "AU");

    protected static G2P_Pair KAN_SIGN_HALANT = new G2P_Pair(0X0CCD);

    protected static G2P_Pair KAN_LENGTH_MARK = new G2P_Pair(0X0CD5);
    protected static G2P_Pair KAN_AI_LENGTH_MARK = new G2P_Pair(0X0CD6);

    protected static G2P_Pair KAN_LETTER_LLLA = new G2P_Pair(0X0CDE, "LLLA"); // TODO

    protected static G2P_Pair KAN_LETTER_VOCALIC_RR = new G2P_Pair(0X0CE0, "VOCALIC_RR"); // TODO
    protected static G2P_Pair KAN_LETTER_VOCALIC_LL = new G2P_Pair(0X0CE1, "VOCALIC_LL"); // TODO

    protected static G2P_Pair KAN_VOWEL_SIGN_VOCALIC_L = new G2P_Pair(0X0CE2, "VOCALIC_L"); // TODO
    protected static G2P_Pair KAN_VOWEL_SIGN_VOCALIC_LL = new G2P_Pair(0X0CE3, "VOCALIC)+_LL"); // TODO

    protected static G2P_Pair KAN_DIGIT_ZERO = new G2P_Pair(0X0CE6, "0");
    protected static G2P_Pair KAN_DIGIT_ONE = new G2P_Pair(0X0CE7, "1");
    protected static G2P_Pair KAN_DIGIT_TWO = new G2P_Pair(0X0CE8, "2");
    protected static G2P_Pair KAN_DIGIT_THREE = new G2P_Pair(0X0CE9, "3");
    protected static G2P_Pair KAN_DIGIT_FOUR = new G2P_Pair(0X0CEA, "4");
    protected static G2P_Pair KAN_DIGIT_FIVE = new G2P_Pair(0X0CEB, "5");
    protected static G2P_Pair KAN_DIGIT_SIX = new G2P_Pair(0X0CEC, "6");
    protected static G2P_Pair KAN_DIGIT_SEVEN = new G2P_Pair(0X0CED, "7");
    protected static G2P_Pair KAN_DIGIT_EIGHT = new G2P_Pair(0X0CEE, "8");
    protected static G2P_Pair KAN_DIGIT_NINE = new G2P_Pair(0X0CEF, "9");

    @Override
    public int getLanguageDigitZero() {
        return KAN_DIGIT_ZERO.grapheme;
    }

    @Override
    public int getLanguageDigitNine() {
        return KAN_DIGIT_NINE.grapheme;
    }

    @Override
    char getEnglishEquivalentOfLanguageDigit(char scriptDigit) {
        return g2pMappings.get(new Integer(scriptDigit)).charAt(0);
    }

    @Override
    public Phoneme[] performG2P(String input) {
        if (input.length() == 1) {
            int currentCharacter = input.charAt(0);
            if (currentCharacter == KAN_SIGN_ANUSVARA.grapheme) {
                input = "ಅಂ";
            } else if (currentCharacter == KAN_SIGN_VISARGA.grapheme) {
                input = "ಅಃ";
            } else if (currentCharacter == KAN_SIGN_HALANT.grapheme) {
                input = "ಒತ್ತು";
            }
        }
        char[] inputChars = input.toCharArray();
        ArrayList<Phoneme> outputPhonemes = new ArrayList<Phoneme>();
        for (int i = 0; i < inputChars.length; i++) {
            int currentCharacter = inputChars[i];
            if (currentCharacter == KAN_SIGN_ANUSVARA.grapheme) {
                // Handle Anusvara: Check for the next consonant. If vargeeya vyanjana is found then put the
                // nasal of that group. Else if avargeeya vyanjana is found then put 'm'
                if (i == inputChars.length - 1) {
                    outputPhonemes.add(new Phoneme(KAN_LETTER_M.phoneme, Phoneme.CONSONANT));
                } else if (inputChars[i + 1] >= KAN_LETTER_K.grapheme && inputChars[i + 1] <= KAN_LETTER_NG.grapheme) {
                    outputPhonemes.add(new Phoneme(KAN_LETTER_NG.phoneme, Phoneme.CONSONANT));
                } else if (inputChars[i + 1] >= KAN_LETTER_C.grapheme && inputChars[i + 1] <= KAN_LETTER_NY.grapheme) {
                    outputPhonemes.add(new Phoneme(KAN_LETTER_NY.phoneme, Phoneme.CONSONANT));
                } else if (inputChars[i + 1] >= KAN_LETTER_TT.grapheme && inputChars[i + 1] <= KAN_LETTER_NN.grapheme) {
                    outputPhonemes.add(new Phoneme(KAN_LETTER_NN.phoneme, Phoneme.CONSONANT));
                } else if (inputChars[i + 1] >= KAN_LETTER_T.grapheme && inputChars[i + 1] <= KAN_LETTER_N.grapheme) {
                    outputPhonemes.add(new Phoneme(KAN_LETTER_N.phoneme, Phoneme.CONSONANT));
                } else {
                    outputPhonemes.add(new Phoneme(KAN_LETTER_M.phoneme, Phoneme.CONSONANT));
                }
            } else if (currentCharacter == KAN_SIGN_VISARGA.grapheme) {
                // Handle Visarga: Ex. "nama:" -> "namaha", "guru:" -> "guruhu"
                // The logic here is to put an "h" and follow it by a vowel.
                // The vowel to be put is decided by the vowel that is found prior to the visarga
                Phoneme prevPhoneme = null;
                if (outputPhonemes.size() > 0) {
                    prevPhoneme = outputPhonemes.get(outputPhonemes.size() - 1);
                }
                outputPhonemes.add(new Phoneme(KAN_LETTER_H.phoneme, Phoneme.CONSONANT));
                if (prevPhoneme != null && prevPhoneme.type == Phoneme.VOWEL) {
                    outputPhonemes.add(new Phoneme(prevPhoneme.phoneme, Phoneme.VOWEL));
                } else {
                    outputPhonemes.add(new Phoneme(KAN_LETTER_A.phoneme, Phoneme.VOWEL));
                }
            } else if (currentCharacter == KAN_SIGN_HALANT.grapheme) {
                // Handle Halant
                if (i >= 1 && i <= inputChars.length - 2 && inputChars[i - 1] == inputChars[i + 1]) {
                    // Same consonants on either side of halant
                    // ex: k-k put kl (kk); m-m put ml (mm); kh-kh put khl (khkh)
                    // insert "l" in between the consonant and "a"
                    if (outputPhonemes.size() >= 2) {
                        Phoneme prevPhoneme = outputPhonemes.get(outputPhonemes.size() - 2);
                        prevPhoneme.phoneme = prevPhoneme.phoneme + GEMINATE_SUFFIX;
                    } // else ignore the current halant as a spurious one
                    i++; // next grapheme is handled
                } else {
                    // Delete "a" from the previous consonant
                    if (outputPhonemes.size() > 0) {
                        outputPhonemes.remove(outputPhonemes.size() - 1);
                    } // else ignore the current halant as a spurious one
                }
            } else if ((currentCharacter >= KAN_VOWEL_SIGN_AA.grapheme
                    && currentCharacter <= KAN_VOWEL_SIGN_AU.grapheme)
                    || (currentCharacter >= KAN_VOWEL_SIGN_VOCALIC_L.grapheme
                            && currentCharacter <= KAN_VOWEL_SIGN_VOCALIC_LL.grapheme)) {
                // First apply the following five Unicode rules:
                // 1) KAN_VOWEL_SIGN_I + KAN_LENGTH_MARK = KAN_VOWEL_SIGN_II;
                if (currentCharacter == KAN_VOWEL_SIGN_I.grapheme && i <= inputChars.length - 2
                        && inputChars[i + 1] == KAN_LENGTH_MARK.grapheme) {
                    currentCharacter = KAN_VOWEL_SIGN_II.grapheme;
                    i++;
                }
                // 2) KAN_VOWEL_SIGN_E + KAN_LENGTH_MARK = KAN_VOWEL_SIGN_EE
                // 3) KAN_VOWEL_SIGN_E + KAN_AI_LENGTH_MARK = KAN_VOWEL_SIGN_AI
                // 4) KAN_VOWEL_SIGN_E + KAN_VOWEL_SIGN_UU = KAN_VOWEL_SIGN_O
                else if (currentCharacter == KAN_VOWEL_SIGN_E.grapheme && i <= inputChars.length - 2) {
                    if (inputChars[i + 1] == KAN_LENGTH_MARK.grapheme) {
                        currentCharacter = KAN_VOWEL_SIGN_EE.grapheme;
                        i++;
                    } else if (inputChars[i + 1] == KAN_AI_LENGTH_MARK.grapheme) {
                        currentCharacter = KAN_VOWEL_SIGN_AI.grapheme;
                        i++;
                    } else if (inputChars[i + 1] == KAN_VOWEL_SIGN_UU.grapheme) {
                        currentCharacter = KAN_VOWEL_SIGN_O.grapheme;
                        i++;
                    }
                }
                // 5) KAN_VOWEL_SIGN_O + KAN_LENGTH_MARK = KAN_VOWEL_SIGN_OO
                else if (currentCharacter == KAN_VOWEL_SIGN_O.grapheme && i <= inputChars.length - 2
                        && inputChars[i + 1] == KAN_LENGTH_MARK.grapheme) {
                    currentCharacter = KAN_VOWEL_SIGN_OO.grapheme;
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
                } else if (inputChars.length == 1) {
                    outputPhonemes.add(new Phoneme(newVowel, Phoneme.VOWEL));
                } // else ignore the current dependent vowel as a spurious one
            } else {
                // Handle all other characters for which there is a direct mapping from grapheme to phoneme
                String phoneme = g2pMappings.get(new Integer(currentCharacter));
                if (phoneme != null && phoneme.length() > 0) {
                    if ((currentCharacter >= KAN_LETTER_A.grapheme && currentCharacter <= KAN_LETTER_AU.grapheme)
                            || (currentCharacter >= KAN_LETTER_VOCALIC_RR.grapheme
                                    && currentCharacter <= KAN_LETTER_VOCALIC_LL.grapheme)) {
                        outputPhonemes.add(new Phoneme(phoneme, Phoneme.VOWEL));
                    } else if ((currentCharacter >= KAN_LETTER_K.grapheme && currentCharacter <= KAN_LETTER_H.grapheme)
                            || currentCharacter == KAN_LETTER_LLLA.grapheme) {
                        outputPhonemes.add(new Phoneme(phoneme, Phoneme.CONSONANT));
                        outputPhonemes.add(new Phoneme(KAN_LETTER_A.phoneme, Phoneme.VOWEL));
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
        if (codePoint >= 0x0C85 && codePoint <= 0x0C94) {
            return true;
        }
        return false;
    }

}
