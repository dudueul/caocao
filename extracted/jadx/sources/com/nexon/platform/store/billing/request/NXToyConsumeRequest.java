package com.nexon.platform.store.billing.request;

import android.support.v4.util.ArrayMap;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyStoreRequest;
import com.nexon.platform.store.NexonStore;
import com.nexon.platform.store.billing.result.NXToyConsumeResult;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyConsumeRequest extends NXToyStoreRequest {
    public NXToyConsumeRequest(String str, String str2) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/v1/consume");
        HashMap map = new HashMap();
        map.put("stamp_id", str);
        map.put("consume_type", str2);
        super.setMessageBody(map);
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(NXToyStoreRequest.X_TOY_TICKET_HEADER_FIELD_NAME, NexonStore.getTicket());
        super.putMessageHeader(arrayMap);
        super.setResultClass(NXToyConsumeResult.class);
    }
}
