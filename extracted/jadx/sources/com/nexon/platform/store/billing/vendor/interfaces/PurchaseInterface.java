package com.nexon.platform.store.billing.vendor.interfaces;

import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface PurchaseInterface<T> {
    String getDeveloperPayload();

    String getItemType();

    String getOrderId();

    T getOriginPurchaseObject();

    String getOriginalJson();

    String getPackageName();

    String getProductId();

    String getPurchaseData();

    int getPurchaseState();

    long getPurchaseTime();

    String getSignature();

    String getToken();

    boolean isAutoRenewing();

    JSONObject toJson();
}
