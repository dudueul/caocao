package com.nexon.platform.store.billing;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderRefreshTokenState extends OrderConsumeState {
    private static final String TAG = OrderRefreshTokenState.class.getName();

    OrderRefreshTokenState() {
    }

    @Override // com.nexon.platform.store.billing.OrderConsumeState
    protected String getConsumeType() {
        return OrderConsumeState.CONSUME_TYPE_RESTORE;
    }

    @Override // com.nexon.platform.store.billing.OrderConsumeState
    protected OrderState getNextOrderState() {
        return new OrderCompletePaymentState();
    }
}
