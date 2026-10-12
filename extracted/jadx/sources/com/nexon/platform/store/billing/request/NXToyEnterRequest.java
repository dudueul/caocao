package com.nexon.platform.store.billing.request;

import android.support.v4.util.ArrayMap;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyStoreRequest;
import com.nexon.platform.store.NexonStore;
import com.nexon.platform.store.billing.result.NXToyEnterResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyEnterRequest extends NXToyStoreRequest {
    public NXToyEnterRequest(String str) {
        super.setMethod(NXToyRequestMethod.GET);
        super.addPathToHttpURL("/sdk/v1/enter?market_type=" + NexonStore.getMarketType() + "&client_id=" + str);
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(NXToyStoreRequest.X_TOY_TICKET_HEADER_FIELD_NAME, NexonStore.getTicket());
        super.putMessageHeader(arrayMap);
        super.setResultClass(NXToyEnterResult.class);
    }
}
