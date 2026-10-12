package twitter4j;

import java.util.Map;
import twitter4j.auth.Authorization;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface HttpClient {
    void addDefaultRequestHeader(String str, String str2);

    HttpResponse delete(String str) throws TwitterException;

    HttpResponse delete(String str, HttpParameter[] httpParameterArr, Authorization authorization, HttpResponseListener httpResponseListener) throws TwitterException;

    HttpResponse get(String str) throws TwitterException;

    HttpResponse get(String str, HttpParameter[] httpParameterArr, Authorization authorization, HttpResponseListener httpResponseListener) throws TwitterException;

    Map<String, String> getRequestHeaders();

    HttpResponse head(String str) throws TwitterException;

    HttpResponse post(String str) throws TwitterException;

    HttpResponse post(String str, HttpParameter[] httpParameterArr, Authorization authorization, HttpResponseListener httpResponseListener) throws TwitterException;

    HttpResponse put(String str) throws TwitterException;

    HttpResponse put(String str, HttpParameter[] httpParameterArr, Authorization authorization, HttpResponseListener httpResponseListener) throws TwitterException;

    HttpResponse request(HttpRequest httpRequest) throws TwitterException;

    HttpResponse request(HttpRequest httpRequest, HttpResponseListener httpResponseListener) throws TwitterException;
}
