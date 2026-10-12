package com.nexon.platform.store.billing.result;

import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyConsumeResult extends NXToyResult {
    public List<String> product_id;
    public String stamp_token;

    public NXToyConsumeResult() {
        this.product_id = new ArrayList();
    }

    public NXToyConsumeResult(int i, String str) {
        super(i, str);
        this.product_id = new ArrayList();
    }

    public NXToyConsumeResult(int i, String str, String str2) {
        super(i, str, str2);
        this.product_id = new ArrayList();
    }
}
