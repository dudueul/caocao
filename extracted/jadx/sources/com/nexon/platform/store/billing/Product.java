package com.nexon.platform.store.billing;

import com.nexon.platform.store.billing.vendor.interfaces.ProductInterface;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Product {
    private String localizedDescription;
    private String localizedPrice;
    private String localizedTitle;
    private long priceAmountMicros;
    private String priceCurrencyCode;
    private ProductInterface productDetail;
    private String productId;
    private String type;
    private boolean valid;

    public Product() {
        this.productDetail = null;
        this.type = "";
        this.localizedDescription = "";
        this.localizedPrice = "";
        this.localizedTitle = "";
        this.priceCurrencyCode = "";
        this.productId = "";
        this.valid = false;
    }

    public Product(ProductInterface productInterface) {
        if (productInterface == null) {
            this.valid = false;
            this.priceAmountMicros = 0L;
            return;
        }
        this.productDetail = productInterface;
        this.type = productInterface.getType();
        this.localizedDescription = productInterface.getDescription();
        this.localizedPrice = productInterface.getPrice();
        this.localizedTitle = productInterface.getTitle();
        this.priceAmountMicros = productInterface.getPriceAmountMicros();
        this.priceCurrencyCode = productInterface.getPriceCurrencyCode();
        this.productId = productInterface.getProductId();
        this.valid = true;
    }

    public Product(String str) {
        this.productId = str;
        this.valid = false;
        this.priceAmountMicros = 0L;
    }

    public String getLocalizedDescription() {
        return this.localizedDescription;
    }

    public String getLocalizedPrice() {
        return this.localizedPrice;
    }

    public String getLocalizedTitle() {
        return this.localizedTitle;
    }

    public JSONObject getOriginalProductDetailJson() {
        if (this.productDetail == null) {
            return null;
        }
        return this.productDetail.toJson();
    }

    public long getPriceAmountMicros() {
        return this.priceAmountMicros;
    }

    public String getPriceCurrencyCode() {
        return this.priceCurrencyCode;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getType() {
        return this.type;
    }

    public boolean isValid() {
        return this.valid;
    }

    public String toString() {
        return "valid : " + this.valid + " ,productId : " + this.productId + " ,localizedDescription : " + this.localizedDescription + " ,localizedPrice : " + this.localizedPrice + " ,localizedTitle : " + this.localizedTitle + " ,priceAmountMicros :" + this.priceAmountMicros + " ,priceCurrencyCode : " + this.priceCurrencyCode;
    }
}
