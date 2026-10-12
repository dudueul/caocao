package com.nexon.platform.store.billing;

import com.nexon.platform.store.billing.vendor.interfaces.ProductInterface;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Transaction {
    private String encryptedUserId;
    private Error error;
    private JSONObject meta;
    private String payload;
    private ProductInterface product;
    private String productId;
    private String purchaseData;
    private JSONObject servicePayload;
    private String stampId;
    private JSONObject stampParameters;
    private String stampToken;
    private State state;
    private ArrayList<Integer> stateTransitions;
    private String userId;

    public enum State {
        Initialized(0),
        PromotionInitialized(2),
        RecoverInitailzed(3),
        VendorChecked(5),
        Issued(10),
        VendorPurchased(15),
        Purchased(20),
        VendorPreConsumed(29),
        Consumed(30),
        VendorPostConsumed(31),
        PaymentCompleted(35),
        Verified(40),
        Canceled(93),
        Failed(99);

        private final int value;

        State(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    Transaction(State state) {
        this.state = State.Initialized;
        this.state = state;
        setMeta(null);
        setServicePayload(null);
        setStampParameters(null);
        setState(state);
    }

    public String getEncryptedUserId() {
        return this.encryptedUserId;
    }

    public Error getError() {
        return this.error;
    }

    public JSONObject getMeta() {
        return this.meta;
    }

    public String getPayload() {
        return this.payload;
    }

    public ProductInterface getProduct() {
        return this.product;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getPurchaseData() {
        return this.purchaseData;
    }

    public JSONObject getServicePayload() {
        return this.servicePayload;
    }

    public String getServicePayloadString() {
        return this.servicePayload.toString();
    }

    public String getStampId() {
        return this.stampId;
    }

    public JSONObject getStampParameters() {
        return this.stampParameters;
    }

    public String getStampToken() {
        return this.stampToken;
    }

    public State getState() {
        return this.state;
    }

    public String getUserId() {
        return this.userId;
    }

    Transaction setEncryptedUserId(String str) {
        this.encryptedUserId = str;
        return this;
    }

    Transaction setError(Constants.ErrorCode errorCode) {
        this.error = Error.createError(errorCode);
        return this;
    }

    Transaction setError(Error error) {
        this.error = error;
        return this;
    }

    Transaction setMeta(JSONObject jSONObject) {
        this.meta = jSONObject;
        if (this.meta == null) {
            this.meta = new JSONObject();
        }
        return this;
    }

    Transaction setPayload(String str) {
        this.payload = str;
        return this;
    }

    Transaction setProduct(ProductInterface productInterface) {
        this.product = productInterface;
        return this;
    }

    Transaction setProductId(String str) {
        this.productId = str;
        return this;
    }

    Transaction setPurchaseData(String str) {
        this.purchaseData = str;
        return this;
    }

    Transaction setServicePayload(JSONObject jSONObject) {
        this.servicePayload = jSONObject;
        if (this.servicePayload == null) {
            this.servicePayload = new JSONObject();
        }
        return this;
    }

    Transaction setStampId(String str) {
        this.stampId = str;
        return this;
    }

    Transaction setStampParameters(JSONObject jSONObject) {
        this.stampParameters = jSONObject;
        if (this.stampParameters == null) {
            this.stampParameters = new JSONObject();
        }
        return this;
    }

    Transaction setStampToken(String str) {
        this.stampToken = str;
        return this;
    }

    Transaction setState(State state) {
        this.state = state;
        if (this.stateTransitions == null) {
            this.stateTransitions = new ArrayList<>();
        }
        this.stateTransitions.add(Integer.valueOf(state.getValue()));
        return this;
    }

    Transaction setUserId(String str) {
        this.userId = str;
        return this;
    }

    public String toString() {
        return "{state:" + this.state + ", productId:" + this.productId + ", stampId:" + this.stampId + ", stampToken:" + this.stampToken + ", meta:" + this.meta + ", servicePayload:" + this.servicePayload + ", userId:" + this.userId + ", payload:" + this.payload + ", stampParameters:" + this.stampParameters + ", error:" + this.error + ", stateTransitionInfo:" + this.stateTransitions + "}";
    }
}
