package kr.co.nexon.npaccount.auth.result;

import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXClassInfo;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.util.List;
import kr.co.nexon.npaccount.auth.result.model.NXToyCustomerServiceItemInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXToyCustomerServiceInfoResult extends NXToyResult {
    public ResultSet result;

    public class ResultSet extends NXClassInfo {
        public String company;
        public String companyNo;
        public List<String> grbField;
        public int grbRating;
        public List<NXToyCustomerServiceItemInfo> items;
        public String tel;
        public String title;

        public ResultSet() {
        }
    }

    public NXToyCustomerServiceInfoResult() {
        this(0, "", "");
    }

    public NXToyCustomerServiceInfoResult(int i, String str) {
        this(i, str, "");
    }

    public NXToyCustomerServiceInfoResult(int i, String str, String str2) {
        super(i, str, str2, NXToyRequestTag.GetSvcInfoForCS.getValue());
        this.result = new ResultSet();
    }
}
