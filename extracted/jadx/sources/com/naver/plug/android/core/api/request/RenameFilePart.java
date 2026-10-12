package com.naver.plug.android.core.api.request;

import android.text.TextUtils;
import com.navercorp.volleyextensions.volleyer.http.ContentType;
import com.navercorp.volleyextensions.volleyer.multipart.FilePart;
import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class RenameFilePart extends FilePart {
    public RenameFilePart(String str, File file, ContentType contentType) {
        super(str, file, contentType);
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.FilePart, com.navercorp.volleyextensions.volleyer.multipart.AbstractPart
    protected byte[] getExtraHeader() {
        return ("filename=\"" + getFilename() + "\"").getBytes();
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.FilePart
    public String getFilename() {
        String[] strArrSplit = TextUtils.split(getContentType().toString(), "/");
        if (strArrSplit.length == 0) {
            return super.getFilename();
        }
        return System.currentTimeMillis() + "." + strArrSplit[strArrSplit.length - 1];
    }
}
