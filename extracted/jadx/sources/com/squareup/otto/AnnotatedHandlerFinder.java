package com.squareup.otto;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class AnnotatedHandlerFinder {
    private static final ConcurrentMap<Class<?>, Map<Class<?>, Method>> PRODUCERS_CACHE = new ConcurrentHashMap();
    private static final ConcurrentMap<Class<?>, Map<Class<?>, Set<Method>>> SUBSCRIBERS_CACHE = new ConcurrentHashMap();

    private AnnotatedHandlerFinder() {
    }

    static Map<Class<?>, EventProducer> findAllProducers(Object obj) {
        Class<?> cls = obj.getClass();
        HashMap map = new HashMap();
        Map<Class<?>, Method> map2 = PRODUCERS_CACHE.get(cls);
        if (map2 == null) {
            map2 = new HashMap<>();
            loadAnnotatedProducerMethods(cls, map2);
        }
        if (!map2.isEmpty()) {
            for (Map.Entry<Class<?>, Method> entry : map2.entrySet()) {
                map.put(entry.getKey(), new EventProducer(obj, entry.getValue()));
            }
        }
        return map;
    }

    static Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object obj) {
        Class<?> cls = obj.getClass();
        HashMap map = new HashMap();
        Map<Class<?>, Set<Method>> map2 = SUBSCRIBERS_CACHE.get(cls);
        if (map2 == null) {
            map2 = new HashMap<>();
            loadAnnotatedSubscriberMethods(cls, map2);
        }
        if (!map2.isEmpty()) {
            for (Map.Entry<Class<?>, Set<Method>> entry : map2.entrySet()) {
                HashSet hashSet = new HashSet();
                Iterator<Method> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    hashSet.add(new EventHandler(obj, it.next()));
                }
                map.put(entry.getKey(), hashSet);
            }
        }
        return map;
    }

    private static void loadAnnotatedMethods(Class<?> cls, Map<Class<?>, Method> map, Map<Class<?>, Set<Method>> map2) {
        for (Method method : cls.getDeclaredMethods()) {
            if (!method.isBridge()) {
                if (method.isAnnotationPresent(Subscribe.class)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation but requires " + parameterTypes.length + " arguments.  Methods must require a single argument.");
                    }
                    Class<?> cls2 = parameterTypes[0];
                    if (cls2.isInterface()) {
                        throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + cls2 + " which is an interface.  Subscription must be on a concrete class type.");
                    }
                    if ((method.getModifiers() & 1) == 0) {
                        throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + cls2 + " but is not 'public'.");
                    }
                    Set<Method> hashSet = map2.get(cls2);
                    if (hashSet == null) {
                        hashSet = new HashSet<>();
                        map2.put(cls2, hashSet);
                    }
                    hashSet.add(method);
                } else if (method.isAnnotationPresent(Produce.class)) {
                    Class<?>[] parameterTypes2 = method.getParameterTypes();
                    if (parameterTypes2.length != 0) {
                        throw new IllegalArgumentException("Method " + method + "has @Produce annotation but requires " + parameterTypes2.length + " arguments.  Methods must require zero arguments.");
                    }
                    if (method.getReturnType() == Void.class) {
                        throw new IllegalArgumentException("Method " + method + " has a return type of void.  Must declare a non-void type.");
                    }
                    Class<?> returnType = method.getReturnType();
                    if (returnType.isInterface()) {
                        throw new IllegalArgumentException("Method " + method + " has @Produce annotation on " + returnType + " which is an interface.  Producers must return a concrete class type.");
                    }
                    if (returnType.equals(Void.TYPE)) {
                        throw new IllegalArgumentException("Method " + method + " has @Produce annotation but has no return type.");
                    }
                    if ((method.getModifiers() & 1) == 0) {
                        throw new IllegalArgumentException("Method " + method + " has @Produce annotation on " + returnType + " but is not 'public'.");
                    }
                    if (map.containsKey(returnType)) {
                        throw new IllegalArgumentException("Producer for type " + returnType + " has already been registered.");
                    }
                    map.put(returnType, method);
                } else {
                    continue;
                }
            }
        }
        PRODUCERS_CACHE.put(cls, map);
        SUBSCRIBERS_CACHE.put(cls, map2);
    }

    private static void loadAnnotatedProducerMethods(Class<?> cls, Map<Class<?>, Method> map) {
        loadAnnotatedMethods(cls, map, new HashMap());
    }

    private static void loadAnnotatedSubscriberMethods(Class<?> cls, Map<Class<?>, Set<Method>> map) {
        loadAnnotatedMethods(cls, new HashMap(), map);
    }
}
