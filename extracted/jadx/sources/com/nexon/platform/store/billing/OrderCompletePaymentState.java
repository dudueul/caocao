package com.nexon.platform.store.billing;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderCompletePaymentState extends OrderState {
    private static final String TAG = OrderCompletePaymentState.class.getName();

    OrderCompletePaymentState() {
    }

    @Override // com.nexon.platform.store.billing.OrderState
    void process(Order order) {
        Transaction transaction = order.getTransaction();
        Logger.d(TAG, "[Billing#" + order.getLoggerSerialNumber() + "] Waiting to Delivery - " + transaction);
        transaction.setState(Transaction.State.PaymentCompleted);
        Logger.d(TAG, " Game must process below.");
        Logger.d(TAG, "1. Game        >>>  Game Server  - Request delivery item with Token");
        Logger.d(TAG, "2. Game Server >>>  Stamp Server - Validate item with Token");
        Logger.d(TAG, "3. Game Server                   - Delivery item");
        Logger.d(TAG, "4. Game Server >>>  Game         - Response delivery item with Token");
    }
}
