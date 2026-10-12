package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyMigrationDataInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyMigrationInfoResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public NXToyMigrationDataInfo current;
        public NXToyMigrationDataInfo stored;

        ResultSet() {
        }
    }

    public NXToyMigrationInfoResult() {
        this(0, "", "");
    }

    public NXToyMigrationInfoResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyMigrationInfoResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetMigrationInfo.getValue());
        this.result = new ResultSet();
    }
}
