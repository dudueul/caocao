package com.google.zxing.client.result;

import com.google.android.gms.games.GamesStatusCodes;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class VINResultParser extends ResultParser {
    private static final Pattern IOQ = Pattern.compile("[IOQ]");
    private static final Pattern AZ09 = Pattern.compile("[A-Z0-9]{17}");

    private static char checkChar(int i) {
        if (i < 10) {
            return (char) (i + 48);
        }
        if (i == 10) {
            return 'X';
        }
        throw new IllegalArgumentException();
    }

    private static boolean checkChecksum(CharSequence charSequence) {
        int iVinPositionWeight = 0;
        for (int i = 0; i < charSequence.length(); i++) {
            iVinPositionWeight += vinPositionWeight(i + 1) * vinCharValue(charSequence.charAt(i));
        }
        return charSequence.charAt(8) == checkChar(iVinPositionWeight % 11);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:4:0x0017 A[ORIG_RETURN, RETURN] */
    private static String countryCode(CharSequence charSequence) {
        char cCharAt = charSequence.charAt(0);
        char cCharAt2 = charSequence.charAt(1);
        switch (cCharAt) {
            case '1':
            case '4':
            case '5':
                return "US";
            case '2':
                return "CA";
            case '3':
                if (cCharAt2 < 'A' || cCharAt2 > 'W') {
                    return null;
                }
                return "MX";
            case '9':
                if ((cCharAt2 < 'A' || cCharAt2 > 'E') && (cCharAt2 < '3' || cCharAt2 > '9')) {
                    return null;
                }
                return "BR";
            case 'J':
                if (cCharAt2 < 'A' || cCharAt2 > 'T') {
                    return null;
                }
                return "JP";
            case 'K':
                if (cCharAt2 < 'L' || cCharAt2 > 'R') {
                    return null;
                }
                return "KO";
            case 'L':
                return "CN";
            case 'M':
                if (cCharAt2 < 'A' || cCharAt2 > 'E') {
                    return null;
                }
                return "IN";
            case 'S':
                if (cCharAt2 >= 'A' && cCharAt2 <= 'M') {
                    return "UK";
                }
                if (cCharAt2 < 'N' || cCharAt2 > 'T') {
                    return null;
                }
                return "DE";
            case 'V':
                if (cCharAt2 >= 'F' && cCharAt2 <= 'R') {
                    return "FR";
                }
                if (cCharAt2 < 'S' || cCharAt2 > 'W') {
                    return null;
                }
                return "ES";
            case 'W':
                return "DE";
            case 'X':
                if (cCharAt2 == '0' || (cCharAt2 >= '3' && cCharAt2 <= '9')) {
                    return "RU";
                }
                return null;
            case 'Z':
                if (cCharAt2 < 'A' || cCharAt2 > 'R') {
                    return null;
                }
                return "IT";
            default:
                return null;
        }
    }

    private static int modelYear(char c) {
        if (c >= 'E' && c <= 'H') {
            return (c - 'E') + 1984;
        }
        if (c >= 'J' && c <= 'N') {
            return (c - 'J') + 1988;
        }
        if (c == 'P') {
            return 1993;
        }
        if (c >= 'R' && c <= 'T') {
            return (c - 'R') + 1994;
        }
        if (c >= 'V' && c <= 'Y') {
            return (c - 'V') + 1997;
        }
        if (c >= '1' && c <= '9') {
            return (c - '1') + GamesStatusCodes.STATUS_REQUEST_UPDATE_TOTAL_FAILURE;
        }
        if (c < 'A' || c > 'D') {
            throw new IllegalArgumentException();
        }
        return (c - 'A') + 2010;
    }

    private static int vinCharValue(char c) {
        if (c >= 'A' && c <= 'I') {
            return (c - 'A') + 1;
        }
        if (c >= 'J' && c <= 'R') {
            return (c - 'J') + 1;
        }
        if (c >= 'S' && c <= 'Z') {
            return (c - 'S') + 2;
        }
        if (c < '0' || c > '9') {
            throw new IllegalArgumentException();
        }
        return c - '0';
    }

    private static int vinPositionWeight(int i) {
        if (i > 0 && i <= 7) {
            return 9 - i;
        }
        if (i == 8) {
            return 10;
        }
        if (i == 9) {
            return 0;
        }
        if (i < 10 || i > 17) {
            throw new IllegalArgumentException();
        }
        return 19 - i;
    }

    @Override // com.google.zxing.client.result.ResultParser
    public VINParsedResult parse(Result result) {
        if (result.getBarcodeFormat() != BarcodeFormat.CODE_39) {
            return null;
        }
        String strTrim = IOQ.matcher(result.getText()).replaceAll("").trim();
        if (!AZ09.matcher(strTrim).matches()) {
            return null;
        }
        try {
            if (!checkChecksum(strTrim)) {
                return null;
            }
            String strSubstring = strTrim.substring(0, 3);
            return new VINParsedResult(strTrim, strSubstring, strTrim.substring(3, 9), strTrim.substring(9, 17), countryCode(strSubstring), strTrim.substring(3, 8), modelYear(strTrim.charAt(9)), strTrim.charAt(10), strTrim.substring(11));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
