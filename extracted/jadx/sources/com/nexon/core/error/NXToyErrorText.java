package com.nexon.core.error;

import com.naver.glink.android.sdk.ChannelCodes;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.util.NXStringUtil;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyErrorText {

    public enum ResourceId {
        request_failed,
        check_network,
        invalid_args_error,
        idpw_error
    }

    public static void convertLocalizedText(String str, NXToyResult nXToyResult) {
        if (NXStringUtil.isNotNull(nXToyResult.errorText)) {
        }
        switch (NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(nXToyResult.errorCode)) {
            case PARSE_ERR1:
            case PARSE_ERR2:
            case PARSE_ERR3:
            case INVALID_NETWORK:
            case NETWORK_TIMEOUT:
            case NETWORK_UNAVAILABLE:
            case NETWORK_CONNECTION_LOST:
            case INVALID_ARGS:
                nXToyResult.errorText = getText(str, nXToyResult.errorCode);
                break;
        }
    }

    public static String getLocaleText(String str, ResourceId resourceId) {
        if (str.equals("de_DE")) {
            return getTextDEDE(resourceId);
        }
        if (str.equals("ja_JP")) {
            return getTextJAJP(resourceId);
        }
        if (str.equals("ko_KR")) {
            return getTextKOKR(resourceId);
        }
        if (str.equals("es_ES")) {
            return getTextESES(resourceId);
        }
        if (str.equals("pt_BR")) {
            return getTextPTBR(resourceId);
        }
        if (str.equals("ru_RU")) {
            return getTextRURU(resourceId);
        }
        if (str.equals(ChannelCodes.CHINESE_SIMPLIFIED)) {
            return getTextZHCN(resourceId);
        }
        return str.equals(ChannelCodes.CHINESE_TRADITIONAL) ? getTextZHTW(resourceId) : getTextENUS(resourceId);
    }

    public static String getText(String str, int i) {
        switch (NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(i)) {
            case PARSE_ERR1:
            case PARSE_ERR2:
            case PARSE_ERR3:
                return getLocaleText(str, ResourceId.request_failed);
            case INVALID_NETWORK:
            case NETWORK_TIMEOUT:
            case NETWORK_UNAVAILABLE:
            case NETWORK_CONNECTION_LOST:
                return getLocaleText(str, ResourceId.check_network);
            case INVALID_ARGS:
                return getLocaleText(str, ResourceId.invalid_args_error);
            default:
                return "";
        }
    }

    private static String getTextDEDE(ResourceId resourceId) {
        switch (resourceId) {
            case request_failed:
                return "Anfrage gescheitert.";
            case check_network:
                return "Anfrage gescheitert. Bitte Netzwerk überprüfen.";
            case invalid_args_error:
                return "Fehler bei Eingabeparametern";
            case idpw_error:
                return "Fehler bei Kennung oder Passwort";
            default:
                return "";
        }
    }

    private static String getTextENUS(ResourceId resourceId) {
        switch (resourceId) {
            case request_failed:
                return "Request failed.";
            case check_network:
                return "Request failed. Please check your network settings.";
            case invalid_args_error:
                return "Please check input parameters";
            case idpw_error:
                return "ID or password error";
            default:
                return "";
        }
    }

    private static String getTextESES(ResourceId resourceId) {
        switch (resourceId) {
            case request_failed:
                return "Error de solicitud";
            case check_network:
                return "Error de solicitud. Compruebe las conexiones de red.";
            case invalid_args_error:
                return "Errores relacionados al ingreso de parámetros";
            case idpw_error:
                return "Error de ID o Contraseña";
            default:
                return "";
        }
    }

    private static String getTextJAJP(ResourceId resourceId) {
        switch (resourceId) {
            case request_failed:
                return "要請に失敗しました。";
            case check_network:
                return "要請に失敗しました。ネットワーク環境をチェックしてください。";
            case invalid_args_error:
                return "入力パラメーターエラーです。";
            case idpw_error:
                return "IDまたはパスワードのエラーです。";
            default:
                return "";
        }
    }

    private static String getTextKOKR(ResourceId resourceId) {
        switch (resourceId) {
            case request_failed:
                return "요청에 실패하였습니다.";
            case check_network:
                return "요청에 실패하였습니다. 네트워크 환경을 체크해주세요.";
            case invalid_args_error:
                return "입력 파라미터 오류 입니다.";
            case idpw_error:
                return "아이디 또는 비밀번호 오류입니다.";
            default:
                return "";
        }
    }

    private static String getTextPTBR(ResourceId resourceId) {
        switch (resourceId) {
            case request_failed:
                return "Falhou ao pedido.";
            case check_network:
                return "Falho ao pedido. Confirme a condição do rede.";
            case invalid_args_error:
                return "Erro de parâmetro";
            case idpw_error:
                return "Erro de ID ou senha";
            default:
                return "";
        }
    }

    private static String getTextRURU(ResourceId resourceId) {
        switch (resourceId) {
            case request_failed:
                return "Не удалось отправить запрос.";
            case check_network:
                return "Не удалось отправить запрос. Проверьте состояние сети.";
            case invalid_args_error:
                return "Ошибка ввода параметра";
            case idpw_error:
                return "Ошибка логина или пароля";
            default:
                return "";
        }
    }

    private static String getTextZHCN(ResourceId resourceId) {
        switch (resourceId) {
            case request_failed:
                return "请求失败。";
            case check_network:
                return "请求失败，请确认网络环境。";
            case invalid_args_error:
                return "输入参数错误。";
            case idpw_error:
                return "ID或帐号错误。";
            default:
                return "";
        }
    }

    private static String getTextZHTW(ResourceId resourceId) {
        switch (resourceId) {
            case request_failed:
                return "請求失敗。";
            case check_network:
                return "請求失敗。請確認網路環境。";
            case invalid_args_error:
                return "輸入參數錯誤";
            case idpw_error:
                return "ID或錯誤帳號";
            default:
                return "";
        }
    }
}
