package com.nexon.ngsm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Ngsm {
    static final String VERSION = "3.0.0";
    private static Ngsm m_Instance = new Ngsm();
    private Activity m_Activity;
    private Context m_Context;
    private Lock m_Lock = new ReentrantLock();
    private boolean m_bExit = false;
    private OnInitListener m_initListener;
    private OnRunListener m_runListener;

    public interface OnAbuseListener {
        void onAbuseDetected(int i, String str);
    }

    public interface OnInitListener {
        void onInit(NgsmResult ngsmResult);
    }

    public interface OnRunListener {
        void onRun(NgsmResult ngsmResult);
    }

    public Ngsm() {
        System.loadLibrary("ngsm");
    }

    private void ShowMsgBox(final String str, boolean z) {
        this.m_bExit = z;
        this.m_Activity.runOnUiThread(new Runnable() { // from class: com.nexon.ngsm.Ngsm.2
            @Override // java.lang.Runnable
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Ngsm.this.m_Activity);
                String str2 = str;
                if (str2.length() == 0) {
                    str2 = "SecurityEvent";
                }
                builder.setMessage(str2).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.nexon.ngsm.Ngsm.2.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (Ngsm.this.m_bExit) {
                            Ngsm.this.finishGame();
                        }
                        try {
                            Ngsm.this.m_Lock.unlock();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (Ngsm.this.m_Lock.tryLock()) {
                    builder.show();
                }
            }
        });
    }

    private boolean ShowMsgBoxFromGame(int i, String str, String str2) {
        try {
            this.m_Activity.getClass().getMethod(str2, Integer.TYPE, String.class).invoke(this.m_Activity, Integer.valueOf(i), str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishGame() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(268435456);
        this.m_Activity.startActivity(intent);
        new Thread(new Runnable() { // from class: com.nexon.ngsm.Ngsm.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Process.killProcess(Process.myPid());
            }
        }).start();
    }

    public static Ngsm getInst() {
        return m_Instance;
    }

    private native void ngsmNativeInit(Activity activity, Context context, Ngsm ngsm, int i);

    private native void ngsmNativePause();

    private native void ngsmNativeResume();

    private native void ngsmNativeRun(String str, String str2);

    private native String ngsmNativeVersion();

    public String getNativeVersion() {
        return ngsmNativeVersion();
    }

    public String getVersion() {
        return VERSION;
    }

    public void ngsmInit(Activity activity, int i, OnInitListener onInitListener) {
        if (activity == null || i == 0) {
            if (onInitListener != null) {
                onInitListener.onInit(new NgsmResult(1, "invalid parameters."));
                return;
            }
            return;
        }
        this.m_Activity = activity;
        this.m_Context = activity.getBaseContext();
        this.m_initListener = onInitListener;
        Log.i("NGSM", "NGSM_SDK_VERSION : Ngsm_AOS_v3.0.0");
        final String strNgsmNativeVersion = ngsmNativeVersion();
        if (strNgsmNativeVersion.contains("_dbg")) {
            this.m_Activity.runOnUiThread(new Runnable() { // from class: com.nexon.ngsm.Ngsm.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Toast.makeText(Ngsm.this.m_Context, "protected by NGSM v" + strNgsmNativeVersion, 1).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        ngsmNativeInit(this.m_Activity, this.m_Context, m_Instance, i);
    }

    public void ngsmLoad() {
        Log.i("NGSM", "NGSM_LOAD : Ngsm_AOS_v3.0.0");
    }

    public native boolean ngsmNativeAddSecuData(long j, long j2);

    public native boolean ngsmNativeAddSecuDataDouble(long j, double d);

    public native boolean ngsmNativeAddSecuDataFloat(long j, float f);

    public native long ngsmNativeCreateSecuData(int i);

    public native long ngsmNativeGetSecuData(long j);

    public native double ngsmNativeGetSecuDataDouble(long j);

    public native float ngsmNativeGetSecuDataFloat(long j);

    public native void ngsmNativeReleaseSecuData(long j);

    public native void ngsmNativeReleaseSecuDataAll();

    public native boolean ngsmNativeSetSecuData(long j, long j2);

    public native boolean ngsmNativeSetSecuDataDouble(long j, double d);

    public native boolean ngsmNativeSetSecuDataFloat(long j, float f);

    public native boolean ngsmNativeSubSecuData(long j, long j2);

    public native boolean ngsmNativeSubSecuDataDouble(long j, double d);

    public native boolean ngsmNativeSubSecuDataFloat(long j, float f);

    public void ngsmPause() {
        ngsmNativePause();
    }

    public void ngsmResume() {
        ngsmNativeResume();
    }

    public void ngsmRun(String str, String str2, OnRunListener onRunListener) {
        if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty()) {
            this.m_runListener = onRunListener;
            ngsmNativeRun(str, str2);
        } else if (onRunListener != null) {
            onRunListener.onRun(new NgsmResult(1, "invalid parameters."));
        }
    }

    public void onAbuseDetect(int i, String str, boolean z) {
        if ((this.m_Activity instanceof OnAbuseListener) && ShowMsgBoxFromGame(i, str, "onAbuseDetected")) {
            return;
        }
        ShowMsgBox(str, z);
    }

    public void onInitComplete(int i) {
        if (this.m_initListener != null) {
            this.m_initListener.onInit(new NgsmResult(i, "ngsm initialize fail."));
        }
    }

    public void onRunComplete(int i) {
        if (this.m_runListener != null) {
            this.m_runListener.onRun(new NgsmResult(i, "ngsm running fail."));
        }
    }
}
