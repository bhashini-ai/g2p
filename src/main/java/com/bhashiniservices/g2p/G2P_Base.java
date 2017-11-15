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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

abstract public class G2P_Base {
    static class G2P_Pair {
        public int grapheme;
        public String phoneme;

        public G2P_Pair(int _grapheme, String _phoneme) {
            grapheme = _grapheme;
            phoneme = _phoneme;
        }

        public G2P_Pair(int _grapheme) {
            grapheme = _grapheme;
            phoneme = "";
        }

        public G2P_Pair(String _phoneme) {
            phoneme = _phoneme;
            grapheme = Character.MIN_VALUE;
        }
    }

    public static final int ENGLISH_DIGIT_ZERO = 0x30;
    public static final int ENGLISH_DIGIT_NINE = 0x39;
    public static final int SYMBOL_COMMA = 0x2C;
    public static final int SYMBOL_DOT = 0x2E;
    public static final String SPACE = " ";

    protected HashMap<Integer, String> g2pMappings = new HashMap<Integer, String>();
    protected Properties numberExpansionProperties = new Properties();
    protected Properties punctuationExpansionProperties = new Properties();

    // public static G2P_Pair SYMBOL_EXCLAMATION = new G2P_Pair(0X21, "H#");
    // public static G2P_Pair SYMBOL_FULL_STOP = new G2P_Pair(0X2E, "H#");
    // public static G2P_Pair SYMBOL_QUESTION_MARK = new G2P_Pair(0X3F, "H#");

    protected void initialize_g2pMappings(String g2pMappingsXml) {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(getClass().getResourceAsStream("/" + g2pMappingsXml));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Enumeration<Object> e = properties.keys(); e.hasMoreElements();) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            int codepoint;
            if (key.startsWith("\\u")) {
                codepoint = Integer.parseInt(key.substring(2, 6), 16);
            } else {
                codepoint = key.codePointAt(0);
            }
            g2pMappings.put(new Integer(codepoint), value);
        }
    }

    protected void loadNumberExpansionProperties(String numberExpansionConfigurationXml) {
        try {
            numberExpansionProperties
                    .loadFromXML(getClass().getResourceAsStream("/" + numberExpansionConfigurationXml));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void loadPunctuationExpansionProperties(String punctuationExpansionConfigurationXml) {
        try {
            punctuationExpansionProperties
                    .loadFromXML(getClass().getResourceAsStream("/" + punctuationExpansionConfigurationXml));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String expandUnitDigit(long number) {
        if (number == 0) {
            return "";
        }
        return numberExpansionProperties.getProperty(number + "") + SPACE;
    }

    protected String expandBasedOnPattern(long number, int divisor, String xString, String zerosString) {
        if (number == 0) {
            return "";
        }
        String output = numberExpansionProperties.getProperty(number + "");
        if (output != null) {
            return output + SPACE;
        }
        output = numberExpansionProperties.getProperty((number / divisor) + xString);
        if (output != null) {
            return output + SPACE + expandNumber(number % divisor);
        }
        if ((number % divisor) == 0 && (output = numberExpansionProperties.getProperty("x" + zerosString)) != null) {
            return expandNumber(number / divisor) + output + SPACE;
        }
        output = numberExpansionProperties.getProperty("x" + xString);
        if (output != null) {
            return expandNumber(number / divisor) + output + SPACE + expandNumber(number % divisor);
        }
        return "";
    }

    protected String expandTens(long number) {
        return expandBasedOnPattern(number, 10, "x", "0");
    }

    protected String expandHundreds(long number) {
        return expandBasedOnPattern(number, 100, "xx", "00");
    }

    protected String expandThousands(long number) {
        return expandBasedOnPattern(number, 1000, "xxx", "000");
    }

    protected String expandLakhs(long number) {
        return expandBasedOnPattern(number, 100000, "xxxxx", "00000");
    }

    protected String expandCrores(long number) {
        return expandBasedOnPattern(number, 10000000, "xxxxxxx", "0000000");
    }

    public String expandNumber(long number) {
        if (number <= 9) {
            return expandUnitDigit(number);
        }
        if (number <= 99) {
            return expandTens(number);
        }
        if (number <= 999) {
            return expandHundreds(number);
        }
        if (number <= 99999) {
            return expandThousands(number);
        }
        if (number <= 9999999) {
            return expandLakhs(number);
        }
        return expandCrores(number);
    }

    protected int replaceLanguageDigitsWithEnglishDigits(char[] inputChars) {
        int numbersStartIndex = -1;
        for (int i = 0; i < inputChars.length; i++) {
            int currentCharacter = inputChars[i];
            if (currentCharacter >= ENGLISH_DIGIT_ZERO && currentCharacter <= ENGLISH_DIGIT_NINE) {
                if (numbersStartIndex == -1) {
                    numbersStartIndex = i;
                }
            } else if (currentCharacter >= getLanguageDigitZero() && currentCharacter <= getLanguageDigitNine()) {
                if (numbersStartIndex == -1) {
                    numbersStartIndex = i;
                }
                inputChars[i] = getEnglishEquivalentOfLanguageDigit((char) currentCharacter);
            }
        }
        return numbersStartIndex;
    }

    protected String expandNumbers(String input) {
        char[] inputChars = input.toCharArray();
        int numbersStartIndex = replaceLanguageDigitsWithEnglishDigits(inputChars);
        if (numbersStartIndex == -1) {
            return input;
        }
        StringBuffer outputBuffer = new StringBuffer();
        if (numbersStartIndex > 0) {
            if (input.charAt(numbersStartIndex - 1) == '-') {
                numbersStartIndex--;
            }
            // copy the original non-numeral text into outputBuffer
            outputBuffer.append(inputChars, 0, numbersStartIndex);
        }
        StringBuffer numbersBeforeDecimalPoint = new StringBuffer();
        int decimalStartIndex = inputChars.length;
        int numbersEndIndex = inputChars.length;
        boolean hasComma = false;
        boolean hasDot = false;
        boolean isNegative = false;
        for (int i = numbersStartIndex; i < inputChars.length; i++) {
            char currentCharacter = inputChars[i];
            if (currentCharacter == SYMBOL_COMMA) {
                // Skip comma as in 80,55,255
                hasComma = true;
                continue;
            }
            if (currentCharacter == '-') {
                if (i == numbersStartIndex) {
                    isNegative = true;
                } else {
                    // currentCharacter is dash and is ignored
                }
                continue;
            }
            if (currentCharacter == SYMBOL_DOT && i < inputChars.length - 1) {
                // If a number with decimals are encountered, then
                // the fractional part must be read out individually.
                hasDot = true;
                decimalStartIndex = i + 1;
                break;
            }
            if (currentCharacter < ENGLISH_DIGIT_ZERO || currentCharacter > ENGLISH_DIGIT_NINE) {
                numbersEndIndex = i;
                break;
            }
            numbersBeforeDecimalPoint.append(currentCharacter);
        }
        if (isNegative) {
            outputBuffer.append(punctuationExpansionProperties.getProperty("-") + SPACE);
        }
        int lengthOfNumber = numbersBeforeDecimalPoint.length();
        if (hasComma || hasDot || isNegative || lengthOfNumber <= 4) {
            // numbers that contain comma, dot or minus are expanded as well as numbers in date format
            long numberToExpand = new Long(numbersBeforeDecimalPoint.toString()).intValue();
            if (numberToExpand == 0) {
                for (char c : numbersBeforeDecimalPoint.toString().toCharArray()) {
                    outputBuffer.append(numberExpansionProperties.getProperty(c + "") + SPACE);
                }
            } else {
                outputBuffer.append(expandNumber(numberToExpand));
            }
        } else {
            // all other numbers are read out individually
            for (char c : numbersBeforeDecimalPoint.toString().toCharArray()) {
                outputBuffer.append(numberExpansionProperties.getProperty(c + "") + SPACE);
            }
        }
        if (decimalStartIndex < inputChars.length) {
            outputBuffer.append(numberExpansionProperties.getProperty(".") + SPACE);
            for (int i = decimalStartIndex; i < inputChars.length; i++) {
                char currentCharacter = inputChars[i];
                if (currentCharacter < ENGLISH_DIGIT_ZERO || currentCharacter > ENGLISH_DIGIT_NINE) {
                    numbersEndIndex = i;
                    break;
                }
                outputBuffer.append(numberExpansionProperties.getProperty(currentCharacter + "") + SPACE);
            }
        }
        if (numbersEndIndex < inputChars.length) {
            // copy the remaining text into outputBuffer
            outputBuffer.append(inputChars, numbersEndIndex, inputChars.length - numbersEndIndex);
        }
        // recursive call to expand any numbers further down the input string
        String output = expandNumbers(outputBuffer.toString());
        return output;
    }

    private boolean isPunctuation(char symbol) {
        return punctuationExpansionProperties.get(symbol + "") != null ? true : false;
    }

    protected String expandPunctuation(String input) {
        char[] inputChars = input.toCharArray();
        StringBuffer outputBuffer = new StringBuffer();
        for (int i = 0; i < inputChars.length; i++) {
            if (isPunctuation(inputChars[i])) {
                outputBuffer.append(SPACE + punctuationExpansionProperties.getProperty(inputChars[i] + "") + SPACE);
            } else {
                outputBuffer.append(inputChars[i]);
            }
        }
        return outputBuffer.toString();
    }

    abstract public Phoneme[] performG2P(String input);

    abstract public int getLanguageDigitZero();

    abstract public int getLanguageDigitNine();

    abstract char getEnglishEquivalentOfLanguageDigit(char scriptDigit);

    public static final int unsignedByteToInt(byte b) {
        // "Convert an UNSIGNED byte to a JAVA type" - http://www.rgagnon.com/javadetails/java-0026.html
        return (int) b & 0xFF;
    }

    public static char[] getContentsOfUnicodeFile(String filePath) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
        int fileSize = in.available();
        if (fileSize < 2) {
            in.close();
            return null;
        }
        byte[] fileBytes = new byte[fileSize];
        in.read(fileBytes);
        in.close();

        int firstByte = unsignedByteToInt(fileBytes[0]);
        int secondByte = unsignedByteToInt(fileBytes[1]);
        String charSetName = "";
        int startIndex = 0;
        if (firstByte == 0xfe && secondByte == 0xff) {
            charSetName = "UTF-16BE";
            startIndex = 2;
        } else if (firstByte == 0xff && secondByte == 0xfe) {
            charSetName = "UTF-16LE";
            startIndex = 2;
        } else if (firstByte == 0xef) {
            charSetName = "UTF-8";
            startIndex = 3;
        } else if (firstByte == 0x0c && secondByte <= 0xef) { // TODO Make this check language independent
            charSetName = "UTF-16BE";
        } else if (secondByte == 0x0c && firstByte <= 0xef) {
            charSetName = "UTF-16LE";
        }

        InputStreamReader isr = new InputStreamReader(
                new ByteArrayInputStream(fileBytes, startIndex, fileSize - startIndex), charSetName);

        int arraySize = startIndex > 0 ? ((fileSize / startIndex) - 1) : (fileSize / 2);
        char[] fileChars = new char[arraySize];
        for (int i = 0, c; (c = isr.read()) != -1; i++) {
            fileChars[i] = (char) c;
        }
        return fileChars;
    }

    public static HashMap<String, Integer> phonemeGroups = new HashMap<String, Integer>();
    public static HashSet<String> vowelPhonemes = new HashSet<String>();
    protected static String GEMINATE_SUFFIX = "l";

    static {
        phonemeGroups.put("k", new Integer(1));
        phonemeGroups.put("c", new Integer(1));
        phonemeGroups.put("T", new Integer(1));
        phonemeGroups.put("t", new Integer(1));
        phonemeGroups.put("p", new Integer(1));

        // For Kannada TTS
        phonemeGroups.put("kh", new Integer(13));
        phonemeGroups.put("ch", new Integer(13));
        phonemeGroups.put("Th", new Integer(13));
        phonemeGroups.put("th", new Integer(13));
        phonemeGroups.put("ph", new Integer(13));

        phonemeGroups.put("g", new Integer(2));
        phonemeGroups.put("j", new Integer(2));
        phonemeGroups.put("D", new Integer(2));
        phonemeGroups.put("d", new Integer(2));
        phonemeGroups.put("b", new Integer(2));

        // For Kannada TTS
        phonemeGroups.put("gh", new Integer(14));
        phonemeGroups.put("jh", new Integer(14));
        phonemeGroups.put("Dh", new Integer(14));
        phonemeGroups.put("dh", new Integer(14));
        phonemeGroups.put("bh", new Integer(14));

        phonemeGroups.put("ng", new Integer(3));
        phonemeGroups.put("ny", new Integer(3));
        phonemeGroups.put("N", new Integer(3));
        phonemeGroups.put("n", new Integer(3));
        phonemeGroups.put("m", new Integer(3));

        phonemeGroups.put("S", new Integer(4));
        phonemeGroups.put("s", new Integer(4));
        phonemeGroups.put("h", new Integer(4));
        phonemeGroups.put("X", new Integer(4)); // TODO - No Grapheme is mapped to this!
        phonemeGroups.put("S1", new Integer(4)); // For Kannada TTS
        phonemeGroups.put("F", new Integer(4));

        phonemeGroups.put("y", new Integer(5));
        phonemeGroups.put("r", new Integer(5));
        phonemeGroups.put("R", new Integer(5));
        phonemeGroups.put("l", new Integer(5));
        phonemeGroups.put("L", new Integer(5));
        phonemeGroups.put("zh", new Integer(5));
        phonemeGroups.put("w", new Integer(5));
        phonemeGroups.put("v", new Integer(5));

        phonemeGroups.put("kl", new Integer(6));
        phonemeGroups.put("cl", new Integer(6));
        phonemeGroups.put("Tl", new Integer(6));
        phonemeGroups.put("tl", new Integer(6));
        phonemeGroups.put("pl", new Integer(6));

        // For Kannada TTS
        phonemeGroups.put("khl", new Integer(13));
        phonemeGroups.put("chl", new Integer(13));
        phonemeGroups.put("Thl", new Integer(13));
        phonemeGroups.put("thl", new Integer(13));
        phonemeGroups.put("phl", new Integer(13));

        phonemeGroups.put("gl", new Integer(7));
        phonemeGroups.put("jl", new Integer(7));
        phonemeGroups.put("Dl", new Integer(7));
        phonemeGroups.put("dl", new Integer(7));
        phonemeGroups.put("bl", new Integer(7));

        // For Kannada TTS
        phonemeGroups.put("ghl", new Integer(14));
        phonemeGroups.put("jhl", new Integer(14));
        phonemeGroups.put("Dhl", new Integer(14));
        phonemeGroups.put("dhl", new Integer(14));
        phonemeGroups.put("bhl", new Integer(14));

        phonemeGroups.put("ngl", new Integer(8));
        phonemeGroups.put("nyl", new Integer(8));
        phonemeGroups.put("Nl", new Integer(8));
        phonemeGroups.put("nl", new Integer(8));
        phonemeGroups.put("ml", new Integer(8));

        phonemeGroups.put("Sl", new Integer(9));
        phonemeGroups.put("sl", new Integer(9));
        phonemeGroups.put("hl", new Integer(9));
        phonemeGroups.put("S1l", new Integer(9));

        phonemeGroups.put("yl", new Integer(10));
        phonemeGroups.put("rl", new Integer(10));
        phonemeGroups.put("TR", new Integer(10));
        phonemeGroups.put("ll", new Integer(10));
        phonemeGroups.put("Ll", new Integer(10));
        phonemeGroups.put("zhl", new Integer(10));
        phonemeGroups.put("wl", new Integer(10));

        phonemeGroups.put("#", new Integer(11));
        phonemeGroups.put("H#", new Integer(11));

        phonemeGroups.put("a", new Integer(12));
        phonemeGroups.put("A", new Integer(12));
        phonemeGroups.put("i", new Integer(12));
        phonemeGroups.put("I", new Integer(12));
        phonemeGroups.put("u", new Integer(12));
        phonemeGroups.put("U", new Integer(12));

        phonemeGroups.put("ru", new Integer(12));

        phonemeGroups.put("e", new Integer(12));
        phonemeGroups.put("E", new Integer(12));
        phonemeGroups.put("ae", new Integer(12));
        phonemeGroups.put("AI", new Integer(12)); // For Kannada TTS
        phonemeGroups.put("o", new Integer(12));
        phonemeGroups.put("O", new Integer(12));
        phonemeGroups.put("au", new Integer(12));

        vowelPhonemes.add("a");
        vowelPhonemes.add("A");
        vowelPhonemes.add("i");
        vowelPhonemes.add("I");
        vowelPhonemes.add("u");
        vowelPhonemes.add("U");
        vowelPhonemes.add("ru");
        vowelPhonemes.add("e");
        vowelPhonemes.add("E");
        vowelPhonemes.add("ae");
        vowelPhonemes.add("AI"); // TODO For Kannada TTS, what about "ai"?
        vowelPhonemes.add("o");
        vowelPhonemes.add("O");
        vowelPhonemes.add("au");
    }

    public static int getPhonemeGroup(String phoneme) {
        Integer groupNumber = phonemeGroups.get(phoneme);
        if (groupNumber != null) {
            return groupNumber.intValue();
        }
        return -1;
    }

    public static boolean isVowel(String phoneme) {
        return vowelPhonemes.contains(phoneme);
    }

    abstract public boolean isVowel(int codePoint);
}
