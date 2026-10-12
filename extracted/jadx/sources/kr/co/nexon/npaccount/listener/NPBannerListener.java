package kr.co.nexon.npaccount.listener;

import android.app.Activity;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NPBannerListener {
    void onBannerClick(Activity activity, String str);

    void onDismissBanner();

    void onFailed(NXToyResult nXToyResult);
}
