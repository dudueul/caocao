package kr.co.nexon.npaccount.push.result.v2;

import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyGetPolicyResult extends NXToyResult {
    public Kind kind;
    public String npToken;
    public Push push;
    public int svcID;

    public class Kind {
        public String name;
        public Map<String, PoliciesAgreeStatement> policies = new HashMap();

        public Kind() {
        }
    }

    public class PoliciesAgreeStatement {
        public Boolean enable;
        public String name;

        public PoliciesAgreeStatement() {
        }
    }

    public class Push {
        public String name;
        public Map<String, PoliciesAgreeStatement> policies = new HashMap();

        public Push() {
        }
    }

    public NXToyGetPolicyResult() {
        this(0, "", "");
    }

    public NXToyGetPolicyResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyGetPolicyResult(int i, String str, String str2) {
        super(i, str, str2);
        this.push = new Push();
        this.kind = new Kind();
    }
}
