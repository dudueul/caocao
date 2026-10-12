package com.nexon.platform.store.vendor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

    public List<Purchase> getAllPurchases() {
        return new ArrayList(this.mPurchaseMap.values());
    }

    public List<SkuDetails> getAllSkuDetails() {
        ArrayList arrayList = new ArrayList();
        Iterator<SkuDetails> it = this.mSkuMap.values().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public Purchase getPurchase(String str) {
        return this.mPurchaseMap.get(str);
    }

    public SkuDetails getSkuDetails(String str) {
        return this.mSkuMap.get(str);
    }

    public boolean hasDetails(String str) {
        return this.mSkuMap.containsKey(str);
    }

    public boolean hasPurchase(String str) {
        return this.mPurchaseMap.containsKey(str);
    }
}
