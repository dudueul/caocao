package kr.co.nexon.android.sns.daum;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class NPDaumOAuthLoginHandler extends Handler {
    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        run(message.what, (Uri) message.obj);
    }

    public abstract void run(int i, Uri uri);
}
