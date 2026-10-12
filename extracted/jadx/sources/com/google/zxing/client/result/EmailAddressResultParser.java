package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Map;
import java.util.regex.Pattern;
import kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService;
import kr.co.nexon.toy.android.ui.baseplate.NPPlateCodes;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class EmailAddressResultParser extends ResultParser {
    private static final Pattern COMMA = Pattern.compile(",");

    @Override // com.google.zxing.client.result.ResultParser
    public EmailAddressParsedResult parse(Result result) {
        String str;
        String massagedText = getMassagedText(result);
        if (!massagedText.startsWith("mailto:") && !massagedText.startsWith("MAILTO:")) {
            if (EmailDoCoMoResultParser.isBasicallyValidEmailAddress(massagedText)) {
                return new EmailAddressParsedResult(massagedText);
            }
            return null;
        }
        String strSubstring = massagedText.substring(7);
        int iIndexOf = strSubstring.indexOf(63);
        if (iIndexOf >= 0) {
            strSubstring = strSubstring.substring(0, iIndexOf);
        }
        try {
            String strUrlDecode = urlDecode(strSubstring);
            String[] strArrSplit = strUrlDecode.isEmpty() ? null : COMMA.split(strUrlDecode);
            Map<String, String> nameValuePairs = parseNameValuePairs(massagedText);
            String[] strArrSplit2 = null;
            String[] strArrSplit3 = null;
            String str2 = null;
            String str3 = null;
            if (nameValuePairs != null) {
                if (strArrSplit == null && (str = nameValuePairs.get("to")) != null) {
                    strArrSplit = COMMA.split(str);
                }
                String str4 = nameValuePairs.get(NPPlateCodes.CODE_CUSTOMER_CENTER);
                strArrSplit2 = str4 != null ? COMMA.split(str4) : null;
                String str5 = nameValuePairs.get("bcc");
                strArrSplit3 = str5 != null ? COMMA.split(str5) : null;
                str2 = nameValuePairs.get("subject");
                str3 = nameValuePairs.get(NXPBaseFcmMessagingService.KEY_MESSAGE);
            }
            return new EmailAddressParsedResult(strArrSplit, strArrSplit2, strArrSplit3, str2, str3);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
