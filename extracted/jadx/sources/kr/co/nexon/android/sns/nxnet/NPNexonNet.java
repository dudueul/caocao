package kr.co.nexon.android.sns.nxnet;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.util.NXStringUtil;
import java.util.Map;
import kr.co.nexon.android.sns.NPAuthFriendsListener;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.nxnet.api.request.NPNXNetGetProfileNameRequest;
import kr.co.nexon.android.sns.nxnet.api.request.NPNXNetGetTokenRequest;
import kr.co.nexon.android.sns.nxnet.api.request.NPNXNetGetUserIdRequest;
import kr.co.nexon.android.sns.nxnet.api.request.NPNXNetLogoutRequest;
import kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRefreshTokenRequest;
import kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequestListener;
import kr.co.nexon.android.sns.nxnet.api.request.NPNXNetValidateTokenRequest;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetGetProfileNameResult;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetGetTokenResult;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetGetUserIdResult;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetRefreshTokenResult;
import kr.co.nexon.android.sns.nxnet.api.result.NPNXNetResult;
import kr.co.nexon.android.sns.nxnet.session.NPNXNetSession;
import kr.co.nexon.android.sns.nxnet.session.NPNXNetSessionManager;
import kr.co.nexon.android.sns.nxnet.ui.NPNXNetAuthDialog;
import kr.co.nexon.mdev.android.view.NXProgressDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPNexonNet extends NPAuthPlugin {
    public static final String CODE_PREF_KEY_PRODUCT_ID = "nexonNetProductId";
    public static final String CODE_PREF_KEY_REDIRECT_URI = "nexonNetRedirectUri";
    public static final String CODE_PREF_KEY_SECRET_KEY = "nexonNetSecretKey";
    public static final String CODE_TICKET = "ticket";
    public static final int DATE_SECOND = 1000;
    public static final String SERVICE_NAME = "nexonNet";
    private static String ticket;
    private NPNXNetAuthDialog authDialog;
    private NXProgressDialog progressDialog;

    public NPNexonNet(Context context) {
        super(context);
        init(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getNXNetAccessToken(Context context, final NPNXNetRequestListener nPNXNetRequestListener) {
        final NPNXNetSessionManager nPNXNetSessionManager = NPNXNetSessionManager.getInstance(context);
        final NPNXNetSession session = nPNXNetSessionManager.getSession();
        NPNXNetInfo nXNetInfo = getNXNetInfo();
        if (NXStringUtil.isNull(ticket) || !existInfo(nXNetInfo)) {
            ToyLog.d("NXMP SDK : Nexon.net Ticket or nxNetInfo is null");
            nPNXNetRequestListener.onComplete(new NPNXNetResult(NXToyErrorCode.NXNET_INVALID_CLIENTID_OR_SECRET_KEY.getCode(), "Product id, sercret key or ticket is null", ""));
            return;
        }
        nXNetInfo.setTicket(ticket);
        NPNXNetGetTokenRequest nPNXNetGetTokenRequest = new NPNXNetGetTokenRequest(context, session);
        nPNXNetGetTokenRequest.setNxNetInfo(nXNetInfo);
        nPNXNetGetTokenRequest.setListener(new NPNXNetRequestListener() { // from class: kr.co.nexon.android.sns.nxnet.NPNexonNet.5
            @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequestListener
            public void onComplete(NPNXNetResult nPNXNetResult) {
                ToyLog.d("NXMP SDK : Neonx.net GetToken : " + nPNXNetResult.toString());
                if (nPNXNetResult.error_code == NXToyErrorCode.SUCCESS.getCode()) {
                    NPNXNetGetTokenResult nPNXNetGetTokenResult = (NPNXNetGetTokenResult) nPNXNetResult;
                    session.setAccessToken(nPNXNetGetTokenResult.token);
                    session.setRefreshToken(nPNXNetGetTokenResult.refresh_token);
                    session.setTokenExpires(nPNXNetGetTokenResult.expires_in);
                    nPNXNetSessionManager.setSession(session);
                }
                if (nPNXNetRequestListener != null) {
                    nPNXNetRequestListener.onComplete(nPNXNetResult);
                }
            }
        });
        nPNXNetGetTokenRequest.execAsync();
    }

    public static int getNXNetErrorCode(int i) {
        switch (i) {
            case 0:
                return NXToyErrorCode.NXNET_AUTH_FAIL.getCode();
            case 1:
                return NXToyErrorCode.NXNET_INVALID_METHOD.getCode();
            case 2:
                return NXToyErrorCode.NXNET_INVALID_ENDPOINT.getCode();
            case 3:
                return NXToyErrorCode.NXNET_INVALID_ACCESS.getCode();
            case 4:
                return NXToyErrorCode.NXNET_INVALID_JSON_FORMAT.getCode();
            case 5:
            case 6:
                return NXToyErrorCode.NXNET_INVALID_OR_MISSING_PARAMETER.getCode();
            case 7:
                return NXToyErrorCode.NXNET_INVALID_ACCESS_IP.getCode();
            case 21:
                return NXToyErrorCode.NXNET_INVALID_USER_ID.getCode();
            case 22:
            case 25:
                return NXToyErrorCode.NXNET_INVALID_CLIENTID_OR_SECRET_KEY.getCode();
            case 27:
                return NXToyErrorCode.NXNET_INVALID_REQUEST_HEADER.getCode();
            case 51:
                return NXToyErrorCode.NXNET_TOKEN_REQUIRED.getCode();
            case 52:
                return NXToyErrorCode.NXNET_INVALID_ACCESS.getCode();
            case 53:
                return NXToyErrorCode.NXNET_SCOPE_RESTRICTION.getCode();
            case 54:
                return NXToyErrorCode.NXNET_ACCESS_LEVEL_RESTRICTION.getCode();
            case 55:
                return NXToyErrorCode.NXNET_INVALID_TICKET.getCode();
            case 993:
            case 999:
                return NXToyErrorCode.NXNET_SERVER_OR_SYSTEM_ERROR.getCode();
            case 1501:
                return NXToyErrorCode.NXNET_EMAIL_VERIFICATION_REQUIRED.getCode();
            case 1550:
                return NXToyErrorCode.NXNET_EXPIRE_ACCESS_TOKEN.getCode();
            case 1570:
                return NXToyErrorCode.NXNET_ALREADY_LOGGED_IN_DIFFERENT_IP.getCode();
            case 1599:
                return NXToyErrorCode.NXNET_UNKNOWN.getCode();
            default:
                return i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getNXNetProfileName(Context context, final NPNXNetRequestListener nPNXNetRequestListener) {
        NPNXNetGetProfileNameRequest nPNXNetGetProfileNameRequest = new NPNXNetGetProfileNameRequest(context, NPNXNetSessionManager.getInstance(context).getSession());
        nPNXNetGetProfileNameRequest.setListener(new NPNXNetRequestListener() { // from class: kr.co.nexon.android.sns.nxnet.NPNexonNet.6
            @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequestListener
            public void onComplete(NPNXNetResult nPNXNetResult) {
                ToyLog.d("NXMP SDK : Nexon.net GetProfilename : " + nPNXNetResult.toString());
                if (nPNXNetRequestListener != null) {
                    nPNXNetRequestListener.onComplete(nPNXNetResult);
                }
            }
        });
        nPNXNetGetProfileNameRequest.execAsync();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserId(final Context context, final NPAuthListener nPAuthListener) {
        final NPNXNetSession session = NPNXNetSessionManager.getInstance(context).getSession();
        NPNXNetGetUserIdRequest nPNXNetGetUserIdRequest = new NPNXNetGetUserIdRequest(context, session);
        nPNXNetGetUserIdRequest.setListener(new NPNXNetRequestListener() { // from class: kr.co.nexon.android.sns.nxnet.NPNexonNet.7
            @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequestListener
            public void onComplete(NPNXNetResult nPNXNetResult) {
                NPNexonNet.this.progressDialog.dismiss();
                ToyLog.d("NXMP SDK : Nexon.Net Get User Id : " + nPNXNetResult.toString());
                if (nPNXNetResult.error_code != NXToyErrorCode.SUCCESS.getCode()) {
                    NPNXNetSessionManager.getInstance(context).remove();
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(NPNexonNet.getNXNetErrorCode(nPNXNetResult.error_code), nPNXNetResult.error_description, null);
                        return;
                    }
                    return;
                }
                NPNXNetGetUserIdResult nPNXNetGetUserIdResult = (NPNXNetGetUserIdResult) nPNXNetResult;
                session.setUserId(nPNXNetGetUserIdResult.user_id);
                session.setUserNo(nPNXNetGetUserIdResult.user_no);
                if (nPAuthListener != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(NPAuthPlugin.KEY_ID, session.getUserNo());
                    bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, session.getAccessToken());
                    nPAuthListener.onResult(nPNXNetResult.error_code, nPNXNetResult.error_description, bundle);
                }
            }
        });
        nPNXNetGetUserIdRequest.execAsync();
    }

    private void init(Context context) {
        NPNXNetSessionManager nPNXNetSessionManager = NPNXNetSessionManager.getInstance(context);
        if (nPNXNetSessionManager.getSession() == null) {
            nPNXNetSessionManager.load();
        }
    }

    private boolean isNotTokenExpiration(long j) {
        return System.currentTimeMillis() / 1000 < j;
    }

    private void refreshToken(Context context, final NPNXNetRequestListener nPNXNetRequestListener) {
        final NPNXNetSessionManager nPNXNetSessionManager = NPNXNetSessionManager.getInstance(context);
        final NPNXNetSession session = nPNXNetSessionManager.getSession();
        String refreshToken = session.getRefreshToken();
        if (isNotTokenExpiration(session.getTokenExpires())) {
            ToyLog.d("NXMP SDK : Nexon.net Accesstoken is not expiration");
            if (nPNXNetRequestListener != null) {
                nPNXNetRequestListener.onComplete(new NPNXNetResult(NXToyErrorCode.SUCCESS.getCode(), "", ""));
                return;
            }
            return;
        }
        if (!NXStringUtil.isNotNull(refreshToken)) {
            ToyLog.d("NXMP SDK : Nexon.net Refreshtoken is null or empty");
            nPNXNetRequestListener.onComplete(new NPNXNetResult(NXToyErrorCode.NXNET_INVALID_REFRESH_TOKEN.getCode(), "Refreshtoken is null", ""));
            return;
        }
        NPNXNetInfo nXNetInfo = getNXNetInfo();
        NPNXNetRefreshTokenRequest nPNXNetRefreshTokenRequest = new NPNXNetRefreshTokenRequest(context, session);
        nPNXNetRefreshTokenRequest.setNxNetInfo(nXNetInfo);
        nPNXNetRefreshTokenRequest.setListener(new NPNXNetRequestListener() { // from class: kr.co.nexon.android.sns.nxnet.NPNexonNet.8
            @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequestListener
            public void onComplete(NPNXNetResult nPNXNetResult) {
                ToyLog.d("NXMP SDK : Nexon.net Get Refreshtoken : " + nPNXNetResult.toString());
                if (nPNXNetResult.error_code == NXToyErrorCode.SUCCESS.getCode()) {
                    NPNXNetRefreshTokenResult nPNXNetRefreshTokenResult = (NPNXNetRefreshTokenResult) nPNXNetResult;
                    session.setAccessToken(nPNXNetRefreshTokenResult.token);
                    session.setRefreshToken(nPNXNetRefreshTokenResult.refresh_token);
                    session.setTokenExpires(nPNXNetRefreshTokenResult.expires_in);
                    nPNXNetSessionManager.setSession(session);
                }
                if (nPNXNetRequestListener != null) {
                    nPNXNetRequestListener.onComplete(nPNXNetResult);
                }
            }
        });
        nPNXNetRefreshTokenRequest.execAsync();
    }

    public boolean existInfo(NPNXNetInfo nPNXNetInfo) {
        return NXStringUtil.isNotNull(nPNXNetInfo.getProductId()) && NXStringUtil.isNotNull(nPNXNetInfo.getSecretKey()) && NXStringUtil.isNotNull(nPNXNetInfo.getRedirectUri());
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getAccessToken(Context context, NPAuthListener nPAuthListener) {
        String accessToken = NPNXNetSessionManager.getInstance(context).getSession().getAccessToken();
        if (NXStringUtil.isNull(accessToken)) {
            ToyLog.d("NXMP SDK : Nexon.net AccessToken is null");
            NPNXNetSessionManager.getInstance(context).remove();
            nPAuthListener.onResult(NXToyErrorCode.NXNET_INVALID_ACCESS_TOKEN.getCode(), "Accesstoeken is null", null);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(NPAuthPlugin.KEY_SNS_NAME, "NXNet");
            bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, accessToken);
            nPAuthListener.onResult(NXToyErrorCode.SUCCESS.getCode(), "", bundle);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getFriends(Context context, boolean z, NPAuthFriendsListener nPAuthFriendsListener) {
        if (nPAuthFriendsListener != null) {
            nPAuthFriendsListener.onResult(NPAuthPlugin.CODE_NOT_SUPPORT, "Do not support", false, null);
        }
    }

    public NPNXNetInfo getNXNetInfo() {
        NPNXNetInfo nPNXNetInfo = new NPNXNetInfo();
        nPNXNetInfo.setProductId(getPref().getString(CODE_PREF_KEY_PRODUCT_ID, ""));
        nPNXNetInfo.setSecretKey(getPref().getString(CODE_PREF_KEY_SECRET_KEY, ""));
        nPNXNetInfo.setRedirectUri(getPref().getString(CODE_PREF_KEY_REDIRECT_URI, ""));
        return nPNXNetInfo;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getUserInfo(final Context context, final NPAuthListener nPAuthListener) {
        init(context);
        refreshToken(context, new NPNXNetRequestListener() { // from class: kr.co.nexon.android.sns.nxnet.NPNexonNet.3
            @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequestListener
            public void onComplete(NPNXNetResult nPNXNetResult) {
                if (nPNXNetResult.error_code == NXToyErrorCode.SUCCESS.getCode()) {
                    NPNexonNet.this.getNXNetProfileName(context, new NPNXNetRequestListener() { // from class: kr.co.nexon.android.sns.nxnet.NPNexonNet.3.1
                        @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequestListener
                        public void onComplete(NPNXNetResult nPNXNetResult2) {
                            ToyLog.d("NXMP SDK : Nexon.net UserInfo : " + nPNXNetResult2.toString());
                            if (nPNXNetResult2.error_code != NXToyErrorCode.SUCCESS.getCode()) {
                                NPNXNetSessionManager.getInstance(context).remove();
                                if (nPAuthListener != null) {
                                    nPAuthListener.onResult(NPNexonNet.getNXNetErrorCode(nPNXNetResult2.error_code), nPNXNetResult2.error_description, null);
                                    return;
                                }
                                return;
                            }
                            NPNXNetGetProfileNameResult nPNXNetGetProfileNameResult = (NPNXNetGetProfileNameResult) nPNXNetResult2;
                            Bundle bundle = new Bundle();
                            bundle.putString(NPAuthPlugin.KEY_ID, nPNXNetGetProfileNameResult.user_no);
                            bundle.putString(NPAuthPlugin.KEY_NAME, nPNXNetGetProfileNameResult.profile_name);
                            if (nPAuthListener != null) {
                                nPAuthListener.onResult(NXToyErrorCode.SUCCESS.getCode(), "", bundle);
                            }
                        }
                    });
                } else {
                    nPAuthListener.onResult(NPNexonNet.getNXNetErrorCode(nPNXNetResult.error_code), nPNXNetResult.error_description, null);
                }
            }
        });
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void isConnect(Context context, NPAuthListener nPAuthListener) {
        if (isConnected()) {
            if (nPAuthListener != null) {
                nPAuthListener.onResult(NXToyErrorCode.SUCCESS.getCode(), "", null);
            }
        } else if (nPAuthListener != null) {
            nPAuthListener.onResult(NXToyErrorCode.NXNET_NOT_CONNECT.getCode(), "NXNet account is not connect.", null);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public boolean isConnected() {
        return NXStringUtil.isNotNull(NPNXNetSessionManager.getInstance(this.applicationContext).getSession().getAccessToken());
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void login(final Activity activity, final NPAuthListener nPAuthListener) {
        ToyLog.d("NXMP SDK : Nexon.net Login Start");
        this.progressDialog = new NXProgressDialog(activity);
        NPNXNetInfo nXNetInfo = getNXNetInfo();
        NPNXNetSessionManager.getInstance(this.applicationContext).remove();
        if (activity == null) {
            ToyLog.d("NXMP SDK : Nexon.net Activity is null");
            if (nPAuthListener != null) {
                nPAuthListener.onResult(NXToyErrorCode.NXNET_AUTH_FAIL.getCode(), "Activity is null", null);
                return;
            }
            return;
        }
        if (existInfo(nXNetInfo)) {
            this.authDialog = new NPNXNetAuthDialog(activity, nXNetInfo);
            this.authDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: kr.co.nexon.android.sns.nxnet.NPNexonNet.1
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    ToyLog.d("NXMP SDK : Nexon.net Login Dialog onDismiss");
                    if (NPNexonNet.this.authDialog.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                        NPNexonNet.this.progressDialog.show();
                        String unused = NPNexonNet.ticket = NPNexonNet.this.authDialog.bundle.getString("ticket", "");
                        NPNexonNet.this.getNXNetAccessToken(activity, new NPNXNetRequestListener() { // from class: kr.co.nexon.android.sns.nxnet.NPNexonNet.1.1
                            @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequestListener
                            public void onComplete(NPNXNetResult nPNXNetResult) {
                                ToyLog.d("NXMP SDK : Nexon.net Get AccessToken : " + nPNXNetResult.toString());
                                if (nPNXNetResult.error_code == NXToyErrorCode.SUCCESS.getCode()) {
                                    NPNexonNet.this.getUserId(activity, nPAuthListener);
                                    return;
                                }
                                NPNexonNet.this.progressDialog.dismiss();
                                NPNXNetSessionManager.getInstance(NPNexonNet.this.applicationContext).remove();
                                nPAuthListener.onResult(NPNexonNet.getNXNetErrorCode(nPNXNetResult.error_code), nPNXNetResult.error_description, null);
                            }
                        });
                    } else if (nPAuthListener != null) {
                        nPAuthListener.onResult(NPNexonNet.this.authDialog.errorCode, NPNexonNet.this.authDialog.errorDetail, NPNexonNet.this.authDialog.bundle);
                    }
                }
            });
            this.authDialog.show();
        } else {
            ToyLog.d("NXMP SDK : Nexon.net Product id, secret Key or redirect uri is null");
            if (nPAuthListener != null) {
                nPAuthListener.onResult(NXToyErrorCode.NXNET_INVALID_CLIENTID_OR_SECRET_KEY.getCode(), "Product id, secret key or redirect uri is null", null);
            }
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void logout(Context context, final NPAuthListener nPAuthListener) {
        init(context);
        final NPNXNetSessionManager nPNXNetSessionManager = NPNXNetSessionManager.getInstance(context);
        if (!NXStringUtil.isNull(nPNXNetSessionManager.getSession().getAccessToken())) {
            NPNXNetLogoutRequest nPNXNetLogoutRequest = new NPNXNetLogoutRequest(context, nPNXNetSessionManager.getSession());
            nPNXNetLogoutRequest.setListener(new NPNXNetRequestListener() { // from class: kr.co.nexon.android.sns.nxnet.NPNexonNet.2
                @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequestListener
                public void onComplete(NPNXNetResult nPNXNetResult) {
                    ToyLog.d("NXMP SDK : Nexon.net Logout Result : " + nPNXNetResult.toString());
                    nPNXNetSessionManager.remove();
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(NXToyErrorCode.SUCCESS.getCode(), "", null);
                    }
                }
            });
            nPNXNetLogoutRequest.execAsync();
        } else {
            nPNXNetSessionManager.remove();
            if (nPAuthListener != null) {
                nPAuthListener.onResult(NXToyErrorCode.SUCCESS.getCode(), "", null);
            }
        }
    }

    public void setNXNetInfo(Map<String, String> map) {
        getEditor().putString(CODE_PREF_KEY_PRODUCT_ID, map.get(CODE_PREF_KEY_PRODUCT_ID)).commit();
        getEditor().putString(CODE_PREF_KEY_SECRET_KEY, map.get(CODE_PREF_KEY_SECRET_KEY)).commit();
        getEditor().putString(CODE_PREF_KEY_REDIRECT_URI, map.get(CODE_PREF_KEY_REDIRECT_URI)).commit();
    }

    public void validateToken(Context context, final NPAuthListener nPAuthListener) {
        init(context);
        NPNXNetSession session = NPNXNetSessionManager.getInstance(context).getSession();
        if (NXStringUtil.isNull(session.getAccessToken())) {
            nPAuthListener.onResult(NXToyErrorCode.NXNET_INVALID_ACCESS_TOKEN.getCode(), "Accesstoken is null", null);
            return;
        }
        NPNXNetValidateTokenRequest nPNXNetValidateTokenRequest = new NPNXNetValidateTokenRequest(context, session);
        nPNXNetValidateTokenRequest.setListener(new NPNXNetRequestListener() { // from class: kr.co.nexon.android.sns.nxnet.NPNexonNet.4
            @Override // kr.co.nexon.android.sns.nxnet.api.request.NPNXNetRequestListener
            public void onComplete(NPNXNetResult nPNXNetResult) {
                if (nPNXNetResult.error_code == 0) {
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(NXToyErrorCode.SUCCESS.getCode(), "", null);
                    }
                } else if (nPAuthListener != null) {
                    nPAuthListener.onResult(NPNexonNet.getNXNetErrorCode(nPNXNetResult.error_code), nPNXNetResult.error_message, null);
                }
            }
        });
        nPNXNetValidateTokenRequest.execAsync();
    }
}
