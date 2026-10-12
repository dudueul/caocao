package com.adjust.sdk;

import android.content.Context;
import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import com.adjust.sdk.plugin.Plugin;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Reflection {
    public static Object createDefaultInstance(Class cls) {
        try {
            return cls.newInstance();
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object createDefaultInstance(String str) {
        return createDefaultInstance(forName(str));
    }

    public static Object createInstance(String str, Class[] clsArr, Object... objArr) {
        try {
            return Class.forName(str).getConstructor(clsArr).newInstance(objArr);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Class forName(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }

    private static Object getAdvertisingInfoObject(Context context) throws Exception {
        return invokeStaticMethod("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", new Class[]{Context.class}, context);
    }

    public static String getAndroidId(Context context) {
        try {
            return (String) invokeStaticMethod("com.adjust.sdk.plugin.AndroidIdUtil", "getAndroidId", new Class[]{Context.class}, context);
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getCpuAbi() {
        try {
            return (String) readField("android.os.Build", "CPU_ABI");
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getImei(TelephonyManager telephonyManager) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getImei", null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getImei(TelephonyManager telephonyManager, int i) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getImei", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        } catch (Exception e) {
            return null;
        }
    }

    public static Locale getLocaleFromField(Configuration configuration) {
        try {
            return (Locale) readField("android.content.res.Configuration", "locale", configuration);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Locale getLocaleFromLocaleList(Configuration configuration) {
        Locale locale = null;
        try {
            Object objInvokeInstanceMethod = invokeInstanceMethod(configuration, "getLocales", null, new Object[0]);
            if (objInvokeInstanceMethod == null) {
                return null;
            }
            locale = (Locale) invokeInstanceMethod(objInvokeInstanceMethod, "get", new Class[]{Integer.TYPE}, 0);
        } catch (Throwable th) {
        }
        return locale;
    }

    public static String getMacAddress(Context context) {
        try {
            return (String) invokeStaticMethod("com.adjust.sdk.plugin.MacAddressUtil", "getMacAddress", new Class[]{Context.class}, context);
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getMeid(TelephonyManager telephonyManager) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getMeid", null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMeid(TelephonyManager telephonyManager, int i) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getMeid", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        } catch (Exception e) {
            return null;
        }
    }

    public static String getPlayAdId(Context context) {
        try {
            return (String) invokeInstanceMethod(getAdvertisingInfoObject(context), "getId", null, new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Map<String, String> getPluginKeys(Context context) {
        HashMap map = new HashMap();
        Iterator<Plugin> it = getPlugins().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> parameter = it.next().getParameter(context);
            if (parameter != null) {
                map.put(parameter.getKey(), parameter.getValue());
            }
        }
        if (map.size() == 0) {
            return null;
        }
        return map;
    }

    private static List<Plugin> getPlugins() {
        ArrayList arrayList = new ArrayList(Constants.PLUGINS.size());
        Iterator<String> it = Constants.PLUGINS.iterator();
        while (it.hasNext()) {
            Object objCreateDefaultInstance = createDefaultInstance(it.next());
            if (objCreateDefaultInstance != null && (objCreateDefaultInstance instanceof Plugin)) {
                arrayList.add((Plugin) objCreateDefaultInstance);
            }
        }
        return arrayList;
    }

    public static String[] getSupportedAbis() {
        try {
            return (String[]) readField("android.os.Build", "SUPPORTED_ABIS");
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getTelephonyId(TelephonyManager telephonyManager) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getDeviceId", null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTelephonyId(TelephonyManager telephonyManager, int i) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getDeviceId", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        } catch (Exception e) {
            return null;
        }
    }

    public static Object getVMRuntimeObject() {
        try {
            return invokeStaticMethod("dalvik.system.VMRuntime", "getRuntime", null, new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getVmInstructionSet() {
        try {
            return (String) invokeInstanceMethod(getVMRuntimeObject(), "vmInstructionSet", null, new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object invokeInstanceMethod(Object obj, String str, Class[] clsArr, Object... objArr) throws Exception {
        return invokeMethod(obj.getClass(), str, obj, clsArr, objArr);
    }

    public static Object invokeMethod(Class cls, String str, Object obj, Class[] clsArr, Object... objArr) throws Exception {
        Method method = cls.getMethod(str, clsArr);
        if (method == null) {
            return null;
        }
        return method.invoke(obj, objArr);
    }

    public static Object invokeStaticMethod(String str, String str2, Class[] clsArr, Object... objArr) throws Exception {
        return invokeMethod(Class.forName(str), str2, null, clsArr, objArr);
    }

    public static Boolean isPlayTrackingEnabled(Context context) {
        try {
            Boolean bool = (Boolean) invokeInstanceMethod(getAdvertisingInfoObject(context), "isLimitAdTrackingEnabled", null, new Object[0]);
            if (bool == null) {
                return null;
            }
            return Boolean.valueOf(bool.booleanValue() ? false : true);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object readField(String str, String str2) throws Exception {
        return readField(str, str2, null);
    }

    public static Object readField(String str, String str2, Object obj) throws Exception {
        Field field;
        Class clsForName = forName(str);
        if (clsForName == null || (field = clsForName.getField(str2)) == null) {
            return null;
        }
        return field.get(obj);
    }
}
