package com.google.android.gms.auth.api.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzi {
    private static Logger zzer = new Logger("GoogleSignInCommon", new String[0]);

    @Nullable
    public static GoogleSignInResult getSignInResultFromIntent(Intent intent) {
        if (intent == null || !(intent.hasExtra("googleSignInStatus") || intent.hasExtra("googleSignInAccount"))) {
            return null;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) intent.getParcelableExtra("googleSignInAccount");
        Status status = (Status) intent.getParcelableExtra("googleSignInStatus");
        if (googleSignInAccount != null) {
            status = Status.RESULT_SUCCESS;
        }
        return new GoogleSignInResult(googleSignInAccount, status);
    }

    public static Intent zzd(Context context, GoogleSignInOptions googleSignInOptions) {
        zzer.d("getSignInIntent()", new Object[0]);
        SignInConfiguration signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setPackage(context.getPackageName());
        intent.setClass(context, SignInHubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("config", signInConfiguration);
        intent.putExtra("config", bundle);
        return intent;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0097  */
    public static OptionalPendingResult<GoogleSignInResult> zzd(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions, boolean z) {
        GoogleSignInResult googleSignInResult;
        boolean zEquals;
        GoogleSignInAccount googleSignInAccountZzo;
        zzer.d("silentSignIn()", new Object[0]);
        zzer.d("getEligibleSavedSignInResult()", new Object[0]);
        Preconditions.checkNotNull(googleSignInOptions);
        GoogleSignInOptions googleSignInOptionsZzp = zzq.zze(context).zzp();
        if (googleSignInOptionsZzp != null) {
            Account account = googleSignInOptionsZzp.getAccount();
            Account account2 = googleSignInOptions.getAccount();
            if (account == null) {
                zEquals = account2 == null;
            } else {
                zEquals = account.equals(account2);
            }
            if (!zEquals || googleSignInOptions.isServerAuthCodeRequested() || ((googleSignInOptions.isIdTokenRequested() && !(googleSignInOptionsZzp.isIdTokenRequested() && googleSignInOptions.getServerClientId().equals(googleSignInOptionsZzp.getServerClientId()))) || !new HashSet(googleSignInOptionsZzp.getScopes()).containsAll(new HashSet(googleSignInOptions.getScopes())) || (googleSignInAccountZzo = zzq.zze(context).zzo()) == null || googleSignInAccountZzo.isExpired())) {
                googleSignInResult = null;
            } else {
                googleSignInResult = new GoogleSignInResult(googleSignInAccountZzo, Status.RESULT_SUCCESS);
            }
        } else {
            googleSignInResult = null;
        }
        if (googleSignInResult != null) {
            zzer.d("Eligible saved sign in result found", new Object[0]);
            return PendingResults.immediatePendingResult(googleSignInResult, googleApiClient);
        }
        if (z) {
            return PendingResults.immediatePendingResult(new GoogleSignInResult(null, new Status(4)), googleApiClient);
        }
        zzer.d("trySilentSignIn()", new Object[0]);
        return new OptionalPendingResultImpl(googleApiClient.enqueue(new zzj(googleApiClient, context, googleSignInOptions)));
    }

    public static PendingResult<Status> zzd(GoogleApiClient googleApiClient, Context context, boolean z) {
        zzer.d("Signing out", new Object[0]);
        zzd(context);
        return z ? PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient) : googleApiClient.execute(new zzl(googleApiClient));
    }

    private static void zzd(Context context) {
        zzq.zze(context).clear();
        Iterator<GoogleApiClient> it = GoogleApiClient.getAllClients().iterator();
        while (it.hasNext()) {
            it.next().maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
    }

    public static Intent zze(Context context, GoogleSignInOptions googleSignInOptions) {
        zzer.d("getFallbackSignInIntent()", new Object[0]);
        Intent intentZzd = zzd(context, googleSignInOptions);
        intentZzd.setAction("com.google.android.gms.auth.APPAUTH_SIGN_IN");
        return intentZzd;
    }

    public static PendingResult<Status> zze(GoogleApiClient googleApiClient, Context context, boolean z) {
        zzer.d("Revoking access", new Object[0]);
        String savedRefreshToken = Storage.getInstance(context).getSavedRefreshToken();
        zzd(context);
        return z ? zze.zzg(savedRefreshToken) : googleApiClient.execute(new zzn(googleApiClient));
    }

    public static Intent zzf(Context context, GoogleSignInOptions googleSignInOptions) {
        zzer.d("getNoImplementationSignInIntent()", new Object[0]);
        Intent intentZzd = zzd(context, googleSignInOptions);
        intentZzd.setAction("com.google.android.gms.auth.NO_IMPL");
        return intentZzd;
    }
}
