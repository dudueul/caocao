package com.crashlytics.android.answers;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CustomEvent extends AnswersEvent<CustomEvent> {
    private final String eventName;

    public CustomEvent(String str) {
        if (str == null) {
            throw new NullPointerException("eventName must not be null");
        }
        this.eventName = this.validator.limitStringLength(str);
    }

    String getCustomType() {
        return this.eventName;
    }

    public String toString() {
        return "{eventName:\"" + this.eventName + "\", customAttributes:" + this.customAttributes + "}";
    }
}
