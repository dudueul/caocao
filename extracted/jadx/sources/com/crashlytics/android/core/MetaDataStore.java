package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class MetaDataStore {
    private static final String KEYDATA_SUFFIX = "keys";
    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_NAME = "userName";
    private static final String METADATA_EXT = ".meta";
    private static final String USERDATA_SUFFIX = "user";
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final File filesDir;

    public MetaDataStore(File file) {
        this.filesDir = file;
    }

    private File getKeysFileForSession(String str) {
        return new File(this.filesDir, str + KEYDATA_SUFFIX + METADATA_EXT);
    }

    private File getUserDataFileForSession(String str) {
        return new File(this.filesDir, str + USERDATA_SUFFIX + METADATA_EXT);
    }

    private static Map<String, String> jsonToKeysData(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, valueOrNull(jSONObject, next));
        }
        return map;
    }

    private static UserMetaData jsonToUserData(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        return new UserMetaData(valueOrNull(jSONObject, KEY_USER_ID), valueOrNull(jSONObject, KEY_USER_NAME), valueOrNull(jSONObject, KEY_USER_EMAIL));
    }

    private static String keysDataToJson(Map<String, String> map) throws JSONException {
        return new JSONObject(map).toString();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.crashlytics.android.core.MetaDataStore$1] */
    private static String userDataToJson(final UserMetaData userMetaData) throws JSONException {
        return new JSONObject() { // from class: com.crashlytics.android.core.MetaDataStore.1
            {
                put(MetaDataStore.KEY_USER_ID, userMetaData.id);
                put(MetaDataStore.KEY_USER_NAME, userMetaData.name);
                put(MetaDataStore.KEY_USER_EMAIL, userMetaData.email);
            }
        }.toString();
    }

    private static String valueOrNull(JSONObject jSONObject, String str) {
        if (jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.optString(str, null);
    }

    public Map<String, String> readKeyData(String str) throws Throwable {
        Map<String, String> mapEmptyMap;
        File keysFileForSession = getKeysFileForSession(str);
        if (!keysFileForSession.exists()) {
            return Collections.emptyMap();
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(keysFileForSession);
                try {
                    mapEmptyMap = jsonToKeysData(CommonUtils.streamToString(fileInputStream2));
                    CommonUtils.closeOrLog(fileInputStream2, "Failed to close user metadata file.");
                } catch (Exception e) {
                    e = e;
                    fileInputStream = fileInputStream2;
                    Fabric.getLogger().e(CrashlyticsCore.TAG, "Error deserializing user metadata.", e);
                    CommonUtils.closeOrLog(fileInputStream, "Failed to close user metadata file.");
                    mapEmptyMap = Collections.emptyMap();
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    CommonUtils.closeOrLog(fileInputStream, "Failed to close user metadata file.");
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return mapEmptyMap;
    }

    public UserMetaData readUserData(String str) throws Throwable {
        UserMetaData userMetaDataJsonToUserData;
        File userDataFileForSession = getUserDataFileForSession(str);
        if (!userDataFileForSession.exists()) {
            return UserMetaData.EMPTY;
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(userDataFileForSession);
                try {
                    userMetaDataJsonToUserData = jsonToUserData(CommonUtils.streamToString(fileInputStream2));
                    CommonUtils.closeOrLog(fileInputStream2, "Failed to close user metadata file.");
                } catch (Exception e) {
                    e = e;
                    fileInputStream = fileInputStream2;
                    Fabric.getLogger().e(CrashlyticsCore.TAG, "Error deserializing user metadata.", e);
                    CommonUtils.closeOrLog(fileInputStream, "Failed to close user metadata file.");
                    userMetaDataJsonToUserData = UserMetaData.EMPTY;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    CommonUtils.closeOrLog(fileInputStream, "Failed to close user metadata file.");
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return userMetaDataJsonToUserData;
    }

    public void writeKeyData(String str, Map<String, String> map) throws Throwable {
        File keysFileForSession = getKeysFileForSession(str);
        BufferedWriter bufferedWriter = null;
        try {
            try {
                String strKeysDataToJson = keysDataToJson(map);
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(keysFileForSession), UTF_8));
                try {
                    bufferedWriter2.write(strKeysDataToJson);
                    bufferedWriter2.flush();
                    CommonUtils.closeOrLog(bufferedWriter2, "Failed to close key/value metadata file.");
                    bufferedWriter = bufferedWriter2;
                } catch (Exception e) {
                    e = e;
                    bufferedWriter = bufferedWriter2;
                    Fabric.getLogger().e(CrashlyticsCore.TAG, "Error serializing key/value metadata.", e);
                    CommonUtils.closeOrLog(bufferedWriter, "Failed to close key/value metadata file.");
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    CommonUtils.closeOrLog(bufferedWriter, "Failed to close key/value metadata file.");
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void writeUserData(String str, UserMetaData userMetaData) throws Throwable {
        File userDataFileForSession = getUserDataFileForSession(str);
        BufferedWriter bufferedWriter = null;
        try {
            try {
                String strUserDataToJson = userDataToJson(userMetaData);
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userDataFileForSession), UTF_8));
                try {
                    bufferedWriter2.write(strUserDataToJson);
                    bufferedWriter2.flush();
                    CommonUtils.closeOrLog(bufferedWriter2, "Failed to close user metadata file.");
                    bufferedWriter = bufferedWriter2;
                } catch (Exception e) {
                    e = e;
                    bufferedWriter = bufferedWriter2;
                    Fabric.getLogger().e(CrashlyticsCore.TAG, "Error serializing user metadata.", e);
                    CommonUtils.closeOrLog(bufferedWriter, "Failed to close user metadata file.");
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    CommonUtils.closeOrLog(bufferedWriter, "Failed to close user metadata file.");
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }
}
