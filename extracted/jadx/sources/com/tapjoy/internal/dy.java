package com.tapjoy.internal;

import android.os.AsyncTask;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class dy extends AsyncTask {
    a d;
    protected final b e;

    public interface a {
        void a();
    }

    public interface b {
        JSONObject a();

        void a(JSONObject jSONObject);
    }

    public dy(b bVar) {
        this.e = bVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        if (this.d != null) {
            this.d.a();
        }
    }
}
