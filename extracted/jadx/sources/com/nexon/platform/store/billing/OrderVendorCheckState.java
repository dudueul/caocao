package com.nexon.platform.store.billing;

import com.nexon.platform.store.billing.vendor.VendorHolder;
import com.nexon.platform.store.billing.vendor.interfaces.ProductInterface;
import com.nexon.platform.store.billing.vendor.interfaces.VendorInterface;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderVendorCheckState extends OrderState {
    private static final String TAG = OrderVendorCheckState.class.getName();

    OrderVendorCheckState() {
    }

    protected Transaction.State getCompletionState() {
        return Transaction.State.VendorChecked;
    }

    protected OrderState getNextOrderState() {
        return new OrderIssueState();
    }

    @Override // com.nexon.platform.store.billing.OrderState
    void process(final Order order) {
        final Transaction transaction = order.getTransaction();
        Logger.d(TAG, "[Billing#" + order.getLoggerSerialNumber() + "] Checking the product is available.");
        VendorHolder.getInstance().getVendorInterface().checkPurchasableProduct(transaction.getProductId(), new VendorInterface.IABPurchasableProductCallback() { // from class: com.nexon.platform.store.billing.OrderVendorCheckState.1
            @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface.IABPurchasableProductCallback
            public void onResult(ProductInterface productInterface, Constants.ErrorCode errorCode) {
                if (errorCode != null) {
                    transaction.setError(Error.createError(errorCode));
                    order.setOrderState(new OrderFailState());
                    return;
                }
                if (productInterface == null) {
                    Logger.d(OrderVendorCheckState.TAG, "In OrderVendorCheckState, productDetails is null!!! internal logic error.");
                    transaction.setError(Error.createError(Constants.ErrorCode.UnknownError));
                    order.setOrderState(new OrderFailState());
                    return;
                }
                JSONObject stampParameters = transaction.getStampParameters();
                try {
                    stampParameters.put("price", String.valueOf(productInterface.getPriceAmountMicros()));
                    stampParameters.put("currency", productInterface.getPriceCurrencyCode());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                transaction.setProduct(productInterface);
                transaction.setStampParameters(stampParameters);
                transaction.setState(OrderVendorCheckState.this.getCompletionState());
                order.setOrderState(OrderVendorCheckState.this.getNextOrderState());
            }
        });
    }
}
