package com.nexon.core.requestpostman.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyCreateTokenResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String npToken;

        public ResultSet() {
        }
    }

    public NXToyCreateTokenResult() {
        this(0, "", "");
    }

    public NXToyCreateTokenResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyCreateTokenResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.CreateNPToken.getValue());
        this.result = new ResultSet();
    }
}
