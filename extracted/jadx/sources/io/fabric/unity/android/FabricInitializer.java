package io.fabric.unity.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FabricInitializer {
    private static Context savedContext = null;
    private static String AUTOMATIC = "Automatic";
    private static String MANUAL = "Manual";

    public enum Caller {
        Android,
        Unity
    }

    public static String JNI_InitializeFabric() {
        return initializeFabric(savedContext, Caller.Unity);
    }

    private static Bundle getManifestMetadata(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(Fabric.TAG, "Could not retrieve application metadata", e);
            return null;
        }
    }

    public static String initializeFabric(Context context, Caller caller) {
        savedContext = context;
        if (savedContext == null) {
            throw new FabricInitializationException("Fabric did not find a valid application context.");
        }
        Bundle manifestMetadata = getManifestMetadata(savedContext);
        if (manifestMetadata == null) {
            throw new FabricInitializationException("Fabric initialization metadata missing. Check your AndroidManifest.xml");
        }
        BundleKitDataProvider bundleKitDataProvider = new BundleKitDataProvider(manifestMetadata);
        Kit[] kitArrCreateKitsFromKitData = new KitInstantiator(manifestMetadata).createKitsFromKitData(bundleKitDataProvider.getKitData());
        if (kitArrCreateKitsFromKitData == null || kitArrCreateKitsFromKitData.length < 1) {
            Log.w(Fabric.TAG, "Fabric found no kits to initialize.");
            return "";
        }
        String initializationType = bundleKitDataProvider.getInitializationType();
        if ((initializationType.equals(AUTOMATIC) && caller == Caller.Android) || (initializationType.equals(MANUAL) && caller == Caller.Unity)) {
            Fabric.with(savedContext, kitArrCreateKitsFromKitData);
        }
        return bundleKitDataProvider.getInitializationKitsList();
    }
}
