package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class SignInButtonCreator extends RemoteCreator<ISignInButtonCreator> {
    private static final SignInButtonCreator zzuz = new SignInButtonCreator();

    private SignInButtonCreator() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View createView(Context context, int i, int i2) throws RemoteCreator.RemoteCreatorException {
        return zzuz.zza(context, i, i2);
    }

    private final View zza(Context context, int i, int i2) throws RemoteCreator.RemoteCreatorException {
        try {
            SignInButtonConfig signInButtonConfig = new SignInButtonConfig(i, i2, null);
            return (View) ObjectWrapper.unwrap(getRemoteCreatorInstance(context).newSignInButtonFromConfig(ObjectWrapper.wrap(context), signInButtonConfig));
        } catch (Exception e) {
            throw new RemoteCreator.RemoteCreatorException(new StringBuilder(64).append("Could not get button with size ").append(i).append(" and color ").append(i2).toString(), e);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.dynamic.RemoteCreator
    public final ISignInButtonCreator getRemoteCreator(IBinder iBinder) {
        return ISignInButtonCreator.Stub.asInterface(iBinder);
    }
}
