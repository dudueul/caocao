package android.support.v4.view;

import android.view.MenuItem;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class MenuCompat {
    private MenuCompat() {
    }

    @Deprecated
    public static void setShowAsAction(MenuItem menuItem, int i) {
        menuItem.setShowAsAction(i);
    }
}
