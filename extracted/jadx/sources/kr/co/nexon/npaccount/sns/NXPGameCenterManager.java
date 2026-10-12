package kr.co.nexon.npaccount.sns;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.constants.NXToyRequestTag;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.google.NPGoogleGame;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.NXPProviderManager;
import kr.co.nexon.npaccount.auth.result.NXToyAchievementsResult;
import kr.co.nexon.npaccount.auth.result.NXToyGooglePlayerStatsResult;
import kr.co.nexon.npaccount.auth.result.NXToyLeaderboardScoreResult;
import kr.co.nexon.npaccount.auth.result.model.NXToyGooglePlayerStats;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NXPGameCenterManager {

    private static class Singleton {
        private static final NXPGameCenterManager instance = new NXPGameCenterManager();

        private Singleton() {
        }
    }

    public static NXPGameCenterManager getInstance() {
        return Singleton.instance;
    }

    private boolean isNotConnected(NPGoogleGame nPGoogleGame) {
        return nPGoogleGame == null || !nPGoogleGame.isConnected();
    }

    public void connect(@NonNull final Activity activity, final NPListener nPListener) {
        final NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (googleGameProvider != null) {
            activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.1
                @Override // java.lang.Runnable
                public void run() {
                    googleGameProvider.login(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.1.1
                        @Override // kr.co.nexon.android.sns.NPAuthListener
                        public void onResult(int i, String str, Bundle bundle) {
                            ToyLog.d("errorCode: " + i + ", errorDetail:" + str);
                            if (nPListener != null) {
                                nPListener.onResult(new NXToyResult(i, str, str, NXToyRequestTag.ConnectGamePlatform.getValue()));
                            }
                        }
                    });
                }
            });
            return;
        }
        ToyLog.d("Unsupported type. NPGoogleGame provider not found.");
        if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NPAuthPlugin.CODE_NOT_SUPPORT, NPGoogleGame.STR_GOOGLEGAME_NOT_SUPPORTED, "", NXToyRequestTag.ConnectGamePlatform.getValue()));
        }
    }

    public void disconnect(final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (googleGameProvider != null) {
            googleGameProvider.disconnect(new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.2
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    ToyLog.d("errorCode:" + str + ", errorDetail:" + str);
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyResult(i, str, str, NXToyRequestTag.DisconnectGamePlatform.getValue()));
                    }
                }
            });
            return;
        }
        ToyLog.d("Unsupported type. NPGoogleGame provider not found.");
        if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NPAuthPlugin.CODE_NOT_SUPPORT, NPGoogleGame.STR_GOOGLEGAME_NOT_SUPPORTED, "", NXToyRequestTag.DisconnectGamePlatform.getValue()));
        }
    }

    public void getPlayerStats(boolean z, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (!isNotConnected(googleGameProvider)) {
            googleGameProvider.getPlayerStats(z, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.14
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    if (i != NXToyErrorCode.SUCCESS.getCode()) {
                        if (nPListener != null) {
                            nPListener.onResult(new NXToyResult(i, str, str, NXToyRequestTag.GetPlayerStats.getValue()));
                            return;
                        }
                        return;
                    }
                    NXToyGooglePlayerStatsResult nXToyGooglePlayerStatsResult = new NXToyGooglePlayerStatsResult(i, str, str);
                    nXToyGooglePlayerStatsResult.requestTag = NXToyRequestTag.GetPlayerStats.getValue();
                    String string = bundle.getString(NPAuthPlugin.KEY_PLAYER_STATS);
                    ToyLog.d("playerStats json:" + string);
                    if (NXStringUtil.isNotNull(string)) {
                        nXToyGooglePlayerStatsResult.result.googlePlayerStats = (NXToyGooglePlayerStats) NXJsonUtil.fromObject(string, NXToyGooglePlayerStats.class);
                    }
                    if (nPListener != null) {
                        nPListener.onResult(nXToyGooglePlayerStatsResult);
                    }
                }
            });
        } else if (nPListener != null) {
            NXToyGooglePlayerStatsResult nXToyGooglePlayerStatsResult = new NXToyGooglePlayerStatsResult(NPGoogleGame.CODE_GOOGLEGAME_NOT_CONNECTED, NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED, "");
            nXToyGooglePlayerStatsResult.requestTag = NXToyRequestTag.GetPlayerStats.getValue();
            nPListener.onResult(nXToyGooglePlayerStatsResult);
        }
    }

    public void incrementAchievement(Activity activity, String str, int i) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (isNotConnected(googleGameProvider)) {
            ToyLog.d(NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED);
        } else {
            googleGameProvider.incrementAchievement(activity, str, i);
        }
    }

    public void incrementAchievementImmediate(Activity activity, String str, int i, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (!isNotConnected(googleGameProvider)) {
            googleGameProvider.incrementAchievementImmediate(activity, str, i, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.7
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i2, String str2, Bundle bundle) {
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyResult(i2, str2, str2, NXToyRequestTag.IncrementAchievementImmediate.getValue()));
                    }
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NPGoogleGame.CODE_GOOGLEGAME_NOT_CONNECTED, NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED, "", NXToyRequestTag.IncrementAchievementImmediate.getValue()));
        }
    }

    public boolean isConnected() {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        boolean zIsConnected = googleGameProvider != null ? googleGameProvider.isConnected() : false;
        ToyLog.d("NPGoogleGame:" + googleGameProvider + ", isEnabledGamePlatform " + zIsConnected);
        return zIsConnected;
    }

    public void loadAchievementData(final Activity activity, boolean z, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (!isNotConnected(googleGameProvider)) {
            googleGameProvider.loadAchievementData(activity, z, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.8
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    NXToyAchievementsResult nXToyAchievementsResult = new NXToyAchievementsResult();
                    nXToyAchievementsResult.requestTag = NXToyRequestTag.LoadAchievementData.getValue();
                    if (i == NXToyErrorCode.SUCCESS.getCode()) {
                        nXToyAchievementsResult.result = (NXToyAchievementsResult.ResultSet) NXJsonUtil.fromObject(bundle.getString(NPAuthPlugin.KEY_ACH_LIST), NXToyAchievementsResult.ResultSet.class);
                    } else {
                        Context applicationContext = activity.getApplicationContext();
                        nXToyAchievementsResult.errorCode = NPGoogleGame.CODE_GOOGLEGAME_GAME_LOAD_ACHEIVE_FAIL;
                        nXToyAchievementsResult.errorText = NXToyLocaleManager.getInstance(applicationContext).getStringEx(R.string.npres_load_achievement_data_fail, "");
                        nXToyAchievementsResult.errorDetail = str;
                    }
                    if (nPListener != null) {
                        nPListener.onResult(nXToyAchievementsResult);
                    }
                }
            });
            return;
        }
        if (nPListener != null) {
            NXToyAchievementsResult nXToyAchievementsResult = new NXToyAchievementsResult();
            nXToyAchievementsResult.errorCode = NPGoogleGame.CODE_GOOGLEGAME_NOT_CONNECTED;
            nXToyAchievementsResult.errorText = NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED;
            nXToyAchievementsResult.errorDetail = "";
            nXToyAchievementsResult.requestTag = NXToyRequestTag.LoadAchievementData.getValue();
            nPListener.onResult(nXToyAchievementsResult);
        }
    }

    public void loadCurrentPlayerLeaderboardScore(Activity activity, String str, int i, int i2, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (!isNotConnected(googleGameProvider)) {
            googleGameProvider.loadCurrentPlayerLeaderboardScore(activity, str, i, i2, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.12
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i3, String str2, Bundle bundle) {
                    if (i3 != NXToyErrorCode.SUCCESS.getCode()) {
                        NXToyResult nXToyResult = new NXToyResult();
                        nXToyResult.errorCode = i3;
                        nXToyResult.errorText = str2;
                        nXToyResult.errorDetail = str2;
                        nXToyResult.requestTag = NXToyRequestTag.LoadCurrentPlayerScore.getValue();
                        nPListener.onResult(nXToyResult);
                        return;
                    }
                    NXToyLeaderboardScoreResult nXToyLeaderboardScoreResult = new NXToyLeaderboardScoreResult();
                    nXToyLeaderboardScoreResult.requestTag = NXToyRequestTag.LoadCurrentPlayerScore.getValue();
                    String string = bundle.getString(NPAuthPlugin.KEY_PLAYER_SCORE);
                    ToyLog.d("score json:" + string);
                    if (NXStringUtil.isNotNull(string)) {
                        nXToyLeaderboardScoreResult.result = (NXToyLeaderboardScoreResult.ResultSet) NXJsonUtil.fromObject(string, NXToyLeaderboardScoreResult.ResultSet.class);
                    }
                    nXToyLeaderboardScoreResult.errorCode = i3;
                    nXToyLeaderboardScoreResult.errorText = str2;
                    nXToyLeaderboardScoreResult.errorDetail = str2;
                    nPListener.onResult(nXToyLeaderboardScoreResult);
                }
            });
            return;
        }
        if (nPListener != null) {
            NXToyLeaderboardScoreResult nXToyLeaderboardScoreResult = new NXToyLeaderboardScoreResult();
            nXToyLeaderboardScoreResult.errorCode = NPGoogleGame.CODE_GOOGLEGAME_NOT_CONNECTED;
            nXToyLeaderboardScoreResult.errorText = NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED;
            nXToyLeaderboardScoreResult.errorDetail = "";
            nXToyLeaderboardScoreResult.requestTag = NXToyRequestTag.LoadCurrentPlayerScore.getValue();
            nPListener.onResult(nXToyLeaderboardScoreResult);
        }
    }

    public void logout(@NonNull Activity activity, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (googleGameProvider != null) {
            googleGameProvider.logout(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.3
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    ToyLog.d("errorCode:" + i + ", errorDetail:" + str);
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyResult(i, str, str, NXToyRequestTag.LogoutGamePlatform.getValue()));
                    }
                }
            });
            return;
        }
        ToyLog.d("Unsupported type. NPGoogleGame provider not found.");
        if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NPAuthPlugin.CODE_NOT_SUPPORT, NPGoogleGame.STR_GOOGLEGAME_NOT_SUPPORTED, "", NXToyRequestTag.LogoutGamePlatform.getValue()));
        }
    }

    public void screenCapture(@NonNull Activity activity, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (!isNotConnected(googleGameProvider)) {
            googleGameProvider.screenCapture(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.13
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    if (i == NXToyErrorCode.SUCCESS.getCode() || nPListener == null) {
                        return;
                    }
                    nPListener.onResult(new NXToyResult(i, str, str, NXToyRequestTag.ScreenCapture.getValue()));
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NPGoogleGame.CODE_GOOGLEGAME_NOT_CONNECTED, NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED, "", NXToyRequestTag.ScreenCapture.getValue()));
        }
    }

    public void setStepsAchievement(Activity activity, String str, int i) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (isNotConnected(googleGameProvider)) {
            ToyLog.d(NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED);
        } else {
            googleGameProvider.setStepsAchievement(activity, str, i);
        }
    }

    public void setStepsAchievementImmediate(Activity activity, String str, int i, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (!isNotConnected(googleGameProvider)) {
            googleGameProvider.setStepsAchievementImmediate(activity, str, i, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.5
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i2, String str2, Bundle bundle) {
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyResult(i2, str2, str2, NXToyRequestTag.SetStepsAchievementImmediate.getValue()));
                    }
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NPGoogleGame.CODE_GOOGLEGAME_NOT_CONNECTED, NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED, "", NXToyRequestTag.SetStepsAchievementImmediate.getValue()));
        }
    }

    public void showAchievement(@NonNull Activity activity, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (!isNotConnected(googleGameProvider)) {
            googleGameProvider.showAchievement(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.4
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyResult(i, str, str, NXToyRequestTag.GameServiceScreen.getValue()));
                    }
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NPGoogleGame.CODE_GOOGLEGAME_NOT_CONNECTED, NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED, "", NXToyRequestTag.GameServiceScreen.getValue()));
        }
    }

    public void showAllLeaderBoard(@NonNull Activity activity, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (!isNotConnected(googleGameProvider)) {
            googleGameProvider.showAllLeaderBoard(activity, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.9
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle) {
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyResult(i, str, str, NXToyRequestTag.GameServiceScreen.getValue()));
                    }
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NPGoogleGame.CODE_GOOGLEGAME_NOT_CONNECTED, NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED, "", NXToyRequestTag.GameServiceScreen.getValue()));
        }
    }

    public void showLeaderBoard(@NonNull Activity activity, String str, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (!isNotConnected(googleGameProvider)) {
            googleGameProvider.showLeaderBoard(activity, str, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.10
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str2, Bundle bundle) {
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyResult(i, str2, str2, NXToyRequestTag.GameServiceScreen.getValue()));
                    }
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NPGoogleGame.CODE_GOOGLEGAME_NOT_CONNECTED, NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED, "", NXToyRequestTag.GameServiceScreen.getValue()));
        }
    }

    public void submitScore(Activity activity, String str, long j) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (isNotConnected(googleGameProvider)) {
            ToyLog.d(NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED);
        } else {
            googleGameProvider.submitScore(activity, str, j);
        }
    }

    public void submitScoreImmediate(Activity activity, String str, long j, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (!isNotConnected(googleGameProvider)) {
            googleGameProvider.submitScoreImmediate(activity, str, j, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.11
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str2, Bundle bundle) {
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyResult(i, str2, str2, NXToyRequestTag.SubmitScoreImmediate.getValue()));
                    }
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NPGoogleGame.CODE_GOOGLEGAME_NOT_CONNECTED, NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED, "", NXToyRequestTag.SubmitScoreImmediate.getValue()));
        }
    }

    public void unlockAchievement(@NonNull Activity activity, String str) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (isNotConnected(googleGameProvider)) {
            ToyLog.d(NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED);
        } else {
            googleGameProvider.unlockAchievement(activity, str);
        }
    }

    public void unlockAchievementImmediate(@NonNull Activity activity, String str, final NPListener nPListener) {
        NPGoogleGame googleGameProvider = NXPProviderManager.getInstance().getGoogleGameProvider();
        if (!isNotConnected(googleGameProvider)) {
            googleGameProvider.unlockAchievementImmediate(activity, str, new NPAuthListener() { // from class: kr.co.nexon.npaccount.sns.NXPGameCenterManager.6
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str2, Bundle bundle) {
                    if (nPListener != null) {
                        nPListener.onResult(new NXToyResult(i, str2, str2, NXToyRequestTag.UnlockAchievementImmediate.getValue()));
                    }
                }
            });
        } else if (nPListener != null) {
            nPListener.onResult(new NXToyResult(NPGoogleGame.CODE_GOOGLEGAME_NOT_CONNECTED, NPGoogleGame.STR_GOOGLEGAME_NOT_CONNECTED, "", NXToyRequestTag.UnlockAchievementImmediate.getValue()));
        }
    }
}
