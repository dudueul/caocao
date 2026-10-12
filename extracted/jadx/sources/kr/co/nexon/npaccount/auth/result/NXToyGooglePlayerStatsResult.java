package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyGooglePlayerStats;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGooglePlayerStatsResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public NXToyGooglePlayerStats googlePlayerStats;

        public ResultSet() {
        }
    }

    public NXToyGooglePlayerStatsResult() {
        this(0, "", "");
    }

    public NXToyGooglePlayerStatsResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyGooglePlayerStatsResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetPlayerStats.getValue());
        this.result = new ResultSet();
    }
}
