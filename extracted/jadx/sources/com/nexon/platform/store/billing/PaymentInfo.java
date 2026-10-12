package com.nexon.platform.store.billing;

import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PaymentInfo {
    private String characterId;
    private String itemId;
    private JSONObject meta;
    private String productId;
    private JSONObject servicePayload;
    private String userId;

    public PaymentInfo(String str) {
        this.productId = str;
    }

    public String getCharacterId() {
        return this.characterId;
    }

    public String getItemId() {
        return this.itemId;
    }

    public JSONObject getMeta() {
        return this.meta;
    }

    public String getProductId() {
        return this.productId;
    }

    public JSONObject getServicePayload() {
        return this.servicePayload;
    }

    public String getUserId() {
        return this.userId;
    }

    public PaymentInfo setCharacterId(String str) {
        this.characterId = str;
        return this;
    }

    public PaymentInfo setItemId(String str) {
        this.itemId = str;
        return this;
    }

    public PaymentInfo setMeta(JSONObject jSONObject) {
        this.meta = jSONObject;
        return this;
    }

    public PaymentInfo setProductId(String str) {
        this.productId = str;
        return this;
    }

    public PaymentInfo setServicePayload(JSONObject jSONObject) {
        this.servicePayload = jSONObject;
        return this;
    }

    public PaymentInfo setUserId(String str) {
        this.userId = str;
        return this;
    }

    public String toString() {
        return "productId:" + this.productId + ", userId:" + this.userId + ", meta:" + this.meta + ", servicePayload:" + this.servicePayload + ", itemId:" + this.itemId + ", characterId:" + this.characterId;
    }
}
