package com.google.firebase.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzc {
    private final Context zza;
    private final zzb zzb;

    static class zza implements zzb {
        private zza() {
        }

        /* synthetic */ zza(byte b) {
            this();
        }

        private static Bundle zzb(Context context) {
            Bundle bundle = null;
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    Log.w("ComponentDiscovery", "Context has no PackageManager.");
                } else {
                    ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, (Class<?>) ComponentDiscoveryService.class), 128);
                    if (serviceInfo == null) {
                        Log.w("ComponentDiscovery", "ComponentDiscoveryService has no service info.");
                    } else {
                        bundle = serviceInfo.metaData;
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.w("ComponentDiscovery", "Application info not found.");
            }
            return bundle;
        }

        @Override // com.google.firebase.components.zzc.zzb
        public final List<String> zza(Context context) {
            Bundle bundleZzb = zzb(context);
            if (bundleZzb == null) {
                Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (String str : bundleZzb.keySet()) {
                if ("com.google.firebase.components.ComponentRegistrar".equals(bundleZzb.get(str)) && str.startsWith("com.google.firebase.components:")) {
                    arrayList.add(str.substring(31));
                }
            }
            return arrayList;
        }
    }

    @VisibleForTesting
    interface zzb {
        List<String> zza(Context context);
    }

    public zzc(Context context) {
        this(context, new zza((byte) 0));
    }

    @VisibleForTesting
    private zzc(Context context, zzb zzbVar) {
        this.zza = context;
        this.zzb = zzbVar;
    }

    private static List<ComponentRegistrar> zza(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            try {
                Class<?> cls = Class.forName(str);
                if (ComponentRegistrar.class.isAssignableFrom(cls)) {
                    arrayList.add((ComponentRegistrar) cls.newInstance());
                } else {
                    Log.w("ComponentDiscovery", String.format("Class %s is not an instance of %s", str, "com.google.firebase.components.ComponentRegistrar"));
                }
            } catch (ClassNotFoundException e) {
                Log.w("ComponentDiscovery", String.format("Class %s is not an found.", str), e);
            } catch (IllegalAccessException e2) {
                Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", str), e2);
            } catch (InstantiationException e3) {
                Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", str), e3);
            }
        }
        return arrayList;
    }

    public final List<ComponentRegistrar> zza() {
        return zza(this.zzb.zza(this.zza));
    }
}
