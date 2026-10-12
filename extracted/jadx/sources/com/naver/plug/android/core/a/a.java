package com.naver.plug.android.core.a;

import com.google.gson.GsonBuilder;

/* JADX INFO: compiled from: BaseModel.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class a {
    public String toJsonString() {
        return new GsonBuilder().create().toJson(this);
    }

    public String toPrettyJsonString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
