package com.nexon.platform.store.billing.request;

import android.support.v4.util.ArrayMap;
import com.facebook.AccessToken;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyStoreRequest;
import com.nexon.platform.store.NexonStore;
import com.nexon.platform.store.billing.result.NXToyRestoreResult;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyRestoreRequest extends NXToyStoreRequest {
    public NXToyRestoreRequest(String str) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/v1/restore");
        HashMap map = new HashMap();
        map.put(AccessToken.USER_ID_KEY, str);
        map.put("market_type", NexonStore.getMarketType());
        map.put("user_type", NexonStore.USER_TYPE);
        super.setMessageBody(map);
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(NXToyStoreRequest.X_TOY_TICKET_HEADER_FIELD_NAME, NexonStore.getTicket());
        super.putMessageHeader(arrayMap);
        super.setResultClass(NXToyRestoreResult.class);
    }
}
