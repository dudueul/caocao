package com.nexon.platform.store.billing;

import android.app.Activity;
import android.content.Intent;
import com.nexon.platform.store.billing.vendor.VendorHolder;
import com.nexon.platform.store.billing.vendor.interfaces.ProductInterface;
import com.nexon.platform.store.billing.vendor.interfaces.VendorInterface;
import com.nexon.platform.store.internal.LoadingProgressBar;
import com.nexon.platform.store.internal.Utility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Billing {
    private static final String TAG = Billing.class.getName();

    public interface RequestPaymentCallback {
        void onRequestPaymentCompleted(Transaction transaction);
    }

    public interface RequestProductsCallback {
        void onRequestProductsCompleted(List<Product> list, Error error);
    }

    public interface RestoreCallback {
        void onRestored(List<Transaction> list);
    }

    public static void handleActivityResult(int i, int i2, Intent intent) {
        VendorInterface vendorInterface = VendorHolder.getInstance().getVendorInterface();
        if (vendorInterface != null) {
            vendorInterface.handleActivityResult(i, i2, intent);
        }
    }

    public static void requestPayment(PaymentInfo paymentInfo, Activity activity, final RequestPaymentCallback requestPaymentCallback) {
        LoadingProgressBar.show(activity);
        String itemId = paymentInfo.getItemId();
        String characterId = paymentInfo.getCharacterId();
        JSONObject jSONObject = new JSONObject();
        try {
            if (Utility.isNotEmpty(itemId)) {
                jSONObject.put("item_id", itemId);
            }
            if (Utility.isNotEmpty(characterId)) {
                jSONObject.put("character_id", characterId);
            }
        } catch (JSONException e) {
        }
        Order order = new Order(new Transaction(Transaction.State.Initialized).setProductId(paymentInfo.getProductId()).setUserId(paymentInfo.getUserId()).setMeta(paymentInfo.getMeta()).setServicePayload(paymentInfo.getServicePayload()).setStampParameters(jSONObject));
        order.setActivity(activity);
        order.process(new Order.ProcessCallback() { // from class: com.nexon.platform.store.billing.Billing.3
            @Override // com.nexon.platform.store.billing.Order.ProcessCallback
            public void onCompleted(Transaction transaction) {
                LoadingProgressBar.dismiss();
                if (requestPaymentCallback != null) {
                    requestPaymentCallback.onRequestPaymentCompleted(transaction);
                }
            }
        });
    }

    @Deprecated
    public static void requestPayment(String str, String str2, JSONObject jSONObject, Activity activity, RequestPaymentCallback requestPaymentCallback) {
        requestPayment(str, str2, jSONObject, null, activity, requestPaymentCallback);
    }

    @Deprecated
    public static void requestPayment(String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, Activity activity, final RequestPaymentCallback requestPaymentCallback) {
        LoadingProgressBar.show(activity);
        Order order = new Order(new Transaction(Transaction.State.Initialized).setProductId(str).setUserId(str2).setMeta(jSONObject).setServicePayload(jSONObject2));
        order.setActivity(activity);
        order.process(new Order.ProcessCallback() { // from class: com.nexon.platform.store.billing.Billing.2
            @Override // com.nexon.platform.store.billing.Order.ProcessCallback
            public void onCompleted(Transaction transaction) {
                LoadingProgressBar.dismiss();
                if (requestPaymentCallback != null) {
                    requestPaymentCallback.onRequestPaymentCompleted(transaction);
                }
            }
        });
    }

    public static void requestProducts(final List<String> list, final RequestProductsCallback requestProductsCallback) {
        VendorHolder.getInstance().getVendorInterface().requestProductDetails(list, new VendorInterface.IABProductDetailsCallback() { // from class: com.nexon.platform.store.billing.Billing.1
            @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface.IABProductDetailsCallback
            public void onResult(List<ProductInterface> list2, Constants.ErrorCode errorCode) {
                ArrayList arrayList = new ArrayList();
                Error errorCreateError = null;
                if (errorCode != null) {
                    errorCreateError = Error.createError(errorCode);
                } else {
                    LinkedList linkedList = new LinkedList();
                    linkedList.addAll(list);
                    for (ProductInterface productInterface : list2) {
                        arrayList.add(new Product(productInterface));
                        linkedList.remove(productInterface.getProductId());
                    }
                    Iterator it = linkedList.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new Product((String) it.next()));
                    }
                }
                if (requestProductsCallback != null) {
                    requestProductsCallback.onRequestProductsCompleted(arrayList, errorCreateError);
                }
            }
        });
    }

    public static void restore(Activity activity, boolean z, String str, final RestoreCallback restoreCallback) {
        if (z) {
            LoadingProgressBar.show(activity);
        }
        Restore.restore(str, new Restore.RestoreCallback() { // from class: com.nexon.platform.store.billing.Billing.4
            @Override // com.nexon.platform.store.billing.Restore.RestoreCallback
            public void onRestored(List<Transaction> list) {
                LoadingProgressBar.dismiss();
                if (restoreCallback != null) {
                    restoreCallback.onRestored(list);
                }
            }
        });
    }
}
