package com.crashlytics.android.answers;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SearchEvent extends PredefinedEvent<SearchEvent> {
    static final String QUERY_ATTRIBUTE = "query";
    static final String TYPE = "search";

    @Override // com.crashlytics.android.answers.PredefinedEvent
    String getPredefinedType() {
        return TYPE;
    }

    public SearchEvent putQuery(String str) {
        this.predefinedAttributes.put("query", str);
        return this;
    }
}
