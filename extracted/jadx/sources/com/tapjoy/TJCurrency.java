package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import com.nexon.nexonanalyticssdk.NxLogInfo;
import com.tapjoy.internal.fr;
import java.util.Map;
import java.util.UUID;
import org.w3c.dom.Document;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJCurrency {
    private static TJGetCurrencyBalanceListener d;
    private static TJSpendCurrencyListener e;
    private static TJAwardCurrencyListener f;
    private static TJEarnedCurrencyListener g;
    String a = null;
    int b = 0;
    Context c;

    public TJCurrency(Context context) {
        this.c = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:29:0x009b A[Catch: all -> 0x00a3, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000d, B:9:0x0019, B:11:0x0022, B:14:0x003a, B:19:0x004c, B:20:0x0069, B:22:0x0070, B:26:0x0078, B:34:0x00a6, B:35:0x00b5, B:27:0x0097, B:29:0x009b, B:36:0x00c4), top: B:38:0x0001, inners: #1 }] */
    public synchronized void a(TapjoyHttpURLResponse tapjoyHttpURLResponse) {
        if (tapjoyHttpURLResponse.response != null) {
            Document documentBuildDocument = TapjoyUtil.buildDocument(tapjoyHttpURLResponse.response);
            if (documentBuildDocument != null) {
                String nodeTrimValue = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("Success"));
                if (nodeTrimValue == null || !nodeTrimValue.equals("true")) {
                    TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "getCurrencyBalance response is invalid -- missing <Success> tag."));
                } else {
                    String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("TapPoints"));
                    String nodeTrimValue3 = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("CurrencyName"));
                    if (nodeTrimValue2 == null || nodeTrimValue3 == null) {
                        TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "getCurrencyBalance response is invalid -- missing tags."));
                    } else {
                        try {
                            int i = Integer.parseInt(nodeTrimValue2);
                            int localCurrencyBalance = getLocalCurrencyBalance();
                            if (g != null && localCurrencyBalance != -9999 && i > localCurrencyBalance) {
                                TapjoyLog.i("TJCurrency", "earned: " + (i - localCurrencyBalance));
                                g.onEarnedCurrency(nodeTrimValue3, i - localCurrencyBalance);
                            }
                            saveCurrencyBalance(i);
                            if (d != null) {
                                d.onGetCurrencyBalanceResponse(nodeTrimValue3, i);
                            }
                        } catch (Exception e2) {
                            TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "Error parsing XML and calling listener: " + e2.toString()));
                            if (d != null) {
                                d.onGetCurrencyBalanceResponseFailure("Failed to get currency balance");
                            }
                        }
                    }
                }
            }
        } else {
            TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "getCurrencyBalance response is NULL"));
        }
        if (d != null) {
            d.onGetCurrencyBalanceResponseFailure("Failed to get currency balance");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(TapjoyHttpURLResponse tapjoyHttpURLResponse) {
        String nodeTrimValue = "Failed to spend currency";
        if (tapjoyHttpURLResponse.response != null) {
            Document documentBuildDocument = TapjoyUtil.buildDocument(tapjoyHttpURLResponse.response);
            if (documentBuildDocument != null) {
                String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("Success"));
                if (nodeTrimValue2 != null && nodeTrimValue2.equals("true")) {
                    String nodeTrimValue3 = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("TapPoints"));
                    String nodeTrimValue4 = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("CurrencyName"));
                    if (nodeTrimValue3 == null || nodeTrimValue4 == null) {
                        TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "spendCurrency response is invalid -- missing tags."));
                    } else {
                        int i = Integer.parseInt(nodeTrimValue3);
                        saveCurrencyBalance(i);
                        if (e != null) {
                            e.onSpendCurrencyResponse(nodeTrimValue4, i);
                        }
                    }
                } else if (nodeTrimValue2 == null || !nodeTrimValue2.endsWith("false")) {
                    TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "spendCurrency response is invalid -- missing <Success> tag."));
                } else {
                    nodeTrimValue = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("Message"));
                    TapjoyLog.i("TJCurrency", nodeTrimValue);
                    if ("BalanceTooLowError".equals(TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("MessageCode")))) {
                        fr.a();
                    }
                }
            }
        } else {
            TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "spendCurrency response is NULL"));
        }
        if (e != null) {
            e.onSpendCurrencyResponseFailure(nodeTrimValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void c(TapjoyHttpURLResponse tapjoyHttpURLResponse) {
        String nodeTrimValue = "Failed to award currency";
        if (tapjoyHttpURLResponse.response != null) {
            Document documentBuildDocument = TapjoyUtil.buildDocument(tapjoyHttpURLResponse.response);
            if (documentBuildDocument != null) {
                String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("Success"));
                if (nodeTrimValue2 != null && nodeTrimValue2.equals("true")) {
                    String nodeTrimValue3 = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("TapPoints"));
                    String nodeTrimValue4 = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("CurrencyName"));
                    if (nodeTrimValue3 == null || nodeTrimValue4 == null) {
                        TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "awardCurrency response is invalid -- missing tags."));
                    } else {
                        int i = Integer.parseInt(nodeTrimValue3);
                        saveCurrencyBalance(i);
                        if (f != null) {
                            f.onAwardCurrencyResponse(nodeTrimValue4, i);
                        }
                    }
                } else if (nodeTrimValue2 == null || !nodeTrimValue2.endsWith("false")) {
                    TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "awardCurrency response is invalid -- missing <Success> tag."));
                } else {
                    nodeTrimValue = TapjoyUtil.getNodeTrimValue(documentBuildDocument.getElementsByTagName("Message"));
                    TapjoyLog.i("TJCurrency", nodeTrimValue);
                }
            }
        } else {
            TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "awardCurrency response is NULL"));
        }
        if (f != null) {
            f.onAwardCurrencyResponseFailure(nodeTrimValue);
        }
    }

    public void awardCurrency(int i, TJAwardCurrencyListener tJAwardCurrencyListener) {
        if (i < 0) {
            TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Amount must be a positive number for the awardCurrency API"));
            return;
        }
        this.b = i;
        f = tJAwardCurrencyListener;
        String string = UUID.randomUUID().toString();
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        final Map genericURLParams = TapjoyConnectCore.getGenericURLParams();
        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_CURRENCY, String.valueOf(this.b), true);
        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_GUID, string, true);
        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_TIMESTAMP, String.valueOf(jCurrentTimeMillis), true);
        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_VERIFIER, TapjoyConnectCore.getAwardCurrencyVerifier(jCurrentTimeMillis, this.b, string), true);
        new Thread(new Runnable() { // from class: com.tapjoy.TJCurrency.3
            @Override // java.lang.Runnable
            public final void run() {
                TJCurrency.this.c(new TapjoyURLConnection().getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_AWARD_CURRENCY_URL_PATH, genericURLParams));
            }
        }).start();
    }

    public void getCurrencyBalance(TJGetCurrencyBalanceListener tJGetCurrencyBalanceListener) {
        d = tJGetCurrencyBalanceListener;
        final Map uRLParams = TapjoyConnectCore.getURLParams();
        new Thread(new Runnable() { // from class: com.tapjoy.TJCurrency.1
            @Override // java.lang.Runnable
            public final void run() {
                TJCurrency.this.a(new TapjoyURLConnection().getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_GET_CURRENCY_BALANCE_URL_PATH, uRLParams));
            }
        }).start();
    }

    public int getLocalCurrencyBalance() {
        return this.c.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).getInt(TapjoyConstants.PREF_LAST_CURRENCY_BALANCE, NxLogInfo.VALUE_STAGE_INVALID_CODE);
    }

    public void saveCurrencyBalance(int i) {
        SharedPreferences.Editor editorEdit = this.c.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).edit();
        editorEdit.putInt(TapjoyConstants.PREF_LAST_CURRENCY_BALANCE, i);
        editorEdit.apply();
    }

    public void setEarnedCurrencyListener(TJEarnedCurrencyListener tJEarnedCurrencyListener) {
        g = tJEarnedCurrencyListener;
    }

    public void spendCurrency(int i, TJSpendCurrencyListener tJSpendCurrencyListener) {
        if (i < 0) {
            TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Amount must be a positive number for the spendCurrency API"));
            return;
        }
        this.a = String.valueOf(i);
        e = tJSpendCurrencyListener;
        final Map uRLParams = TapjoyConnectCore.getURLParams();
        TapjoyUtil.safePut(uRLParams, TapjoyConstants.TJC_CURRENCY, this.a, true);
        new Thread(new Runnable() { // from class: com.tapjoy.TJCurrency.2
            @Override // java.lang.Runnable
            public final void run() {
                TJCurrency.this.b(new TapjoyURLConnection().getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_SPEND_CURRENCY_URL_PATH, uRLParams));
            }
        }).start();
    }
}
