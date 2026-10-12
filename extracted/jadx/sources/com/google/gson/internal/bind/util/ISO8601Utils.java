package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder("yyyy-MM-ddThh:mm:ss".length() + (z ? ".sss".length() : 0) + (timeZone.getRawOffset() == 0 ? "Z".length() : "+hh:mm".length()));
        padInt(sb, gregorianCalendar.get(1), "yyyy".length());
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, "MM".length());
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), "dd".length());
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), "hh".length());
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), "mm".length());
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), "ss".length());
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), "sss".length());
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int iAbs = Math.abs((offset / 60000) / 60);
            int iAbs2 = Math.abs((offset / 60000) % 60);
            sb.append(offset < 0 ? '-' : '+');
            padInt(sb, iAbs, "hh".length());
            sb.append(':');
            padInt(sb, iAbs2, "mm".length());
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    private static int indexOfNonDigit(String str, int i) {
        int i2 = i;
        while (i2 < str.length()) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < '0' || cCharAt > '9') {
                return i2;
            }
            i2++;
        }
        return str.length();
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String string = Integer.toString(i);
        for (int length = i2 - string.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(string);
    }

    /* JADX WARN: Code duplicated, block: B:48:0x0150  */
    /* JADX WARN: Code duplicated, block: B:51:0x0157  */
    /* JADX WARN: Code duplicated, block: B:53:0x015d  */
    /* JADX WARN: Code duplicated, block: B:87:0x0315  */
    public static Date parse(String str, ParsePosition parsePosition) throws ParseException {
        Exception exc;
        String str2;
        String message;
        int length;
        TimeZone timeZone;
        char cCharAt;
        try {
            int index = parsePosition.getIndex();
            int i = index + 4;
            int i2 = parseInt(str, index, i);
            if (checkOffset(str, i, '-')) {
                i++;
            }
            int i3 = i + 2;
            int i4 = parseInt(str, i, i3);
            int i5 = checkOffset(str, i3, '-') ? i3 + 1 : i3;
            int i6 = i5 + 2;
            int i7 = parseInt(str, i5, i6);
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            boolean zCheckOffset = checkOffset(str, i6, 'T');
            if (!zCheckOffset && str.length() <= i6) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(i2, i4 - 1, i7);
                parsePosition.setIndex(i6);
                return gregorianCalendar.getTime();
            }
            if (zCheckOffset) {
                int i12 = i6 + 1;
                int i13 = i12 + 2;
                i8 = parseInt(str, i12, i13);
                if (checkOffset(str, i13, ':')) {
                    i13++;
                }
                int i14 = i13 + 2;
                i9 = parseInt(str, i13, i14);
                int i15 = checkOffset(str, i14, ':') ? i14 + 1 : i14;
                if (str.length() <= i15 || (cCharAt = str.charAt(i15)) == 'Z' || cCharAt == '+' || cCharAt == '-') {
                    i6 = i15;
                } else {
                    i6 = i15 + 2;
                    i10 = parseInt(str, i15, i6);
                    if (i10 > 59 && i10 < 63) {
                        i10 = 59;
                    }
                    if (checkOffset(str, i6, '.')) {
                        int i16 = i6 + 1;
                        int iIndexOfNonDigit = indexOfNonDigit(str, i16 + 1);
                        int iMin = Math.min(iIndexOfNonDigit, i16 + 3);
                        int i17 = parseInt(str, i16, iMin);
                        switch (iMin - i16) {
                            case 1:
                                i11 = i17 * 100;
                                break;
                            case 2:
                                i11 = i17 * 10;
                                break;
                            default:
                                i11 = i17;
                                break;
                        }
                        i6 = iIndexOfNonDigit;
                    }
                }
            }
            if (str.length() <= i6) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            char cCharAt2 = str.charAt(i6);
            if (cCharAt2 == 'Z') {
                timeZone = TIMEZONE_UTC;
                length = i6 + 1;
            } else {
                if (cCharAt2 != '+' && cCharAt2 != '-') {
                    throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt2 + "'");
                }
                String strSubstring = str.substring(i6);
                if (strSubstring.length() < 5) {
                    strSubstring = strSubstring + "00";
                }
                length = i6 + strSubstring.length();
                if ("+0000".equals(strSubstring) || "+00:00".equals(strSubstring)) {
                    timeZone = TIMEZONE_UTC;
                } else {
                    String str3 = "GMT" + strSubstring;
                    timeZone = TimeZone.getTimeZone(str3);
                    String id = timeZone.getID();
                    if (!id.equals(str3) && !id.replace(":", "").equals(str3)) {
                        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone.getID());
                    }
                }
            }
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
            gregorianCalendar2.setLenient(false);
            gregorianCalendar2.set(1, i2);
            gregorianCalendar2.set(2, i4 - 1);
            gregorianCalendar2.set(5, i7);
            gregorianCalendar2.set(11, i8);
            gregorianCalendar2.set(12, i9);
            gregorianCalendar2.set(13, i10);
            gregorianCalendar2.set(14, i11);
            parsePosition.setIndex(length);
            return gregorianCalendar2.getTime();
        } catch (IllegalArgumentException e) {
            exc = e;
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + "'";
            }
            message = exc.getMessage();
            if (message != null || message.isEmpty()) {
                message = "(" + exc.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(exc);
            throw parseException;
        } catch (IndexOutOfBoundsException e2) {
            exc = e2;
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + "'";
            }
            message = exc.getMessage();
            if (message != null) {
                message = "(" + exc.getClass().getName() + ")";
            } else {
                message = "(" + exc.getClass().getName() + ")";
            }
            ParseException parseException2 = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException2.initCause(exc);
            throw parseException2;
        } catch (NumberFormatException e3) {
            exc = e3;
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + "'";
            }
            message = exc.getMessage();
            if (message != null) {
                message = "(" + exc.getClass().getName() + ")";
            } else {
                message = "(" + exc.getClass().getName() + ")";
            }
            ParseException parseException3 = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException3.initCause(exc);
            throw parseException3;
        }
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        int i4 = 0;
        if (i < i2) {
            i3 = i + 1;
            int iDigit = Character.digit(str.charAt(i), 10);
            if (iDigit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = -iDigit;
        } else {
            i3 = i;
        }
        while (i3 < i2) {
            int i5 = i3 + 1;
            int iDigit2 = Character.digit(str.charAt(i3), 10);
            if (iDigit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = (i4 * 10) - iDigit2;
            i3 = i5;
        }
        return -i4;
    }
}
