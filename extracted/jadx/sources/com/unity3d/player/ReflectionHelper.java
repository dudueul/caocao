package com.unity3d.player;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class ReflectionHelper {
    protected static final boolean LOGV = false;
    protected static boolean LOG = false;
    private static a[] a = new a[4096];

    private static class a {
        public volatile Member a;
        private final Class b;
        private final String c;
        private final String d;
        private final int e;

        a(Class cls, String str, String str2) {
            this.b = cls;
            this.c = str;
            this.d = str2;
            this.e = ((((this.b.hashCode() + 527) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.e == aVar.e && this.d.equals(aVar.d) && this.c.equals(aVar.c) && this.b.equals(aVar.b);
        }

        public final int hashCode() {
            return this.e;
        }
    }

    ReflectionHelper() {
    }

    private static float a(Class cls, Class cls2) {
        if (cls.equals(cls2)) {
            return 1.0f;
        }
        if (!cls.isPrimitive() && !cls2.isPrimitive()) {
            try {
                if (cls.asSubclass(cls2) != null) {
                    return 0.5f;
                }
            } catch (ClassCastException e) {
            }
            try {
                if (cls2.asSubclass(cls) != null) {
                    return 0.1f;
                }
            } catch (ClassCastException e2) {
            }
        }
        return 0.0f;
    }

    private static float a(Class cls, Class[] clsArr, Class[] clsArr2) {
        if (clsArr2.length == 0) {
            return 0.1f;
        }
        if ((clsArr == null ? 0 : clsArr.length) + 1 != clsArr2.length) {
            return 0.0f;
        }
        float fA = 1.0f;
        if (clsArr != null) {
            int length = clsArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                fA *= a(clsArr[i], clsArr2[i2]);
                i++;
                i2++;
            }
        }
        return fA * a(cls, clsArr2[clsArr2.length - 1]);
    }

    private static Class a(String str, int[] iArr) {
        while (iArr[0] < str.length()) {
            int i = iArr[0];
            iArr[0] = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt != '(' && cCharAt != ')') {
                if (cCharAt != 'L') {
                    if (cCharAt != 'Z') {
                        if (cCharAt != 'I') {
                            if (cCharAt != 'F') {
                                if (cCharAt != 'V') {
                                    if (cCharAt != 'B') {
                                        if (cCharAt != 'S') {
                                            if (cCharAt != 'J') {
                                                if (cCharAt != 'D') {
                                                    if (cCharAt != '[') {
                                                        g.Log(5, "! parseType; " + cCharAt + " is not known!");
                                                        break;
                                                    }
                                                    return Array.newInstance((Class<?>) a(str, iArr), 0).getClass();
                                                }
                                                return Double.TYPE;
                                            }
                                            return Long.TYPE;
                                        }
                                        return Short.TYPE;
                                    }
                                    return Byte.TYPE;
                                }
                                return Void.TYPE;
                            }
                            return Float.TYPE;
                        }
                        return Integer.TYPE;
                    }
                    return Boolean.TYPE;
                }
                int iIndexOf = str.indexOf(59, iArr[0]);
                if (iIndexOf == -1) {
                    break;
                }
                String strSubstring = str.substring(iArr[0], iIndexOf);
                iArr[0] = iIndexOf + 1;
                try {
                    return Class.forName(strSubstring.replace('/', '.'));
                } catch (ClassNotFoundException e) {
                    break;
                }
            }
        }
        return null;
    }

    private static void a(a aVar, Member member) {
        aVar.a = member;
        a[aVar.hashCode() & (a.length - 1)] = aVar;
    }

    private static boolean a(a aVar) {
        a aVar2 = a[aVar.hashCode() & (a.length - 1)];
        if (!aVar.equals(aVar2)) {
            return false;
        }
        aVar.a = aVar2.a;
        return true;
    }

    private static Class[] a(String str) {
        Class clsA;
        int[] iArr = {0};
        ArrayList arrayList = new ArrayList();
        while (iArr[0] < str.length() && (clsA = a(str, iArr)) != null) {
            arrayList.add(clsA);
        }
        Class[] clsArr = new Class[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            clsArr[i] = (Class) it.next();
            i++;
        }
        return clsArr;
    }

    protected static Constructor getConstructorID(Class cls, String str) {
        Constructor<?> constructor;
        if (LOG) {
            g.Log(3, "? getConstructorID(\"" + cls.getName() + "\", \"" + str + "\")");
        }
        Constructor<?> constructor2 = null;
        a aVar = new a(cls, "", str);
        if (a(aVar)) {
            constructor = (Constructor) aVar.a;
        } else {
            Class[] clsArrA = a(str);
            float f = 0.0f;
            Constructor<?>[] constructors = cls.getConstructors();
            int length = constructors.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    constructor = constructor2;
                    break;
                }
                constructor = constructors[i];
                float fA = a(Void.TYPE, constructor.getParameterTypes(), clsArrA);
                if (fA > f) {
                    if (fA == 1.0f) {
                        break;
                    }
                } else {
                    fA = f;
                    constructor = constructor2;
                }
                i++;
                f = fA;
                constructor2 = constructor;
            }
            a(aVar, constructor);
        }
        if (constructor == null) {
            throw new NoSuchMethodError("<init>" + str + " in class " + cls.getName());
        }
        if (LOG) {
            StringBuilder sb = new StringBuilder();
            for (Class<?> cls2 : constructor.getParameterTypes()) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(cls2.getSimpleName());
            }
            g.Log(3, "! " + constructor.getName() + "(" + sb.toString() + ");");
        }
        return constructor;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x012f  */
    protected static Field getFieldID(Class cls, String str, String str2, boolean z) {
        Field field;
        float fA;
        if (LOG) {
            g.Log(3, "? getFieldID(\"" + cls.getName() + "\", \"" + str + "\", \"" + str2 + "\", " + (z ? "static)" : "non-static)"));
        }
        Field field2 = null;
        a aVar = new a(cls, str, str2);
        if (a(aVar)) {
            field2 = (Field) aVar.a;
        } else {
            Class[] clsArrA = a(str2);
            float f = 0.0f;
            while (cls != null) {
                Field[] declaredFields = cls.getDeclaredFields();
                int length = declaredFields.length;
                int i = 0;
                Field field3 = field2;
                while (true) {
                    if (i >= length) {
                        field = field3;
                        break;
                    }
                    field = declaredFields[i];
                    if (z == Modifier.isStatic(field.getModifiers()) && field.getName().compareTo(str) == 0) {
                        fA = a(field.getType(), (Class[]) null, clsArrA);
                        if (fA <= f) {
                            fA = f;
                            field = field3;
                        } else if (fA == 1.0f) {
                            f = fA;
                            break;
                        }
                    } else {
                        fA = f;
                        field = field3;
                    }
                    i++;
                    f = fA;
                    field3 = field;
                }
                if (f == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    field2 = field;
                    break;
                }
                cls = cls.getSuperclass();
                field2 = field;
            }
            a(aVar, field2);
        }
        if (field2 != null) {
            if (LOG) {
                g.Log(3, "! " + field2.getType().getSimpleName() + " " + field2.getDeclaringClass().getSimpleName() + "." + field2.getName() + ";");
            }
            return field2;
        }
        Object[] objArr = new Object[4];
        objArr[0] = z ? "static" : "non-static";
        objArr[1] = str;
        objArr[2] = str2;
        objArr[3] = cls.getName();
        throw new NoSuchFieldError(String.format("no %s field with name='%s' signature='%s' in class L%s;", objArr));
    }

    /* JADX WARN: Code duplicated, block: B:56:0x0166  */
    protected static Method getMethodID(Class cls, String str, String str2, boolean z) {
        Method method;
        Method method2;
        float fA;
        if (LOG) {
            g.Log(3, "? getMethodID(\"" + cls.getName() + "\", \"" + str + "\", \"" + str2 + "\", " + (z ? "static)" : "non-static)"));
        }
        Method method3 = null;
        a aVar = new a(cls, str, str2);
        if (a(aVar)) {
            method = (Method) aVar.a;
        } else {
            Class[] clsArrA = a(str2);
            float f = 0.0f;
            while (cls != null) {
                Method[] declaredMethods = cls.getDeclaredMethods();
                int length = declaredMethods.length;
                int i = 0;
                Method method4 = method3;
                while (true) {
                    if (i >= length) {
                        method2 = method4;
                        break;
                    }
                    method2 = declaredMethods[i];
                    if (z == Modifier.isStatic(method2.getModifiers()) && method2.getName().compareTo(str) == 0) {
                        fA = a(method2.getReturnType(), method2.getParameterTypes(), clsArrA);
                        if (fA > f) {
                            if (fA == 1.0f) {
                                f = fA;
                                break;
                            }
                        } else {
                            fA = f;
                            method2 = method4;
                        }
                    } else {
                        fA = f;
                        method2 = method4;
                    }
                    i++;
                    f = fA;
                    method4 = method2;
                }
                if (f == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    method3 = method2;
                    break;
                }
                cls = cls.getSuperclass();
                method3 = method2;
            }
            a(aVar, method3);
            method = method3;
        }
        if (method == null) {
            Object[] objArr = new Object[4];
            objArr[0] = z ? "static" : "non-static";
            objArr[1] = str;
            objArr[2] = str2;
            objArr[3] = cls.getName();
            throw new NoSuchMethodError(String.format("no %s method with name='%s' signature='%s' in class L%s;", objArr));
        }
        if (LOG) {
            StringBuilder sb = new StringBuilder();
            for (Class<?> cls2 : method.getParameterTypes()) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(cls2.getSimpleName());
            }
            g.Log(3, "! " + method.getReturnType().getSimpleName() + " " + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "(" + sb.toString() + ");");
        }
        return method;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeProxyFinalize(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native Object nativeProxyInvoke(int i, String str, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeProxyLogJNIInvokeException();

    protected static Object newProxyInstance(int i, Class cls) {
        return newProxyInstance(i, new Class[]{cls});
    }

    protected static Object newProxyInstance(final int i, final Class[] clsArr) {
        if (LOG) {
            g.Log(3, String.format("ReflectionHelper.Proxy(%d,%s)", Integer.valueOf(i), Arrays.asList(clsArr)));
        }
        return Proxy.newProxyInstance(ReflectionHelper.class.getClassLoader(), clsArr, new InvocationHandler() { // from class: com.unity3d.player.ReflectionHelper.1
            private static Object a(Object obj, Method method, Object[] objArr) throws NoSuchMethodException {
                if (objArr == null) {
                    try {
                        objArr = new Object[0];
                    } catch (NoClassDefFoundError e) {
                        g.Log(6, String.format("Java interface default methods are only supported since Android Oreo", new Object[0]));
                        ReflectionHelper.nativeProxyLogJNIInvokeException();
                        return null;
                    }
                }
                Class<?> declaringClass = method.getDeclaringClass();
                Constructor declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
                declaredConstructor.setAccessible(true);
                return ((MethodHandles.Lookup) declaredConstructor.newInstance(declaringClass, 2)).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
            }

            protected final void finalize() throws Throwable {
                try {
                    if (ReflectionHelper.LOG) {
                        g.Log(3, String.format("ReflectionHelper.Proxy.finilize(%d, %s)", Integer.valueOf(i), Arrays.asList(clsArr)));
                    }
                    ReflectionHelper.nativeProxyFinalize(i);
                } finally {
                    super.finalize();
                }
            }

            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object obj, Method method, Object[] objArr) {
                if (ReflectionHelper.LOG) {
                    Object[] objArr2 = new Object[4];
                    objArr2[0] = Integer.valueOf(i);
                    objArr2[1] = Arrays.asList(clsArr);
                    objArr2[2] = method.getName();
                    objArr2[3] = objArr == null ? "<null>" : Arrays.asList(objArr);
                    g.Log(3, String.format("ReflectionHelper.Proxy.invoke(%d, %s, %s, %s)", objArr2));
                }
                Object objNativeProxyInvoke = ReflectionHelper.nativeProxyInvoke(i, method.getName(), objArr);
                if (objNativeProxyInvoke != null) {
                    return objNativeProxyInvoke;
                }
                if ((method.getModifiers() & 1024) == 0) {
                    return a(obj, method, objArr);
                }
                ReflectionHelper.nativeProxyLogJNIInvokeException();
                return objNativeProxyInvoke;
            }
        });
    }
}
