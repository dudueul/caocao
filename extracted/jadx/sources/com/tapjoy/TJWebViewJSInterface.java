package com.tapjoy;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TJWebViewJSInterface {
    WebView a;
    TJWebViewJSInterfaceListener b;
    private final ConcurrentLinkedQueue c = new ConcurrentLinkedQueue();
    private boolean d;

    @TargetApi(19)
    class a extends AsyncTask {
        WebView a;

        public a(WebView webView) {
            this.a = webView;
        }

        @Override // android.os.AsyncTask
        protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
            return ((String[]) objArr)[0];
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            if (this.a != null) {
                if (!str.startsWith("javascript:") || Build.VERSION.SDK_INT < 19) {
                    try {
                        this.a.loadUrl(str);
                        return;
                    } catch (Exception e) {
                        TapjoyLog.e("TJWebViewJSInterface", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTERNAL_ERROR, "Exception in loadUrl. Device not supported. " + e.toString()));
                        return;
                    }
                }
                try {
                    this.a.evaluateJavascript(str.replaceFirst("javascript:", ""), null);
                } catch (Exception e2) {
                    TapjoyLog.e("TJWebViewJSInterface", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTERNAL_ERROR, "Exception in evaluateJavascript. Device not supported. " + e2.toString()));
                }
            }
        }
    }

    public TJWebViewJSInterface(WebView webView, TJWebViewJSInterfaceListener tJWebViewJSInterfaceListener) {
        this.a = webView;
        this.b = tJWebViewJSInterfaceListener;
    }

    public void callback(ArrayList arrayList, String str, String str2) {
        try {
            callbackToJavaScript(new JSONArray((Collection) arrayList), str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callback(Map map, String str, String str2) {
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(new JSONObject(map));
            callbackToJavaScript(jSONArray, str, str2);
        } catch (Exception e) {
            TapjoyLog.e("TJWebViewJSInterface", "Exception in callback to JS: " + e.toString());
            e.printStackTrace();
        }
    }

    public void callbackToJavaScript(Object obj, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TJAdUnitConstants.String.ARGUMENTS, obj);
            if (str != null && str.length() > 0) {
                jSONObject.put("method", str);
            }
            JSONObject jSONObject2 = new JSONObject();
            if (str2 != null && str2.length() > 0) {
                jSONObject2.put(TJAdUnitConstants.String.CALLBACK_ID, str2);
            }
            jSONObject2.put("data", jSONObject);
            String str3 = "javascript:if(window.AndroidWebViewJavascriptBridge) AndroidWebViewJavascriptBridge._handleMessageFromAndroid('" + jSONObject2 + "');";
            if (this.d) {
                new a(this.a).execute(str3);
            } else {
                this.c.add(str3);
            }
        } catch (Exception e) {
            TapjoyLog.e("TJWebViewJSInterface", "Exception in callback to JS: " + e.toString());
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void dispatchMethod(String str) {
        TapjoyLog.d("TJWebViewJSInterface", "dispatchMethod params: " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getJSONObject("data").getString("method");
            TapjoyLog.d("TJWebViewJSInterface", "method: " + string);
            if (this.b != null) {
                this.b.onDispatchMethod(string, jSONObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void flushMessageQueue() {
        if (this.d) {
            return;
        }
        while (true) {
            String str = (String) this.c.poll();
            if (str == null) {
                this.d = true;
                return;
            }
            new a(this.a).execute(str);
        }
    }
}
