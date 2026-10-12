package com.naver.glink.android.sdk.a;

import android.support.annotation.MenuRes;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.PopupMenu;
import com.naver.glink.android.sdk.R;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: PopupMenuHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class o {
    private static final String a = "PopupMenuHelper";

    public static PopupMenu a(View view, @MenuRes int i, PopupMenu.OnMenuItemClickListener onMenuItemClickListener) {
        PopupMenu popupMenu = new PopupMenu(new ContextThemeWrapper(view.getContext(), R.style.SdkTheme), view);
        popupMenu.inflate(i);
        a(popupMenu);
        popupMenu.setOnMenuItemClickListener(onMenuItemClickListener);
        popupMenu.show();
        return popupMenu;
    }

    private static void a(PopupMenu popupMenu) {
        try {
            for (Field field : popupMenu.getClass().getDeclaredFields()) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object obj = field.get(popupMenu);
                    Class.forName(obj.getClass().getName()).getMethod("setForceShowIcon", Boolean.TYPE).invoke(obj, true);
                    return;
                }
            }
        } catch (Throwable th) {
            Log.d(a, "exception raised during setForceShowIcon", th);
        }
    }
}
