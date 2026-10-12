package com.nexon.platform.store.billing.interfaces;

import android.app.Activity;
import android.content.Intent;
import com.nexon.platform.store.billing.Error;
import com.nexon.platform.store.billing.PaymentInfo;
import com.nexon.platform.store.billing.Product;
import com.nexon.platform.store.billing.Transaction;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface Billinginterface {

    public interface RequestPaymentCallback {
        void onRequestPaymentCompleted(Transaction transaction);
    }

    public interface RequestProductsCallback {
        void onRequestProductsCompleted(List<Product> list, Error error);
    }

    public interface RestoreCallback {
        void onRestored(List<Transaction> list);
    }

    void handleActivityResult(int i, int i2, Intent intent);

    void requestPayment(PaymentInfo paymentInfo, Activity activity, RequestPaymentCallback requestPaymentCallback);

    @Deprecated
    void requestPayment(String str, String str2, JSONObject jSONObject, Activity activity, RequestPaymentCallback requestPaymentCallback);

    @Deprecated
    void requestPayment(String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, Activity activity, RequestPaymentCallback requestPaymentCallback);

    void requestProducts(List<String> list, RequestProductsCallback requestProductsCallback);

    void restore(String str, RestoreCallback restoreCallback);
}
