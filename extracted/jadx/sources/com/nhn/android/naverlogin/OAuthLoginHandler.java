package com.nhn.android.naverlogin;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class OAuthLoginHandler extends Handler {
    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        run(message.what == 1);
    }

    public abstract void run(boolean z);
}
