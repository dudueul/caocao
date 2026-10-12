package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser {
    private static String parseName(String str) {
        int iIndexOf = str.indexOf(44);
        return iIndexOf >= 0 ? str.substring(iIndexOf + 1) + ' ' + str.substring(0, iIndexOf) : str;
    }

    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String[] strArrMatchDoCoMoPrefixedField;
        String massagedText = getMassagedText(result);
        if (!massagedText.startsWith("MECARD:") || (strArrMatchDoCoMoPrefixedField = matchDoCoMoPrefixedField("N:", massagedText, true)) == null) {
            return null;
        }
        String name = parseName(strArrMatchDoCoMoPrefixedField[0]);
        String strMatchSingleDoCoMoPrefixedField = matchSingleDoCoMoPrefixedField("SOUND:", massagedText, true);
        String[] strArrMatchDoCoMoPrefixedField2 = matchDoCoMoPrefixedField("TEL:", massagedText, true);
        String[] strArrMatchDoCoMoPrefixedField3 = matchDoCoMoPrefixedField("EMAIL:", massagedText, true);
        String strMatchSingleDoCoMoPrefixedField2 = matchSingleDoCoMoPrefixedField("NOTE:", massagedText, false);
        String[] strArrMatchDoCoMoPrefixedField4 = matchDoCoMoPrefixedField("ADR:", massagedText, true);
        String strMatchSingleDoCoMoPrefixedField3 = matchSingleDoCoMoPrefixedField("BDAY:", massagedText, true);
        if (!isStringOfDigits(strMatchSingleDoCoMoPrefixedField3, 8)) {
            strMatchSingleDoCoMoPrefixedField3 = null;
        }
        return new AddressBookParsedResult(maybeWrap(name), null, strMatchSingleDoCoMoPrefixedField, strArrMatchDoCoMoPrefixedField2, null, strArrMatchDoCoMoPrefixedField3, null, null, strMatchSingleDoCoMoPrefixedField2, strArrMatchDoCoMoPrefixedField4, null, matchSingleDoCoMoPrefixedField("ORG:", massagedText, true), strMatchSingleDoCoMoPrefixedField3, null, matchDoCoMoPrefixedField("URL:", massagedText, true), null);
    }
}
