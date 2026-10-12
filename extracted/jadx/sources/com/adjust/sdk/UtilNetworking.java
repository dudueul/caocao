package com.adjust.sdk;

import android.net.Uri;
import com.facebook.GraphRequest;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import com.tapjoy.TapjoyConstants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class UtilNetworking {
    private static String userAgent;

    static class ConnectionOptions implements IConnectionOptions {
        ConnectionOptions() {
        }

        @Override // com.adjust.sdk.UtilNetworking.IConnectionOptions
        public void applyConnectionOptions(HttpsURLConnection httpsURLConnection, String str) {
            httpsURLConnection.setRequestProperty("Client-SDK", str);
            httpsURLConnection.setConnectTimeout(60000);
            httpsURLConnection.setReadTimeout(60000);
            if (UtilNetworking.userAgent != null) {
                httpsURLConnection.setRequestProperty("User-Agent", UtilNetworking.userAgent);
            }
        }
    }

    public interface IConnectionOptions {
        void applyConnectionOptions(HttpsURLConnection httpsURLConnection, String str);
    }

    private static String buildAuthorizationHeader(Map<String, String> map, String str, String str2, String str3) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Map<String, String> signature = getSignature(map, str3, str);
        String string = Util.formatString("Signature %s,%s,%s,%s", Util.formatString("secret_id=\"%s\"", str2), Util.formatString("signature=\"%s\"", Util.sha256(signature.get("clear_signature"))), Util.formatString("algorithm=\"%s\"", "sha256"), Util.formatString("headers=\"%s\"", signature.get(GraphRequest.FIELDS_PARAM)));
        getLogger().verbose("authorizationHeader: %s", string);
        return string;
    }

    private static Uri buildUri(String str, Map<String, String> map, String str2) {
        Uri.Builder builder = new Uri.Builder();
        String protocol = Constants.SCHEME;
        String authority = Constants.AUTHORITY;
        String path = "";
        try {
            String baseUrl = AdjustFactory.getBaseUrl();
            if (str2 != null) {
                baseUrl = baseUrl + str2;
            }
            URL url = new URL(baseUrl);
            protocol = url.getProtocol();
            authority = url.getAuthority();
            path = url.getPath();
        } catch (MalformedURLException e) {
            getLogger().error("Unable to parse endpoint (%s)", e.getMessage());
        }
        builder.scheme(protocol);
        builder.encodedAuthority(authority);
        builder.path(path);
        builder.appendPath(str);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        builder.appendQueryParameter("sent_at", Util.dateFormatter.format(Long.valueOf(System.currentTimeMillis())));
        return builder.build();
    }

    public static ResponseData createGETHttpsURLConnection(ActivityPackage activityPackage, String str) throws Exception {
        try {
            HashMap map = new HashMap(activityPackage.getParameters());
            String strExtractAppSecret = extractAppSecret(map);
            String strExtractSecretId = extractSecretId(map);
            HttpsURLConnection httpsURLConnection = AdjustFactory.getHttpsURLConnection(new URL(buildUri(activityPackage.getPath(), map, str).toString()));
            AdjustFactory.getConnectionOptions().applyConnectionOptions(httpsURLConnection, activityPackage.getClientSdk());
            String strBuildAuthorizationHeader = buildAuthorizationHeader(map, strExtractAppSecret, strExtractSecretId, activityPackage.getActivityKind().toString());
            if (strBuildAuthorizationHeader != null) {
                httpsURLConnection.setRequestProperty(HttpRequest.HEADER_AUTHORIZATION, strBuildAuthorizationHeader);
            }
            httpsURLConnection.setRequestMethod("GET");
            return readHttpResponse(httpsURLConnection, activityPackage);
        } catch (Exception e) {
            throw e;
        }
    }

    public static ResponseData createPOSTHttpsURLConnection(String str, ActivityPackage activityPackage, int i) throws Exception {
        DataOutputStream dataOutputStream = null;
        try {
            try {
                HttpsURLConnection httpsURLConnection = AdjustFactory.getHttpsURLConnection(new URL(str));
                HashMap map = new HashMap(activityPackage.getParameters());
                AdjustFactory.getConnectionOptions().applyConnectionOptions(httpsURLConnection, activityPackage.getClientSdk());
                String strBuildAuthorizationHeader = buildAuthorizationHeader(map, extractAppSecret(map), extractSecretId(map), activityPackage.getActivityKind().toString());
                if (strBuildAuthorizationHeader != null) {
                    httpsURLConnection.setRequestProperty(HttpRequest.HEADER_AUTHORIZATION, strBuildAuthorizationHeader);
                }
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                DataOutputStream dataOutputStream2 = new DataOutputStream(httpsURLConnection.getOutputStream());
                try {
                    dataOutputStream2.writeBytes(getPostDataString(map, i));
                    ResponseData httpResponse = readHttpResponse(httpsURLConnection, activityPackage);
                    if (dataOutputStream2 != null) {
                        try {
                            dataOutputStream2.flush();
                            dataOutputStream2.close();
                        } catch (Exception e) {
                        }
                    }
                    return httpResponse;
                } catch (Exception e2) {
                    throw e2;
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.flush();
                            dataOutputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            throw e4;
        }
    }

    private static String extractAppSecret(Map<String, String> map) {
        return map.remove("app_secret");
    }

    private static String extractSecretId(Map<String, String> map) {
        return map.remove("secret_id");
    }

    private static ILogger getLogger() {
        return AdjustFactory.getLogger();
    }

    private static String getPostDataString(Map<String, String> map, int i) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String strEncode = URLEncoder.encode(entry.getKey(), "UTF-8");
            String value = entry.getValue();
            String strEncode2 = value != null ? URLEncoder.encode(value, "UTF-8") : "";
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(strEncode);
            sb.append("=");
            sb.append(strEncode2);
        }
        String str = Util.dateFormatter.format(Long.valueOf(System.currentTimeMillis()));
        sb.append("&");
        sb.append(URLEncoder.encode("sent_at", "UTF-8"));
        sb.append("=");
        sb.append(URLEncoder.encode(str, "UTF-8"));
        if (i > 0) {
            sb.append("&");
            sb.append(URLEncoder.encode("queue_size", "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode("" + i, "UTF-8"));
        }
        return sb.toString();
    }

    private static Map<String, String> getSignature(Map<String, String> map, String str, String str2) {
        String str3 = map.get("created_at");
        String validIdentifier = getValidIdentifier(map);
        String str4 = map.get(validIdentifier);
        HashMap map2 = new HashMap();
        map2.put("app_secret", str2);
        map2.put("created_at", str3);
        map2.put("activity_kind", str);
        map2.put(validIdentifier, str4);
        String str5 = "";
        String str6 = "";
        for (Map.Entry entry : map2.entrySet()) {
            if (entry.getValue() != null) {
                str5 = str5 + ((String) entry.getKey()) + " ";
                str6 = str6 + ((String) entry.getValue());
            }
        }
        String strSubstring = str5.substring(0, str5.length() - 1);
        HashMap map3 = new HashMap();
        map3.put("clear_signature", str6);
        map3.put(GraphRequest.FIELDS_PARAM, strSubstring);
        return map3;
    }

    private static String getValidIdentifier(Map<String, String> map) {
        if (map.get("gps_adid") != null) {
            return "gps_adid";
        }
        if (map.get("fire_adid") != null) {
            return "fire_adid";
        }
        if (map.get(TapjoyConstants.TJC_ANDROID_ID) != null) {
            return TapjoyConstants.TJC_ANDROID_ID;
        }
        if (map.get("mac_sha1") != null) {
            return "mac_sha1";
        }
        if (map.get("mac_md5") != null) {
            return "mac_md5";
        }
        if (map.get("android_uuid") != null) {
            return "android_uuid";
        }
        return null;
    }

    private static ResponseData readHttpResponse(HttpsURLConnection httpsURLConnection, ActivityPackage activityPackage) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        ILogger logger = getLogger();
        ResponseData responseDataBuildResponseData = ResponseData.buildResponseData(activityPackage);
        try {
            try {
                httpsURLConnection.connect();
                Integer numValueOf = Integer.valueOf(httpsURLConnection.getResponseCode());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(numValueOf.intValue() >= 400 ? httpsURLConnection.getErrorStream() : httpsURLConnection.getInputStream()));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    stringBuffer.append(line);
                }
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                String string = stringBuffer.toString();
                logger.verbose("Response: %s", string);
                if (string != null && string.length() != 0) {
                    JSONObject jSONObject = null;
                    try {
                        jSONObject = new JSONObject(string);
                    } catch (JSONException e) {
                        String string2 = Util.formatString("Failed to parse json response. (%s)", e.getMessage());
                        logger.error(string2, new Object[0]);
                        responseDataBuildResponseData.message = string2;
                    }
                    if (jSONObject != null) {
                        responseDataBuildResponseData.jsonResponse = jSONObject;
                        String strOptString = jSONObject.optString("message", null);
                        responseDataBuildResponseData.message = strOptString;
                        responseDataBuildResponseData.timestamp = jSONObject.optString(TapjoyConstants.TJC_TIMESTAMP, null);
                        responseDataBuildResponseData.adid = jSONObject.optString(NxLogInfo.KEY_ADID, null);
                        if (strOptString == null) {
                            strOptString = "No message found";
                        }
                        if (numValueOf == null || numValueOf.intValue() != 200) {
                            logger.error("%s", strOptString);
                        } else {
                            logger.info("%s", strOptString);
                            responseDataBuildResponseData.success = true;
                        }
                    }
                }
                return responseDataBuildResponseData;
            } catch (Exception e2) {
                logger.error("Failed to read response. (%s)", e2.getMessage());
                throw e2;
            }
        } catch (Throwable th) {
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
            throw th;
        }
    }

    public static void setUserAgent(String str) {
        userAgent = str;
    }
}
