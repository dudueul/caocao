package com.facebook.appevents.internal;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Validate;
import java.math.BigDecimal;
import java.util.Currency;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AutomaticAnalyticsLogger {
    private static final String TAG = AutomaticAnalyticsLogger.class.getCanonicalName();
    private static final InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(FacebookSdk.getApplicationContext());

    private static class PurchaseLoggingParameters {
        Currency currency;
        Bundle param;
        BigDecimal purchaseAmount;

        PurchaseLoggingParameters(BigDecimal bigDecimal, Currency currency, Bundle bundle) {
            this.purchaseAmount = bigDecimal;
            this.currency = currency;
            this.param = bundle;
        }
    }

    @Nullable
    private static PurchaseLoggingParameters getPurchaseLoggingParameters(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject(str2);
            Bundle bundle = new Bundle(1);
            bundle.putCharSequence(Constants.IAP_PRODUCT_ID, jSONObject.getString("productId"));
            bundle.putCharSequence(Constants.IAP_PURCHASE_TIME, jSONObject.getString("purchaseTime"));
            bundle.putCharSequence(Constants.IAP_PURCHASE_TOKEN, jSONObject.getString("purchaseToken"));
            bundle.putCharSequence(Constants.IAP_PACKAGE_NAME, jSONObject.optString("packageName"));
            bundle.putCharSequence(Constants.IAP_PRODUCT_TITLE, jSONObject2.optString("title"));
            bundle.putCharSequence(Constants.IAP_PRODUCT_DESCRIPTION, jSONObject2.optString("description"));
            String strOptString = jSONObject2.optString("type");
            bundle.putCharSequence(Constants.IAP_PRODUCT_TYPE, strOptString);
            if (strOptString.equals("subs")) {
                bundle.putCharSequence(Constants.IAP_SUBSCRIPTION_AUTORENEWING, Boolean.toString(jSONObject.optBoolean("autoRenewing", false)));
                bundle.putCharSequence(Constants.IAP_SUBSCRIPTION_PERIOD, jSONObject2.optString("subscriptionPeriod"));
                bundle.putCharSequence(Constants.IAP_FREE_TRIAL_PERIOD, jSONObject2.optString("freeTrialPeriod"));
                String strOptString2 = jSONObject2.optString("introductoryPriceCycles");
                if (!strOptString2.isEmpty()) {
                    bundle.putCharSequence(Constants.IAP_INTRO_PRICE_AMOUNT_MICROS, jSONObject2.optString("introductoryPriceAmountMicros"));
                    bundle.putCharSequence(Constants.IAP_INTRO_PRICE_CYCLES, strOptString2);
                }
            }
            return new PurchaseLoggingParameters(new BigDecimal(jSONObject2.getLong("price_amount_micros") / 1000000.0d), Currency.getInstance(jSONObject2.getString("price_currency_code")), bundle);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing in-app subscription data.", e);
            return null;
        }
    }

    public static boolean isImplicitPurchaseLoggingEnabled() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        return appSettingsWithoutQuery != null && FacebookSdk.getAutoLogAppEventsEnabled() && appSettingsWithoutQuery.getIAPAutomaticLoggingEnabled();
    }

    public static void logActivateAppEvent() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        String applicationId = FacebookSdk.getApplicationId();
        boolean autoLogAppEventsEnabled = FacebookSdk.getAutoLogAppEventsEnabled();
        Validate.notNull(applicationContext, "context");
        if (autoLogAppEventsEnabled) {
            if (applicationContext instanceof Application) {
                AppEventsLogger.activateApp((Application) applicationContext, applicationId);
            } else {
                Log.w(TAG, "Automatic logging of basic events will not happen, because FacebookSdk.getApplicationContext() returns object that is not instance of android.app.Application. Make sure you call FacebookSdk.sdkInitialize() from Application class and pass application context.");
            }
        }
    }

    public static void logActivityTimeSpentEvent(String str, long j) {
        Context applicationContext = FacebookSdk.getApplicationContext();
        String applicationId = FacebookSdk.getApplicationId();
        Validate.notNull(applicationContext, "context");
        FetchedAppSettings fetchedAppSettingsQueryAppSettings = FetchedAppSettingsManager.queryAppSettings(applicationId, false);
        if (fetchedAppSettingsQueryAppSettings == null || !fetchedAppSettingsQueryAppSettings.getAutomaticLoggingEnabled() || j <= 0) {
            return;
        }
        AppEventsLogger appEventsLoggerNewLogger = AppEventsLogger.newLogger(applicationContext);
        Bundle bundle = new Bundle(1);
        bundle.putCharSequence(Constants.AA_TIME_SPENT_SCREEN_PARAMETER_NAME, str);
        appEventsLoggerNewLogger.logEvent(Constants.AA_TIME_SPENT_EVENT_NAME, j, bundle);
    }

    public static void logPurchaseInapp(String str, String str2) {
        PurchaseLoggingParameters purchaseLoggingParameters;
        if (isImplicitPurchaseLoggingEnabled() && (purchaseLoggingParameters = getPurchaseLoggingParameters(str, str2)) != null) {
            internalAppEventsLogger.logPurchaseImplicitlyInternal(purchaseLoggingParameters.purchaseAmount, purchaseLoggingParameters.currency, purchaseLoggingParameters.param);
        }
    }

    public static void logPurchaseSubs(SubscriptionType subscriptionType, String str, String str2) {
        String str3;
        if (isImplicitPurchaseLoggingEnabled()) {
            switch (subscriptionType) {
                case RESTORE:
                    str3 = "SubscriptionRestore";
                    break;
                case CANCEL:
                    str3 = "SubscriptionCancel";
                    break;
                case HEARTBEAT:
                    str3 = "SubscriptionHeartbeat";
                    break;
                case EXPIRE:
                    str3 = "SubscriptionExpire";
                    break;
                case NEW:
                    logPurchaseInapp(str, str2);
                    return;
                default:
                    return;
            }
            PurchaseLoggingParameters purchaseLoggingParameters = getPurchaseLoggingParameters(str, str2);
            if (purchaseLoggingParameters != null) {
                internalAppEventsLogger.logEventImplicitly(str3, purchaseLoggingParameters.purchaseAmount, purchaseLoggingParameters.currency, purchaseLoggingParameters.param);
            }
        }
    }
}
