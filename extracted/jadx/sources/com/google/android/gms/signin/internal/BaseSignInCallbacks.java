package com.google.android.gms.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class BaseSignInCallbacks extends ISignInCallbacks.Stub {
    @Override // com.google.android.gms.signin.internal.ISignInCallbacks
    public void onAuthAccountComplete(ConnectionResult connectionResult, AuthAccountResult authAccountResult) throws RemoteException {
    }

    @Override // com.google.android.gms.signin.internal.ISignInCallbacks
    public void onGetCurrentAccountComplete(Status status, GoogleSignInAccount googleSignInAccount) throws RemoteException {
    }

    @Override // com.google.android.gms.signin.internal.ISignInCallbacks
    public void onRecordConsentComplete(Status status) throws RemoteException {
    }

    @Override // com.google.android.gms.signin.internal.ISignInCallbacks
    public void onSaveAccountToSessionStoreComplete(Status status) throws RemoteException {
    }

    public void onSignInComplete(SignInResponse signInResponse) throws RemoteException {
    }
}
