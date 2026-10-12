package kr.co.nexon.toy.android.ui.util;

import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.Spanned;
import com.nexon.core.log.ToyLog;
import com.nexon.core.util.NXStringUtil;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPatternFilter implements InputFilter {
    private String patternString;

    public NXPPatternFilter(@NonNull String str) {
        this.patternString = str;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (NXStringUtil.isNull(this.patternString)) {
            ToyLog.d("patternString is null");
            return "";
        }
        ToyLog.d("In filter, source:" + ((Object) charSequence) + " start:" + i + "  end:" + i2 + "  dest:" + ((Object) spanned) + "  dStart:" + i3 + "  dend:" + i4);
        if (Pattern.compile(this.patternString).matcher(charSequence).matches()) {
            return (i3 != i4 || i3 == 0 || i3 == i4) ? charSequence : String.valueOf(charSequence.charAt(charSequence.length() - 1));
        }
        ToyLog.d("not matched source:" + ((Object) charSequence) + "  dest:" + ((Object) spanned) + "  dStart:" + i3 + "  dend:" + i4);
        return (charSequence.length() != 0 && i4 - i3 > 0) ? charSequence.subSequence(0, charSequence.length() - 1) : "";
    }
}
