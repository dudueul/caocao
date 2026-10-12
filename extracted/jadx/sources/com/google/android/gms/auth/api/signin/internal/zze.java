package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zze implements Runnable {
    private static final Logger zzer = new Logger("RevokeAccessOperation", new String[0]);
    private final StatusPendingResult zzes;
    private final String zzz;

    private zze(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzz = str;
        this.zzes = new StatusPendingResult((GoogleApiClient) null);
    }

    public static PendingResult<Status> zzg(String str) {
        if (str == null) {
            return PendingResults.immediateFailedResult(new Status(4), null);
        }
        zze zzeVar = new zze(str);
        new Thread(zzeVar).start();
        return zzeVar.zzes;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Exception e;
        IOException e2;
        Status status;
        Status status2 = Status.RESULT_INTERNAL_ERROR;
        try {
            String strValueOf = String.valueOf("https://accounts.google.com/o/oauth2/revoke?token=");
            String strValueOf2 = String.valueOf(this.zzz);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf)).openConnection();
            httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_FORM);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                status = Status.RESULT_SUCCESS;
            } else {
                zzer.e("Unable to revoke access!", new Object[0]);
                status = status2;
            }
            try {
                zzer.d(new StringBuilder(26).append("Response Code: ").append(responseCode).toString(), new Object[0]);
            } catch (IOException e3) {
                e2 = e3;
                status2 = status;
                Logger logger = zzer;
                String strValueOf3 = String.valueOf(e2.toString());
                logger.e(strValueOf3.length() != 0 ? "IOException when revoking access: ".concat(strValueOf3) : new String("IOException when revoking access: "), new Object[0]);
                status = status2;
            } catch (Exception e4) {
                e = e4;
                status2 = status;
                Logger logger2 = zzer;
                String strValueOf4 = String.valueOf(e.toString());
                logger2.e(strValueOf4.length() != 0 ? "Exception when revoking access: ".concat(strValueOf4) : new String("Exception when revoking access: "), new Object[0]);
                status = status2;
            }
        } catch (IOException e5) {
            e2 = e5;
        } catch (Exception e6) {
            e = e6;
        }
        this.zzes.setResult(status);
    }
}
