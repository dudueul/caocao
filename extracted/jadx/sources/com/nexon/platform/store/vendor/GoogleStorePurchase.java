package com.nexon.platform.store.vendor;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.nexon.platform.store.billing.Logger;
import com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface;
import com.nexon.platform.store.internal.Utility;
import com.nexon.platform.store.vendor.util.Purchase;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class GoogleStorePurchase implements PurchaseInterface {
    private static final String TAG = GoogleStorePurchase.class.getName();
    Purchase purchase;

    public GoogleStorePurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public String getDeveloperPayload() {
        return this.purchase.getDeveloperPayload();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public String getItemType() {
        return this.purchase.getItemType();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public String getOrderId() {
        return this.purchase.getOrderId();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public Object getOriginPurchaseObject() {
        return this.purchase;
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public String getOriginalJson() {
        return this.purchase.getOriginalJson();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public String getPackageName() {
        return this.purchase.getPackageName();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public String getProductId() {
        return this.purchase.getSku();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public String getPurchaseData() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("order_id", getOrderId());
            jSONObject.put("product_id", getProductId());
            jSONObject.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, getDeveloperPayload());
            jSONObject.put("receipt", getOriginalJson());
            return Utility.base64EncodeStr(jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(TAG, "getPurchaseData exception:" + e.toString());
            return null;
        }
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public int getPurchaseState() {
        return this.purchase.getPurchaseState();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public long getPurchaseTime() {
        return this.purchase.getPurchaseTime();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public String getSignature() {
        return this.purchase.getSignature();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public String getToken() {
        return this.purchase.getToken();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public boolean isAutoRenewing() {
        return this.purchase.isAutoRenewing();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface
    public JSONObject toJson() {
        return this.purchase.toJson();
    }
}
