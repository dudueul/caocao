package com.nexon.platform.store.vendor.util;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SkuDetails {
    private final String mDescription;
    private final String mItemType;
    private final String mJson;
    private final String mPrice;
    private final long mPriceAmountMicros;
    private final String mPriceCurrencyCode;
    private final String mSku;
    private final String mTitle;
    private final String mType;

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
        this.mPriceAmountMicros = jSONObject.optLong("price_amount_micros");
        this.mPriceCurrencyCode = jSONObject.optString("price_currency_code");
        this.mTitle = jSONObject.optString("title");
        this.mDescription = jSONObject.optString("description");
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public long getPriceAmountMicros() {
        return this.mPriceAmountMicros;
    }

    public String getPriceCurrencyCode() {
        return this.mPriceCurrencyCode;
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

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("productId", getSku());
            jSONObject.put("type", getType());
            jSONObject.put("price", getPrice());
            jSONObject.put("price_amount_micros", getPriceAmountMicros());
            jSONObject.put("price_currency_code", getPriceCurrencyCode());
            jSONObject.put("title", getTitle());
            jSONObject.put("description", getDescription());
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public String toString() {
        return "SkuDetails:" + this.mJson;
    }
}
