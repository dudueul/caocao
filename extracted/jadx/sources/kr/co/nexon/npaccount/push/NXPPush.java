package kr.co.nexon.npaccount.push;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.nexon.core.android.NXPActivityLifecycleCallbackAdapter;
import com.nexon.core.android.NXPActivityLifecycleCallbackManager;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import kr.co.nexon.mdev.android.util.NXActivityUtil;
import kr.co.nexon.npaccount.gcm.NPFcmUtil;
import kr.co.nexon.npaccount.gcm.NXPBaseFcmMessagingService;
import kr.co.nexon.npaccount.push.interfaces.NXPPushFactory;
import kr.co.nexon.npaccount.push.interfaces.NXPPushService;
import kr.co.nexon.npaccount.push.v1.NXPPushFactoryV1;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPush {
    public static final int VERSION1 = 1;
    public static final int VERSION2 = 2;
    private static Context context;
    private static boolean isInitialized = false;
    private static Application.ActivityLifecycleCallbacks lifecycleCallback = null;
    private static String pushKey;

    private NXPPush() {
    }

    private static NXPPushFactory createPushFactory() {
        switch (getPushServiceVersion()) {
            case 1:
                return new NXPPushFactoryV1();
            case 2:
                return new NXPPushFactoryV2();
            default:
                return new NXPPushFactoryV1();
        }
    }

    public static void getPolicy(Context context2, NPListener nPListener) {
        if (!isInitialized) {
            initialize(context2);
        }
        createPushFactory().createSetting().getPolicy(context2, nPListener);
    }

    public static int getPushServiceVersion() {
        return NXPApplicationConfigManager.getInstance().getPushVersion();
    }

    public static void initialize(@NonNull Context context2) {
        context = context2.getApplicationContext();
        if (lifecycleCallback == null) {
            lifecycleCallback = new NXPActivityLifecycleCallbackAdapter() { // from class: kr.co.nexon.npaccount.push.NXPPush.1
                @Override // com.nexon.core.android.NXPActivityLifecycleCallbackAdapter, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                    ToyLog.d("onActivityStarted activity:" + activity);
                    String luancherActivityName = NXActivityUtil.getLuancherActivityName(activity);
                    String name = activity.getClass().getName();
                    if (name.equals(luancherActivityName)) {
                        NXPPush.onNewIntent(activity.getIntent());
                    } else {
                        ToyLog.d("activity Name:" + name + "  launcherActivityName:" + luancherActivityName);
                    }
                }
            };
            NXPActivityLifecycleCallbackManager.getInstance().addActivityLifecycleCallbacks(lifecycleCallback);
        }
        isInitialized = true;
    }

    public static boolean isIsInitialized() {
        return isInitialized;
    }

    public static void onNewIntent(Intent intent) {
        if (intent == null) {
            ToyLog.d("intent extra is null");
            return;
        }
        Bundle bundleExtra = intent.getBundleExtra(NXPBaseFcmMessagingService.TOY_PUSH_CLICK_EVENT_EXTRA);
        intent.putExtra(NXPBaseFcmMessagingService.TOY_PUSH_CLICK_EVENT_EXTRA, new Bundle());
        ToyLog.d("intent extra :" + bundleExtra + " , Context:" + context);
        if (bundleExtra == null || bundleExtra.isEmpty()) {
            ToyLog.d("skipped the onActivityStarted event.");
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (new NPFcmUtil().getValidNotificationCount(notificationManager, 1) == 0) {
            notificationManager.cancel(1);
        }
        NXPPushService nXPPushServiceCreateService = createPushFactory().createService();
        ToyLog.d("push click event handled");
        nXPPushServiceCreateService.handlePushClickEvent(context, bundleExtra);
    }

    public static void onPushReceive(Context context2, Bundle bundle, boolean z) {
        if (!isInitialized) {
            initialize(context2);
        }
        createPushFactory().createService().handlePushReceiveEvent(context2, bundle, z);
    }

    public static void registerPush(Context context2, String str) {
        if (!isInitialized) {
            initialize(context2);
        }
        pushKey = str;
        createPushFactory().createService().registerPush(context2, str);
    }

    public static void setPolicy(Context context2, NXPPushPolicies nXPPushPolicies, NPListener nPListener) {
        if (!isInitialized) {
            initialize(context2);
        }
        createPushFactory().createSetting().setPolicy(context2, nXPPushPolicies, pushKey, nPListener);
    }

    public static void showPushSettingDialog(@NonNull Activity activity, NXPPushMenuOption nXPPushMenuOption, NPListener nPListener) {
        if (!isInitialized) {
            initialize(activity);
        }
        NXPPushFactory nXPPushFactoryCreatePushFactory = createPushFactory();
        nXPPushFactoryCreatePushFactory.createMenu().showMenu(activity, nXPPushFactoryCreatePushFactory.createSetting(), nXPPushMenuOption, nPListener);
    }
}
