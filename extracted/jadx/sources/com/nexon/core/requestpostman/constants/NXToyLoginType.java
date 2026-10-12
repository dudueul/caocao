package com.nexon.core.requestpostman.constants;

import com.google.android.gms.games.GamesStatusCodes;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum NXToyLoginType {
    LoginTypeNotLogined(-1),
    LoginTypeDefault(0),
    LoginTypeNXCom(1),
    LoginTypeNaverChannel(3),
    LoginTypeEmail(4),
    LoginTypeDaumChannel(5),
    LoginTypeNXOneId(6),
    LoginTypeMapleId(7),
    LoginTypeNXNet(8),
    LoginTypeFaceBook(101),
    LoginTypeTwitter(102),
    LoginTypeGoogle(103),
    LoginTypeNaver(104),
    LoginTypeKakao(9001),
    LoginTypePlayPark(GamesStatusCodes.STATUS_VIDEO_PERMISSION_ERROR),
    LoginTypeVKontakte(GamesStatusCodes.STATUS_VIDEO_UNEXPECTED_CAPTURE_ERROR),
    LoginTypeGameCenter(9998),
    LoginTypeGuest(9999);

    private int value;

    NXToyLoginType(int i) {
        this.value = i;
    }

    public static NXToyLoginType convertIntLoginTypeToEnumLoginType(int i) {
        NXToyLoginType[] nXToyLoginTypeArrValues = values();
        for (int i2 = 0; i2 < nXToyLoginTypeArrValues.length; i2++) {
            if (nXToyLoginTypeArrValues[i2].Compare(i)) {
                return nXToyLoginTypeArrValues[i2];
            }
        }
        return LoginTypeNotLogined;
    }

    public static int getLoginTypeFromType(NXToyRequestTag nXToyRequestTag) {
        switch (nXToyRequestTag) {
            case LoginWithFB:
                return LoginTypeFaceBook.getValue();
            case LoginWithGoogleSignIn:
                return LoginTypeGoogle.getValue();
            case LoginWithGuest:
                return LoginTypeGuest.getValue();
            case LoginWithNX:
                return LoginTypeNXCom.getValue();
            case LoginWithKakao:
                return LoginTypeKakao.getValue();
            case LoginWithEmail:
                return LoginTypeEmail.getValue();
            case LoginWithTwitter:
                return LoginTypeTwitter.getValue();
            case LoginWithNaverChannel:
                return LoginTypeNaverChannel.getValue();
            case LoginWithNaver:
                return LoginTypeNaver.getValue();
            case LoginWithNXOneId:
                return LoginTypeNXOneId.getValue();
            case LoginWithMapleId:
                return LoginTypeMapleId.getValue();
            case LoginWithPlayPark:
                return LoginTypePlayPark.getValue();
            case LoginWithNXNet:
                return LoginTypeNXNet.getValue();
            case LoginWithDaumChannel:
                return LoginTypeDaumChannel.getValue();
            case LoginWithGameCenter:
                return LoginTypeGameCenter.getValue();
            case LoginWithVKontakte:
                return LoginTypeVKontakte.getValue();
            default:
                return LoginTypeGuest.getValue();
        }
    }

    public static int getLoginTypeFromTypeCode(int i) {
        switch (i) {
            case 9984:
                return LoginTypeVKontakte.getValue();
            case 9985:
                return LoginTypeGameCenter.getValue();
            case 9986:
            default:
                return LoginTypeNotLogined.getValue();
            case 9987:
                return LoginTypeNXNet.getValue();
            case 9988:
                return LoginTypePlayPark.getValue();
            case 9989:
                return LoginTypeMapleId.getValue();
            case 9990:
                return LoginTypeNXOneId.getValue();
            case 9991:
                return LoginTypeNaver.getValue();
            case 9992:
                return LoginTypeTwitter.getValue();
            case 9993:
                return LoginTypeEmail.getValue();
            case 9994:
                return LoginTypeDaumChannel.getValue();
            case 9995:
                return LoginTypeNaverChannel.getValue();
            case 9996:
                return LoginTypeKakao.getValue();
            case 9997:
                return LoginTypeGoogle.getValue();
            case 9998:
                return LoginTypeFaceBook.getValue();
            case 9999:
                return LoginTypeGuest.getValue();
        }
    }

    public static boolean isValidLoginType(int i) {
        switch (convertIntLoginTypeToEnumLoginType(i)) {
            case LoginTypeNXCom:
            case LoginTypeNaverChannel:
            case LoginTypeEmail:
            case LoginTypeDaumChannel:
            case LoginTypeNXOneId:
            case LoginTypeMapleId:
            case LoginTypeNXNet:
            case LoginTypeFaceBook:
            case LoginTypeTwitter:
            case LoginTypeGoogle:
            case LoginTypeNaver:
            case LoginTypeKakao:
            case LoginTypePlayPark:
            case LoginTypeGameCenter:
            case LoginTypeGuest:
            case LoginTypeVKontakte:
                return true;
            default:
                return false;
        }
    }

    public boolean Compare(int i) {
        return this.value == i;
    }

    public int getValue() {
        return this.value;
    }
}
