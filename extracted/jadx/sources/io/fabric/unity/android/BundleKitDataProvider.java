package io.fabric.unity.android;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import io.fabric.sdk.android.Fabric;
import java.util.LinkedList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class BundleKitDataProvider implements KitDataProvider {
    static final String INITIALIZATION_KITS_LIST = "InitializationKitsList";
    static final String INITIALIZATION_TYPE = "InitializationType";
    static final String KEY_PREFIX = "io.fabric";
    static final String KITS_KEY = "io.fabric.kits";
    static final String KITS_SUFFIX = ".kits";
    static final String NAME_SUFFIX = ".unqualified";
    private static final String[] NO_KEYS = new String[0];
    static final String QUALIFIED_SUFFIX = ".qualified";
    private final Bundle metadata;

    public BundleKitDataProvider(Bundle bundle) {
        this.metadata = bundle;
    }

    private String[] getKitKeys() {
        String string = this.metadata.getString(KITS_KEY);
        return TextUtils.isEmpty(string) ? NO_KEYS : string.split(",");
    }

    @Override // io.fabric.unity.android.KitDataProvider
    public String getInitializationKitsList() {
        return this.metadata.getString("io.fabric.InitializationKitsList");
    }

    @Override // io.fabric.unity.android.KitDataProvider
    public String getInitializationType() {
        return this.metadata.getString("io.fabric.InitializationType");
    }

    @Override // io.fabric.unity.android.KitDataProvider
    public KitData[] getKitData() {
        LinkedList linkedList = new LinkedList();
        for (String str : getKitKeys()) {
            String str2 = "io.fabric." + str;
            String str3 = str2 + NAME_SUFFIX;
            String str4 = str2 + QUALIFIED_SUFFIX;
            if (this.metadata.containsKey(str3)) {
                linkedList.add(new KitData(this.metadata.getString(str3), this.metadata.getString(str4)));
            } else {
                Log.w(Fabric.TAG, "Could not find kit info for key " + str3);
            }
        }
        return (KitData[]) linkedList.toArray(new KitData[linkedList.size()]);
    }
}
