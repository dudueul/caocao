package net.daum.mf.oauth;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public abstract class OAuthError {
    public static final String OAuthErrorMessageAccessDenied = "사용자가 승인을 거부했습니다.";
    public static final String OAuthErrorMessageInsufficientScope = "요청 권한이 부족합니다.";
    public static final String OAuthErrorMessageInvalidAuthorizationRequest = "유효한 인증 요청이 아닙니다.";
    public static final String OAuthErrorMessageInvalidResourceRequest = "정상적인 요청이 아닙니다.";
    public static final String OAuthErrorMessageInvalidScope = "유효한 권한 요청이 아닙니다.";
    public static final String OAuthErrorMessageInvalidToken = "유효한 토큰이 아닙니다.";
    public static final String OAuthErrorMessageNework = "네트워크가 원활하지 않습니다. 확인 후 다시 시도해 주세요.";
    public static final String OAuthErrorMessageServer = "일시적인 오류로 접속하지 못했습니다. 잠시 후 다시 시도 해주세요.";
    public static final String OAuthErrorMessageServiceNotFounde = "서비스를 찾을 수 없습니다.";
    public static final String OAuthErrorMessageUnauthorizedClient = "승인되지 않은 Client입니다.";
    public static final String OAuthErrorMessageUnknown = "알 수 없는 이유로 성공하지 못했습니다. 잠시 후 다시 시도 해주세요.";
    public static final String OAuthErrorMessageUnsupportedResponseType = "지원하지 않는 인증 방식입니다.";

    public enum OAuthErrorCodes {
        OAuthErrorNetwork,
        OAuthErrorServer,
        OAuthErrorServiceNotFound,
        OAuthErrorUnknown,
        OAuthErrorInvalidAuthorizationRequest,
        OAuthErrorUnauthorizedClient,
        OAuthErrorAccessDenied,
        OAuthErrorUnsupportedResponseType,
        OAuthErrorInvalidScope,
        OAuthErrorInvalidResourceRequest,
        OAuthErrorInvalidToken,
        OAuthErrorInsufficientScope
    }
}
