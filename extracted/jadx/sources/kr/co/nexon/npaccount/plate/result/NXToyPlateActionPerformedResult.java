package kr.co.nexon.npaccount.plate.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyPlateActionPerformedResult extends NXToyResult {
    public String result;

    public NXToyPlateActionPerformedResult() {
        this(0, "", "");
    }

    public NXToyPlateActionPerformedResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyPlateActionPerformedResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.PlateButtonClick.getValue());
    }
}
