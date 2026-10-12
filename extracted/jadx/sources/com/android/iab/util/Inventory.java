package com.android.iab.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Inventory {
    Map<String, SkuDetails> mSkuMap = new HashMap();
    Map<String, Purchase> mPurchaseMap = new HashMap();

    Inventory() {
    }

    void addPurchase(Purchase purchase) {
        this.mPurchaseMap.put(purchase.getSku(), purchase);
    }

    void addSkuDetails(SkuDetails skuDetails) {
        this.mSkuMap.put(skuDetails.getSku(), skuDetails);
    }

    public void erasePurchase(String str) {
        if (this.mPurchaseMap.containsKey(str)) {
            this.mPurchaseMap.remove(str);
        }
    }

    List<String> getAllOwnedSkus() {
        return new ArrayList(this.mPurchaseMap.keySet());
    }

    public List<String> getAllOwnedSkus(String str) {
        ArrayList arrayList = new ArrayList();
        for (Purchase purchase : this.mPurchaseMap.values()) {
            if (purchase.getItemType().equals(str)) {
                arrayList.add(purchase.getSku());
            }
        }
        return arrayList;
    }

    List<Purchase> getAllPurchases() {
        return new ArrayList(this.mPurchaseMap.values());
    }

    public Purchase getPurchase(String str) {
        return this.mPurchaseMap.get(str);
    }

    public Map<String, Purchase> getPurchaseMap() {
        return Collections.unmodifiableMap(this.mPurchaseMap);
    }

    public SkuDetails getSkuDetails(String str) {
        return this.mSkuMap.get(str);
    }

    public Map<String, SkuDetails> getSkuMap() {
        return Collections.unmodifiableMap(this.mSkuMap);
    }

    public boolean hasDetails(String str) {
        return this.mSkuMap.containsKey(str);
    }

    public boolean hasPurchase(String str) {
        return this.mPurchaseMap.containsKey(str);
    }
}
