package com.naver.glink.android.sdk.statistics.jackpot;

import com.tapjoy.TJAdUnitConstants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum JackpotAction {
    SCENE_ENTER("scene_enter"),
    CLICK(TJAdUnitConstants.String.CLICK),
    CREATE("create");

    private String id;

    JackpotAction(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }
}
