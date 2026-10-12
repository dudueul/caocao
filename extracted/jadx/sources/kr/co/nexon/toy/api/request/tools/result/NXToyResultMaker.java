package kr.co.nexon.toy.api.request.tools.result;

import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface NXToyResultMaker {
    NXToyResult makeBaseResult(Class cls);

    NXToyResult toResult(String str, Class cls);
}
