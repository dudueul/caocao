package com.google.android.gms.dynamic;

import android.os.IBinder;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class ObjectWrapper<T> extends IObjectWrapper.Stub {
    private final T zzabn;

    private ObjectWrapper(T t) {
        this.zzabn = t;
    }

    public static <T> T unwrap(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper instanceof ObjectWrapper) {
            return ((ObjectWrapper) iObjectWrapper).zzabn;
        }
        IBinder iBinderAsBinder = iObjectWrapper.asBinder();
        Field[] declaredFields = iBinderAsBinder.getClass().getDeclaredFields();
        Field field = null;
        int length = declaredFields.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Field field2 = declaredFields[i];
            if (field2.isSynthetic()) {
                field2 = field;
            } else {
                i2++;
            }
            i++;
            field = field2;
        }
        if (i2 != 1) {
            throw new IllegalArgumentException(new StringBuilder(64).append("Unexpected number of IObjectWrapper declared fields: ").append(declaredFields.length).toString());
        }
        if (field.isAccessible()) {
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
        field.setAccessible(true);
        try {
            return (T) field.get(iBinderAsBinder);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Could not access the field in remoteBinder.", e);
        } catch (NullPointerException e2) {
            throw new IllegalArgumentException("Binder object is null.", e2);
        }
    }

    public static <T> IObjectWrapper wrap(T t) {
        return new ObjectWrapper(t);
    }
}
