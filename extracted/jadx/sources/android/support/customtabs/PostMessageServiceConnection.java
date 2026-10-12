package android.support.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class PostMessageServiceConnection implements ServiceConnection {
    private final Object mLock = new Object();
    private IPostMessageService mService;
    private final ICustomTabsCallback mSessionBinder;

    public PostMessageServiceConnection(CustomTabsSessionToken customTabsSessionToken) {
        this.mSessionBinder = ICustomTabsCallback.Stub.asInterface(customTabsSessionToken.getCallbackBinder());
    }

    public boolean bindSessionToPostMessageService(Context context, String str) {
        Intent intent = new Intent();
        intent.setClassName(str, PostMessageService.class.getName());
        return context.bindService(intent, this, 1);
    }

    public final boolean notifyMessageChannelReady(Bundle bundle) {
        boolean z = false;
        if (this.mService != null) {
            synchronized (this.mLock) {
                try {
                    this.mService.onMessageChannelReady(this.mSessionBinder, bundle);
                } catch (RemoteException e) {
                }
            }
            z = true;
        }
        return z;
    }

    public void onPostMessageServiceConnected() {
    }

    public void onPostMessageServiceDisconnected() {
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mService = IPostMessageService.Stub.asInterface(iBinder);
        onPostMessageServiceConnected();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.mService = null;
        onPostMessageServiceDisconnected();
    }

    public final boolean postMessage(String str, Bundle bundle) {
        boolean z = false;
        if (this.mService != null) {
            synchronized (this.mLock) {
                try {
                    this.mService.onPostMessage(this.mSessionBinder, str, bundle);
                } catch (RemoteException e) {
                }
            }
            z = true;
        }
        return z;
    }

    public void unbindFromContext(Context context) {
        context.unbindService(this);
    }
}
