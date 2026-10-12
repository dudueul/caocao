package kr.co.nexon.android.sns.google;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.facebook.GraphResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.video.Videos;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import java.util.ArrayList;
import java.util.List;
import kr.co.nexon.android.sns.NPAuthFriendsListener;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.mdev.android.activity.NPActivityResultManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPGoogleGame extends NPAuthPlugin implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static final int CODE_GOOGLEGAME_APP_MISCONFIGURED = 10004;
    public static final int CODE_GOOGLEGAME_CAPTURE_STATE_ALREADY_START = 90615;
    public static final int CODE_GOOGLEGAME_EXPIRE_TOKEN = 90607;
    public static final int CODE_GOOGLEGAME_GAME_LOAD_ACHEIVE_FAIL = 90605;
    public static final int CODE_GOOGLEGAME_GET_FRIENDS_CONNECT_FAILED = 90604;
    public static final int CODE_GOOGLEGAME_GET_FRIENDS_LOAD_DATA_FAILED = 90606;
    public static final int CODE_GOOGLEGAME_GET_PERSON_FAILED = 90601;
    public static final int CODE_GOOGLEGAME_GET_TOKEN_FAILED = 90602;
    public static final int CODE_GOOGLEGAME_GET_USERINFO_CONNECT_FAILED = 90603;
    public static final int CODE_GOOGLEGAME_GET_USERINFO_FAIL = 90609;
    public static final int CODE_GOOGLEGAME_INVALID_ROOM = 10008;
    public static final int CODE_GOOGLEGAME_LEFT_ROOM = 10005;
    public static final int CODE_GOOGLEGAME_LICENSE_FAILED = 10003;
    public static final int CODE_GOOGLEGAME_LOAD_CURRENT_PLAYER_SCORE_FAIL = 90614;
    public static final int CODE_GOOGLEGAME_LOGIN_FAIL = 90610;
    public static final int CODE_GOOGLEGAME_LOGIN_GET_USERINFO_FAIL = 90608;
    public static final int CODE_GOOGLEGAME_LOGIN_USER_CANCEL = 90618;
    public static final int CODE_GOOGLEGAME_NETWORK_FAILURE = 10006;
    public static final int CODE_GOOGLEGAME_NOT_CONNECTED = 90611;
    public static final int CODE_GOOGLEGAME_NOT_SUPPORT_DEVICE = 90613;
    public static final int CODE_GOOGLEGAME_PLAYER_STATS_FAIL = 90616;
    public static final int CODE_GOOGLEGAME_PLAYER_STATS_NULL = 90617;
    public static final int CODE_GOOGLEGAME_RECONNECT_REQUIRED = 10001;
    public static final int CODE_GOOGLEGAME_RECOVERABLE_ERROR = 90612;
    public static final int CODE_GOOGLEGAME_SEND_REQUEST_FAILED = 10007;
    public static final int CODE_GOOGLEGAME_SIGN_IN_FAILED = 10002;
    private static final String KEY_CONNECTION_RESULT = "connectionResult";
    private static final String LAST_PLAYED_WITH_TIMESTAMP = "lastPlayedWithTimeStamp`";
    public static final int RC_SIGN_IN = 9011;
    public static final int REQUEST_ACHIEVEMENTS = 9013;
    public static final int REQUEST_LEADERBOARD = 9014;
    public static final int REQUEST_PLAY_SERVICES_RESOLUTION = 9012;
    public static final int REQUEST_SCREEN_CAPTURE = 9015;
    public static final int SERVICE_MISSING = 1;
    public static final String SERVICE_NAME = "googlegame";
    public static final String STR_GOOGLEGAME_ALREADY_SCREEN_CAPTURE = "Google screen capture function is already running.";
    public static final String STR_GOOGLEGAME_NOT_CONNECTED = "Not connected to google play game service.";
    public static final String STR_GOOGLEGAME_NOT_SUPPORTED = "Google play game service is not supported.";
    public static final String STR_GOOGLEGAME_PLAYER_STATS_NULL = "There is no google player stats data.";
    public static final int SUCCESS = 0;
    private Activity activity;
    private String gplusAppID;
    private NPAuthListener loginListener;
    private boolean mAutoStartSignInflow;
    private GoogleApiClient mGoogleApiClient;
    private boolean mResolvingConnectionFailure;
    private boolean mSignInClicked;
    private NPActivityResultManager onActivityResultManager;

    public class NPGPlusAcheivementResult {
        public List<NPGoogleGameAchievement> achievements;

        public NPGPlusAcheivementResult() {
        }
    }

    public class NPGPlusLeaderboardScoreResult {
        public NPGoogleGameLeaderboardScore score;

        public NPGPlusLeaderboardScoreResult() {
        }
    }

    public NPGoogleGame(Activity activity) {
        super(activity.getApplicationContext());
        this.mResolvingConnectionFailure = false;
        this.mAutoStartSignInflow = true;
        this.mSignInClicked = false;
        this.activity = activity;
        this.onActivityResultManager = NPActivityResultManager.getInstance();
        if (this.gplusAppID == null) {
            try {
                this.gplusAppID = this.applicationContext.getPackageManager().getApplicationInfo(this.applicationContext.getPackageName(), 128).metaData.getString("com.google.android.gms.games.APP_ID");
            } catch (PackageManager.NameNotFoundException e) {
                this.gplusAppID = null;
            }
        }
        createApiClient(activity);
    }

    private void createApiClient(Activity activity) {
        this.mGoogleApiClient = new GoogleApiClient.Builder(activity.getApplicationContext()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Games.API).addScope(Games.SCOPE_GAMES).setViewForPopups(activity.getWindow().getDecorView().findViewById(R.id.content)).build();
    }

    private boolean hasGplusAppID() {
        return (this.gplusAppID == null || "".equals(this.gplusAppID)) ? false : true;
    }

    private void onExecutionError(Exception exc, NPAuthListener nPAuthListener) {
        ToyLog.d(exc.toString());
        createApiClient(this.activity);
        if (nPAuthListener != null) {
            nPAuthListener.onResult(CODE_GOOGLEGAME_NOT_CONNECTED, STR_GOOGLEGAME_NOT_CONNECTED, null);
        }
    }

    public void disconnect(NPAuthListener nPAuthListener) {
        if (hasGplusAppID()) {
            if (this.mGoogleApiClient == null || !this.mGoogleApiClient.isConnected()) {
                ToyLog.d("mGoogleApiClient null");
                if (nPAuthListener != null) {
                    nPAuthListener.onResult(CODE_GOOGLEGAME_NOT_CONNECTED, STR_GOOGLEGAME_NOT_CONNECTED, null);
                    return;
                }
                return;
            }
            this.mGoogleApiClient.disconnect();
            if (nPAuthListener != null) {
                nPAuthListener.onResult(0, "", null);
            }
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getAccessToken(Context context, NPAuthListener nPAuthListener) {
        Bundle bundle = new Bundle();
        bundle.putString(NPAuthPlugin.KEY_SNS_NAME, SERVICE_NAME);
        bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, "GoogleGame_Token");
        nPAuthListener.onResult(0, "", bundle);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getFriends(Context context, boolean z, NPAuthFriendsListener nPAuthFriendsListener) {
        nPAuthFriendsListener.onResult(NPAuthPlugin.CODE_NOT_SUPPORT, "not supported", false, null);
    }

    public String getGcId() {
        return isConnected() ? Games.Players.getCurrentPlayerId(this.mGoogleApiClient) : "";
    }

    public String getName() {
        if (!isConnected()) {
            return "";
        }
        String displayName = Games.Players.getCurrentPlayer(this.mGoogleApiClient).getDisplayName();
        return NXStringUtil.isNull(displayName) ? "" : displayName;
    }

    public void getPlayerStats(boolean z, final NPAuthListener nPAuthListener) {
        if (hasGplusAppID()) {
            try {
                Games.Stats.loadPlayerStats(this.mGoogleApiClient, z).setResultCallback(new ResultCallback<Stats.LoadPlayerStatsResult>() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.12
                    @Override // com.google.android.gms.common.api.ResultCallback
                    public void onResult(@NonNull Stats.LoadPlayerStatsResult loadPlayerStatsResult) {
                        if (!loadPlayerStatsResult.getStatus().isSuccess()) {
                            ToyLog.d("get player stats error message:" + loadPlayerStatsResult.getStatus().getStatusMessage());
                            if (nPAuthListener != null) {
                                nPAuthListener.onResult(NPGoogleGame.CODE_GOOGLEGAME_PLAYER_STATS_FAIL, loadPlayerStatsResult.getStatus().getStatusMessage(), null);
                                return;
                            }
                            return;
                        }
                        PlayerStats playerStats = loadPlayerStatsResult.getPlayerStats();
                        if (playerStats == null) {
                            ToyLog.d("player stats is null");
                            if (nPAuthListener != null) {
                                nPAuthListener.onResult(NPGoogleGame.CODE_GOOGLEGAME_PLAYER_STATS_NULL, NPGoogleGame.STR_GOOGLEGAME_PLAYER_STATS_NULL, null);
                                return;
                            }
                            return;
                        }
                        ToyLog.d("player stats is not null");
                        NPGooglePlayerStats nPGooglePlayerStats = new NPGooglePlayerStats();
                        nPGooglePlayerStats.averageSessionLength = playerStats.getAverageSessionLength();
                        nPGooglePlayerStats.churnProbability = playerStats.getChurnProbability();
                        nPGooglePlayerStats.daysSinceLastPlayed = playerStats.getDaysSinceLastPlayed();
                        nPGooglePlayerStats.highSpenderProbability = playerStats.getHighSpenderProbability();
                        nPGooglePlayerStats.numberOfPurchases = playerStats.getNumberOfPurchases();
                        nPGooglePlayerStats.numberOfSessions = playerStats.getNumberOfSessions();
                        nPGooglePlayerStats.sessionPercentile = playerStats.getSessionPercentile();
                        nPGooglePlayerStats.spendPercentile = playerStats.getSpendPercentile();
                        nPGooglePlayerStats.spendProbability = playerStats.getSpendProbability();
                        nPGooglePlayerStats.totalSpendNext28Days = playerStats.getTotalSpendNext28Days();
                        String jsonString = NXJsonUtil.toJsonString(nPGooglePlayerStats);
                        ToyLog.d("" + jsonString);
                        Bundle bundle = new Bundle();
                        bundle.putString(NPAuthPlugin.KEY_PLAYER_STATS, jsonString);
                        if (nPAuthListener != null) {
                            nPAuthListener.onResult(0, "", bundle);
                        }
                    }
                });
            } catch (Exception e) {
                onExecutionError(e, nPAuthListener);
            }
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getUserInfo(Context context, NPAuthListener nPAuthListener) {
        if (!isConnected()) {
            nPAuthListener.onResult(CODE_GOOGLEGAME_NOT_CONNECTED, "api client not connected", null);
            return;
        }
        Player currentPlayer = Games.Players.getCurrentPlayer(this.mGoogleApiClient);
        Bundle bundle = new Bundle();
        bundle.putString(NPAuthPlugin.KEY_ID, Games.Players.getCurrentPlayerId(this.mGoogleApiClient));
        bundle.putString(NPAuthPlugin.KEY_NAME, getName());
        bundle.putString(NPAuthPlugin.KEY_PICTURE_URL, currentPlayer.getIconImageUri() == null ? "" : currentPlayer.getIconImageUri().toString());
        bundle.putString(LAST_PLAYED_WITH_TIMESTAMP, "" + currentPlayer.getLastPlayedWithTimestamp());
        nPAuthListener.onResult(0, "", bundle);
        ToyLog.d("getUserInfo " + Games.Players.getCurrentPlayerId(this.mGoogleApiClient));
    }

    public void incrementAchievement(Activity activity, String str, int i) {
        ToyLog.d("achievementID " + str);
        try {
            Games.Achievements.increment(this.mGoogleApiClient, str, i);
        } catch (Exception e) {
            onExecutionError(e, null);
        }
    }

    public void incrementAchievementImmediate(Activity activity, String str, int i, final NPAuthListener nPAuthListener) {
        ToyLog.d("achieveMentID " + str);
        try {
            Games.Achievements.incrementImmediate(this.mGoogleApiClient, str, i).setResultCallback(new ResultCallback<Achievements.UpdateAchievementResult>() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.4
                @Override // com.google.android.gms.common.api.ResultCallback
                public void onResult(@NonNull Achievements.UpdateAchievementResult updateAchievementResult) {
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(updateAchievementResult.getStatus().getStatusCode(), GamesStatusCodes.getStatusString(updateAchievementResult.getStatus().getStatusCode()), null);
                    }
                }
            });
        } catch (Exception e) {
            onExecutionError(e, nPAuthListener);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void isConnect(Context context, NPAuthListener nPAuthListener) {
        if (isConnected()) {
            nPAuthListener.onResult(0, "", null);
        } else {
            nPAuthListener.onResult(CODE_GOOGLEGAME_NOT_CONNECTED, "not connected", null);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public boolean isConnected() {
        return this.mGoogleApiClient.isConnected();
    }

    public void loadAchievementData(Activity activity, boolean z, final NPAuthListener nPAuthListener) {
        try {
            Games.Achievements.load(this.mGoogleApiClient, z).setResultCallback(new ResultCallback<Achievements.LoadAchievementsResult>() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.6
                @Override // com.google.android.gms.common.api.ResultCallback
                public void onResult(@NonNull Achievements.LoadAchievementsResult loadAchievementsResult) {
                    if (!loadAchievementsResult.getStatus().isSuccess()) {
                        ToyLog.d("loadAchievementData error message:" + loadAchievementsResult.getStatus().getStatusMessage());
                        nPAuthListener.onResult(NPGoogleGame.CODE_GOOGLEGAME_GAME_LOAD_ACHEIVE_FAIL, loadAchievementsResult.getStatus().getStatusMessage(), null);
                        return;
                    }
                    AchievementBuffer achievements = loadAchievementsResult.getAchievements();
                    NPGPlusAcheivementResult nPGPlusAcheivementResult = NPGoogleGame.this.new NPGPlusAcheivementResult();
                    ArrayList arrayList = new ArrayList();
                    for (Achievement achievement : achievements) {
                        NPGoogleGameAchievement nPGoogleGameAchievement = new NPGoogleGameAchievement();
                        ToyLog.d(achievement.toString());
                        nPGoogleGameAchievement.achievementID = achievement.getAchievementId();
                        nPGoogleGameAchievement.name = achievement.getName();
                        nPGoogleGameAchievement.type = achievement.getType();
                        nPGoogleGameAchievement.state = achievement.getState();
                        if (achievement.getType() == 1) {
                            nPGoogleGameAchievement.currentSteps = achievement.getCurrentSteps();
                            nPGoogleGameAchievement.totalSteps = achievement.getTotalSteps();
                        }
                        arrayList.add(nPGoogleGameAchievement);
                    }
                    achievements.close();
                    nPGPlusAcheivementResult.achievements = arrayList;
                    String jsonString = NXJsonUtil.toJsonString(nPGPlusAcheivementResult);
                    ToyLog.d(jsonString);
                    Bundle bundle = new Bundle();
                    bundle.putString(NPAuthPlugin.KEY_ACH_LIST, jsonString);
                    nPAuthListener.onResult(0, "", bundle);
                }
            });
        } catch (Exception e) {
            onExecutionError(e, nPAuthListener);
        }
    }

    public void loadCurrentPlayerLeaderboardScore(Activity activity, String str, int i, int i2, final NPAuthListener nPAuthListener) {
        if (hasGplusAppID()) {
            try {
                Games.Leaderboards.loadCurrentPlayerLeaderboardScore(this.mGoogleApiClient, str, i, i2).setResultCallback(new ResultCallback<Leaderboards.LoadPlayerScoreResult>() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.10
                    @Override // com.google.android.gms.common.api.ResultCallback
                    public void onResult(@NonNull Leaderboards.LoadPlayerScoreResult loadPlayerScoreResult) {
                        if (!loadPlayerScoreResult.getStatus().isSuccess()) {
                            ToyLog.d("loadCurrentPlayerLeaderboardScore error message:" + loadPlayerScoreResult.getStatus().getStatusMessage());
                            nPAuthListener.onResult(NPGoogleGame.CODE_GOOGLEGAME_LOAD_CURRENT_PLAYER_SCORE_FAIL, loadPlayerScoreResult.getStatus().getStatusMessage(), null);
                            return;
                        }
                        NPGPlusLeaderboardScoreResult nPGPlusLeaderboardScoreResult = NPGoogleGame.this.new NPGPlusLeaderboardScoreResult();
                        LeaderboardScore score = loadPlayerScoreResult.getScore();
                        if (score == null) {
                            ToyLog.d("current score null");
                            Bundle bundle = new Bundle();
                            bundle.putString(NPAuthPlugin.KEY_PLAYER_SCORE, "");
                            nPAuthListener.onResult(0, "", bundle);
                            return;
                        }
                        ToyLog.d("current score:" + score.toString());
                        NPGoogleGameLeaderboardScore nPGoogleGameLeaderboardScore = new NPGoogleGameLeaderboardScore();
                        nPGoogleGameLeaderboardScore.displayRank = score.getDisplayRank();
                        nPGoogleGameLeaderboardScore.displayScore = score.getDisplayScore();
                        nPGoogleGameLeaderboardScore.rank = score.getRank();
                        nPGoogleGameLeaderboardScore.rawScore = score.getRawScore();
                        nPGoogleGameLeaderboardScore.scoreHolderDisplayName = score.getScoreHolderDisplayName();
                        nPGoogleGameLeaderboardScore.scoreHolderHiResImageUrl = score.getScoreHolder().getHiResImageUri().toString();
                        nPGoogleGameLeaderboardScore.scoreHolderIconImageUrl = score.getScoreHolder().getIconImageUri().toString();
                        nPGoogleGameLeaderboardScore.scoreTag = score.getScoreTag();
                        nPGoogleGameLeaderboardScore.timestampMillis = score.getTimestampMillis();
                        nPGPlusLeaderboardScoreResult.score = nPGoogleGameLeaderboardScore;
                        String jsonString = NXJsonUtil.toJsonString(nPGPlusLeaderboardScoreResult);
                        ToyLog.d("" + jsonString);
                        Bundle bundle2 = new Bundle();
                        bundle2.putString(NPAuthPlugin.KEY_PLAYER_SCORE, jsonString);
                        nPAuthListener.onResult(0, "", bundle2);
                    }
                });
            } catch (Exception e) {
                onExecutionError(e, nPAuthListener);
            }
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void login(@NonNull Activity activity, final NPAuthListener nPAuthListener) {
        if (!hasGplusAppID()) {
            if (nPAuthListener != null) {
                nPAuthListener.onResult(NPAuthPlugin.CODE_NOT_SUPPORT, STR_GOOGLEGAME_NOT_SUPPORTED, null);
            }
        } else {
            if (isConnected()) {
                if (nPAuthListener != null) {
                    ToyLog.d("googleGame isConnected :" + isConnected());
                    nPAuthListener.onResult(0, GraphResponse.SUCCESS_KEY, null);
                    return;
                }
                return;
            }
            this.activity = activity;
            this.loginListener = nPAuthListener;
            this.mSignInClicked = true;
            this.onActivityResultManager.registerActivityResultCallback(RC_SIGN_IN, new NPActivityResultManager.ActivityResultCallback() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.1
                @Override // kr.co.nexon.mdev.android.activity.NPActivityResultManager.ActivityResultCallback
                public void onActivityResult(int i, int i2, Intent intent) {
                    String str;
                    int code;
                    if (i == 9011) {
                        NPGoogleGame.this.mSignInClicked = false;
                        NPGoogleGame.this.mResolvingConnectionFailure = false;
                        if (i2 == -1) {
                            NPGoogleGame.this.mGoogleApiClient.connect();
                            NPGoogleGame.this.onActivityResultManager.registerActivityResultCallback(NPGoogleGame.RC_SIGN_IN, this);
                            return;
                        }
                        if (i2 < 10001) {
                            if (nPAuthListener != null) {
                                nPAuthListener.onResult(NPAuthPlugin.CODE_USER_CANCEL, "user cancel", null);
                                return;
                            }
                            return;
                        }
                        if (nPAuthListener != null) {
                            switch (i2) {
                                case 10001:
                                    str = "The GoogleApiClient is in an inconsistent state and must reconnect to the service to resolve the issue.";
                                    code = NXToyErrorCode.GOOGLE_GAME_RECONNECT_REQUIRED.getCode();
                                    break;
                                case 10002:
                                    str = "The attempt to sign in to the Games service failed.";
                                    code = NXToyErrorCode.GOOGLE_GAME_SIGN_IN_FAILED.getCode();
                                    break;
                                case 10003:
                                    str = "the game is not licensed to the user.";
                                    code = NXToyErrorCode.GOOGLE_GAME_LICENSE_FAILED.getCode();
                                    break;
                                case 10004:
                                    str = "the game is not properly configured to access the Games service.";
                                    code = NXToyErrorCode.GOOGLE_GAME_APP_MISCONFIGURED.getCode();
                                    break;
                                case 10005:
                                    str = "the user explicitly chose to \"leave the room\" from the real-time multiplayer \"waiting room\" screen.";
                                    code = NXToyErrorCode.GOOGLE_GAME_LEFT_ROOM.getCode();
                                    break;
                                case 10006:
                                    str = "the server request resulted in a network error.";
                                    code = NXToyErrorCode.GOOGLE_GAME_NETWORK_FAILURE.getCode();
                                    break;
                                case 10007:
                                    str = "sending a request from the \"send request\" screen failed.";
                                    code = NXToyErrorCode.GOOGLE_GAME_SEND_REQUEST_FAILED.getCode();
                                    break;
                                case 10008:
                                    str = "a RealTimeWaitingRoom cannot be displayed because the room does not exist.";
                                    code = NXToyErrorCode.GOOGLE_GAME_INVALID_ROOM.getCode();
                                    break;
                                default:
                                    str = "unknown error. google error code:" + i2;
                                    code = NPAuthPlugin.CODE_UNKNOWN_ERROR;
                                    break;
                            }
                            nPAuthListener.onResult(code, str, null);
                        }
                    }
                }
            });
            this.mGoogleApiClient.connect();
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void logout(Context context, final NPAuthListener nPAuthListener) {
        this.mSignInClicked = false;
        if (!isConnected()) {
            ToyLog.d("not connected listener:" + nPAuthListener);
            if (nPAuthListener != null) {
                nPAuthListener.onResult(CODE_GOOGLEGAME_NOT_CONNECTED, STR_GOOGLEGAME_NOT_CONNECTED, null);
                return;
            }
            return;
        }
        ToyLog.d("hasConnectd Games.API " + this.mGoogleApiClient.hasConnectedApi(Games.API));
        ToyLog.d("getAppid " + Games.getAppId(this.mGoogleApiClient));
        try {
            Games.signOut(this.mGoogleApiClient).setResultCallback(new ResultCallback<Status>() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.2
                @Override // com.google.android.gms.common.api.ResultCallback
                public void onResult(@NonNull Status status) {
                    NPGoogleGame.this.mGoogleApiClient.disconnect();
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(0, "", null);
                    }
                }
            });
        } catch (Exception e) {
            ToyLog.d(e.toString());
            createApiClient(this.activity);
            if (nPAuthListener != null) {
                nPAuthListener.onResult(0, "", null);
            }
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
        ToyLog.d("NPGoogleGame, onConnected");
        if (this.loginListener != null) {
            this.loginListener.onResult(0, "", null);
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        ToyLog.d("NPGoogleGame, onConnectionFailed, mResolvingConnetionFailure:" + this.mResolvingConnectionFailure);
        if (this.mResolvingConnectionFailure) {
            return;
        }
        ToyLog.d("signinClicked :" + this.mSignInClicked + "  autoStartSigninFlow:" + this.mAutoStartSignInflow);
        if (this.mSignInClicked || this.mAutoStartSignInflow) {
            this.mAutoStartSignInflow = false;
            this.mSignInClicked = false;
            this.mResolvingConnectionFailure = true;
            if (!connectionResult.hasResolution()) {
                ToyLog.d("not resolvable... so show an error message loginListener:" + this.loginListener);
                if (this.loginListener != null) {
                    this.loginListener.onResult(connectionResult.getErrorCode(), connectionResult.getErrorMessage(), null);
                }
                this.mResolvingConnectionFailure = false;
                return;
            }
            try {
                ToyLog.d("connectionResult.startResolutionForResult");
                connectionResult.startResolutionForResult(this.activity, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                ToyLog.d("IntentSender.SendIntentException e:" + e.toString());
                this.mGoogleApiClient.connect();
                this.mResolvingConnectionFailure = false;
            }
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnectionSuspended(int i) {
        ToyLog.d("NPGoogleGame, onConnectionSuspended");
        this.mGoogleApiClient.connect();
    }

    public void screenCapture(@NonNull final Activity activity, final NPAuthListener nPAuthListener) {
        if (hasGplusAppID()) {
            try {
                Games.Videos.getCaptureState(this.mGoogleApiClient).setResultCallback(new ResultCallback<Videos.CaptureStateResult>() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.11
                    @Override // com.google.android.gms.common.api.ResultCallback
                    public void onResult(@NonNull Videos.CaptureStateResult captureStateResult) {
                        if (captureStateResult.getStatus().getStatusCode() != 0) {
                            if (nPAuthListener != null) {
                                nPAuthListener.onResult(captureStateResult.getStatus().getStatusCode(), "" + captureStateResult.getStatus().getStatusMessage(), null);
                            }
                        } else if (!captureStateResult.getStatus().isSuccess() || captureStateResult.getCaptureState().isOverlayVisible()) {
                            if (nPAuthListener != null) {
                                nPAuthListener.onResult(NPGoogleGame.CODE_GOOGLEGAME_CAPTURE_STATE_ALREADY_START, NPGoogleGame.STR_GOOGLEGAME_ALREADY_SCREEN_CAPTURE, null);
                            }
                        } else {
                            Intent captureOverlayIntent = Games.Videos.getCaptureOverlayIntent(NPGoogleGame.this.mGoogleApiClient);
                            NPGoogleGame.this.onActivityResultManager.registerActivityResultCallback(NPGoogleGame.REQUEST_SCREEN_CAPTURE, new NPActivityResultManager.ActivityResultCallback() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.11.1
                                @Override // kr.co.nexon.mdev.android.activity.NPActivityResultManager.ActivityResultCallback
                                public void onActivityResult(int i, int i2, Intent intent) {
                                    nPAuthListener.onResult(0, "", null);
                                }
                            });
                            activity.startActivityForResult(captureOverlayIntent, NPGoogleGame.REQUEST_SCREEN_CAPTURE);
                        }
                    }
                });
            } catch (Exception e) {
                onExecutionError(e, nPAuthListener);
            }
        }
    }

    public void setStepsAchievement(Activity activity, String str, int i) {
        ToyLog.d("achievementID " + str);
        try {
            Games.Achievements.setSteps(this.mGoogleApiClient, str, i);
        } catch (Exception e) {
            onExecutionError(e, null);
        }
    }

    public void setStepsAchievementImmediate(Activity activity, String str, int i, final NPAuthListener nPAuthListener) {
        ToyLog.d("achievementID " + str);
        try {
            Games.Achievements.setStepsImmediate(this.mGoogleApiClient, str, i).setResultCallback(new ResultCallback<Achievements.UpdateAchievementResult>() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.5
                @Override // com.google.android.gms.common.api.ResultCallback
                public void onResult(@NonNull Achievements.UpdateAchievementResult updateAchievementResult) {
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(updateAchievementResult.getStatus().getStatusCode(), GamesStatusCodes.getStatusString(updateAchievementResult.getStatus().getStatusCode()), null);
                    }
                }
            });
        } catch (Exception e) {
            onExecutionError(e, nPAuthListener);
        }
    }

    public void showAchievement(@NonNull final Activity activity, final NPAuthListener nPAuthListener) {
        if (hasGplusAppID()) {
            try {
                this.onActivityResultManager.registerActivityResultCallback(REQUEST_ACHIEVEMENTS, new NPActivityResultManager.ActivityResultCallback() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.7
                    @Override // kr.co.nexon.mdev.android.activity.NPActivityResultManager.ActivityResultCallback
                    public void onActivityResult(int i, int i2, Intent intent) {
                        if (i2 < 10001) {
                            if (nPAuthListener != null) {
                                nPAuthListener.onResult(0, "", null);
                            }
                        } else if (i2 == 10001) {
                            NPGoogleGame.this.logout(activity, null);
                            if (nPAuthListener != null) {
                                nPAuthListener.onResult(i2, "You have been logged out of Google.", null);
                            }
                        }
                    }
                });
                activity.startActivityForResult(Games.Achievements.getAchievementsIntent(this.mGoogleApiClient), REQUEST_ACHIEVEMENTS);
            } catch (Exception e) {
                onExecutionError(e, nPAuthListener);
            }
        }
    }

    public void showAllLeaderBoard(@NonNull Activity activity, NPAuthListener nPAuthListener) {
        showLeaderBoard(activity, "", nPAuthListener);
    }

    public void showLeaderBoard(@NonNull final Activity activity, String str, final NPAuthListener nPAuthListener) {
        ToyLog.d("leaderBoardID " + str);
        if (hasGplusAppID()) {
            try {
                this.onActivityResultManager.registerActivityResultCallback(REQUEST_LEADERBOARD, new NPActivityResultManager.ActivityResultCallback() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.9
                    @Override // kr.co.nexon.mdev.android.activity.NPActivityResultManager.ActivityResultCallback
                    public void onActivityResult(int i, int i2, Intent intent) {
                        if (i2 < 10001) {
                            if (nPAuthListener != null) {
                                nPAuthListener.onResult(0, "", null);
                            }
                        } else if (i2 == 10001) {
                            NPGoogleGame.this.logout(activity, null);
                            if (nPAuthListener != null) {
                                nPAuthListener.onResult(i2, "You have been logged out of Google.", null);
                            }
                        }
                    }
                });
                if (NXStringUtil.isNotNull(str)) {
                    activity.startActivityForResult(Games.Leaderboards.getLeaderboardIntent(this.mGoogleApiClient, str), REQUEST_LEADERBOARD);
                } else {
                    activity.startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(this.mGoogleApiClient), REQUEST_LEADERBOARD);
                }
            } catch (Exception e) {
                onExecutionError(e, nPAuthListener);
            }
        }
    }

    public void submitScore(Activity activity, String str, long j) {
        ToyLog.d("leaderBoardID " + str + " " + j);
        if (hasGplusAppID()) {
            try {
                Games.Leaderboards.submitScore(this.mGoogleApiClient, str, j);
            } catch (Exception e) {
                onExecutionError(e, null);
            }
        }
    }

    public void submitScoreImmediate(Activity activity, String str, long j, final NPAuthListener nPAuthListener) {
        ToyLog.d("leaderBoardID " + str + " " + j);
        if (hasGplusAppID()) {
            try {
                Games.Leaderboards.submitScoreImmediate(this.mGoogleApiClient, str, j).setResultCallback(new ResultCallback<Leaderboards.SubmitScoreResult>() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.8
                    @Override // com.google.android.gms.common.api.ResultCallback
                    public void onResult(@NonNull Leaderboards.SubmitScoreResult submitScoreResult) {
                        if (nPAuthListener != null) {
                            nPAuthListener.onResult(submitScoreResult.getStatus().getStatusCode(), GamesStatusCodes.getStatusString(submitScoreResult.getStatus().getStatusCode()), null);
                        }
                    }
                });
            } catch (Exception e) {
                onExecutionError(e, nPAuthListener);
            }
        }
    }

    public void unlockAchievement(@NonNull Activity activity, String str) {
        ToyLog.d("achievementID " + str);
        try {
            Games.setViewForPopups(this.mGoogleApiClient, activity.getWindow().getDecorView().findViewById(R.id.content));
            Games.Achievements.unlock(this.mGoogleApiClient, str);
        } catch (Exception e) {
            onExecutionError(e, null);
        }
    }

    public void unlockAchievementImmediate(@NonNull Activity activity, String str, final NPAuthListener nPAuthListener) {
        ToyLog.d("achievementID " + str);
        try {
            Games.setViewForPopups(this.mGoogleApiClient, activity.getWindow().getDecorView().findViewById(R.id.content));
            Games.Achievements.unlockImmediate(this.mGoogleApiClient, str).setResultCallback(new ResultCallback<Achievements.UpdateAchievementResult>() { // from class: kr.co.nexon.android.sns.google.NPGoogleGame.3
                @Override // com.google.android.gms.common.api.ResultCallback
                public void onResult(@NonNull Achievements.UpdateAchievementResult updateAchievementResult) {
                    if (nPAuthListener != null) {
                        nPAuthListener.onResult(updateAchievementResult.getStatus().getStatusCode(), GamesStatusCodes.getStatusString(updateAchievementResult.getStatus().getStatusCode()), null);
                    }
                }
            });
        } catch (Exception e) {
            onExecutionError(e, nPAuthListener);
        }
    }
}
