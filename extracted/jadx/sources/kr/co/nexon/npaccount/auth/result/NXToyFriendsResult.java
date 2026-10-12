package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.model.NXToyUserInfo;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyFriendsResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public List<NXToyUserInfo> friends;
        public int hasNext;
        public List<NXToyUserInfo> invites;

        public ResultSet() {
        }
    }

    public NXToyFriendsResult() {
        this(0, "", "");
    }

    public NXToyFriendsResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyFriendsResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetFriends.getValue());
        this.result = new ResultSet();
    }

    public NXToyFriendsResult(int i, String str, String str2, int i2) {
        super(i, str, str2, i2);
        this.result = new ResultSet();
    }
}
