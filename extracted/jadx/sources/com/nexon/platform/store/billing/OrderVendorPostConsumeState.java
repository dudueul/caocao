package com.nexon.platform.store.billing;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderVendorPostConsumeState extends OrderState {
    private static final String TAG = OrderVendorPostConsumeState.class.getName();

    OrderVendorPostConsumeState() {
    }

    @Override // com.nexon.platform.store.billing.OrderState
    void process(Order order) {
        Transaction transaction = order.getTransaction();
        Logger.d(TAG, "[Billing#" + order.getLoggerSerialNumber() + "]");
        transaction.setState(Transaction.State.VendorPostConsumed);
        order.setOrderState(new OrderCompletePaymentState());
    }
}
