package com.naver.glink.android.sdk.a;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: ByteLengthInputFilter.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b implements InputFilter {
    private static final String a = "ByteLengthInputFilter";
    private int b;

    public b(int i) {
        this.b = i;
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return str.getBytes("KSC5601").length;
        } catch (UnsupportedEncodingException e) {
            Log.e(a, "exception: " + e);
            return 0;
        }
    }

    protected int a(String str, String str2, int i) {
        int length = str2.length();
        while (a(str2.subSequence(i, i + length).toString()) > this.b - a(str)) {
            length--;
        }
        return length;
    }

    protected int b(String str) {
        if (a(str) == 0) {
            return 0;
        }
        return this.b - (a(str) - str.length());
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        CharSequence charSequenceSubSequence;
        try {
            int iB = b(((new String() + ((Object) spanned.subSequence(0, i3))) + ((Object) charSequence.subSequence(i, i2))) + ((Object) spanned.subSequence(i4, spanned.length()))) - (spanned.length() - (i4 - i3));
            int i5 = iB >= 0 ? iB : 0;
            int iA = a(spanned.toString(), charSequence.toString(), i);
            if (i5 <= 0 && iA <= 0) {
                charSequenceSubSequence = "";
            } else if (i5 >= i2 - i) {
                charSequenceSubSequence = null;
            } else if (spanned.length() != 0 || iA > 0) {
                charSequenceSubSequence = iA <= 0 ? charSequence.subSequence(i, (charSequence.length() - 1) + i) : charSequence.subSequence(i, i + iA);
            } else {
                charSequenceSubSequence = charSequence.subSequence(i, i5 + i);
            }
            return charSequenceSubSequence;
        } catch (StringIndexOutOfBoundsException e) {
            Log.d(a, "", e);
            return "";
        }
    }
}
