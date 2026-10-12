package kr.co.nexon.android.sns.email.util;

import android.content.Context;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import kr.co.nexon.mdev.android.util.NXLocalizedString;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPLocalizedStringWrapper {
    private Context context;

    public NPLocalizedStringWrapper(Context context) {
        this.context = context;
    }

    public String getString(int i) {
        return NXLocalizedString.getText(this.context, NXToyCommonPreferenceController.getInstance().getLocale(), i);
    }
}
