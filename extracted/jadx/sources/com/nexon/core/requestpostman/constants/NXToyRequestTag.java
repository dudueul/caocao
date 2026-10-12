package com.nexon.core.requestpostman.constants;

import android.support.v4.view.PointerIconCompat;
import com.google.android.gms.games.GamesStatusCodes;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum NXToyRequestTag {
    LoginWithNX(0),
    GetTerms(1),
    AgreeTerms(2),
    Logout(3),
    UnregisterSVC(4),
    GetUserInfo(5),
    CreateNPToken(6),
    EditUserInfo(7),
    RegisterPush(8),
    UnregisterPush(9),
    GetSvcInfo(10),
    EncryptForJoinNXKWithGPlus(11),
    EncryptForNXKRealNameAuthentication(12),
    GetFriends(13),
    GetBannerList(14),
    GetNexonSN(15),
    GetInitData(16),
    PutCouponPin(17),
    IncrBannerClickCount(18),
    GetSvcInfoForCS(19),
    RecoverUser(20),
    CheckEmailAccountRegistered(21),
    EmailAccountSignUp(22),
    EmailAccountResetPassword(23),
    GetClientID(24),
    GetPlateInfo(25),
    GetTermsList(26),
    GetGameInfo(27),
    GetNPSNs(28),
    GetTokenList(29),
    GameServiceScreen(30),
    SaveTermsOfAgree(31),
    GetCountry(32),
    GetEmailUserInfo(33),
    CreateMGToken(34),
    ValidateMGToken(35),
    GetMigrationInfo(36),
    Migration(37),
    EnterToy(38),
    OnPush(39),
    OffPush(40),
    GetPolicy(41),
    ValidatePolicy(42),
    BannerClick(43),
    BannerClosed(44),
    EndingBannerClick(45),
    EndingBannerExit(46),
    EndingBannerClosed(47),
    RequestPermissions(48),
    GetPromotion(49),
    ShowPromotion(50),
    RecvRemoteNotification(51),
    ShowNotice(52),
    GetAdId(53),
    PromotionMeta(54),
    PromotionPid(55),
    WebShop(56),
    GetPushPolicy(57),
    SetPushPolicy(58),
    ShowPushMenu(59),
    ShowPushNSMSSetting(60),
    GetSmsEnabled(61),
    GetPolicyList(62),
    PutPhoneNumberPolicy(63),
    GetPhoneNumberPolicy(64),
    DeletePhoneNumberPolicy(65),
    PutEmailPolicy(66),
    GetEmailPolicy(67),
    DeleteEmailPolicy(68),
    PutFundsSettlementPolicy(69),
    GetFundsSettlementPolicy(70),
    RegisterFbBizToken(71),
    AgreeTermsWithoutUpdateToyToken(72),
    SetupSecondPassword(73),
    VerifySecondPassword(74),
    GetSecondPasswordInfo(75),
    RegisterSecondPassword(76),
    UnregisterSecondPassword(77),
    ResetSecondPassword(78),
    SetSecondPassword(79),
    SendSecondPasswordRegisteredEmail(80),
    ShowToday(81),
    ShowOfferwall(82),
    UNKNOWN_RequestTag(PointerIconCompat.TYPE_WAIT),
    ShowAccountMenu(BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT),
    IsNewGCID(5001),
    LinkGCIDToNPSN(5002),
    LoginWithGCID(5003),
    CreateMGTokenForGcid(5004),
    ValidateMGTokenForGcid(5005),
    MigrationForGcid(5006),
    RecoverUserWithGcid(5007),
    GetNPSN(GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED),
    GetNpsnWithTwitter(GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_NOT_TRUSTED_TESTER),
    GetNpsnWithEmail(GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_INVALID_MULTIPLAYER_TYPE),
    GetNpsnWithDaumChannel(6003),
    GetNpsnWithNaverChannel(GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_INVALID_OPERATION),
    GetNpsnWithGPlus(6005),
    GetNpsnWithFB(6006),
    GetNpsnWithGuest(6007),
    GetNpsnWithNX(6008),
    GetNpsnWithNXOneId(6009),
    GetNpsnWithNaver(6010),
    GetNexonSNByNaverChannel(6104),
    GetNexonSNByMembershipId(6105),
    SNSConnect(7000),
    SNSConnectWithFacebook(7001),
    SNSConnectWithTwitter(GamesStatusCodes.STATUS_INVALID_REAL_TIME_ROOM_ID),
    SNSConnectWithGPlus(GamesStatusCodes.STATUS_PARTICIPANT_NOT_CONNECTED),
    SNSDisconnect(7050),
    SNSDisconnectWithFacebook(7051),
    SNSDisconnectWithTwitter(7052),
    SNSDisconnectWithGPlus(7053),
    SNSGetUserInfoWithFacebook(7100),
    SNSGetUserInfoWithTwitter(7101),
    SNSGetUserInfoWithGPlus(7102),
    GetSnsConnectionStatus(7200),
    PostFacebook(7300),
    PostImageFacebook(7301),
    PostGooglePlus(7302),
    PostImageGooglePlus(7303),
    FetchDeferredAppLink(7400),
    AppInviteFacebook(7401),
    ShareFacebook(7402),
    ShareImageFacebook(7403),
    DataBackup(7500),
    DataRestore(7501),
    PlateButtonClick(7600),
    PlateClosed(7601),
    LinkPaidGoogleAccount(8000),
    CheckPaidGoogleAccount(GamesStatusCodes.STATUS_MILESTONE_CLAIM_FAILED),
    LoadAchievementData(GamesStatusCodes.STATUS_QUEST_NO_LONGER_AVAILABLE),
    IncrementAchievementImmediate(GamesStatusCodes.STATUS_QUEST_NOT_STARTED),
    UnlockAchievementImmediate(8004),
    SetStepsAchievementImmediate(8005),
    SubmitScoreImmediate(8006),
    LoadCurrentPlayerScore(8007),
    ConnectGamePlatform(8008),
    DisconnectGamePlatform(8009),
    ScreenCapture(8010),
    GetPlayerStats(8011),
    LogoutGamePlatform(8012),
    Login(GamesStatusCodes.STATUS_VIDEO_NOT_ACTIVE),
    LoginWithVKontakte(9984),
    LoginWithGameCenter(9985),
    LoginWithNXNet(9987),
    LoginWithPlayPark(9988),
    LoginWithMapleId(9989),
    LoginWithNXOneId(9990),
    LoginWithNaver(9991),
    LoginWithTwitter(9992),
    LoginWithEmail(9993),
    LoginWithDaumChannel(9994),
    LoginWithNaverChannel(9995),
    LoginWithKakao(9996),
    LoginWithGoogleSignIn(9997),
    LoginWithFB(9998),
    LoginWithGuest(9999),
    GetLoginType(10000),
    BillingPayment(BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT),
    BillingFinish(20001),
    BillingRestore(20002),
    BillingRequestProduct(20003);

    private int value;

    NXToyRequestTag(int i) {
        this.value = i;
    }

    public static NXToyRequestTag convertIntRequestTagToEnumRequestTag(int i) {
        NXToyRequestTag[] nXToyRequestTagArrValues = values();
        for (int i2 = 0; i2 < nXToyRequestTagArrValues.length; i2++) {
            if (nXToyRequestTagArrValues[i2].Compare(i)) {
                return nXToyRequestTagArrValues[i2];
            }
        }
        return UNKNOWN_RequestTag;
    }

    public static int convertLoginTypeToRequestTag(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return LoginWithFB.getValue();
            case LoginTypeGoogle:
                return LoginWithGoogleSignIn.getValue();
            case LoginTypeGuest:
                return LoginWithGuest.getValue();
            case LoginTypeNXCom:
                return LoginWithNX.getValue();
            case LoginTypeKakao:
                return LoginWithKakao.getValue();
            case LoginTypeEmail:
                return LoginWithEmail.getValue();
            case LoginTypeTwitter:
                return LoginWithTwitter.getValue();
            case LoginTypeNaverChannel:
                return LoginWithNaverChannel.getValue();
            case LoginTypeNaver:
                return LoginWithNaver.getValue();
            case LoginTypeNXOneId:
                return LoginWithNXOneId.getValue();
            case LoginTypeMapleId:
                return LoginWithMapleId.getValue();
            case LoginTypePlayPark:
                return LoginWithPlayPark.getValue();
            case LoginTypeNXNet:
                return LoginWithNXNet.getValue();
            case LoginTypeDaumChannel:
                return LoginWithDaumChannel.getValue();
            case LoginTypeGameCenter:
                return LoginWithGameCenter.getValue();
            case LoginTypeVKontakte:
                return LoginWithVKontakte.getValue();
            default:
                return LoginWithGuest.getValue();
        }
    }

    public static int getCodeFromLoginType(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return LoginWithFB.getValue();
            case LoginTypeGoogle:
                return LoginWithGoogleSignIn.getValue();
            case LoginTypeGuest:
                return LoginWithGuest.getValue();
            case LoginTypeNXCom:
                return LoginWithNX.getValue();
            case LoginTypeKakao:
                return LoginWithKakao.getValue();
            case LoginTypeEmail:
                return LoginWithEmail.getValue();
            case LoginTypeTwitter:
                return LoginWithTwitter.getValue();
            case LoginTypeNaverChannel:
                return LoginWithNaverChannel.getValue();
            case LoginTypeNaver:
                return LoginWithNaver.getValue();
            case LoginTypeNXOneId:
                return LoginWithNXOneId.getValue();
            case LoginTypeMapleId:
                return LoginWithMapleId.getValue();
            case LoginTypePlayPark:
                return LoginWithPlayPark.getValue();
            case LoginTypeNXNet:
                return LoginWithNXNet.getValue();
            case LoginTypeDaumChannel:
                return LoginWithDaumChannel.getValue();
            case LoginTypeGameCenter:
                return LoginWithGameCenter.getValue();
            case LoginTypeVKontakte:
                return LoginWithVKontakte.getValue();
            default:
                return LoginWithGuest.getValue();
        }
    }

    public static int getSNSDisconnectCodeFromSnsType(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return SNSDisconnectWithFacebook.getValue();
            case LoginTypeTwitter:
                return SNSDisconnectWithTwitter.getValue();
            default:
                return -1;
        }
    }

    public static int getSnsConnectCodeFromSnsType(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return SNSConnectWithFacebook.getValue();
            case LoginTypeTwitter:
                return SNSConnectWithTwitter.getValue();
            default:
                return -1;
        }
    }

    public static int getSnsUserInfoCodeFromSnsType(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return SNSGetUserInfoWithFacebook.getValue();
            case LoginTypeTwitter:
                return SNSGetUserInfoWithTwitter.getValue();
            default:
                return -1;
        }
    }

    public static NXToyRequestTag getTypeFromLoginType(int i) {
        switch (NXToyLoginType.convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeFaceBook:
                return LoginWithFB;
            case LoginTypeGoogle:
                return LoginWithGoogleSignIn;
            case LoginTypeGuest:
                return LoginWithGuest;
            case LoginTypeNXCom:
                return LoginWithNX;
            case LoginTypeKakao:
                return LoginWithKakao;
            case LoginTypeEmail:
                return LoginWithEmail;
            case LoginTypeTwitter:
                return LoginWithTwitter;
            case LoginTypeNaverChannel:
                return LoginWithNaverChannel;
            case LoginTypeNaver:
                return LoginWithNaver;
            case LoginTypeNXOneId:
                return LoginWithNXOneId;
            case LoginTypeMapleId:
                return LoginWithMapleId;
            case LoginTypePlayPark:
                return LoginWithPlayPark;
            case LoginTypeNXNet:
                return LoginWithNXNet;
            case LoginTypeDaumChannel:
                return LoginWithDaumChannel;
            case LoginTypeGameCenter:
                return LoginWithGameCenter;
            case LoginTypeVKontakte:
                return LoginWithVKontakte;
            default:
                return LoginWithGuest;
        }
    }

    public boolean Compare(int i) {
        return this.value == i;
    }

    public int getValue() {
        return this.value;
    }
}
