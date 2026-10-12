package kr.co.nexon.mdev.android.work;

import android.os.AsyncTask;
import android.os.Handler;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class NXAsyncTask<T> extends AsyncTask<Object, Integer, T> {
    protected int errorCode = 0;
    protected String errorMessage = "";
    protected Handler handler;

    public NXAsyncTask(Handler handler) {
        this.handler = handler;
    }
}
