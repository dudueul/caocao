package com.crashlytics.android.answers;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class InviteEvent extends PredefinedEvent<InviteEvent> {
    static final String METHOD_ATTRIBUTE = "method";
    static final String TYPE = "invite";

    @Override // com.crashlytics.android.answers.PredefinedEvent
    String getPredefinedType() {
        return TYPE;
    }

    public InviteEvent putMethod(String str) {
        this.predefinedAttributes.put("method", str);
        return this;
    }
}
