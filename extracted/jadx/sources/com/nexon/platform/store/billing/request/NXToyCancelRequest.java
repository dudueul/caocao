package com.nexon.platform.store.billing.request;

import android.support.v4.util.ArrayMap;
import com.nexon.core.requestpostman.constants.NXToyRequestMethod;
import com.nexon.core.requestpostman.request.NXToyStoreRequest;
import com.nexon.platform.store.NexonStore;
import com.nexon.platform.store.billing.result.NXToyCancelResult;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyCancelRequest extends NXToyStoreRequest {
    public NXToyCancelRequest(String str, String str2, String str3) {
        super.setMethod(NXToyRequestMethod.POST);
        super.addPathToHttpURL("/sdk/v1/cancel");
        HashMap map = new HashMap();
        map.put("stamp_id", str);
        map.put("cancel_type", str2);
        map.put("description", str3);
        super.setMessageBody(map);
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(NXToyStoreRequest.X_TOY_TICKET_HEADER_FIELD_NAME, NexonStore.getTicket());
        super.putMessageHeader(arrayMap);
        super.setResultClass(NXToyCancelResult.class);
    }
}
