package com.nexon.platform.store.billing;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderPromotionIssueState extends OrderIssueState {
    OrderPromotionIssueState() {
    }

    @Override // com.nexon.platform.store.billing.OrderIssueState
    protected String getIssueType() {
        return OrderIssueState.ISSUE_TYPE_PROMOTION;
    }

    @Override // com.nexon.platform.store.billing.OrderIssueState
    protected OrderState getNextOrderState() {
        return new OrderPurchaseState();
    }
}
