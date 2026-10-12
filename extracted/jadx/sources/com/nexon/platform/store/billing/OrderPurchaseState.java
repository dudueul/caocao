package com.nexon.platform.store.billing;

import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.platform.store.billing.request.NXToyPurchaseRequest;
import com.nexon.platform.store.billing.result.NXToyPurchaseResult;
import com.nexon.platform.store.billing.vendor.VendorHolder;
import com.nexon.platform.store.internal.Utility;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderPurchaseState extends OrderState {
    private static final String TAG = OrderPurchaseState.class.getName();

    OrderPurchaseState() {
    }

    @Override // com.nexon.platform.store.billing.OrderState
    void process(final Order order) {
        Transaction transaction = order.getTransaction();
        Logger.d(TAG, "[Billing#" + order.getLoggerSerialNumber() + "] Attempt to server purchase");
        String stampId = transaction.getStampId();
        String purchaseData = transaction.getPurchaseData();
        if (!Utility.isNullOrEmpty(stampId) && !Utility.isNullOrEmpty(purchaseData)) {
            NXToyRequestPostman.getInstance().postRequest(new NXToyPurchaseRequest(stampId, purchaseData), new NXToyRequestListener() { // from class: com.nexon.platform.store.billing.OrderPurchaseState.1
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                        Transaction transaction2 = order.getTransaction();
                        String str = ((NXToyPurchaseResult) nXToyResult).stamp_id;
                        if (Utility.isNullOrEmpty(str)) {
                            Logger.e(OrderPurchaseState.TAG, "stampId shouldn't be null  stampId:" + str);
                            transaction2.setError(Constants.ErrorCode.ServerResponseInvalidError);
                            order.setOrderState(new OrderFailState());
                            return;
                        } else {
                            transaction2.setStampId(str);
                            transaction2.setState(Transaction.State.Purchased);
                            order.setOrderState(new OrderVendorPreConsumeState());
                            return;
                        }
                    }
                    int i = nXToyResult.errorCode;
                    if (i == Constants.ErrorCode.AlreadyConsumedReceipt.getValue() || i == Constants.ErrorCode.PurchaseCanceled.getValue() || i == Constants.ErrorCode.InvalidTransactionFlow.getValue()) {
                        String strOptString = null;
                        try {
                            strOptString = new JSONObject(Utility.base64DecodeStr(order.getTransaction().getPurchaseData())).optString("product_id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        VendorHolder.getInstance().getVendorInterface().consumeProduct(strOptString, null);
                        Logger.d(OrderPurchaseState.TAG, "receipt is invalid. force consume completed. productId:" + strOptString);
                    }
                    order.getTransaction().setError(Error.createError(Utility.wrappingToyErrorToStoreErrorCode(nXToyResult.errorCode), nXToyResult.errorText, nXToyResult.errorDetail));
                    order.setOrderState(new OrderFailState());
                }
            });
        } else {
            Constants.ErrorCode errorCode = Constants.ErrorCode.ParameterInvalidError;
            transaction.setError(Error.createError(errorCode.getValue(), errorCode.getMessage(), errorCode.getMessage()));
            order.setOrderState(new OrderFailState());
            Logger.d(TAG, "Parameter is invalid. stampId:" + stampId + "   purchaseData:" + purchaseData);
        }
    }
}
