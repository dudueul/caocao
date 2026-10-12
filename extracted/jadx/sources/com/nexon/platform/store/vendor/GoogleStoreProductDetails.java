package com.nexon.platform.store.vendor;

import com.nexon.platform.store.billing.vendor.interfaces.ProductInterface;
import com.nexon.platform.store.vendor.util.SkuDetails;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class GoogleStoreProductDetails implements ProductInterface {
    private SkuDetails skuDetails;

    public GoogleStoreProductDetails(SkuDetails skuDetails) {
        this.skuDetails = skuDetails;
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.ProductInterface
    public String getDescription() {
        return this.skuDetails.getDescription();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.ProductInterface
    public String getPrice() {
        return this.skuDetails.getPrice();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.ProductInterface
    public long getPriceAmountMicros() {
        return this.skuDetails.getPriceAmountMicros();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.ProductInterface
    public String getPriceCurrencyCode() {
        return this.skuDetails.getPriceCurrencyCode();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.ProductInterface
    public String getProductId() {
        return this.skuDetails.getSku();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.ProductInterface
    public String getTitle() {
        return this.skuDetails.getTitle();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.ProductInterface
    public String getType() {
        return this.skuDetails.getType();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.ProductInterface
    public JSONObject toJson() {
        return this.skuDetails.toJson();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.ProductInterface
    public String toString() {
        return super.toString();
    }
}
