package android.support.v7.widget;

import android.graphics.Rect;
import android.support.annotation.RestrictTo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface FitWindowsViewGroup {

    public interface OnFitSystemWindowsListener {
        void onFitSystemWindows(Rect rect);
    }

    void setOnFitSystemWindowsListener(OnFitSystemWindowsListener onFitSystemWindowsListener);
}
