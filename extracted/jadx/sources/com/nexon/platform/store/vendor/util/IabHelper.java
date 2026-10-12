package com.nexon.platform.store.vendor.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class IabHelper {
    public static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
    public static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
    public static final int BILLING_RESPONSE_RESULT_ERROR = 6;
    public static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
    public static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;
    public static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
    public static final int BILLING_RESPONSE_RESULT_OK = 0;
    public static final int BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE = 2;
    public static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
    public static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
    public static final String GET_SKU_DETAILS_ITEM_TYPE_LIST = "ITEM_TYPE_LIST";
    public static final int IABHELPER_BAD_RESPONSE = -1002;
    public static final int IABHELPER_ERROR_BASE = -1000;
    public static final int IABHELPER_INVALID_CONSUMPTION = -1010;
    public static final int IABHELPER_MISSING_TOKEN = -1007;
    public static final int IABHELPER_REMOTE_EXCEPTION = -1001;
    public static final int IABHELPER_SEND_INTENT_FAILED = -1004;
    public static final int IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE = -1009;
    public static final int IABHELPER_SUBSCRIPTION_UPDATE_NOT_AVAILABLE = -1011;
    public static final int IABHELPER_UNKNOWN_ERROR = -1008;
    public static final int IABHELPER_UNKNOWN_PURCHASE_RESPONSE = -1006;
    public static final int IABHELPER_USER_CANCELLED = -1005;
    public static final int IABHELPER_VERIFICATION_FAILED = -1003;
    public static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    public static final String ITEM_TYPE_INAPP = "inapp";
    public static final String ITEM_TYPE_SUBS = "subs";
    public static final String RESPONSE_BUY_INTENT = "BUY_INTENT";
    public static final String RESPONSE_CODE = "RESPONSE_CODE";
    public static final String RESPONSE_GET_SKU_DETAILS_LIST = "DETAILS_LIST";
    public static final String RESPONSE_INAPP_ITEM_LIST = "INAPP_PURCHASE_ITEM_LIST";
    public static final String RESPONSE_INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
    public static final String RESPONSE_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    public static final String RESPONSE_INAPP_SIGNATURE = "INAPP_DATA_SIGNATURE";
    public static final String RESPONSE_INAPP_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
    Context mContext;
    OnIabPurchaseFinishedListener mPurchaseListener;
    String mPurchasingItemType;
    int mRequestCode;
    IInAppBillingService mService;
    ServiceConnection mServiceConn;
    String mSignatureBase64;
    boolean mDebugLog = false;
    String mDebugTag = "IabHelper";
    boolean mSetupDone = false;
    boolean mDisposed = false;
    boolean mDisposeAfterAsync = false;
    boolean mSubscriptionsSupported = false;
    boolean mSubscriptionUpdateSupported = false;
    boolean mAsyncInProgress = false;
    private final Object mAsyncInProgressLock = new Object();
    String mAsyncOperation = "";

    public static class IabAsyncInProgressException extends Exception {
        public IabAsyncInProgressException(String str) {
            super(str);
        }
    }

    public interface OnConsumeFinishedListener {
        void onConsumeFinished(Purchase purchase, IabResult iabResult);
    }

    public interface OnConsumeMultiFinishedListener {
        void onConsumeMultiFinished(List<Purchase> list, List<IabResult> list2);
    }

    public interface OnIabPurchaseFinishedListener {
        void onIabPurchaseFinished(IabResult iabResult, Purchase purchase);
    }

    public interface OnIabSetupFinishedListener {
        void onIabSetupFinished(IabResult iabResult);
    }

    public interface QueryInventoryFinishedListener {
        void onQueryInventoryFinished(IabResult iabResult, Inventory inventory);
    }

    public IabHelper(Context context, String str) {
        this.mSignatureBase64 = null;
        this.mContext = context.getApplicationContext();
        this.mSignatureBase64 = str;
        logDebug("IAB helper created.");
    }

    private void checkNotDisposed() {
        if (this.mDisposed) {
            throw new IllegalStateException("IabHelper was disposed of, so it cannot be used.");
        }
    }

    public static String getResponseDesc(int i) {
        String[] strArrSplit = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        String[] strArrSplit2 = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        if (i > -1000) {
            return (i < 0 || i >= strArrSplit.length) ? String.valueOf(i) + ":Unknown" : strArrSplit[i];
        }
        int i2 = (-1000) - i;
        return (i2 < 0 || i2 >= strArrSplit2.length) ? String.valueOf(i) + ":Unknown IAB Helper Error" : strArrSplit2[i2];
    }

    void checkSetupDone(String str) {
        if (this.mSetupDone) {
            return;
        }
        logError("Illegal state for operation (" + str + "): IAB helper is not set up.");
        throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + str);
    }

    void consume(Purchase purchase) throws IabException {
        checkNotDisposed();
        checkSetupDone("consume");
        if (!purchase.mItemType.equals("inapp")) {
            throw new IabException(-1010, "Items of type '" + purchase.mItemType + "' can't be consumed.");
        }
        try {
            String token = purchase.getToken();
            String sku = purchase.getSku();
            if (token == null || token.equals("")) {
                logError("Can't consume " + sku + ". No token.");
                throw new IabException(-1007, "PurchaseInfo is missing token for sku: " + sku + " " + purchase);
            }
            logDebug("Consuming sku: " + sku + ", token: " + token);
            int iConsumePurchase = this.mService.consumePurchase(3, this.mContext.getPackageName(), token);
            if (iConsumePurchase == 0) {
                logDebug("Successfully consumed sku: " + sku);
            } else {
                logDebug("Error consuming consuming sku " + sku + ". " + getResponseDesc(iConsumePurchase));
                throw new IabException(iConsumePurchase, "Error consuming sku " + sku);
            }
        } catch (RemoteException e) {
            throw new IabException(-1001, "Remote exception while consuming. PurchaseInfo: " + purchase, e);
        }
    }

    public void consumeAsync(Purchase purchase, OnConsumeFinishedListener onConsumeFinishedListener) throws IabAsyncInProgressException {
        checkNotDisposed();
        checkSetupDone("consume");
        ArrayList arrayList = new ArrayList();
        arrayList.add(purchase);
        consumeAsyncInternal(arrayList, onConsumeFinishedListener, null);
    }

    public void consumeAsync(List<Purchase> list, OnConsumeMultiFinishedListener onConsumeMultiFinishedListener) throws IabAsyncInProgressException {
        checkNotDisposed();
        checkSetupDone("consume");
        consumeAsyncInternal(list, null, onConsumeMultiFinishedListener);
    }

    void consumeAsyncInternal(final List<Purchase> list, final OnConsumeFinishedListener onConsumeFinishedListener, final OnConsumeMultiFinishedListener onConsumeMultiFinishedListener) throws IabAsyncInProgressException {
        final Handler handler = new Handler();
        flagStartAsync("consume");
        new Thread(new Runnable() { // from class: com.nexon.platform.store.vendor.util.IabHelper.3
            @Override // java.lang.Runnable
            public void run() {
                final ArrayList arrayList = new ArrayList();
                for (Purchase purchase : list) {
                    try {
                        IabHelper.this.consume(purchase);
                        arrayList.add(new IabResult(0, "Successful consume of sku " + purchase.getSku()));
                    } catch (IabException e) {
                        arrayList.add(e.getResult());
                    }
                }
                IabHelper.this.flagEndAsync();
                if (!IabHelper.this.mDisposed && onConsumeFinishedListener != null) {
                    handler.post(new Runnable() { // from class: com.nexon.platform.store.vendor.util.IabHelper.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            onConsumeFinishedListener.onConsumeFinished((Purchase) list.get(0), (IabResult) arrayList.get(0));
                        }
                    });
                }
                if (IabHelper.this.mDisposed || onConsumeMultiFinishedListener == null) {
                    return;
                }
                handler.post(new Runnable() { // from class: com.nexon.platform.store.vendor.util.IabHelper.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        onConsumeMultiFinishedListener.onConsumeMultiFinished(list, arrayList);
                    }
                });
            }
        }).start();
    }

    public void dispose() throws IabAsyncInProgressException {
        synchronized (this.mAsyncInProgressLock) {
            if (this.mAsyncInProgress) {
                throw new IabAsyncInProgressException("Can't dispose because an async operation (" + this.mAsyncOperation + ") is in progress.");
            }
        }
        logDebug("Disposing.");
        this.mSetupDone = false;
        if (this.mServiceConn != null) {
            logDebug("Unbinding from service.");
            if (this.mContext != null) {
                this.mContext.unbindService(this.mServiceConn);
            }
        }
        this.mDisposed = true;
        this.mContext = null;
        this.mServiceConn = null;
        this.mService = null;
        this.mPurchaseListener = null;
    }

    public void disposeWhenFinished() {
        synchronized (this.mAsyncInProgressLock) {
            if (this.mAsyncInProgress) {
                logDebug("Will dispose after async operation finishes.");
                this.mDisposeAfterAsync = true;
            } else {
                try {
                    dispose();
                } catch (IabAsyncInProgressException e) {
                }
            }
        }
    }

    public void enableDebugLogging(boolean z) {
        checkNotDisposed();
        this.mDebugLog = z;
    }

    public void enableDebugLogging(boolean z, String str) {
        checkNotDisposed();
        this.mDebugLog = z;
        this.mDebugTag = str;
    }

    void flagEndAsync() {
        synchronized (this.mAsyncInProgressLock) {
            logDebug("Ending async operation: " + this.mAsyncOperation);
            this.mAsyncOperation = "";
            this.mAsyncInProgress = false;
            if (this.mDisposeAfterAsync) {
                try {
                    dispose();
                } catch (IabAsyncInProgressException e) {
                }
            }
        }
    }

    void flagStartAsync(String str) throws IabAsyncInProgressException {
        synchronized (this.mAsyncInProgressLock) {
            if (this.mAsyncInProgress) {
                throw new IabAsyncInProgressException("Can't start async operation (" + str + ") because another async operation (" + this.mAsyncOperation + ") is in progress.");
            }
            this.mAsyncOperation = str;
            this.mAsyncInProgress = true;
            logDebug("Starting async operation: " + str);
        }
    }

    int getResponseCodeFromBundle(Bundle bundle) {
        Object obj = bundle.get("RESPONSE_CODE");
        if (obj == null) {
            logDebug("Bundle with null response code, assuming OK (known issue)");
            return 0;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return (int) ((Long) obj).longValue();
        }
        logError("Unexpected type for bundle response code.");
        logError(obj.getClass().getName());
        throw new RuntimeException("Unexpected type for bundle response code: " + obj.getClass().getName());
    }

    int getResponseCodeFromIntent(Intent intent) {
        Object obj = intent.getExtras().get("RESPONSE_CODE");
        if (obj == null) {
            logError("Intent with no response code, assuming OK (known issue)");
            return 0;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return (int) ((Long) obj).longValue();
        }
        logError("Unexpected type for intent response code.");
        logError(obj.getClass().getName());
        throw new RuntimeException("Unexpected type for intent response code: " + obj.getClass().getName());
    }

    public boolean handleActivityResult(int i, int i2, Intent intent) {
        if (i != this.mRequestCode) {
            return false;
        }
        checkNotDisposed();
        checkSetupDone("handleActivityResult");
        flagEndAsync();
        if (intent == null) {
            logError("Null data in IAB activity result.");
            IabResult iabResult = new IabResult(-1002, "Null data in IAB result");
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(iabResult, null);
            }
            return true;
        }
        int responseCodeFromIntent = getResponseCodeFromIntent(intent);
        String stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
        String stringExtra2 = intent.getStringExtra("INAPP_DATA_SIGNATURE");
        if (i2 == -1 && responseCodeFromIntent == 0) {
            logDebug("Successful resultcode from purchase activity.");
            logDebug("Purchase data: " + stringExtra);
            logDebug("Data signature: " + stringExtra2);
            logDebug("Extras: " + intent.getExtras());
            logDebug("Expected item type: " + this.mPurchasingItemType);
            if (stringExtra == null || stringExtra2 == null) {
                logError("BUG: either purchaseData or dataSignature is null.");
                logDebug("Extras: " + intent.getExtras().toString());
                IabResult iabResult2 = new IabResult(-1008, "IAB returned null purchaseData or dataSignature");
                if (this.mPurchaseListener != null) {
                    this.mPurchaseListener.onIabPurchaseFinished(iabResult2, null);
                }
                return true;
            }
            try {
                Purchase purchase = new Purchase(this.mPurchasingItemType, stringExtra, stringExtra2);
                try {
                    purchase.getSku();
                    logDebug("Purchase signature successfully verified.");
                    if (this.mPurchaseListener != null) {
                        this.mPurchaseListener.onIabPurchaseFinished(new IabResult(0, "Success"), purchase);
                    }
                } catch (JSONException e) {
                    e = e;
                    logError("Failed to parse purchase data.");
                    e.printStackTrace();
                    IabResult iabResult3 = new IabResult(-1002, "Failed to parse purchase data.");
                    if (this.mPurchaseListener != null) {
                        this.mPurchaseListener.onIabPurchaseFinished(iabResult3, null);
                    }
                    return true;
                }
            } catch (JSONException e2) {
                e = e2;
            }
        } else if (i2 == -1) {
            logDebug("Result code was OK but in-app billing response was not OK: " + getResponseDesc(responseCodeFromIntent));
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(new IabResult(responseCodeFromIntent, getResponseDesc(responseCodeFromIntent)), null);
            }
        } else if (i2 == 0) {
            logDebug("Purchase canceled - Response: " + getResponseDesc(responseCodeFromIntent));
            IabResult iabResult4 = new IabResult(responseCodeFromIntent, getResponseDesc(responseCodeFromIntent));
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(iabResult4, null);
            }
        } else {
            logError("Purchase failed. Result code: " + Integer.toString(i2) + ". Response: " + getResponseDesc(responseCodeFromIntent));
            IabResult iabResult5 = new IabResult(responseCodeFromIntent, getResponseDesc(responseCodeFromIntent));
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(iabResult5, null);
            }
        }
        return true;
    }

    public boolean isCompleteSetup() {
        return this.mSetupDone;
    }

    public void launchPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener) throws IabAsyncInProgressException {
        launchPurchaseFlow(activity, str, i, onIabPurchaseFinishedListener, "");
    }

    public void launchPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str2) throws IabAsyncInProgressException {
        launchPurchaseFlow(activity, str, "inapp", null, i, onIabPurchaseFinishedListener, str2);
    }

    public void launchPurchaseFlow(Activity activity, String str, String str2, List<String> list, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str3) throws IabAsyncInProgressException {
        Bundle buyIntent;
        checkNotDisposed();
        checkSetupDone("launchPurchaseFlow");
        flagStartAsync("launchPurchaseFlow");
        if (str2.equals("subs") && !this.mSubscriptionsSupported) {
            IabResult iabResult = new IabResult(-1009, "Subscriptions are not available.");
            flagEndAsync();
            if (onIabPurchaseFinishedListener != null) {
                onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, null);
                return;
            }
            return;
        }
        try {
            logDebug("Constructing buy intent for " + str + ", item type: " + str2);
            if (list == null || list.isEmpty()) {
                buyIntent = this.mService.getBuyIntent(3, this.mContext.getPackageName(), str, str2, str3);
            } else {
                if (!this.mSubscriptionUpdateSupported) {
                    IabResult iabResult2 = new IabResult(IABHELPER_SUBSCRIPTION_UPDATE_NOT_AVAILABLE, "Subscription updates are not available.");
                    flagEndAsync();
                    if (onIabPurchaseFinishedListener != null) {
                        onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult2, null);
                        return;
                    }
                    return;
                }
                buyIntent = this.mService.getBuyIntentToReplaceSkus(5, this.mContext.getPackageName(), list, str, str2, str3);
            }
            int responseCodeFromBundle = getResponseCodeFromBundle(buyIntent);
            if (responseCodeFromBundle != 0) {
                logError("Unable to buy item, Error response: " + getResponseDesc(responseCodeFromBundle));
                flagEndAsync();
                IabResult iabResult3 = new IabResult(responseCodeFromBundle, "Unable to buy item");
                if (onIabPurchaseFinishedListener != null) {
                    onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult3, null);
                    return;
                }
                return;
            }
            PendingIntent pendingIntent = (PendingIntent) buyIntent.getParcelable("BUY_INTENT");
            logDebug("Launching buy intent for " + str + ". Request code: " + i);
            this.mRequestCode = i;
            this.mPurchaseListener = onIabPurchaseFinishedListener;
            this.mPurchasingItemType = str2;
            IntentSender intentSender = pendingIntent.getIntentSender();
            Intent intent = new Intent();
            Integer num = 0;
            int iIntValue = num.intValue();
            Integer num2 = 0;
            int iIntValue2 = num2.intValue();
            Integer num3 = 0;
            activity.startIntentSenderForResult(intentSender, i, intent, iIntValue, iIntValue2, num3.intValue());
        } catch (IntentSender.SendIntentException e) {
            logError("SendIntentException while launching purchase flow for sku " + str);
            e.printStackTrace();
            flagEndAsync();
            IabResult iabResult4 = new IabResult(-1004, "Failed to send intent.");
            if (onIabPurchaseFinishedListener != null) {
                onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult4, null);
            }
        } catch (RemoteException e2) {
            logError("RemoteException while launching purchase flow for sku " + str);
            e2.printStackTrace();
            flagEndAsync();
            IabResult iabResult5 = new IabResult(-1001, "Remote exception while starting purchase flow");
            if (onIabPurchaseFinishedListener != null) {
                onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult5, null);
            }
        }
    }

    public void launchSubscriptionPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener) throws IabAsyncInProgressException {
        launchSubscriptionPurchaseFlow(activity, str, i, onIabPurchaseFinishedListener, "");
    }

    public void launchSubscriptionPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str2) throws IabAsyncInProgressException {
        launchPurchaseFlow(activity, str, "subs", null, i, onIabPurchaseFinishedListener, str2);
    }

    void logDebug(String str) {
        if (this.mDebugLog) {
            Log.d(this.mDebugTag, str);
        }
    }

    void logError(String str) {
        Log.e(this.mDebugTag, "In-app billing error: " + str);
    }

    void logWarn(String str) {
        Log.w(this.mDebugTag, "In-app billing warning: " + str);
    }

    public Inventory queryInventory() throws IabException {
        return queryInventory(false, null, null);
    }

    public Inventory queryInventory(boolean z, List<String> list, List<String> list2) throws IabException {
        int iQuerySkuDetails;
        int iQuerySkuDetails2;
        checkNotDisposed();
        checkSetupDone("queryInventory");
        try {
            Inventory inventory = new Inventory();
            int iQueryPurchases = queryPurchases(inventory, "inapp");
            if (iQueryPurchases != 0) {
                throw new IabException(iQueryPurchases, "Error refreshing inventory (querying owned items).");
            }
            if (z && (iQuerySkuDetails2 = querySkuDetails("inapp", inventory, list)) != 0) {
                throw new IabException(iQuerySkuDetails2, "Error refreshing inventory (querying prices of items).");
            }
            if (this.mSubscriptionsSupported) {
                int iQueryPurchases2 = queryPurchases(inventory, "subs");
                if (iQueryPurchases2 != 0) {
                    throw new IabException(iQueryPurchases2, "Error refreshing inventory (querying owned subscriptions).");
                }
                if (z && (iQuerySkuDetails = querySkuDetails("subs", inventory, list2)) != 0) {
                    throw new IabException(iQuerySkuDetails, "Error refreshing inventory (querying prices of subscriptions).");
                }
            }
            return inventory;
        } catch (RemoteException e) {
            throw new IabException(-1001, "Remote exception while refreshing inventory.", e);
        } catch (JSONException e2) {
            throw new IabException(-1002, "Error parsing JSON response while refreshing inventory.", e2);
        }
    }

    public void queryInventoryAsync(QueryInventoryFinishedListener queryInventoryFinishedListener) throws IabAsyncInProgressException {
        queryInventoryAsync(false, null, null, queryInventoryFinishedListener);
    }

    public void queryInventoryAsync(final boolean z, final List<String> list, final List<String> list2, final QueryInventoryFinishedListener queryInventoryFinishedListener) throws IabAsyncInProgressException {
        final Handler handler = new Handler();
        checkNotDisposed();
        checkSetupDone("queryInventory");
        flagStartAsync("refresh inventory");
        new Thread(new Runnable() { // from class: com.nexon.platform.store.vendor.util.IabHelper.2
            @Override // java.lang.Runnable
            public void run() {
                IabResult iabResult = new IabResult(0, "Inventory refresh successful.");
                Inventory inventoryQueryInventory = null;
                try {
                    inventoryQueryInventory = IabHelper.this.queryInventory(z, list, list2);
                } catch (IabException e) {
                    iabResult = e.getResult();
                }
                IabHelper.this.flagEndAsync();
                final IabResult iabResult2 = iabResult;
                final Inventory inventory = inventoryQueryInventory;
                if (IabHelper.this.mDisposed || queryInventoryFinishedListener == null) {
                    return;
                }
                handler.post(new Runnable() { // from class: com.nexon.platform.store.vendor.util.IabHelper.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        queryInventoryFinishedListener.onQueryInventoryFinished(iabResult2, inventory);
                    }
                });
            }
        }).start();
    }

    int queryPurchases(Inventory inventory, String str) throws JSONException, RemoteException {
        logDebug("Querying owned items, item type: " + str);
        logDebug("Package name: " + this.mContext.getPackageName());
        String string = null;
        do {
            try {
                logDebug("Calling getPurchases with continuation token: " + string);
                Bundle purchases = this.mService.getPurchases(3, this.mContext.getPackageName(), str, string);
                int responseCodeFromBundle = getResponseCodeFromBundle(purchases);
                logDebug("Owned items response: " + String.valueOf(responseCodeFromBundle));
                if (responseCodeFromBundle != 0) {
                    logDebug("getPurchases() failed: " + getResponseDesc(responseCodeFromBundle));
                    return responseCodeFromBundle;
                }
                if (!purchases.containsKey("INAPP_PURCHASE_ITEM_LIST") || !purchases.containsKey("INAPP_PURCHASE_DATA_LIST") || !purchases.containsKey("INAPP_DATA_SIGNATURE_LIST")) {
                    logError("Bundle returned from getPurchases() doesn't contain required fields.");
                    return -1002;
                }
                ArrayList<String> stringArrayList = purchases.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = purchases.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = purchases.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                for (int i = 0; i < stringArrayList2.size(); i++) {
                    String str2 = stringArrayList2.get(i);
                    String str3 = stringArrayList3.get(i);
                    logDebug("Sku is owned: " + stringArrayList.get(i));
                    Purchase purchase = new Purchase(str, str2, str3);
                    if (TextUtils.isEmpty(purchase.getToken())) {
                        logWarn("BUG: empty/null token!");
                        logDebug("Purchase data: " + str2);
                    }
                    inventory.addPurchase(purchase);
                }
                string = purchases.getString("INAPP_CONTINUATION_TOKEN");
                logDebug("Continuation token: " + string);
            } catch (Exception e) {
                e.printStackTrace();
                return -1003;
            }
        } while (!TextUtils.isEmpty(string));
        return 0 != 0 ? -1003 : 0;
    }

    int querySkuDetails(String str, Inventory inventory, List<String> list) throws JSONException, RemoteException {
        logDebug("Querying SKU details.");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(inventory.getAllOwnedSkus(str));
        if (list != null) {
            for (String str2 : list) {
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        if (arrayList.size() == 0) {
            logDebug("queryPrices: nothing to do because there are no SKUs.");
            return 0;
        }
        ArrayList<ArrayList<String>> arrayList2 = new ArrayList();
        int size = arrayList.size() / 20;
        int size2 = arrayList.size() % 20;
        for (int i = 0; i < size; i++) {
            ArrayList arrayList3 = new ArrayList();
            Iterator it = arrayList.subList(i * 20, (i * 20) + 20).iterator();
            while (it.hasNext()) {
                arrayList3.add((String) it.next());
            }
            arrayList2.add(arrayList3);
        }
        if (size2 != 0) {
            ArrayList arrayList4 = new ArrayList();
            Iterator it2 = arrayList.subList(size * 20, (size * 20) + size2).iterator();
            while (it2.hasNext()) {
                arrayList4.add((String) it2.next());
            }
            arrayList2.add(arrayList4);
        }
        for (ArrayList<String> arrayList5 : arrayList2) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList5);
            Bundle skuDetails = this.mService.getSkuDetails(3, this.mContext.getPackageName(), str, bundle);
            if (!skuDetails.containsKey("DETAILS_LIST")) {
                int responseCodeFromBundle = getResponseCodeFromBundle(skuDetails);
                if (responseCodeFromBundle != 0) {
                    logDebug("getSkuDetails() failed: " + getResponseDesc(responseCodeFromBundle));
                    return responseCodeFromBundle;
                }
                logError("getSkuDetails() returned a bundle with neither an error nor a detail list.");
                return -1002;
            }
            Iterator<String> it3 = skuDetails.getStringArrayList("DETAILS_LIST").iterator();
            while (it3.hasNext()) {
                SkuDetails skuDetails2 = new SkuDetails(str, it3.next());
                logDebug("Got sku details: " + skuDetails2);
                inventory.addSkuDetails(skuDetails2);
            }
        }
        return 0;
    }

    public void startSetup(final OnIabSetupFinishedListener onIabSetupFinishedListener) {
        checkNotDisposed();
        if (this.mSetupDone) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        logDebug("Starting in-app billing setup.");
        this.mServiceConn = new ServiceConnection() { // from class: com.nexon.platform.store.vendor.util.IabHelper.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                if (IabHelper.this.mDisposed) {
                    return;
                }
                IabHelper.this.logDebug("Billing service connected.");
                IabHelper.this.mService = IInAppBillingService.Stub.asInterface(iBinder);
                String packageName = IabHelper.this.mContext.getPackageName();
                try {
                    IabHelper.this.logDebug("Checking for in-app billing 3 support.");
                    int iIsBillingSupported = IabHelper.this.mService.isBillingSupported(3, packageName, "inapp");
                    if (iIsBillingSupported != 0) {
                        if (onIabSetupFinishedListener != null) {
                            onIabSetupFinishedListener.onIabSetupFinished(new IabResult(iIsBillingSupported, "Error checking for billing v3 support."));
                        }
                        IabHelper.this.mSubscriptionsSupported = false;
                        IabHelper.this.mSubscriptionUpdateSupported = false;
                        return;
                    }
                    IabHelper.this.logDebug("In-app billing version 3 supported for " + packageName);
                    if (IabHelper.this.mService.isBillingSupported(5, packageName, "subs") == 0) {
                        IabHelper.this.logDebug("Subscription re-signup AVAILABLE.");
                        IabHelper.this.mSubscriptionUpdateSupported = true;
                    } else {
                        IabHelper.this.logDebug("Subscription re-signup not available.");
                        IabHelper.this.mSubscriptionUpdateSupported = false;
                    }
                    if (IabHelper.this.mSubscriptionUpdateSupported) {
                        IabHelper.this.mSubscriptionsSupported = true;
                    } else {
                        int iIsBillingSupported2 = IabHelper.this.mService.isBillingSupported(3, packageName, "subs");
                        if (iIsBillingSupported2 == 0) {
                            IabHelper.this.logDebug("Subscriptions AVAILABLE.");
                            IabHelper.this.mSubscriptionsSupported = true;
                        } else {
                            IabHelper.this.logDebug("Subscriptions NOT AVAILABLE. Response: " + iIsBillingSupported2);
                            IabHelper.this.mSubscriptionsSupported = false;
                            IabHelper.this.mSubscriptionUpdateSupported = false;
                        }
                    }
                    IabHelper.this.mSetupDone = true;
                    if (onIabSetupFinishedListener != null) {
                        onIabSetupFinishedListener.onIabSetupFinished(new IabResult(0, "Setup successful."));
                    }
                } catch (RemoteException e) {
                    if (onIabSetupFinishedListener != null) {
                        onIabSetupFinishedListener.onIabSetupFinished(new IabResult(-1001, "RemoteException while setting up in-app billing."));
                    }
                    e.printStackTrace();
                } catch (Exception e2) {
                    if (onIabSetupFinishedListener != null) {
                        onIabSetupFinishedListener.onIabSetupFinished(new IabResult(-1000, "RemoteException while setting up in-app billing."));
                    }
                    e2.printStackTrace();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                IabHelper.this.logDebug("Billing service disconnected.");
                IabHelper.this.mService = null;
            }
        };
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        List<ResolveInfo> listQueryIntentServices = this.mContext.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices != null && !listQueryIntentServices.isEmpty()) {
            this.mContext.bindService(intent, this.mServiceConn, 1);
        } else if (onIabSetupFinishedListener != null) {
            onIabSetupFinishedListener.onIabSetupFinished(new IabResult(3, "Billing service unavailable on device."));
        }
    }

    public boolean subscriptionsSupported() {
        checkNotDisposed();
        return this.mSubscriptionsSupported;
    }
}
