package com.nexon.platform.store.billing;

import android.app.Activity;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class Order {
    private static final String TAG = Order.class.getName();
    private static Integer g_serialNumberCounter = 1111;
    private Activity activity;
    private Integer loggerSerialNumber = generateSerialNumber();
    private OrderState orderState;
    private ProcessCallback processCallback;
    private Transaction transaction;

    interface ProcessCallback {
        void onCompleted(Transaction transaction);
    }

    Order(Transaction transaction) {
        this.transaction = transaction;
    }

    private Integer generateSerialNumber() {
        Integer num = g_serialNumberCounter;
        g_serialNumberCounter = Integer.valueOf(g_serialNumberCounter.intValue() + 1);
        return num;
    }

    Activity getActivity() {
        return this.activity;
    }

    Integer getLoggerSerialNumber() {
        return this.loggerSerialNumber;
    }

    Transaction getTransaction() {
        return this.transaction;
    }

    boolean isCompletedOrderState(OrderState orderState) {
        if (orderState != null && !(orderState instanceof OrderPromotionVendorCheckState) && !(orderState instanceof OrderVendorCheckState) && !(orderState instanceof OrderPromotionIssueState) && !(orderState instanceof OrderIssueState) && !(orderState instanceof OrderVendorPurchaseState) && !(orderState instanceof OrderPurchaseState) && !(orderState instanceof OrderVendorPreConsumeState) && !(orderState instanceof OrderRefreshTokenState) && !(orderState instanceof OrderConsumeState) && !(orderState instanceof OrderVendorPostConsumeState)) {
            if (orderState instanceof OrderCompletePaymentState) {
                return true;
            }
            if (!(orderState instanceof OrderCancelState) && !(orderState instanceof OrderFailState) && (orderState instanceof OrderCompleteState)) {
                return true;
            }
        }
        return false;
    }

    void process(ProcessCallback processCallback) {
        Logger.d(TAG, "==================================");
        this.processCallback = processCallback;
        switch (this.transaction.getState()) {
            case Initialized:
                setOrderState(new OrderVendorCheckState());
                break;
            case PromotionInitialized:
                setOrderState(new OrderPromotionVendorCheckState());
                break;
            case VendorPurchased:
                setOrderState(new OrderPurchaseState());
                break;
            case Consumed:
                setOrderState(new OrderRefreshTokenState());
                break;
            default:
                Logger.e(TAG, "In process, transaction state not matched. state:" + this.transaction.getState());
                this.transaction.setError(Error.createError(Constants.ErrorCode.BillingInvalidTransactionStateError));
                setOrderState(new OrderFailState());
                break;
        }
    }

    void processOrderState() {
        Processor.willProcess(this.transaction);
        if (this.orderState != null) {
            Logger.d(TAG, "--------------------------------");
            Logger.d(TAG, "[Billing#" + getLoggerSerialNumber() + "] " + this.orderState.getClass().getSimpleName() + " will process.");
            Logger.d(TAG, "[" + this.transaction + "]");
            this.orderState.process(this);
        }
    }

    void setActivity(Activity activity) {
        this.activity = activity;
    }

    void setOrderState(OrderState orderState) {
        this.orderState = orderState;
        BreakPoint.checkContinue(this.orderState.getClass().getSimpleName(), isCompletedOrderState(this.orderState), new BreakPoint.ActionCallback() { // from class: com.nexon.platform.store.billing.Order.1
            @Override // com.nexon.platform.store.billing.BreakPoint.ActionCallback
            public void onContinued(Error error) {
                if (error != null) {
                    this.transaction.setError(error);
                    this.orderState = new OrderCompleteState();
                }
                Order.this.processOrderState();
                if (!Order.this.isCompletedOrderState(this.orderState) || this.processCallback == null) {
                    return;
                }
                this.processCallback.onCompleted(this.transaction);
                this.processCallback = null;
            }
        });
    }
}
