package kr.co.nexon.npaccount.plate.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGameInfoResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String company;
        public String companyNo;
        public List<String> grbField;
        public int grbRating;
        public String tel;
        public String title;

        public ResultSet() {
        }
    }

    public NXToyGameInfoResult() {
        this(0, "", "");
    }

    public NXToyGameInfoResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyGameInfoResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetGameInfo.getValue());
        this.result = new ResultSet();
    }
}
