package com.naver.glink.android.sdk.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class SmallTouchView extends LinearLayout {
    private static int a = 30;
    private float b;
    private float c;
    private a d;

    public interface a {
        void a();
    }

    public SmallTouchView(Context context) {
        super(context);
        setOnHomeBannerTouchEvent(this);
    }

    public SmallTouchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnHomeBannerTouchEvent(this);
    }

    public SmallTouchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOnHomeBannerTouchEvent(this);
    }

    public void setOnHomeBannerTouchEvent(View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.widget.SmallTouchView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.naver.glink.android.sdk.ui.widget.SmallTouchView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                switch (motionEvent.getAction() & 255) {
                    case 0:
                        SmallTouchView.this.b = motionEvent.getX();
                        SmallTouchView.this.c = motionEvent.getY();
                        break;
                    case 1:
                        if (Math.abs(motionEvent.getX() - SmallTouchView.this.b) < SmallTouchView.a && Math.abs(motionEvent.getY() - SmallTouchView.this.c) < SmallTouchView.a && SmallTouchView.this.d != null) {
                            SmallTouchView.this.d.a();
                            break;
                        }
                        break;
                }
                return false;
            }
        });
    }

    public void setSmallClickListener(a aVar) {
        this.d = aVar;
    }
}
