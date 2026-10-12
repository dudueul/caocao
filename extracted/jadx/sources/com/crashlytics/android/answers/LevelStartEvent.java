package com.crashlytics.android.answers;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class LevelStartEvent extends PredefinedEvent<LevelStartEvent> {
    static final String LEVEL_NAME_ATTRIBUTE = "levelName";
    static final String TYPE = "levelStart";

    @Override // com.crashlytics.android.answers.PredefinedEvent
    String getPredefinedType() {
        return TYPE;
    }

    public LevelStartEvent putLevelName(String str) {
        this.predefinedAttributes.put(LEVEL_NAME_ATTRIBUTE, str);
        return this;
    }
}
