package kr.co.nexon.npaccount.auth.result.model;

import com.nexon.core.requestpostman.result.NXClassInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyBillingTransaction extends NXClassInfo {
    public State state = State.Initialized;
    public String productId = "";
    public String stampId = "";
    public String stampToken = "";
    public String servicePayload = "{}";
    public NXToyBillingTransactionError error = new NXToyBillingTransactionError();

    /* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
    public enum State {
        Initialized(0),
        PromotionInitialized(2),
        RecoverInitailzed(3),
        VendorChecked(5),
        Issued(10),
        VendorPurchased(15),
        Purchased(20),
        Consumed(30),
        PaymentCompleted(35),
        Verified(40),
        Finished(50),
        Canceled(93),
        Failed(99);

        private final int value;

        State(int i) {
            this.value = i;
        }

        public static State getStateFromValue(int i) {
            switch (i) {
                case 0:
                    return Initialized;
                case 2:
                    return PromotionInitialized;
                case 3:
                    return RecoverInitailzed;
                case 5:
                    return VendorChecked;
                case 10:
                    return Issued;
                case 15:
                    return VendorPurchased;
                case 20:
                    return Purchased;
                case 30:
                    return Consumed;
                case 35:
                    return PaymentCompleted;
                case 40:
                    return Verified;
                case 50:
                    return Finished;
                case 93:
                    return Canceled;
                case 99:
                    return Failed;
                default:
                    return null;
            }
        }

        public int getValue() {
            return this.value;
        }
    }
}
