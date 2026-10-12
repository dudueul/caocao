package kr.co.nexon.npaccount.push.interfaces;

import android.app.Activity;
import android.support.annotation.NonNull;
import kr.co.nexon.npaccount.push.NXPPushMenuOption;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXPPushMenu {
    void showMenu(@NonNull Activity activity, @NonNull NXPPushSetting nXPPushSetting, NXPPushMenuOption nXPPushMenuOption, NPListener nPListener);
}
