package com.nexon.platform.store.billing.vendor.interfaces;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.nexon.platform.store.billing.Constants;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface VendorInterface {

    public interface IABConsumeCallback {
        void onFailed(Constants.ErrorCode errorCode);

        void onSuccess();
    }

    public interface IABInitCallback {
        void onFailedToRequest(int i, String str);

        void onSuccess();
    }

    public interface IABProductDetailsCallback {
        void onResult(List<ProductInterface> list, Constants.ErrorCode errorCode);
    }

    public interface IABPurchasableProductCallback {
        void onResult(ProductInterface productInterface, Constants.ErrorCode errorCode);
    }

    public interface IABPurchaseCallback {
        void onFailed(Constants.ErrorCode errorCode);

        void onSuccess(PurchaseInterface purchaseInterface);
    }

    public interface IABQueryInventoryCallback {
        void onResult(List<PurchaseInterface> list);
    }

    void checkPurchasableProduct(String str, IABPurchasableProductCallback iABPurchasableProductCallback);

    void consumeProduct(String str, IABConsumeCallback iABConsumeCallback);

    void dispose();

    String getStampIdFromDeveloperPayload(String str);

    String getStoreType();

    boolean handleActivityResult(int i, int i2, Intent intent);

    void initializeIAB(Context context, String str, IABInitCallback iABInitCallback);

    boolean isNotReadyVendor();

    boolean isReadyVendor();

    void purchaseProduct(Activity activity, String str, String str2, String str3, IABPurchaseCallback iABPurchaseCallback);

    void queryInventory(IABQueryInventoryCallback iABQueryInventoryCallback);

    void requestProductDetails(List<String> list, IABProductDetailsCallback iABProductDetailsCallback);
}
