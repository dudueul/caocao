package com.nexon.platform.store.billing;

import com.nexon.platform.store.billing.vendor.VendorHolder;
import com.nexon.platform.store.billing.vendor.interfaces.VendorInterface;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderVendorPreConsumeState extends OrderState {
    private static final String TAG = OrderVendorPreConsumeState.class.getName();

    OrderVendorPreConsumeState() {
    }

    @Override // com.nexon.platform.store.billing.OrderState
    void process(final Order order) {
        final Transaction transaction = order.getTransaction();
        Logger.d(TAG, "[Billing#" + order.getLoggerSerialNumber() + "] Attempt to vendor-consume in Android In-App Billing");
        VendorHolder.getInstance().getVendorInterface().consumeProduct(transaction.getProductId(), new VendorInterface.IABConsumeCallback() { // from class: com.nexon.platform.store.billing.OrderVendorPreConsumeState.1
            @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface.IABConsumeCallback
            public void onFailed(Constants.ErrorCode errorCode) {
                Logger.d(OrderVendorPreConsumeState.TAG, "[Billing#" + order.getLoggerSerialNumber() + "] Vendor consume failure - errorCode:" + errorCode.getValue() + ", errorMsg:" + errorCode.getMessage());
                transaction.setError(Error.createError(errorCode));
                order.setOrderState(new OrderFailState());
            }

            @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface.IABConsumeCallback
            public void onSuccess() {
                transaction.setState(Transaction.State.VendorPreConsumed);
                order.setOrderState(new OrderConsumeState());
            }
        });
    }
}
