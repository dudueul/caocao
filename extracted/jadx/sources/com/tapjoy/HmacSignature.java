package com.tapjoy;

import android.net.Uri;
import com.adjust.sdk.Constants;
import java.util.Map;
import java.util.TreeSet;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class HmacSignature {
    private String a;
    private String b;

    public HmacSignature(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    private static String a(Map map) {
        TreeSet<String> treeSet = new TreeSet(map.keySet());
        StringBuilder sb = new StringBuilder();
        for (String str : treeSet) {
            sb.append(str + "=" + ((String) map.get(str)) + "&");
        }
        sb.deleteCharAt(sb.lastIndexOf("&"));
        TapjoyLog.v("HmacSignature", "Unhashed String: " + sb.toString());
        return sb.toString();
    }

    public boolean matches(String str, Map map, String str2) {
        return sign(str, map).equals(str2);
    }

    public String sign(String str, Map map) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(this.b.getBytes(), this.a);
            Mac mac = Mac.getInstance(this.a);
            mac.init(secretKeySpec);
            Uri uri = Uri.parse(str);
            String str2 = uri.getScheme() + "://" + uri.getHost();
            if (!((uri.getScheme().equals("http") && uri.getPort() == 80) || (uri.getScheme().equals(Constants.SCHEME) && uri.getPort() == 443)) && -1 != uri.getPort()) {
                str2 = str2 + ":" + uri.getPort();
            }
            String str3 = "POST&" + Uri.encode(str2.toLowerCase() + uri.getPath()) + "&" + Uri.encode(a(map));
            TapjoyLog.v("HmacSignature", "Base Url: " + str3);
            byte[] bArrDoFinal = mac.doFinal(str3.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDoFinal) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
