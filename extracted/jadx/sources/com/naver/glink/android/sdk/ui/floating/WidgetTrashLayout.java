package com.naver.glink.android.sdk.ui.floating;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.naver.glink.android.sdk.R;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class WidgetTrashLayout extends RelativeLayout {
    private static int a;
    private static volatile WidgetTrashLayout b;
    private WindowManager.LayoutParams c;
    private WindowManager d;
    private View e;
    private View f;
    private Point g;

    public WidgetTrashLayout(Context context) {
        super(context);
    }

    public WidgetTrashLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WidgetTrashLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public WidgetTrashLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public static void a() {
        if (b != null) {
            try {
                WidgetTrashLayout widgetTrashLayout = b;
                widgetTrashLayout.d.removeView(widgetTrashLayout);
                b = null;
            } catch (Exception e) {
                Log.e("glink", "[error] closeFloatingView. " + e.getMessage());
                b = null;
            }
        }
    }

    public static void a(Activity activity) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        if (b == null) {
            b = (WidgetTrashLayout) layoutInflater.inflate(R.layout.widget_trash_layout, (ViewGroup) null);
            b.b(activity);
        }
    }

    public static boolean a(int i, int i2) {
        if (b != null) {
            int[] iArr = new int[2];
            b.e.getLocationOnScreen(iArr);
            if (i >= iArr[0] - a && i <= iArr[0] + b.e.getWidth() + a && i2 >= iArr[1] - a && i2 <= iArr[1] + b.e.getHeight() + a) {
                return true;
            }
        }
        return false;
    }

    private void b(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        this.g = new Point();
        defaultDisplay.getSize(this.g);
        try {
            this.c = new WindowManager.LayoutParams();
            this.c.width = -2;
            this.c.height = -2;
            this.c.gravity = 51;
            this.c.x = (this.g.x / 2) - (getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal) / 2);
            this.c.y = getResources().getDimensionPixelSize(R.dimen.glink_trash_top_margine);
            this.c.format = -3;
            this.c.type = 1002;
            this.c.flags = 262152;
            this.d = (WindowManager) activity.getSystemService("window");
            this.d.addView(this, this.c);
            this.e = findViewById(R.id.btn_trash);
            this.f = findViewById(R.id.trash_background);
            this.f.setVisibility(4);
            a = getResources().getDimensionPixelSize(R.dimen.glink_widget_size_normal) / 2;
        } catch (Exception e) {
            Log.e("glink", "[error] addToWindow. " + e.getMessage());
            b = null;
        }
    }

    public static boolean b() {
        return b != null;
    }

    public static void setBackgroundVisible(int i) {
        if (b != null) {
            b.f.setVisibility(i);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b = null;
    }
}
