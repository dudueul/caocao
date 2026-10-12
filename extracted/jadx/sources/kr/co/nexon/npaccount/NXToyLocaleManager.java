package kr.co.nexon.npaccount;

import android.content.Context;
import android.support.annotation.StringRes;
import com.nexon.core.locale.NXLocale;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.core.requestpostman.interfaces.NXToyRequestListener;
import com.nexon.core.requestpostman.result.NXToyResult;
import com.nexon.core.session.NXToySessionManager;
import com.nexon.core.util.NXStringUtil;
import com.nexon.platform.NXPFinalizer;
import java.util.Locale;
import kr.co.nexon.mdev.android.util.NXLocalizedString;
import kr.co.nexon.mdev.android.util.NXTelephonyUtil;
import kr.co.nexon.npaccount.auth.request.NXToyGetCountryRequest;
import kr.co.nexon.npaccount.auth.result.NXToyCountryResult;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyLocaleManager {
    private final Context applicationContext;
    private static NXToyLocaleManager instance = null;
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.npaccount.NXToyLocaleManager.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NXToyLocaleManager unused = NXToyLocaleManager.instance = null;
        }
    };

    public NXToyLocaleManager(Context context) {
        this.applicationContext = context;
    }

    public static NXToyLocaleManager getInstance() {
        if (instance == null) {
            return null;
        }
        return instance;
    }

    public static NXToyLocaleManager getInstance(Context context) {
        if (instance == null) {
            synchronized (NXToyLocaleManager.class) {
                instance = new NXToyLocaleManager(context);
            }
        }
        return instance;
    }

    public NXLocale.COUNTRY getCountry() {
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        return nXToyCommonPreferenceController.getCountry().equals("") ? NXLocale.getCountryCode(Locale.getDefault().getCountry()) : NXLocale.getCountryCode(nXToyCommonPreferenceController.getCountry());
    }

    public NXLocale.LOCALE getLocale() {
        ToyLog.d("default locale:" + Locale.getDefault().toString());
        NXToyCommonPreferenceController nXToyCommonPreferenceController = NXToyCommonPreferenceController.getInstance();
        return (nXToyCommonPreferenceController.getPreference() == null || nXToyCommonPreferenceController.getLocale().equals("")) ? NXLocale.getLocaleCode(Locale.getDefault().toString()) : NXLocale.getLocaleCode(nXToyCommonPreferenceController.getLocale());
    }

    public String getString(int i) {
        return getString(i, getLocale().getLocaleCode());
    }

    public String getString(int i, String str) {
        if (NXStringUtil.isNull(str)) {
            str = getLocale().getLocaleCode();
        }
        return NXLocalizedString.getText(this.applicationContext, str, i);
    }

    public String getStringEx(@StringRes int i, String str) {
        return this.applicationContext == null ? str : getString(i);
    }

    public void loadLocatedCountry(final NPListener nPListener, NXToySessionManager nXToySessionManager) {
        int i;
        int i2;
        String mcc = NXTelephonyUtil.getMcc(this.applicationContext);
        String mnc = NXTelephonyUtil.getMnc(this.applicationContext);
        if (mnc == null) {
            i = 0;
        } else {
            try {
                i = Integer.parseInt(mnc);
            } catch (Exception e) {
                i = 0;
                i2 = 0;
            }
        }
        i2 = mcc == null ? 0 : Integer.parseInt(mcc);
        NXToyRequestPostman.getInstance().postRequest(new NXToyGetCountryRequest(i2, i), new NXToyRequestListener() { // from class: kr.co.nexon.npaccount.NXToyLocaleManager.2
            @Override // com.nexon.core.requestpostman.interfaces.NXToyRequestListener
            public void onComplete(NXToyResult nXToyResult) {
                if (nXToyResult.errorCode == NXToyErrorCode.SUCCESS.getCode()) {
                    NXToyCountryResult nXToyCountryResult = (NXToyCountryResult) nXToyResult;
                    nXToyCountryResult.result.countryCode = NXLocale.getCountryCode(nXToyCountryResult.result.country).getCountryCodeNumber();
                    ToyLog.d("loadLocatedCountry result(success):" + nXToyCountryResult);
                    nPListener.onResult(nXToyCountryResult);
                    return;
                }
                NXToyCountryResult nXToyCountryResult2 = (NXToyCountryResult) nXToyResult;
                nXToyCountryResult2.result.country = NXToyLocaleManager.this.getCountry().getCountryCode();
                nXToyCountryResult2.result.countryCode = NXToyLocaleManager.this.getCountry().getCountryCodeNumber();
                ToyLog.d("loadLocatedCountry result(failed):" + nXToyCountryResult2);
                nPListener.onResult(nXToyCountryResult2);
            }
        });
    }

    public void setCountry(NXLocale.COUNTRY country) {
        ToyLog.d("setCountry :" + country);
        if (country != null) {
            NXToyCommonPreferenceController.getInstance().setCountry(country.getCountryCode());
        }
    }

    public void setLocale(NXLocale.LOCALE locale) {
        ToyLog.d("setLocale :" + locale);
        if (locale != null) {
            NXToyCommonPreferenceController.getInstance().setLocale(locale.getLocaleCode());
        }
    }
}
