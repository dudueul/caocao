package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyLeaderboardScore;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyLeaderboardScoreResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public NXToyLeaderboardScore score;

        public ResultSet() {
        }
    }

    public NXToyLeaderboardScoreResult() {
        this(0, "", "");
    }

    public NXToyLeaderboardScoreResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyLeaderboardScoreResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.LoadCurrentPlayerScore.getValue());
        this.result = new ResultSet();
    }
}
