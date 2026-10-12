package com.nhn.android.naverlogin.connection;

import android.content.Context;
import android.util.Log;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import com.nhn.android.naverlogin.connection.gen.OAuthQueryGenerator;
import com.nhn.android.naverlogin.data.OAuthErrorCode;
import com.nhn.android.naverlogin.data.OAuthResponse;
import com.nhn.android.naverlogin.util.DeviceAppInfo;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class OAuthLoginConnection extends CommonConnection {
    private static final String TAG = "NaverLoginOAuth|OAuthLoginConnection";

    public static OAuthResponse deleteToken(Context context, String str, String str2, String str3) {
        return request(context, new OAuthQueryGenerator().generateRequestDeleteAccessTokenUrl(str, str2, str3, DeviceAppInfo.getBaseInstance().getLocaleString(context)));
    }

    private static OAuthResponse request(Context context, String str) {
        ResponseData responseDataRequest = CommonConnection.request(context, str, null, null);
        if (!responseDataRequest.mStat.equals(ResponseData.ResponseDataStat.SUCCESS)) {
            if (responseDataRequest.mStatusCode == 503) {
                return new OAuthResponse(OAuthErrorCode.SERVER_ERROR_TEMPORARILY_UNAVAILABLE);
            }
            if (responseDataRequest.mStatusCode == 500) {
                return new OAuthResponse(OAuthErrorCode.SERVER_ERROR_SERVER_ERROR);
            }
            if (responseDataRequest.mStat.equals(ResponseData.ResponseDataStat.CONNECTION_TIMEOUT) || responseDataRequest.mStat.equals(ResponseData.ResponseDataStat.CONNECTION_FAIL)) {
                return new OAuthResponse(OAuthErrorCode.CLIENT_ERROR_CONNECTION_ERROR);
            }
            return responseDataRequest.mStat.equals(ResponseData.ResponseDataStat.NO_PEER_CERTIFICATE) ? new OAuthResponse(OAuthErrorCode.CLIENT_ERROR_CERTIFICATION_ERROR) : new OAuthResponse(OAuthErrorCode.ERROR_NO_CATAGORIZED);
        }
        try {
            JSONObject jSONObject = new JSONObject(responseDataRequest.mContent);
            if (OAuthLoginDefine.DEVELOPER_VERSION) {
                Log.d(TAG, "len :" + jSONObject.length());
                Log.d(TAG, "str :" + jSONObject.toString());
            }
            Iterator<String> itKeys = jSONObject.keys();
            HashMap map = new HashMap();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                String string = jSONObject.getString(next);
                map.put(next, string);
                if (OAuthLoginDefine.DEVELOPER_VERSION) {
                    Log.d(TAG, "key:" + next + ",value:" + string);
                }
            }
            return new OAuthResponse(map);
        } catch (JSONException e) {
            if (OAuthLoginDefine.DEVELOPER_VERSION && responseDataRequest != null) {
                Log.d(TAG, "content:" + responseDataRequest.mContent);
            }
            e.printStackTrace();
            return new OAuthResponse(OAuthErrorCode.CLIENT_ERROR_PARSING_FAIL);
        }
    }

    public static OAuthResponse requestAccessToken(Context context, String str, String str2, String str3, String str4) {
        return request(context, new OAuthQueryGenerator().generateRequestAccessTokenUrl(str, str2, str3, str4, DeviceAppInfo.getBaseInstance().getLocaleString(context)));
    }

    public static OAuthResponse requestRefreshToken(Context context, String str, String str2, String str3) {
        return request(context, new OAuthQueryGenerator().generateRequestRefreshAccessTokenUrl(str, str2, str3, DeviceAppInfo.getBaseInstance().getLocaleString(context)));
    }

    public static ResponseData requestWithOAuthHeader(Context context, String str, String str2, String str3, String str4) {
        return CommonConnection.request(context, str, null, null, str4, false);
    }
}
