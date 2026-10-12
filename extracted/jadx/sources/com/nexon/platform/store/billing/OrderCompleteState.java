package com.nexon.platform.store.billing;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderCompleteState extends OrderState {
    private static final String TAG = OrderCompleteState.class.getName();

    OrderCompleteState() {
    }

    @Override // com.nexon.platform.store.billing.OrderState
    void process(Order order) {
        Logger.d(TAG, "[Billing#" + order.getLoggerSerialNumber() + "]");
    }
}
