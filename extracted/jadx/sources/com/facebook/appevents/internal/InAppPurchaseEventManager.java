package com.facebook.appevents.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.FacebookSdk;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class InAppPurchaseEventManager {
    private static final String AS_INTERFACE = "asInterface";
    private static final int CACHE_CLEAR_TIME_LIMIT_SEC = 604800;
    private static final String DETAILS_LIST = "DETAILS_LIST";
    private static final String GET_PURCHASES = "getPurchases";
    private static final String GET_PURCHASE_HISTORY = "getPurchaseHistory";
    private static final String GET_SKU_DETAILS = "getSkuDetails";
    private static final String INAPP = "inapp";
    private static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    private static final String INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    private static final String IN_APP_BILLING_SERVICE = "com.android.vending.billing.IInAppBillingService";
    private static final String IN_APP_BILLING_SERVICE_STUB = "com.android.vending.billing.IInAppBillingService$Stub";
    private static final String IS_BILLING_SUPPORTED = "isBillingSupported";
    private static final String ITEM_ID_LIST = "ITEM_ID_LIST";
    private static final String LAST_CLEARED_TIME = "LAST_CLEARED_TIME";
    private static final String LAST_LOGGED_TIME_SEC = "LAST_LOGGED_TIME_SEC";
    private static final int MAX_QUERY_PURCHASE_NUM = 30;
    private static final int PURCHASE_EXPIRE_TIME_SEC = 43200;
    private static final int PURCHASE_STOP_QUERY_TIME_SEC = 1200;
    private static final String RESPONSE_CODE = "RESPONSE_CODE";
    private static final int SKU_DETAIL_EXPIRE_TIME_SEC = 43200;
    private static final String SUBSCRIPTION = "subs";
    private static final long SUBSCRIPTION_HARTBEAT_INTERVAL = 86400;
    private static final String TAG = InAppPurchaseEventManager.class.getCanonicalName();
    private static final HashMap<String, Method> methodMap = new HashMap<>();
    private static final HashMap<String, Class<?>> classMap = new HashMap<>();
    private static final String PACKAGE_NAME = FacebookSdk.getApplicationContext().getPackageName();
    private static final String SKU_DETAILS_STORE = "com.facebook.internal.SKU_DETAILS";
    private static final SharedPreferences skuDetailSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences(SKU_DETAILS_STORE, 0);
    private static final String PURCHASE_INAPP_STORE = "com.facebook.internal.PURCHASE";
    private static final SharedPreferences purchaseInappSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences(PURCHASE_INAPP_STORE, 0);
    private static final String PURCHASE_SUBS_STORE = "com.facebook.internal.PURCHASE_SUBS";
    private static final SharedPreferences purchaseSubsSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences(PURCHASE_SUBS_STORE, 0);

    InAppPurchaseEventManager() {
    }

    @Nullable
    public static Object asInterface(Context context, IBinder iBinder) {
        return invokeMethod(context, IN_APP_BILLING_SERVICE_STUB, AS_INTERFACE, null, new Object[]{iBinder});
    }

    public static void clearSkuDetailsCache() {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        long j = skuDetailSharedPrefs.getLong(LAST_CLEARED_TIME, 0L);
        if (j == 0) {
            skuDetailSharedPrefs.edit().putLong(LAST_CLEARED_TIME, jCurrentTimeMillis).apply();
        } else if (jCurrentTimeMillis - j > 604800) {
            skuDetailSharedPrefs.edit().clear().putLong(LAST_CLEARED_TIME, jCurrentTimeMillis).apply();
        }
    }

    private static ArrayList<String> filterPurchasesInapp(ArrayList<String> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        SharedPreferences.Editor editorEdit = purchaseInappSharedPrefs.edit();
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        for (String str : arrayList) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("productId");
                long j = jSONObject.getLong("purchaseTime");
                String string2 = jSONObject.getString("purchaseToken");
                if (jCurrentTimeMillis - (j / 1000) <= 43200 && !purchaseInappSharedPrefs.getString(string, "").equals(string2)) {
                    editorEdit.putString(string, string2);
                    arrayList2.add(str);
                }
            } catch (JSONException e) {
                Log.e(TAG, "parsing purchase failure: ", e);
            }
        }
        editorEdit.apply();
        return arrayList2;
    }

    @Nullable
    private static Class<?> getClass(Context context, String str) {
        Class<?> clsLoadClass = classMap.get(str);
        if (clsLoadClass != null) {
            return clsLoadClass;
        }
        try {
            clsLoadClass = context.getClassLoader().loadClass(str);
            classMap.put(str, clsLoadClass);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, str + " is not available, please add " + str + " to the project.", e);
        }
        return clsLoadClass;
    }

    @Nullable
    private static Method getMethod(Class<?> cls, String str) {
        byte b = 0;
        Method declaredMethod = methodMap.get(str);
        if (declaredMethod != null) {
            return declaredMethod;
        }
        Class<?>[] clsArr = null;
        try {
            switch (str.hashCode()) {
                case -1801122596:
                    b = !str.equals(GET_PURCHASES) ? (byte) -1 : (byte) 3;
                    break;
                case -1450694211:
                    b = !str.equals(IS_BILLING_SUPPORTED) ? (byte) -1 : (byte) 2;
                    break;
                case -1123215065:
                    if (!str.equals(AS_INTERFACE)) {
                        b = -1;
                    }
                    break;
                case -594356707:
                    b = !str.equals(GET_PURCHASE_HISTORY) ? (byte) -1 : (byte) 4;
                    break;
                case -573310373:
                    b = !str.equals(GET_SKU_DETAILS) ? (byte) -1 : (byte) 1;
                    break;
                default:
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                    clsArr = new Class[]{IBinder.class};
                    break;
                case 1:
                    clsArr = new Class[]{Integer.TYPE, String.class, String.class, Bundle.class};
                    break;
                case 2:
                    clsArr = new Class[]{Integer.TYPE, String.class, String.class};
                    break;
                case 3:
                    clsArr = new Class[]{Integer.TYPE, String.class, String.class, String.class};
                    break;
                case 4:
                    clsArr = new Class[]{Integer.TYPE, String.class, String.class, String.class, Bundle.class};
                    break;
            }
            declaredMethod = cls.getDeclaredMethod(str, clsArr);
            methodMap.put(str, declaredMethod);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, cls.getName() + "." + str + " method not found", e);
        }
        return declaredMethod;
    }

    private static ArrayList<String> getPurchaseHistory(Context context, Object obj, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (isBillingSupported(context, obj, str).booleanValue()) {
            String string = null;
            int i = 0;
            Boolean bool = false;
            do {
                Object[] objArr = {6, PACKAGE_NAME, str, string, new Bundle()};
                string = null;
                Object objInvokeMethod = invokeMethod(context, IN_APP_BILLING_SERVICE, GET_PURCHASE_HISTORY, obj, objArr);
                if (objInvokeMethod != null) {
                    long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                    Bundle bundle = (Bundle) objInvokeMethod;
                    if (bundle.getInt("RESPONSE_CODE") == 0) {
                        for (String str2 : bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST")) {
                            try {
                                if (jCurrentTimeMillis - (new JSONObject(str2).getLong("purchaseTime") / 1000) > 1200) {
                                    bool = true;
                                    break;
                                }
                                arrayList.add(str2);
                                i++;
                            } catch (JSONException e) {
                                Log.e(TAG, "parsing purchase failure: ", e);
                            }
                        }
                        string = bundle.getString("INAPP_CONTINUATION_TOKEN");
                    }
                }
                if (i >= 30 || string == null) {
                    break;
                }
            } while (!bool.booleanValue());
        }
        return arrayList;
    }

    public static ArrayList<String> getPurchaseHistoryInapp(Context context, Object obj) {
        Class<?> cls;
        ArrayList<String> arrayList = new ArrayList<>();
        if (obj != null && (cls = getClass(context, IN_APP_BILLING_SERVICE)) != null && getMethod(cls, GET_PURCHASE_HISTORY) != null) {
            return filterPurchasesInapp(getPurchaseHistory(context, obj, "inapp"));
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0055 A[PHI: r1 r5
      0x0055: PHI (r1v3 java.lang.String) = (r1v2 java.lang.String), (r1v2 java.lang.String), (r1v4 java.lang.String) binds: [B:9:0x0032, B:11:0x003d, B:14:0x0047] A[DONT_GENERATE, DONT_INLINE]
      0x0055: PHI (r5v2 int) = (r5v1 int), (r5v1 int), (r5v3 int) binds: [B:9:0x0032, B:11:0x003d, B:14:0x0047] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:17:0x0059 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:19:0x0008 A[EDGE_INSN: B:19:0x0008->B:4:0x0008 BREAK  A[LOOP:0: B:8:0x0015->B:22:?], SYNTHETIC] */
    private static ArrayList<String> getPurchases(Context context, Object obj, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (obj != null && isBillingSupported(context, obj, str).booleanValue()) {
            String string = null;
            int size = 0;
            do {
                Object objInvokeMethod = invokeMethod(context, IN_APP_BILLING_SERVICE, GET_PURCHASES, obj, new Object[]{3, PACKAGE_NAME, str, string});
                string = null;
                if (objInvokeMethod == null) {
                    if (size >= 30) {
                        break;
                        break;
                    }
                } else {
                    Bundle bundle = (Bundle) objInvokeMethod;
                    if (bundle.getInt("RESPONSE_CODE") == 0) {
                        ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                        if (stringArrayList == null) {
                            break;
                        }
                        size += stringArrayList.size();
                        arrayList.addAll(stringArrayList);
                        string = bundle.getString("INAPP_CONTINUATION_TOKEN");
                        if (size >= 30) {
                            break;
                            break;
                        }
                    } else if (size >= 30) {
                        break;
                    }
                }
            } while (string != null);
        }
        return arrayList;
    }

    public static ArrayList<String> getPurchasesInapp(Context context, Object obj) {
        return filterPurchasesInapp(getPurchases(context, obj, "inapp"));
    }

    public static Map<String, SubscriptionType> getPurchasesSubs(Context context, Object obj) {
        HashMap map = new HashMap();
        for (String str : getPurchases(context, obj, "subs")) {
            SubscriptionType subsType = getSubsType(str);
            if (subsType != SubscriptionType.DUPLICATED && subsType != SubscriptionType.UNKNOWN) {
                map.put(str, subsType);
            }
        }
        return map;
    }

    public static ArrayList<String> getPurchasesSubsExpire(Context context, Object obj) {
        ArrayList<String> arrayList = new ArrayList<>();
        Map<String, ?> all = purchaseSubsSharedPrefs.getAll();
        if (!all.isEmpty()) {
            ArrayList<String> purchases = getPurchases(context, obj, "subs");
            HashSet hashSet = new HashSet();
            Iterator<String> it = purchases.iterator();
            while (it.hasNext()) {
                try {
                    hashSet.add(new JSONObject(it.next()).getString("productId"));
                } catch (JSONException e) {
                    Log.e(TAG, "Error parsing purchase json", e);
                }
            }
            HashSet<String> hashSet2 = new HashSet();
            Iterator<Map.Entry<String, ?>> it2 = all.entrySet().iterator();
            while (it2.hasNext()) {
                String key = it2.next().getKey();
                if (!hashSet.contains(key)) {
                    hashSet2.add(key);
                }
            }
            SharedPreferences.Editor editorEdit = purchaseSubsSharedPrefs.edit();
            for (String str : hashSet2) {
                String string = purchaseSubsSharedPrefs.getString(str, "");
                editorEdit.remove(str);
                if (!string.isEmpty()) {
                    arrayList.add(purchaseSubsSharedPrefs.getString(str, ""));
                }
            }
            editorEdit.apply();
        }
        return arrayList;
    }

    public static Map<String, String> getSkuDetails(Context context, ArrayList<String> arrayList, Object obj, boolean z) {
        Map<String, String> skuDetailsFromCache = readSkuDetailsFromCache(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (String str : arrayList) {
            if (!skuDetailsFromCache.containsKey(str)) {
                arrayList2.add(str);
            }
        }
        skuDetailsFromCache.putAll(getSkuDetailsFromGoogle(context, arrayList2, obj, z));
        return skuDetailsFromCache;
    }

    private static Map<String, String> getSkuDetailsFromGoogle(Context context, ArrayList<String> arrayList, Object obj, boolean z) {
        HashMap map = new HashMap();
        if (obj != null && !arrayList.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList);
            Object[] objArr = new Object[4];
            objArr[0] = 3;
            objArr[1] = PACKAGE_NAME;
            objArr[2] = z ? "subs" : "inapp";
            objArr[3] = bundle;
            Object objInvokeMethod = invokeMethod(context, IN_APP_BILLING_SERVICE, GET_SKU_DETAILS, obj, objArr);
            if (objInvokeMethod != null) {
                Bundle bundle2 = (Bundle) objInvokeMethod;
                if (bundle2.getInt("RESPONSE_CODE") == 0) {
                    ArrayList<String> stringArrayList = bundle2.getStringArrayList("DETAILS_LIST");
                    if (stringArrayList != null && arrayList.size() == stringArrayList.size()) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            map.put(arrayList.get(i), stringArrayList.get(i));
                        }
                    }
                    writeSkuDetailsToCache(map);
                }
            }
        }
        return map;
    }

    private static SubscriptionType getSubsType(String str) {
        SubscriptionType subscriptionType = null;
        try {
            long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("productId");
            String string2 = purchaseSubsSharedPrefs.getString(string, "");
            JSONObject jSONObject2 = string2.isEmpty() ? new JSONObject() : new JSONObject(string2);
            if (!jSONObject2.optString("purchaseToken").equals(jSONObject.get("purchaseToken"))) {
                subscriptionType = jCurrentTimeMillis - (jSONObject.getLong("purchaseTime") / 1000) < 43200 ? SubscriptionType.NEW : SubscriptionType.HEARTBEAT;
            }
            if (subscriptionType == null && !string2.isEmpty()) {
                boolean z = jSONObject2.getBoolean("autoRenewing");
                boolean z2 = jSONObject.getBoolean("autoRenewing");
                if (!z2 && z) {
                    subscriptionType = SubscriptionType.CANCEL;
                } else if (!z && z2) {
                    subscriptionType = SubscriptionType.RESTORE;
                }
            }
            if (subscriptionType == null && !string2.isEmpty()) {
                subscriptionType = jCurrentTimeMillis - jSONObject2.getLong(LAST_LOGGED_TIME_SEC) > SUBSCRIPTION_HARTBEAT_INTERVAL ? SubscriptionType.HEARTBEAT : SubscriptionType.DUPLICATED;
            }
            if (subscriptionType == SubscriptionType.DUPLICATED) {
                return subscriptionType;
            }
            jSONObject.put(LAST_LOGGED_TIME_SEC, jCurrentTimeMillis);
            purchaseSubsSharedPrefs.edit().putString(string, jSONObject.toString()).apply();
            return subscriptionType;
        } catch (JSONException e) {
            Log.e(TAG, "parsing purchase failure: ", e);
            return SubscriptionType.UNKNOWN;
        }
    }

    @Nullable
    private static Object invokeMethod(Context context, String str, String str2, Object obj, Object[] objArr) {
        Method method;
        Class<?> cls = getClass(context, str);
        if (cls == null || (method = getMethod(cls, str2)) == null) {
            return null;
        }
        if (obj != null) {
            obj = cls.cast(obj);
        }
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Illegal access to method " + cls.getName() + "." + method.getName(), e);
            return null;
        } catch (InvocationTargetException e2) {
            Log.e(TAG, "Invocation target exception in " + cls.getName() + "." + method.getName(), e2);
            return null;
        }
    }

    private static Boolean isBillingSupported(Context context, Object obj, String str) {
        if (obj == null) {
            return false;
        }
        Object objInvokeMethod = invokeMethod(context, IN_APP_BILLING_SERVICE, IS_BILLING_SUPPORTED, obj, new Object[]{3, PACKAGE_NAME, str});
        return Boolean.valueOf(objInvokeMethod != null && ((Integer) objInvokeMethod).intValue() == 0);
    }

    private static Map<String, String> readSkuDetailsFromCache(ArrayList<String> arrayList) {
        HashMap map = new HashMap();
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        for (String str : arrayList) {
            String string = skuDetailSharedPrefs.getString(str, null);
            if (string != null) {
                String[] strArrSplit = string.split(";", 2);
                if (jCurrentTimeMillis - Long.parseLong(strArrSplit[0]) < 43200) {
                    map.put(str, strArrSplit[1]);
                }
            }
        }
        return map;
    }

    private static void writeSkuDetailsToCache(Map<String, String> map) {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        SharedPreferences.Editor editorEdit = skuDetailSharedPrefs.edit();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            editorEdit.putString(entry.getKey(), jCurrentTimeMillis + ";" + entry.getValue());
        }
        editorEdit.apply();
    }
}
