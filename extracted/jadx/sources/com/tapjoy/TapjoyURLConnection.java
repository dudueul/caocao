package com.tapjoy;

import com.tapjoy.internal.fj;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TapjoyURLConnection {
    public static final int TYPE_GET = 0;
    public static final int TYPE_POST = 1;

    public String getContentLength(String str) {
        String headerField;
        try {
            String strReplaceAll = str.replaceAll(" ", "%20");
            TapjoyLog.d("TapjoyURLConnection", "requestURL: " + strReplaceAll);
            HttpURLConnection httpURLConnection = (HttpURLConnection) fj.a(new URL(strReplaceAll));
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(30000);
            headerField = httpURLConnection.getHeaderField("content-length");
        } catch (Exception e) {
            TapjoyLog.e("TapjoyURLConnection", "Exception: " + e.toString());
            headerField = null;
        }
        TapjoyLog.d("TapjoyURLConnection", "content-length: " + headerField);
        return headerField;
    }

    public TapjoyHttpURLResponse getRedirectFromURL(String str) {
        return getResponseFromURL(str, "", 0, true, null, null, null);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str) {
        return getResponseFromURL(str, "", 0);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, String str2) {
        return getResponseFromURL(str, str2, 0);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, String str2, int i) {
        return getResponseFromURL(str, str2, i, false, null, null, null);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0057 A[Catch: Exception -> 0x007b, TryCatch #1 {Exception -> 0x007b, blocks: (B:9:0x0048, B:10:0x004b, B:12:0x0057, B:13:0x005f, B:15:0x0065, B:41:0x014b, B:43:0x0152, B:44:0x0198, B:46:0x01b5, B:48:0x01c6, B:49:0x01cb, B:51:0x01d1, B:52:0x01e8, B:53:0x01ee, B:55:0x01f4, B:56:0x01fc, B:60:0x0210, B:62:0x0216, B:58:0x0204), top: B:71:0x0048, inners: #3 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x0065 A[Catch: Exception -> 0x007b, LOOP:1: B:13:0x005f->B:15:0x0065, LOOP_END, TRY_LEAVE, TryCatch #1 {Exception -> 0x007b, blocks: (B:9:0x0048, B:10:0x004b, B:12:0x0057, B:13:0x005f, B:15:0x0065, B:41:0x014b, B:43:0x0152, B:44:0x0198, B:46:0x01b5, B:48:0x01c6, B:49:0x01cb, B:51:0x01d1, B:52:0x01e8, B:53:0x01ee, B:55:0x01f4, B:56:0x01fc, B:60:0x0210, B:62:0x0216, B:58:0x0204), top: B:71:0x0048, inners: #3 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x014b A[Catch: Exception -> 0x007b, TRY_ENTER, TryCatch #1 {Exception -> 0x007b, blocks: (B:9:0x0048, B:10:0x004b, B:12:0x0057, B:13:0x005f, B:15:0x0065, B:41:0x014b, B:43:0x0152, B:44:0x0198, B:46:0x01b5, B:48:0x01c6, B:49:0x01cb, B:51:0x01d1, B:52:0x01e8, B:53:0x01ee, B:55:0x01f4, B:56:0x01fc, B:60:0x0210, B:62:0x0216, B:58:0x0204), top: B:71:0x0048, inners: #3 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x0152 A[Catch: Exception -> 0x007b, TryCatch #1 {Exception -> 0x007b, blocks: (B:9:0x0048, B:10:0x004b, B:12:0x0057, B:13:0x005f, B:15:0x0065, B:41:0x014b, B:43:0x0152, B:44:0x0198, B:46:0x01b5, B:48:0x01c6, B:49:0x01cb, B:51:0x01d1, B:52:0x01e8, B:53:0x01ee, B:55:0x01f4, B:56:0x01fc, B:60:0x0210, B:62:0x0216, B:58:0x0204), top: B:71:0x0048, inners: #3 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x01b5 A[Catch: Exception -> 0x007b, TryCatch #1 {Exception -> 0x007b, blocks: (B:9:0x0048, B:10:0x004b, B:12:0x0057, B:13:0x005f, B:15:0x0065, B:41:0x014b, B:43:0x0152, B:44:0x0198, B:46:0x01b5, B:48:0x01c6, B:49:0x01cb, B:51:0x01d1, B:52:0x01e8, B:53:0x01ee, B:55:0x01f4, B:56:0x01fc, B:60:0x0210, B:62:0x0216, B:58:0x0204), top: B:71:0x0048, inners: #3 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x01c6 A[Catch: Exception -> 0x007b, TryCatch #1 {Exception -> 0x007b, blocks: (B:9:0x0048, B:10:0x004b, B:12:0x0057, B:13:0x005f, B:15:0x0065, B:41:0x014b, B:43:0x0152, B:44:0x0198, B:46:0x01b5, B:48:0x01c6, B:49:0x01cb, B:51:0x01d1, B:52:0x01e8, B:53:0x01ee, B:55:0x01f4, B:56:0x01fc, B:60:0x0210, B:62:0x0216, B:58:0x0204), top: B:71:0x0048, inners: #3 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x01d1 A[Catch: Exception -> 0x007b, LOOP:2: B:49:0x01cb->B:51:0x01d1, LOOP_END, TryCatch #1 {Exception -> 0x007b, blocks: (B:9:0x0048, B:10:0x004b, B:12:0x0057, B:13:0x005f, B:15:0x0065, B:41:0x014b, B:43:0x0152, B:44:0x0198, B:46:0x01b5, B:48:0x01c6, B:49:0x01cb, B:51:0x01d1, B:52:0x01e8, B:53:0x01ee, B:55:0x01f4, B:56:0x01fc, B:60:0x0210, B:62:0x0216, B:58:0x0204), top: B:71:0x0048, inners: #3 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x01f4 A[Catch: Exception -> 0x007b, TryCatch #1 {Exception -> 0x007b, blocks: (B:9:0x0048, B:10:0x004b, B:12:0x0057, B:13:0x005f, B:15:0x0065, B:41:0x014b, B:43:0x0152, B:44:0x0198, B:46:0x01b5, B:48:0x01c6, B:49:0x01cb, B:51:0x01d1, B:52:0x01e8, B:53:0x01ee, B:55:0x01f4, B:56:0x01fc, B:60:0x0210, B:62:0x0216, B:58:0x0204), top: B:71:0x0048, inners: #3 }] */
    /* JADX WARN: Code duplicated, block: B:59:0x020e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:60:0x0210 A[Catch: Exception -> 0x007b, TRY_ENTER, TryCatch #1 {Exception -> 0x007b, blocks: (B:9:0x0048, B:10:0x004b, B:12:0x0057, B:13:0x005f, B:15:0x0065, B:41:0x014b, B:43:0x0152, B:44:0x0198, B:46:0x01b5, B:48:0x01c6, B:49:0x01cb, B:51:0x01d1, B:52:0x01e8, B:53:0x01ee, B:55:0x01f4, B:56:0x01fc, B:60:0x0210, B:62:0x0216, B:58:0x0204), top: B:71:0x0048, inners: #3 }] */
    /* JADX WARN: Code duplicated, block: B:68:0x023b  */
    /* JADX WARN: Code duplicated, block: B:75:0x0204 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x01e8 A[EDGE_INSN: B:79:0x01e8->B:52:0x01e8 BREAK  A[LOOP:2: B:49:0x01cb->B:51:0x01d1], SYNTHETIC] */
    public TapjoyHttpURLResponse getResponseFromURL(String str, String str2, int i, boolean z, Map map, String str3, String str4) {
        Exception e;
        BufferedReader bufferedReader;
        String headerField;
        StringBuilder sb;
        String line;
        TapjoyHttpURLResponse tapjoyHttpURLResponse = new TapjoyHttpURLResponse();
        HttpURLConnection httpURLConnection = null;
        try {
            String str5 = str + str2;
            TapjoyLog.i("TapjoyURLConnection", "http " + (i == 0 ? "get" : "post") + ": " + str5);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) fj.a(new URL(str5));
            if (z) {
                try {
                    httpURLConnection2.setInstanceFollowRedirects(false);
                    httpURLConnection2.setConnectTimeout(15000);
                    httpURLConnection2.setReadTimeout(30000);
                    if (map != null) {
                        for (Map.Entry entry : map.entrySet()) {
                            httpURLConnection2.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                    if (i == 1) {
                        httpURLConnection2.setRequestMethod("POST");
                        if (str4 != null) {
                            TapjoyLog.i("TapjoyURLConnection", "Content-Type: " + str3);
                            TapjoyLog.i("TapjoyURLConnection", "Content:");
                            TapjoyLog.i("TapjoyURLConnection", str4);
                            httpURLConnection2.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, str3);
                            httpURLConnection2.setRequestProperty("Connection", TJAdUnitConstants.String.CLOSE);
                            httpURLConnection2.setDoOutput(true);
                            httpURLConnection2.setFixedLengthStreamingMode(str4.length());
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection2.getOutputStream());
                            outputStreamWriter.write(str4);
                            outputStreamWriter.close();
                        }
                    }
                    httpURLConnection2.connect();
                    tapjoyHttpURLResponse.statusCode = httpURLConnection2.getResponseCode();
                    tapjoyHttpURLResponse.headerFields = httpURLConnection2.getHeaderFields();
                    tapjoyHttpURLResponse.date = httpURLConnection2.getDate();
                    tapjoyHttpURLResponse.expires = httpURLConnection2.getExpiration();
                    if (z) {
                        bufferedReader = null;
                    } else {
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
                    }
                    if (!z) {
                        sb = new StringBuilder();
                        while (true) {
                            line = bufferedReader.readLine();
                            if (line != null) {
                                break;
                            }
                            sb.append(line + '\n');
                        }
                        tapjoyHttpURLResponse.response = sb.toString();
                    }
                    if (tapjoyHttpURLResponse.statusCode == 302) {
                        tapjoyHttpURLResponse.redirectURL = httpURLConnection2.getHeaderField(HttpRequest.HEADER_LOCATION);
                    }
                    headerField = httpURLConnection2.getHeaderField("content-length");
                    if (headerField != null) {
                        try {
                            tapjoyHttpURLResponse.contentLength = Integer.valueOf(headerField).intValue();
                        } catch (Exception e2) {
                            TapjoyLog.e("TapjoyURLConnection", "Exception: " + e2.toString());
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                    } else if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (Exception e3) {
                    e = e3;
                    httpURLConnection = httpURLConnection2;
                    TapjoyLog.e("TapjoyURLConnection", "Exception: " + e.toString());
                    tapjoyHttpURLResponse.statusCode = 0;
                    if (httpURLConnection != null) {
                        try {
                            if (tapjoyHttpURLResponse.response == null) {
                                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                                StringBuilder sb2 = new StringBuilder();
                                while (true) {
                                    String line2 = bufferedReader2.readLine();
                                    if (line2 == null) {
                                        break;
                                    }
                                    sb2.append(line2 + '\n');
                                }
                                tapjoyHttpURLResponse.response = sb2.toString();
                            }
                        } catch (Exception e4) {
                            TapjoyLog.e("TapjoyURLConnection", "Exception trying to get error code/content: " + e4.toString());
                        }
                    }
                }
            } else {
                httpURLConnection2.setConnectTimeout(15000);
                httpURLConnection2.setReadTimeout(30000);
                if (map != null) {
                    while (r5.hasNext()) {
                        httpURLConnection2.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                if (i == 1) {
                    httpURLConnection2.setRequestMethod("POST");
                    if (str4 != null) {
                        TapjoyLog.i("TapjoyURLConnection", "Content-Type: " + str3);
                        TapjoyLog.i("TapjoyURLConnection", "Content:");
                        TapjoyLog.i("TapjoyURLConnection", str4);
                        httpURLConnection2.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, str3);
                        httpURLConnection2.setRequestProperty("Connection", TJAdUnitConstants.String.CLOSE);
                        httpURLConnection2.setDoOutput(true);
                        httpURLConnection2.setFixedLengthStreamingMode(str4.length());
                        OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(httpURLConnection2.getOutputStream());
                        outputStreamWriter2.write(str4);
                        outputStreamWriter2.close();
                    }
                }
                httpURLConnection2.connect();
                tapjoyHttpURLResponse.statusCode = httpURLConnection2.getResponseCode();
                tapjoyHttpURLResponse.headerFields = httpURLConnection2.getHeaderFields();
                tapjoyHttpURLResponse.date = httpURLConnection2.getDate();
                tapjoyHttpURLResponse.expires = httpURLConnection2.getExpiration();
                if (z) {
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
                } else {
                    bufferedReader = null;
                }
                if (!z) {
                    sb = new StringBuilder();
                    while (true) {
                        line = bufferedReader.readLine();
                        if (line != null) {
                            break;
                            break;
                        }
                        sb.append(line + '\n');
                    }
                    tapjoyHttpURLResponse.response = sb.toString();
                }
                if (tapjoyHttpURLResponse.statusCode == 302) {
                    tapjoyHttpURLResponse.redirectURL = httpURLConnection2.getHeaderField(HttpRequest.HEADER_LOCATION);
                }
                headerField = httpURLConnection2.getHeaderField("content-length");
                if (headerField != null) {
                    tapjoyHttpURLResponse.contentLength = Integer.valueOf(headerField).intValue();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } else if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
        } catch (Exception e5) {
            e = e5;
        }
        TapjoyLog.i("TapjoyURLConnection", "--------------------");
        TapjoyLog.i("TapjoyURLConnection", "response status: " + tapjoyHttpURLResponse.statusCode);
        TapjoyLog.i("TapjoyURLConnection", "response size: " + tapjoyHttpURLResponse.contentLength);
        if (tapjoyHttpURLResponse.redirectURL != null && tapjoyHttpURLResponse.redirectURL.length() > 0) {
            TapjoyLog.i("TapjoyURLConnection", "redirectURL: " + tapjoyHttpURLResponse.redirectURL);
        }
        TapjoyLog.i("TapjoyURLConnection", "--------------------");
        return tapjoyHttpURLResponse;
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map) {
        return getResponseFromURL(str, TapjoyUtil.convertURLParams(map, false), 0);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map, int i) {
        return getResponseFromURL(str, TapjoyUtil.convertURLParams(map, false), i);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map, Map map2, String str2) {
        return getResponseFromURL(str, map != null ? TapjoyUtil.convertURLParams(map, false) : "", 1, false, map2, "application/json;charset=utf-8", str2);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map, Map map2, Map map3) {
        return getResponseFromURL(str, map != null ? TapjoyUtil.convertURLParams(map, false) : "", 1, false, map2, HttpRequest.CONTENT_TYPE_FORM, TapjoyUtil.convertURLParams(map3, false));
    }
}
