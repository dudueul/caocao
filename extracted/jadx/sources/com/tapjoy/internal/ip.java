package com.tapjoy.internal;

import android.content.Context;
import android.view.ViewGroup;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ip extends ai {
    private final hu a;
    private final iq b;
    private aa c;

    public ip(Context context, hu huVar, iq iqVar) {
        super(context);
        this.a = huVar;
        this.b = iqVar;
        addView(iqVar, new ViewGroup.LayoutParams(-1, -1));
        this.c = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tapjoy.internal.ai, com.tapjoy.internal.aj, android.view.View
    public final void onMeasure(int i, int i2) {
        aa aaVar;
        aa aaVarA = aa.a(getContext());
        if (!this.a.a()) {
            aaVar = aa.LANDSCAPE;
            if (!aaVarA.a()) {
                setRotationCount(0);
            } else if (aaVarA.c() == 3) {
                setRotationCount(1);
            } else {
                setRotationCount(1);
            }
        } else if (this.a.b()) {
            if (aaVarA.a()) {
                aaVar = aa.PORTRAIT;
            } else {
                aaVar = (aaVarA.b() || !aa.b(getContext()).a()) ? aa.LANDSCAPE : aa.PORTRAIT;
            }
            setRotationCount(0);
        } else {
            aaVar = aa.PORTRAIT;
            if (!aaVarA.b()) {
                setRotationCount(0);
            } else if (aaVarA.c() == 3) {
                setRotationCount(1);
            } else {
                setRotationCount(3);
            }
        }
        if (this.c != aaVar) {
            this.c = aaVar;
            this.b.setLandscape(this.c.b());
        }
        super.onMeasure(i, i2);
    }
}
