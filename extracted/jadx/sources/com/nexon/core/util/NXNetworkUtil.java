package com.nexon.core.util;

import com.naver.glink.android.sdk.ChannelCodes;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.result.NXToyResult;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXNetworkUtil {

    public enum ResourceId {
        request_failed,
        check_network,
        invalid_args_error,
        idpw_error
    }

    public static void convertLocalizedText(String str, NXToyResult nXToyResult) {
        switch (NXToyErrorCode.convertIntErrorCodeToEnumErrorCode(nXToyResult.errorCode)) {
            case PARSE_ERR1:
            case PARSE_ERR2:
            case PARSE_ERR3:
                nXToyResult.errorText = getLocaleText(str, ResourceId.request_failed);
                break;
            case INVALID_NETWORK:
            case NETWORK_TIMEOUT:
            case NETWORK_UNAVAILABLE:
            case NETWORK_CONNECTION_LOST:
                nXToyResult.errorText = getLocaleText(str, ResourceId.check_network);
                break;
            case INVALID_ARGS:
                nXToyResult.errorText = getLocaleText(str, ResourceId.invalid_args_error);
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

    public static String getipAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress()) {
                        return inetAddressNextElement.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
        }
        return null;
    }

    public static NXToyResult makeErrorResult(int i, String str, String str2, int i2, Class<? extends NXToyResult> cls) {
        NXToyResult nXToyResultMakeErrorResult = makeErrorResult(i, str, str2, cls);
        nXToyResultMakeErrorResult.requestTag = i2;
        return nXToyResultMakeErrorResult;
    }

    public static NXToyResult makeErrorResult(int i, String str, String str2, Class<? extends NXToyResult> cls) {
        NXToyResult nXToyResult;
        try {
            nXToyResult = cls.newInstance();
        } catch (IllegalAccessException e) {
            nXToyResult = new NXToyResult(i, str, str2);
        } catch (InstantiationException e2) {
            nXToyResult = new NXToyResult(i, str, str2);
        }
        nXToyResult.errorCode = i;
        nXToyResult.errorText = str;
        nXToyResult.errorDetail = str2;
        return nXToyResult;
    }
}
