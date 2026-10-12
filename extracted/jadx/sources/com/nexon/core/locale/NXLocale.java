package com.nexon.core.locale;

import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.naver.glink.android.sdk.ChannelCodes;
import com.naver.glink.android.sdk.ui.record.c;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.Locale;
import twitter4j.HttpResponseCode;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXLocale {

    /* JADX INFO: renamed from: com.nexon.core.locale.NXLocale$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY;
        static final /* synthetic */ int[] $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE = new int[LOCALE.values().length];

        static {
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_AU.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.MS_BN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ZH_CN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_GU.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.CH_GU.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_HK.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ZH_HK.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ID_ID.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.JA_JP.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.KO_KR.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_MO.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.PT_MO.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ZH_MO.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.MS_MY.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.MN_MN.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_NZ.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.MI_NZ.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_PH.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.TL_PH.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.MS_SG.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_SG.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ZH_SG.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.SI_LK.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.TA_LK.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_LK.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ZH_TW.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.TH_TH.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.VI_VN.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.SQ_AL.ordinal()] = 29;
            } catch (NoSuchFieldError e29) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.NL_BE.ordinal()] = 30;
            } catch (NoSuchFieldError e30) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_BE.ordinal()] = 31;
            } catch (NoSuchFieldError e31) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.DE_BE.ordinal()] = 32;
            } catch (NoSuchFieldError e32) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EL_CY.ordinal()] = 33;
            } catch (NoSuchFieldError e33) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.TR_CY.ordinal()] = 34;
            } catch (NoSuchFieldError e34) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.CS_CZ.ordinal()] = 35;
            } catch (NoSuchFieldError e35) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.DA_DK.ordinal()] = 36;
            } catch (NoSuchFieldError e36) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.DE_DE.ordinal()] = 37;
            } catch (NoSuchFieldError e37) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ES_ES.ordinal()] = 38;
            } catch (NoSuchFieldError e38) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_FR.ordinal()] = 39;
            } catch (NoSuchFieldError e39) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.IS_IS.ordinal()] = 40;
            } catch (NoSuchFieldError e40) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.GA_IE.ordinal()] = 41;
            } catch (NoSuchFieldError e41) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.IT_IT.ordinal()] = 42;
            } catch (NoSuchFieldError e42) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.DE_LI.ordinal()] = 43;
            } catch (NoSuchFieldError e43) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.DE_LU.ordinal()] = 44;
            } catch (NoSuchFieldError e44) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_LU.ordinal()] = 45;
            } catch (NoSuchFieldError e45) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.LB_LU.ordinal()] = 46;
            } catch (NoSuchFieldError e46) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.MK_MK.ordinal()] = 47;
            } catch (NoSuchFieldError e47) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.HU_HU.ordinal()] = 48;
            } catch (NoSuchFieldError e48) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.MT_MT.ordinal()] = 49;
            } catch (NoSuchFieldError e49) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_MT.ordinal()] = 50;
            } catch (NoSuchFieldError e50) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.IT_MT.ordinal()] = 51;
            } catch (NoSuchFieldError e51) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.RO_MD.ordinal()] = 52;
            } catch (NoSuchFieldError e52) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.SR_ME.ordinal()] = 53;
            } catch (NoSuchFieldError e53) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.NL_NL.ordinal()] = 54;
            } catch (NoSuchFieldError e54) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.NO_NO.ordinal()] = 55;
            } catch (NoSuchFieldError e55) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.DE_AT.ordinal()] = 56;
            } catch (NoSuchFieldError e56) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.PL_PL.ordinal()] = 57;
            } catch (NoSuchFieldError e57) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.PT_PT.ordinal()] = 58;
            } catch (NoSuchFieldError e58) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.RU_RU.ordinal()] = 59;
            } catch (NoSuchFieldError e59) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.DE_CH.ordinal()] = 60;
            } catch (NoSuchFieldError e60) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_CH.ordinal()] = 61;
            } catch (NoSuchFieldError e61) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.IT_CH.ordinal()] = 62;
            } catch (NoSuchFieldError e62) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FI_FI.ordinal()] = 63;
            } catch (NoSuchFieldError e63) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.SV_FI.ordinal()] = 64;
            } catch (NoSuchFieldError e64) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.SV_SE.ordinal()] = 65;
            } catch (NoSuchFieldError e65) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.TR_TR.ordinal()] = 66;
            } catch (NoSuchFieldError e66) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_GB.ordinal()] = 67;
            } catch (NoSuchFieldError e67) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_AI.ordinal()] = 68;
            } catch (NoSuchFieldError e68) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_AG.ordinal()] = 69;
            } catch (NoSuchFieldError e69) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_AR.ordinal()] = 70;
            } catch (NoSuchFieldError e70) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_BB.ordinal()] = 71;
            } catch (NoSuchFieldError e71) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_BZ.ordinal()] = 72;
            } catch (NoSuchFieldError e72) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_BM.ordinal()] = 73;
            } catch (NoSuchFieldError e73) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_BO.ordinal()] = 74;
            } catch (NoSuchFieldError e74) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.PT_BR.ordinal()] = 75;
            } catch (NoSuchFieldError e75) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_VG.ordinal()] = 76;
            } catch (NoSuchFieldError e76) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_KY.ordinal()] = 77;
            } catch (NoSuchFieldError e77) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_CL.ordinal()] = 78;
            } catch (NoSuchFieldError e78) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_CO.ordinal()] = 79;
            } catch (NoSuchFieldError e79) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_CR.ordinal()] = 80;
            } catch (NoSuchFieldError e80) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_DM.ordinal()] = 81;
            } catch (NoSuchFieldError e81) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_DO.ordinal()] = 82;
            } catch (NoSuchFieldError e82) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_EC.ordinal()] = 83;
            } catch (NoSuchFieldError e83) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_SV.ordinal()] = 84;
            } catch (NoSuchFieldError e84) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_GD.ordinal()] = 85;
            } catch (NoSuchFieldError e85) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_GT.ordinal()] = 86;
            } catch (NoSuchFieldError e86) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_GY.ordinal()] = 87;
            } catch (NoSuchFieldError e87) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.HI_GY.ordinal()] = 88;
            } catch (NoSuchFieldError e88) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.UR_GY.ordinal()] = 89;
            } catch (NoSuchFieldError e89) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_HN.ordinal()] = 90;
            } catch (NoSuchFieldError e90) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_JM.ordinal()] = 91;
            } catch (NoSuchFieldError e91) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_MX.ordinal()] = 92;
            } catch (NoSuchFieldError e92) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_MS.ordinal()] = 93;
            } catch (NoSuchFieldError e93) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_NI.ordinal()] = 94;
            } catch (NoSuchFieldError e94) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_PA.ordinal()] = 95;
            } catch (NoSuchFieldError e95) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_PY.ordinal()] = 96;
            } catch (NoSuchFieldError e96) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.GN_PY.ordinal()] = 97;
            } catch (NoSuchFieldError e97) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AY_PE.ordinal()] = 98;
            } catch (NoSuchFieldError e98) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_PE.ordinal()] = 99;
            } catch (NoSuchFieldError e99) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.QY_PE.ordinal()] = 100;
            } catch (NoSuchFieldError e100) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_KN.ordinal()] = 101;
            } catch (NoSuchFieldError e101) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_LC.ordinal()] = 102;
            } catch (NoSuchFieldError e102) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_VC.ordinal()] = 103;
            } catch (NoSuchFieldError e103) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_VC.ordinal()] = 104;
            } catch (NoSuchFieldError e104) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.NL_SR.ordinal()] = 105;
            } catch (NoSuchFieldError e105) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_BS.ordinal()] = 106;
            } catch (NoSuchFieldError e106) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_TT.ordinal()] = 107;
            } catch (NoSuchFieldError e107) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_TC.ordinal()] = 108;
            } catch (NoSuchFieldError e108) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_UY.ordinal()] = 109;
            } catch (NoSuchFieldError e109) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_VE.ordinal()] = 110;
            } catch (NoSuchFieldError e110) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_CA.ordinal()] = 111;
            } catch (NoSuchFieldError e111) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_CA.ordinal()] = 112;
            } catch (NoSuchFieldError e112) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_PR.ordinal()] = 113;
            } catch (NoSuchFieldError e113) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_PR.ordinal()] = 114;
            } catch (NoSuchFieldError e114) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_US.ordinal()] = 115;
            } catch (NoSuchFieldError e115) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.HY_AM.ordinal()] = 116;
            } catch (NoSuchFieldError e116) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_BH.ordinal()] = 117;
            } catch (NoSuchFieldError e117) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_BW.ordinal()] = 118;
            } catch (NoSuchFieldError e118) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_CM.ordinal()] = 119;
            } catch (NoSuchFieldError e119) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_CM.ordinal()] = 120;
            } catch (NoSuchFieldError e120) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_CI.ordinal()] = 121;
            } catch (NoSuchFieldError e121) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_EG.ordinal()] = 122;
            } catch (NoSuchFieldError e122) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.PT_GW.ordinal()] = 123;
            } catch (NoSuchFieldError e123) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_GN.ordinal()] = 124;
            } catch (NoSuchFieldError e124) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ET_GQ.ordinal()] = 125;
            } catch (NoSuchFieldError e125) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_GQ.ordinal()] = 126;
            } catch (NoSuchFieldError e126) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.HI_IN.ordinal()] = 127;
            } catch (NoSuchFieldError e127) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_IN.ordinal()] = 128;
            } catch (NoSuchFieldError e128) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.HE_IL.ordinal()] = 129;
            } catch (NoSuchFieldError e129) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_IL.ordinal()] = 130;
            } catch (NoSuchFieldError e130) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_JO.ordinal()] = 131;
            } catch (NoSuchFieldError e131) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_KE.ordinal()] = 132;
            } catch (NoSuchFieldError e132) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_KW.ordinal()] = 133;
            } catch (NoSuchFieldError e133) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_LB.ordinal()] = 134;
            } catch (NoSuchFieldError e134) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_MG.ordinal()] = 135;
            } catch (NoSuchFieldError e135) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.MG_MG.ordinal()] = 136;
            } catch (NoSuchFieldError e136) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_ML.ordinal()] = 137;
            } catch (NoSuchFieldError e137) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_MA.ordinal()] = 138;
            } catch (NoSuchFieldError e138) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_MU.ordinal()] = 139;
            } catch (NoSuchFieldError e139) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.PT_MZ.ordinal()] = 140;
            } catch (NoSuchFieldError e140) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_NE.ordinal()] = 141;
            } catch (NoSuchFieldError e141) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_NG.ordinal()] = 142;
            } catch (NoSuchFieldError e142) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_OM.ordinal()] = 143;
            } catch (NoSuchFieldError e143) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_QA.ordinal()] = 144;
            } catch (NoSuchFieldError e144) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_CF.ordinal()] = 145;
            } catch (NoSuchFieldError e145) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.SG_CF.ordinal()] = 146;
            } catch (NoSuchFieldError e146) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_SA.ordinal()] = 147;
            } catch (NoSuchFieldError e147) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.FR_SN.ordinal()] = 148;
            } catch (NoSuchFieldError e148) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AF_ZA.ordinal()] = 149;
            } catch (NoSuchFieldError e149) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.EN_ZA.ordinal()] = 150;
            } catch (NoSuchFieldError e150) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.ZU_ZA.ordinal()] = 151;
            } catch (NoSuchFieldError e151) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_TN.ordinal()] = 152;
            } catch (NoSuchFieldError e152) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[LOCALE.AR_AE.ordinal()] = 153;
            } catch (NoSuchFieldError e153) {
            }
            $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY = new int[COUNTRY.values().length];
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Afghanistan.ordinal()] = 1;
            } catch (NoSuchFieldError e154) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.f0landIslands.ordinal()] = 2;
            } catch (NoSuchFieldError e155) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Albania.ordinal()] = 3;
            } catch (NoSuchFieldError e156) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Algeria.ordinal()] = 4;
            } catch (NoSuchFieldError e157) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.AmericanSamoa.ordinal()] = 5;
            } catch (NoSuchFieldError e158) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Andorra.ordinal()] = 6;
            } catch (NoSuchFieldError e159) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Angola.ordinal()] = 7;
            } catch (NoSuchFieldError e160) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Anguilla.ordinal()] = 8;
            } catch (NoSuchFieldError e161) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Antarctica.ordinal()] = 9;
            } catch (NoSuchFieldError e162) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.AntiguaandBarbuda.ordinal()] = 10;
            } catch (NoSuchFieldError e163) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Argentina.ordinal()] = 11;
            } catch (NoSuchFieldError e164) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Armenia.ordinal()] = 12;
            } catch (NoSuchFieldError e165) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Aruba.ordinal()] = 13;
            } catch (NoSuchFieldError e166) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Australia.ordinal()] = 14;
            } catch (NoSuchFieldError e167) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Austria.ordinal()] = 15;
            } catch (NoSuchFieldError e168) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Azerbaijan.ordinal()] = 16;
            } catch (NoSuchFieldError e169) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Bahamas.ordinal()] = 17;
            } catch (NoSuchFieldError e170) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Bahrain.ordinal()] = 18;
            } catch (NoSuchFieldError e171) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Bangladesh.ordinal()] = 19;
            } catch (NoSuchFieldError e172) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Barbados.ordinal()] = 20;
            } catch (NoSuchFieldError e173) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Belarus.ordinal()] = 21;
            } catch (NoSuchFieldError e174) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Belgium.ordinal()] = 22;
            } catch (NoSuchFieldError e175) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Belize.ordinal()] = 23;
            } catch (NoSuchFieldError e176) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Benin.ordinal()] = 24;
            } catch (NoSuchFieldError e177) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Bermuda.ordinal()] = 25;
            } catch (NoSuchFieldError e178) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Bhutan.ordinal()] = 26;
            } catch (NoSuchFieldError e179) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Bolivia.ordinal()] = 27;
            } catch (NoSuchFieldError e180) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.BonaireSintEustatiusandSaba.ordinal()] = 28;
            } catch (NoSuchFieldError e181) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.BosniaandHerzegovina.ordinal()] = 29;
            } catch (NoSuchFieldError e182) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Botswana.ordinal()] = 30;
            } catch (NoSuchFieldError e183) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.BouvetIsland.ordinal()] = 31;
            } catch (NoSuchFieldError e184) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Brazil.ordinal()] = 32;
            } catch (NoSuchFieldError e185) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.BritishIndianOceanTerritory.ordinal()] = 33;
            } catch (NoSuchFieldError e186) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.BruneiDarussalam.ordinal()] = 34;
            } catch (NoSuchFieldError e187) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Bulgaria.ordinal()] = 35;
            } catch (NoSuchFieldError e188) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.BurkinaFaso.ordinal()] = 36;
            } catch (NoSuchFieldError e189) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Burundi.ordinal()] = 37;
            } catch (NoSuchFieldError e190) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.CaboVerde.ordinal()] = 38;
            } catch (NoSuchFieldError e191) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Cambodia.ordinal()] = 39;
            } catch (NoSuchFieldError e192) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Cameroon.ordinal()] = 40;
            } catch (NoSuchFieldError e193) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Canada.ordinal()] = 41;
            } catch (NoSuchFieldError e194) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.CaymanIslands.ordinal()] = 42;
            } catch (NoSuchFieldError e195) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.CentralAfricanRepublic.ordinal()] = 43;
            } catch (NoSuchFieldError e196) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Chad.ordinal()] = 44;
            } catch (NoSuchFieldError e197) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Chile.ordinal()] = 45;
            } catch (NoSuchFieldError e198) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.China.ordinal()] = 46;
            } catch (NoSuchFieldError e199) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.ChristmasIsland.ordinal()] = 47;
            } catch (NoSuchFieldError e200) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.CocosIslands.ordinal()] = 48;
            } catch (NoSuchFieldError e201) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Colombia.ordinal()] = 49;
            } catch (NoSuchFieldError e202) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Comoros.ordinal()] = 50;
            } catch (NoSuchFieldError e203) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Congo.ordinal()] = 51;
            } catch (NoSuchFieldError e204) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.DemocraticRepublicoftheCongo.ordinal()] = 52;
            } catch (NoSuchFieldError e205) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.CookIslands.ordinal()] = 53;
            } catch (NoSuchFieldError e206) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.CostaRica.ordinal()] = 54;
            } catch (NoSuchFieldError e207) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.IvoryCoast.ordinal()] = 55;
            } catch (NoSuchFieldError e208) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Croatia.ordinal()] = 56;
            } catch (NoSuchFieldError e209) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Cuba.ordinal()] = 57;
            } catch (NoSuchFieldError e210) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Curacao.ordinal()] = 58;
            } catch (NoSuchFieldError e211) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Cyprus.ordinal()] = 59;
            } catch (NoSuchFieldError e212) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Czechia.ordinal()] = 60;
            } catch (NoSuchFieldError e213) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Denmark.ordinal()] = 61;
            } catch (NoSuchFieldError e214) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Djibouti.ordinal()] = 62;
            } catch (NoSuchFieldError e215) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Dominica.ordinal()] = 63;
            } catch (NoSuchFieldError e216) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.DominicanRepublic.ordinal()] = 64;
            } catch (NoSuchFieldError e217) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Ecuador.ordinal()] = 65;
            } catch (NoSuchFieldError e218) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Egypt.ordinal()] = 66;
            } catch (NoSuchFieldError e219) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.ElSalvador.ordinal()] = 67;
            } catch (NoSuchFieldError e220) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.EquatorialGuinea.ordinal()] = 68;
            } catch (NoSuchFieldError e221) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Eritrea.ordinal()] = 69;
            } catch (NoSuchFieldError e222) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Estonia.ordinal()] = 70;
            } catch (NoSuchFieldError e223) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Ethiopia.ordinal()] = 71;
            } catch (NoSuchFieldError e224) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.FalklandIslands.ordinal()] = 72;
            } catch (NoSuchFieldError e225) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.FaroeIslands.ordinal()] = 73;
            } catch (NoSuchFieldError e226) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Fiji.ordinal()] = 74;
            } catch (NoSuchFieldError e227) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Finland.ordinal()] = 75;
            } catch (NoSuchFieldError e228) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.France.ordinal()] = 76;
            } catch (NoSuchFieldError e229) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.FrenchGuiana.ordinal()] = 77;
            } catch (NoSuchFieldError e230) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.FrenchPolynesia.ordinal()] = 78;
            } catch (NoSuchFieldError e231) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.FrenchSouthernTerritories.ordinal()] = 79;
            } catch (NoSuchFieldError e232) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Gabon.ordinal()] = 80;
            } catch (NoSuchFieldError e233) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Gambia.ordinal()] = 81;
            } catch (NoSuchFieldError e234) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Georgia.ordinal()] = 82;
            } catch (NoSuchFieldError e235) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Germany.ordinal()] = 83;
            } catch (NoSuchFieldError e236) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Ghana.ordinal()] = 84;
            } catch (NoSuchFieldError e237) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Gibraltar.ordinal()] = 85;
            } catch (NoSuchFieldError e238) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Greece.ordinal()] = 86;
            } catch (NoSuchFieldError e239) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Greenland.ordinal()] = 87;
            } catch (NoSuchFieldError e240) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Grenada.ordinal()] = 88;
            } catch (NoSuchFieldError e241) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Guadeloupe.ordinal()] = 89;
            } catch (NoSuchFieldError e242) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Guam.ordinal()] = 90;
            } catch (NoSuchFieldError e243) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Guatemala.ordinal()] = 91;
            } catch (NoSuchFieldError e244) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Guernsey.ordinal()] = 92;
            } catch (NoSuchFieldError e245) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Guinea.ordinal()] = 93;
            } catch (NoSuchFieldError e246) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.GuineaBissau.ordinal()] = 94;
            } catch (NoSuchFieldError e247) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Guyana.ordinal()] = 95;
            } catch (NoSuchFieldError e248) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Haiti.ordinal()] = 96;
            } catch (NoSuchFieldError e249) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.HeardIslandandMcDonaldIslands.ordinal()] = 97;
            } catch (NoSuchFieldError e250) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.HolySee.ordinal()] = 98;
            } catch (NoSuchFieldError e251) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Honduras.ordinal()] = 99;
            } catch (NoSuchFieldError e252) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.HongKong.ordinal()] = 100;
            } catch (NoSuchFieldError e253) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Hungary.ordinal()] = 101;
            } catch (NoSuchFieldError e254) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Iceland.ordinal()] = 102;
            } catch (NoSuchFieldError e255) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.India.ordinal()] = 103;
            } catch (NoSuchFieldError e256) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Indonesia.ordinal()] = 104;
            } catch (NoSuchFieldError e257) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Iran.ordinal()] = 105;
            } catch (NoSuchFieldError e258) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Iraq.ordinal()] = 106;
            } catch (NoSuchFieldError e259) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Ireland.ordinal()] = 107;
            } catch (NoSuchFieldError e260) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.IsleofMan.ordinal()] = 108;
            } catch (NoSuchFieldError e261) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Israel.ordinal()] = 109;
            } catch (NoSuchFieldError e262) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Italy.ordinal()] = 110;
            } catch (NoSuchFieldError e263) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Jamaica.ordinal()] = 111;
            } catch (NoSuchFieldError e264) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Japan.ordinal()] = 112;
            } catch (NoSuchFieldError e265) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Jersey.ordinal()] = 113;
            } catch (NoSuchFieldError e266) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Jordan.ordinal()] = 114;
            } catch (NoSuchFieldError e267) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Kazakhstan.ordinal()] = 115;
            } catch (NoSuchFieldError e268) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Kenya.ordinal()] = 116;
            } catch (NoSuchFieldError e269) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Kiribati.ordinal()] = 117;
            } catch (NoSuchFieldError e270) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.NorthKorea.ordinal()] = 118;
            } catch (NoSuchFieldError e271) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Korea.ordinal()] = 119;
            } catch (NoSuchFieldError e272) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Kuwait.ordinal()] = 120;
            } catch (NoSuchFieldError e273) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Kyrgyzstan.ordinal()] = 121;
            } catch (NoSuchFieldError e274) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Laos.ordinal()] = 122;
            } catch (NoSuchFieldError e275) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Latvia.ordinal()] = 123;
            } catch (NoSuchFieldError e276) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Lebanon.ordinal()] = 124;
            } catch (NoSuchFieldError e277) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Lesotho.ordinal()] = 125;
            } catch (NoSuchFieldError e278) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Liberia.ordinal()] = 126;
            } catch (NoSuchFieldError e279) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Libya.ordinal()] = 127;
            } catch (NoSuchFieldError e280) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Liechtenstein.ordinal()] = 128;
            } catch (NoSuchFieldError e281) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Lithuania.ordinal()] = 129;
            } catch (NoSuchFieldError e282) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Luxembourg.ordinal()] = 130;
            } catch (NoSuchFieldError e283) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Macao.ordinal()] = 131;
            } catch (NoSuchFieldError e284) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Macedonia.ordinal()] = 132;
            } catch (NoSuchFieldError e285) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Madagascar.ordinal()] = 133;
            } catch (NoSuchFieldError e286) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Malawi.ordinal()] = 134;
            } catch (NoSuchFieldError e287) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Malaysia.ordinal()] = 135;
            } catch (NoSuchFieldError e288) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Maldives.ordinal()] = 136;
            } catch (NoSuchFieldError e289) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Mali.ordinal()] = 137;
            } catch (NoSuchFieldError e290) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Malta.ordinal()] = 138;
            } catch (NoSuchFieldError e291) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.MarshallIslands.ordinal()] = 139;
            } catch (NoSuchFieldError e292) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Martinique.ordinal()] = 140;
            } catch (NoSuchFieldError e293) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Mauritania.ordinal()] = 141;
            } catch (NoSuchFieldError e294) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Mauritius.ordinal()] = 142;
            } catch (NoSuchFieldError e295) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Mayotte.ordinal()] = 143;
            } catch (NoSuchFieldError e296) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Mexico.ordinal()] = 144;
            } catch (NoSuchFieldError e297) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Micronesia.ordinal()] = 145;
            } catch (NoSuchFieldError e298) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Moldova.ordinal()] = 146;
            } catch (NoSuchFieldError e299) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Monaco.ordinal()] = 147;
            } catch (NoSuchFieldError e300) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Mongolia.ordinal()] = 148;
            } catch (NoSuchFieldError e301) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Montenegro.ordinal()] = 149;
            } catch (NoSuchFieldError e302) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Montserrat.ordinal()] = 150;
            } catch (NoSuchFieldError e303) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Morocco.ordinal()] = 151;
            } catch (NoSuchFieldError e304) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Mozambique.ordinal()] = 152;
            } catch (NoSuchFieldError e305) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Myanmar.ordinal()] = 153;
            } catch (NoSuchFieldError e306) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Namibia.ordinal()] = 154;
            } catch (NoSuchFieldError e307) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Nauru.ordinal()] = 155;
            } catch (NoSuchFieldError e308) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Nepal.ordinal()] = 156;
            } catch (NoSuchFieldError e309) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Netherlands.ordinal()] = 157;
            } catch (NoSuchFieldError e310) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.NewCaledonia.ordinal()] = 158;
            } catch (NoSuchFieldError e311) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.NewZealand.ordinal()] = 159;
            } catch (NoSuchFieldError e312) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Nicaragua.ordinal()] = 160;
            } catch (NoSuchFieldError e313) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Niger.ordinal()] = 161;
            } catch (NoSuchFieldError e314) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Nigeria.ordinal()] = 162;
            } catch (NoSuchFieldError e315) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Niue.ordinal()] = 163;
            } catch (NoSuchFieldError e316) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.NorfolkIsland.ordinal()] = 164;
            } catch (NoSuchFieldError e317) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.NorthernMarianaIslands.ordinal()] = 165;
            } catch (NoSuchFieldError e318) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Norway.ordinal()] = 166;
            } catch (NoSuchFieldError e319) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Oman.ordinal()] = 167;
            } catch (NoSuchFieldError e320) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Pakistan.ordinal()] = 168;
            } catch (NoSuchFieldError e321) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Palau.ordinal()] = 169;
            } catch (NoSuchFieldError e322) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.PalestineStateof.ordinal()] = 170;
            } catch (NoSuchFieldError e323) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Panama.ordinal()] = 171;
            } catch (NoSuchFieldError e324) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.PapuaNewGuinea.ordinal()] = 172;
            } catch (NoSuchFieldError e325) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Paraguay.ordinal()] = 173;
            } catch (NoSuchFieldError e326) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Peru.ordinal()] = 174;
            } catch (NoSuchFieldError e327) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Philippines.ordinal()] = 175;
            } catch (NoSuchFieldError e328) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Pitcairn.ordinal()] = 176;
            } catch (NoSuchFieldError e329) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Poland.ordinal()] = 177;
            } catch (NoSuchFieldError e330) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Portugal.ordinal()] = 178;
            } catch (NoSuchFieldError e331) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.PuertoRico.ordinal()] = 179;
            } catch (NoSuchFieldError e332) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Qatar.ordinal()] = 180;
            } catch (NoSuchFieldError e333) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Reunion.ordinal()] = 181;
            } catch (NoSuchFieldError e334) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Romania.ordinal()] = 182;
            } catch (NoSuchFieldError e335) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Russia.ordinal()] = 183;
            } catch (NoSuchFieldError e336) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Rwanda.ordinal()] = 184;
            } catch (NoSuchFieldError e337) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.StBarts.ordinal()] = 185;
            } catch (NoSuchFieldError e338) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SaintHelenaAscensionandTristandaCunha.ordinal()] = 186;
            } catch (NoSuchFieldError e339) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SaintKittsandNevis.ordinal()] = 187;
            } catch (NoSuchFieldError e340) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SaintLucia.ordinal()] = 188;
            } catch (NoSuchFieldError e341) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SaintMartin.ordinal()] = 189;
            } catch (NoSuchFieldError e342) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SaintPierreandMiquelon.ordinal()] = 190;
            } catch (NoSuchFieldError e343) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SaintVincentandtheGrenadines.ordinal()] = 191;
            } catch (NoSuchFieldError e344) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Samoa.ordinal()] = 192;
            } catch (NoSuchFieldError e345) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SanMarino.ordinal()] = 193;
            } catch (NoSuchFieldError e346) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SaoTomeandPrincipe.ordinal()] = 194;
            } catch (NoSuchFieldError e347) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SaudiArabia.ordinal()] = 195;
            } catch (NoSuchFieldError e348) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Senegal.ordinal()] = 196;
            } catch (NoSuchFieldError e349) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Serbia.ordinal()] = 197;
            } catch (NoSuchFieldError e350) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Seychelles.ordinal()] = 198;
            } catch (NoSuchFieldError e351) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SierraLeone.ordinal()] = 199;
            } catch (NoSuchFieldError e352) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Singapore.ordinal()] = 200;
            } catch (NoSuchFieldError e353) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SintMaarten.ordinal()] = 201;
            } catch (NoSuchFieldError e354) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Slovakia.ordinal()] = 202;
            } catch (NoSuchFieldError e355) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Slovenia.ordinal()] = 203;
            } catch (NoSuchFieldError e356) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SolomonIslands.ordinal()] = 204;
            } catch (NoSuchFieldError e357) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Somalia.ordinal()] = 205;
            } catch (NoSuchFieldError e358) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SouthAfrica.ordinal()] = 206;
            } catch (NoSuchFieldError e359) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SouthGeorgiaandtheSouthSandwichIslands.ordinal()] = 207;
            } catch (NoSuchFieldError e360) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SouthSudan.ordinal()] = 208;
            } catch (NoSuchFieldError e361) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Spain.ordinal()] = 209;
            } catch (NoSuchFieldError e362) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SriLanka.ordinal()] = 210;
            } catch (NoSuchFieldError e363) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Sudan.ordinal()] = 211;
            } catch (NoSuchFieldError e364) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Suriname.ordinal()] = 212;
            } catch (NoSuchFieldError e365) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SvalbardandJanMayen.ordinal()] = 213;
            } catch (NoSuchFieldError e366) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Swaziland.ordinal()] = 214;
            } catch (NoSuchFieldError e367) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Sweden.ordinal()] = 215;
            } catch (NoSuchFieldError e368) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Switzerland.ordinal()] = 216;
            } catch (NoSuchFieldError e369) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.SyrianArabRepublic.ordinal()] = 217;
            } catch (NoSuchFieldError e370) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Taiwan.ordinal()] = 218;
            } catch (NoSuchFieldError e371) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Tajikistan.ordinal()] = 219;
            } catch (NoSuchFieldError e372) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Tanzania.ordinal()] = 220;
            } catch (NoSuchFieldError e373) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Thailand.ordinal()] = 221;
            } catch (NoSuchFieldError e374) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.TimorLeste.ordinal()] = 222;
            } catch (NoSuchFieldError e375) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Togo.ordinal()] = 223;
            } catch (NoSuchFieldError e376) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Tokelau.ordinal()] = 224;
            } catch (NoSuchFieldError e377) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Tonga.ordinal()] = 225;
            } catch (NoSuchFieldError e378) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.TrinidadandTobago.ordinal()] = 226;
            } catch (NoSuchFieldError e379) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Tunisia.ordinal()] = 227;
            } catch (NoSuchFieldError e380) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Turkey.ordinal()] = 228;
            } catch (NoSuchFieldError e381) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Turkmenistan.ordinal()] = 229;
            } catch (NoSuchFieldError e382) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.TurksandCaicosIslands.ordinal()] = 230;
            } catch (NoSuchFieldError e383) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Tuvalu.ordinal()] = 231;
            } catch (NoSuchFieldError e384) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Uganda.ordinal()] = 232;
            } catch (NoSuchFieldError e385) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Ukraine.ordinal()] = 233;
            } catch (NoSuchFieldError e386) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.UnitedArabEmirates.ordinal()] = 234;
            } catch (NoSuchFieldError e387) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.UnitedKingdomofGreatBritainandNorthernIreland.ordinal()] = 235;
            } catch (NoSuchFieldError e388) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.UnitedStatesMinorOutlyingIslands.ordinal()] = 236;
            } catch (NoSuchFieldError e389) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.UnitedStatesofAmerica.ordinal()] = 237;
            } catch (NoSuchFieldError e390) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Uruguay.ordinal()] = 238;
            } catch (NoSuchFieldError e391) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Uzbekistan.ordinal()] = 239;
            } catch (NoSuchFieldError e392) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Vanuatu.ordinal()] = 240;
            } catch (NoSuchFieldError e393) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Venezuela.ordinal()] = 241;
            } catch (NoSuchFieldError e394) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.VietNam.ordinal()] = 242;
            } catch (NoSuchFieldError e395) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.BritishVirginIslands.ordinal()] = 243;
            } catch (NoSuchFieldError e396) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.UnitedStatesVirginIslands.ordinal()] = 244;
            } catch (NoSuchFieldError e397) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.WallisandFutuna.ordinal()] = 245;
            } catch (NoSuchFieldError e398) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.WesternSahara.ordinal()] = 246;
            } catch (NoSuchFieldError e399) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Yemen.ordinal()] = 247;
            } catch (NoSuchFieldError e400) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Zambia.ordinal()] = 248;
            } catch (NoSuchFieldError e401) {
            }
            try {
                $SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[COUNTRY.Zimbabwe.ordinal()] = 249;
            } catch (NoSuchFieldError e402) {
            }
        }
    }

    public enum COUNTRY {
        Afghanistan,
        f0landIslands,
        Albania,
        Algeria,
        AmericanSamoa,
        Andorra,
        Angola,
        Anguilla,
        Antarctica,
        AntiguaandBarbuda,
        Argentina,
        Armenia,
        Aruba,
        Australia,
        Austria,
        Azerbaijan,
        Bahamas,
        Bahrain,
        Bangladesh,
        Barbados,
        Belarus,
        Belgium,
        Belize,
        Benin,
        Bermuda,
        Bhutan,
        Bolivia,
        BonaireSintEustatiusandSaba,
        BosniaandHerzegovina,
        Botswana,
        BouvetIsland,
        Brazil,
        BritishIndianOceanTerritory,
        BruneiDarussalam,
        Bulgaria,
        BurkinaFaso,
        Burundi,
        CaboVerde,
        Cambodia,
        Cameroon,
        Canada,
        CaymanIslands,
        CentralAfricanRepublic,
        Chad,
        Chile,
        China,
        ChristmasIsland,
        CocosIslands,
        Colombia,
        Comoros,
        Congo,
        DemocraticRepublicoftheCongo,
        CookIslands,
        CostaRica,
        IvoryCoast,
        Croatia,
        Cuba,
        Curacao,
        Cyprus,
        Czechia,
        Denmark,
        Djibouti,
        Dominica,
        DominicanRepublic,
        Ecuador,
        Egypt,
        ElSalvador,
        EquatorialGuinea,
        Eritrea,
        Estonia,
        Ethiopia,
        FalklandIslands,
        FaroeIslands,
        Fiji,
        Finland,
        France,
        FrenchGuiana,
        FrenchPolynesia,
        FrenchSouthernTerritories,
        Gabon,
        Gambia,
        Georgia,
        Germany,
        Ghana,
        Gibraltar,
        Greece,
        Greenland,
        Grenada,
        Guadeloupe,
        Guam,
        Guatemala,
        Guernsey,
        Guinea,
        GuineaBissau,
        Guyana,
        Haiti,
        HeardIslandandMcDonaldIslands,
        HolySee,
        Honduras,
        HongKong,
        Hungary,
        Iceland,
        India,
        Indonesia,
        Iran,
        Iraq,
        Ireland,
        IsleofMan,
        Israel,
        Italy,
        Jamaica,
        Japan,
        Jersey,
        Jordan,
        Kazakhstan,
        Kenya,
        Kiribati,
        NorthKorea,
        Korea,
        Kuwait,
        Kyrgyzstan,
        Laos,
        Latvia,
        Lebanon,
        Lesotho,
        Liberia,
        Libya,
        Liechtenstein,
        Lithuania,
        Luxembourg,
        Macao,
        Macedonia,
        Madagascar,
        Malawi,
        Malaysia,
        Maldives,
        Mali,
        Malta,
        MarshallIslands,
        Martinique,
        Mauritania,
        Mauritius,
        Mayotte,
        Mexico,
        Micronesia,
        Moldova,
        Monaco,
        Mongolia,
        Montenegro,
        Montserrat,
        Morocco,
        Mozambique,
        Myanmar,
        Namibia,
        Nauru,
        Nepal,
        Netherlands,
        NewCaledonia,
        NewZealand,
        Nicaragua,
        Niger,
        Nigeria,
        Niue,
        NorfolkIsland,
        NorthernMarianaIslands,
        Norway,
        Oman,
        Pakistan,
        Palau,
        PalestineStateof,
        Panama,
        PapuaNewGuinea,
        Paraguay,
        Peru,
        Philippines,
        Pitcairn,
        Poland,
        Portugal,
        PuertoRico,
        Qatar,
        Reunion,
        Romania,
        Russia,
        Rwanda,
        StBarts,
        SaintHelenaAscensionandTristandaCunha,
        SaintKittsandNevis,
        SaintLucia,
        SaintMartin,
        SaintPierreandMiquelon,
        SaintVincentandtheGrenadines,
        Samoa,
        SanMarino,
        SaoTomeandPrincipe,
        SaudiArabia,
        Senegal,
        Serbia,
        Seychelles,
        SierraLeone,
        Singapore,
        SintMaarten,
        Slovakia,
        Slovenia,
        SolomonIslands,
        Somalia,
        SouthAfrica,
        SouthGeorgiaandtheSouthSandwichIslands,
        SouthSudan,
        Spain,
        SriLanka,
        Sudan,
        Suriname,
        SvalbardandJanMayen,
        Swaziland,
        Sweden,
        Switzerland,
        SyrianArabRepublic,
        Taiwan,
        Tajikistan,
        Tanzania,
        Thailand,
        TimorLeste,
        Togo,
        Tokelau,
        Tonga,
        TrinidadandTobago,
        Tunisia,
        Turkey,
        Turkmenistan,
        TurksandCaicosIslands,
        Tuvalu,
        Uganda,
        Ukraine,
        UnitedArabEmirates,
        UnitedKingdomofGreatBritainandNorthernIreland,
        UnitedStatesMinorOutlyingIslands,
        UnitedStatesofAmerica,
        Uruguay,
        Uzbekistan,
        Vanuatu,
        Venezuela,
        VietNam,
        BritishVirginIslands,
        UnitedStatesVirginIslands,
        WallisandFutuna,
        WesternSahara,
        Yemen,
        Zambia,
        Zimbabwe,
        ETC;

        public String getCountryCode() {
            switch (AnonymousClass1.$SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[ordinal()]) {
                case 1:
                    return "AF";
                case 2:
                    return "AX";
                case 3:
                    return "AL";
                case 4:
                    return "DZ";
                case 5:
                    return "AS";
                case 6:
                    return "AD";
                case 7:
                    return "AO";
                case 8:
                    return "AI";
                case 9:
                    return "AQ";
                case 10:
                    return "AG";
                case 11:
                    return "AR";
                case 12:
                    return "AM";
                case 13:
                    return "AW";
                case 14:
                    return "AU";
                case 15:
                    return "AT";
                case 16:
                    return "AZ";
                case 17:
                    return "BS";
                case 18:
                    return "BH";
                case 19:
                    return "BD";
                case 20:
                    return "BB";
                case 21:
                    return "BY";
                case 22:
                    return "BE";
                case 23:
                    return "BZ";
                case 24:
                    return "BJ";
                case 25:
                    return "BM";
                case 26:
                    return "BT";
                case 27:
                    return "BO";
                case 28:
                    return "BQ";
                case 29:
                    return "BA";
                case 30:
                    return "BW";
                case 31:
                    return "BV";
                case 32:
                    return "BR";
                case 33:
                    return "IO";
                case 34:
                    return "BN";
                case 35:
                    return "BG";
                case 36:
                    return "BF";
                case 37:
                    return "BI";
                case 38:
                    return "CV";
                case 39:
                    return "KH";
                case 40:
                    return "CM";
                case 41:
                    return "CA";
                case 42:
                    return "KY";
                case 43:
                    return "CF";
                case 44:
                    return "TD";
                case 45:
                    return "CL";
                case 46:
                    return "CN";
                case 47:
                    return "CX";
                case 48:
                    return "CC";
                case 49:
                    return "CO";
                case 50:
                    return "KM";
                case 51:
                    return "CG";
                case 52:
                    return "CD";
                case 53:
                    return "CK";
                case 54:
                    return "CR";
                case 55:
                    return "CI";
                case 56:
                    return "HR";
                case 57:
                    return "CU";
                case 58:
                    return "CW";
                case 59:
                    return "CY";
                case 60:
                    return "CZ";
                case 61:
                    return "DK";
                case 62:
                    return "DJ";
                case 63:
                    return "DM";
                case 64:
                    return "DO";
                case 65:
                    return "EC";
                case 66:
                    return "EG";
                case 67:
                    return "SV";
                case 68:
                    return "GQ";
                case 69:
                    return "ER";
                case 70:
                    return "EE";
                case 71:
                    return "ET";
                case 72:
                    return "FK";
                case 73:
                    return "FO";
                case 74:
                    return "FJ";
                case 75:
                    return "FI";
                case 76:
                    return "FR";
                case 77:
                    return "GF";
                case 78:
                    return "PF";
                case 79:
                    return "TF";
                case 80:
                    return "GA";
                case 81:
                    return "GM";
                case 82:
                    return "GE";
                case 83:
                    return "DE";
                case 84:
                    return "GH";
                case 85:
                    return "GI";
                case 86:
                    return "GR";
                case 87:
                    return "GL";
                case 88:
                    return "GD";
                case 89:
                    return "GP";
                case 90:
                    return "GU";
                case 91:
                    return "GT";
                case 92:
                    return "GG";
                case 93:
                    return "GN";
                case 94:
                    return "GW";
                case 95:
                    return "GY";
                case 96:
                    return "HT";
                case 97:
                    return "HM";
                case 98:
                    return "VA";
                case 99:
                    return "HN";
                case 100:
                    return "HK";
                case 101:
                    return "HU";
                case 102:
                    return "IS";
                case 103:
                    return "IN";
                case 104:
                    return "ID";
                case 105:
                    return "IR";
                case 106:
                    return "IQ";
                case 107:
                    return "IE";
                case 108:
                    return "IM";
                case 109:
                    return "IL";
                case 110:
                    return "IT";
                case 111:
                    return "JM";
                case 112:
                    return "JP";
                case 113:
                    return "JE";
                case 114:
                    return "JO";
                case 115:
                    return "KZ";
                case 116:
                    return "KE";
                case 117:
                    return "KI";
                case 118:
                    return "KP";
                case 119:
                    return "KR";
                case 120:
                    return "KW";
                case 121:
                    return ExpandedProductParsedResult.KILOGRAM;
                case 122:
                    return "LA";
                case 123:
                    return "LV";
                case MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES /* 124 */:
                    return ExpandedProductParsedResult.POUND;
                case 125:
                    return "LS";
                case 126:
                    return "LR";
                case 127:
                    return "LY";
                case 128:
                    return "LI";
                case GmsClientSupervisor.DEFAULT_BIND_FLAGS /* 129 */:
                    return "LT";
                case 130:
                    return "LU";
                case 131:
                    return "MO";
                case 132:
                    return "MK";
                case 133:
                    return "MG";
                case 134:
                    return "MW";
                case 135:
                    return "MY";
                case 136:
                    return "MV";
                case 137:
                    return "ML";
                case 138:
                    return "MT";
                case 139:
                    return "MH";
                case 140:
                    return "MQ";
                case 141:
                    return "MR";
                case 142:
                    return "MU";
                case 143:
                    return "YT";
                case 144:
                    return "MX";
                case 145:
                    return "FM";
                case 146:
                    return "MD";
                case 147:
                    return "MC";
                case 148:
                    return "MN";
                case 149:
                    return "ME";
                case 150:
                    return "MS";
                case 151:
                    return "MA";
                case 152:
                    return "MZ";
                case 153:
                    return "MM";
                case 154:
                    return "NA";
                case 155:
                    return "NR";
                case 156:
                    return "NP";
                case 157:
                    return "NL";
                case 158:
                    return "NC";
                case 159:
                    return "NZ";
                case 160:
                    return "NI";
                case 161:
                    return "NE";
                case 162:
                    return "NG";
                case 163:
                    return "NU";
                case 164:
                    return "NF";
                case 165:
                    return "MP";
                case 166:
                    return "NO";
                case 167:
                    return "OM";
                case 168:
                    return "PK";
                case 169:
                    return "PW";
                case 170:
                    return "PS";
                case 171:
                    return "PA";
                case 172:
                    return "PG";
                case 173:
                    return "PY";
                case 174:
                    return "PE";
                case 175:
                    return "PH";
                case 176:
                    return "PN";
                case 177:
                    return "PL";
                case 178:
                    return "PT";
                case 179:
                    return "PR";
                case 180:
                    return "QA";
                case 181:
                    return "RE";
                case 182:
                    return "RO";
                case 183:
                    return "RU";
                case 184:
                    return "RW";
                case 185:
                    return "BL";
                case 186:
                    return "SH";
                case 187:
                    return "KN";
                case 188:
                    return "LC";
                case 189:
                    return "MF";
                case FacebookRequestErrorClassification.EC_INVALID_TOKEN /* 190 */:
                    return "PM";
                case 191:
                    return "VC";
                case 192:
                    return "WS";
                case 193:
                    return "SM";
                case 194:
                    return "ST";
                case 195:
                    return "SA";
                case 196:
                    return "SN";
                case 197:
                    return "RS";
                case 198:
                    return "SC";
                case 199:
                    return "SL";
                case 200:
                    return "SG";
                case 201:
                    return "SX";
                case 202:
                    return "SK";
                case 203:
                    return "SI";
                case 204:
                    return "SB";
                case 205:
                    return "SO";
                case 206:
                    return "ZA";
                case 207:
                    return "GS";
                case 208:
                    return "SS";
                case 209:
                    return "ES";
                case 210:
                    return "LK";
                case 211:
                    return "SD";
                case 212:
                    return "SR";
                case 213:
                    return "SJ";
                case 214:
                    return "SZ";
                case 215:
                    return "SE";
                case 216:
                    return "CH";
                case 217:
                    return "SY";
                case 218:
                    return "TW";
                case 219:
                    return "TJ";
                case 220:
                    return "TZ";
                case 221:
                    return "TH";
                case 222:
                    return "TL";
                case 223:
                    return "TG";
                case 224:
                    return "TK";
                case 225:
                    return "TO";
                case 226:
                    return "TT";
                case 227:
                    return "TN";
                case 228:
                    return "TR";
                case 229:
                    return "TM";
                case 230:
                    return "TC";
                case 231:
                    return "TV";
                case 232:
                    return "UG";
                case 233:
                    return "UA";
                case 234:
                    return "AE";
                case 235:
                    return "GB";
                case 236:
                    return "UM";
                case 237:
                    return "US";
                case 238:
                    return "UY";
                case 239:
                    return "UZ";
                case 240:
                    return "VU";
                case 241:
                    return "VE";
                case 242:
                    return "VN";
                case 243:
                    return "VG";
                case 244:
                    return "VI";
                case 245:
                    return "WF";
                case 246:
                    return "EH";
                case 247:
                    return "YE";
                case 248:
                    return "ZM";
                case 249:
                    return "ZW";
                default:
                    return Locale.getDefault().getCountry();
            }
        }

        public int getCountryCodeNumber() {
            switch (AnonymousClass1.$SwitchMap$com$nexon$core$locale$NXLocale$COUNTRY[ordinal()]) {
                case 1:
                    return 4;
                case 2:
                    return 248;
                case 3:
                    return 8;
                case 4:
                    return 12;
                case 5:
                    return 16;
                case 6:
                    return 20;
                case 7:
                    return 24;
                case 8:
                    return 660;
                case 9:
                    return 10;
                case 10:
                    return 28;
                case 11:
                    return 32;
                case 12:
                    return 51;
                case 13:
                    return 533;
                case 14:
                    return 36;
                case 15:
                    return 40;
                case 16:
                    return 31;
                case 17:
                    return 44;
                case 18:
                    return 48;
                case 19:
                    return 50;
                case 20:
                    return 52;
                case 21:
                    return 112;
                case 22:
                    return 56;
                case 23:
                    return 84;
                case 24:
                    return 204;
                case 25:
                    return 60;
                case 26:
                    return 64;
                case 27:
                    return 68;
                case 28:
                    return 535;
                case 29:
                    return 70;
                case 30:
                    return 72;
                case 31:
                    return 74;
                case 32:
                    return 76;
                case 33:
                    return 86;
                case 34:
                    return 96;
                case 35:
                    return 100;
                case 36:
                    return 854;
                case 37:
                    return 108;
                case 38:
                    return 132;
                case 39:
                    return 116;
                case 40:
                    return 120;
                case 41:
                    return MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES;
                case 42:
                    return 136;
                case 43:
                    return 140;
                case 44:
                    return 148;
                case 45:
                    return 152;
                case 46:
                    return 156;
                case 47:
                    return 162;
                case 48:
                    return 166;
                case 49:
                    return 170;
                case 50:
                    return 174;
                case 51:
                    return 178;
                case 52:
                    return 180;
                case 53:
                    return 184;
                case 54:
                    return 188;
                case 55:
                    return 384;
                case 56:
                    return 191;
                case 57:
                    return 192;
                case 58:
                    return 531;
                case 59:
                    return 196;
                case 60:
                    return 203;
                case 61:
                    return 208;
                case 62:
                    return 262;
                case 63:
                    return 212;
                case 64:
                    return 214;
                case 65:
                    return 218;
                case 66:
                    return 818;
                case 67:
                    return 222;
                case 68:
                    return 226;
                case 69:
                    return 232;
                case 70:
                    return 233;
                case 71:
                    return 231;
                case 72:
                    return 238;
                case 73:
                    return 234;
                case 74:
                    return 242;
                case 75:
                    return 246;
                case 76:
                    return 250;
                case 77:
                    return 254;
                case 78:
                    return 258;
                case 79:
                    return 260;
                case 80:
                    return 266;
                case 81:
                    return 270;
                case 82:
                    return 268;
                case 83:
                    return 276;
                case 84:
                    return 288;
                case 85:
                    return 292;
                case 86:
                    return HttpResponseCode.MULTIPLE_CHOICES;
                case 87:
                    return HttpResponseCode.NOT_MODIFIED;
                case 88:
                    return 308;
                case 89:
                    return 312;
                case 90:
                    return 316;
                case 91:
                    return 320;
                case 92:
                    return 831;
                case 93:
                    return 324;
                case 94:
                    return 624;
                case 95:
                    return 328;
                case 96:
                    return 332;
                case 97:
                    return 334;
                case 98:
                    return 336;
                case 99:
                    return 340;
                case 100:
                    return 344;
                case 101:
                    return 348;
                case 102:
                    return 352;
                case 103:
                    return 356;
                case 104:
                    return c.a;
                case 105:
                    return 364;
                case 106:
                    return 368;
                case 107:
                    return 372;
                case 108:
                    return 833;
                case 109:
                    return 376;
                case 110:
                    return 380;
                case 111:
                    return 388;
                case 112:
                    return 392;
                case 113:
                    return 832;
                case 114:
                    return HttpResponseCode.BAD_REQUEST;
                case 115:
                    return 398;
                case 116:
                    return HttpResponseCode.NOT_FOUND;
                case 117:
                    return 296;
                case 118:
                    return 408;
                case 119:
                    return 410;
                case 120:
                    return 414;
                case 121:
                    return 417;
                case 122:
                    return 418;
                case 123:
                    return 428;
                case MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES /* 124 */:
                    return HttpResponseCode.UNPROCESSABLE_ENTITY;
                case 125:
                    return 426;
                case 126:
                    return 430;
                case 127:
                    return 434;
                case 128:
                    return 438;
                case GmsClientSupervisor.DEFAULT_BIND_FLAGS /* 129 */:
                    return 440;
                case 130:
                    return 442;
                case 131:
                    return 446;
                case 132:
                    return 807;
                case 133:
                    return 450;
                case 134:
                    return 454;
                case 135:
                    return FacebookRequestErrorClassification.ESC_APP_NOT_INSTALLED;
                case 136:
                    return 462;
                case 137:
                    return 466;
                case 138:
                    return 470;
                case 139:
                    return 584;
                case 140:
                    return 474;
                case 141:
                    return 478;
                case 142:
                    return c.b;
                case 143:
                    return 175;
                case 144:
                    return 484;
                case 145:
                    return 583;
                case 146:
                    return 498;
                case 147:
                    return 492;
                case 148:
                    return 496;
                case 149:
                    return 499;
                case 150:
                    return 500;
                case 151:
                    return HttpResponseCode.GATEWAY_TIMEOUT;
                case 152:
                    return 508;
                case 153:
                    return 104;
                case 154:
                    return 516;
                case 155:
                    return 520;
                case 156:
                    return 524;
                case 157:
                    return 528;
                case 158:
                    return 540;
                case 159:
                    return 554;
                case 160:
                    return 558;
                case 161:
                    return 562;
                case 162:
                    return 566;
                case 163:
                    return 570;
                case 164:
                    return 574;
                case 165:
                    return 580;
                case 166:
                    return 578;
                case 167:
                    return 512;
                case 168:
                    return 586;
                case 169:
                    return 585;
                case 170:
                    return 275;
                case 171:
                    return 591;
                case 172:
                    return 598;
                case 173:
                    return SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT;
                case 174:
                    return 604;
                case 175:
                    return 608;
                case 176:
                    return 612;
                case 177:
                    return 616;
                case 178:
                    return 620;
                case 179:
                    return 630;
                case 180:
                    return 634;
                case 181:
                    return 638;
                case 182:
                    return 642;
                case 183:
                    return 643;
                case 184:
                    return 646;
                case 185:
                    return 652;
                case 186:
                    return 654;
                case 187:
                    return 659;
                case 188:
                    return 662;
                case 189:
                    return 663;
                case FacebookRequestErrorClassification.EC_INVALID_TOKEN /* 190 */:
                    return 666;
                case 191:
                    return 670;
                case 192:
                    return 882;
                case 193:
                    return 674;
                case 194:
                    return 678;
                case 195:
                    return 682;
                case 196:
                    return 686;
                case 197:
                    return 688;
                case 198:
                    return 690;
                case 199:
                    return 694;
                case 200:
                    return 702;
                case 201:
                    return 534;
                case 202:
                    return 703;
                case 203:
                    return 705;
                case 204:
                    return 90;
                case 205:
                    return 706;
                case 206:
                    return 710;
                case 207:
                    return 239;
                case 208:
                    return 728;
                case 209:
                    return 724;
                case 210:
                    return 144;
                case 211:
                    return 729;
                case 212:
                    return 740;
                case 213:
                    return 744;
                case 214:
                    return 748;
                case 215:
                    return 752;
                case 216:
                    return 756;
                case 217:
                    return 760;
                case 218:
                    return 158;
                case 219:
                    return 762;
                case 220:
                    return 834;
                case 221:
                    return 764;
                case 222:
                    return 626;
                case 223:
                    return 768;
                case 224:
                    return 772;
                case 225:
                    return 776;
                case 226:
                    return 780;
                case 227:
                    return 788;
                case 228:
                    return 792;
                case 229:
                    return 795;
                case 230:
                    return 796;
                case 231:
                    return 798;
                case 232:
                    return 800;
                case 233:
                    return 804;
                case 234:
                    return 784;
                case 235:
                    return 826;
                case 236:
                    return 581;
                case 237:
                    return 840;
                case 238:
                    return 858;
                case 239:
                    return 860;
                case 240:
                    return 548;
                case 241:
                    return 862;
                case 242:
                    return 704;
                case 243:
                    return 92;
                case 244:
                    return 850;
                case 245:
                    return 876;
                case 246:
                    return 732;
                case 247:
                    return 887;
                case 248:
                    return 894;
                case 249:
                    return 716;
                default:
                    return -1;
            }
        }
    }

    public enum LOCALE {
        EN_AU,
        MS_BN,
        ZH_CN,
        EN_GU,
        CH_GU,
        EN_HK,
        ZH_HK,
        ID_ID,
        JA_JP,
        KO_KR,
        EN_MO,
        PT_MO,
        ZH_MO,
        MS_MY,
        MN_MN,
        EN_NZ,
        MI_NZ,
        EN_PH,
        TL_PH,
        MS_SG,
        EN_SG,
        ZH_SG,
        SI_LK,
        TA_LK,
        EN_LK,
        ZH_TW,
        TH_TH,
        VI_VN,
        SQ_AL,
        NL_BE,
        FR_BE,
        DE_BE,
        EL_CY,
        TR_CY,
        CS_CZ,
        DA_DK,
        DE_DE,
        ES_ES,
        FR_FR,
        IS_IS,
        GA_IE,
        IT_IT,
        DE_LI,
        DE_LU,
        FR_LU,
        LB_LU,
        MK_MK,
        HU_HU,
        MT_MT,
        EN_MT,
        IT_MT,
        RO_MD,
        SR_ME,
        NL_NL,
        NO_NO,
        DE_AT,
        PL_PL,
        PT_PT,
        RU_RU,
        DE_CH,
        FR_CH,
        IT_CH,
        FI_FI,
        SV_FI,
        SV_SE,
        TR_TR,
        EN_GB,
        EN_AI,
        EN_AG,
        ET_AR,
        EN_BB,
        EN_BZ,
        EN_BM,
        ET_BO,
        PT_BR,
        EN_VG,
        EN_KY,
        ET_CL,
        ET_CO,
        ET_CR,
        EN_DM,
        ET_DO,
        ET_EC,
        ET_SV,
        EN_GD,
        ET_GT,
        EN_GY,
        HI_GY,
        UR_GY,
        ET_HN,
        EN_JM,
        ET_MX,
        EN_MS,
        ET_NI,
        ET_PA,
        ET_PY,
        GN_PY,
        AY_PE,
        ET_PE,
        QY_PE,
        EN_KN,
        EN_LC,
        EN_VC,
        FR_VC,
        NL_SR,
        EN_BS,
        EN_TT,
        EN_TC,
        ET_UY,
        ET_VE,
        EN_CA,
        FR_CA,
        EN_PR,
        ET_PR,
        EN_US,
        HY_AM,
        AR_BH,
        EN_BW,
        EN_CM,
        FR_CM,
        FR_CI,
        AR_EG,
        PT_GW,
        FR_GN,
        ET_GQ,
        FR_GQ,
        HI_IN,
        EN_IN,
        HE_IL,
        AR_IL,
        AR_JO,
        EN_KE,
        AR_KW,
        AR_LB,
        FR_MG,
        MG_MG,
        FR_ML,
        AR_MA,
        EN_MU,
        PT_MZ,
        FR_NE,
        EN_NG,
        AR_OM,
        AR_QA,
        FR_CF,
        SG_CF,
        AR_SA,
        FR_SN,
        AF_ZA,
        EN_ZA,
        ZU_ZA,
        AR_TN,
        AR_AE,
        ETC;

        public String getLocaleCode() {
            switch (AnonymousClass1.$SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[ordinal()]) {
                case 1:
                    return "en_AU";
                case 2:
                    return "ms_BN";
                case 3:
                    return ChannelCodes.CHINESE_SIMPLIFIED;
                case 4:
                    return "en_GU";
                case 5:
                    return "ch_GU";
                case 6:
                    return "en_HK";
                case 7:
                    return "zh_HK";
                case 8:
                    return "id_ID";
                case 9:
                    return "ja_JP";
                case 10:
                    return "ko_KR";
                case 11:
                    return "en_MO";
                case 12:
                    return "pt_MO";
                case 13:
                    return "zh_MO";
                case 14:
                    return "ms_MY";
                case 15:
                    return "mn_MN";
                case 16:
                    return "en_NZ";
                case 17:
                    return "mi_NZ";
                case 18:
                    return "en_PH";
                case 19:
                    return "tl_PH";
                case 20:
                    return "ms_SG";
                case 21:
                    return "en_SG";
                case 22:
                    return "zh_SG";
                case 23:
                    return "si_LK";
                case 24:
                    return "ta_LK";
                case 25:
                    return "en_LK";
                case 26:
                    return ChannelCodes.CHINESE_TRADITIONAL;
                case 27:
                    return "th_TH";
                case 28:
                    return "vi_VN";
                case 29:
                    return "sq_AL";
                case 30:
                    return "nl_BE";
                case 31:
                    return "fr_BE";
                case 32:
                    return "de_BE";
                case 33:
                    return "el_CY";
                case 34:
                    return "tr_CY";
                case 35:
                    return "cs_CZ";
                case 36:
                    return "da_DK";
                case 37:
                    return "de_DE";
                case 38:
                    return "es_ES";
                case 39:
                    return "fr_FR";
                case 40:
                    return "is_IS";
                case 41:
                    return "ga_IE";
                case 42:
                    return "it_IT";
                case 43:
                    return "de_LI";
                case 44:
                    return "de_LU";
                case 45:
                    return "fr_LU";
                case 46:
                    return "lb_LU";
                case 47:
                    return "mk_MK";
                case 48:
                    return "hu_HU";
                case 49:
                    return "mt_MT";
                case 50:
                    return "en_MT";
                case 51:
                    return "it_MT";
                case 52:
                    return "ro_MD";
                case 53:
                    return "sr_ME";
                case 54:
                    return "nl_NL";
                case 55:
                    return "no_NO";
                case 56:
                    return "de_AT";
                case 57:
                    return "pl_PL";
                case 58:
                    return "pt_PT";
                case 59:
                    return "ru_RU";
                case 60:
                    return "de_CH";
                case 61:
                    return "fr_CH";
                case 62:
                    return "it_CH";
                case 63:
                    return "fi_FI";
                case 64:
                    return "sv_FI";
                case 65:
                    return "sv_SE";
                case 66:
                    return "tr_TR";
                case 67:
                    return "en_GB";
                case 68:
                    return "en_AI";
                case 69:
                    return "en_AG";
                case 70:
                    return "et_AR";
                case 71:
                    return "en_BB";
                case 72:
                    return "en_BZ";
                case 73:
                    return "en_BM";
                case 74:
                    return "et_BO";
                case 75:
                    return "pt_BR";
                case 76:
                    return "en_VG";
                case 77:
                    return "en_KY";
                case 78:
                    return "et_CL";
                case 79:
                    return "et_CO";
                case 80:
                    return "et_CR";
                case 81:
                    return "en_DM";
                case 82:
                    return "et_DO";
                case 83:
                    return "et_EC";
                case 84:
                    return "et_SV";
                case 85:
                    return "en_GD";
                case 86:
                    return "et_GT";
                case 87:
                    return "en_GY";
                case 88:
                    return "hi_GY";
                case 89:
                    return "ur_GY";
                case 90:
                    return "et_HN";
                case 91:
                    return "en_JM";
                case 92:
                    return "et_MX";
                case 93:
                    return "en_MS";
                case 94:
                    return "et_NI";
                case 95:
                    return "et_PA";
                case 96:
                    return "et_PY";
                case 97:
                    return "gn_PY";
                case 98:
                    return "ay_PE";
                case 99:
                    return "et_PE";
                case 100:
                    return "qy_PE";
                case 101:
                    return "en_KN";
                case 102:
                    return "en_LC";
                case 103:
                    return "en_VC";
                case 104:
                    return "fr_VC";
                case 105:
                    return "nl_SR";
                case 106:
                    return "en_BS";
                case 107:
                    return "en_TT";
                case 108:
                    return "en_TC";
                case 109:
                    return "et_UY";
                case 110:
                    return "et_VE";
                case 111:
                    return "en_CA";
                case 112:
                    return "fr_CA";
                case 113:
                    return "en_PR";
                case 114:
                    return "et_PR";
                case 115:
                    return "en_US";
                case 116:
                    return "hy_AM";
                case 117:
                    return "ar_BH";
                case 118:
                    return "en_BW";
                case 119:
                    return "en_CM";
                case 120:
                    return "fr_CM";
                case 121:
                    return "fr_CI";
                case 122:
                    return "ar_EG";
                case 123:
                    return "pt_GW";
                case MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES /* 124 */:
                    return "fr_GN";
                case 125:
                    return "et_GQ";
                case 126:
                    return "fr_GQ";
                case 127:
                    return "hi_IN";
                case 128:
                    return "en_IN";
                case GmsClientSupervisor.DEFAULT_BIND_FLAGS /* 129 */:
                    return "he_IL";
                case 130:
                    return "ar_IL";
                case 131:
                    return "ar_JO";
                case 132:
                    return "en_KE";
                case 133:
                    return "ar_KW";
                case 134:
                    return "ar_LB";
                case 135:
                    return "fr_MG";
                case 136:
                    return "mg_MG";
                case 137:
                    return "fr_ML";
                case 138:
                    return "ar_MA";
                case 139:
                    return "en_MU";
                case 140:
                    return "pt_MZ";
                case 141:
                    return "fr_NE";
                case 142:
                    return "en_NG";
                case 143:
                    return "ar_OM";
                case 144:
                    return "ar_QA";
                case 145:
                    return "fr_CF";
                case 146:
                    return "sg_CF";
                case 147:
                    return "ar_SA";
                case 148:
                    return "fr_SN";
                case 149:
                    return "af_ZA";
                case 150:
                    return "en_ZA";
                case 151:
                    return "zu_ZA";
                case 152:
                    return "ar_TN";
                case 153:
                    return "ar_AE";
                default:
                    return Locale.getDefault().toString();
            }
        }

        public int getLocaleCodeNumber() {
            switch (AnonymousClass1.$SwitchMap$com$nexon$core$locale$NXLocale$LOCALE[ordinal()]) {
                case 1:
                    return 0;
                case 2:
                    return 1;
                case 3:
                    return 2;
                case 4:
                    return 3;
                case 5:
                    return 4;
                case 6:
                    return 5;
                case 7:
                    return 6;
                case 8:
                    return 7;
                case 9:
                    return 8;
                case 10:
                    return 9;
                case 11:
                    return 10;
                case 12:
                    return 11;
                case 13:
                    return 12;
                case 14:
                    return 13;
                case 15:
                    return 14;
                case 16:
                    return 15;
                case 17:
                    return 16;
                case 18:
                    return 17;
                case 19:
                    return 18;
                case 20:
                    return 19;
                case 21:
                    return 20;
                case 22:
                    return 21;
                case 23:
                    return 22;
                case 24:
                    return 23;
                case 25:
                    return 24;
                case 26:
                    return 25;
                case 27:
                    return 26;
                case 28:
                    return 27;
                case 29:
                    return 28;
                case 30:
                    return 29;
                case 31:
                    return 30;
                case 32:
                    return 31;
                case 33:
                    return 32;
                case 34:
                    return 33;
                case 35:
                    return 34;
                case 36:
                    return 35;
                case 37:
                    return 36;
                case 38:
                    return 37;
                case 39:
                    return 38;
                case 40:
                    return 39;
                case 41:
                    return 40;
                case 42:
                    return 41;
                case 43:
                    return 42;
                case 44:
                    return 43;
                case 45:
                    return 44;
                case 46:
                    return 45;
                case 47:
                    return 46;
                case 48:
                    return 47;
                case 49:
                    return 48;
                case 50:
                    return 49;
                case 51:
                    return 50;
                case 52:
                    return 51;
                case 53:
                    return 52;
                case 54:
                    return 53;
                case 55:
                    return 54;
                case 56:
                    return 55;
                case 57:
                    return 56;
                case 58:
                    return 57;
                case 59:
                    return 58;
                case 60:
                    return 59;
                case 61:
                    return 60;
                case 62:
                    return 61;
                case 63:
                    return 62;
                case 64:
                    return 63;
                case 65:
                    return 64;
                case 66:
                    return 65;
                case 67:
                    return 66;
                case 68:
                    return 67;
                case 69:
                    return 68;
                case 70:
                    return 69;
                case 71:
                    return 70;
                case 72:
                    return 71;
                case 73:
                    return 72;
                case 74:
                    return 73;
                case 75:
                    return 74;
                case 76:
                    return 75;
                case 77:
                    return 76;
                case 78:
                    return 77;
                case 79:
                    return 78;
                case 80:
                    return 79;
                case 81:
                    return 80;
                case 82:
                    return 81;
                case 83:
                    return 82;
                case 84:
                    return 83;
                case 85:
                    return 84;
                case 86:
                    return 85;
                case 87:
                    return 86;
                case 88:
                    return 87;
                case 89:
                    return 88;
                case 90:
                    return 89;
                case 91:
                    return 90;
                case 92:
                    return 91;
                case 93:
                    return 92;
                case 94:
                    return 93;
                case 95:
                    return 94;
                case 96:
                    return 95;
                case 97:
                    return 96;
                case 98:
                    return 97;
                case 99:
                    return 98;
                case 100:
                    return 99;
                case 101:
                    return 100;
                case 102:
                    return 101;
                case 103:
                    return 102;
                case 104:
                    return 103;
                case 105:
                    return 104;
                case 106:
                    return 105;
                case 107:
                    return 106;
                case 108:
                    return 107;
                case 109:
                    return 108;
                case 110:
                    return 109;
                case 111:
                    return 110;
                case 112:
                    return 111;
                case 113:
                    return 112;
                case 114:
                    return 113;
                case 115:
                    return 114;
                case 116:
                    return 115;
                case 117:
                    return 116;
                case 118:
                    return 117;
                case 119:
                    return 118;
                case 120:
                    return 119;
                case 121:
                    return 120;
                case 122:
                    return 121;
                case 123:
                    return 122;
                case MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES /* 124 */:
                    return 123;
                case 125:
                    return MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES;
                case 126:
                    return 125;
                case 127:
                    return 126;
                case 128:
                    return 127;
                case GmsClientSupervisor.DEFAULT_BIND_FLAGS /* 129 */:
                    return 128;
                case 130:
                    return GmsClientSupervisor.DEFAULT_BIND_FLAGS;
                case 131:
                    return 130;
                case 132:
                    return 131;
                case 133:
                    return 132;
                case 134:
                    return 133;
                case 135:
                    return 134;
                case 136:
                    return 135;
                case 137:
                    return 136;
                case 138:
                    return 137;
                case 139:
                    return 138;
                case 140:
                    return 139;
                case 141:
                    return 140;
                case 142:
                    return 141;
                case 143:
                    return 142;
                case 144:
                    return 143;
                case 145:
                    return 144;
                case 146:
                    return 145;
                case 147:
                    return 146;
                case 148:
                    return 147;
                case 149:
                    return 148;
                case 150:
                    return 149;
                case 151:
                    return 150;
                case 152:
                    return 151;
                case 153:
                    return 152;
                default:
                    return -1;
            }
        }
    }

    public static COUNTRY getCountryCode(String str) {
        if (str.equals("AF")) {
            return COUNTRY.Afghanistan;
        }
        if (str.equals("AX")) {
            return COUNTRY.f0landIslands;
        }
        if (str.equals("AL")) {
            return COUNTRY.Albania;
        }
        if (str.equals("DZ")) {
            return COUNTRY.Algeria;
        }
        if (str.equals("AS")) {
            return COUNTRY.AmericanSamoa;
        }
        if (str.equals("AD")) {
            return COUNTRY.Andorra;
        }
        if (str.equals("AO")) {
            return COUNTRY.Angola;
        }
        if (str.equals("AI")) {
            return COUNTRY.Anguilla;
        }
        if (str.equals("AQ")) {
            return COUNTRY.Antarctica;
        }
        if (str.equals("AG")) {
            return COUNTRY.AntiguaandBarbuda;
        }
        if (str.equals("AR")) {
            return COUNTRY.Argentina;
        }
        if (str.equals("AM")) {
            return COUNTRY.Armenia;
        }
        if (str.equals("AW")) {
            return COUNTRY.Aruba;
        }
        if (str.equals("AU")) {
            return COUNTRY.Australia;
        }
        if (str.equals("AT")) {
            return COUNTRY.Austria;
        }
        if (str.equals("AZ")) {
            return COUNTRY.Azerbaijan;
        }
        if (str.equals("BS")) {
            return COUNTRY.Bahamas;
        }
        if (str.equals("BH")) {
            return COUNTRY.Bahrain;
        }
        if (str.equals("BD")) {
            return COUNTRY.Bangladesh;
        }
        if (str.equals("BB")) {
            return COUNTRY.Barbados;
        }
        if (str.equals("BY")) {
            return COUNTRY.Belarus;
        }
        if (str.equals("BE")) {
            return COUNTRY.Belgium;
        }
        if (str.equals("BZ")) {
            return COUNTRY.Belize;
        }
        if (str.equals("BJ")) {
            return COUNTRY.Benin;
        }
        if (str.equals("BM")) {
            return COUNTRY.Bermuda;
        }
        if (str.equals("BT")) {
            return COUNTRY.Bhutan;
        }
        if (str.equals("BO")) {
            return COUNTRY.Bolivia;
        }
        if (str.equals("BQ")) {
            return COUNTRY.BonaireSintEustatiusandSaba;
        }
        if (str.equals("BA")) {
            return COUNTRY.BosniaandHerzegovina;
        }
        if (str.equals("BW")) {
            return COUNTRY.Botswana;
        }
        if (str.equals("BV")) {
            return COUNTRY.BouvetIsland;
        }
        if (str.equals("BR")) {
            return COUNTRY.Brazil;
        }
        if (str.equals("IO")) {
            return COUNTRY.BritishIndianOceanTerritory;
        }
        if (str.equals("BN")) {
            return COUNTRY.BruneiDarussalam;
        }
        if (str.equals("BG")) {
            return COUNTRY.Bulgaria;
        }
        if (str.equals("BF")) {
            return COUNTRY.BurkinaFaso;
        }
        if (str.equals("BI")) {
            return COUNTRY.Burundi;
        }
        if (str.equals("CV")) {
            return COUNTRY.CaboVerde;
        }
        if (str.equals("KH")) {
            return COUNTRY.Cambodia;
        }
        if (str.equals("CM")) {
            return COUNTRY.Cameroon;
        }
        if (str.equals("CA")) {
            return COUNTRY.Canada;
        }
        if (str.equals("KY")) {
            return COUNTRY.CaymanIslands;
        }
        if (str.equals("CF")) {
            return COUNTRY.CentralAfricanRepublic;
        }
        if (str.equals("TD")) {
            return COUNTRY.Chad;
        }
        if (str.equals("CL")) {
            return COUNTRY.Chile;
        }
        if (str.equals("CN")) {
            return COUNTRY.China;
        }
        if (str.equals("CX")) {
            return COUNTRY.ChristmasIsland;
        }
        if (str.equals("CC")) {
            return COUNTRY.CocosIslands;
        }
        if (str.equals("CO")) {
            return COUNTRY.Colombia;
        }
        if (str.equals("KM")) {
            return COUNTRY.Comoros;
        }
        if (str.equals("CG")) {
            return COUNTRY.Congo;
        }
        if (str.equals("CD")) {
            return COUNTRY.DemocraticRepublicoftheCongo;
        }
        if (str.equals("CK")) {
            return COUNTRY.CookIslands;
        }
        if (str.equals("CR")) {
            return COUNTRY.CostaRica;
        }
        if (str.equals("CI")) {
            return COUNTRY.IvoryCoast;
        }
        if (str.equals("HR")) {
            return COUNTRY.Croatia;
        }
        if (str.equals("CU")) {
            return COUNTRY.Cuba;
        }
        if (str.equals("CW")) {
            return COUNTRY.Curacao;
        }
        if (str.equals("CY")) {
            return COUNTRY.Cyprus;
        }
        if (str.equals("CZ")) {
            return COUNTRY.Czechia;
        }
        if (str.equals("DK")) {
            return COUNTRY.Denmark;
        }
        if (str.equals("DJ")) {
            return COUNTRY.Djibouti;
        }
        if (str.equals("DM")) {
            return COUNTRY.Dominica;
        }
        if (str.equals("DO")) {
            return COUNTRY.DominicanRepublic;
        }
        if (str.equals("EC")) {
            return COUNTRY.Ecuador;
        }
        if (str.equals("EG")) {
            return COUNTRY.Egypt;
        }
        if (str.equals("SV")) {
            return COUNTRY.ElSalvador;
        }
        if (str.equals("GQ")) {
            return COUNTRY.EquatorialGuinea;
        }
        if (str.equals("ER")) {
            return COUNTRY.Eritrea;
        }
        if (str.equals("EE")) {
            return COUNTRY.Estonia;
        }
        if (str.equals("ET")) {
            return COUNTRY.Ethiopia;
        }
        if (str.equals("FK")) {
            return COUNTRY.FalklandIslands;
        }
        if (str.equals("FO")) {
            return COUNTRY.FaroeIslands;
        }
        if (str.equals("FJ")) {
            return COUNTRY.Fiji;
        }
        if (str.equals("FI")) {
            return COUNTRY.Finland;
        }
        if (str.equals("FR")) {
            return COUNTRY.France;
        }
        if (str.equals("GF")) {
            return COUNTRY.FrenchGuiana;
        }
        if (str.equals("PF")) {
            return COUNTRY.FrenchPolynesia;
        }
        if (str.equals("TF")) {
            return COUNTRY.FrenchSouthernTerritories;
        }
        if (str.equals("GA")) {
            return COUNTRY.Gabon;
        }
        if (str.equals("GM")) {
            return COUNTRY.Gambia;
        }
        if (str.equals("GE")) {
            return COUNTRY.Georgia;
        }
        if (str.equals("DE")) {
            return COUNTRY.Germany;
        }
        if (str.equals("GH")) {
            return COUNTRY.Ghana;
        }
        if (str.equals("GI")) {
            return COUNTRY.Gibraltar;
        }
        if (str.equals("GR")) {
            return COUNTRY.Greece;
        }
        if (str.equals("GL")) {
            return COUNTRY.Greenland;
        }
        if (str.equals("GD")) {
            return COUNTRY.Grenada;
        }
        if (str.equals("GP")) {
            return COUNTRY.Guadeloupe;
        }
        if (str.equals("GU")) {
            return COUNTRY.Guam;
        }
        if (str.equals("GT")) {
            return COUNTRY.Guatemala;
        }
        if (str.equals("GG")) {
            return COUNTRY.Guernsey;
        }
        if (str.equals("GN")) {
            return COUNTRY.Guinea;
        }
        if (str.equals("GW")) {
            return COUNTRY.GuineaBissau;
        }
        if (str.equals("GY")) {
            return COUNTRY.Guyana;
        }
        if (str.equals("HT")) {
            return COUNTRY.Haiti;
        }
        if (str.equals("HM")) {
            return COUNTRY.HeardIslandandMcDonaldIslands;
        }
        if (str.equals("VA")) {
            return COUNTRY.HolySee;
        }
        if (str.equals("HN")) {
            return COUNTRY.Honduras;
        }
        if (str.equals("HK")) {
            return COUNTRY.HongKong;
        }
        if (str.equals("HU")) {
            return COUNTRY.Hungary;
        }
        if (str.equals("IS")) {
            return COUNTRY.Iceland;
        }
        if (str.equals("IN")) {
            return COUNTRY.India;
        }
        if (str.equals("ID")) {
            return COUNTRY.Indonesia;
        }
        if (str.equals("IR")) {
            return COUNTRY.Iran;
        }
        if (str.equals("IQ")) {
            return COUNTRY.Iraq;
        }
        if (str.equals("IE")) {
            return COUNTRY.Ireland;
        }
        if (str.equals("IM")) {
            return COUNTRY.IsleofMan;
        }
        if (str.equals("IL")) {
            return COUNTRY.Israel;
        }
        if (str.equals("IT")) {
            return COUNTRY.Italy;
        }
        if (str.equals("JM")) {
            return COUNTRY.Jamaica;
        }
        if (str.equals("JP")) {
            return COUNTRY.Japan;
        }
        if (str.equals("JE")) {
            return COUNTRY.Jersey;
        }
        if (str.equals("JO")) {
            return COUNTRY.Jordan;
        }
        if (str.equals("KZ")) {
            return COUNTRY.Kazakhstan;
        }
        if (str.equals("KE")) {
            return COUNTRY.Kenya;
        }
        if (str.equals("KI")) {
            return COUNTRY.Kiribati;
        }
        if (str.equals("KP")) {
            return COUNTRY.NorthKorea;
        }
        if (str.equals("KR")) {
            return COUNTRY.Korea;
        }
        if (str.equals("KW")) {
            return COUNTRY.Kuwait;
        }
        if (str.equals(ExpandedProductParsedResult.KILOGRAM)) {
            return COUNTRY.Kyrgyzstan;
        }
        if (str.equals("LA")) {
            return COUNTRY.Laos;
        }
        if (str.equals("LV")) {
            return COUNTRY.Latvia;
        }
        if (str.equals(ExpandedProductParsedResult.POUND)) {
            return COUNTRY.Lebanon;
        }
        if (str.equals("LS")) {
            return COUNTRY.Lesotho;
        }
        if (str.equals("LR")) {
            return COUNTRY.Liberia;
        }
        if (str.equals("LY")) {
            return COUNTRY.Libya;
        }
        if (str.equals("LI")) {
            return COUNTRY.Liechtenstein;
        }
        if (str.equals("LT")) {
            return COUNTRY.Lithuania;
        }
        if (str.equals("LU")) {
            return COUNTRY.Luxembourg;
        }
        if (str.equals("MO")) {
            return COUNTRY.Macao;
        }
        if (str.equals("MK")) {
            return COUNTRY.Macedonia;
        }
        if (str.equals("MG")) {
            return COUNTRY.Madagascar;
        }
        if (str.equals("MW")) {
            return COUNTRY.Malawi;
        }
        if (str.equals("MY")) {
            return COUNTRY.Malaysia;
        }
        if (str.equals("MV")) {
            return COUNTRY.Maldives;
        }
        if (str.equals("ML")) {
            return COUNTRY.Mali;
        }
        if (str.equals("MT")) {
            return COUNTRY.Malta;
        }
        if (str.equals("MH")) {
            return COUNTRY.MarshallIslands;
        }
        if (str.equals("MQ")) {
            return COUNTRY.Martinique;
        }
        if (str.equals("MR")) {
            return COUNTRY.Mauritania;
        }
        if (str.equals("MU")) {
            return COUNTRY.Mauritius;
        }
        if (str.equals("YT")) {
            return COUNTRY.Mayotte;
        }
        if (str.equals("MX")) {
            return COUNTRY.Mexico;
        }
        if (str.equals("FM")) {
            return COUNTRY.Micronesia;
        }
        if (str.equals("MD")) {
            return COUNTRY.Moldova;
        }
        if (str.equals("MC")) {
            return COUNTRY.Monaco;
        }
        if (str.equals("MN")) {
            return COUNTRY.Mongolia;
        }
        if (str.equals("ME")) {
            return COUNTRY.Montenegro;
        }
        if (str.equals("MS")) {
            return COUNTRY.Montserrat;
        }
        if (str.equals("MA")) {
            return COUNTRY.Morocco;
        }
        if (str.equals("MZ")) {
            return COUNTRY.Mozambique;
        }
        if (str.equals("MM")) {
            return COUNTRY.Myanmar;
        }
        if (str.equals("NA")) {
            return COUNTRY.Namibia;
        }
        if (str.equals("NR")) {
            return COUNTRY.Nauru;
        }
        if (str.equals("NP")) {
            return COUNTRY.Nepal;
        }
        if (str.equals("NL")) {
            return COUNTRY.Netherlands;
        }
        if (str.equals("NC")) {
            return COUNTRY.NewCaledonia;
        }
        if (str.equals("NZ")) {
            return COUNTRY.NewZealand;
        }
        if (str.equals("NI")) {
            return COUNTRY.Nicaragua;
        }
        if (str.equals("NE")) {
            return COUNTRY.Niger;
        }
        if (str.equals("NG")) {
            return COUNTRY.Nigeria;
        }
        if (str.equals("NU")) {
            return COUNTRY.Niue;
        }
        if (str.equals("NF")) {
            return COUNTRY.NorfolkIsland;
        }
        if (str.equals("MP")) {
            return COUNTRY.NorthernMarianaIslands;
        }
        if (str.equals("NO")) {
            return COUNTRY.Norway;
        }
        if (str.equals("OM")) {
            return COUNTRY.Oman;
        }
        if (str.equals("PK")) {
            return COUNTRY.Pakistan;
        }
        if (str.equals("PW")) {
            return COUNTRY.Palau;
        }
        if (str.equals("PS")) {
            return COUNTRY.PalestineStateof;
        }
        if (str.equals("PA")) {
            return COUNTRY.Panama;
        }
        if (str.equals("PG")) {
            return COUNTRY.PapuaNewGuinea;
        }
        if (str.equals("PY")) {
            return COUNTRY.Paraguay;
        }
        if (str.equals("PE")) {
            return COUNTRY.Peru;
        }
        if (str.equals("PH")) {
            return COUNTRY.Philippines;
        }
        if (str.equals("PN")) {
            return COUNTRY.Pitcairn;
        }
        if (str.equals("PL")) {
            return COUNTRY.Poland;
        }
        if (str.equals("PT")) {
            return COUNTRY.Portugal;
        }
        if (str.equals("PR")) {
            return COUNTRY.PuertoRico;
        }
        if (str.equals("QA")) {
            return COUNTRY.Qatar;
        }
        if (str.equals("RE")) {
            return COUNTRY.Reunion;
        }
        if (str.equals("RO")) {
            return COUNTRY.Romania;
        }
        if (str.equals("RU")) {
            return COUNTRY.Russia;
        }
        if (str.equals("RW")) {
            return COUNTRY.Rwanda;
        }
        if (str.equals("BL")) {
            return COUNTRY.StBarts;
        }
        if (str.equals("SH")) {
            return COUNTRY.SaintHelenaAscensionandTristandaCunha;
        }
        if (str.equals("KN")) {
            return COUNTRY.SaintKittsandNevis;
        }
        if (str.equals("LC")) {
            return COUNTRY.SaintLucia;
        }
        if (str.equals("MF")) {
            return COUNTRY.SaintMartin;
        }
        if (str.equals("PM")) {
            return COUNTRY.SaintPierreandMiquelon;
        }
        if (str.equals("VC")) {
            return COUNTRY.SaintVincentandtheGrenadines;
        }
        if (str.equals("WS")) {
            return COUNTRY.Samoa;
        }
        if (str.equals("SM")) {
            return COUNTRY.SanMarino;
        }
        if (str.equals("ST")) {
            return COUNTRY.SaoTomeandPrincipe;
        }
        if (str.equals("SA")) {
            return COUNTRY.SaudiArabia;
        }
        if (str.equals("SN")) {
            return COUNTRY.Senegal;
        }
        if (str.equals("RS")) {
            return COUNTRY.Serbia;
        }
        if (str.equals("SC")) {
            return COUNTRY.Seychelles;
        }
        if (str.equals("SL")) {
            return COUNTRY.SierraLeone;
        }
        if (str.equals("SG")) {
            return COUNTRY.Singapore;
        }
        if (str.equals("SX")) {
            return COUNTRY.SintMaarten;
        }
        if (str.equals("SK")) {
            return COUNTRY.Slovakia;
        }
        if (str.equals("SI")) {
            return COUNTRY.Slovenia;
        }
        if (str.equals("SB")) {
            return COUNTRY.SolomonIslands;
        }
        if (str.equals("SO")) {
            return COUNTRY.Somalia;
        }
        if (str.equals("ZA")) {
            return COUNTRY.SouthAfrica;
        }
        if (str.equals("GS")) {
            return COUNTRY.SouthGeorgiaandtheSouthSandwichIslands;
        }
        if (str.equals("SS")) {
            return COUNTRY.SouthSudan;
        }
        if (str.equals("ES")) {
            return COUNTRY.Spain;
        }
        if (str.equals("LK")) {
            return COUNTRY.SriLanka;
        }
        if (str.equals("SD")) {
            return COUNTRY.Sudan;
        }
        if (str.equals("SR")) {
            return COUNTRY.Suriname;
        }
        if (str.equals("SJ")) {
            return COUNTRY.SvalbardandJanMayen;
        }
        if (str.equals("SZ")) {
            return COUNTRY.Swaziland;
        }
        if (str.equals("SE")) {
            return COUNTRY.Sweden;
        }
        if (str.equals("CH")) {
            return COUNTRY.Switzerland;
        }
        if (str.equals("SY")) {
            return COUNTRY.SyrianArabRepublic;
        }
        if (str.equals("TW")) {
            return COUNTRY.Taiwan;
        }
        if (str.equals("TJ")) {
            return COUNTRY.Tajikistan;
        }
        if (str.equals("TZ")) {
            return COUNTRY.Tanzania;
        }
        if (str.equals("TH")) {
            return COUNTRY.Thailand;
        }
        if (str.equals("TL")) {
            return COUNTRY.TimorLeste;
        }
        if (str.equals("TG")) {
            return COUNTRY.Togo;
        }
        if (str.equals("TK")) {
            return COUNTRY.Tokelau;
        }
        if (str.equals("TO")) {
            return COUNTRY.Tonga;
        }
        if (str.equals("TT")) {
            return COUNTRY.TrinidadandTobago;
        }
        if (str.equals("TN")) {
            return COUNTRY.Tunisia;
        }
        if (str.equals("TR")) {
            return COUNTRY.Turkey;
        }
        if (str.equals("TM")) {
            return COUNTRY.Turkmenistan;
        }
        if (str.equals("TC")) {
            return COUNTRY.TurksandCaicosIslands;
        }
        if (str.equals("TV")) {
            return COUNTRY.Tuvalu;
        }
        if (str.equals("UG")) {
            return COUNTRY.Uganda;
        }
        if (str.equals("UA")) {
            return COUNTRY.Ukraine;
        }
        if (str.equals("AE")) {
            return COUNTRY.UnitedArabEmirates;
        }
        if (str.equals("GB")) {
            return COUNTRY.UnitedKingdomofGreatBritainandNorthernIreland;
        }
        if (str.equals("UM")) {
            return COUNTRY.UnitedStatesMinorOutlyingIslands;
        }
        if (str.equals("US")) {
            return COUNTRY.UnitedStatesofAmerica;
        }
        if (str.equals("UY")) {
            return COUNTRY.Uruguay;
        }
        if (str.equals("UZ")) {
            return COUNTRY.Uzbekistan;
        }
        if (str.equals("VU")) {
            return COUNTRY.Vanuatu;
        }
        if (str.equals("VE")) {
            return COUNTRY.Venezuela;
        }
        if (str.equals("VN")) {
            return COUNTRY.VietNam;
        }
        if (str.equals("VG")) {
            return COUNTRY.BritishVirginIslands;
        }
        if (str.equals("VI")) {
            return COUNTRY.UnitedStatesVirginIslands;
        }
        if (str.equals("WF")) {
            return COUNTRY.WallisandFutuna;
        }
        if (str.equals("EH")) {
            return COUNTRY.WesternSahara;
        }
        if (str.equals("YE")) {
            return COUNTRY.Yemen;
        }
        if (str.equals("ZM")) {
            return COUNTRY.Zambia;
        }
        return str.equals("ZW") ? COUNTRY.Zimbabwe : COUNTRY.ETC;
    }

    public static COUNTRY getCountryFromCodeNumber(int i) {
        switch (i) {
            case 4:
                return COUNTRY.Afghanistan;
            case 8:
                return COUNTRY.Albania;
            case 10:
                return COUNTRY.Antarctica;
            case 12:
                return COUNTRY.Algeria;
            case 16:
                return COUNTRY.AmericanSamoa;
            case 20:
                return COUNTRY.Andorra;
            case 24:
                return COUNTRY.Angola;
            case 28:
                return COUNTRY.AntiguaandBarbuda;
            case 31:
                return COUNTRY.Azerbaijan;
            case 32:
                return COUNTRY.Argentina;
            case 36:
                return COUNTRY.Australia;
            case 40:
                return COUNTRY.Austria;
            case 44:
                return COUNTRY.Bahamas;
            case 48:
                return COUNTRY.Bahrain;
            case 50:
                return COUNTRY.Bangladesh;
            case 51:
                return COUNTRY.Armenia;
            case 52:
                return COUNTRY.Barbados;
            case 56:
                return COUNTRY.Belgium;
            case 60:
                return COUNTRY.Bermuda;
            case 64:
                return COUNTRY.Bhutan;
            case 68:
                return COUNTRY.Bolivia;
            case 70:
                return COUNTRY.BosniaandHerzegovina;
            case 72:
                return COUNTRY.Botswana;
            case 74:
                return COUNTRY.BouvetIsland;
            case 76:
                return COUNTRY.Brazil;
            case 84:
                return COUNTRY.Belize;
            case 86:
                return COUNTRY.BritishIndianOceanTerritory;
            case 90:
                return COUNTRY.SolomonIslands;
            case 92:
                return COUNTRY.BritishVirginIslands;
            case 96:
                return COUNTRY.BruneiDarussalam;
            case 100:
                return COUNTRY.Bulgaria;
            case 104:
                return COUNTRY.Myanmar;
            case 108:
                return COUNTRY.Burundi;
            case 112:
                return COUNTRY.Belarus;
            case 116:
                return COUNTRY.Cambodia;
            case 120:
                return COUNTRY.Cameroon;
            case MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES /* 124 */:
                return COUNTRY.Canada;
            case 132:
                return COUNTRY.CaboVerde;
            case 136:
                return COUNTRY.CaymanIslands;
            case 140:
                return COUNTRY.CentralAfricanRepublic;
            case 144:
                return COUNTRY.SriLanka;
            case 148:
                return COUNTRY.Chad;
            case 152:
                return COUNTRY.Chile;
            case 156:
                return COUNTRY.China;
            case 158:
                return COUNTRY.Taiwan;
            case 162:
                return COUNTRY.ChristmasIsland;
            case 166:
                return COUNTRY.CocosIslands;
            case 170:
                return COUNTRY.Colombia;
            case 174:
                return COUNTRY.Comoros;
            case 175:
                return COUNTRY.Mayotte;
            case 178:
                return COUNTRY.Congo;
            case 180:
                return COUNTRY.DemocraticRepublicoftheCongo;
            case 184:
                return COUNTRY.CookIslands;
            case 188:
                return COUNTRY.CostaRica;
            case 191:
                return COUNTRY.Croatia;
            case 192:
                return COUNTRY.Cuba;
            case 196:
                return COUNTRY.Cyprus;
            case 203:
                return COUNTRY.Czechia;
            case 204:
                return COUNTRY.Benin;
            case 208:
                return COUNTRY.Denmark;
            case 212:
                return COUNTRY.Dominica;
            case 214:
                return COUNTRY.DominicanRepublic;
            case 218:
                return COUNTRY.Ecuador;
            case 222:
                return COUNTRY.ElSalvador;
            case 226:
                return COUNTRY.EquatorialGuinea;
            case 231:
                return COUNTRY.Ethiopia;
            case 232:
                return COUNTRY.Eritrea;
            case 233:
                return COUNTRY.Estonia;
            case 234:
                return COUNTRY.FaroeIslands;
            case 238:
                return COUNTRY.FalklandIslands;
            case 239:
                return COUNTRY.SouthGeorgiaandtheSouthSandwichIslands;
            case 242:
                return COUNTRY.Fiji;
            case 246:
                return COUNTRY.Finland;
            case 248:
                return COUNTRY.f0landIslands;
            case 250:
                return COUNTRY.France;
            case 254:
                return COUNTRY.FrenchGuiana;
            case 258:
                return COUNTRY.FrenchPolynesia;
            case 260:
                return COUNTRY.FrenchSouthernTerritories;
            case 262:
                return COUNTRY.Djibouti;
            case 266:
                return COUNTRY.Gabon;
            case 268:
                return COUNTRY.Georgia;
            case 270:
                return COUNTRY.Gambia;
            case 275:
                return COUNTRY.PalestineStateof;
            case 276:
                return COUNTRY.Germany;
            case 288:
                return COUNTRY.Ghana;
            case 292:
                return COUNTRY.Gibraltar;
            case 296:
                return COUNTRY.Kiribati;
            case HttpResponseCode.MULTIPLE_CHOICES /* 300 */:
                return COUNTRY.Greece;
            case HttpResponseCode.NOT_MODIFIED /* 304 */:
                return COUNTRY.Greenland;
            case 308:
                return COUNTRY.Grenada;
            case 312:
                return COUNTRY.Guadeloupe;
            case 316:
                return COUNTRY.Guam;
            case 320:
                return COUNTRY.Guatemala;
            case 324:
                return COUNTRY.Guinea;
            case 328:
                return COUNTRY.Guyana;
            case 332:
                return COUNTRY.Haiti;
            case 334:
                return COUNTRY.HeardIslandandMcDonaldIslands;
            case 336:
                return COUNTRY.HolySee;
            case 340:
                return COUNTRY.Honduras;
            case 344:
                return COUNTRY.HongKong;
            case 348:
                return COUNTRY.Hungary;
            case 352:
                return COUNTRY.Iceland;
            case 356:
                return COUNTRY.India;
            case c.a /* 360 */:
                return COUNTRY.Indonesia;
            case 364:
                return COUNTRY.Iran;
            case 368:
                return COUNTRY.Iraq;
            case 372:
                return COUNTRY.Ireland;
            case 376:
                return COUNTRY.Israel;
            case 380:
                return COUNTRY.Italy;
            case 384:
                return COUNTRY.IvoryCoast;
            case 388:
                return COUNTRY.Jamaica;
            case 392:
                return COUNTRY.Japan;
            case 398:
                return COUNTRY.Kazakhstan;
            case HttpResponseCode.BAD_REQUEST /* 400 */:
                return COUNTRY.Jordan;
            case HttpResponseCode.NOT_FOUND /* 404 */:
                return COUNTRY.Kenya;
            case 408:
                return COUNTRY.NorthKorea;
            case 410:
                return COUNTRY.Korea;
            case 414:
                return COUNTRY.Kuwait;
            case 417:
                return COUNTRY.Kyrgyzstan;
            case 418:
                return COUNTRY.Laos;
            case HttpResponseCode.UNPROCESSABLE_ENTITY /* 422 */:
                return COUNTRY.Lebanon;
            case 426:
                return COUNTRY.Lesotho;
            case 428:
                return COUNTRY.Latvia;
            case 430:
                return COUNTRY.Liberia;
            case 434:
                return COUNTRY.Libya;
            case 438:
                return COUNTRY.Liechtenstein;
            case 440:
                return COUNTRY.Lithuania;
            case 442:
                return COUNTRY.Luxembourg;
            case 446:
                return COUNTRY.Macao;
            case 450:
                return COUNTRY.Madagascar;
            case 454:
                return COUNTRY.Malawi;
            case FacebookRequestErrorClassification.ESC_APP_NOT_INSTALLED /* 458 */:
                return COUNTRY.Malaysia;
            case 462:
                return COUNTRY.Maldives;
            case 466:
                return COUNTRY.Mali;
            case 470:
                return COUNTRY.Malta;
            case 474:
                return COUNTRY.Martinique;
            case 478:
                return COUNTRY.Mauritania;
            case c.b /* 480 */:
                return COUNTRY.Mauritius;
            case 484:
                return COUNTRY.Mexico;
            case 492:
                return COUNTRY.Monaco;
            case 496:
                return COUNTRY.Mongolia;
            case 498:
                return COUNTRY.Moldova;
            case 499:
                return COUNTRY.Montenegro;
            case 500:
                return COUNTRY.Montserrat;
            case HttpResponseCode.GATEWAY_TIMEOUT /* 504 */:
                return COUNTRY.Morocco;
            case 508:
                return COUNTRY.Mozambique;
            case 512:
                return COUNTRY.Oman;
            case 516:
                return COUNTRY.Namibia;
            case 520:
                return COUNTRY.Nauru;
            case 524:
                return COUNTRY.Nepal;
            case 528:
                return COUNTRY.Netherlands;
            case 531:
                return COUNTRY.Curacao;
            case 533:
                return COUNTRY.Aruba;
            case 534:
                return COUNTRY.SintMaarten;
            case 535:
                return COUNTRY.BonaireSintEustatiusandSaba;
            case 540:
                return COUNTRY.NewCaledonia;
            case 548:
                return COUNTRY.Vanuatu;
            case 554:
                return COUNTRY.NewZealand;
            case 558:
                return COUNTRY.Nicaragua;
            case 562:
                return COUNTRY.Niger;
            case 566:
                return COUNTRY.Nigeria;
            case 570:
                return COUNTRY.Niue;
            case 574:
                return COUNTRY.NorfolkIsland;
            case 578:
                return COUNTRY.Norway;
            case 580:
                return COUNTRY.NorthernMarianaIslands;
            case 581:
                return COUNTRY.UnitedStatesMinorOutlyingIslands;
            case 583:
                return COUNTRY.Micronesia;
            case 584:
                return COUNTRY.MarshallIslands;
            case 585:
                return COUNTRY.Palau;
            case 586:
                return COUNTRY.Pakistan;
            case 591:
                return COUNTRY.Panama;
            case 598:
                return COUNTRY.PapuaNewGuinea;
            case SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT /* 600 */:
                return COUNTRY.Paraguay;
            case 604:
                return COUNTRY.Peru;
            case 608:
                return COUNTRY.Philippines;
            case 612:
                return COUNTRY.Pitcairn;
            case 616:
                return COUNTRY.Poland;
            case 620:
                return COUNTRY.Portugal;
            case 624:
                return COUNTRY.GuineaBissau;
            case 626:
                return COUNTRY.TimorLeste;
            case 630:
                return COUNTRY.PuertoRico;
            case 634:
                return COUNTRY.Qatar;
            case 638:
                return COUNTRY.Reunion;
            case 642:
                return COUNTRY.Romania;
            case 643:
                return COUNTRY.Russia;
            case 646:
                return COUNTRY.Rwanda;
            case 652:
                return COUNTRY.StBarts;
            case 654:
                return COUNTRY.SaintHelenaAscensionandTristandaCunha;
            case 659:
                return COUNTRY.SaintKittsandNevis;
            case 660:
                return COUNTRY.Anguilla;
            case 662:
                return COUNTRY.SaintLucia;
            case 663:
                return COUNTRY.SaintMartin;
            case 666:
                return COUNTRY.SaintPierreandMiquelon;
            case 670:
                return COUNTRY.SaintVincentandtheGrenadines;
            case 674:
                return COUNTRY.SanMarino;
            case 678:
                return COUNTRY.SaoTomeandPrincipe;
            case 682:
                return COUNTRY.SaudiArabia;
            case 686:
                return COUNTRY.Senegal;
            case 688:
                return COUNTRY.Serbia;
            case 690:
                return COUNTRY.Seychelles;
            case 694:
                return COUNTRY.SierraLeone;
            case 702:
                return COUNTRY.Singapore;
            case 703:
                return COUNTRY.Slovakia;
            case 704:
                return COUNTRY.VietNam;
            case 705:
                return COUNTRY.Slovenia;
            case 706:
                return COUNTRY.Somalia;
            case 710:
                return COUNTRY.SouthAfrica;
            case 716:
                return COUNTRY.Zimbabwe;
            case 724:
                return COUNTRY.Spain;
            case 728:
                return COUNTRY.SouthSudan;
            case 729:
                return COUNTRY.Sudan;
            case 732:
                return COUNTRY.WesternSahara;
            case 740:
                return COUNTRY.Suriname;
            case 744:
                return COUNTRY.SvalbardandJanMayen;
            case 748:
                return COUNTRY.Swaziland;
            case 752:
                return COUNTRY.Sweden;
            case 756:
                return COUNTRY.Switzerland;
            case 760:
                return COUNTRY.SyrianArabRepublic;
            case 762:
                return COUNTRY.Tajikistan;
            case 764:
                return COUNTRY.Thailand;
            case 768:
                return COUNTRY.Togo;
            case 772:
                return COUNTRY.Tokelau;
            case 776:
                return COUNTRY.Tonga;
            case 780:
                return COUNTRY.TrinidadandTobago;
            case 784:
                return COUNTRY.UnitedArabEmirates;
            case 788:
                return COUNTRY.Tunisia;
            case 792:
                return COUNTRY.Turkey;
            case 795:
                return COUNTRY.Turkmenistan;
            case 796:
                return COUNTRY.TurksandCaicosIslands;
            case 798:
                return COUNTRY.Tuvalu;
            case 800:
                return COUNTRY.Uganda;
            case 804:
                return COUNTRY.Ukraine;
            case 807:
                return COUNTRY.Macedonia;
            case 818:
                return COUNTRY.Egypt;
            case 826:
                return COUNTRY.UnitedKingdomofGreatBritainandNorthernIreland;
            case 831:
                return COUNTRY.Guernsey;
            case 832:
                return COUNTRY.Jersey;
            case 833:
                return COUNTRY.IsleofMan;
            case 834:
                return COUNTRY.Tanzania;
            case 840:
                return COUNTRY.UnitedStatesofAmerica;
            case 850:
                return COUNTRY.UnitedStatesVirginIslands;
            case 854:
                return COUNTRY.BurkinaFaso;
            case 858:
                return COUNTRY.Uruguay;
            case 860:
                return COUNTRY.Uzbekistan;
            case 862:
                return COUNTRY.Venezuela;
            case 876:
                return COUNTRY.WallisandFutuna;
            case 882:
                return COUNTRY.Samoa;
            case 887:
                return COUNTRY.Yemen;
            case 894:
                return COUNTRY.Zambia;
            default:
                return COUNTRY.ETC;
        }
    }

    public static LOCALE getLocaleCode(String str) {
        if (str.equals("en_AU")) {
            return LOCALE.EN_AU;
        }
        if (str.equals("ms_BN")) {
            return LOCALE.MS_BN;
        }
        if (str.equals(ChannelCodes.CHINESE_SIMPLIFIED)) {
            return LOCALE.ZH_CN;
        }
        if (str.equals("en_GU")) {
            return LOCALE.EN_GU;
        }
        if (str.equals("ch_GU")) {
            return LOCALE.CH_GU;
        }
        if (str.equals("en_HK")) {
            return LOCALE.EN_HK;
        }
        if (str.equals("zh_HK")) {
            return LOCALE.ZH_HK;
        }
        if (str.equals("id_ID")) {
            return LOCALE.ID_ID;
        }
        if (str.equals("ja_JP")) {
            return LOCALE.JA_JP;
        }
        if (str.equals("ko_KR")) {
            return LOCALE.KO_KR;
        }
        if (str.equals("en_MO")) {
            return LOCALE.EN_MO;
        }
        if (str.equals("pt_MO")) {
            return LOCALE.PT_MO;
        }
        if (str.equals("zh_MO")) {
            return LOCALE.ZH_MO;
        }
        if (str.equals("ms_MY")) {
            return LOCALE.MS_MY;
        }
        if (str.equals("mn_MN")) {
            return LOCALE.MN_MN;
        }
        if (str.equals("en_NZ")) {
            return LOCALE.EN_NZ;
        }
        if (str.equals("mi_NZ")) {
            return LOCALE.MI_NZ;
        }
        if (str.equals("en_PH")) {
            return LOCALE.EN_PH;
        }
        if (str.equals("tl_PH")) {
            return LOCALE.TL_PH;
        }
        if (str.equals("ms_SG")) {
            return LOCALE.MS_SG;
        }
        if (str.equals("en_SG")) {
            return LOCALE.EN_SG;
        }
        if (str.equals("zh_SG")) {
            return LOCALE.ZH_SG;
        }
        if (str.equals("si_LK")) {
            return LOCALE.SI_LK;
        }
        if (str.equals("ta_LK")) {
            return LOCALE.TA_LK;
        }
        if (str.equals("en_LK")) {
            return LOCALE.EN_LK;
        }
        if (str.equals(ChannelCodes.CHINESE_TRADITIONAL)) {
            return LOCALE.ZH_TW;
        }
        if (str.equals("th_TH")) {
            return LOCALE.TH_TH;
        }
        if (str.equals("vi_VN")) {
            return LOCALE.VI_VN;
        }
        if (str.equals("sq_AL")) {
            return LOCALE.SQ_AL;
        }
        if (str.equals("nl_BE")) {
            return LOCALE.NL_BE;
        }
        if (str.equals("fr_BE")) {
            return LOCALE.FR_BE;
        }
        if (str.equals("de_BE")) {
            return LOCALE.DE_BE;
        }
        if (str.equals("el_CY")) {
            return LOCALE.EL_CY;
        }
        if (str.equals("tr_CY")) {
            return LOCALE.TR_CY;
        }
        if (str.equals("cs_CZ")) {
            return LOCALE.CS_CZ;
        }
        if (str.equals("da_DK")) {
            return LOCALE.DA_DK;
        }
        if (str.equals("de_DE")) {
            return LOCALE.DE_DE;
        }
        if (str.equals("es_ES")) {
            return LOCALE.ES_ES;
        }
        if (str.equals("fr_FR")) {
            return LOCALE.FR_FR;
        }
        if (str.equals("is_IS")) {
            return LOCALE.IS_IS;
        }
        if (str.equals("ga_IE")) {
            return LOCALE.GA_IE;
        }
        if (str.equals("it_IT")) {
            return LOCALE.IT_IT;
        }
        if (str.equals("de_LI")) {
            return LOCALE.DE_LI;
        }
        if (str.equals("de_LU")) {
            return LOCALE.DE_LU;
        }
        if (str.equals("fr_LU")) {
            return LOCALE.FR_LU;
        }
        if (str.equals("lb_LU")) {
            return LOCALE.LB_LU;
        }
        if (str.equals("mk_MK")) {
            return LOCALE.MK_MK;
        }
        if (str.equals("hu_HU")) {
            return LOCALE.HU_HU;
        }
        if (str.equals("mt_MT")) {
            return LOCALE.MT_MT;
        }
        if (str.equals("en_MT")) {
            return LOCALE.EN_MT;
        }
        if (str.equals("it_MT")) {
            return LOCALE.IT_MT;
        }
        if (str.equals("ro_MD")) {
            return LOCALE.RO_MD;
        }
        if (str.equals("sr_ME")) {
            return LOCALE.SR_ME;
        }
        if (str.equals("nl_NL")) {
            return LOCALE.NL_NL;
        }
        if (str.equals("no_NO")) {
            return LOCALE.NO_NO;
        }
        if (str.equals("de_AT")) {
            return LOCALE.DE_AT;
        }
        if (str.equals("pl_PL")) {
            return LOCALE.PL_PL;
        }
        if (str.equals("pt_PT")) {
            return LOCALE.PT_PT;
        }
        if (str.equals("ru_RU")) {
            return LOCALE.RU_RU;
        }
        if (str.equals("de_CH")) {
            return LOCALE.DE_CH;
        }
        if (str.equals("fr_CH")) {
            return LOCALE.FR_CH;
        }
        if (str.equals("it_CH")) {
            return LOCALE.IT_CH;
        }
        if (str.equals("fi_FI")) {
            return LOCALE.FI_FI;
        }
        if (str.equals("sv_FI")) {
            return LOCALE.SV_FI;
        }
        if (str.equals("sv_SE")) {
            return LOCALE.SV_SE;
        }
        if (str.equals("tr_TR")) {
            return LOCALE.TR_TR;
        }
        if (str.equals("en_GB")) {
            return LOCALE.EN_GB;
        }
        if (str.equals("en_AI")) {
            return LOCALE.EN_AI;
        }
        if (str.equals("en_AG")) {
            return LOCALE.EN_AG;
        }
        if (str.equals("et_AR")) {
            return LOCALE.ET_AR;
        }
        if (str.equals("en_BB")) {
            return LOCALE.EN_BB;
        }
        if (str.equals("en_BZ")) {
            return LOCALE.EN_BZ;
        }
        if (str.equals("en_BM")) {
            return LOCALE.EN_BM;
        }
        if (str.equals("et_BO")) {
            return LOCALE.ET_BO;
        }
        if (str.equals("pt_BR")) {
            return LOCALE.PT_BR;
        }
        if (str.equals("en_VG")) {
            return LOCALE.EN_VG;
        }
        if (str.equals("en_KY")) {
            return LOCALE.EN_KY;
        }
        if (str.equals("et_CL")) {
            return LOCALE.ET_CL;
        }
        if (str.equals("et_CO")) {
            return LOCALE.ET_CO;
        }
        if (str.equals("et_CR")) {
            return LOCALE.ET_CR;
        }
        if (str.equals("en_DM")) {
            return LOCALE.EN_DM;
        }
        if (str.equals("et_DO")) {
            return LOCALE.ET_DO;
        }
        if (str.equals("et_EC")) {
            return LOCALE.ET_EC;
        }
        if (str.equals("et_SV")) {
            return LOCALE.ET_SV;
        }
        if (str.equals("en_GD")) {
            return LOCALE.EN_GD;
        }
        if (str.equals("et_GT")) {
            return LOCALE.ET_GT;
        }
        if (str.equals("en_GY")) {
            return LOCALE.EN_GY;
        }
        if (str.equals("hi_GY")) {
            return LOCALE.HI_GY;
        }
        if (str.equals("ur_GY")) {
            return LOCALE.UR_GY;
        }
        if (str.equals("et_HN")) {
            return LOCALE.ET_HN;
        }
        if (str.equals("en_JM")) {
            return LOCALE.EN_JM;
        }
        if (str.equals("et_MX")) {
            return LOCALE.ET_MX;
        }
        if (str.equals("en_MS")) {
            return LOCALE.EN_MS;
        }
        if (str.equals("et_NI")) {
            return LOCALE.ET_NI;
        }
        if (str.equals("et_PA")) {
            return LOCALE.ET_PA;
        }
        if (str.equals("et_PY")) {
            return LOCALE.ET_PY;
        }
        if (str.equals("gn_PY")) {
            return LOCALE.GN_PY;
        }
        if (str.equals("ay_PE")) {
            return LOCALE.AY_PE;
        }
        if (str.equals("et_PE")) {
            return LOCALE.ET_PE;
        }
        if (str.equals("qy_PE")) {
            return LOCALE.QY_PE;
        }
        if (str.equals("en_KN")) {
            return LOCALE.EN_KN;
        }
        if (str.equals("en_LC")) {
            return LOCALE.EN_LC;
        }
        if (str.equals("en_VC")) {
            return LOCALE.EN_VC;
        }
        if (str.equals("fr_VC")) {
            return LOCALE.FR_VC;
        }
        if (str.equals("nl_SR")) {
            return LOCALE.NL_SR;
        }
        if (str.equals("en_BS")) {
            return LOCALE.EN_BS;
        }
        if (str.equals("en_TT")) {
            return LOCALE.EN_TT;
        }
        if (str.equals("en_TC")) {
            return LOCALE.EN_TC;
        }
        if (str.equals("et_UY")) {
            return LOCALE.ET_UY;
        }
        if (str.equals("et_VE")) {
            return LOCALE.ET_VE;
        }
        if (str.equals("en_CA")) {
            return LOCALE.EN_CA;
        }
        if (str.equals("fr_CA")) {
            return LOCALE.FR_CA;
        }
        if (str.equals("en_PR")) {
            return LOCALE.EN_PR;
        }
        if (str.equals("et_PR")) {
            return LOCALE.ET_PR;
        }
        if (str.equals("en_US")) {
            return LOCALE.EN_US;
        }
        if (str.equals("hy_AM")) {
            return LOCALE.HY_AM;
        }
        if (str.equals("ar_BH")) {
            return LOCALE.AR_BH;
        }
        if (str.equals("en_BW")) {
            return LOCALE.EN_BW;
        }
        if (str.equals("en_CM")) {
            return LOCALE.EN_CM;
        }
        if (str.equals("fr_CM")) {
            return LOCALE.FR_CM;
        }
        if (str.equals("fr_CI")) {
            return LOCALE.FR_CI;
        }
        if (str.equals("ar_EG")) {
            return LOCALE.AR_EG;
        }
        if (str.equals("pt_GW")) {
            return LOCALE.PT_GW;
        }
        if (str.equals("fr_GN")) {
            return LOCALE.FR_GN;
        }
        if (str.equals("et_GQ")) {
            return LOCALE.ET_GQ;
        }
        if (str.equals("fr_GQ")) {
            return LOCALE.FR_GQ;
        }
        if (str.equals("hi_IN")) {
            return LOCALE.HI_IN;
        }
        if (str.equals("en_IN")) {
            return LOCALE.EN_IN;
        }
        if (str.equals("he_IL")) {
            return LOCALE.HE_IL;
        }
        if (str.equals("ar_IL")) {
            return LOCALE.AR_IL;
        }
        if (str.equals("ar_JO")) {
            return LOCALE.AR_JO;
        }
        if (str.equals("en_KE")) {
            return LOCALE.EN_KE;
        }
        if (str.equals("ar_KW")) {
            return LOCALE.AR_KW;
        }
        if (str.equals("ar_LB")) {
            return LOCALE.AR_LB;
        }
        if (str.equals("fr_MG")) {
            return LOCALE.FR_MG;
        }
        if (str.equals("mg_MG")) {
            return LOCALE.MG_MG;
        }
        if (str.equals("fr_ML")) {
            return LOCALE.FR_ML;
        }
        if (str.equals("ar_MA")) {
            return LOCALE.AR_MA;
        }
        if (str.equals("en_MU")) {
            return LOCALE.EN_MU;
        }
        if (str.equals("pt_MZ")) {
            return LOCALE.PT_MZ;
        }
        if (str.equals("fr_NE")) {
            return LOCALE.FR_NE;
        }
        if (str.equals("en_NG")) {
            return LOCALE.EN_NG;
        }
        if (str.equals("ar_OM")) {
            return LOCALE.AR_OM;
        }
        if (str.equals("ar_QA")) {
            return LOCALE.AR_QA;
        }
        if (str.equals("fr_CF")) {
            return LOCALE.FR_CF;
        }
        if (str.equals("sg_CF")) {
            return LOCALE.SG_CF;
        }
        if (str.equals("ar_SA")) {
            return LOCALE.AR_SA;
        }
        if (str.equals("fr_SN")) {
            return LOCALE.FR_SN;
        }
        if (str.equals("af_ZA")) {
            return LOCALE.AF_ZA;
        }
        if (str.equals("en_ZA")) {
            return LOCALE.EN_ZA;
        }
        if (str.equals("zu_ZA")) {
            return LOCALE.ZU_ZA;
        }
        if (str.equals("ar_TN")) {
            return LOCALE.AR_TN;
        }
        return str.equals("ar_AE") ? LOCALE.AR_AE : LOCALE.ETC;
    }

    public static LOCALE getLocaleFromCodeNumber(int i) {
        switch (i) {
            case 0:
                return LOCALE.EN_AU;
            case 1:
                return LOCALE.MS_BN;
            case 2:
                return LOCALE.ZH_CN;
            case 3:
                return LOCALE.EN_GU;
            case 4:
                return LOCALE.CH_GU;
            case 5:
                return LOCALE.EN_HK;
            case 6:
                return LOCALE.ZH_HK;
            case 7:
                return LOCALE.ID_ID;
            case 8:
                return LOCALE.JA_JP;
            case 9:
                return LOCALE.KO_KR;
            case 10:
                return LOCALE.EN_MO;
            case 11:
                return LOCALE.PT_MO;
            case 12:
                return LOCALE.ZH_MO;
            case 13:
                return LOCALE.MS_MY;
            case 14:
                return LOCALE.MN_MN;
            case 15:
                return LOCALE.EN_NZ;
            case 16:
                return LOCALE.MI_NZ;
            case 17:
                return LOCALE.EN_PH;
            case 18:
                return LOCALE.TL_PH;
            case 19:
                return LOCALE.MS_SG;
            case 20:
                return LOCALE.EN_SG;
            case 21:
                return LOCALE.ZH_SG;
            case 22:
                return LOCALE.SI_LK;
            case 23:
                return LOCALE.TA_LK;
            case 24:
                return LOCALE.EN_LK;
            case 25:
                return LOCALE.ZH_TW;
            case 26:
                return LOCALE.TH_TH;
            case 27:
                return LOCALE.VI_VN;
            case 28:
                return LOCALE.SQ_AL;
            case 29:
                return LOCALE.NL_BE;
            case 30:
                return LOCALE.FR_BE;
            case 31:
                return LOCALE.DE_BE;
            case 32:
                return LOCALE.EL_CY;
            case 33:
                return LOCALE.TR_CY;
            case 34:
                return LOCALE.CS_CZ;
            case 35:
                return LOCALE.DA_DK;
            case 36:
                return LOCALE.DE_DE;
            case 37:
                return LOCALE.ES_ES;
            case 38:
                return LOCALE.FR_FR;
            case 39:
                return LOCALE.IS_IS;
            case 40:
                return LOCALE.GA_IE;
            case 41:
                return LOCALE.IT_IT;
            case 42:
                return LOCALE.DE_LI;
            case 43:
                return LOCALE.DE_LU;
            case 44:
                return LOCALE.FR_LU;
            case 45:
                return LOCALE.LB_LU;
            case 46:
                return LOCALE.MK_MK;
            case 47:
                return LOCALE.HU_HU;
            case 48:
                return LOCALE.MT_MT;
            case 49:
                return LOCALE.EN_MT;
            case 50:
                return LOCALE.IT_MT;
            case 51:
                return LOCALE.RO_MD;
            case 52:
                return LOCALE.SR_ME;
            case 53:
                return LOCALE.NL_NL;
            case 54:
                return LOCALE.NO_NO;
            case 55:
                return LOCALE.DE_AT;
            case 56:
                return LOCALE.PL_PL;
            case 57:
                return LOCALE.PT_PT;
            case 58:
                return LOCALE.RU_RU;
            case 59:
                return LOCALE.DE_CH;
            case 60:
                return LOCALE.FR_CH;
            case 61:
                return LOCALE.IT_CH;
            case 62:
                return LOCALE.FI_FI;
            case 63:
                return LOCALE.SV_FI;
            case 64:
                return LOCALE.SV_SE;
            case 65:
                return LOCALE.TR_TR;
            case 66:
                return LOCALE.EN_GB;
            case 67:
                return LOCALE.EN_AI;
            case 68:
                return LOCALE.EN_AG;
            case 69:
                return LOCALE.ET_AR;
            case 70:
                return LOCALE.EN_BB;
            case 71:
                return LOCALE.EN_BZ;
            case 72:
                return LOCALE.EN_BM;
            case 73:
                return LOCALE.ET_BO;
            case 74:
                return LOCALE.PT_BR;
            case 75:
                return LOCALE.EN_VG;
            case 76:
                return LOCALE.EN_KY;
            case 77:
                return LOCALE.ET_CL;
            case 78:
                return LOCALE.ET_CO;
            case 79:
                return LOCALE.ET_CR;
            case 80:
                return LOCALE.EN_DM;
            case 81:
                return LOCALE.ET_DO;
            case 82:
                return LOCALE.ET_EC;
            case 83:
                return LOCALE.ET_SV;
            case 84:
                return LOCALE.EN_GD;
            case 85:
                return LOCALE.ET_GT;
            case 86:
                return LOCALE.EN_GY;
            case 87:
                return LOCALE.HI_GY;
            case 88:
                return LOCALE.UR_GY;
            case 89:
                return LOCALE.ET_HN;
            case 90:
                return LOCALE.EN_JM;
            case 91:
                return LOCALE.ET_MX;
            case 92:
                return LOCALE.EN_MS;
            case 93:
                return LOCALE.ET_NI;
            case 94:
                return LOCALE.ET_PA;
            case 95:
                return LOCALE.ET_PY;
            case 96:
                return LOCALE.GN_PY;
            case 97:
                return LOCALE.AY_PE;
            case 98:
                return LOCALE.ET_PE;
            case 99:
                return LOCALE.QY_PE;
            case 100:
                return LOCALE.EN_KN;
            case 101:
                return LOCALE.EN_LC;
            case 102:
                return LOCALE.EN_VC;
            case 103:
                return LOCALE.FR_VC;
            case 104:
                return LOCALE.NL_SR;
            case 105:
                return LOCALE.EN_BS;
            case 106:
                return LOCALE.EN_TT;
            case 107:
                return LOCALE.EN_TC;
            case 108:
                return LOCALE.ET_UY;
            case 109:
                return LOCALE.ET_VE;
            case 110:
                return LOCALE.EN_CA;
            case 111:
                return LOCALE.FR_CA;
            case 112:
                return LOCALE.EN_PR;
            case 113:
                return LOCALE.ET_PR;
            case 114:
                return LOCALE.EN_US;
            case 115:
                return LOCALE.HY_AM;
            case 116:
                return LOCALE.AR_BH;
            case 117:
                return LOCALE.EN_BW;
            case 118:
                return LOCALE.EN_CM;
            case 119:
                return LOCALE.FR_CM;
            case 120:
                return LOCALE.FR_CI;
            case 121:
                return LOCALE.AR_EG;
            case 122:
                return LOCALE.PT_GW;
            case 123:
                return LOCALE.FR_GN;
            case MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES /* 124 */:
                return LOCALE.ET_GQ;
            case 125:
                return LOCALE.FR_GQ;
            case 126:
                return LOCALE.HI_IN;
            case 127:
                return LOCALE.EN_IN;
            case 128:
                return LOCALE.HE_IL;
            case GmsClientSupervisor.DEFAULT_BIND_FLAGS /* 129 */:
                return LOCALE.AR_IL;
            case 130:
                return LOCALE.AR_JO;
            case 131:
                return LOCALE.EN_KE;
            case 132:
                return LOCALE.AR_KW;
            case 133:
                return LOCALE.AR_LB;
            case 134:
                return LOCALE.FR_MG;
            case 135:
                return LOCALE.MG_MG;
            case 136:
                return LOCALE.FR_ML;
            case 137:
                return LOCALE.AR_MA;
            case 138:
                return LOCALE.EN_MU;
            case 139:
                return LOCALE.PT_MZ;
            case 140:
                return LOCALE.FR_NE;
            case 141:
                return LOCALE.EN_NG;
            case 142:
                return LOCALE.AR_OM;
            case 143:
                return LOCALE.AR_QA;
            case 144:
                return LOCALE.FR_CF;
            case 145:
                return LOCALE.SG_CF;
            case 146:
                return LOCALE.AR_SA;
            case 147:
                return LOCALE.FR_SN;
            case 148:
                return LOCALE.AF_ZA;
            case 149:
                return LOCALE.EN_ZA;
            case 150:
                return LOCALE.ZU_ZA;
            case 151:
                return LOCALE.AR_TN;
            case 152:
                return LOCALE.AR_AE;
            default:
                return LOCALE.ETC;
        }
    }
}
