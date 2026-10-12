package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.tapjoy.internal.al;
import com.tapjoy.internal.bh;
import com.tapjoy.internal.bn;
import com.tapjoy.internal.eu;
import com.tapjoy.internal.fa;
import com.tapjoy.internal.fb;
import com.tapjoy.internal.fh;
import com.tapjoy.internal.fo;
import com.tapjoy.internal.fs;
import com.tapjoy.internal.ga;
import com.tapjoy.internal.ge;
import com.tapjoy.internal.gz;
import com.tapjoy.internal.hc;
import com.tapjoy.internal.hn;
import com.tapjoy.internal.jq;
import com.tapjoy.internal.jz;
import com.tapjoy.internal.v;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import org.w3c.dom.Document;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TapjoyConnectCore {
    public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0f;
    private static Integer aA;
    private static Long aB;
    private static Long aC;
    private static Long aD;
    private static String aE;
    private static Integer aF;
    private static Double aG;
    private static Double aH;
    private static Long aI;
    private static Integer aJ;
    private static Integer aK;
    private static Integer aL;
    private static String aM;
    private static String aN;
    private static String aO;
    private static boolean ac;
    private static PackageManager ad;
    private static TapjoyGpsHelper ae;
    private static String ah;
    private static String ai;
    private static String aj;
    private static String ak;
    private static Integer al;
    private static String am;
    private static String an;
    private static Long ao;
    private static String ap;
    private static Integer aq;
    private static Integer ar;
    private static String as;
    private static String at;
    private static String au;
    private static String av;
    private static String aw;
    private static Set ax;
    private static Integer ay;
    private static Integer az;
    protected static boolean d;
    private long Y = 0;
    private boolean Z = false;
    private boolean ab = false;
    private static Context g = null;
    private static String h = null;
    private static TapjoyConnectCore i = null;
    private static TapjoyURLConnection j = null;
    private static TJConnectListener k = null;
    private static TJSetUserIDListener l = null;
    private static Vector m = new Vector(Arrays.asList(TapjoyConstants.dependencyClassNames));
    private static String n = "";
    private static String o = "";
    private static String p = "";
    private static String q = "";
    private static String r = "";
    private static String s = "";
    private static String t = "";
    private static String u = "";
    private static String v = "";
    private static String w = "";
    private static String x = "";
    private static String y = "";
    private static int z = 1;
    private static float A = 1.0f;
    private static int B = 1;
    private static String C = "";
    private static String D = "";
    private static String E = "";
    private static String F = "";
    private static String G = "";
    private static String H = "";
    private static String I = "";
    private static String J = "";
    private static String K = "";
    private static String L = "";
    private static String M = "";
    private static String N = "";
    private static String O = "native";
    private static String P = "";
    private static String Q = "";
    private static float R = 1.0f;
    private static boolean S = false;
    private static String T = "";
    private static String U = "";
    private static String V = "";
    private static String W = "";
    private static String X = null;
    private static long aa = 0;
    protected static int a = 0;
    protected static int b = 0;
    protected static String c = "";
    protected static String e = "";
    protected static String f = "";
    private static Hashtable af = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
    private static Map ag = new ConcurrentHashMap();
    private static String aP = "";
    private static String aQ = "";
    private static String aR = "";
    private static boolean aS = false;
    private static TJConnectListener aT = null;
    private static boolean aU = false;

    public class PPAThread implements Runnable {
        private Map b;

        public PPAThread(Map map) {
            this.b = map;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            TapjoyHttpURLResponse responseFromURL = TapjoyConnectCore.j.getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_CONNECT_URL_PATH, (Map) null, (Map) null, this.b);
            if (responseFromURL.response != null) {
                TapjoyConnectCore.d(responseFromURL.response);
            }
        }
    }

    private static String a(long j2) {
        try {
            return TapjoyUtil.SHA256(v + ":" + q() + ":" + j2 + ":" + M);
        } catch (Exception e2) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error in computing verifier value -- " + e2.toString()));
            return "";
        }
    }

    static /* synthetic */ void a() {
        if (aU) {
            return;
        }
        try {
            ae.loadAdvertisingId(!n());
            if (ae.isGooglePlayServicesAvailable() && ae.isGooglePlayManifestConfigured()) {
                b = ae.getDeviceGooglePlayServicesVersion();
                a = ae.getPackagedGooglePlayServicesVersion();
            }
            if (ae.isAdIdAvailable()) {
                setAdTrackingEnabled();
                c = ae.getAdvertisingId();
                gz gzVarA = gz.a();
                String str = c;
                boolean z2 = d ? false : true;
                hc hcVar = gzVarA.f;
                String strA = hcVar.c.A.a();
                hcVar.b.q = str;
                hcVar.b.r = Boolean.valueOf(z2);
                hcVar.c.A.a(str);
                hcVar.c.B.a(z2);
                hn.a(str, z2);
                if (!jq.c(strA) && !str.equals(strA)) {
                    hcVar.c.a(false);
                }
            }
        } catch (Exception e2) {
            TapjoyLog.i("TapjoyConnect", "Error fetching advertising id: " + e2.toString());
            e2.printStackTrace();
        }
        aU = true;
    }

    private static void a(String str, String str2) {
        if ((str.equals(TapjoyConnectFlag.SERVICE_URL) || str.equals(TapjoyConnectFlag.PLACEMENT_URL)) && !str2.endsWith("/")) {
            str2 = str2 + "/";
        }
        af.put(str, str2);
    }

    private static void a(Properties properties) {
        Enumeration enumerationKeys = properties.keys();
        while (enumerationKeys.hasMoreElements()) {
            try {
                String str = (String) enumerationKeys.nextElement();
                a(str, (String) properties.get(str));
            } catch (ClassCastException e2) {
                TapjoyLog.e("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
            }
        }
    }

    static /* synthetic */ void a(boolean z2) {
        if (z2) {
            TapjoyLog.i("TapjoyConnect", "Set userID is successful");
            if (l != null) {
                l.onSetUserIDSuccess();
                return;
            }
            return;
        }
        TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Failed to set userID"));
        if (l != null) {
            l.onSetUserIDFailure("Failed to set userID");
        }
    }

    /* JADX WARN: Code duplicated, block: B:90:0x0306  */
    private static boolean a(Context context) throws TapjoyException {
        WifiInfo connectionInfo;
        boolean z2;
        g = context;
        ad = context.getPackageManager();
        ge.a().a(context);
        ga.a().a(context);
        ae = new TapjoyGpsHelper(g);
        if (j == null) {
            j = new TapjoyURLConnection();
        }
        if (af == null) {
            af = new Hashtable();
        }
        j();
        int identifier = g.getResources().getIdentifier("raw/tapjoy_config", null, g.getPackageName());
        Properties properties = new Properties();
        try {
            properties.load(g.getResources().openRawResource(identifier));
            a(properties);
        } catch (Exception e2) {
        }
        if (jq.c(getConnectFlagValue("unit_test_mode"))) {
            k();
        }
        String string = Settings.Secure.getString(g.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        n = string;
        if (string != null) {
            n = n.toLowerCase();
        }
        try {
            w = ad.getPackageInfo(g.getPackageName(), 0).versionName;
            t = "android";
            E = "android";
            r = Build.MODEL;
            s = Build.MANUFACTURER;
            u = Build.VERSION.RELEASE;
            x = "12.4.2";
            y = TapjoyConstants.TJC_BRIDGE_VERSION_NUMBER;
            try {
                if (Build.VERSION.SDK_INT > 3) {
                    TapjoyDisplayMetricsUtil tapjoyDisplayMetricsUtil = new TapjoyDisplayMetricsUtil(g);
                    z = tapjoyDisplayMetricsUtil.getScreenDensityDPI();
                    A = tapjoyDisplayMetricsUtil.getScreenDensityScale();
                    B = tapjoyDisplayMetricsUtil.getScreenLayoutSize();
                }
            } catch (Exception e3) {
                TapjoyLog.e("TapjoyConnect", "Error getting screen density/dimensions/layout: " + e3.toString());
            }
            if (e("android.permission.ACCESS_WIFI_STATE")) {
                try {
                    WifiManager wifiManager = (WifiManager) g.getSystemService(TapjoyConstants.TJC_CONNECTION_TYPE_WIFI);
                    if (wifiManager != null && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                        String macAddress = connectionInfo.getMacAddress();
                        p = macAddress;
                        if (macAddress != null) {
                            p = p.replace(":", "").toLowerCase();
                        }
                    }
                } catch (Exception e4) {
                    TapjoyLog.e("TapjoyConnect", "Error getting device mac address: " + e4.toString());
                }
            } else {
                TapjoyLog.d("TapjoyConnect", "*** ignore macAddress");
            }
            TelephonyManager telephonyManager = (TelephonyManager) g.getSystemService("phone");
            if (telephonyManager != null) {
                F = telephonyManager.getNetworkOperatorName();
                G = telephonyManager.getNetworkCountryIso();
                String networkOperator = telephonyManager.getNetworkOperator();
                if (networkOperator != null && (networkOperator.length() == 5 || networkOperator.length() == 6)) {
                    H = networkOperator.substring(0, 3);
                    I = networkOperator.substring(3);
                }
            }
            SharedPreferences sharedPreferences = g.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
            String string2 = sharedPreferences.getString(TapjoyConstants.PREF_INSTALL_ID, "");
            q = string2;
            if (string2 == null || q.length() == 0) {
                try {
                    q = TapjoyUtil.SHA256(UUID.randomUUID().toString() + System.currentTimeMillis());
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putString(TapjoyConstants.PREF_INSTALL_ID, q);
                    editorEdit.apply();
                } catch (Exception e5) {
                    TapjoyLog.e("TapjoyConnect", "Error generating install id: " + e5.toString());
                }
            }
            if (getConnectFlagValue(TapjoyConnectFlag.STORE_NAME) != null && getConnectFlagValue(TapjoyConnectFlag.STORE_NAME).length() > 0) {
                L = getConnectFlagValue(TapjoyConnectFlag.STORE_NAME);
                if (!new ArrayList(Arrays.asList(TapjoyConnectFlag.STORE_ARRAY)).contains(L)) {
                    TapjoyLog.w("TapjoyConnect", "Warning -- undefined STORE_NAME: " + L);
                }
            }
            try {
                String str = L;
                Intent intent = new Intent("android.intent.action.VIEW");
                if (str.length() <= 0) {
                    intent.setData(Uri.parse("market://details"));
                    if (ad.queryIntentActivities(intent, 0).size() > 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                } else {
                    z2 = false;
                }
                S = z2;
            } catch (Exception e6) {
                TapjoyLog.e("TapjoyConnect", "Error trying to detect store intent on devicee: " + e6.toString());
            }
            h();
            if (getConnectFlagValue(TapjoyConnectFlag.ALLOW_LEGACY_ID_FALLBACK) != null && getConnectFlagValue(TapjoyConnectFlag.ALLOW_LEGACY_ID_FALLBACK).length() > 0) {
                f = getConnectFlagValue(TapjoyConnectFlag.ALLOW_LEGACY_ID_FALLBACK);
            }
            if (getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK) != null && getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK).length() > 0) {
                e = getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK);
            }
            if (getConnectFlagValue(TapjoyConnectFlag.USER_ID) != null && getConnectFlagValue(TapjoyConnectFlag.USER_ID).length() > 0) {
                TapjoyLog.i("TapjoyConnect", "Setting userID to: " + getConnectFlagValue(TapjoyConnectFlag.USER_ID));
                setUserID(getConnectFlagValue(TapjoyConnectFlag.USER_ID), null);
            }
            Q = TapjoyUtil.getRedirectDomain(getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL));
            if (af != null) {
                i();
            }
            return true;
        } catch (PackageManager.NameNotFoundException e7) {
            throw new TapjoyException(e7.getMessage());
        }
    }

    static /* synthetic */ boolean a(String str) {
        String nodeTrimValue;
        Document documentBuildDocument = TapjoyUtil.buildDocument(str);
        return documentBuildDocument == null || ((nodeTrimValue = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("Success"))) != null && nodeTrimValue.equals("true"));
    }

    private static boolean a(String str, boolean z2) throws Throwable {
        bn bnVar;
        bn bnVarB;
        long jLongValue;
        bn bnVar2 = null;
        try {
            try {
                bnVarB = bn.b(str);
                try {
                    Map mapD = bnVarB.d();
                    String strA = jq.a((String) mapD.get(TapjoyConstants.TJC_APP_GROUP_ID));
                    String strA2 = jq.a((String) mapD.get(TapjoyConstants.TJC_STORE));
                    String strA3 = jq.a((String) mapD.get(TapjoyConstants.TJC_ANALYTICS_API_KEY));
                    String strA4 = jq.a((String) mapD.get(TapjoyConstants.TJC_MANAGED_DEVICE_ID));
                    Object obj = mapD.get("cache_max_age");
                    fo foVar = new fo(strA3);
                    if (foVar.a != fo.a.RPC_ANALYTICS) {
                        throw new IOException("Invalid analytics_api_key");
                    }
                    String strA5 = fo.a(foVar.b);
                    String str2 = foVar.c;
                    String str3 = strA == null ? strA5 : strA;
                    gz.a().a(g, strA3, "12.4.2", TapjoyConfig.TJC_ANALYTICS_SERVICE_URL, strA5, str2);
                    T = str3;
                    U = strA2;
                    V = strA3;
                    W = strA4;
                    bnVarB.close();
                    if (!z2) {
                        try {
                            if (obj instanceof String) {
                                try {
                                    jLongValue = Long.parseLong(((String) obj).trim());
                                } catch (NumberFormatException e2) {
                                    jLongValue = 0;
                                }
                            } else if (obj instanceof Number) {
                                try {
                                    jLongValue = ((Number) obj).longValue();
                                } catch (NumberFormatException e3) {
                                    jLongValue = 0;
                                }
                            } else {
                                jLongValue = 0;
                            }
                            if (jLongValue <= 0) {
                                TapjoyAppSettings.getInstance().removeConnectResult();
                            } else {
                                TapjoyAppSettings.getInstance().saveConnectResultAndParams(str, r(), (jLongValue * 1000) + v.b());
                            }
                            ga gaVarA = ga.a();
                            Object obj2 = mapD.get(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS);
                            if (obj2 instanceof Map) {
                                try {
                                    gaVarA.a.a((Map) obj2);
                                    gaVarA.c().edit().putString(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS, bh.a(obj2)).apply();
                                } catch (Exception e4) {
                                }
                            } else if (obj2 == null) {
                                gaVarA.a.a((Map) null);
                                gaVarA.c().edit().remove(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS).apply();
                            }
                        } catch (IOException e5) {
                            e = e5;
                            bnVar2 = null;
                            try {
                                TapjoyLog.v("TapjoyConnect", e.getMessage());
                                jz.a(bnVar2);
                                return false;
                            } catch (Throwable th) {
                                th = th;
                                bnVar = bnVar2;
                                jz.a(bnVar);
                                throw th;
                            }
                        } catch (RuntimeException e6) {
                            e = e6;
                            bnVarB = null;
                            TapjoyLog.v("TapjoyConnect", e.getMessage());
                            jz.a(bnVarB);
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            bnVar = null;
                            jz.a(bnVar);
                            throw th;
                        }
                    }
                    jz.a(null);
                    return true;
                } catch (IOException e7) {
                    e = e7;
                    bnVar2 = bnVarB;
                } catch (RuntimeException e8) {
                    e = e8;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e9) {
            e = e9;
        } catch (RuntimeException e10) {
            e = e10;
            bnVarB = null;
        } catch (Throwable th4) {
            th = th4;
            bnVar = null;
        }
    }

    private static String b(long j2) {
        try {
            return TapjoyUtil.SHA256(aP + ":" + q() + ":" + j2 + ":" + aQ);
        } catch (Exception e2) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error in computing verifier value -- " + e2.toString()));
            return "";
        }
    }

    private static boolean c(String str) throws Throwable {
        bn bnVarB;
        try {
            try {
                bnVarB = bn.b(str);
                try {
                    Map mapD = bnVarB.d();
                    String strA = jq.a((String) mapD.get(TapjoyConstants.TJC_APP_GROUP_ID));
                    String strA2 = jq.a((String) mapD.get(TapjoyConstants.TJC_STORE));
                    String strA3 = jq.a((String) mapD.get(TapjoyConstants.TJC_ANALYTICS_API_KEY));
                    String strA4 = jq.a((String) mapD.get(TapjoyConstants.TJC_MANAGED_DEVICE_ID));
                    fo foVar = new fo(strA3);
                    if (foVar.a != fo.a.RPC_ANALYTICS) {
                        throw new IOException("Invalid analytics_api_key");
                    }
                    String strA5 = fo.a(foVar.b);
                    if (strA != null) {
                        strA5 = strA;
                    }
                    aR = strA5;
                    U = strA2;
                    W = strA4;
                    bnVarB.close();
                    jz.a(null);
                    return true;
                } catch (IOException e2) {
                    e = e2;
                    TapjoyLog.v("TapjoyConnect", e.getMessage());
                    jz.a(bnVarB);
                    return false;
                } catch (RuntimeException e3) {
                    e = e3;
                    TapjoyLog.v("TapjoyConnect", e.getMessage());
                    jz.a(bnVarB);
                    return false;
                }
            } catch (Throwable th) {
                th = th;
                jz.a(null);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            bnVarB = null;
        } catch (RuntimeException e5) {
            e = e5;
            bnVarB = null;
        } catch (Throwable th2) {
            th = th2;
            jz.a(null);
            throw th;
        }
    }

    private static void d() {
        if (!jq.c(N)) {
            gz.a().a(g, h, "12.4.2", TapjoyConfig.TJC_ANALYTICS_SERVICE_URL, N, M);
        }
        if (k != null) {
            k.onConnectFailure();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(String str) throws Throwable {
        bn bnVarB;
        try {
            try {
                bnVarB = bn.b(str);
                try {
                    if (!bnVarB.a()) {
                        bnVarB.close();
                        jz.a(null);
                        TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Completed Pay-Per-Action call failed."));
                        return false;
                    }
                    bnVarB.s();
                    TapjoyLog.d("TapjoyConnect", "Successfully sent completed Pay-Per-Action to Tapjoy server.");
                    bnVarB.close();
                    jz.a(null);
                    return true;
                } catch (IOException e2) {
                    e = e2;
                    TapjoyLog.v("TapjoyConnect", e.getMessage());
                    jz.a(bnVarB);
                } catch (RuntimeException e3) {
                    e = e3;
                    TapjoyLog.v("TapjoyConnect", e.getMessage());
                    jz.a(bnVarB);
                }
            } catch (Throwable th) {
                th = th;
                jz.a(null);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            bnVarB = null;
        } catch (RuntimeException e5) {
            e = e5;
            bnVarB = null;
        } catch (Throwable th2) {
            th = th2;
            jz.a(null);
            throw th;
        }
    }

    private static void e() {
        if (aT != null) {
            aT.onConnectFailure();
        }
    }

    private static boolean e(String str) {
        return ad.checkPermission(str, g.getPackageName()) == 0;
    }

    private static Map f() {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        TapjoyUtil.safePut(map3, TapjoyConstants.TJC_PLUGIN, O, true);
        TapjoyUtil.safePut(map3, TapjoyConstants.TJC_SDK_TYPE, P, true);
        TapjoyUtil.safePut(map3, "app_id", v, true);
        TapjoyUtil.safePut(map3, TapjoyConstants.TJC_LIBRARY_VERSION, x, true);
        TapjoyUtil.safePut(map3, TapjoyConstants.TJC_LIBRARY_REVISION, TapjoyRevision.GIT_REVISION, true);
        TapjoyUtil.safePut(map3, TapjoyConstants.TJC_BRIDGE_VERSION, y, true);
        TapjoyUtil.safePut(map3, "app_version", w, true);
        map2.putAll(map3);
        HashMap map4 = new HashMap();
        TapjoyUtil.safePut(map4, "device_name", r, true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_PLATFORM, E, true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, u, true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_DEVICE_MANUFACTURER, s, true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_DEVICE_TYPE_NAME, t, true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_DEVICE_SCREEN_LAYOUT_SIZE, new StringBuilder().append(B).toString(), true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_STORE_NAME, L, true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_STORE_VIEW, String.valueOf(S), true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_CARRIER_NAME, F, true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_CARRIER_COUNTRY_CODE, G, true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_MOBILE_NETWORK_CODE, I, true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_MOBILE_COUNTRY_CODE, H, true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, Locale.getDefault().getCountry(), true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_DEVICE_LANGUAGE, Locale.getDefault().getLanguage(), true);
        J = getConnectionType();
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_CONNECTION_TYPE, J, true);
        K = getConnectionSubType();
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_CONNECTION_SUBTYPE, K, true);
        TapjoyUtil.safePut(map4, TapjoyConstants.TJC_DEVICE_SCREEN_DENSITY, new StringBuilder().append(z).toString(), true);
        map2.putAll(map4);
        HashMap map5 = new HashMap();
        if (m()) {
            TapjoyUtil.safePut(map5, TapjoyConstants.TJC_ADVERTISING_ID, c, true);
            TapjoyUtil.safePut(map5, TapjoyConstants.TJC_AD_TRACKING_ENABLED, String.valueOf(d), true);
        }
        if ((n() && !m()) || !o()) {
            TapjoyUtil.safePut(map5, TapjoyConstants.TJC_ANDROID_ID, n, true);
            TapjoyUtil.safePut(map5, TapjoyConstants.TJC_DEVICE_MAC_ADDRESS, p, true);
        }
        TapjoyUtil.safePut(map5, TapjoyConstants.TJC_INSTALL_ID, q, true);
        TapjoyUtil.safePut(map5, TapjoyConstants.TJC_USER_ID, C, true);
        TapjoyUtil.safePut(map5, TapjoyConstants.TJC_ADVERTISING_ID_CHECK_DISABLED, e, true);
        TapjoyUtil.safePut(map5, TapjoyConstants.TJC_LEGACY_ID_FALLBACK_ALLOWED, f, true);
        if (a != 0) {
            TapjoyUtil.safePut(map5, TapjoyConstants.TJC_PACKAGED_GOOGLE_PLAY_SERVICES_VERSION, Integer.toString(a), true);
        }
        if (b != 0) {
            TapjoyUtil.safePut(map5, TapjoyConstants.TJC_DEVICE_GOOGLE_PLAY_SERVICES_VERSION, Integer.toString(b), true);
        }
        if (o == null || o.length() == 0 || System.currentTimeMillis() - aa > TapjoyConstants.SESSION_ID_INACTIVITY_TIME) {
            o = p();
        } else {
            aa = System.currentTimeMillis();
        }
        TapjoyUtil.safePut(map5, TapjoyConstants.TJC_SESSION_ID, o, true);
        map2.putAll(map5);
        HashMap map6 = new HashMap();
        TapjoyUtil.safePut(map6, TapjoyConstants.TJC_APP_GROUP_ID, T, true);
        TapjoyUtil.safePut(map6, TapjoyConstants.TJC_STORE, U, true);
        TapjoyUtil.safePut(map6, TapjoyConstants.TJC_ANALYTICS_API_KEY, V, true);
        TapjoyUtil.safePut(map6, TapjoyConstants.TJC_MANAGED_DEVICE_ID, W, true);
        map2.putAll(map6);
        ge geVarA = ge.a();
        HashMap map7 = new HashMap();
        if (geVarA.a != null) {
            TapjoyUtil.safePut(map7, "gdpr", geVarA.a.booleanValue() ? "1" : "0", true);
        }
        if (!al.a(geVarA.b)) {
            TapjoyUtil.safePut(map7, "cgdpr", geVarA.b, true);
        }
        map2.putAll(map7);
        if (TapjoyCache.getInstance() != null && TapjoyCache.getInstance().getCachedOfferIDs() != null && TapjoyCache.getInstance().getCachedOfferIDs().length() > 0) {
            TapjoyUtil.safePut(map2, TapjoyConstants.TJC_CACHED_OFFERS, TapjoyCache.getInstance().getCachedOfferIDs(), true);
        }
        TapjoyUtil.safePut(map2, TapjoyConstants.TJC_CURRENCY_MULTIPLIER, Float.toString(R), true);
        map.putAll(map2);
        HashMap map8 = new HashMap();
        h();
        HashMap map9 = new HashMap();
        TapjoyUtil.safePut(map9, TapjoyConstants.TJC_ANALYTICS_ID, ah, true);
        TapjoyUtil.safePut(map9, TapjoyConstants.TJC_PACKAGE_ID, ai, true);
        TapjoyUtil.safePut(map9, TapjoyConstants.TJC_PACKAGE_SIGN, aj, true);
        TapjoyUtil.safePut(map9, TapjoyConstants.TJC_DEVICE_DISPLAY_DENSITY, aJ);
        TapjoyUtil.safePut(map9, TapjoyConstants.TJC_DEVICE_DISPLAY_WIDTH, aK);
        TapjoyUtil.safePut(map9, TapjoyConstants.TJC_DEVICE_DISPLAY_HEIGHT, aL);
        TapjoyUtil.safePut(map9, TapjoyConstants.TJC_DEVICE_COUNTRY_SIM, aM, true);
        TapjoyUtil.safePut(map9, TapjoyConstants.TJC_DEVICE_TIMEZONE, aN, true);
        map8.putAll(map9);
        HashMap map10 = new HashMap();
        TapjoyUtil.safePut(map10, TapjoyConstants.TJC_PACKAGE_VERSION, ak, true);
        TapjoyUtil.safePut(map10, TapjoyConstants.TJC_PACKAGE_REVISION, al);
        TapjoyUtil.safePut(map10, TapjoyConstants.TJC_PACKAGE_DATA_VERSION, am, true);
        TapjoyUtil.safePut(map10, TapjoyConstants.TJC_INSTALLER, an, true);
        if (jq.c(L)) {
            TapjoyUtil.safePut(map10, TapjoyConstants.TJC_STORE_NAME, aO, true);
        }
        map8.putAll(map10);
        map8.putAll(g());
        map.putAll(map8);
        return map;
    }

    private static Map g() {
        HashMap map = new HashMap();
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_INSTALLED, ao);
        TapjoyUtil.safePut(map, "referrer", ap, true);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_USER_LEVEL, aq);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_USER_FRIEND_COUNT, ar);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_USER_VARIABLE_1, as, true);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_USER_VARIABLE_2, at, true);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_USER_VARIABLE_3, au, true);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_USER_VARIABLE_4, av, true);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_USER_VARIABLE_5, aw, true);
        Iterator it = ax.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            TapjoyUtil.safePut(map, "user_tags[" + i2 + "]", (String) it.next(), true);
            i2++;
        }
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_USER_WEEKLY_FREQUENCY, ay);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_USER_MONTHLY_FREQUENCY, az);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_SESSION_TOTAL_COUNT, aA);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_SESSION_TOTAL_LENGTH, aB);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_SESSION_LAST_AT, aC);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_SESSION_LAST_LENGTH, aD);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_PURCHASE_CURRENCY, aE, true);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_PURCHASE_TOTAL_COUNT, aF);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_PURCHASE_TOTAL_PRICE, aG);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_PURCHASE_LAST_PRICE, aH);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_PURCHASE_LAST_AT, aI);
        return map;
    }

    public static String getAndroidID() {
        return n;
    }

    public static String getAppID() {
        return v;
    }

    public static String getAwardCurrencyVerifier(long j2, int i2, String str) {
        try {
            return TapjoyUtil.SHA256(v + ":" + q() + ":" + j2 + ":" + M + ":" + i2 + ":" + str);
        } catch (Exception e2) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error in computing awardCurrencyVerifier -- " + e2.toString()));
            return "";
        }
    }

    public static String getCarrierName() {
        return F;
    }

    public static String getConnectFlagValue(String str) {
        return (af == null || af.get(str) == null) ? "" : af.get(str).toString();
    }

    public static String getConnectURL() {
        return TapjoyConfig.TJC_CONNECT_SERVICE_URL;
    }

    public static String getConnectionSubType() {
        String subtypeName;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) g.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "";
            }
            subtypeName = connectivityManager.getActiveNetworkInfo().getSubtypeName();
            try {
                TapjoyLog.d("TapjoyConnect", "connection_sub_type: " + subtypeName);
                return subtypeName;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            subtypeName = "";
        }
        TapjoyLog.e("TapjoyConnect", "getConnectionSubType error: " + e.toString());
        return subtypeName;
    }

    public static String getConnectionType() {
        String str = "";
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) g.getSystemService("connectivity");
            if (connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null) {
                switch (connectivityManager.getActiveNetworkInfo().getType()) {
                    case 1:
                    case 6:
                        str = TapjoyConstants.TJC_CONNECTION_TYPE_WIFI;
                        break;
                    default:
                        str = TapjoyConstants.TJC_CONNECTION_TYPE_MOBILE;
                        break;
                }
                TapjoyLog.d("TapjoyConnect", "connectivity: " + connectivityManager.getActiveNetworkInfo().getType());
                TapjoyLog.d("TapjoyConnect", "connection_type: " + str);
            }
            return str;
        } catch (Exception e2) {
            String str2 = str;
            TapjoyLog.e("TapjoyConnect", "getConnectionType error: " + e2.toString());
            return str2;
        }
    }

    public static Context getContext() {
        return g;
    }

    public static String getCustomParameter() {
        return D;
    }

    public static float getDeviceScreenDensityScale() {
        return A;
    }

    public static Map getGenericURLParams() {
        Map mapF = f();
        TapjoyUtil.safePut(mapF, "app_id", v, true);
        return mapF;
    }

    public static String getHostURL() {
        return getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL);
    }

    public static TapjoyConnectCore getInstance() {
        return i;
    }

    public static String getLimitedAppID() {
        return aP;
    }

    public static Map getLimitedGenericURLParams() {
        Map mapF = f();
        TapjoyUtil.safePut(mapF, "app_id", aP, true);
        TapjoyUtil.safePut(mapF, TapjoyConstants.TJC_APP_GROUP_ID, aR, true);
        TapjoyUtil.safePut(mapF, TapjoyConstants.TJC_LIMITED, "true", true);
        return mapF;
    }

    public static Map getLimitedTimeStampAndVerifierParams() {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        String strB = b(jCurrentTimeMillis);
        HashMap map = new HashMap();
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_TIMESTAMP, String.valueOf(jCurrentTimeMillis), true);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_VERIFIER, strB, true);
        return map;
    }

    public static Map getLimitedURLParams() {
        Map limitedGenericURLParams = getLimitedGenericURLParams();
        limitedGenericURLParams.putAll(getLimitedTimeStampAndVerifierParams());
        return limitedGenericURLParams;
    }

    public static String getMacAddress() {
        return p;
    }

    public static String getPlacementURL() {
        return getConnectFlagValue(TapjoyConnectFlag.PLACEMENT_URL);
    }

    public static String getRedirectDomain() {
        return Q;
    }

    public static String getSecretKey() {
        return M;
    }

    public static String getSha1MacAddress() {
        try {
            return TapjoyUtil.SHA1(p);
        } catch (Exception e2) {
            TapjoyLog.e("TapjoyConnect", "Error generating sha1 of macAddress: " + e2.toString());
            return null;
        }
    }

    public static String getSupportURL(String str) {
        if (str == null) {
            str = v;
        }
        return getHostURL() + "support_requests/new?currency_id=" + str + "&app_id=" + v + "&udid=" + W + "&language_code=" + Locale.getDefault().getLanguage();
    }

    public static Map getTimeStampAndVerifierParams() {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        String strA = a(jCurrentTimeMillis);
        HashMap map = new HashMap();
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_TIMESTAMP, String.valueOf(jCurrentTimeMillis), true);
        TapjoyUtil.safePut(map, TapjoyConstants.TJC_VERIFIER, strA, true);
        return map;
    }

    public static Map getURLParams() {
        Map genericURLParams = getGenericURLParams();
        genericURLParams.putAll(getTimeStampAndVerifierParams());
        return genericURLParams;
    }

    public static String getUserID() {
        return C;
    }

    public static String getUserToken() {
        return !jq.c(C) ? W + ":" + C : W;
    }

    private static void h() {
        fb fbVarA = gz.a(g).a(true);
        fa faVar = fbVarA.d;
        ah = faVar.h;
        ai = faVar.r;
        aj = faVar.s;
        aJ = faVar.m;
        aK = faVar.n;
        aL = faVar.o;
        aM = faVar.u;
        aN = faVar.q;
        eu euVar = fbVarA.e;
        ak = euVar.e;
        al = euVar.f;
        am = euVar.g;
        an = euVar.h;
        aO = euVar.i;
        fh fhVar = fbVarA.f;
        ao = fhVar.s;
        ap = fhVar.t;
        aq = fhVar.J;
        ar = fhVar.K;
        as = fhVar.L;
        at = fhVar.M;
        au = fhVar.N;
        av = fhVar.O;
        aw = fhVar.P;
        ax = new HashSet(fhVar.Q);
        ay = fhVar.u;
        az = fhVar.v;
        aA = fhVar.x;
        aB = fhVar.y;
        aC = fhVar.z;
        aD = fhVar.A;
        aE = fhVar.B;
        aF = fhVar.C;
        aG = fhVar.D;
        aH = fhVar.F;
        aI = fhVar.E;
    }

    private static void i() {
        TapjoyLog.i("TapjoyConnect", "Connect Flags:");
        TapjoyLog.i("TapjoyConnect", "--------------------");
        for (Map.Entry entry : af.entrySet()) {
            TapjoyLog.i("TapjoyConnect", "key: " + ((String) entry.getKey()) + ", value: " + Uri.encode(entry.getValue().toString()));
        }
        TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL) + "]");
        TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + Q + "]");
        TapjoyLog.i("TapjoyConnect", "--------------------");
    }

    public static boolean isConnected() {
        return ac;
    }

    public static boolean isFullScreenViewOpen() {
        Iterator it = ag.values().iterator();
        while (it.hasNext()) {
            switch (((Integer) it.next()).intValue()) {
                case 1:
                case 2:
                    return true;
                default:
                    break;
            }
        }
        return false;
    }

    public static boolean isLimitedConnected() {
        return aS;
    }

    public static boolean isUnitTestMode() {
        return getConnectFlagValue("unit_test_mode") == "true";
    }

    public static boolean isViewOpen() {
        TapjoyLog.d("TapjoyConnect", "isViewOpen: " + ag.size());
        return !ag.isEmpty();
    }

    private static void j() {
        try {
            if (ad != null) {
                ApplicationInfo applicationInfo = ad.getApplicationInfo(g.getPackageName(), 128);
                if (applicationInfo == null || applicationInfo.metaData == null) {
                    TapjoyLog.d("TapjoyConnect", "No metadata present.");
                    return;
                }
                for (String str : TapjoyConnectFlag.FLAG_ARRAY) {
                    String string = applicationInfo.metaData.getString("tapjoy." + str);
                    if (string != null) {
                        TapjoyLog.d("TapjoyConnect", "Found manifest flag: " + str + ", " + string);
                        a(str, string);
                    }
                }
                TapjoyLog.d("TapjoyConnect", "Metadata successfully loaded");
            }
        } catch (Exception e2) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error reading manifest meta-data -- " + e2.toString()));
        }
    }

    private static void k() throws TapjoyIntegrationException {
        try {
            List<ActivityInfo> listAsList = Arrays.asList(ad.getPackageInfo(g.getPackageName(), 1).activities);
            if (listAsList != null) {
                for (ActivityInfo activityInfo : listAsList) {
                    if (m.contains(activityInfo.name)) {
                        int iIndexOf = m.indexOf(activityInfo.name);
                        try {
                            Class.forName((String) m.get(iIndexOf));
                            Vector vector = new Vector();
                            if ((activityInfo.configChanges & 128) != 128) {
                                vector.add(TJAdUnitConstants.String.ORIENTATION);
                            }
                            if ((activityInfo.configChanges & 32) != 32) {
                                vector.add("keyboardHidden");
                            }
                            if (vector.size() != 0) {
                                if (vector.size() != 1) {
                                    throw new TapjoyIntegrationException(vector.toString() + " properties are not specified in manifest configChanges for " + ((String) m.get(iIndexOf)));
                                }
                                throw new TapjoyIntegrationException(vector.toString() + " property is not specified in manifest configChanges for " + ((String) m.get(iIndexOf)));
                            }
                            if (Build.VERSION.SDK_INT >= 13 && (activityInfo.configChanges & 1024) != 1024) {
                                TapjoyLog.w("TapjoyConnect", "WARNING -- screenSize property is not specified in manifest configChanges for " + ((String) m.get(iIndexOf)));
                            }
                            if (Build.VERSION.SDK_INT >= 11 && activityInfo.name.equals("com.tapjoy.TJAdUnitActivity") && (activityInfo.flags & 512) != 512) {
                                throw new TapjoyIntegrationException("'hardwareAccelerated' property not specified in manifest for " + ((String) m.get(iIndexOf)));
                            }
                            m.remove(iIndexOf);
                        } catch (ClassNotFoundException e2) {
                            throw new TapjoyIntegrationException("[ClassNotFoundException] Could not find dependency class " + ((String) m.get(iIndexOf)));
                        }
                    }
                }
            }
            if (m.size() != 0) {
                if (m.size() != 1) {
                    throw new TapjoyIntegrationException("Missing " + m.size() + " dependency classes in manifest: " + m.toString());
                }
                throw new TapjoyIntegrationException("Missing " + m.size() + " dependency class in manifest: " + m.toString());
            }
            l();
            try {
                try {
                    Class.forName("com.tapjoy.TJAdUnitJSBridge").getMethod(TJAdUnitConstants.String.CLOSE_REQUESTED, Boolean.class);
                    if (getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK) == null || !getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK).equals("true")) {
                        ae.checkGooglePlayIntegration();
                    } else {
                        TapjoyLog.i("TapjoyConnect", "Skipping integration check for Google Play Services and Advertising ID. Do this only if you do not have access to Google Play Services.");
                    }
                } catch (NoSuchMethodException e3) {
                    throw new TapjoyIntegrationException("Try configuring Proguard or other code obfuscators to ignore com.tapjoy classes. Visit http://dev.tapjoy.comfor more information.");
                }
            } catch (ClassNotFoundException e4) {
                throw new TapjoyIntegrationException("ClassNotFoundException: com.tapjoy.TJAdUnitJSBridge was not found.");
            }
        } catch (Exception e5) {
            throw new TapjoyIntegrationException("Error while getting package info.");
        }
    }

    private static void l() throws TapjoyIntegrationException {
        Vector vector = new Vector();
        for (String str : TapjoyConstants.dependencyPermissions) {
            if (!e(str)) {
                vector.add(str);
            }
        }
        if (vector.size() != 0) {
            if (vector.size() != 1) {
                throw new TapjoyIntegrationException("Missing " + vector.size() + " permissions in manifest: " + vector.toString());
            }
            throw new TapjoyIntegrationException("Missing 1 permission in manifest: " + vector.toString());
        }
        Vector vector2 = new Vector();
        for (String str2 : TapjoyConstants.optionalPermissions) {
            if (!e(str2)) {
                vector2.add(str2);
            }
        }
        if (vector2.size() != 0) {
            if (vector2.size() == 1) {
                TapjoyLog.w("TapjoyConnect", "WARNING -- " + vector2.toString() + " permission was not found in manifest. The exclusion of this permission could cause problems.");
            } else {
                TapjoyLog.w("TapjoyConnect", "WARNING -- " + vector2.toString() + " permissions were not found in manifest. The exclusion of these permissions could cause problems.");
            }
        }
    }

    private static boolean m() {
        return c != null && c.length() > 0;
    }

    private static boolean n() {
        return getConnectFlagValue(TapjoyConnectFlag.ALLOW_LEGACY_ID_FALLBACK) != null && getConnectFlagValue(TapjoyConnectFlag.ALLOW_LEGACY_ID_FALLBACK).equals("true");
    }

    private static boolean o() {
        return (ae.isGooglePlayServicesAvailable() && ae.isGooglePlayManifestConfigured()) || getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK) == null || !getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK).equals("true");
    }

    private static String p() {
        String strSHA256 = null;
        TapjoyLog.i("TapjoyConnect", "generating sessionID...");
        try {
            strSHA256 = TapjoyUtil.SHA256((System.currentTimeMillis() / 1000) + v);
            aa = System.currentTimeMillis();
            return strSHA256;
        } catch (Exception e2) {
            TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + e2.toString());
            return strSHA256;
        }
    }

    private static String q() {
        if (m()) {
            return c;
        }
        if (n() || !o()) {
            if (p != null && p.length() > 0) {
                return p;
            }
            if (n != null && n.length() > 0) {
                return n;
            }
        }
        TapjoyLog.e("TapjoyConnect", "Error -- no valid device identifier");
        return null;
    }

    private static String r() {
        String str = v + w + x + c + q;
        try {
            return TapjoyUtil.SHA1(str);
        } catch (Exception e2) {
            return str;
        }
    }

    public static void requestLimitedTapjoyConnect(Context context, String str, TJConnectListener tJConnectListener) throws TapjoyIntegrationException {
        try {
            fo foVar = new fo(str);
            if (foVar.a != fo.a.SDK_ANDROID) {
                throw new IllegalArgumentException("The given API key was not for Android.");
            }
            aP = foVar.b;
            aQ = foVar.c;
            if (i == null) {
                i = new TapjoyConnectCore();
            }
            aT = tJConnectListener;
            TapjoyConnectCore tapjoyConnectCore = i;
            try {
                a(context);
                new Thread(new Runnable() { // from class: com.tapjoy.TapjoyConnectCore.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        TapjoyConnectCore.a();
                        TapjoyConnectCore.this.completeLimitedConnectCall();
                    }
                }).start();
            } catch (TapjoyIntegrationException e2) {
                TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, e2.getMessage()));
                e();
                fs.b.notifyObservers(Boolean.FALSE);
            } catch (TapjoyException e3) {
                TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, e3.getMessage()));
                e();
                fs.b.notifyObservers(Boolean.FALSE);
            }
            TapjoyLog.d("TapjoyConnect", "requestTapjoyConnect function complete");
        } catch (IllegalArgumentException e4) {
            TapjoyLog.d("TapjoyConnect", e4.getMessage());
            throw new TapjoyIntegrationException(e4.getMessage());
        }
    }

    public static void requestTapjoyConnect(Context context, String str) throws TapjoyIntegrationException {
        requestTapjoyConnect(context, str, null);
    }

    public static void requestTapjoyConnect(Context context, String str, Hashtable hashtable) throws TapjoyIntegrationException {
        requestTapjoyConnect(context, str, hashtable, null);
    }

    public static void requestTapjoyConnect(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) throws TapjoyIntegrationException {
        try {
            fo foVar = new fo(str);
            if (foVar.a != fo.a.SDK_ANDROID) {
                throw new IllegalArgumentException("The given API key was not for Android.");
            }
            h = str;
            v = foVar.b;
            M = foVar.c;
            N = foVar.d;
            if (hashtable != null) {
                af.putAll(hashtable);
                ga.b().a(hashtable);
            }
            gz.a(context).j = str;
            k = tJConnectListener;
            if (i == null) {
                i = new TapjoyConnectCore();
            }
            TapjoyConnectCore tapjoyConnectCore = i;
            try {
                a(context);
                new Thread(new Runnable() { // from class: com.tapjoy.TapjoyConnectCore.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        TapjoyConnectCore.a();
                        TapjoyConnectCore.this.completeConnectCall();
                    }
                }).start();
                tapjoyConnectCore.ab = true;
            } catch (TapjoyIntegrationException e2) {
                TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, e2.getMessage()));
                d();
                fs.b.notifyObservers(Boolean.FALSE);
            } catch (TapjoyException e3) {
                TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, e3.getMessage()));
                d();
                fs.b.notifyObservers(Boolean.FALSE);
            }
        } catch (IllegalArgumentException e4) {
            throw new TapjoyIntegrationException(e4.getMessage());
        }
    }

    public static void setAdTrackingEnabled() {
        if (ge.a() != null) {
            ge geVarA = ge.a();
            if (geVarA.c == null ? false : geVarA.c.booleanValue()) {
                d = false;
                return;
            }
        }
        if (ae != null) {
            d = ae.isAdTrackingEnabled();
        }
    }

    public static void setCustomParameter(String str) {
        D = str;
    }

    public static void setPlugin(String str) {
        O = str;
    }

    public static void setSDKType(String str) {
        P = str;
    }

    public static void setUserID(String str, TJSetUserIDListener tJSetUserIDListener) {
        C = str;
        l = tJSetUserIDListener;
        TapjoyLog.d("TapjoyConnect", "URL parameters: " + getURLParams());
        new Thread(new Runnable() { // from class: com.tapjoy.TapjoyConnectCore.3
            @Override // java.lang.Runnable
            public final void run() {
                TapjoyLog.i("TapjoyConnect", "Setting userID to " + TapjoyConnectCore.C);
                TapjoyHttpURLResponse responseFromURL = TapjoyConnectCore.j.getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_USER_ID_URL_PATH, TapjoyConnectCore.getURLParams());
                TapjoyConnectCore.a(responseFromURL.response != null ? TapjoyConnectCore.a(responseFromURL.response) : false);
            }
        }).start();
    }

    public static void setViewShowing(boolean z2) {
        if (z2) {
            ag.put("", 1);
        } else {
            ag.clear();
        }
    }

    public static void viewDidClose(String str) {
        TapjoyLog.d("TapjoyConnect", "viewDidClose: " + str);
        ag.remove(str);
        fs.e.notifyObservers();
    }

    public static void viewWillOpen(String str, int i2) {
        TapjoyLog.d("TapjoyConnect", "viewWillOpen: " + str);
        ag.put(str, Integer.valueOf(i2));
    }

    public void actionComplete(String str) {
        TapjoyLog.i("TapjoyConnect", "actionComplete: " + str);
        Map mapF = f();
        TapjoyUtil.safePut(mapF, "app_id", str, true);
        mapF.putAll(getTimeStampAndVerifierParams());
        TapjoyLog.d("TapjoyConnect", "PPA URL parameters: " + mapF);
        new Thread(new PPAThread(mapF)).start();
    }

    public void appPause() {
        this.Z = true;
    }

    public void appResume() {
        if (this.Z) {
            p();
            this.Z = false;
        }
    }

    public void completeConnectCall() {
        boolean z2;
        String connectResult;
        TapjoyLog.d("TapjoyConnect", "starting connect call...");
        String hostURL = TapjoyConfig.TJC_CONNECT_SERVICE_URL;
        if (getHostURL() != TapjoyConfig.TJC_SERVICE_URL) {
            hostURL = getHostURL();
        }
        if (isConnected() || (connectResult = TapjoyAppSettings.getInstance().getConnectResult(r(), v.b())) == null || !a(connectResult, true)) {
            z2 = false;
        } else {
            TapjoyLog.i("TapjoyConnect", "Connect using stored connect result");
            ac = true;
            if (k != null) {
                k.onConnectSuccess();
            }
            fs.a.notifyObservers();
            z2 = true;
        }
        TapjoyHttpURLResponse responseFromURL = j.getResponseFromURL(hostURL + TapjoyConstants.TJC_CONNECT_URL_PATH, (Map) null, (Map) null, getURLParams());
        if (responseFromURL == null || responseFromURL.statusCode != 200) {
            if (!z2) {
                d();
            }
            fs.b.notifyObservers(Boolean.FALSE);
            return;
        }
        if (!a(responseFromURL.response, false)) {
            if (!z2) {
                d();
            }
            fs.b.notifyObservers(Boolean.FALSE);
            return;
        }
        TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
        ac = true;
        for (Map.Entry entry : getGenericURLParams().entrySet()) {
            TapjoyLog.d("TapjoyConnect", ((String) entry.getKey()) + ": " + ((String) entry.getValue()));
        }
        if (!z2) {
            if (k != null) {
                k.onConnectSuccess();
            }
            fs.a.notifyObservers();
        }
        fs.b.notifyObservers(Boolean.TRUE);
    }

    public void completeLimitedConnectCall() {
        String hostURL = TapjoyConfig.TJC_CONNECT_SERVICE_URL;
        if (getHostURL() != TapjoyConfig.TJC_SERVICE_URL) {
            hostURL = getHostURL();
        }
        TapjoyHttpURLResponse responseFromURL = j.getResponseFromURL(hostURL + TapjoyConstants.TJC_CONNECT_URL_PATH, (Map) null, (Map) null, getLimitedURLParams());
        if (responseFromURL == null || responseFromURL.statusCode != 200) {
            e();
            fs.b.notifyObservers(Boolean.FALSE);
            return;
        }
        if (!c(responseFromURL.response)) {
            e();
            fs.b.notifyObservers(Boolean.FALSE);
            return;
        }
        TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
        aS = true;
        for (Map.Entry entry : getLimitedGenericURLParams().entrySet()) {
            TapjoyLog.d("TapjoyConnect", ((String) entry.getKey()) + ": " + ((String) entry.getValue()));
        }
        if (aT != null) {
            aT.onConnectSuccess();
        }
        fs.a.notifyObservers();
        fs.b.notifyObservers(Boolean.TRUE);
    }

    public float getCurrencyMultiplier() {
        return R;
    }

    public boolean isInitialized() {
        return this.ab;
    }

    public void release() {
        i = null;
        j = null;
        TapjoyLog.d("TapjoyConnect", "Releasing core static instance.");
    }

    public void setCurrencyMultiplier(float f2) {
        TapjoyLog.i("TapjoyConnect", "setVirtualCurrencyMultiplier: " + f2);
        R = f2;
    }
}
