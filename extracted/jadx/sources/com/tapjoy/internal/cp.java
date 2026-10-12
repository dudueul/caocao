package com.tapjoy.internal;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class cp extends ContentObserver {
    public final Context a;
    public float b;
    private final AudioManager c;
    private final cm d;
    private final co e;

    public cp(Handler handler, Context context, cm cmVar, co coVar) {
        super(handler);
        this.a = context;
        this.c = (AudioManager) context.getSystemService("audio");
        this.d = cmVar;
        this.e = coVar;
    }

    public final float a() {
        int streamVolume = this.c.getStreamVolume(3);
        int streamMaxVolume = this.c.getStreamMaxVolume(3);
        if (streamMaxVolume <= 0 || streamVolume <= 0) {
            return 0.0f;
        }
        float f = streamVolume / streamMaxVolume;
        if (f <= 1.0f) {
            return f;
        }
        return 1.0f;
    }

    public final void b() {
        this.e.a(this.b);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        float fA = a();
        if (fA != this.b) {
            this.b = fA;
            b();
        }
    }
}
