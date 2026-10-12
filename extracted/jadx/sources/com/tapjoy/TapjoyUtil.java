package com.tapjoy;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.adjust.sdk.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class TapjoyUtil {
    private static HashMap a = new HashMap();
    private static final AtomicInteger b = new AtomicInteger(1);

    public static String SHA1(String str) {
        return a("SHA-1", str);
    }

    public static String SHA256(String str) {
        return a(Constants.SHA256, str);
    }

    private static String a(String str, String str2) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(str2.getBytes("iso-8859-1"), 0, str2.length());
        return convertToHex(messageDigest.digest());
    }

    public static Document buildDocument(String str) {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(str.getBytes("UTF-8")));
        } catch (Exception e) {
            TapjoyLog.e("TapjoyUtil", "buildDocument exception: " + e.toString());
            return null;
        }
    }

    public static String convertToHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            int i2 = 0;
            int i3 = (bArr[i] >>> 4) & 15;
            while (true) {
                if (i3 < 0 || i3 > 9) {
                    stringBuffer.append((char) ((i3 - 10) + 97));
                } else {
                    stringBuffer.append((char) (i3 + 48));
                }
                i3 = bArr[i] & 15;
                int i4 = i2 + 1;
                if (i2 > 0) {
                    break;
                }
                i2 = i4;
            }
        }
        return stringBuffer.toString();
    }

    public static String convertURLParams(Map map, boolean z) {
        String str = "";
        Iterator it = map.entrySet().iterator();
        while (true) {
            String str2 = str;
            if (!it.hasNext()) {
                return str2;
            }
            Map.Entry entry = (Map.Entry) it.next();
            if (str2.length() > 0) {
                str2 = str2 + "&";
            }
            str = z ? str2 + Uri.encode((String) entry.getKey()) + "=" + Uri.encode((String) entry.getValue()) : str2 + ((String) entry.getKey()) + "=" + ((String) entry.getValue());
        }
    }

    public static Map convertURLParams(String str, boolean z) {
        HashMap map = new HashMap();
        String strDecode = "";
        String strDecode2 = "";
        boolean z2 = false;
        for (int i = 0; i < str.length() && i != -1; i++) {
            char cCharAt = str.charAt(i);
            if (z2) {
                if (z2) {
                    if (cCharAt == '&') {
                        String strDecode3 = z ? Uri.decode(strDecode) : strDecode;
                        strDecode = "";
                        map.put(strDecode2, strDecode3);
                        z2 = false;
                    } else {
                        strDecode = strDecode + cCharAt;
                    }
                }
            } else if (cCharAt == '=') {
                strDecode2 = z ? Uri.decode(strDecode) : strDecode;
                strDecode = "";
                z2 = true;
            } else {
                strDecode = strDecode + cCharAt;
            }
        }
        if (z2 && strDecode.length() > 0) {
            if (z) {
                strDecode = Uri.decode(strDecode);
            }
            map.put(strDecode2, strDecode);
        }
        return map;
    }

    public static String copyTextFromJarIntoString(String str) {
        return copyTextFromJarIntoString(str, null);
    }

    /* JADX WARN: Code duplicated, block: B:48:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    public static String copyTextFromJarIntoString(String str, Context context) throws Throwable {
        InputStream inputStream;
        String string = 0;
        string = 0;
        string = 0;
        byte[] bArr = new byte[1024];
        StringBuffer stringBuffer = new StringBuffer();
        URL resource = TapjoyUtil.class.getClassLoader().getResource(str);
        try {
            try {
                if (context == null || resource != null) {
                    String file = resource.getFile();
                    if (file.startsWith("jar:")) {
                        file = file.substring(4);
                    }
                    if (file.startsWith("file:")) {
                        file = file.substring(5);
                    }
                    int iIndexOf = file.indexOf("!");
                    if (iIndexOf > 0) {
                        file = file.substring(0, iIndexOf);
                    }
                    JarFile jarFile = new JarFile(file);
                    inputStream = jarFile.getInputStream(jarFile.getJarEntry(str));
                } else {
                    inputStream = context.getAssets().open(str);
                }
                while (true) {
                    try {
                        int i = inputStream.read(bArr);
                        if (i <= 0) {
                            break;
                        }
                        stringBuffer.append(new String(bArr).substring(0, i));
                    } catch (Exception e) {
                        e = e;
                        TapjoyLog.d("TapjoyUtil", "file exception: " + e.toString());
                        e.printStackTrace();
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e2) {
                            }
                        }
                    }
                }
                string = stringBuffer.toString();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    try {
                        string.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                string.close();
            }
            throw th;
        }
        return string;
    }

    public static Bitmap createBitmapFromView(View view) {
        Bitmap bitmapCreateBitmap = null;
        if (view == null || view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
            return null;
        }
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(view.getLayoutParams().width, view.getLayoutParams().height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            view.draw(canvas);
            return bitmapCreateBitmap;
        } catch (Exception e) {
            TapjoyLog.d("TapjoyUtil", "error creating bitmap: " + e.toString());
            return bitmapCreateBitmap;
        }
    }

    public static void deleteFileOrDirectory(File file) {
        File[] fileArrListFiles;
        if (file == null) {
            return;
        }
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                deleteFileOrDirectory(file2);
            }
        }
        TapjoyLog.d("TapjoyUtil", "****************************************");
        TapjoyLog.d("TapjoyUtil", "deleteFileOrDirectory: " + file.getAbsolutePath());
        TapjoyLog.d("TapjoyUtil", "****************************************");
        file.delete();
    }

    public static String determineMimeType(String str) {
        if (str.endsWith(".")) {
            str = str.substring(0, str.length() - 1);
        }
        String strSubstring = str.lastIndexOf(46) != -1 ? str.substring(str.lastIndexOf(46) + 1) : "";
        if (strSubstring.equals("css")) {
            return "text/css";
        }
        if (strSubstring.equals("js")) {
            return "text/javascript";
        }
        return strSubstring.equals(TJAdUnitConstants.String.HTML) ? "text/html" : "application/octet-stream";
    }

    public static long fileOrDirectorySize(File file) {
        long length = 0;
        File[] fileArrListFiles = file.listFiles();
        int length2 = fileArrListFiles.length;
        int i = 0;
        while (i < length2) {
            File file2 = fileArrListFiles[i];
            i++;
            length = (file2.isFile() ? file2.length() : fileOrDirectorySize(file2)) + length;
        }
        return length;
    }

    public static int generateViewId() {
        int i;
        int i2;
        do {
            i = b.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!b.compareAndSet(i, i2));
        return i;
    }

    public static String getFileContents(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        while (!z) {
            String line = bufferedReader.readLine();
            z = line == null;
            if (line != null) {
                sb.append(line);
            }
        }
        bufferedReader.close();
        fileInputStream.close();
        return sb.toString();
    }

    public static String getNodeTrimValue(NodeList nodeList) {
        Element element = (Element) nodeList.item(0);
        if (element == null) {
            return null;
        }
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        String str = "";
        for (int i = 0; i < length; i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem != null) {
                str = str + nodeItem.getNodeValue();
            }
        }
        if (str == null || str.equals("")) {
            return null;
        }
        return str.trim();
    }

    public static String getRedirectDomain(String str) {
        return str != null ? str.substring(str.indexOf("//") + 2, str.lastIndexOf("/")) : "";
    }

    public static Object getResource(String str) {
        return a.get(str);
    }

    public static Map jsonToStringMap(JSONObject jSONObject) {
        return jSONObject != JSONObject.NULL ? toStringMap(jSONObject) : new HashMap();
    }

    /* JADX WARN: Code duplicated, block: B:54:0x00b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r2v18, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v7 */
    public static Bitmap loadBitmapFromJar(String str, Context context) throws Throwable {
        Bitmap bitmapDecodeStream;
        ?? r1 = 0;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        byte[] bArr = (byte[]) getResource(str);
        if (bArr != null) {
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            bitmapDecodeStream = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        } else {
            bitmapDecodeStream = null;
        }
        if (bitmapDecodeStream == null) {
            ?? inputStream = "com/tapjoy/res/";
            String str2 = "com/tapjoy/res/" + str;
            try {
                try {
                    URL resource = TapjoyUtil.class.getClassLoader().getResource(str2);
                    try {
                        if (resource == null) {
                            AssetManager assets = context.getAssets();
                            BitmapFactory.decodeStream(assets.open(str2), null, options);
                            inputStream = assets.open(str2);
                        } else {
                            String file = resource.getFile();
                            if (file.startsWith("jar:")) {
                                file = file.substring(4);
                            }
                            if (file.startsWith("file:")) {
                                file = file.substring(5);
                            }
                            int iIndexOf = file.indexOf("!");
                            if (iIndexOf > 0) {
                                file = file.substring(0, iIndexOf);
                            }
                            JarFile jarFile = new JarFile(file);
                            JarEntry jarEntry = jarFile.getJarEntry(str2);
                            BitmapFactory.decodeStream(jarFile.getInputStream(jarEntry), null, options);
                            inputStream = jarFile.getInputStream(jarEntry);
                        }
                        bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
                        if (inputStream != 0) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        if (inputStream != 0) {
                            try {
                                inputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    r1 = "com/tapjoy/res/";
                    if (r1 != 0) {
                        try {
                            r1.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                inputStream = 0;
            } catch (Throwable th2) {
                th = th2;
                if (r1 != 0) {
                    r1.close();
                }
                throw th;
            }
        }
        float deviceScreenDensityScale = TapjoyConnectCore.getDeviceScreenDensityScale();
        return bitmapDecodeStream != null ? Bitmap.createScaledBitmap(bitmapDecodeStream, (int) (options.outWidth * deviceScreenDensityScale), (int) (deviceScreenDensityScale * options.outHeight), true) : bitmapDecodeStream;
    }

    public static void runOnMainThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public static void safePut(Map map, String str, Number number) {
        if (str == null || str.length() <= 0 || number == null) {
            return;
        }
        map.put(str, number.toString());
    }

    public static void safePut(Map map, String str, String str2, boolean z) {
        if (str == null || str.length() <= 0 || str2 == null || str2.length() <= 0) {
            return;
        }
        if (z) {
            map.put(Uri.encode(str), Uri.encode(str2));
        } else {
            map.put(str, str2);
        }
    }

    public static View scaleDisplayAd(View view, int i) {
        int i2 = view.getLayoutParams().width;
        int i3 = view.getLayoutParams().height;
        TapjoyLog.d("TapjoyUtil", "wxh: " + i2 + "x" + i3);
        if (i2 > i) {
            int iIntValue = Double.valueOf(Double.valueOf(Double.valueOf(i).doubleValue() / Double.valueOf(i2).doubleValue()).doubleValue() * 100.0d).intValue();
            ((WebView) view).getSettings().setSupportZoom(true);
            ((WebView) view).setPadding(0, 0, 0, 0);
            ((WebView) view).setVerticalScrollBarEnabled(false);
            ((WebView) view).setHorizontalScrollBarEnabled(false);
            ((WebView) view).setInitialScale(iIntValue);
            view.setLayoutParams(new ViewGroup.LayoutParams(i, (i3 * i) / i2));
        }
        return view;
    }

    public static void setResource(String str, Object obj) {
        a.put(str, obj);
    }

    public static Map toStringMap(JSONObject jSONObject) {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, jSONObject.get(next).toString());
        }
        return map;
    }

    public static void writeFileToDevice(BufferedInputStream bufferedInputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = bufferedInputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }
}
