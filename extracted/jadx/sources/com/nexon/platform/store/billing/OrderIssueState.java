package com.nexon.platform.store.billing;

import com.facebook.AccessToken;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.platform.store.NexonStore;
import com.nexon.platform.store.billing.request.NXToyIssueRequest;
import com.nexon.platform.store.billing.result.NXToyIssueResult;
import com.nexon.platform.store.billing.vendor.VendorHolder;
import com.nexon.platform.store.internal.Utility;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class OrderIssueState extends OrderState {
    public static final String ISSUE_TYPE_NORMAL = "normal";
    public static final String ISSUE_TYPE_PROMOTION = "promotion";
    private static final String TAG = OrderIssueState.class.getName();

    OrderIssueState() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getReprocessedPayloadForOneStore(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(Utility.base64DecodeStr(str));
            StringBuilder sb = new StringBuilder();
            sb.append(jSONObject.getString("stamp_id")).append(",").append(jSONObject.getString(AccessToken.USER_ID_KEY));
            return sb.toString();
        } catch (Exception e) {
            Logger.e(TAG, "In getReprocessedPayloadForOneStore, exception:" + e.toString());
            return null;
        }
    }

    protected Transaction.State getCompletionState() {
        return Transaction.State.Issued;
    }

    protected String getIssueType() {
        return "normal";
    }

    protected OrderState getNextOrderState() {
        return new OrderVendorPurchaseState();
    }

    @Override // com.nexon.platform.store.billing.OrderState
    void process(final Order order) {
        Transaction transaction = order.getTransaction();
        Logger.d(TAG, "[Billing#" + order.getLoggerSerialNumber() + "] Attempt to issue request");
        if (NexonStore.isInitialized()) {
            NXToyRequestPostman.getInstance().postRequest(new NXToyIssueRequest(transaction.getUserId(), transaction.getProductId(), NexonStore.getClientId(), transaction.getMeta(), transaction.getServicePayload(), transaction.getStampParameters(), getIssueType()), new NXToyRequestListener() { // from class: com.nexon.platform.store.billing.OrderIssueState.1
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        order.getTransaction().setError(Error.createError(Utility.wrappingToyErrorToStoreErrorCode(nXToyResult.errorCode), nXToyResult.errorText, nXToyResult.errorDetail));
                        order.setOrderState(new OrderFailState());
                        return;
                    }
                    Transaction transaction2 = order.getTransaction();
                    NXToyIssueResult nXToyIssueResult = (NXToyIssueResult) nXToyResult;
                    String str = nXToyIssueResult.stamp_id;
                    String storeType = VendorHolder.getInstance().getVendorInterface().getStoreType();
                    String str2 = nXToyIssueResult.enc_user_id;
                    ToyLog.d("storeType:" + storeType);
                    String reprocessedPayloadForOneStore = null;
                    if (NexonStore.MARKET_TYPE_GOOGLE_STORE.equals(storeType)) {
                        reprocessedPayloadForOneStore = nXToyIssueResult.payload;
                    } else if (NexonStore.MARKET_TYPE_ONE_STORE.equals(storeType)) {
                        reprocessedPayloadForOneStore = OrderIssueState.this.getReprocessedPayloadForOneStore(nXToyIssueResult.payload);
                    }
                    if (Utility.isNullOrEmpty(str) || Utility.isNullOrEmpty(reprocessedPayloadForOneStore)) {
                        Logger.e(OrderIssueState.TAG, "stampId or developerPayload shouldn't be null  stampId:" + str + ", developerPayload:" + reprocessedPayloadForOneStore);
                        transaction2.setError(Constants.ErrorCode.ServerResponseInvalidError);
                        order.setOrderState(new OrderFailState());
                    } else {
                        ToyLog.d("transaction.servicePayload:" + transaction2.getServicePayload() + " ,  issueResult.payload:" + reprocessedPayloadForOneStore);
                        transaction2.setPayload(reprocessedPayloadForOneStore);
                        transaction2.setStampId(str);
                        transaction2.setState(OrderIssueState.this.getCompletionState());
                        transaction2.setEncryptedUserId(str2);
                        order.setOrderState(OrderIssueState.this.getNextOrderState());
                    }
                }
            });
        } else {
            transaction.setError(Error.createError(Constants.ErrorCode.BillingIabNotInitializedError));
            order.setOrderState(new OrderFailState());
        }
    }
}
