package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyConstants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ge {
    private static final ge e;
    private static ge f;
    public Boolean a = null;
    public String b = null;
    public Boolean c = null;
    public boolean d = false;
    private Context g;

    static {
        ge geVar = new ge();
        e = geVar;
        f = geVar;
    }

    public static ge a() {
        return f;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0036 A[Catch: all -> 0x007d, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0009, B:9:0x000f, B:11:0x001d, B:13:0x0025, B:14:0x0032, B:16:0x0036, B:17:0x0040, B:19:0x0044, B:21:0x004c, B:22:0x0059, B:24:0x005d, B:26:0x0066, B:28:0x006a, B:29:0x006d, B:31:0x0071, B:32:0x0074, B:34:0x0078), top: B:40:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:24:0x005d A[Catch: all -> 0x007d, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0009, B:9:0x000f, B:11:0x001d, B:13:0x0025, B:14:0x0032, B:16:0x0036, B:17:0x0040, B:19:0x0044, B:21:0x004c, B:22:0x0059, B:24:0x005d, B:26:0x0066, B:28:0x006a, B:29:0x006d, B:31:0x0071, B:32:0x0074, B:34:0x0078), top: B:40:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:26:0x0066 A[Catch: all -> 0x007d, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0009, B:9:0x000f, B:11:0x001d, B:13:0x0025, B:14:0x0032, B:16:0x0036, B:17:0x0040, B:19:0x0044, B:21:0x004c, B:22:0x0059, B:24:0x005d, B:26:0x0066, B:28:0x006a, B:29:0x006d, B:31:0x0071, B:32:0x0074, B:34:0x0078), top: B:40:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x006a A[Catch: all -> 0x007d, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0009, B:9:0x000f, B:11:0x001d, B:13:0x0025, B:14:0x0032, B:16:0x0036, B:17:0x0040, B:19:0x0044, B:21:0x004c, B:22:0x0059, B:24:0x005d, B:26:0x0066, B:28:0x006a, B:29:0x006d, B:31:0x0071, B:32:0x0074, B:34:0x0078), top: B:40:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x0071 A[Catch: all -> 0x007d, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0009, B:9:0x000f, B:11:0x001d, B:13:0x0025, B:14:0x0032, B:16:0x0036, B:17:0x0040, B:19:0x0044, B:21:0x004c, B:22:0x0059, B:24:0x005d, B:26:0x0066, B:28:0x006a, B:29:0x006d, B:31:0x0071, B:32:0x0074, B:34:0x0078), top: B:40:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x0078 A[Catch: all -> 0x007d, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0009, B:9:0x000f, B:11:0x001d, B:13:0x0025, B:14:0x0032, B:16:0x0036, B:17:0x0040, B:19:0x0044, B:21:0x004c, B:22:0x0059, B:24:0x005d, B:26:0x0066, B:28:0x006a, B:29:0x006d, B:31:0x0071, B:32:0x0074, B:34:0x0078), top: B:40:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:9:0x000f A[Catch: all -> 0x007d, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0009, B:9:0x000f, B:11:0x001d, B:13:0x0025, B:14:0x0032, B:16:0x0036, B:17:0x0040, B:19:0x0044, B:21:0x004c, B:22:0x0059, B:24:0x005d, B:26:0x0066, B:28:0x006a, B:29:0x006d, B:31:0x0071, B:32:0x0074, B:34:0x0078), top: B:40:0x0003 }] */
    public final synchronized void a(Context context) {
        ge geVar;
        ge geVar2;
        SharedPreferences sharedPreferences;
        if (context != null) {
            if (this.g == null) {
                this.g = context;
            }
            geVar = f;
            if (geVar.g != null) {
                sharedPreferences = geVar.g.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
                if (geVar.a == null && sharedPreferences.contains("gdpr")) {
                    geVar.a = Boolean.valueOf(sharedPreferences.getBoolean("gdpr", false));
                }
                if (geVar.b == null) {
                    geVar.b = sharedPreferences.getString("cgdpr", "");
                }
                if (geVar.c == null && sharedPreferences.contains(TapjoyConstants.PREF_BELOW_CONSENT_AGE)) {
                    geVar.c = Boolean.valueOf(sharedPreferences.getBoolean(TapjoyConstants.PREF_BELOW_CONSENT_AGE, false));
                }
            }
            if (this.d) {
                this.d = false;
                geVar2 = f;
                if (geVar2.g != null) {
                    if (geVar2.a != null) {
                        geVar2.b();
                    }
                    if (geVar2.b != null) {
                        geVar2.c();
                    }
                    if (geVar2.c != null) {
                        geVar2.d();
                    }
                }
            }
        } else {
            geVar = f;
            if (geVar.g != null) {
                sharedPreferences = geVar.g.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
                if (geVar.a == null) {
                    geVar.a = Boolean.valueOf(sharedPreferences.getBoolean("gdpr", false));
                }
                if (geVar.b == null) {
                    geVar.b = sharedPreferences.getString("cgdpr", "");
                }
                if (geVar.c == null) {
                    geVar.c = Boolean.valueOf(sharedPreferences.getBoolean(TapjoyConstants.PREF_BELOW_CONSENT_AGE, false));
                }
            }
            if (this.d) {
                this.d = false;
                geVar2 = f;
                if (geVar2.g != null) {
                    if (geVar2.a != null) {
                        geVar2.b();
                    }
                    if (geVar2.b != null) {
                        geVar2.c();
                    }
                    if (geVar2.c != null) {
                        geVar2.d();
                    }
                }
            }
        }
        throw th;
    }

    public final boolean b() {
        if (this.g == null) {
            return false;
        }
        SharedPreferences.Editor editorEdit = this.g.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).edit();
        editorEdit.putBoolean("gdpr", this.a.booleanValue());
        editorEdit.apply();
        return true;
    }

    public final boolean c() {
        if (this.g == null) {
            return false;
        }
        SharedPreferences.Editor editorEdit = this.g.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).edit();
        editorEdit.putString("cgdpr", this.b);
        editorEdit.apply();
        return true;
    }

    public final boolean d() {
        if (this.g == null) {
            return false;
        }
        SharedPreferences.Editor editorEdit = this.g.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).edit();
        editorEdit.putBoolean(TapjoyConstants.PREF_BELOW_CONSENT_AGE, this.c.booleanValue());
        editorEdit.apply();
        TapjoyConnectCore.setAdTrackingEnabled();
        return true;
    }
}
