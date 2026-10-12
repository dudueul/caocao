package com.naver.plug.android.core.api;

import android.text.TextUtils;
import com.naver.plug.android.core.a.a;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Response extends a {
    private Integer errorCode;
    private String errorMessage;
    private String moreInfo;
    private Integer status;
    private Integer statusCode;
    private Object tag;

    public PlugError getError() {
        int iIntValue;
        if (this.errorCode == null) {
            return null;
        }
        if (this.errorCode.intValue() < 1000) {
            iIntValue = PlugError.Status.OPEN_API_ERROR.code;
        } else if (this.status != null) {
            iIntValue = this.status.intValue();
        } else {
            iIntValue = this.statusCode != null ? this.statusCode.intValue() : 0;
        }
        return new PlugError(iIntValue, this.errorCode.intValue(), this.errorMessage, this.moreInfo);
    }

    public Object getTag() {
        return this.tag;
    }

    public boolean hasErrorMessage() {
        return !TextUtils.isEmpty(this.errorMessage);
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }
}
