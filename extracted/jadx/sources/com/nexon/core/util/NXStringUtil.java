package com.nexon.core.util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXStringUtil {
    public static String changeStringListToString(List<String> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                if (str == null) {
                    sb.append(", ");
                } else {
                    sb.append(str);
                }
            }
        }
        return sb.toString();
    }

    public static boolean containsEmail(String str) {
        if (isNull(str)) {
            return false;
        }
        return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 2).matcher(str).find();
    }

    public static String escapeNull(String str) {
        return str == null ? "" : str;
    }

    public static String getCommaValue(double d) {
        return new DecimalFormat("#,###").format(d);
    }

    public static String getMaskedEmail(String str) {
        if (!isValidEmail(str)) {
            return "";
        }
        return str.replaceAll("(^[^@]{" + ((int) Math.ceil(((double) str.split("@")[0].length()) / 2.0d)) + "}|(?!^)\\G)[^@]", "$1*");
    }

    public static String getMaskedPhoneNumber(String str) {
        if (isNull(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {
                sb.append("*");
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public static int getMatchCount(String str, String str2) {
        Matcher matcher = Pattern.compile(str).matcher(str2);
        int i = 0;
        for (int iEnd = 0; matcher.find(iEnd); iEnd = matcher.end()) {
            i++;
        }
        return i;
    }

    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    public static boolean isNull(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidEmail(CharSequence charSequence) {
        return (charSequence == null || charSequence.length() == 0 || !charSequence.toString().contains("@")) ? false : true;
    }

    public static boolean matchingPattern(String str, String str2) {
        return Pattern.compile(str).matcher(str2).matches();
    }
}
