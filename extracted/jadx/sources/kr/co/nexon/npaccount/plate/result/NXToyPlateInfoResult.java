package kr.co.nexon.npaccount.plate.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.model.NXToyPlateInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyPlateInfoResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public List<NXToyPlateInfo> items;
        public String title;

        public ResultSet() {
        }
    }

    public NXToyPlateInfoResult() {
        this(0, "", "");
    }

    public NXToyPlateInfoResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyPlateInfoResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetPlateInfo.getValue());
        this.result = new ResultSet();
    }
}
