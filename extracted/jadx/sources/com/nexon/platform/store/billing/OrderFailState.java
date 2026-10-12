package com.nexon.platform.store.billing;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderFailState extends OrderState {
    private static final String TAG = OrderFailState.class.getName();

    OrderFailState() {
    }

    @Override // com.nexon.platform.store.billing.OrderState
    void process(Order order) {
        Transaction transaction = order.getTransaction();
        Error error = transaction.getError();
        if (error != null) {
            Logger.e(TAG, "code: " + error.getCode() + ", message :" + error.getMessage());
        }
        Logger.d(TAG, "[Billing#" + order.getLoggerSerialNumber() + "]");
        transaction.setState(Transaction.State.Failed);
        order.setOrderState(new OrderCompleteState());
    }
}
