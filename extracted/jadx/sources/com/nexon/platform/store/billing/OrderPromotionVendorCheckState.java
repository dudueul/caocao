package com.nexon.platform.store.billing;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderPromotionVendorCheckState extends OrderVendorCheckState {
    OrderPromotionVendorCheckState() {
    }

    @Override // com.nexon.platform.store.billing.OrderVendorCheckState
    protected OrderState getNextOrderState() {
        return new OrderPromotionIssueState();
    }
}
