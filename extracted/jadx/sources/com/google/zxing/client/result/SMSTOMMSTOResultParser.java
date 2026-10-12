package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class SMSTOMMSTOResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public SMSParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        if (!massagedText.startsWith("smsto:") && !massagedText.startsWith("SMSTO:") && !massagedText.startsWith("mmsto:") && !massagedText.startsWith("MMSTO:")) {
            return null;
        }
        String strSubstring = massagedText.substring(6);
        String strSubstring2 = null;
        int iIndexOf = strSubstring.indexOf(58);
        if (iIndexOf >= 0) {
            strSubstring2 = strSubstring.substring(iIndexOf + 1);
            strSubstring = strSubstring.substring(0, iIndexOf);
        }
        return new SMSParsedResult(strSubstring, (String) null, (String) null, strSubstring2);
    }
}
