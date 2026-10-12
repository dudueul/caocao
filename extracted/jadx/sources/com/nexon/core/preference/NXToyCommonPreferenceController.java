package com.nexon.core.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import com.facebook.appevents.UserDataStore;
import com.google.gson.reflect.TypeToken;
import com.nexon.core.android.NXPApplicationConfigManager;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.model.NXToyMaintenanceEnterToyResultInfo;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import com.nexon.nexonanalyticssdk.NxSystemInfo;
import com.nexon.platform.NXPFinalizer;
import com.tapjoy.TapjoyConstants;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyCommonPreferenceController {
    public static final String KEY_SET_NAME = "NXPCommonPrefKeySetName";
    private static final String PREF_NAME = "NPACommon";
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: com.nexon.core.preference.NXToyCommonPreferenceController.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NXToyCommonPreferenceController unused = NXToyCommonPreferenceController.instance = null;
        }
    };
    private static NXToyCommonPreferenceController instance;
    private String androidID;
    private String deviceModel;
    private String os;
    private SharedPreferences preference;

    private NXToyCommonPreferenceController() {
    }

    private SharedPreferences.Editor getEditor() {
        SharedPreferences.Editor editorEdit;
        synchronized (NXToyCommonPreferenceController.class) {
            editorEdit = this.preference.edit();
        }
        return editorEdit;
    }

    public static NXToyCommonPreferenceController getInstance() {
        if (instance == null) {
            synchronized (NXToySessionManager.class) {
                if (instance == null) {
                    instance = new NXToyCommonPreferenceController();
                }
            }
        }
        return instance;
    }

    public String getAdditionalTermsAgree() {
        return this.preference.getString("additionalTermsAgree", "");
    }

    public String getAdvertisingId() {
        return this.preference.getString("advertisingId", "");
    }

    public String getAndroidID() {
        return this.androidID;
    }

    public String getAppId() {
        return this.preference.getString("appId", "");
    }

    public int getAppVersionCode() {
        return this.preference.getInt("appVersionCode", -1);
    }

    public String getBasePlate(String str) {
        return this.preference.getString("basePlate_" + str, "");
    }

    public String getCarrierName() {
        return this.preference.getString("carrierName", "");
    }

    public String getCountry() {
        return this.preference.getString(UserDataStore.COUNTRY, "");
    }

    public String getCustomerService() {
        return this.preference.getString("customerService", "");
    }

    public List<Integer> getDefaultLoginTypeList() {
        String optionUseMemberships = getOptionUseMemberships();
        ToyLog.d("Option Membership : " + optionUseMemberships);
        Type type = new TypeToken<ArrayList<Integer>>() { // from class: com.nexon.core.preference.NXToyCommonPreferenceController.2
        }.getType();
        List<Integer> list = (List) NXJsonUtil.fromObject(optionUseMemberships, type);
        ToyLog.d("Use Membership List : " + list);
        if (list != null && list.size() >= 1) {
            return list;
        }
        String useMemberships = getUseMemberships();
        return NXStringUtil.isNotNull(useMemberships) ? (List) NXJsonUtil.fromObject(useMemberships, type) : list;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getEndingBanner() {
        return this.preference.getString("endingBanner", "");
    }

    public int getGoogleSignInInsteadOfGcidLogin() {
        return this.preference.getInt("googleSignInInsteadOfGcidLogin", 0);
    }

    public boolean getHasPhoneNumberFlag(long j) {
        return this.preference.getBoolean("hasPhoneNumberFlag" + j, true);
    }

    public String getInitCountryFlag() {
        return this.preference.getString("userCountry", "");
    }

    public boolean getIsDebugMode() {
        return this.preference.getBoolean("isDebugMode", false);
    }

    public boolean getIsInitializedHasPhoneNumberFlag() {
        return this.preference.getBoolean("isInitializedHasPhoneNumberFlag", false);
    }

    public boolean getIsTermsOfAgree() {
        return this.preference.getBoolean("isTermsOfAgree", false);
    }

    @Deprecated
    public String getLanguage() {
        return this.preference.getString("language", "");
    }

    public String getLocale() {
        return this.preference.getString("locale", "");
    }

    public String getLoginUIType() {
        return this.preference.getString("loginUIType", "");
    }

    public String getMCC() {
        return this.preference.getString("mcc", "0");
    }

    public String getMNC() {
        return this.preference.getString("mnc", "0");
    }

    public NXToyMaintenanceEnterToyResultInfo getMaintenanceEnterToyResultInfo() {
        return (NXToyMaintenanceEnterToyResultInfo) NXJsonUtil.fromObject(this.preference.getString("NXToyMaintenanceEnterToyResultInfo", "{}"), NXToyMaintenanceEnterToyResultInfo.class);
    }

    public String getNotShowDateForBanner(int i) {
        return this.preference.getString("banner_" + i, "");
    }

    public String getNotShowDateForShowToday(int i) {
        return this.preference.getString("showtoday_" + i, "");
    }

    public int getNxkATL() {
        return this.preference.getInt("nxkATL", -1);
    }

    public String getOfferwall() {
        return this.preference.getString("offerwall", "");
    }

    public String getOptionUseMemberships() {
        return this.preference.getString("optionUseMemberships", "[]");
    }

    public String getOs() {
        return this.os;
    }

    public long getPhoneNumberSaveTime() {
        return this.preference.getLong("phoneNumberSaveTime", 0L);
    }

    public int getPolicyApiVer() {
        return this.preference.getInt("policyApiVer", 1);
    }

    public SharedPreferences getPreference() {
        return this.preference;
    }

    public int getPushAgree() {
        return this.preference.getInt("pushAgree", 0);
    }

    public String getPushPolicies() {
        return this.preference.getString("nxpPushPolicies", "");
    }

    public String getSaveTerms() {
        return this.preference.getString("saveTerms", "");
    }

    public String getServiceClientId() {
        return this.preference.getString("serviceClientId", "");
    }

    public String getServiceTitle() {
        return this.preference.getString("serviceTitle", "");
    }

    public int getTermsApiVer() {
        return this.preference.getInt("termsApiVer", 1);
    }

    public String getUUID() {
        synchronized (NXToyCommonPreferenceController.class) {
            String string = this.preference.getString(NxSystemInfo.KEY_UUID, "");
            if (NXStringUtil.isNotNull(string)) {
                return string;
            }
            ToyLog.d("UUID not exist. get uuid from storage.");
            String string2 = UUID.randomUUID().toString();
            ToyLog.d("MakeUUID : " + string2);
            setUUID(string2);
            return string2;
        }
    }

    public String getUUID2() {
        return this.preference.getString(NxLogInfo.KEY_UUID2, "");
    }

    public boolean getUseGCID() {
        return this.preference.getBoolean("useGcid", false);
    }

    public String getUseMemberships() {
        return this.preference.getString("useMemberships", "");
    }

    public int getUseNgsm() {
        return this.preference.getInt("useNgsm", 0);
    }

    public void initialize(Context context) {
        String str = "NPACommon." + NXPApplicationConfigManager.getInstance().getServiceId();
        this.preference = context.getSharedPreferences(str, 0);
        this.androidID = Settings.Secure.getString(context.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        String string = this.preference.getString(NxSystemInfo.KEY_UUID, "");
        if (!NXStringUtil.isNotNull(string)) {
            setUUID(UUID.randomUUID().toString());
        }
        ToyLog.d("Set uuid : " + string);
        if (getCountry().equals("")) {
            setCountry(Locale.getDefault().getCountry());
        }
        ToyLog.d("Set default country : " + getCountry());
        if (getLocale().equals("")) {
            setLocale(Locale.getDefault().toString());
        }
        ToyLog.d("Set default locale : " + getLocale());
        setUUID2(this.androidID);
        this.deviceModel = Build.MODEL;
        this.os = "A";
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> stringSet = defaultSharedPreferences.getStringSet(KEY_SET_NAME, new HashSet());
        stringSet.add(str);
        defaultSharedPreferences.edit().putStringSet(KEY_SET_NAME, stringSet).apply();
    }

    public void removeAll() {
        getEditor().clear().commit();
    }

    public void removeHasPhoneNumberFlag() {
        Iterator<Map.Entry<String, ?>> it = this.preference.getAll().entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            if (key != null || !key.isEmpty()) {
                if (key.startsWith("hasPhoneNumberFlag")) {
                    this.preference.edit().remove(key).commit();
                }
            }
        }
    }

    public void removeMaintenanceEnterToyResultInfo() {
        getEditor().remove("NXToyMaintenanceEnterToyResultInfo").commit();
    }

    public void setAdditionalTermsAgree(String str) {
        getEditor().putString("additionalTermsAgree", str).commit();
    }

    public void setAdvertisingId(String str) {
        getEditor().putString("advertisingId", str).commit();
    }

    public void setAppId(String str) {
        getEditor().putString("appId", str).commit();
    }

    public void setAppVersionCode(int i) {
        getEditor().putInt("appVersionCode", i).commit();
    }

    public void setBasePlate(String str, String str2) {
        getEditor().putString("basePlate_" + str, str2).commit();
    }

    public void setCarrierName(String str) {
        getEditor().putString("carrierName", str).commit();
    }

    public void setCountry(String str) {
        getEditor().putString(UserDataStore.COUNTRY, str).commit();
    }

    public void setCustomerService(String str) {
        getEditor().putString("customerService", str).commit();
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setEndingBanner(String str) {
        getEditor().putString("endingBanner", str).commit();
    }

    public void setGoogleSignInInsteadOfGcidLogin(int i) {
        getEditor().putInt("googleSignInInsteadOfGcidLogin", i).commit();
    }

    public void setHasPhoneNumberFlag(long j, boolean z) {
        getEditor().putBoolean("hasPhoneNumberFlag" + j, z).commit();
    }

    public void setInitCountryFlag(String str) {
        getEditor().putString("userCountry", str).commit();
    }

    public void setIsDebugMode(boolean z) {
        getEditor().putBoolean("isDebugMode", z).commit();
    }

    public void setIsInitializedHasPhoneNumberFlag() {
        getEditor().putBoolean("isInitializedHasPhoneNumberFlag", true).commit();
    }

    public void setIsTermsOfAgree(boolean z) {
        getEditor().putBoolean("isTermsOfAgree", z).commit();
    }

    @Deprecated
    public void setLanguage(String str) {
        getEditor().putString("language", str).commit();
    }

    public void setLocale(String str) {
        getEditor().putString("locale", str).commit();
    }

    public void setLoginUIType(String str) {
        getEditor().putString("loginUIType", str).commit();
    }

    public void setMCC(String str) {
        getEditor().putString("mcc", str).commit();
    }

    public void setMNC(String str) {
        getEditor().putString("mnc", str).commit();
    }

    public void setMaintenanceEnterToyResultInfo(NXToyMaintenanceEnterToyResultInfo nXToyMaintenanceEnterToyResultInfo) {
        getEditor().putString("NXToyMaintenanceEnterToyResultInfo", NXJsonUtil.toJsonString(nXToyMaintenanceEnterToyResultInfo)).commit();
    }

    public void setNotShowDateForBanner(int i, String str) {
        getEditor().putString("banner_" + i, str).commit();
    }

    public void setNotShowDateForShowToday(int i, String str) {
        getEditor().putString("showtoday_" + i, str).commit();
    }

    public void setNxkATL(String str) {
        if (NXStringUtil.isNotNull(str)) {
            getEditor().putInt("nxkATL", Integer.parseInt(str)).commit();
        } else {
            getEditor().putInt("nxkATL", -1).commit();
        }
    }

    public void setOfferwall(String str) {
        getEditor().putString("offerwall", str).commit();
    }

    public void setOptionUseMemberships(String str) {
        getEditor().putString("optionUseMemberships", str).commit();
    }

    public void setOs(String str) {
        this.os = str;
    }

    public void setPhoneNumberSaveTime(long j) {
        getEditor().putLong("phoneNumberSaveTime", j).commit();
    }

    public void setPolicyApiVer(int i) {
        getEditor().putInt("policyApiVer", i).commit();
    }

    public void setPushAgree(int i) {
        getEditor().putInt("pushAgree", i).commit();
    }

    public void setPushPolicies(String str) {
        getEditor().putString("nxpPushPolicies", str).commit();
    }

    public void setSaveTerms(String str) {
        getEditor().putString("saveTerms", str).commit();
    }

    public void setServiceClientId(String str) {
        if (str != null) {
            getEditor().putString("serviceClientId", str).commit();
        }
    }

    public void setServiceTitle(String str) {
        getEditor().putString("serviceTitle", str).commit();
    }

    public void setTermsApiVer(int i) {
        getEditor().putInt("termsApiVer", i).commit();
    }

    public void setUUID(String str) {
        getEditor().putString(NxSystemInfo.KEY_UUID, str).commit();
    }

    public void setUUID2(String str) {
        getEditor().putString(NxLogInfo.KEY_UUID2, str).commit();
    }

    public void setUseGCID(boolean z) {
        getEditor().putBoolean("useGcid", z).commit();
    }

    public void setUseMemberships(String str) {
        getEditor().putString("useMemberships", str).commit();
    }

    public void setUseNgsm(int i) {
        getEditor().putInt("useNgsm", i).commit();
    }

    public void setUseTPA(boolean z) {
        getEditor().putBoolean("useTPA", z).commit();
    }

    public String toString() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("androidID", this.androidID);
        linkedHashMap.put("deviceModel", this.deviceModel);
        linkedHashMap.put("os", this.os);
        return (this.preference == null || this.preference.getAll() == null) ? NXJsonUtil.toJsonString(linkedHashMap) : NXJsonUtil.toJsonString(this.preference.getAll());
    }

    public boolean useTPA() {
        return this.preference.getBoolean("useTPA", true);
    }
}
