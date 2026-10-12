package com.nexon.platform.store.billing;

import com.nexon.platform.store.billing.vendor.VendorHolder;
import com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface;
import com.nexon.platform.store.billing.vendor.interfaces.VendorInterface;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderVendorPurchaseState extends OrderState {
    private static final String TAG = OrderVendorPurchaseState.class.getName();

    OrderVendorPurchaseState() {
    }

    @Override // com.nexon.platform.store.billing.OrderState
    void process(final Order order) {
        final Transaction transaction = order.getTransaction();
        Logger.d(TAG, "[Billing#" + order.getLoggerSerialNumber() + "] Attempt to vendor-purchase in Android In-App Billing");
        VendorHolder.getInstance().getVendorInterface().purchaseProduct(order.getActivity(), transaction.getProductId(), transaction.getPayload(), transaction.getEncryptedUserId(), new VendorInterface.IABPurchaseCallback() { // from class: com.nexon.platform.store.billing.OrderVendorPurchaseState.1
            @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface.IABPurchaseCallback
            public void onFailed(Constants.ErrorCode errorCode) {
                Logger.d(OrderVendorPurchaseState.TAG, "[Billing#" + order.getLoggerSerialNumber() + "] Vendor purchase failure");
                transaction.setError(Error.createError(errorCode));
                if (errorCode == Constants.ErrorCode.BillingVendorPurchaseUserCancel) {
                    order.setOrderState(new OrderCancelState());
                } else {
                    order.setOrderState(new OrderFailState());
                }
            }

            @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface.IABPurchaseCallback
            public void onSuccess(PurchaseInterface purchaseInterface) {
                Logger.d(OrderVendorPurchaseState.TAG, "[Billing#" + order.getLoggerSerialNumber() + "] Vendor purchase success");
                transaction.setProductId(purchaseInterface.getProductId());
                transaction.setPayload(purchaseInterface.getDeveloperPayload());
                transaction.setPurchaseData(purchaseInterface.getPurchaseData());
                transaction.setState(Transaction.State.VendorPurchased);
                order.setOrderState(new OrderPurchaseState());
            }
        });
    }
}
