package kr.co.nexon.mdev.android.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.facebook.GraphResponse;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.io.IOException;
import kr.co.nexon.npaccount.auth.result.NXToyAdIdResult;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXAdsUtil {
    public static void getAdvertisingId(final Context context, final NPListener nPListener) {
        final Handler handler = new Handler(Looper.getMainLooper());
        Thread thread = new Thread(new Runnable() { // from class: kr.co.nexon.mdev.android.util.NXAdsUtil.1
            @Override // java.lang.Runnable
            public void run() {
                String id = null;
                boolean zIsLimitAdTrackingEnabled = true;
                int code = NXToyErrorCode.GOOGLE_GET_ADVERTISING_FAILED.getCode();
                String str = "";
                try {
                    AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                    if (advertisingIdInfo != null) {
                        id = advertisingIdInfo.getId();
                        zIsLimitAdTrackingEnabled = advertisingIdInfo.isLimitAdTrackingEnabled();
                        code = 0;
                        str = GraphResponse.SUCCESS_KEY;
                    }
                } catch (GooglePlayServicesNotAvailableException e) {
                    ToyLog.d("google play service not available!!");
                    str = "google play service not available!!";
                } catch (GooglePlayServicesRepairableException e2) {
                    ToyLog.d("Google Play Services is not installed, up-to-date, or enabled");
                    str = "Google Play Services is not installed, up-to-date, or enabled";
                } catch (IOException e3) {
                    ToyLog.d("getAdvertisingId IOException:" + e3.getMessage());
                    str = "getAdvertisingId IOException :" + e3.getMessage();
                } catch (Exception e4) {
                    ToyLog.d("getAdvertisingId Exception :" + e4.getMessage());
                    str = "getAdvertisingId Exception :" + e4.getMessage();
                }
                if (nPListener != null) {
                    final NXToyAdIdResult nXToyAdIdResult = new NXToyAdIdResult();
                    nXToyAdIdResult.requestTag = NXToyRequestTag.GetAdId.getValue();
                    nXToyAdIdResult.errorCode = code;
                    nXToyAdIdResult.errorText = str;
                    nXToyAdIdResult.errorDetail = str;
                    nXToyAdIdResult.result.adId = id;
                    nXToyAdIdResult.result.isLimitAdTrackingEnabled = zIsLimitAdTrackingEnabled;
                    handler.postAtFrontOfQueue(new Runnable() { // from class: kr.co.nexon.mdev.android.util.NXAdsUtil.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            nPListener.onResult(nXToyAdIdResult);
                        }
                    });
                }
            }
        });
        thread.setPriority(10);
        thread.start();
    }

    public static void getAdvertisingIdAfterCaching(Context context, final NPListener nPListener) {
        getAdvertisingId(context, new NPListener() { // from class: kr.co.nexon.mdev.android.util.NXAdsUtil.2
            @Override // kr.co.nexon.toy.listener.NPListener
            public void onResult(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXToyCommonPreferenceController.getInstance().setAdvertisingId(((NXToyAdIdResult) nXToyResult).result.adId);
                }
                if (nPListener != null) {
                    nPListener.onResult(nXToyResult);
                }
            }
        });
    }

    public static String getCachedAdvertisingId() {
        return NXToyCommonPreferenceController.getInstance().getAdvertisingId();
    }
}
