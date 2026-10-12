package com.nexon.platform.store.billing;

import android.os.Handler;
import android.os.Looper;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.platform.store.billing.request.NXToyRestoreRequest;
import com.nexon.platform.store.billing.result.NXToyRestoreResult;
import com.nexon.platform.store.billing.vendor.VendorHolder;
import com.nexon.platform.store.billing.vendor.interfaces.PurchaseInterface;
import com.nexon.platform.store.billing.vendor.interfaces.VendorInterface;
import com.nexon.platform.store.internal.Utility;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class Restore {
    private static final int EMPTY_INT = -18937;
    private static final String TAG = Restore.class.getName();
    private static final int TX_CONSUMED_STATUS = 30;

    private interface ResolveCallback {
        void onResolved();
    }

    interface RestoreCallback {
        void onRestored(List<Transaction> list);
    }

    private interface RestorePaymentCallback {
        void onRestored();
    }

    Restore() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void resolve(final LinkedList<Transaction> linkedList, final ResolveCallback resolveCallback) {
        if (linkedList == null || linkedList.isEmpty()) {
            resolveCallback.onResolved();
        } else {
            new Order(linkedList.pop()).process(new Order.ProcessCallback() { // from class: com.nexon.platform.store.billing.Restore.3
                @Override // com.nexon.platform.store.billing.Order.ProcessCallback
                public void onCompleted(Transaction transaction) {
                    Logger.d(Restore.TAG, "*** Restored - " + transaction);
                    Restore.resolve(linkedList, resolveCallback);
                }
            });
        }
    }

    static void restore(final String str, final RestoreCallback restoreCallback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.nexon.platform.store.billing.Restore.1
            @Override // java.lang.Runnable
            public void run() {
                Logger.d(Restore.TAG, "==================================================");
                Logger.d(Restore.TAG, "*** Start to Restore ***");
                Restore.restorePayment(str, new RestorePaymentCallback() { // from class: com.nexon.platform.store.billing.Restore.1.1
                    @Override // com.nexon.platform.store.billing.Restore.RestorePaymentCallback
                    public void onRestored() {
                        Restore.restoreDelivery(str, restoreCallback);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void restoreDelivery(String str, final RestoreCallback restoreCallback) {
        if (Utility.isNullOrEmpty(str)) {
            restoreCallback.onRestored(new LinkedList());
            Logger.d(TAG, "Parameter is invalid. userId:" + str);
        } else {
            NXToyRequestPostman.getInstance().postRequest(new NXToyRestoreRequest(str), new NXToyRequestListener() { // from class: com.nexon.platform.store.billing.Restore.4
                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                public void onComplete(NXToyResult nXToyResult) {
                    if (nXToyResult.errorCode != 0) {
                        restoreCallback.onRestored(new LinkedList());
                        return;
                    }
                    final LinkedList linkedList = new LinkedList();
                    List<NXToyRestoreResult.RestoreStamp> list = ((NXToyRestoreResult) nXToyResult).restore_stamps;
                    for (int i = 0; list != null && i < list.size(); i++) {
                        NXToyRestoreResult.RestoreStamp restoreStamp = list.get(i);
                        Transaction stampParameters = null;
                        switch (restoreStamp.tx_status) {
                            case 30:
                                Logger.d(Restore.TAG, "restoreItem:" + restoreStamp);
                                String str2 = restoreStamp.stamp_id;
                                String str3 = null;
                                if (Utility.isNullOrEmpty(str2)) {
                                    Logger.e(Restore.TAG, "In makeConsumedStateTransaction, Purchased Info invalid. info:" + restoreStamp);
                                } else {
                                    List<String> list2 = restoreStamp.product_ids;
                                    if (list2 != null && list2.size() > 0) {
                                        str3 = list2.get(0);
                                    }
                                    String str4 = restoreStamp.service_payload;
                                    JSONObject jSONObject = Utility.isNotEmpty(str4) ? Utility.toJSONObject(Utility.base64DecodeStr(str4)) : null;
                                    String str5 = restoreStamp.stamp_payload;
                                    stampParameters = new Transaction(Transaction.State.Consumed).setProductId(str3).setStampId(str2).setServicePayload(jSONObject).setStampParameters(Utility.isNotEmpty(str5) ? Utility.toJSONObject(Utility.base64DecodeStr(str5)) : null);
                                    Logger.d(Restore.TAG, "restore Transaction info:" + stampParameters);
                                }
                                break;
                        }
                        if (stampParameters != null) {
                            linkedList.add(stampParameters);
                        }
                    }
                    if (linkedList == null || linkedList.size() == 0) {
                        restoreCallback.onRestored(new LinkedList());
                    } else {
                        Restore.resolve((LinkedList) linkedList.clone(), new ResolveCallback() { // from class: com.nexon.platform.store.billing.Restore.4.1
                            @Override // com.nexon.platform.store.billing.Restore.ResolveCallback
                            public void onResolved() {
                                Logger.d(Restore.TAG, "==================================================");
                                restoreCallback.onRestored(linkedList);
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void restorePayment(final String str, final RestorePaymentCallback restorePaymentCallback) {
        VendorHolder.getInstance().getVendorInterface().queryInventory(new VendorInterface.IABQueryInventoryCallback() { // from class: com.nexon.platform.store.billing.Restore.2
            @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface.IABQueryInventoryCallback
            public void onResult(List<PurchaseInterface> list) {
                Transaction purchaseData;
                Logger.d(Restore.TAG, "In restorePayment, Restore purchases size:" + list.size());
                if (list.size() == 0) {
                    restorePaymentCallback.onRestored();
                    return;
                }
                LinkedList linkedList = new LinkedList();
                for (int i = 0; i < list.size(); i++) {
                    PurchaseInterface purchaseInterface = list.get(i);
                    String stampIdFromDeveloperPayload = VendorHolder.getInstance().getVendorInterface().getStampIdFromDeveloperPayload(purchaseInterface.getDeveloperPayload());
                    ToyLog.d("In restorePayment, parsed stampId:" + stampIdFromDeveloperPayload);
                    if (Utility.isNullOrEmpty(stampIdFromDeveloperPayload)) {
                        Logger.e(Restore.TAG, "In restorePayment, stampId is null. promotion purchase type!!!");
                        purchaseData = new Transaction(Transaction.State.PromotionInitialized).setProductId(purchaseInterface.getProductId()).setUserId(str).setPurchaseData(purchaseInterface.getPurchaseData());
                    } else {
                        purchaseData = new Transaction(Transaction.State.VendorPurchased).setProductId(purchaseInterface.getProductId()).setStampId(stampIdFromDeveloperPayload).setUserId(str).setPurchaseData(purchaseInterface.getPurchaseData());
                    }
                    linkedList.add(purchaseData);
                }
                Restore.resolve(linkedList, new ResolveCallback() { // from class: com.nexon.platform.store.billing.Restore.2.1
                    @Override // com.nexon.platform.store.billing.Restore.ResolveCallback
                    public void onResolved() {
                        restorePaymentCallback.onRestored();
                    }
                });
            }
        });
    }
}
