package com.tapjoy.internal;

import android.view.View;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface di {

    public interface a {
        void a(View view, di diVar, JSONObject jSONObject);
    }

    JSONObject a(View view);

    void a(View view, JSONObject jSONObject, a aVar, boolean z);
}
