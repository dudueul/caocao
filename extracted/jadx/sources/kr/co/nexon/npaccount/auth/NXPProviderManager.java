package kr.co.nexon.npaccount.auth;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyLoginType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.daum.NPDaum;
import kr.co.nexon.android.sns.email.NPEmail;
import kr.co.nexon.android.sns.facebook.NPFacebook;
import kr.co.nexon.android.sns.google.NPGoogleGame;
import kr.co.nexon.android.sns.google.NPGoogleSignIn;
import kr.co.nexon.android.sns.naver.NPNaverChannel;
import kr.co.nexon.android.sns.nxcom.NPNexonCom;
import kr.co.nexon.android.sns.nxnet.NPNexonNet;
import kr.co.nexon.android.sns.twitter.NPTwitter;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPProviderManager {
    private NPFacebook facebookProvider;
    private NPGoogleGame googleGameProvider;
    private Map<Integer, NPAuthPlugin> providerMap = new HashMap();
    private NPTwitter twitterProvider;

    private class InitializationTask implements Runnable {
        private Activity activity;

        public InitializationTask(Activity activity) {
            this.activity = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Class.forName("com.facebook.FacebookSdk");
                NXPProviderManager.this.facebookProvider = new NPFacebook(this.activity);
                NXPProviderManager.this.providerMap.put(Integer.valueOf(NXToyLoginType.LoginTypeFaceBook.getValue()), NXPProviderManager.this.facebookProvider);
            } catch (Exception e) {
                ToyLog.d("facebook sdk not found :" + e);
            }
            try {
                Class.forName("twitter4j.Twitter");
                NXPProviderManager.this.twitterProvider = new NPTwitter(this.activity);
                NXPProviderManager.this.providerMap.put(Integer.valueOf(NXToyLoginType.LoginTypeTwitter.getValue()), NXPProviderManager.this.twitterProvider);
            } catch (Exception e2) {
                ToyLog.d("twitter sdk not found :" + e2);
            }
            try {
                Class.forName("com.google.android.gms.common.api.GoogleApiClient");
                NXPProviderManager.this.googleGameProvider = new NPGoogleGame(this.activity);
                NXPProviderManager.this.providerMap.put(Integer.valueOf(NXToyLoginType.LoginTypeGameCenter.getValue()), NXPProviderManager.this.googleGameProvider);
            } catch (Exception e3) {
                ToyLog.d("GoogleApiClient not found :" + e3);
            }
            try {
                Class.forName("com.google.android.gms.common.api.GoogleApiClient");
                NXPProviderManager.this.providerMap.put(Integer.valueOf(NXToyLoginType.LoginTypeGoogle.getValue()), new NPGoogleSignIn(this.activity));
            } catch (Exception e4) {
                ToyLog.d("GoogleApiClient not found :" + e4);
            }
            try {
                Class.forName("com.nhn.android.naverlogin.OAuthLogin");
                NPNaverChannel nPNaverChannel = new NPNaverChannel(this.activity);
                NXPProviderManager.this.providerMap.put(Integer.valueOf(NXToyLoginType.LoginTypeNaverChannel.getValue()), nPNaverChannel);
                NXPProviderManager.this.providerMap.put(Integer.valueOf(NXToyLoginType.LoginTypeNaver.getValue()), nPNaverChannel);
            } catch (Exception e5) {
                ToyLog.d("naver sdk not found :" + e5);
            }
            try {
                Class.forName("net.daum.mf.oauth.impl.OAuthUtils");
                NXPProviderManager.this.providerMap.put(Integer.valueOf(NXToyLoginType.LoginTypeDaumChannel.getValue()), new NPDaum(this.activity));
            } catch (Exception e6) {
                ToyLog.d("daum sdk not found :" + e6);
            }
            try {
                NXPProviderManager.this.providerMap.put(Integer.valueOf(NXToyLoginType.LoginTypeVKontakte.getValue()), (NPAuthPlugin) Class.forName("com.nexon.platform.auth.NXPVKontakte").getDeclaredConstructor(Context.class).newInstance(this.activity));
            } catch (Exception e7) {
                ToyLog.d("VKontakte sdk not found :" + e7);
            }
            NXPProviderManager.this.providerMap.put(Integer.valueOf(NXToyLoginType.LoginTypeNXCom.getValue()), new NPNexonCom(this.activity));
            NXPProviderManager.this.providerMap.put(Integer.valueOf(NXToyLoginType.LoginTypeEmail.getValue()), new NPEmail(this.activity));
            NXPProviderManager.this.providerMap.put(Integer.valueOf(NXToyLoginType.LoginTypeNXNet.getValue()), new NPNexonNet(this.activity));
            this.activity = null;
        }
    }

    private static class Singleton {
        private static final NXPProviderManager instance = new NXPProviderManager();

        private Singleton() {
        }
    }

    public static NXPProviderManager getInstance() {
        return Singleton.instance;
    }

    @NonNull
    public List<NPAuthPlugin> getAllSNSList() {
        ArrayList arrayList = new ArrayList();
        if (this.facebookProvider != null) {
            arrayList.add(this.facebookProvider);
        }
        if (this.twitterProvider != null) {
            arrayList.add(this.twitterProvider);
        }
        return arrayList;
    }

    @Nullable
    public NPFacebook getFacebookProvider() {
        return this.facebookProvider;
    }

    @Nullable
    public NPGoogleGame getGoogleGameProvider() {
        return this.googleGameProvider;
    }

    @Nullable
    public NPAuthPlugin getProvider(int i) {
        return this.providerMap.get(Integer.valueOf(i));
    }

    @Nullable
    public NPTwitter getTwitterProvider() {
        return this.twitterProvider;
    }

    public void initialize(@NonNull Activity activity) {
        activity.runOnUiThread(new InitializationTask(activity));
    }
}
