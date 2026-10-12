package com.nexon.platform.store.billing.interfaces;

import com.nexon.platform.store.billing.Transaction;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface RestoreInterface {

    public interface ResolveCallback {
        void onResolved();
    }

    public interface RestoreCallback {
        void onRestored(List<Transaction> list);
    }

    public interface RestorePaymentCallback {
        void onRestored();
    }

    JSONObject getServicePayloadFromPayload(String str);

    String getStampIdFromPayload(String str);

    void resolve(LinkedList<Transaction> linkedList, ResolveCallback resolveCallback);

    void restore(String str, RestoreCallback restoreCallback);

    void restoreDelivery(String str, RestoreCallback restoreCallback);

    void restorePayment(String str, RestorePaymentCallback restorePaymentCallback);
}
