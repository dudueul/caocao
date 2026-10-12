package com.google.zxing.oned;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class CodaBarWriter extends OneDimensionalCodeWriter {
    private static final char[] START_END_CHARS = {'A', 'B', 'C', 'D'};
    private static final char[] ALT_START_END_CHARS = {'T', 'N', '*', 'E'};
    private static final char[] CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED = {'/', ':', '+', '.'};
    private static final char DEFAULT_GUARD = START_END_CHARS[0];

    /* JADX WARN: Code duplicated, block: B:52:0x019d  */
    /* JADX WARN: Code duplicated, block: B:57:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:64:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:68:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:77:0x01da A[SYNTHETIC] */
    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        boolean z;
        int i;
        int i2;
        if (str.length() < 2) {
            str = DEFAULT_GUARD + str + DEFAULT_GUARD;
        } else {
            char upperCase = Character.toUpperCase(str.charAt(0));
            char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            boolean zArrayContains = CodaBarReader.arrayContains(START_END_CHARS, upperCase);
            boolean zArrayContains2 = CodaBarReader.arrayContains(START_END_CHARS, upperCase2);
            boolean zArrayContains3 = CodaBarReader.arrayContains(ALT_START_END_CHARS, upperCase);
            boolean zArrayContains4 = CodaBarReader.arrayContains(ALT_START_END_CHARS, upperCase2);
            if (zArrayContains) {
                if (!zArrayContains2) {
                    throw new IllegalArgumentException("Invalid start/end guards: " + str);
                }
            } else if (!zArrayContains3) {
                if (zArrayContains2 || zArrayContains4) {
                    throw new IllegalArgumentException("Invalid start/end guards: " + str);
                }
                str = DEFAULT_GUARD + str + DEFAULT_GUARD;
            } else if (!zArrayContains4) {
                throw new IllegalArgumentException("Invalid start/end guards: " + str);
            }
        }
        int i3 = 20;
        for (int i4 = 1; i4 < str.length() - 1; i4++) {
            if (Character.isDigit(str.charAt(i4)) || str.charAt(i4) == '-' || str.charAt(i4) == '$') {
                i3 += 9;
            } else {
                if (!CodaBarReader.arrayContains(CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED, str.charAt(i4))) {
                    throw new IllegalArgumentException("Cannot encode : '" + str.charAt(i4) + '\'');
                }
                i3 += 10;
            }
        }
        boolean[] zArr = new boolean[(str.length() - 1) + i3];
        int i5 = 0;
        for (int i6 = 0; i6 < str.length(); i6++) {
            char upperCase3 = Character.toUpperCase(str.charAt(i6));
            if (i6 == 0 || i6 == str.length() - 1) {
                switch (upperCase3) {
                    case '*':
                        upperCase3 = 'C';
                        break;
                    case 'E':
                        upperCase3 = 'D';
                        break;
                    case 'N':
                        upperCase3 = 'B';
                        break;
                    case 'T':
                        upperCase3 = 'A';
                        break;
                }
            }
            int i7 = 0;
            for (int i8 = 0; i8 < CodaBarReader.ALPHABET.length; i8++) {
                if (upperCase3 == CodaBarReader.ALPHABET[i8]) {
                    i7 = CodaBarReader.CHARACTER_ENCODINGS[i8];
                    z = true;
                    i = 0;
                    i2 = 0;
                    while (i2 < 7) {
                        zArr[i5] = z;
                        i5++;
                        if (((i7 >> (6 - i2)) & 1) != 0 || i == 1) {
                            if (z) {
                                z = false;
                            } else {
                                z = true;
                            }
                            i2++;
                            i = 0;
                        } else {
                            i++;
                        }
                    }
                    if (i6 < str.length() - 1) {
                        zArr[i5] = false;
                        i5++;
                    }
                }
            }
            z = true;
            i = 0;
            i2 = 0;
            while (i2 < 7) {
                zArr[i5] = z;
                i5++;
                if (((i7 >> (6 - i2)) & 1) != 0) {
                }
                if (z) {
                    z = true;
                } else {
                    z = false;
                }
                i2++;
                i = 0;
            }
            if (i6 < str.length() - 1) {
                zArr[i5] = false;
                i5++;
            }
        }
        return zArr;
    }
}
