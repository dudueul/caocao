package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class SMTPResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public EmailAddressParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        if (!massagedText.startsWith("smtp:") && !massagedText.startsWith("SMTP:")) {
            return null;
        }
        String strSubstring = massagedText.substring(5);
        String strSubstring2 = null;
        String strSubstring3 = null;
        int iIndexOf = strSubstring.indexOf(58);
        if (iIndexOf >= 0) {
            strSubstring2 = strSubstring.substring(iIndexOf + 1);
            strSubstring = strSubstring.substring(0, iIndexOf);
            int iIndexOf2 = strSubstring2.indexOf(58);
            if (iIndexOf2 >= 0) {
                strSubstring3 = strSubstring2.substring(iIndexOf2 + 1);
                strSubstring2 = strSubstring2.substring(0, iIndexOf2);
            }
        }
        return new EmailAddressParsedResult(new String[]{strSubstring}, null, null, strSubstring2, strSubstring3);
    }
}
