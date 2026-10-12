package com.nexon.core.requestpostman;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.facebook.internal.NativeProtocol;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.naver.glink.android.sdk.login.neoid.PlugSchemeActivity;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.request.NXToyBoltRequest;
import com.nexon.core.requestpostman.request.NXToyByteArrayRequest;
import com.nexon.core.requestpostman.request.NXToyCreateNPTokenRequest;
import com.nexon.core.requestpostman.request.NXToyPushRequest;
import com.nexon.core.requestpostman.request.NXToyRequest;
import com.nexon.core.requestpostman.request.NXToyStoreRequest;
import com.nexon.core.requestpostman.result.NXToyCreateTokenResult;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.util.NXCrypto.NXCrypto;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXNetworkUtil;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kr.co.nexon.toy.android.ui.backup.NPDataBackupDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyRequestPostman {
    public final int MAX_RETRIES_COUNT = 3;
    public final int TIME_OUT_MILLISECONDS = 30000;
    private RequestQueue requestQueue;

    private static class Singleton {
        private static final NXToyRequestPostman instance = new NXToyRequestPostman();

        private Singleton() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMessageBody(NXToyRequest nXToyRequest) {
        if (nXToyRequest != null) {
            nXToyRequest.setMessageBody(new byte[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NXToyResult convertSuccessResponseToToyResult(String str, Class<? extends NXToyResult> cls) {
        if (cls == null) {
            NXToyResult nXToyResultMakeErrorResult = NXNetworkUtil.makeErrorResult(NXToyErrorCode.PARSE_ERR3.getCode(), NXToyRequestPostman.class.getSimpleName() + " fail to convert result. ResultClass is null, parsing fail", "Response parsing fail", cls);
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult);
            return nXToyResultMakeErrorResult;
        }
        if (str == null || str.length() == 0) {
            return NXNetworkUtil.makeErrorResult(NXToyErrorCode.SUCCESS.getCode(), "Success", "Success", cls);
        }
        try {
            return (NXToyResult) NXJsonUtil.fromObject(str, (Class) cls);
        } catch (Exception e) {
            if (str == null || str.length() <= 0) {
                NXToyResult nXToyResultMakeErrorResult2 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.PARSE_ERR2.getCode(), NXToyRequestPostman.class.getSimpleName() + " fail to convert result. Response parsing fail", "Response parsing fail", cls);
                NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult2);
                return nXToyResultMakeErrorResult2;
            }
            NXToyResult nXToyResultMakeErrorResult3 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.PARSE_ERR1.getCode(), NXToyRequestPostman.class.getSimpleName() + " fail to convert result. Can not convert response to result class", str, cls);
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult3);
            return nXToyResultMakeErrorResult3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NXToyResult convertVolleyErrorToToyResult(VolleyError volleyError, NXToyRequest nXToyRequest) {
        NXToyResult nXToyResultMakeErrorResult;
        if (volleyError == null || nXToyRequest == null) {
            ToyLog.d("VolleyError is null or request is null");
            NXToyResult nXToyResultMakeErrorResult2 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.UNKNOWN_ERROR.getCode(), "VolleyError or request is null.", NXToyRequestPostman.class.getSimpleName() + "'s VolleyError or request is null.", nXToyRequest.getResultClass());
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult2);
            return nXToyResultMakeErrorResult2;
        }
        if (volleyError.getCause() instanceof UnknownHostException) {
            UnknownHostException unknownHostException = (UnknownHostException) volleyError.getCause();
            ToyLog.d("Catch Network unavailable exception, Please check your network environment. " + unknownHostException.getMessage());
            NXToyResult nXToyResultMakeErrorResult3 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.NETWORK_UNAVAILABLE.getCode(), "Network unavailable.", NXToyRequestPostman.class.getSimpleName() + " catch unknown host exception, Please check your network environment. : " + unknownHostException.getMessage(), nXToyRequest.getResultClass());
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult3);
            return nXToyResultMakeErrorResult3;
        }
        if ((volleyError.getCause() instanceof ConnectException) || (volleyError instanceof NoConnectionError)) {
            ToyLog.d("Catch Connect exception, Please check Toy Server environment. " + volleyError.getMessage());
            NXToyResult nXToyResultMakeErrorResult4 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.NETWORK_CONNECTION_LOST.getCode(), "Network connection exception.", NXToyRequestPostman.class.getSimpleName() + " catch Connection exception, Please check Toy Server environment.: " + volleyError, nXToyRequest.getResultClass());
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult4);
            return nXToyResultMakeErrorResult4;
        }
        if (volleyError instanceof TimeoutError) {
            ToyLog.d("Catch Network time out, Please check your network environment. " + volleyError.getMessage());
            NXToyResult nXToyResultMakeErrorResult5 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.NETWORK_TIMEOUT.getCode(), "Network time out.", NXToyRequestPostman.class.getSimpleName() + " catch Network time out exception, Please check your network environment. : " + volleyError, nXToyRequest.getResultClass());
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult5);
            return nXToyResultMakeErrorResult5;
        }
        if (!(volleyError instanceof ServerError)) {
            if (!(volleyError instanceof ParseError)) {
                ToyLog.e("Fail to convert error result. UnknownError.");
                return NXNetworkUtil.makeErrorResult(NXToyErrorCode.UNKNOWN_ERROR.getCode(), "Fail to convert errorResponse ", nXToyRequest.getResultClass() + " fail to convert error result. UnknownError.", nXToyRequest.getResultClass());
            }
            NXToyResult nXToyResultMakeErrorResult6 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.PARSE_ERR1.getCode(), "Fail to parsing ErrorResponse.", NXToyRequestPostman.class.getSimpleName() + " fail to parsing ErrorResponse. Volley's response parsingError. : " + volleyError, nXToyRequest.getResultClass());
            ToyLog.e("Fail to parsing ErrorResponse.", PlugSchemeActivity.b, nXToyResultMakeErrorResult6);
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult6);
            return nXToyResultMakeErrorResult6;
        }
        if (volleyError.networkResponse == null || volleyError == null) {
            ToyLog.d("Catch Server error, but not returns more detail info. " + volleyError.getMessage());
            NXToyResult nXToyResultMakeErrorResult7 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.INVALID_ARGS.getCode(), "Server error, but not returns more detail info.", NXToyRequestPostman.class.getSimpleName() + "catch Server error, but not returns more detail info. : " + volleyError.getMessage(), nXToyRequest.getResultClass());
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult7);
            return nXToyResultMakeErrorResult7;
        }
        String str = new String(volleyError.networkResponse.data);
        try {
            JsonElement jsonElement = new JsonParser().parse(str);
            if (jsonElement.isJsonNull() || !jsonElement.isJsonObject()) {
                ToyLog.d("Catch Server error, but Error string is null or not Json String. " + str);
                nXToyResultMakeErrorResult = NXNetworkUtil.makeErrorResult(NXToyErrorCode.PARSE_ERR1.getCode(), "Error string is null or not Json String.", NXToyRequestPostman.class.getSimpleName() + "catch Server error, but string is null or not Json String. : " + str, nXToyRequest.getResultClass());
                NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult);
            } else {
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                ToyLog.d("Server error, Json Error String : " + asJsonObject);
                int i = volleyError.networkResponse.statusCode;
                if (i < 400 || i >= 500) {
                    if (i >= 500 && i < 600) {
                        nXToyResultMakeErrorResult = NXNetworkUtil.makeErrorResult(NXToyErrorCode.TOYSERVER_INTERNAL_SERVER_ERROR.getCode(), "Internal server error.", "Request success but toy internal server error. Please Check Toy Server environment.", nXToyRequest.getResultClass());
                    } else if (i < 200 || i >= 300) {
                        nXToyResultMakeErrorResult = (i < 300 || i >= 400) ? NXNetworkUtil.makeErrorResult(NXToyErrorCode.INVALID_ARGS.getCode(), nXToyRequest.getResultClass() + "Unknown error", "Please Check toy's request postman.", nXToyRequest.getResultClass()) : NXNetworkUtil.makeErrorResult(NXToyErrorCode.NEED_ADDITIONAL_ACTION.getCode(), "Need to additional action.", "Request success but need to additional action. Please Check Toy's request postman.", nXToyRequest.getResultClass());
                    } else {
                        nXToyResultMakeErrorResult = NXNetworkUtil.makeErrorResult(NXToyErrorCode.SUCCESS.getCode(), "Need to redirection.", "Request success but need to redirection. Please Check Toy's request postman.", nXToyRequest.getResultClass());
                    }
                } else if (nXToyRequest instanceof NXToyPushRequest) {
                    nXToyResultMakeErrorResult = NXNetworkUtil.makeErrorResult(asJsonObject.get(NxLogInfo.KEY_ERROR_CODE).getAsInt(), asJsonObject.get("error").getAsString(), asJsonObject.get(NPDataBackupDialog.KEY_ERROR_DESCRIPTOIN).getAsString(), nXToyRequest.getResultClass());
                } else if (nXToyRequest instanceof NXToyBoltRequest) {
                    nXToyResultMakeErrorResult = NXNetworkUtil.makeErrorResult(asJsonObject.get(NxLogInfo.KEY_ERROR_CODE).getAsInt(), asJsonObject.get("errorText").getAsString(), asJsonObject.get("errorDetail").getAsString(), nXToyRequest.getResultClass());
                } else {
                    nXToyResultMakeErrorResult = nXToyRequest instanceof NXToyStoreRequest ? NXNetworkUtil.makeErrorResult(asJsonObject.get(NativeProtocol.BRIDGE_ARG_ERROR_CODE).getAsInt(), asJsonObject.get("error").getAsString(), asJsonObject.get(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION).getAsString(), nXToyRequest.getResultClass()) : NXNetworkUtil.makeErrorResult(NXToyErrorCode.UNKNOWN_ERROR.getCode(), "Unknown request type.", NXToyRequestPostman.class.getSimpleName() + " can not make error result, due to Unknown request type.", nXToyRequest.getResultClass());
                }
                NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult);
                ToyLog.e("Convert Error response to error Result, HttpStatusCode : " + i + ", Error result is : \n " + nXToyResultMakeErrorResult.toString());
            }
            return nXToyResultMakeErrorResult;
        } catch (JsonParseException e) {
            ToyLog.e("Catch Server error, but string is not Json String. " + str);
            NXToyResult nXToyResultMakeErrorResult8 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.PARSE_ERR1.getCode(), "Error string is not Json String.", NXToyRequestPostman.class.getSimpleName() + "catch Server error, but Error string is not Json String. : " + str, nXToyRequest.getResultClass());
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult8);
            return nXToyResultMakeErrorResult8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String decryptResponse(byte[] bArr, NXToyCryptoType nXToyCryptoType, long j) throws Exception {
        ToyLog.d("Decrypt response, using decrypt npsn key is : " + j);
        return new String(new NXCrypto().decrypt(bArr, nXToyCryptoType, j));
    }

    public static NXToyRequestPostman getInstance() {
        return Singleton.instance;
    }

    public void initialize(Context context) {
        if (this.requestQueue == null) {
            this.requestQueue = Volley.newRequestQueue(context);
        }
    }

    public void postRequest(final NXToyRequest nXToyRequest, final NXToyRequestListener nXToyRequestListener) {
        Response.ErrorListener errorListener = new Response.ErrorListener() { // from class: com.nexon.core.requestpostman.NXToyRequestPostman.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                NXToyResult nXToyResultConvertVolleyErrorToToyResult = NXToyRequestPostman.this.convertVolleyErrorToToyResult(volleyError, nXToyRequest);
                NXToyRequestPostman.this.clearMessageBody(nXToyRequest);
                if (nXToyRequestListener != null) {
                    nXToyRequestListener.onComplete(nXToyResultConvertVolleyErrorToToyResult);
                }
            }
        };
        NXToyByteArrayRequest nXToyByteArrayRequest = new NXToyByteArrayRequest(nXToyRequest.getMethod().getValue(), nXToyRequest.getHttpURL(), new Response.Listener<byte[]>() { // from class: com.nexon.core.requestpostman.NXToyRequestPostman.3
            @Override // com.android.volley.Response.Listener
            public void onResponse(byte[] bArr) {
                NXToyResult nXToyResultConvertSuccessResponseToToyResult;
                if (nXToyRequest instanceof NXToyBoltRequest) {
                    NXToyBoltRequest nXToyBoltRequest = (NXToyBoltRequest) nXToyRequest;
                    try {
                        long cryptoKey = ((NXToyBoltRequest) nXToyRequest).getCryptoKey();
                        if (cryptoKey < 1) {
                            cryptoKey = NXToySessionManager.getInstance().getSession().getNpsn();
                        }
                        String strDecryptResponse = NXToyRequestPostman.this.decryptResponse(bArr, nXToyBoltRequest.getDecryptType(), cryptoKey);
                        ToyLog.d("Decrypt result : " + strDecryptResponse);
                        nXToyResultConvertSuccessResponseToToyResult = NXToyRequestPostman.this.convertSuccessResponseToToyResult(strDecryptResponse, nXToyBoltRequest.getResultClass());
                        if (nXToyResultConvertSuccessResponseToToyResult.errorCode == NXToyErrorCode.TOYSERVER_TOKEN_EXPIRED.getCode()) {
                            ToyLog.d("Refresh token due to token expired.");
                            NXToyRequestPostman.this.postRequest(new NXToyCreateNPTokenRequest(), new NXToyRequestListener() { // from class: com.nexon.core.requestpostman.NXToyRequestPostman.3.1
                                @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
                                public void onComplete(NXToyResult nXToyResult) {
                                    if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                                        String str = ((NXToyCreateTokenResult) nXToyResult).result.npToken;
                                        ToyLog.d("Refresh token success : " + str);
                                        NXToySessionManager nXToySessionManager = NXToySessionManager.getInstance();
                                        nXToySessionManager.getSession().setNPToken(str);
                                        nXToySessionManager.saveSession();
                                        ((NXToyBoltRequest) nXToyRequest).setRequestHeader(nXToySessionManager.getSession());
                                        NXToyRequestPostman.this.postRequest(nXToyRequest, nXToyRequestListener);
                                        return;
                                    }
                                    ToyLog.e("Fail to refresh token.");
                                    NXToyResult nXToyResultMakeErrorResult = NXNetworkUtil.makeErrorResult(NXToyErrorCode.INVALID_NETWORK.getCode(), "Token refresh fail.", "Token refresh fail.", nXToyRequest.getResultClass());
                                    NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult);
                                    NXToyRequestPostman.this.clearMessageBody(nXToyRequest);
                                    if (nXToyRequestListener != null) {
                                        nXToyRequestListener.onComplete(nXToyResultMakeErrorResult);
                                    }
                                }
                            });
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToyLog.d("Decrypt fail, and catch exception. message is : " + e.getMessage());
                        NXToyResult nXToyResultMakeErrorResult = NXNetworkUtil.makeErrorResult(NXToyErrorCode.PARSE_ERR5.getCode(), " fail to decrypt.", NXToyRequestPostman.class.getSimpleName() + " decrypt fail, and catch exception. message is : " + e.getMessage(), nXToyBoltRequest.getResultClass());
                        NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult);
                        NXToyRequestPostman.this.clearMessageBody(nXToyRequest);
                        if (nXToyRequestListener != null) {
                            nXToyRequestListener.onComplete(nXToyResultMakeErrorResult);
                            return;
                        }
                        return;
                    }
                } else {
                    nXToyResultConvertSuccessResponseToToyResult = NXToyRequestPostman.this.convertSuccessResponseToToyResult(new String(bArr), nXToyRequest.getResultClass());
                }
                ToyLog.d("Post " + nXToyRequest.getClass().getSimpleName() + " and receive success result : " + nXToyResultConvertSuccessResponseToToyResult);
                NXToyRequestPostman.this.clearMessageBody(nXToyRequest);
                if (nXToyRequestListener != null) {
                    nXToyRequestListener.onComplete(nXToyResultConvertSuccessResponseToToyResult);
                }
            }
        }, errorListener) { // from class: com.nexon.core.requestpostman.NXToyRequestPostman.4
            @Override // com.android.volley.Request
            public byte[] getBody() {
                return nXToyRequest.getMessageBody();
            }

            @Override // com.android.volley.Request
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> messageHeader = nXToyRequest.getMessageHeader();
                ToyLog.d("Request header : " + messageHeader.toString());
                return messageHeader;
            }
        };
        nXToyByteArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, 1.0f));
        this.requestQueue.add(nXToyByteArrayRequest);
    }

    public NXToyResult postSyncRequest(final NXToyRequest nXToyRequest) {
        RequestFuture requestFutureNewFuture = RequestFuture.newFuture();
        NXToyByteArrayRequest nXToyByteArrayRequest = new NXToyByteArrayRequest(nXToyRequest.getMethod().getValue(), nXToyRequest.getHttpURL(), requestFutureNewFuture, requestFutureNewFuture) { // from class: com.nexon.core.requestpostman.NXToyRequestPostman.1
            @Override // com.android.volley.Request
            public byte[] getBody() {
                return nXToyRequest.getMessageBody();
            }

            @Override // com.android.volley.Request
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> messageHeader = nXToyRequest.getMessageHeader();
                ToyLog.d("Set request header : " + messageHeader.toString());
                return messageHeader;
            }
        };
        nXToyByteArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, 1.0f));
        this.requestQueue.add(nXToyByteArrayRequest);
        try {
            byte[] bArr = (byte[]) requestFutureNewFuture.get(30000L, TimeUnit.MILLISECONDS);
            NXToyResult nXToyResultConvertSuccessResponseToToyResult = null;
            if (nXToyRequest instanceof NXToyBoltRequest) {
                try {
                    NXToyBoltRequest nXToyBoltRequest = (NXToyBoltRequest) nXToyRequest;
                    long cryptoKey = ((NXToyBoltRequest) nXToyRequest).getCryptoKey();
                    if (cryptoKey < 1) {
                        cryptoKey = NXToySessionManager.getInstance().getSession().getNpsn();
                    }
                    String strDecryptResponse = decryptResponse(bArr, nXToyBoltRequest.getDecryptType(), cryptoKey);
                    ToyLog.d("Decrypt response : " + strDecryptResponse);
                    nXToyResultConvertSuccessResponseToToyResult = convertSuccessResponseToToyResult(strDecryptResponse, nXToyRequest.getResultClass());
                    if (nXToyResultConvertSuccessResponseToToyResult.errorCode == NXToyErrorCode.TOYSERVER_TOKEN_EXPIRED.getCode()) {
                        NXToyResult nXToyResultPostSyncRequest = postSyncRequest(new NXToyCreateNPTokenRequest());
                        if (nXToyResultPostSyncRequest.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                            ToyLog.e("Refresh token fail.");
                            NXToyResult nXToyResultMakeErrorResult = NXNetworkUtil.makeErrorResult(NXToyErrorCode.INVALID_NETWORK.getCode(), "Token refresh fail.", "Token refresh fail.", nXToyRequest.getResultClass());
                            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult);
                            clearMessageBody(nXToyRequest);
                            return nXToyResultMakeErrorResult;
                        }
                        String str = ((NXToyCreateTokenResult) nXToyResultPostSyncRequest).result.npToken;
                        ToyLog.d("Refresh token : " + str);
                        NXToySessionManager nXToySessionManager = NXToySessionManager.getInstance();
                        nXToySessionManager.getSession().setNPToken(str);
                        nXToySessionManager.saveSession();
                        ((NXToyBoltRequest) nXToyRequest).setRequestHeader(nXToySessionManager.getSession());
                        return postSyncRequest(nXToyRequest);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToyLog.d("Catch exception. during decryption. message : " + e.getMessage());
                    NXToyResult nXToyResultMakeErrorResult2 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.PARSE_ERR1.getCode(), "Response decrypt fail.", NXToyRequestPostman.class.getSimpleName() + " fail to decrypt, message is " + e.getMessage(), nXToyRequest.getResultClass());
                    NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult2);
                    clearMessageBody(nXToyRequest);
                    return nXToyResultMakeErrorResult2;
                }
            } else if ((nXToyRequest instanceof NXToyPushRequest) || (nXToyRequest instanceof NXToyStoreRequest)) {
                nXToyResultConvertSuccessResponseToToyResult = convertSuccessResponseToToyResult(new String(bArr), nXToyRequest.getResultClass());
            }
            clearMessageBody(nXToyRequest);
            return nXToyResultConvertSuccessResponseToToyResult;
        } catch (InterruptedException e2) {
            ToyLog.d("Catch interrupt signal exception, but not receive response, message : " + e2.getMessage());
            NXToyResult nXToyResultMakeErrorResult3 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.NETWORK_CONNECTION_LOST.getCode(), "Catch interrupt signal exception, but not receive response.", nXToyRequest.getClass().getSimpleName() + " catch interrupt signal exception, but not receive response. fail to post " + nXToyRequest.getClass().getSimpleName() + " message is " + e2.getMessage(), nXToyRequest.getResultClass());
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult3);
            clearMessageBody(nXToyRequest);
            return nXToyResultMakeErrorResult3;
        } catch (ExecutionException e3) {
            if (e3.getCause() instanceof VolleyError) {
                NXToyResult nXToyResultConvertVolleyErrorToToyResult = convertVolleyErrorToToyResult((VolleyError) e3.getCause(), nXToyRequest);
                clearMessageBody(nXToyRequest);
                return nXToyResultConvertVolleyErrorToToyResult;
            }
            ToyLog.d("Catch exception. message is " + e3.getMessage());
            NXToyResult nXToyResultMakeErrorResult4 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.NETWORK_CONNECTION_LOST.getCode(), "Fail to post request.", NXToyRequestPostman.class.getSimpleName() + " fail to post " + nXToyRequest.getClass().getSimpleName() + " message is " + e3.getMessage(), nXToyRequest.getResultClass());
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult4);
            clearMessageBody(nXToyRequest);
            return nXToyResultMakeErrorResult4;
        } catch (TimeoutException e4) {
            ToyLog.d("Catch Network time out, Please check your network environment. ");
            NXToyResult nXToyResultMakeErrorResult5 = NXNetworkUtil.makeErrorResult(NXToyErrorCode.NETWORK_TIMEOUT.getCode(), "Network time out.", NXToyRequestPostman.class.getSimpleName() + " catch Network time out exception, Please check your network environment. : " + e4, nXToyRequest.getResultClass());
            NXNetworkUtil.convertLocalizedText(NXToyCommonPreferenceController.getInstance().getLocale(), nXToyResultMakeErrorResult5);
            return nXToyResultMakeErrorResult5;
        }
    }
}
