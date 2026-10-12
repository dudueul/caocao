package com.nexon.platform.store.billing;

import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.platform.store.billing.request.NXToyCancelRequest;
import com.nexon.platform.store.internal.Utility;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderCancelState extends OrderState {
    private static final String TAG = OrderCancelState.class.getName();

    OrderCancelState() {
    }

    @Override // com.nexon.platform.store.billing.OrderState
    void process(final Order order) {
        final Transaction transaction = order.getTransaction();
        Logger.d(TAG, "[Billing#" + order.getLoggerSerialNumber() + "]");
        String stampId = transaction.getStampId();
        if (!Utility.isNullOrEmpty(stampId)) {
            NXToyRequestPostman.getInstance().postRequest(new NXToyCancelRequest(stampId, "user_cancel", "canceled by user"), new NXToyRequestListener() { // from class: com.nexon.platform.store.billing.OrderCancelState.1
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        transaction.setError(Error.createError(Utility.wrappingToyErrorToStoreErrorCode(nXToyResult.errorCode), nXToyResult.errorText, nXToyResult.errorDetail));
                        order.setOrderState(new OrderFailState());
                    }
                    transaction.setState(Transaction.State.Canceled);
                    order.setOrderState(new OrderCompleteState());
                }
            });
        } else {
            Constants.ErrorCode errorCode = Constants.ErrorCode.ParameterInvalidError;
            transaction.setError(Error.createError(errorCode.getValue(), errorCode.getMessage(), errorCode.getMessage()));
            order.setOrderState(new OrderFailState());
        }
    }
}
