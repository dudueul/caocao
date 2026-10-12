package com.nexon.nexonanalyticssdk;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
enum NxNetwork {
    INSTANCE;

    public static final int TIME_OUT = 30000;
    private final String apiKey = "X-API-KEY";
    private final String apiValue = "65925AD99D428123739DA1CD058FF9C03DE74F09C9EA81E9418332178E1F8426";
    private ExecutorService executorService;

    NxNetwork() {
    }

    public static NxNetwork getInstance() {
        return INSTANCE;
    }

    public String getInfo(String str) {
        String body = null;
        try {
            NxResponse nxResponse = (NxResponse) this.executorService.submit(new NxRequest.Builder().connectionTimeout(30000).readTimeout(30000).url(str).extraHeader("X-API-KEY", "65925AD99D428123739DA1CD058FF9C03DE74F09C9EA81E9418332178E1F8426").build()).get();
            int statusCode = nxResponse.getStatusCode();
            if (statusCode == 200) {
                body = nxResponse.getBody();
                NxLogcat.d("Info data received Successfully! >> " + body);
            } else {
                NxLogcat.e("Failed to receive Info, StatusCode : " + statusCode + "Error : " + nxResponse.getBody());
            }
        } catch (Exception e) {
            NxLogcat.e("Failure! " + e.getMessage() + ", timeOut : 30000");
        }
        return body;
    }

    public void initialize() {
        this.executorService = Executors.newFixedThreadPool(1);
    }

    public boolean postNxLog(String str, String str2) {
        NxRequest nxRequestBuild = null;
        try {
            try {
                nxRequestBuild = new NxRequest.Builder().url(str2).connectionTimeout(30000).post(str).build();
            } catch (MalformedURLException e) {
                NxLogcat.e("MalformedURL Exception : " + e.getMessage());
            }
            NxResponse nxResponse = (NxResponse) this.executorService.submit(nxRequestBuild).get();
            int statusCode = nxResponse.getStatusCode();
            if (statusCode == 200) {
                NxLogcat.d("NxLogCreator was sent Successfully!");
                return true;
            }
            NxLogcat.d("Failed to send NxLogCreator, StatusCode : " + statusCode + "Error : " + nxResponse.getBody());
            return false;
        } catch (NullPointerException e2) {
            NxLogcat.e("NullPointException : " + e2.getMessage());
            return false;
        } catch (Exception e3) {
            NxLogcat.e("Exception : " + e3.getMessage());
            return false;
        }
    }
}
