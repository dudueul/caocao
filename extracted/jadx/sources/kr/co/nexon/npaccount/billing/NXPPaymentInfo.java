package kr.co.nexon.npaccount.billing;

import com.nexon.platform.store.billing.PaymentInfo;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPPaymentInfo {
    private String characterId;
    private String itemId;
    private JSONObject meta;
    private String productId;
    private JSONObject servicePayload;

    public NXPPaymentInfo(String str) {
        this.productId = str;
    }

    public PaymentInfo createPaymentInfo(String str) {
        return new PaymentInfo(this.productId).setUserId(str).setMeta(this.meta).setServicePayload(this.servicePayload).setItemId(this.itemId).setCharacterId(this.characterId);
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

    public NXPPaymentInfo setCharacterId(String str) {
        this.characterId = str;
        return this;
    }

    public NXPPaymentInfo setItemId(String str) {
        this.itemId = str;
        return this;
    }

    public NXPPaymentInfo setMeta(JSONObject jSONObject) {
        this.meta = jSONObject;
        return this;
    }

    public NXPPaymentInfo setProductId(String str) {
        this.productId = str;
        return this;
    }

    public NXPPaymentInfo setServicePayload(JSONObject jSONObject) {
        this.servicePayload = jSONObject;
        return this;
    }

    public String toString() {
        return "productId:" + this.productId + ", meta:" + this.meta + ", servicePayload:" + this.servicePayload + ", itemId:" + this.itemId + ", characterId:" + this.characterId;
    }
}
