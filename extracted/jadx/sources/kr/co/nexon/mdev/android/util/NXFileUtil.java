package kr.co.nexon.mdev.android.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import com.naver.glink.android.sdk.ui.record.c;
import java.io.ByteArrayOutputStream;
import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXFileUtil {
    public static String fileToString(File file) {
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(file.getPath());
        int width = bitmapDecodeFile.getWidth();
        int height = bitmapDecodeFile.getHeight();
        int i = width;
        int i2 = height;
        if (width > height) {
            if (720 < width) {
                i2 = (int) (height * (c.c / width));
                i = 720;
            }
        } else if (720 < height) {
            i = (int) (width * (c.c / height));
            i2 = 720;
        }
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeFile, i, i2, true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            bitmapCreateScaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @TargetApi(19)
    public static File uriToFile(Context context, Uri uri) {
        String path = "";
        if (uri.getPath().contains(":")) {
            String[] strArr = {"_data"};
            Cursor cursorQuery = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, "_id=?", new String[]{(Environment.getExternalStorageDirectory() + File.separator + "upload").split(":")[1]}, null);
            path = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndex(strArr[0])) : "";
            cursorQuery.close();
        } else {
            String[] strArr2 = {"_data"};
            if (uri.getScheme().equalsIgnoreCase("content")) {
                Cursor cursorQuery2 = context.getContentResolver().query(uri, strArr2, null, null, null);
                if (cursorQuery2.moveToFirst()) {
                    path = cursorQuery2.getString(cursorQuery2.getColumnIndex("_data"));
                }
            } else {
                path = uri.getPath();
            }
        }
        return new File(path);
    }
}
