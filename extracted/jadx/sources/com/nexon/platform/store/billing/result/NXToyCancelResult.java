package com.nexon.platform.store.billing.result;

import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyCancelResult extends NXToyResult {
    public String stamp_id;

    public NXToyCancelResult() {
        this(0, "", "");
    }

    public NXToyCancelResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyCancelResult(int i, String str, String str2) {
        super(i, str, str2);
    }
}
