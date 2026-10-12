package kr.co.nexon.android.sns.twitter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.util.NXStringUtil;
import java.util.ArrayList;
import kr.co.nexon.android.sns.NPAuthFriendsListener;
import kr.co.nexon.android.sns.NPAuthListener;
import kr.co.nexon.android.sns.NPAuthPlugin;
import kr.co.nexon.android.sns.NPAuthUser;
import twitter4j.PagableResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NPTwitter extends NPAuthPlugin {
    public static final String SERVICE_NAME = "twitter";
    private static final String TWITTER_ID = "twitterId";
    private static final String TWITTER_REQUEST_SECRET_TOKEN = "twitterRequestSecretToken";
    private static final String TWITTER_REQUEST_TOKEN = "twitterRequestToken";
    private static final String TWITTER_SECRET_TOKEN = "twitterSecretToken";
    private static final String TWITTER_TOKEN = "twitterToken";
    private NPTwitterAuthDialog authDialog;
    private String callbackURL;
    private String consumerKey;
    private String consumerSecret;
    private boolean enabled;
    private long nextPageOffset;

    /* JADX INFO: renamed from: twitter4j, reason: collision with root package name */
    private Twitter f2twitter4j;

    public NPTwitter(Context context) throws Exception {
        super(context);
        this.enabled = false;
        this.nextPageOffset = -1L;
        this.f2twitter4j = new TwitterFactory().getInstance();
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            this.consumerKey = applicationInfo.metaData.getString("TwitterConsumerKey");
            this.consumerSecret = applicationInfo.metaData.getString("TwitterConsumerSecret");
            this.callbackURL = applicationInfo.metaData.getString("TwitterCallbackURL");
            if (NXStringUtil.isNull(this.consumerKey) || NXStringUtil.isNull(this.consumerSecret) || NXStringUtil.isNull(this.callbackURL)) {
                throw new Exception("consumerKey, consumerSecret, callbackURL CHECK!");
            }
            try {
                this.f2twitter4j.setOAuthConsumer(this.consumerKey, this.consumerSecret);
            } catch (IllegalStateException e) {
                this.f2twitter4j = new TwitterFactory().getInstance();
                try {
                    this.f2twitter4j.setOAuthConsumer(this.consumerKey, this.consumerSecret);
                } catch (Exception e2) {
                    throw e2;
                }
            } catch (Exception e3) {
                throw e3;
            }
        } catch (PackageManager.NameNotFoundException e4) {
            ToyLog.d(e4.toString());
            throw new Exception("consumerKey, consumerSecret, callbackURL CHECK!");
        }
    }

    public NPTwitter(Context context, String str, String str2, String str3, String str4, String str5) throws Exception {
        this(context);
        setTwitterId(str5);
        setTwitterToken(str);
        setTwitterSecretToken(str2);
        setTwitterRequestToken(str3);
        setTwitterRequestSecretToken(str4);
    }

    private void clearTwitterToken() {
        this.enabled = false;
        setTwitterToken("");
        setTwitterSecretToken("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAccessToken() {
        return getTwitterToken();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAccessTokenSecret() {
        return getTwitterSecretToken();
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getAccessToken(Context context, NPAuthListener nPAuthListener) {
        if (!initInstance()) {
            if (nPAuthListener != null) {
                nPAuthListener.onResult(NXToyErrorCode.TWITTER_NOT_ENABLED.getCode(), "twitter not enabled", null);
                return;
            }
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(NPAuthPlugin.KEY_SNS_NAME, SERVICE_NAME);
        bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, getAccessToken());
        bundle.putString(NPAuthPlugin.KEY_SECRETTOKEN, getAccessTokenSecret());
        if (nPAuthListener != null) {
            nPAuthListener.onResult(0, "twitter get token success", bundle);
        }
    }

    public String getCallbackURL() {
        return this.callbackURL;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getFriends(Context context, boolean z, final NPAuthFriendsListener nPAuthFriendsListener) {
        if (initInstance()) {
            if (!z) {
                this.nextPageOffset = -1L;
            }
            new Thread(new Runnable() { // from class: kr.co.nexon.android.sns.twitter.NPTwitter.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        PagableResponseList<User> friendsList = NPTwitter.this.f2twitter4j.getFriendsList(Long.parseLong(NPTwitter.this.getID()), NPTwitter.this.nextPageOffset, 100);
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < friendsList.size(); i++) {
                            User user = (User) friendsList.get(i);
                            NPAuthUser nPAuthUser = new NPAuthUser();
                            nPAuthUser.memID = "" + user.getId();
                            nPAuthUser.name = user.getName();
                            nPAuthUser.accountName = user.getScreenName();
                            nPAuthUser.pictureURL = user.getProfileImageURL();
                            arrayList.add(nPAuthUser);
                        }
                        if (friendsList.hasNext()) {
                            NPTwitter.this.nextPageOffset = friendsList.getNextCursor();
                        } else {
                            NPTwitter.this.nextPageOffset = -1L;
                        }
                        nPAuthFriendsListener.onResult(0, "", friendsList.hasNext(), arrayList);
                    } catch (IllegalStateException e) {
                        nPAuthFriendsListener.onResult(NXToyErrorCode.TWITTER_GET_FRIENDS_FAIL.getCode(), e.toString(), false, null);
                    } catch (TwitterException e2) {
                        nPAuthFriendsListener.onResult(NXToyErrorCode.TWITTER_GET_FRIENDS_FAIL.getCode(), e2.toString(), false, null);
                    }
                }
            }).start();
        } else if (nPAuthFriendsListener != null) {
            nPAuthFriendsListener.onResult(NXToyErrorCode.TWITTER_GET_REQUEST_TOKEN_FAIL.getCode(), "Get AccessToken failed. Twitter is not connected.", false, null);
        }
    }

    public String getID() {
        return getTwitterId();
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public String getServiceName() {
        return SERVICE_NAME;
    }

    String getTwitterId() {
        return getPref().getString(TWITTER_ID, null);
    }

    public Twitter getTwitterInstance() {
        return this.f2twitter4j;
    }

    String getTwitterRequestSecretToken() {
        return getPref().getString(TWITTER_REQUEST_SECRET_TOKEN, null);
    }

    String getTwitterRequestToken() {
        return getPref().getString(TWITTER_REQUEST_TOKEN, null);
    }

    String getTwitterSecretToken() {
        return getPref().getString(TWITTER_SECRET_TOKEN, null);
    }

    String getTwitterToken() {
        return getPref().getString(TWITTER_TOKEN, null);
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void getUserInfo(final Context context, final NPAuthListener nPAuthListener) {
        initInstance();
        if (isEnabled()) {
            new Thread(new Runnable() { // from class: kr.co.nexon.android.sns.twitter.NPTwitter.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        User userShowUser = NPTwitter.this.f2twitter4j.showUser(Long.parseLong(NPTwitter.this.getID()));
                        Bundle bundle = new Bundle();
                        bundle.putString(NPAuthPlugin.KEY_ID, "" + userShowUser.getId());
                        bundle.putString(NPAuthPlugin.KEY_NAME, userShowUser.getScreenName());
                        bundle.putString(NPAuthPlugin.KEY_PICTURE_URL, "" + userShowUser.getProfileImageURL());
                        bundle.putInt(NPAuthPlugin.KEY_GENDER, 3);
                        bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, NPTwitter.this.getAccessToken());
                        if (nPAuthListener != null) {
                            nPAuthListener.onResult(0, "", bundle);
                        }
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        NPTwitter.this.logout(context, null);
                        if (nPAuthListener != null) {
                            nPAuthListener.onResult(NXToyErrorCode.TWITTER_SHOW_USER_FAILED.getCode(), e.toString(), null);
                        }
                    } catch (TwitterException e2) {
                        e2.printStackTrace();
                        NPTwitter.this.logout(context, null);
                        if (nPAuthListener != null) {
                            if (e2.getStatusCode() == 401 && e2.getErrorCode() == 89) {
                                nPAuthListener.onResult(NXToyErrorCode.TWITTER_INVALID_TOKEN.getCode(), e2.toString(), null);
                            } else {
                                nPAuthListener.onResult(e2.getErrorCode(), e2.toString(), null);
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        NPTwitter.this.logout(context, null);
                        if (nPAuthListener != null) {
                            nPAuthListener.onResult(NXToyErrorCode.TWITTER_SHOW_USER_FAILED.getCode(), e3.toString(), null);
                        }
                    }
                }
            }).start();
        } else if (nPAuthListener != null) {
            nPAuthListener.onResult(NXToyErrorCode.TWITTER_GET_REQUEST_TOKEN_FAIL.getCode(), "Get AccessToken failed. Twitter is not connected.", null);
        }
    }

    public boolean initInstance() {
        if (isEnabled()) {
            return true;
        }
        String twitterToken = getTwitterToken();
        String twitterSecretToken = getTwitterSecretToken();
        ToyLog.v("access_token :" + twitterToken);
        ToyLog.v("secret_token :" + twitterSecretToken);
        if ("".equals(twitterToken) || "".equals(twitterSecretToken)) {
            return false;
        }
        try {
            this.f2twitter4j.setOAuthAccessToken(new AccessToken(twitterToken, twitterSecretToken));
            this.enabled = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void isConnect(Context context, NPAuthListener nPAuthListener) {
        if (isEnabled()) {
            nPAuthListener.onResult(0, "", null);
        } else if (initInstance()) {
            nPAuthListener.onResult(0, "", null);
        } else {
            nPAuthListener.onResult(NXToyErrorCode.TWITTER_NOT_ENABLED.getCode(), "not connected", null);
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public boolean isConnected() {
        return isEnabled() || initInstance();
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void login(Activity activity, final NPAuthListener nPAuthListener) {
        initInstance();
        if (activity == null) {
            if (nPAuthListener != null) {
                nPAuthListener.onResult(NXToyErrorCode.TWITTER_AUTH_FAIL.getCode(), "wrong activity", null);
            }
        } else {
            if (isEnabled()) {
                Bundle bundle = new Bundle();
                bundle.putString(NPAuthPlugin.KEY_ID, getID());
                bundle.putString(NPAuthPlugin.KEY_ACCESSTOKEN, getAccessToken());
                bundle.putString(NPAuthPlugin.KEY_SECRETTOKEN, getAccessTokenSecret());
                nPAuthListener.onResult(0, "", bundle);
                return;
            }
            clearTwitterToken();
            new ConfigurationBuilder().setGZIPEnabled(false);
            this.f2twitter4j = new TwitterFactory().getInstance();
            this.f2twitter4j.setOAuthConsumer(this.consumerKey, this.consumerSecret);
            this.authDialog = new NPTwitterAuthDialog(activity, this, new NPAuthListener() { // from class: kr.co.nexon.android.sns.twitter.NPTwitter.2
                @Override // kr.co.nexon.android.sns.NPAuthListener
                public void onResult(int i, String str, Bundle bundle2) {
                    if (i != NXToyErrorCode.SUCCESS.getCode()) {
                        nPAuthListener.onResult(i, str, bundle2);
                        return;
                    }
                    Bundle bundle3 = new Bundle();
                    bundle3.putString(NPAuthPlugin.KEY_ID, NPTwitter.this.getID());
                    bundle3.putString(NPAuthPlugin.KEY_ACCESSTOKEN, NPTwitter.this.getAccessToken());
                    bundle3.putString(NPAuthPlugin.KEY_SECRETTOKEN, NPTwitter.this.getAccessTokenSecret());
                    nPAuthListener.onResult(0, "", bundle3);
                }
            });
            this.authDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: kr.co.nexon.android.sns.twitter.NPTwitter.3
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    ToyLog.d("onDismiss");
                    if (NPTwitter.this.authDialog.errorCode != NXToyErrorCode.SUCCESS.getCode()) {
                        nPAuthListener.onResult(NPTwitter.this.authDialog.errorCode, NPTwitter.this.authDialog.errorDetail, NPTwitter.this.authDialog.bundle);
                    } else {
                        final String string = NPTwitter.this.authDialog.bundle.getString("oauthVerifier");
                        new Thread(new Runnable() { // from class: kr.co.nexon.android.sns.twitter.NPTwitter.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    AccessToken oAuthAccessToken = NPTwitter.this.f2twitter4j.getOAuthAccessToken(new RequestToken(NPTwitter.this.getTwitterRequestToken(), NPTwitter.this.getTwitterRequestSecretToken()), string);
                                    if (oAuthAccessToken == null) {
                                        ToyLog.d("accessToken null");
                                    } else {
                                        ToyLog.v("accessToken " + oAuthAccessToken.getToken());
                                        NPTwitter.this.setTwitterToken(oAuthAccessToken.getToken());
                                        NPTwitter.this.setTwitterSecretToken(oAuthAccessToken.getTokenSecret());
                                        NPTwitter.this.f2twitter4j.setOAuthAccessToken(oAuthAccessToken);
                                        NPTwitter.this.initInstance();
                                        NPTwitter.this.setTwitterId("" + oAuthAccessToken.getUserId());
                                        ToyLog.d("twitId " + NPTwitter.this.getID());
                                        Bundle bundle2 = new Bundle();
                                        bundle2.putString(NPAuthPlugin.KEY_ID, NPTwitter.this.getID());
                                        bundle2.putString(NPAuthPlugin.KEY_ACCESSTOKEN, NPTwitter.this.getAccessToken());
                                        bundle2.putString(NPAuthPlugin.KEY_SECRETTOKEN, NPTwitter.this.getAccessTokenSecret());
                                        nPAuthListener.onResult(0, "Login Success", bundle2);
                                    }
                                } catch (TwitterException e) {
                                    e.printStackTrace();
                                    nPAuthListener.onResult(NXToyErrorCode.TWITTER_TOKEN_SETUP_FAIL.getCode(), e.toString(), null);
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }
            });
            this.authDialog.show();
        }
    }

    @Override // kr.co.nexon.android.sns.NPAuthPlugin
    public void logout(Context context, NPAuthListener nPAuthListener) {
        clearTwitterToken();
        if (nPAuthListener != null) {
            nPAuthListener.onResult(0, "", null);
        }
        this.f2twitter4j = new TwitterFactory().getInstance();
        this.f2twitter4j.setOAuthConsumer(this.consumerKey, this.consumerSecret);
    }

    public void sendPost(final String str, final NPAuthListener nPAuthListener) {
        initInstance();
        if (isEnabled()) {
            new Thread(new Runnable() { // from class: kr.co.nexon.android.sns.twitter.NPTwitter.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Status statusUpdateStatus = NPTwitter.this.f2twitter4j.updateStatus(str);
                        if (statusUpdateStatus == null) {
                            if (nPAuthListener != null) {
                                nPAuthListener.onResult(NXToyErrorCode.TWITTER_POST_FAILED.getCode(), "return status is null", null);
                            }
                        } else if (nPAuthListener != null) {
                            nPAuthListener.onResult(0, statusUpdateStatus.getText(), null);
                        }
                    } catch (TwitterException e) {
                        e.printStackTrace();
                        if (nPAuthListener != null) {
                            nPAuthListener.onResult(NXToyErrorCode.TWITTER_POST_FAILED.getCode(), "updateStatus failed " + e.toString(), null);
                        }
                    }
                }
            }).start();
        } else if (nPAuthListener != null) {
            nPAuthListener.onResult(NXToyErrorCode.TWITTER_NOT_ENABLED.getCode(), "twitter not enabled", null);
        }
    }

    void setTwitterId(String str) {
        getEditor().putString(TWITTER_ID, str).commit();
    }

    void setTwitterRequestSecretToken(String str) {
        getEditor().putString(TWITTER_REQUEST_SECRET_TOKEN, str).commit();
    }

    void setTwitterRequestToken(String str) {
        getEditor().putString(TWITTER_REQUEST_TOKEN, str).commit();
    }

    void setTwitterSecretToken(String str) {
        getEditor().putString(TWITTER_SECRET_TOKEN, str).commit();
    }

    void setTwitterToken(String str) {
        getEditor().putString(TWITTER_TOKEN, str).commit();
    }
}
