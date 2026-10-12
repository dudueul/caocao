package com.nexon.platform.store.billing;

import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.platform.store.billing.request.NXToyConsumeRequest;
import com.nexon.platform.store.billing.result.NXToyConsumeResult;
import com.nexon.platform.store.internal.Utility;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderConsumeState extends OrderState {
    public static final String CONSUME_TYPE_NORMAL = "normal";
    public static final String CONSUME_TYPE_RESTORE = "restore";
    private static final String TAG = OrderConsumeState.class.getName();

    OrderConsumeState() {
    }

    protected Transaction.State getCompletionState() {
        return Transaction.State.Consumed;
    }

    protected String getConsumeType() {
        return "normal";
    }

    protected OrderState getNextOrderState() {
        return new OrderVendorPostConsumeState();
    }

    @Override // com.nexon.platform.store.billing.OrderState
    void process(final Order order) {
        Transaction transaction = order.getTransaction();
        Logger.d(TAG, "[Billing#" + order.getLoggerSerialNumber() + "] Attempt to server consume");
        String stampId = transaction.getStampId();
        String consumeType = getConsumeType();
        if (!Utility.isNullOrEmpty(stampId) && !Utility.isNullOrEmpty(consumeType)) {
            NXToyRequestPostman.getInstance().postRequest(new NXToyConsumeRequest(stampId, consumeType), new NXToyRequestListener() { // from class: com.nexon.platform.store.billing.OrderConsumeState.1
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        order.getTransaction().setError(Error.createError(Utility.wrappingToyErrorToStoreErrorCode(nXToyResult.errorCode), nXToyResult.errorText, nXToyResult.errorDetail));
                        order.setOrderState(new OrderFailState());
                        return;
                    }
                    Transaction transaction2 = order.getTransaction();
                    NXToyConsumeResult nXToyConsumeResult = (NXToyConsumeResult) nXToyResult;
                    String str = nXToyConsumeResult.stamp_token;
                    List<String> list = nXToyConsumeResult.product_id;
                    String str2 = list != null ? list.get(0) : null;
                    if (Utility.isNullOrEmpty(str) || Utility.isNullOrEmpty(str2)) {
                        Logger.e(OrderConsumeState.TAG, "token or productId shouldn't be null  token:" + str + ", productId:" + str2);
                        transaction2.setError(Constants.ErrorCode.ServerResponseInvalidError);
                        order.setOrderState(new OrderFailState());
                    } else {
                        transaction2.setStampToken(str);
                        transaction2.setProductId(str2);
                        transaction2.setState(OrderConsumeState.this.getCompletionState());
                        order.setOrderState(OrderConsumeState.this.getNextOrderState());
                    }
                }
            });
        } else {
            Constants.ErrorCode errorCode = Constants.ErrorCode.ParameterInvalidError;
            transaction.setError(Error.createError(errorCode.getValue(), errorCode.getMessage(), errorCode.getMessage()));
            Logger.d(TAG, "Parameter is invalid. stampId:" + stampId + "  consumeType:" + consumeType);
        }
    }
}
