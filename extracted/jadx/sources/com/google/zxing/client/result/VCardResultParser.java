package com.google.zxing.client.result;

import com.facebook.share.internal.ShareConstants;
import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class VCardResultParser extends ResultParser {
    private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");
    private static final Pattern CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
    private static final Pattern NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
    private static final Pattern VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern SEMICOLON_OR_COMMA = Pattern.compile("[;,]");

    private static String decodeQuotedPrintable(CharSequence charSequence, String str) {
        char cCharAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char cCharAt2 = charSequence.charAt(i);
            switch (cCharAt2) {
                case '\n':
                case '\r':
                    break;
                case '=':
                    if (i < length - 2 && (cCharAt = charSequence.charAt(i + 1)) != '\r' && cCharAt != '\n') {
                        char cCharAt3 = charSequence.charAt(i + 2);
                        int hexDigit = parseHexDigit(cCharAt);
                        int hexDigit2 = parseHexDigit(cCharAt3);
                        if (hexDigit >= 0 && hexDigit2 >= 0) {
                            byteArrayOutputStream.write((hexDigit << 4) + hexDigit2);
                        }
                        i += 2;
                    }
                    break;
                default:
                    maybeAppendFragment(byteArrayOutputStream, str, sb);
                    sb.append(cCharAt2);
                    break;
            }
            i++;
        }
        maybeAppendFragment(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    private static void formatNames(Iterable<List<String>> iterable) {
        if (iterable != null) {
            for (List<String> list : iterable) {
                String str = list.get(0);
                String[] strArr = new String[5];
                int i = 0;
                int i2 = 0;
                while (i2 < 4) {
                    int iIndexOf = str.indexOf(59, i);
                    if (iIndexOf < 0) {
                        break;
                    }
                    strArr[i2] = str.substring(i, iIndexOf);
                    i2++;
                    i = iIndexOf + 1;
                }
                strArr[i2] = str.substring(i);
                StringBuilder sb = new StringBuilder(100);
                maybeAppendComponent(strArr, 3, sb);
                maybeAppendComponent(strArr, 1, sb);
                maybeAppendComponent(strArr, 2, sb);
                maybeAppendComponent(strArr, 0, sb);
                maybeAppendComponent(strArr, 4, sb);
                list.set(0, sb.toString().trim());
            }
        }
    }

    private static boolean isLikeVCardDate(CharSequence charSequence) {
        return charSequence == null || VCARD_LIKE_DATE.matcher(charSequence).matches();
    }

    static List<String> matchSingleVCardPrefixedField(CharSequence charSequence, String str, boolean z, boolean z2) {
        List<List<String>> listMatchVCardPrefixedField = matchVCardPrefixedField(charSequence, str, z, z2);
        if (listMatchVCardPrefixedField == null || listMatchVCardPrefixedField.isEmpty()) {
            return null;
        }
        return listMatchVCardPrefixedField.get(0);
    }

    static List<List<String>> matchVCardPrefixedField(CharSequence charSequence, String str, boolean z, boolean z2) {
        int iIndexOf;
        String strReplaceAll;
        ArrayList arrayList = null;
        int i = 0;
        int length = str.length();
        while (i < length) {
            Matcher matcher = Pattern.compile("(?:^|\n)" + ((Object) charSequence) + "(?:;([^:]*))?:", 2).matcher(str);
            if (i > 0) {
                i--;
            }
            if (!matcher.find(i)) {
                break;
            }
            int iEnd = matcher.end(0);
            String strGroup = matcher.group(1);
            ArrayList arrayList2 = null;
            boolean z3 = false;
            String str2 = null;
            if (strGroup != null) {
                for (String str3 : SEMICOLON.split(strGroup)) {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(1);
                    }
                    arrayList2.add(str3);
                    String[] strArrSplit = EQUALS.split(str3, 2);
                    if (strArrSplit.length > 1) {
                        String str4 = strArrSplit[0];
                        String str5 = strArrSplit[1];
                        if ("ENCODING".equalsIgnoreCase(str4) && "QUOTED-PRINTABLE".equalsIgnoreCase(str5)) {
                            z3 = true;
                        } else if ("CHARSET".equalsIgnoreCase(str4)) {
                            str2 = str5;
                        }
                    }
                }
            }
            while (true) {
                iIndexOf = str.indexOf(10, iEnd);
                if (iIndexOf < 0) {
                    break;
                }
                if (iIndexOf < str.length() - 1 && (str.charAt(iIndexOf + 1) == ' ' || str.charAt(iIndexOf + 1) == '\t')) {
                    iEnd = iIndexOf + 2;
                } else {
                    if (!z3 || ((iIndexOf <= 0 || str.charAt(iIndexOf - 1) != '=') && (iIndexOf < 2 || str.charAt(iIndexOf - 2) != '='))) {
                        break;
                    }
                    iEnd = iIndexOf + 1;
                }
            }
            if (iIndexOf < 0) {
                i = length;
            } else if (iIndexOf > iEnd) {
                if (arrayList == null) {
                    arrayList = new ArrayList(1);
                }
                if (iIndexOf > 0 && str.charAt(iIndexOf - 1) == '\r') {
                    iIndexOf--;
                }
                String strSubstring = str.substring(iEnd, iIndexOf);
                if (z) {
                    strSubstring = strSubstring.trim();
                }
                if (z3) {
                    strReplaceAll = decodeQuotedPrintable(strSubstring, str2);
                    if (z2) {
                        strReplaceAll = UNESCAPED_SEMICOLONS.matcher(strReplaceAll).replaceAll("\n").trim();
                    }
                } else {
                    if (z2) {
                        strSubstring = UNESCAPED_SEMICOLONS.matcher(strSubstring).replaceAll("\n").trim();
                    }
                    strReplaceAll = VCARD_ESCAPES.matcher(NEWLINE_ESCAPE.matcher(CR_LF_SPACE_TAB.matcher(strSubstring).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                }
                if (arrayList2 == null) {
                    ArrayList arrayList3 = new ArrayList(1);
                    arrayList3.add(strReplaceAll);
                    arrayList.add(arrayList3);
                } else {
                    arrayList2.add(0, strReplaceAll);
                    arrayList.add(arrayList2);
                }
                i = iIndexOf + 1;
            } else {
                i = iIndexOf + 1;
            }
        }
        return arrayList;
    }

    private static void maybeAppendComponent(String[] strArr, int i, StringBuilder sb) {
        if (strArr[i] == null || strArr[i].isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(strArr[i]);
    }

    private static void maybeAppendFragment(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, Charset.forName("UTF-8"));
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException e) {
                    str2 = new String(byteArray, Charset.forName("UTF-8"));
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    private static String toPrimaryValue(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static String[] toPrimaryValues(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<List<String>> it = collection.iterator();
        while (it.hasNext()) {
            String str = it.next().get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    private static String[] toTypes(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String strSubstring = null;
            for (int i = 1; i < list.size(); i++) {
                String str = list.get(i);
                int iIndexOf = str.indexOf(61);
                if (iIndexOf < 0) {
                    strSubstring = str;
                    break;
                }
                if ("TYPE".equalsIgnoreCase(str.substring(0, iIndexOf))) {
                    strSubstring = str.substring(iIndexOf + 1);
                    break;
                }
            }
            arrayList.add(strSubstring);
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        Matcher matcher = BEGIN_VCARD.matcher(massagedText);
        if (!matcher.find() || matcher.start() != 0) {
            return null;
        }
        List<List<String>> listMatchVCardPrefixedField = matchVCardPrefixedField("FN", massagedText, true, false);
        if (listMatchVCardPrefixedField == null) {
            listMatchVCardPrefixedField = matchVCardPrefixedField("N", massagedText, true, false);
            formatNames(listMatchVCardPrefixedField);
        }
        List<String> listMatchSingleVCardPrefixedField = matchSingleVCardPrefixedField("NICKNAME", massagedText, true, false);
        String[] strArrSplit = listMatchSingleVCardPrefixedField == null ? null : COMMA.split(listMatchSingleVCardPrefixedField.get(0));
        List<List<String>> listMatchVCardPrefixedField2 = matchVCardPrefixedField("TEL", massagedText, true, false);
        List<List<String>> listMatchVCardPrefixedField3 = matchVCardPrefixedField("EMAIL", massagedText, true, false);
        List<String> listMatchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("NOTE", massagedText, false, false);
        List<List<String>> listMatchVCardPrefixedField4 = matchVCardPrefixedField("ADR", massagedText, true, true);
        List<String> listMatchSingleVCardPrefixedField3 = matchSingleVCardPrefixedField("ORG", massagedText, true, true);
        List<String> listMatchSingleVCardPrefixedField4 = matchSingleVCardPrefixedField("BDAY", massagedText, true, false);
        if (listMatchSingleVCardPrefixedField4 != null && !isLikeVCardDate(listMatchSingleVCardPrefixedField4.get(0))) {
            listMatchSingleVCardPrefixedField4 = null;
        }
        List<String> listMatchSingleVCardPrefixedField5 = matchSingleVCardPrefixedField(ShareConstants.TITLE, massagedText, true, false);
        List<List<String>> listMatchVCardPrefixedField5 = matchVCardPrefixedField("URL", massagedText, true, false);
        List<String> listMatchSingleVCardPrefixedField6 = matchSingleVCardPrefixedField("IMPP", massagedText, true, false);
        List<String> listMatchSingleVCardPrefixedField7 = matchSingleVCardPrefixedField("GEO", massagedText, true, false);
        String[] strArrSplit2 = listMatchSingleVCardPrefixedField7 == null ? null : SEMICOLON_OR_COMMA.split(listMatchSingleVCardPrefixedField7.get(0));
        if (strArrSplit2 != null && strArrSplit2.length != 2) {
            strArrSplit2 = null;
        }
        return new AddressBookParsedResult(toPrimaryValues(listMatchVCardPrefixedField), strArrSplit, null, toPrimaryValues(listMatchVCardPrefixedField2), toTypes(listMatchVCardPrefixedField2), toPrimaryValues(listMatchVCardPrefixedField3), toTypes(listMatchVCardPrefixedField3), toPrimaryValue(listMatchSingleVCardPrefixedField6), toPrimaryValue(listMatchSingleVCardPrefixedField2), toPrimaryValues(listMatchVCardPrefixedField4), toTypes(listMatchVCardPrefixedField4), toPrimaryValue(listMatchSingleVCardPrefixedField3), toPrimaryValue(listMatchSingleVCardPrefixedField4), toPrimaryValue(listMatchSingleVCardPrefixedField5), toPrimaryValues(listMatchVCardPrefixedField5), strArrSplit2);
    }
}
