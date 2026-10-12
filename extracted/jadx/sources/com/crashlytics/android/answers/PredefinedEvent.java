package com.crashlytics.android.answers;

import com.crashlytics.android.answers.PredefinedEvent;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class PredefinedEvent<T extends PredefinedEvent> extends AnswersEvent<T> {
    final AnswersAttributes predefinedAttributes = new AnswersAttributes(this.validator);

    Map<String, Object> getPredefinedAttributes() {
        return this.predefinedAttributes.attributes;
    }

    abstract String getPredefinedType();

    public String toString() {
        return "{type:\"" + getPredefinedType() + "\", predefinedAttributes:" + this.predefinedAttributes + ", customAttributes:" + this.customAttributes + "}";
    }
}
