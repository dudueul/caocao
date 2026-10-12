package com.crashlytics.android.answers;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class KeepAllEventFilter implements EventFilter {
    KeepAllEventFilter() {
    }

    @Override // com.crashlytics.android.answers.EventFilter
    public boolean skipEvent(SessionEvent sessionEvent) {
        return false;
    }
}
