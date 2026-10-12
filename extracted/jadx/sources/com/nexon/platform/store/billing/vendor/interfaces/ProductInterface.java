package com.nexon.platform.store.billing.vendor.interfaces;

import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface ProductInterface {
    String getDescription();

    String getPrice();

    long getPriceAmountMicros();

    String getPriceCurrencyCode();

    String getProductId();

    String getTitle();

    String getType();

    JSONObject toJson();

    String toString();
}
