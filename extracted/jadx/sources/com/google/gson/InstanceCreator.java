package com.google.gson;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface InstanceCreator<T> {
    T createInstance(Type type);
}
