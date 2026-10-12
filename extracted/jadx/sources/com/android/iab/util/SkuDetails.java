package com.android.iab.util;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SkuDetails {
    String mDescription;
    String mItemType;
    String mJson;
    String mPrice;
    String mPriceCurrencyCode;
    String mPriceMicros;
    String mSku;
    String mTitle;
    String mType;

    public SkuDetails(String str) throws JSONException {
        this("inapp", str);
    }

    public SkuDetails(String str, String str2) throws JSONException {
        this.mItemType = str;
        this.mJson = str2;
        JSONObject jSONObject = new JSONObject(this.mJson);
        this.mSku = jSONObject.optString("productId");
        this.mType = jSONObject.optString("type");
        this.mPrice = jSONObject.optString("price");
        this.mTitle = jSONObject.optString("title");
        this.mDescription = jSONObject.optString("description");
        this.mPriceCurrencyCode = jSONObject.optString("price_currency_code");
        this.mPriceMicros = jSONObject.optString("price_amount_micros");
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getItemType() {
        return this.mItemType;
    }

    public String getJson() {
        return this.mJson;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public String getPriceCurrencyCode() {
        return this.mPriceCurrencyCode;
    }

    public String getPriceMicros() {
        return this.mPriceMicros;
    }

    public String getSku() {
        return this.mSku;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getType() {
        return this.mType;
    }

    public String toString() {
        return "SkuDetails:" + this.mJson;
    }
}
