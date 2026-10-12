package kr.co.nexon.npaccount.secondpassword.constants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public enum NXPSecondPasswordState {
    Unregistered(0, "Unregistered"),
    Reset(1, "Reset"),
    Processing(2, "Processing"),
    Registered(3, "Registered"),
    Locked(4, "Locked");

    private int state;
    private String stateDescription;

    NXPSecondPasswordState(int i, String str) {
        this.state = i;
        this.stateDescription = str;
    }

    public static NXPSecondPasswordState getStateByCode(int i) {
        switch (i) {
            case 1:
                return Reset;
            case 2:
                return Processing;
            case 3:
                return Registered;
            case 4:
                return Locked;
            default:
                return Unregistered;
        }
    }

    public String getStateDescription() {
        return this.stateDescription;
    }

    public int getValue() {
        return this.state;
    }
}
