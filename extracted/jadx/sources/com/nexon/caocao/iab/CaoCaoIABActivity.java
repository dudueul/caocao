package com.nexon.caocao.iab;

import android.content.Intent;
import android.util.Log;
import com.android.iab.util.IabHelper;
import com.android.iab.util.IabResult;
import com.android.iab.util.Inventory;
import com.android.iab.util.Purchase;
import com.android.iab.util.SkuDetails;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CaoCaoIABActivity {
    static final String CALLBACK_BILLING_INIT_FAILED = "OnBillingInitFailed";
    static final String CALLBACK_BILLING_INIT_SUCCEED = "OnBillingInitSucceed";
    static final String CALLBACK_CONSUME_PURCHASE_FAILED = "OnConsumePurchaseFailed";
    static final String CALLBACK_CONSUME_PURCHASE_SUCCEED = "OnConsumePurchaseSucceed";
    static final String CALLBACK_PURCHASE_FAILED = "OnPurchaseFailed";
    static final String CALLBACK_PURCHASE_SUCCEED = "OnPurchaseSucceed";
    static final String CALLBACK_QUERY_INVENTORY_FAILED = "OnQueryInventoryFailed";
    static final String CALLBACK_QUERY_INVENTORY_SUCCEED = "OnQueryInventorySucceed";
    static final String CAOCAO_IAB_EVENTMANAGER = "CaoCaoIABEventManager";
    static final int RC_REQUEST = 10001;
    static CaoCaoIABActivity _instance;
    public static String mLogTag = "CaoCaoIAB";
    public static boolean sendRequest = false;
    private IabHelper mHelper;
    private boolean debugLogging = false;
    private boolean initialized = false;
    IabHelper.QueryInventoryFinishedListener queryInventoryFinishedListner = new IabHelper.QueryInventoryFinishedListener() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.5
        @Override // com.android.iab.util.IabHelper.QueryInventoryFinishedListener
        public void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) {
            CaoCaoIABActivity.this.mHelper.logDebug("Query Inventory Finished.");
            if (iabResult.isFailure()) {
                CaoCaoIABActivity.this.mHelper.logDebug("Query Inventory Failed.");
                UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_QUERY_INVENTORY_FAILED, iabResult.getMessageForUnity());
                return;
            }
            try {
                String strInventoryToJson = CaoCaoIABActivity.this.inventoryToJson(inventory);
                CaoCaoIABActivity.this.mHelper.logDebug("inventory json string: " + strInventoryToJson);
                CaoCaoIABActivity.this.mHelper.logDebug("Query Inventory Succeed");
                UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_QUERY_INVENTORY_SUCCEED, strInventoryToJson);
            } catch (JSONException e) {
                Log.e(CaoCaoIABActivity.mLogTag, "inventory serealizing failed.");
                UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_QUERY_INVENTORY_FAILED, "-9997:Cannot Serealize inventory data to json.");
            }
        }
    };
    IabHelper.OnIabPurchaseFinishedListener iabPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.8
        @Override // com.android.iab.util.IabHelper.OnIabPurchaseFinishedListener
        public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
            UnityPlayer.currentActivity.sendBroadcast(new Intent(CCPurchaseProxyActivity.ACTION_FINISH));
            CaoCaoIABActivity.this.mHelper.logDebug("Purchase finished. result: " + iabResult + ", info: " + purchase);
            if (iabResult.isFailure()) {
                Log.e(CaoCaoIABActivity.mLogTag, "Purchase Failed. result: " + iabResult);
                UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_PURCHASE_FAILED, iabResult.getMessageForUnity());
            } else {
                CaoCaoIABActivity.this.mHelper.logDebug("Purchase Succeed.");
                try {
                    UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_PURCHASE_SUCCEED, CaoCaoIABActivity.this.purchaseToJson(purchase));
                } catch (JSONException e) {
                    UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_PURCHASE_FAILED, "-9998:Cannot serialize Purchase.");
                }
            }
        }
    };
    IabHelper.OnConsumeFinishedListener consumeFinishedListener = new IabHelper.OnConsumeFinishedListener() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.11
        @Override // com.android.iab.util.IabHelper.OnConsumeFinishedListener
        public void onConsumeFinished(Purchase purchase, IabResult iabResult) {
            CaoCaoIABActivity.this.mHelper.logDebug("Consume Finished.");
            if (iabResult.isFailure()) {
                Log.e(CaoCaoIABActivity.mLogTag, "Consumption finished. Purchase: " + purchase + ", result: " + iabResult.getMessage());
                UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_CONSUME_PURCHASE_FAILED, iabResult.getMessageForUnity());
            } else {
                CaoCaoIABActivity.this.mHelper.logDebug("Consumption succeed.");
                try {
                    UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_CONSUME_PURCHASE_SUCCEED, CaoCaoIABActivity.this.purchaseToJson(purchase));
                } catch (JSONException e) {
                    UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_CONSUME_PURCHASE_FAILED, "-9998:Couldn't serialize the purchase");
                }
            }
        }
    };
    IabHelper.OnConsumeMultiFinishedListener consumeMultiFinishedListener = new IabHelper.OnConsumeMultiFinishedListener() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.12
        @Override // com.android.iab.util.IabHelper.OnConsumeMultiFinishedListener
        public void onConsumeMultiFinished(List<Purchase> list, List<IabResult> list2) {
            CaoCaoIABActivity.this.mHelper.logDebug("Multi-Consume Finished.");
            for (int i = 0; i < list2.size(); i++) {
                Purchase purchase = list.get(i);
                IabResult iabResult = list2.get(i);
                if (iabResult.isFailure()) {
                    CaoCaoIABActivity.this.mHelper.logDebug("Consumption failed.");
                    UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_CONSUME_PURCHASE_FAILED, iabResult.getMessageForUnity());
                } else {
                    CaoCaoIABActivity.this.mHelper.logDebug("Consumption Succeed.");
                    try {
                        UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_CONSUME_PURCHASE_SUCCEED, CaoCaoIABActivity.this.purchaseToJson(purchase));
                    } catch (JSONException e) {
                        UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_CONSUME_PURCHASE_FAILED, "-9998:Couldn't serialize the purchase");
                        return;
                    }
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void StartPurchaseFlow(String str, String str2, String str3) {
        this.mHelper.logDebug("startpurchaseflow - developerPayload: " + str3);
        if (str2 == "inapp") {
            startProxyPurchaseActivity(str, true, str3);
        } else if (str2 == "subs") {
            startProxyPurchaseActivity(str, false, str3);
        }
    }

    public static CaoCaoIABActivity instance() {
        if (_instance == null) {
            _instance = new CaoCaoIABActivity();
        }
        return _instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String inventoryToJson(Inventory inventory) throws JSONException {
        JSONStringer jSONStringerObject = new JSONStringer().object();
        jSONStringerObject.key("purchaseMap").array();
        for (Map.Entry<String, Purchase> entry : inventory.getPurchaseMap().entrySet()) {
            jSONStringerObject.array();
            jSONStringerObject.value(entry.getKey());
            jSONStringerObject.value(purchaseToJson(entry.getValue()));
            jSONStringerObject.endArray();
        }
        jSONStringerObject.endArray();
        jSONStringerObject.key("skuMap").array();
        for (Map.Entry<String, SkuDetails> entry2 : inventory.getSkuMap().entrySet()) {
            jSONStringerObject.array();
            jSONStringerObject.value(entry2.getKey());
            jSONStringerObject.value(skuDetailsToJson(entry2.getValue()));
            jSONStringerObject.endArray();
        }
        jSONStringerObject.endArray();
        jSONStringerObject.endObject();
        return jSONStringerObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String purchaseToJson(Purchase purchase) throws JSONException {
        return new JSONStringer().object().key("itemType").value(purchase.getItemType()).key("orderId").value(purchase.getOrderId()).key("packageName").value(purchase.getPackageName()).key("sku").value(purchase.getSku()).key("purchaseTime").value(purchase.getPurchaseTime()).key("purchaseState").value(purchase.getPurchaseState()).key("developerPayload").value(purchase.getDeveloperPayload()).key("token").value(purchase.getToken()).key("originalJson").value(purchase.getOriginalJson()).key("signature").value(purchase.getSignature()).endObject().toString();
    }

    private String skuDetailsToJson(SkuDetails skuDetails) throws JSONException {
        return new JSONStringer().object().key("itemType").value(skuDetails.getItemType()).key("sku").value(skuDetails.getSku()).key("type").value(skuDetails.getType()).key("price").value(skuDetails.getPrice()).key("priceMicros").value(skuDetails.getPriceMicros()).key("title").value(skuDetails.getTitle()).key("currencyCode").value(skuDetails.getPriceCurrencyCode()).key("description").value(skuDetails.getDescription()).key("json").value(skuDetails.getJson()).endObject().toString();
    }

    private void startProxyPurchaseActivity(String str, boolean z, String str2) {
        if (instance().getHelper() == null) {
            Log.e(mLogTag, "IAB is not initialized!");
            return;
        }
        sendRequest = true;
        Intent intent = new Intent(UnityPlayer.currentActivity, (Class<?>) CCPurchaseProxyActivity.class);
        intent.putExtra("sku", str);
        intent.putExtra("inapp", z);
        intent.putExtra("developerPayload", str2);
        UnityPlayer.currentActivity.startActivity(intent);
    }

    public boolean CheckInitIabHelper() {
        return this.initialized;
    }

    public void ConsumeProduct(final String str) {
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.9
            @Override // java.lang.Runnable
            public void run() {
                CaoCaoIABActivity.this.mHelper.logDebug("Consume Product.");
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    CaoCaoIABActivity.this.mHelper.consumeAsync(new Purchase(jSONObject.getString("itemType"), jSONObject.getString("originalJson"), jSONObject.getString("signature")), CaoCaoIABActivity.this.consumeFinishedListener);
                } catch (JSONException e) {
                    e.printStackTrace();
                    UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_CONSUME_PURCHASE_FAILED, "-9999:Cannot parse json data.");
                }
            }
        });
    }

    public void ConsumeProduct(final String[] strArr) {
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.10
            @Override // java.lang.Runnable
            public void run() {
                CaoCaoIABActivity.this.mHelper.logDebug("Multi-Consume Product.");
                try {
                    ArrayList arrayList = new ArrayList();
                    for (String str : strArr) {
                        JSONObject jSONObject = new JSONObject(str);
                        arrayList.add(new Purchase(jSONObject.getString("itemType"), jSONObject.getString("originalJson"), jSONObject.getString("signature")));
                    }
                    CaoCaoIABActivity.this.mHelper.consumeAsync(arrayList, CaoCaoIABActivity.this.consumeMultiFinishedListener);
                } catch (JSONException e) {
                    e.printStackTrace();
                    UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_CONSUME_PURCHASE_FAILED, "-9999:Cannot parse json data.");
                }
            }
        });
    }

    public void DisposeHelper() {
        this.mHelper.logDebug("Destroying helper.");
        if (this.mHelper != null) {
            this.mHelper.dispose();
        }
        this.mHelper = null;
        this.initialized = false;
    }

    public void InitIabHelper(String str) {
        this.initialized = false;
        this.mHelper = new IabHelper(UnityPlayer.currentActivity, str);
        this.mHelper.enableDebugLogging(this.debugLogging, mLogTag);
        this.mHelper.logDebug("IabHelper Starting setup.");
        this.mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.1
            @Override // com.android.iab.util.IabHelper.OnIabSetupFinishedListener
            public void onIabSetupFinished(IabResult iabResult) {
                CaoCaoIABActivity.this.mHelper.logDebug("IabHelper Setup Finished.");
                if (iabResult.isFailure()) {
                    Log.e(CaoCaoIABActivity.mLogTag, "IabHelper Setup Failed. result: " + iabResult);
                    UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_BILLING_INIT_FAILED, iabResult.getMessageForUnity());
                } else {
                    CaoCaoIABActivity.this.mHelper.logDebug("IabHelper Setup Succeed.");
                    UnityPlayer.UnitySendMessage(CaoCaoIABActivity.CAOCAO_IAB_EVENTMANAGER, CaoCaoIABActivity.CALLBACK_BILLING_INIT_SUCCEED, iabResult.getMessageForUnity());
                    CaoCaoIABActivity.this.initialized = true;
                }
            }
        });
    }

    public void PurchaseProduct(final String str, final String str2) {
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.6
            @Override // java.lang.Runnable
            public void run() {
                CaoCaoIABActivity.this.mHelper.logDebug("purchaseproduct - developerPayload: " + str2);
                CaoCaoIABActivity.this.StartPurchaseFlow(str, "inapp", str2);
            }
        });
    }

    public void PurchaseSubscription(final String str, final String str2) {
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.7
            @Override // java.lang.Runnable
            public void run() {
                CaoCaoIABActivity.this.mHelper.logDebug("purchasesubscription - developerPayload: " + str2);
                CaoCaoIABActivity.this.StartPurchaseFlow(str, "subs", str2);
            }
        });
    }

    public void QueryInventory() {
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.2
            @Override // java.lang.Runnable
            public void run() {
                CaoCaoIABActivity.this.mHelper.logDebug("Query Inventory.");
                CaoCaoIABActivity.this.mHelper.queryInventoryAsync(true, CaoCaoIABActivity.this.queryInventoryFinishedListner);
            }
        });
    }

    public void QueryInventory(final String[] strArr) {
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.3
            @Override // java.lang.Runnable
            public void run() {
                CaoCaoIABActivity.this.mHelper.logDebug("Query Inventory.");
                CaoCaoIABActivity.this.mHelper.logDebug("[Query Inventory] skus: " + strArr.toString());
                CaoCaoIABActivity.this.mHelper.queryInventoryAsync(true, Arrays.asList(strArr), CaoCaoIABActivity.this.queryInventoryFinishedListner);
            }
        });
    }

    public void QueryInventory(final String[] strArr, final String[] strArr2) {
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() { // from class: com.nexon.caocao.iab.CaoCaoIABActivity.4
            @Override // java.lang.Runnable
            public void run() {
                CaoCaoIABActivity.this.mHelper.logDebug("Query Inventory.");
                ArrayList arrayList = new ArrayList(strArr.length + strArr2.length);
                for (String str : strArr) {
                    arrayList.add(str);
                }
                for (String str2 : strArr2) {
                    arrayList.add(str2);
                }
                CaoCaoIABActivity.this.mHelper.queryInventoryAsync(true, arrayList, CaoCaoIABActivity.this.queryInventoryFinishedListner);
            }
        });
    }

    public void enableDebugLogging(boolean z) {
        this.debugLogging = z;
    }

    public IabHelper getHelper() {
        return this.mHelper;
    }

    public IabHelper.OnIabPurchaseFinishedListener getPurchaseFinishedListener() {
        return this.iabPurchaseFinishedListener;
    }

    boolean verifyDeveloperPayload(Purchase purchase) {
        purchase.getDeveloperPayload();
        return true;
    }
}
