package io.fabric.unity.android;

import android.os.Bundle;
import io.fabric.sdk.android.Kit;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class KitInstantiator {
    static final String TWITTERAUTH_KEY = "io.fabric.twittercore.key";
    static final String TWITTERAUTH_SECRET = "io.fabric.twittercore.secret";
    private static final String TWITTER_AUTH_CLASSNAME = "com.twitter.sdk.android.core.TwitterAuthConfig";
    static final String TWITTER_CORE = "TwitterCore";
    private final Bundle manifestMetadata;

    public KitInstantiator() {
        this(new Bundle());
    }

    public KitInstantiator(Bundle bundle) {
        this.manifestMetadata = bundle;
    }

    private Kit instantiateKit(KitData kitData) throws IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Class<? extends U> clsAsSubclass = Class.forName(kitData.fullyQualifiedClassName).asSubclass(Kit.class);
        if (!TWITTER_CORE.equals(kitData.kitName)) {
            return (Kit) clsAsSubclass.getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        if (!this.manifestMetadata.containsKey(TWITTERAUTH_KEY) || !this.manifestMetadata.containsKey(TWITTERAUTH_SECRET)) {
            throw new FabricInitializationException("Could not find Twitter key and secret.");
        }
        return instantiateTwitterKit(kitData.fullyQualifiedClassName, this.manifestMetadata.getString(TWITTERAUTH_KEY), this.manifestMetadata.getString(TWITTERAUTH_SECRET));
    }

    private Kit instantiateTwitterKit(String str, String str2, String str3) throws IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Class<? extends U> clsAsSubclass = Class.forName(str).asSubclass(Kit.class);
        Class<?> cls = Class.forName(TWITTER_AUTH_CLASSNAME);
        return (Kit) clsAsSubclass.getConstructor(cls).newInstance(cls.getConstructor(String.class, String.class).newInstance(str2, str3));
    }

    public Kit[] createKitsFromKitData(KitData[] kitDataArr) {
        int length = kitDataArr.length;
        Kit[] kitArr = new Kit[length];
        for (int i = 0; i < length; i++) {
            try {
                kitArr[i] = instantiateKit(kitDataArr[i]);
            } catch (FabricInitializationException e) {
                throw e;
            } catch (Exception e2) {
                throw new FabricInitializationException("Could not instantiate kits", e2);
            }
        }
        return kitArr;
    }
}
