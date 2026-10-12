package com.tapjoy.internal;

import android.app.RemoteInput;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class jl {
    final String a;
    final CharSequence b;
    final CharSequence[] c;
    final boolean d;
    final Bundle e;
    final Set f;

    @RequiresApi(20)
    static RemoteInput[] a(jl[] jlVarArr) {
        if (jlVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[jlVarArr.length];
        for (int i = 0; i < jlVarArr.length; i++) {
            jl jlVar = jlVarArr[i];
            remoteInputArr[i] = new RemoteInput.Builder(jlVar.a).setLabel(jlVar.b).setChoices(jlVar.c).setAllowFreeFormInput(jlVar.d).addExtras(jlVar.e).build();
        }
        return remoteInputArr;
    }
}
