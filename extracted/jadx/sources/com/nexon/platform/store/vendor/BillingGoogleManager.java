package com.nexon.platform.store.vendor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.nexon.platform.store.billing.Constants;
import com.nexon.platform.store.billing.Logger;
import com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface;
import com.nexon.platform.store.billing.vendor.interfaces.VendorInterface;
import com.nexon.platform.store.internal.Utility;
import com.nexon.platform.store.vendor.util.IabHelper;
import com.nexon.platform.store.vendor.util.IabResult;
import com.nexon.platform.store.vendor.util.Inventory;
import com.nexon.platform.store.vendor.util.Purchase;
import com.nexon.platform.store.vendor.util.SkuDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class BillingGoogleManager implements VendorInterface {
    private static final String MARKET_TYPE = "gps";
    public static final int REQUEST_CODE_BILLING = 10248;
    private static final String TAG = BillingGoogleManager.class.getName();
    private IabHelper iabHelper;
    private Constants.ErrorCode.ConvertVendorCodeToErrorCode convertVendorCodeToErrorCode = new Constants.ErrorCode.ConvertVendorCodeToErrorCode() { // from class: com.nexon.platform.store.vendor.BillingGoogleManager.1
        @Override // com.nexon.platform.store.billing.Constants.ErrorCode.ConvertVendorCodeToErrorCode
        public Constants.ErrorCode convertCode(int i) {
            switch (i) {
                case 1:
                    return Constants.ErrorCode.BillingVendorPurchaseUserCancel;
                case 2:
                    return Constants.ErrorCode.BillingVendorServiceUnavailableError;
                case 3:
                    return Constants.ErrorCode.BillingVendorUnavailableError;
                case 4:
                    return Constants.ErrorCode.BillingVendorItemUnavailableError;
                case 5:
                    return Constants.ErrorCode.BillingVendorDeveloperError;
                case 6:
                    return Constants.ErrorCode.BillingVendorError;
                case 7:
                    return Constants.ErrorCode.BillingItemNotVendorConsumedError;
                case 8:
                    return Constants.ErrorCode.BillingVendorItemNotOwned;
                default:
                    return Constants.ErrorCode.BillingVendorError;
            }
        }
    };
    private VendorInterface.IABInitCallback iabInitCallback = null;

    private void initIabHelper(Context context, VendorInterface.IABInitCallback iABInitCallback) {
        this.iabInitCallback = iABInitCallback;
        try {
            this.iabHelper = new IabHelper(context, null);
            this.iabHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() { // from class: com.nexon.platform.store.vendor.BillingGoogleManager.4
                @Override // com.nexon.platform.store.vendor.util.IabHelper.OnIabSetupFinishedListener
                public void onIabSetupFinished(IabResult iabResult) {
                    Logger.d(BillingGoogleManager.TAG, "In initIabHelper, iabInitCallback : " + BillingGoogleManager.this.iabInitCallback);
                    if (BillingGoogleManager.this.iabInitCallback != null) {
                        if (iabResult.isFailure()) {
                            Constants.ErrorCode errorCodeConvertCode = BillingGoogleManager.this.convertVendorCodeToErrorCode.convertCode(iabResult.getResponse());
                            BillingGoogleManager.this.iabInitCallback.onFailedToRequest(errorCodeConvertCode.getValue(), errorCodeConvertCode.getMessage());
                        } else {
                            BillingGoogleManager.this.iabInitCallback.onSuccess();
                        }
                        BillingGoogleManager.this.iabInitCallback = null;
                    }
                }
            });
        } catch (Exception e) {
            Logger.e(TAG, "In initIabHelper exception:" + e.toString());
            if (iABInitCallback != null) {
                Constants.ErrorCode errorCode = Constants.ErrorCode.BillingVendorError;
                iABInitCallback.onFailedToRequest(errorCode.getValue(), errorCode.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void vendorConsume(Purchase purchase, final VendorInterface.IABConsumeCallback iABConsumeCallback) {
        try {
            this.iabHelper.consumeAsync(purchase, new IabHelper.OnConsumeFinishedListener() { // from class: com.nexon.platform.store.vendor.BillingGoogleManager.6
                @Override // com.nexon.platform.store.vendor.util.IabHelper.OnConsumeFinishedListener
                public void onConsumeFinished(Purchase purchase2, IabResult iabResult) {
                    if (!iabResult.isFailure()) {
                        Logger.d(BillingGoogleManager.TAG, "consume finished");
                        if (iABConsumeCallback != null) {
                            iABConsumeCallback.onSuccess();
                            return;
                        }
                        return;
                    }
                    Logger.d(BillingGoogleManager.TAG, "consume failed. result:" + iabResult.toString());
                    if (iABConsumeCallback != null) {
                        iABConsumeCallback.onFailed(BillingGoogleManager.this.convertVendorCodeToErrorCode.convertCode(iabResult.getResponse()));
                    }
                }
            });
        } catch (IabHelper.IabAsyncInProgressException e) {
            Logger.e(TAG, "Error consuming purchase:" + purchase + " Another async operation in progress.");
            if (iABConsumeCallback != null) {
                iABConsumeCallback.onFailed(Constants.ErrorCode.BillingIabInProgressError);
            }
        } catch (Error e2) {
            Logger.e(TAG, "Error consuming purchase:" + purchase + "  error:" + e2.toString());
            if (iABConsumeCallback != null) {
                iABConsumeCallback.onFailed(Constants.ErrorCode.UnknownError);
            }
        } catch (Exception e3) {
            Logger.e(TAG, "Error consuming purchase:" + purchase + "  exception:" + e3.toString());
            if (iABConsumeCallback != null) {
                iABConsumeCallback.onFailed(Constants.ErrorCode.UnknownError);
            }
        }
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public void checkPurchasableProduct(final String str, final VendorInterface.IABPurchasableProductCallback iABPurchasableProductCallback) {
        if (iABPurchasableProductCallback == null) {
            Logger.d(TAG, "param callback is null!!");
            return;
        }
        if (isNotReadyVendor()) {
            iABPurchasableProductCallback.onResult(null, Constants.ErrorCode.BillingIabNotInitializedError);
            return;
        }
        if (Utility.isNullOrEmpty(str)) {
            Logger.d(TAG, "In checkPurchasableProduct, productId parameter is invalid. productId:" + str);
            iABPurchasableProductCallback.onResult(null, Constants.ErrorCode.BillingParameterInvalidError);
            return;
        }
        try {
            this.iabHelper.queryInventoryAsync(true, Arrays.asList(str), null, new IabHelper.QueryInventoryFinishedListener() { // from class: com.nexon.platform.store.vendor.BillingGoogleManager.2
                @Override // com.nexon.platform.store.vendor.util.IabHelper.QueryInventoryFinishedListener
                public void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) {
                    if (inventory == null || inventory.getAllSkuDetails() == null) {
                        iABPurchasableProductCallback.onResult(null, Constants.ErrorCode.BillingVendorAccountInvalidError);
                        return;
                    }
                    List<SkuDetails> allSkuDetails = inventory.getAllSkuDetails();
                    Logger.d(BillingGoogleManager.TAG, "purchasableList count:" + allSkuDetails.size());
                    for (SkuDetails skuDetails : allSkuDetails) {
                        Logger.d(BillingGoogleManager.TAG, "detail:" + skuDetails.toString());
                        if (str.equals(skuDetails.getSku())) {
                            iABPurchasableProductCallback.onResult(new GoogleStoreProductDetails(skuDetails), null);
                            return;
                        }
                    }
                    iABPurchasableProductCallback.onResult(null, Constants.ErrorCode.BillingVendorItemUnavailableError);
                }
            });
        } catch (IabHelper.IabAsyncInProgressException e) {
            Logger.e(TAG, "In checkPurchasableProduct, Another async operation in progress. " + e.toString());
            if (iABPurchasableProductCallback != null) {
                iABPurchasableProductCallback.onResult(null, Constants.ErrorCode.BillingIabInProgressError);
            }
        } catch (Exception e2) {
            Logger.e(TAG, "In checkPurchasableProduct, exception:" + e2.toString());
            if (iABPurchasableProductCallback != null) {
                iABPurchasableProductCallback.onResult(null, Constants.ErrorCode.UnknownError);
            }
        }
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public void consumeProduct(final String str, final VendorInterface.IABConsumeCallback iABConsumeCallback) {
        Logger.d(TAG, "[consumeProduct] productId:" + str + "  callback:" + iABConsumeCallback);
        if (isNotReadyVendor()) {
            if (iABConsumeCallback != null) {
                iABConsumeCallback.onFailed(Constants.ErrorCode.BillingIabNotInitializedError);
            }
        } else if (!Utility.isNullOrEmpty(str)) {
            queryInventory(new VendorInterface.IABQueryInventoryCallback() { // from class: com.nexon.platform.store.vendor.BillingGoogleManager.5
                @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface.IABQueryInventoryCallback
                public void onResult(List<PurchaseInterface> list) {
                    Logger.d(BillingGoogleManager.TAG, "In consumeProduct, queryInven result:" + list.toString());
                    if (list == null || list.isEmpty()) {
                        if (iABConsumeCallback != null) {
                            iABConsumeCallback.onFailed(Constants.ErrorCode.BillingVendorItemNotOwned);
                            return;
                        }
                        return;
                    }
                    Purchase purchase = null;
                    for (PurchaseInterface purchaseInterface : list) {
                        Logger.d(BillingGoogleManager.TAG, "In consumeProduct, productId:" + str + ", restoreProductId:" + purchaseInterface.getProductId());
                        if (str.equals(purchaseInterface.getProductId())) {
                            purchase = (Purchase) purchaseInterface.getOriginPurchaseObject();
                            break;
                        }
                    }
                    if (purchase != null) {
                        BillingGoogleManager.this.vendorConsume(purchase, iABConsumeCallback);
                        return;
                    }
                    Logger.d(BillingGoogleManager.TAG, "In consumeProduct, purchase object not exist. Already consumed");
                    if (iABConsumeCallback != null) {
                        iABConsumeCallback.onFailed(Constants.ErrorCode.BillingVendorItemNotOwned);
                    }
                }
            });
        } else if (iABConsumeCallback != null) {
            iABConsumeCallback.onFailed(Constants.ErrorCode.BillingParameterInvalidError);
        }
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public void dispose() {
        Logger.d(TAG, "[dispose]");
        if (isNotReadyVendor()) {
            return;
        }
        this.iabHelper.disposeWhenFinished();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public String getStampIdFromDeveloperPayload(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(Utility.base64DecodeStr(str)).getString("stamp_id");
        } catch (Exception e) {
            Logger.e(TAG, "In getStampIdFromDeveloperPayload, exception:" + e.toString());
            return null;
        }
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public String getStoreType() {
        return "gps";
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public boolean handleActivityResult(int i, int i2, Intent intent) {
        if (i != 10248) {
            return true;
        }
        Logger.d(TAG, "[handleActivityResult]  requestCode:" + i + "  resultCode:" + i2 + "  intent:" + intent);
        if (isReadyVendor()) {
            return this.iabHelper.handleActivityResult(i, i2, intent);
        }
        Logger.e(TAG, "In handleActivityResult, IAB not initialized");
        return true;
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public void initializeIAB(Context context, String str, VendorInterface.IABInitCallback iABInitCallback) {
        Logger.d(TAG, "Process initializeIAB, context:" + context + "  clientId:" + str + "  callback:" + iABInitCallback);
        if (isReadyVendor()) {
            if (iABInitCallback != null) {
                iABInitCallback.onSuccess();
            }
        } else if (!Utility.isNullOrEmpty(str)) {
            initIabHelper(context, iABInitCallback);
        } else if (iABInitCallback != null) {
            Constants.ErrorCode errorCode = Constants.ErrorCode.StoreInitializeFailError;
            iABInitCallback.onFailedToRequest(errorCode.getValue(), errorCode.getMessage());
            Logger.d(TAG, "Parameter is invalid. clientId:" + str);
        }
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public boolean isNotReadyVendor() {
        return !isReadyVendor();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public boolean isReadyVendor() {
        return this.iabHelper != null && this.iabHelper.isCompleteSetup();
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public void purchaseProduct(Activity activity, String str, String str2, String str3, final VendorInterface.IABPurchaseCallback iABPurchaseCallback) {
        Logger.d(TAG, "[purchaseProduct] productId:" + str + "  payload:" + str2 + " activity:" + activity + "   callback:" + iABPurchaseCallback);
        if (isNotReadyVendor()) {
            if (iABPurchaseCallback != null) {
                iABPurchaseCallback.onFailed(Constants.ErrorCode.BillingIabNotInitializedError);
                return;
            }
            return;
        }
        if (Utility.isNullOrEmpty(str) || activity == null) {
            if (iABPurchaseCallback != null) {
                iABPurchaseCallback.onFailed(Constants.ErrorCode.BillingParameterInvalidError);
                return;
            }
            return;
        }
        try {
            this.iabHelper.launchPurchaseFlow(activity, str, REQUEST_CODE_BILLING, new IabHelper.OnIabPurchaseFinishedListener() { // from class: com.nexon.platform.store.vendor.BillingGoogleManager.3
                @Override // com.nexon.platform.store.vendor.util.IabHelper.OnIabPurchaseFinishedListener
                public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
                    Logger.d(BillingGoogleManager.TAG, "onIabPurchaseFinished  result:" + iabResult.toString() + "  payment:" + purchase);
                    if (iabResult.isFailure()) {
                        iABPurchaseCallback.onFailed(BillingGoogleManager.this.convertVendorCodeToErrorCode.convertCode(iabResult.getResponse()));
                        return;
                    }
                    Logger.d(BillingGoogleManager.TAG, "Vendor Purchase successful");
                    try {
                        GoogleStorePurchase googleStorePurchase = new GoogleStorePurchase(purchase);
                        if (iABPurchaseCallback != null) {
                            iABPurchaseCallback.onSuccess(googleStorePurchase);
                        }
                    } catch (Exception e) {
                        if (iABPurchaseCallback != null) {
                            iABPurchaseCallback.onFailed(Constants.ErrorCode.InternalParseError);
                        }
                    }
                }
            }, str2);
        } catch (IabHelper.IabAsyncInProgressException e) {
            Logger.e(TAG, "In purchaseProduct, Another async operation in progress." + e.toString());
            if (iABPurchaseCallback != null) {
                iABPurchaseCallback.onFailed(Constants.ErrorCode.BillingIabInProgressError);
            }
        } catch (Exception e2) {
            Logger.e(TAG, "In purchaseProduct exception:" + e2.toString());
            if (iABPurchaseCallback != null) {
                iABPurchaseCallback.onFailed(Constants.ErrorCode.UnknownError);
            }
        }
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public void queryInventory(final VendorInterface.IABQueryInventoryCallback iABQueryInventoryCallback) {
        Logger.d(TAG, "[queryInventory]  callback:" + iABQueryInventoryCallback);
        if (isNotReadyVendor()) {
            if (iABQueryInventoryCallback != null) {
                iABQueryInventoryCallback.onResult(new ArrayList());
                return;
            }
            return;
        }
        try {
            this.iabHelper.queryInventoryAsync(new IabHelper.QueryInventoryFinishedListener() { // from class: com.nexon.platform.store.vendor.BillingGoogleManager.8
                @Override // com.nexon.platform.store.vendor.util.IabHelper.QueryInventoryFinishedListener
                public void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) {
                    if (iabResult.isFailure()) {
                        Logger.d(BillingGoogleManager.TAG, "Failed to query inventory: " + iabResult.toString());
                        iABQueryInventoryCallback.onResult(new ArrayList());
                        return;
                    }
                    Logger.d(BillingGoogleManager.TAG, "Query inventory was successful.");
                    List<Purchase> allPurchases = inventory.getAllPurchases();
                    ArrayList arrayList = new ArrayList();
                    for (Purchase purchase : allPurchases) {
                        Logger.d(BillingGoogleManager.TAG, "queryInventory payment:" + purchase.toString());
                        try {
                            arrayList.add(new GoogleStorePurchase(purchase));
                        } catch (Exception e) {
                            Logger.d(BillingGoogleManager.TAG, "[In QueryInventory], JsonException e:" + e.toString());
                        }
                    }
                    if (iABQueryInventoryCallback != null) {
                        iABQueryInventoryCallback.onResult(arrayList);
                    }
                }
            });
        } catch (IabHelper.IabAsyncInProgressException e) {
            Logger.e(TAG, "In Iabhelper.startSetup, failed to query inventory. Another async operation in progress. e:" + e.toString());
            if (iABQueryInventoryCallback != null) {
                iABQueryInventoryCallback.onResult(new ArrayList());
            }
        } catch (Error e2) {
            Logger.e(TAG, "In Iabhelper.startSetup, failed to query inventory.  error:" + e2.toString());
            if (iABQueryInventoryCallback != null) {
                iABQueryInventoryCallback.onResult(new ArrayList());
            }
        } catch (Exception e3) {
            Logger.e(TAG, "In Iabhelper.startSetup, failed to query inventory.  exception:" + e3.toString());
            if (iABQueryInventoryCallback != null) {
                iABQueryInventoryCallback.onResult(new ArrayList());
            }
        }
    }

    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface
    public void requestProductDetails(List<String> list, final VendorInterface.IABProductDetailsCallback iABProductDetailsCallback) {
        Logger.d(TAG, "[requestProductDetail] productIds" + list + "  callback:" + iABProductDetailsCallback);
        if (isNotReadyVendor()) {
            if (iABProductDetailsCallback != null) {
                iABProductDetailsCallback.onResult(new ArrayList(), Constants.ErrorCode.BillingIabNotInitializedError);
                return;
            }
            return;
        }
        if (list == null || list.size() < 1) {
            iABProductDetailsCallback.onResult(new ArrayList(), Constants.ErrorCode.BillingParameterInvalidError);
            return;
        }
        try {
            this.iabHelper.queryInventoryAsync(true, list, null, new IabHelper.QueryInventoryFinishedListener() { // from class: com.nexon.platform.store.vendor.BillingGoogleManager.7
                @Override // com.nexon.platform.store.vendor.util.IabHelper.QueryInventoryFinishedListener
                public void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) {
                    if (inventory == null || inventory.getAllSkuDetails() == null) {
                        Logger.d(BillingGoogleManager.TAG, "In requestProductDetail, google account is invalid. relogin google account");
                        if (iABProductDetailsCallback != null) {
                            iABProductDetailsCallback.onResult(new ArrayList(), Constants.ErrorCode.BillingVendorAccountInvalidError);
                            return;
                        }
                        return;
                    }
                    if (iABProductDetailsCallback != null) {
                        List<SkuDetails> allSkuDetails = inventory.getAllSkuDetails();
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < allSkuDetails.size(); i++) {
                            arrayList.add(new GoogleStoreProductDetails(allSkuDetails.get(i)));
                        }
                        Logger.d(BillingGoogleManager.TAG, "queryInventory result list count:" + allSkuDetails.size());
                        iABProductDetailsCallback.onResult(arrayList, null);
                    }
                }
            });
        } catch (IabHelper.IabAsyncInProgressException e) {
            Logger.e(TAG, "In requestProductDetail, another IAB operation in progress" + e.toString());
            if (iABProductDetailsCallback != null) {
                iABProductDetailsCallback.onResult(new ArrayList(), Constants.ErrorCode.BillingIabInProgressError);
            }
        } catch (Error e2) {
            Logger.e(TAG, "In requestProductDetail, error:" + e2.toString());
            if (iABProductDetailsCallback != null) {
                iABProductDetailsCallback.onResult(new ArrayList(), Constants.ErrorCode.UnknownError);
            }
        } catch (Exception e3) {
            Logger.e(TAG, "In requestProductDetail, exception:" + e3.toString());
            if (iABProductDetailsCallback != null) {
                iABProductDetailsCallback.onResult(new ArrayList(), Constants.ErrorCode.UnknownError);
            }
        }
    }
}
