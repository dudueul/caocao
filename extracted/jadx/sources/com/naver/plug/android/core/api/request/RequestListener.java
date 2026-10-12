package com.naver.plug.android.core.api.request;

import android.support.annotation.NonNull;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.Response;
import java.util.UUID;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class RequestListener<T extends Response> {
    final String uuid = UUID.randomUUID().toString();

    public void onFailure(@NonNull PlugError plugError) {
    }

    public void onFinally(T t, PlugError plugError) {
    }

    public void onSuccess(@NonNull T t) {
    }
}
