package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface DrawableWrapper {
    Drawable getWrappedDrawable();

    void setWrappedDrawable(Drawable drawable);
}
