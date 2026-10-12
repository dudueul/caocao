package com.nexon.platform.store.billing.request;

import android.support.v4.util.ArrayMap;
import com.facebook.AccessToken;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyStoreRequest;
import com.nexon.platform.store.NexonStore;
import com.nexon.platform.store.billing.result.NXToyIssueResult;
import com.nexon.platform.store.internal.Utility;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyIssueRequest extends NXToyStoreRequest {
    public NXToyIssueRequest(String str, String str2, String str3, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, String str4) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/v1/issue");
        String strBase64EncodeStr = Utility.base64EncodeStr(jSONObject.toString());
        String strBase64EncodeStr2 = Utility.base64EncodeStr(jSONObject2.toString());
        String strBase64EncodeStr3 = Utility.base64EncodeStr(jSONObject3.toString());
        HashMap map = new HashMap();
        map.put(AccessToken.USER_ID_KEY, str);
        map.put("user_type", NexonStore.USER_TYPE);
        map.put("market_type", NexonStore.getMarketType());
        map.put("product_id", str2);
        map.put("client_id", str3);
        map.put("issue_type", str4);
        map.put("game_meta", strBase64EncodeStr);
        map.put("service_payload", strBase64EncodeStr2);
        map.put("stamp_payload", strBase64EncodeStr3);
        super.setMessageBody(map);
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(NXToyStoreRequest.X_TOY_TICKET_HEADER_FIELD_NAME, NexonStore.getTicket());
        super.putMessageHeader(arrayMap);
        super.setResultClass(NXToyIssueResult.class);
    }
}
