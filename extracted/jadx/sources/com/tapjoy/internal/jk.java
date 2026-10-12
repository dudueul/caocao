package com.tapjoy.internal;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompatExtras;
import android.support.v4.app.NotificationCompatJellybean;
import android.util.Log;
import android.util.SparseArray;
import com.facebook.applinks.AppLinkData;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@RequiresApi(16)
final class jk {
    private static Field b;
    private static boolean c;
    private static final Object a = new Object();
    private static final Object d = new Object();

    public static Bundle a(Notification.Builder builder, ji.a aVar) {
        builder.addAction(aVar.e, aVar.f, aVar.g);
        Bundle bundle = new Bundle(aVar.a);
        if (aVar.b != null) {
            bundle.putParcelableArray(NotificationCompatExtras.EXTRA_REMOTE_INPUTS, a(aVar.b));
        }
        if (aVar.c != null) {
            bundle.putParcelableArray("android.support.dataRemoteInputs", a(aVar.c));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.d);
        return bundle;
    }

    public static Bundle a(Notification notification) {
        synchronized (a) {
            if (c) {
                return null;
            }
            try {
                if (b == null) {
                    Field declaredField = Notification.class.getDeclaredField(AppLinkData.ARGUMENTS_EXTRAS_KEY);
                    if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                        Log.e(NotificationCompatJellybean.TAG, "Notification.extras field is not of type Bundle");
                        c = true;
                        return null;
                    }
                    declaredField.setAccessible(true);
                    b = declaredField;
                }
                Bundle bundle = (Bundle) b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    b.set(notification, bundle);
                }
                return bundle;
            } catch (IllegalAccessException e) {
                Log.e(NotificationCompatJellybean.TAG, "Unable to access notification extras", e);
                c = true;
                return null;
            } catch (NoSuchFieldException e2) {
                Log.e(NotificationCompatJellybean.TAG, "Unable to access notification extras", e2);
                c = true;
                return null;
            }
        }
    }

    public static SparseArray a(List list) {
        SparseArray sparseArray = null;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Bundle bundle = (Bundle) list.get(i);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                sparseArray.put(i, bundle);
            }
        }
        return sparseArray;
    }

    private static Bundle[] a(jl[] jlVarArr) {
        if (jlVarArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[jlVarArr.length];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= jlVarArr.length) {
                return bundleArr;
            }
            jl jlVar = jlVarArr[i2];
            Bundle bundle = new Bundle();
            bundle.putString("resultKey", jlVar.a);
            bundle.putCharSequence("label", jlVar.b);
            bundle.putCharSequenceArray("choices", jlVar.c);
            bundle.putBoolean("allowFreeFormInput", jlVar.d);
            bundle.putBundle(AppLinkData.ARGUMENTS_EXTRAS_KEY, jlVar.e);
            Set set = jlVar.f;
            if (set != null && !set.isEmpty()) {
                ArrayList<String> arrayList = new ArrayList<>(set.size());
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    arrayList.add((String) it.next());
                }
                bundle.putStringArrayList("allowedDataTypes", arrayList);
            }
            bundleArr[i2] = bundle;
            i = i2 + 1;
        }
    }
}
