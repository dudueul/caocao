package kr.co.nexon.npaccount.gcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nexon.core.android.NXPActivityLifecycleCallbackManager;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.util.NXStringUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kr.co.nexon.mdev.android.util.NXApplicationUtil;
import kr.co.nexon.npaccount.jnisupport.NXPUnity;
import kr.co.nexon.npaccount.jnisupport.NXPUnreal;
import kr.co.nexon.npaccount.push.NXPPush;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPBaseFcmMessagingService extends FirebaseMessagingService {
    public static final String KEY_FORCE = "force";
    public static final String KEY_LOCAL_MESSAGE = "message";
    public static final String KEY_MESSAGE = "body";
    public static final String KEY_META = "meta";
    public static final String KEY_NOTIFICATION_SUMMARY_CONTENT = "com.nexon.nxgcmnoti.summary";
    public static final String KEY_NOTI_COLOR = "com.nexon.nxgcmnoti.color";
    public static final String KEY_NOTI_GROUP = "com.nexon.nxgcmnoti.group";
    public static final String KEY_NOTI_ICON = "com.nexon.nxgcmnoti.icon";
    public static final String KEY_OPEN_STORE = "openStore";
    public static final String KEY_SENDER = "senderNPSN";
    public static final String KEY_STORE_URL = "com.nexon.nxgcmnoti.store.url";
    public static final String KEY_SUBJECT = "title";
    public static final String KEY_TYPE = "msgType";
    public static final String NOTIFICATION_CHANNEL_ID_POSTFIX = "_general_default";
    public static final int NOTIFICATION_GROUP_SUMMARY_ID = 1;
    public static int NOTIFICATION_ID = 2;
    public static final String TOY_PUSH_CLICK_EVENT_EXTRA = "toy_push_click_event_extra";
    FcmInterface fcmInterface;
    private boolean isEngine;

    public interface FcmInterface {
        void notifyNotification(NXNotification nXNotification, Bundle bundle);

        void onLocalPush(Context context, Bundle bundle, NXNotification nXNotification);
    }

    protected class NXNotification {
        public int color;
        public boolean doOpenStore;
        public int iconResource;
        public boolean isForce;
        public Spanned message;
        public Map<String, Object> metaMap = new HashMap();
        public String metaString;
        public long sender;
        public Spanned subject;
        public int type;

        public NXNotification(Context context, Bundle bundle) {
            String string;
            this.metaString = bundle.getString(NXPBaseFcmMessagingService.KEY_META);
            if (NXStringUtil.isNotNull(this.metaString)) {
                try {
                    JSONObject jSONObject = new JSONObject(this.metaString);
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        try {
                            this.metaMap.put(next, jSONObject.get(next));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            this.color = getNotificationColor(context);
            this.iconResource = getNotificationIconResource(context);
            this.type = getIntFromString(bundle.getString(NXPBaseFcmMessagingService.KEY_TYPE));
            Boolean bool = (Boolean) this.metaMap.get(NXPBaseFcmMessagingService.KEY_FORCE);
            this.isForce = bool != null && bool.booleanValue();
            Boolean bool2 = (Boolean) this.metaMap.get(NXPBaseFcmMessagingService.KEY_OPEN_STORE);
            this.doOpenStore = bool2 != null && bool2.booleanValue();
            String string2 = bundle.getString("title");
            if (NXStringUtil.isNotNull(string2)) {
                this.subject = fromHtml(string2);
            } else {
                this.subject = new SpannableString(context.getApplicationInfo().loadLabel(context.getPackageManager()));
            }
            if (Boolean.parseBoolean(bundle.getString("isLocalPush"))) {
                string = bundle.getString("message", "");
            } else {
                this.sender = getLongFromString(bundle.getString(NXPBaseFcmMessagingService.KEY_SENDER));
                string = bundle.getString(NXPBaseFcmMessagingService.KEY_MESSAGE, "");
            }
            this.message = fromHtml(string);
        }

        private Spanned fromHtml(String str) {
            return Build.VERSION.SDK_INT >= 24 ? Html.fromHtml(str, 0) : Html.fromHtml(str);
        }

        private int getIntFromString(String str) {
            try {
                return Integer.parseInt(str);
            } catch (Exception e) {
                ToyLog.d(e.getMessage());
                return 0;
            }
        }

        private long getLongFromString(String str) {
            try {
                return Long.parseLong(str);
            } catch (Exception e) {
                ToyLog.d(e.getMessage());
                return 0L;
            }
        }

        private int getNotificationColor(Context context) {
            int appMetaDataTypeInteger = NXApplicationUtil.getAppMetaDataTypeInteger(context, NXPBaseFcmMessagingService.KEY_NOTI_COLOR);
            if (appMetaDataTypeInteger != -1) {
                return appMetaDataTypeInteger;
            }
            ToyLog.d("Missing com.nexon.nxgcmnoti.color value in AndroidManifest.xml");
            return Color.parseColor("#ff0aa0d2");
        }

        private int getNotificationIconResource(Context context) {
            Bundle bundle = null;
            try {
                bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (bundle != null) {
                Object obj = bundle.get(NXPBaseFcmMessagingService.KEY_NOTI_ICON);
                if (obj != null) {
                    return ((Integer) obj).intValue();
                }
                ToyLog.d("Missing com.nexon.nxgcmnoti.icon value in AndroidManifest.xml");
            }
            return context.getApplicationInfo().icon;
        }
    }

    public NXPBaseFcmMessagingService() {
        ToyLog.d("NXFcmNoti : NXPBaseFcmMessagingService");
        if (NXPUnity.getUnityPlayerClass() != null || NXPUnreal.getNPAForUnrealClass() != null) {
            this.isEngine = true;
        }
        createImplOfFcmInterface();
    }

    public NXPBaseFcmMessagingService(String str, boolean z) {
        ToyLog.d("Init FCM Notification : " + str + " / isEngine : " + z);
        this.isEngine = z;
        createImplOfFcmInterface();
    }

    private void createImplOfFcmInterface() {
        if (this.isEngine) {
            this.fcmInterface = new NXPFcmEngineImpl(this);
        } else {
            this.fcmInterface = new NXPFcmNativeImpl(this);
        }
    }

    private void setChannelIdIfNeed(NotificationManager notificationManager, NotificationCompat.Builder builder, Context context) {
        NXPApplicationConfigManager nXPApplicationConfigManager;
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        try {
            nXPApplicationConfigManager = NXPApplicationConfigManager.getInstance(true);
        } catch (Exception e) {
            ToyLog.e("In setChannelIdIfNeed, NXPApplicationConfigManager is not initialized. " + e.toString());
            nXPApplicationConfigManager = NXPApplicationConfigManager.getInstance();
            nXPApplicationConfigManager.initialize(context);
        }
        String str = nXPApplicationConfigManager.getServiceId() + NOTIFICATION_CHANNEL_ID_POSTFIX;
        Iterator<NotificationChannel> it = notificationManager.getNotificationChannels().iterator();
        while (it.hasNext()) {
            if (it.next().getId().equals(str)) {
                builder.setChannelId(str);
                return;
            }
        }
        NotificationChannel notificationChannel = new NotificationChannel(str, "General Notifications", 4);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationManager.createNotificationChannel(notificationChannel);
        builder.setChannelId(str);
    }

    protected void notify(NXNotification nXNotification, Bundle bundle) {
        ToyLog.d("Call Engine notifyNotification");
        this.fcmInterface.notifyNotification(nXNotification, bundle);
    }

    /* JADX WARN: Code duplicated, block: B:49:0x01d6  */
    protected void notifyNotification(Context context, NXNotification nXNotification, Bundle bundle, boolean z) {
        Intent intent;
        Intent intent2;
        ToyLog.d("Context :" + context + ", notifyNotification notification:" + nXNotification + ",  bundle:" + bundle + ", isRemotePush:" + z);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        int i = NOTIFICATION_ID;
        NOTIFICATION_ID = i + 1;
        if (nXNotification.doOpenStore) {
            String appMetaDataTypeString = NXApplicationUtil.getAppMetaDataTypeString(context, KEY_STORE_URL);
            if (appMetaDataTypeString != null) {
                intent = new Intent("android.intent.action.VIEW", Uri.parse(appMetaDataTypeString));
            } else {
                ToyLog.d("Missing com.nexon.nxgcmnoti.store.url value in AndroidManifest.xml");
                intent = null;
            }
        } else {
            intent = null;
        }
        if (intent == null) {
            try {
                ToyLog.d("Intent is null.");
                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                ComponentName component = launchIntentForPackage != null ? launchIntentForPackage.getComponent() : null;
                if (component != null) {
                    intent2 = new Intent(context, Class.forName(component.getClassName()));
                } else {
                    intent2 = intent;
                }
            } catch (ClassNotFoundException e) {
                ToyLog.d("Intent is null. catch ClassNotFoundException");
                return;
            }
        } else {
            intent2 = intent;
        }
        if (z) {
            intent2.putExtra(TOY_PUSH_CLICK_EVENT_EXTRA, bundle);
        }
        NotificationCompat.Builder contentText = new NotificationCompat.Builder(context, "").setSmallIcon(nXNotification.iconResource).setLargeIcon(BitmapFactory.decodeResource(context.getResources(), context.getApplicationInfo().icon)).setStyle(new NotificationCompat.BigTextStyle().bigText(nXNotification.message)).setAutoCancel(true).setLights(nXNotification.color, 1000, 1000).setContentIntent(PendingIntent.getActivity(context, i, intent2, 0)).setSound(Settings.System.DEFAULT_NOTIFICATION_URI).setContentTitle(nXNotification.subject).setPriority(1).setContentText(nXNotification.message);
        if (Build.VERSION.SDK_INT >= 20) {
            contentText.setGroup(context.getPackageName());
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                contentText.setCategory(NotificationCompat.CATEGORY_RECOMMENDATION).setColor(nXNotification.color);
            } catch (NoSuchMethodError e2) {
                ToyLog.d("Current android-support-v4.jar is not included setColor Methods. upgrade v4 jar file.");
            }
        }
        setChannelIdIfNeed(notificationManager, contentText, context);
        if (notificationManager != null) {
            notificationManager.notify(i, contentText.build());
        }
        if (Build.VERSION.SDK_INT < 24) {
            ToyLog.d("Build.VERSION.SDK_INT < Build.VERSION_CODES.N");
            return;
        }
        try {
            if (new NPFcmUtil().getValidNotificationCount(notificationManager, 1) <= 1) {
                if (notificationManager != null) {
                    notificationManager.cancel(1);
                    return;
                }
                return;
            }
            String str = context.getApplicationInfo().name;
            String appMetaDataTypeString2 = NXApplicationUtil.getAppMetaDataTypeString(context, KEY_NOTIFICATION_SUMMARY_CONTENT);
            if (appMetaDataTypeString2 != null) {
                str = appMetaDataTypeString2;
            } else {
                ToyLog.d("Missing com.nexon.nxgcmnoti.summary value in AndroidManifest.xml");
            }
            NotificationCompat.Builder groupSummary = new NotificationCompat.Builder(context, "").setSmallIcon(nXNotification.iconResource).setStyle(new NotificationCompat.BigTextStyle().setSummaryText(str)).setGroup(context.getPackageName()).setGroupSummary(true);
            setChannelIdIfNeed(notificationManager, groupSummary, context);
            if (notificationManager != null) {
                notificationManager.notify(1, groupSummary.build());
            }
        } catch (Exception e3) {
            ToyLog.d("Current android SDK version is not included setGroupSummary Methods.");
        }
    }

    public void onLocalPush(Context context, Bundle bundle) {
        this.fcmInterface.onLocalPush(context, bundle, new NXNotification(context, bundle));
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (!NXPPush.isIsInitialized()) {
            NXPPush.initialize(this);
            if (NXToySessionManager.getInstance().getSession() == null) {
                ToyLog.e("In onMessageReceived, NXToySession is null.");
                ToyLog.setupLogcatLogger(getPackageManager());
                NXToyRequestPostman.getInstance().initialize(this);
                NXToySessionManager.getInstance().initialize(this);
                NXToyCommonPreferenceController.getInstance().initialize(this);
                NXPActivityLifecycleCallbackManager.getInstance().registerToApplication(getApplication());
            }
        }
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        String messageId = remoteMessage.getMessageId();
        if (NXStringUtil.isNotNull(messageId)) {
            bundle.putString("google.message_id", messageId);
        }
        if (bundle.isEmpty() || !bundle.containsKey(KEY_SENDER)) {
            ToyLog.d("Message bundle is empty");
            return;
        }
        NXNotification nXNotification = new NXNotification(this, bundle);
        int appMetaDataTypeInteger = NXApplicationUtil.getAppMetaDataTypeInteger(this, KEY_NOTI_GROUP);
        if (appMetaDataTypeInteger == -1) {
            appMetaDataTypeInteger = 2;
            ToyLog.d("Missing com.nexon.nxgcmnoti.group value in AndroidManifest.xml");
        }
        int i = nXNotification.type;
        if (i > 0 && appMetaDataTypeInteger != i) {
            ToyLog.d("Check notification group. msgType > 0 && notificationGroup != msgType");
            return;
        }
        if (!NXApplicationUtil.isRunningForeground(this)) {
            ToyLog.d("Received notification on Background. messageData length : " + bundle.keySet().size() + ",  messageData : " + bundle);
            notifyNotification(this, nXNotification, bundle, true);
            NXPPush.onPushReceive(this, bundle, false);
            return;
        }
        ToyLog.d("Received message on foreground.");
        if (this.isEngine) {
            ToyLog.d("Engine received notification. message is " + bundle);
            notify(nXNotification, bundle);
            NXPPush.onPushReceive(this, bundle, true);
        } else {
            ToyLog.d("Native App received notification.");
            if (nXNotification.isForce) {
                notifyNotification(this, nXNotification, bundle, true);
            }
            NXPPush.onPushReceive(this, bundle, true);
        }
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        super.onNewToken(str);
        ToyLog.d("Notification onNewToken", "token", str);
        new NPFcmUtil().storeRegistrationToken(this, str);
    }
}
