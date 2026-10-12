package com.naver.glink.android.sdk.login;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.login.neoid.NeoIdSdkManager;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.data.OAuthLoginState;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class LoginHelper {
    private static com.naver.glink.android.sdk.login.a.b a = new com.naver.glink.android.sdk.login.a.b() { // from class: com.naver.glink.android.sdk.login.LoginHelper.1
        @Override // com.naver.glink.android.sdk.login.a.b
        public com.naver.glink.android.sdk.login.a.b a(boolean z) {
            return this;
        }

        @Override // com.naver.glink.android.sdk.login.a.b
        public void a() {
        }

        @Override // com.naver.glink.android.sdk.login.a.b
        public com.naver.glink.android.sdk.login.a.b b(String str, AlertDialogFragmentView.c cVar) {
            return this;
        }
    };

    public enum LoginType implements a {
        NAVER { // from class: com.naver.glink.android.sdk.login.LoginHelper.LoginType.1
            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public com.naver.glink.android.sdk.login.a.b builder(Context context, Glink.OnLoggedInListener onLoggedInListener) {
                return context == null ? LoginHelper.a : new com.naver.glink.android.sdk.login.b(onLoggedInListener);
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public String getAccessToken() {
                Context contextR = d.r();
                if (contextR == null) {
                    return null;
                }
                return OAuthLogin.getInstance().getAccessToken(contextR);
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public void init(Context context) {
                if (context == null || !OAuthLoginState.NEED_INIT.equals(OAuthLogin.getInstance().getState(context))) {
                    return;
                }
                OAuthLogin.getInstance().init(context, d.b().d.b, d.b().d.c, "glink");
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public boolean isLogin(Context context) {
                init(context);
                return context != null && OAuthLoginState.OK == OAuthLogin.getInstance().getState(context);
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public boolean isNeedRefreshToken(Context context) {
                init(context);
                return context != null && OAuthLoginState.NEED_REFRESH_TOKEN == OAuthLogin.getInstance().getState(context);
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public void login(Context context, Glink.OnLoggedInListener onLoggedInListener) {
                builder(context, onLoggedInListener).a();
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public void logout(Context context) {
                if (context != null) {
                    init(context);
                    OAuthLogin.getInstance().logout(context);
                    LoginHelper.b(context);
                    p.c(context, (String) null);
                    p.d(context, (String) null);
                    com.naver.glink.android.sdk.api.requests.a.d();
                    com.naver.glink.android.sdk.a.a.b.c(new b(false));
                }
            }
        },
        NEO_ID { // from class: com.naver.glink.android.sdk.login.LoginHelper.LoginType.2
            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public com.naver.glink.android.sdk.login.a.b builder(Context context, Glink.OnLoggedInListener onLoggedInListener) {
                return context == null ? LoginHelper.a : new c(onLoggedInListener);
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public String getAccessToken() {
                return NeoIdSdkManager.d();
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public void init(Context context) {
                if (context == null || NeoIdSdkManager.NeoIdTokenState.NEED_INIT != NeoIdSdkManager.c()) {
                    return;
                }
                NeoIdSdkManager.a(context);
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public boolean isLogin(Context context) {
                init(context);
                return context != null && NeoIdSdkManager.NeoIdTokenState.OK == NeoIdSdkManager.c();
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public boolean isNeedRefreshToken(Context context) {
                return false;
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public void login(Context context, Glink.OnLoggedInListener onLoggedInListener) {
                builder(context, onLoggedInListener).a();
            }

            @Override // com.naver.glink.android.sdk.login.LoginHelper.a
            public void logout(Context context) {
                if (context != null) {
                    init(context);
                    NeoIdSdkManager.e();
                    com.naver.glink.android.sdk.api.requests.c.c().execute(context, null);
                    LoginHelper.b(context);
                    p.c(context, (String) null);
                    p.d(context, (String) null);
                    com.naver.glink.android.sdk.api.requests.a.d();
                    com.naver.glink.android.sdk.a.a.b.c(new b(false));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public interface a {
        com.naver.glink.android.sdk.login.a.b builder(Context context, Glink.OnLoggedInListener onLoggedInListener);

        String getAccessToken();

        void init(Context context);

        boolean isLogin(Context context);

        boolean isNeedRefreshToken(Context context);

        void login(Context context, Glink.OnLoggedInListener onLoggedInListener);

        void logout(Context context);
    }

    public static class b {
        public boolean a;

        public b(boolean z) {
            this.a = z;
        }
    }

    public static LoginType a() {
        return a(d.k());
    }

    public static LoginType a(boolean z) {
        return z ? LoginType.NAVER : LoginType.NEO_ID;
    }

    public static void a(Context context, Glink.OnLoggedInListener onLoggedInListener) {
        if (a().isNeedRefreshToken(context)) {
            a().builder(context, onLoggedInListener).a(false).a();
        } else if (onLoggedInListener != null) {
            onLoggedInListener.onLoggedIn(true);
        }
    }

    public static String b() {
        return d.c().plugNeoIdServiceId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context) {
        if (Build.VERSION.SDK_INT >= 22) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
            return;
        }
        CookieSyncManager cookieSyncManagerCreateInstance = CookieSyncManager.createInstance(context);
        cookieSyncManagerCreateInstance.startSync();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        cookieManager.removeSessionCookie();
        cookieSyncManagerCreateInstance.stopSync();
        cookieSyncManagerCreateInstance.sync();
    }

    public static String c() {
        return d.c().plugNeoIdServiceKey;
    }
}
