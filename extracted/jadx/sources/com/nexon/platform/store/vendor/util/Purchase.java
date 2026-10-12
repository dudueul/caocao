package com.nexon.platform.store.vendor.util;

import com.facebook.share.internal.MessengerShareContentUtility;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Purchase {
    String mDeveloperPayload;
    boolean mIsAutoRenewing;
    String mItemType;
    String mOrderId;
    String mOriginalJson;
    String mPackageName;
    int mPurchaseState;
    long mPurchaseTime;
    String mSignature;
    String mSku;
    String mToken;

    public Purchase(String str, String str2, String str3) throws JSONException {
        this.mItemType = str;
        this.mOriginalJson = str2;
        JSONObject jSONObject = new JSONObject(this.mOriginalJson);
        this.mOrderId = jSONObject.optString("orderId");
        this.mPackageName = jSONObject.optString("packageName");
        this.mSku = jSONObject.optString("productId");
        this.mPurchaseTime = jSONObject.optLong("purchaseTime");
        this.mPurchaseState = jSONObject.optInt("purchaseState");
        this.mDeveloperPayload = jSONObject.optString("developerPayload");
        this.mToken = jSONObject.optString("token", jSONObject.optString("purchaseToken"));
        this.mIsAutoRenewing = jSONObject.optBoolean("autoRenewing");
        this.mSignature = str3;
    }

    public String getDeveloperPayload() {
        return this.mDeveloperPayload;
    }

    public String getItemType() {
        return this.mItemType;
    }

    public String getOrderId() {
        return this.mOrderId;
    }

    public String getOriginalJson() {
        return this.mOriginalJson;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public int getPurchaseState() {
        return this.mPurchaseState;
    }

    public long getPurchaseTime() {
        return this.mPurchaseTime;
    }

    public String getSignature() {
        return this.mSignature;
    }

    public String getSku() {
        return this.mSku;
    }

    public String getToken() {
        return this.mToken;
    }

    public boolean isAutoRenewing() {
        return this.mIsAutoRenewing;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("itemType", getItemType());
            jSONObject.put("orderId", getOrderId());
            jSONObject.put("productId", getSku());
            jSONObject.put("signature", getSignature());
            jSONObject.put("receipt", getOriginalJson());
            jSONObject.put("token", getToken());
            jSONObject.put("packageName", getPackageName());
            jSONObject.put("purchaseTime", getPurchaseTime());
            jSONObject.put("purchaseState", getPurchaseState());
            jSONObject.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, getDeveloperPayload());
            jSONObject.put("isAutoRenewing", isAutoRenewing());
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public String toString() {
        return "PurchaseInfo(type:" + this.mItemType + "):" + this.mOriginalJson;
    }
}
