package kr.co.nexon.toy.api.request.tools.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXJsonUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyJsonResultMaker implements NXToyResultMaker {
    private final NXToyRequestTag tag;

    public NXToyJsonResultMaker(NXToyRequestTag nXToyRequestTag) {
        this.tag = nXToyRequestTag;
    }

    @Override // kr.co.nexon.toy.api.request.tools.result.NXToyResultMaker
    public NXToyResult makeBaseResult(Class cls) {
        NXToyResult nXToyResult = null;
        try {
            nXToyResult = (NXToyResult) cls.newInstance();
            nXToyResult.requestTag = this.tag.getValue();
            return nXToyResult;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return nXToyResult;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            return nXToyResult;
        }
    }

    @Override // kr.co.nexon.toy.api.request.tools.result.NXToyResultMaker
    public NXToyResult toResult(String str, Class cls) {
        NXToyResult nXToyResult = (NXToyResult) NXJsonUtil.fromObject(str, cls);
        nXToyResult.requestTag = this.tag.getValue();
        return nXToyResult;
    }
}
