package com.nexon.nexonanalyticssdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxMsgHandlerThread extends Thread {
    public static final int REQUEST_ERROR_LOG = 3;
    public static final int REQUEST_INITIALIZE = 1;
    public static final int REQUEST_INITIALIZE_TEST = 6;
    public static final int REQUEST_LOG = 4;
    public static final int REQUEST_SET_USER = 5;
    public static final int REQUEST_STAGE_LOG = 2;
    private Handler msgHandler;
    private Lock handlerLock = new ReentrantLock();
    private Condition condition = this.handlerLock.newCondition();
    private NxLogManager logManager = new NxLogManager();

    NxMsgHandlerThread() {
    }

    Handler getNxMsgHandler() {
        return this.msgHandler;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.msgHandler = new Handler(Looper.myLooper()) { // from class: com.nexon.nexonanalyticssdk.NxMsgHandlerThread.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Map<String, Object> map = (Map) message.obj;
                switch (message.what) {
                    case 1:
                        NxMsgHandlerThread.this.logManager.initialize(map);
                        break;
                    case 2:
                        NxMsgHandlerThread.this.logManager.enqueueStageLog(map);
                        break;
                    case 3:
                        NxMsgHandlerThread.this.logManager.enqueueErrorLog(map);
                        break;
                    case 4:
                        NxMsgHandlerThread.this.logManager.enqueueLog(map);
                        break;
                    case 5:
                        NxMsgHandlerThread.this.logManager.setUserInfo(map);
                        break;
                    case 6:
                        NxMsgHandlerThread.this.logManager.initialize_TEST(map);
                        break;
                }
            }
        };
        this.handlerLock.lock();
        try {
            this.condition.signalAll();
            this.handlerLock.unlock();
            Looper.loop();
        } catch (Throwable th) {
            this.handlerLock.unlock();
            throw th;
        }
    }

    void startAndWait() {
        start();
        this.handlerLock.lock();
        try {
            if (this.msgHandler == null) {
                this.condition.await();
            }
        } catch (InterruptedException e) {
            NxLogcat.e("startAndWait : " + e.getMessage());
        } finally {
            this.handlerLock.unlock();
        }
    }
}
