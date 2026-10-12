package com.naver.glink.android.sdk.ui.write;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult.RequestActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;

/* JADX INFO: compiled from: PermissionHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    public static List<String> a(Context context, List<String> list) {
        return b(context, (String[]) list.toArray(new String[list.size()]));
    }

    @TargetApi(23)
    public static void a(Fragment fragment, int i, String... strArr) {
        fragment.requestPermissions(strArr, i);
    }

    public static void a(Context context) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    @TargetApi(23)
    public static void a(Context context, int i, String... strArr) {
        if (!d.h(context)) {
            Fragment fragmentFindFragmentByTag = ((Activity) context).getFragmentManager().findFragmentByTag(d.a);
            if (fragmentFindFragmentByTag != null) {
                fragmentFindFragmentByTag.requestPermissions(strArr, i);
                return;
            }
            return;
        }
        Intent intent = new Intent(context, (Class<?>) RequestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArray(RequestActivity.d, strArr);
        bundle.putInt(RequestActivity.e, i);
        intent.putExtra(RequestActivity.c, bundle);
        intent.setAction(RequestActivity.c);
        context.startActivity(intent);
    }

    public static void a(final Context context, String str, String str2, final int i) {
        if (a(context, str)) {
            AlertDialogFragmentView.a(context, str2).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.write.b.1
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void a(DialogInterface dialogInterface, int i2) {
                    b.a(context);
                }
            }).a();
        } else {
            AlertDialogFragmentView.b(context, str2).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.write.b.2
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void a(DialogInterface dialogInterface, int i2) {
                    b.a(context, i, NXRuntimePermissionManager.READ_EXTERNAL_STORAGE);
                }

                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void b(DialogInterface dialogInterface, int i2) {
                    super.b(dialogInterface, i2);
                }
            }).a();
        }
    }

    public static boolean a(Fragment fragment, String... strArr) {
        return b(fragment, strArr).size() == 0;
    }

    @TargetApi(23)
    public static boolean a(Context context, String str) {
        if (d.h(context)) {
            return false;
        }
        return ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, str);
    }

    public static boolean a(Context context, String... strArr) {
        return b(context, strArr).size() == 0;
    }

    public static boolean a(int[] iArr) {
        if (iArr == null) {
            return true;
        }
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    public static List<String> b(Fragment fragment, String... strArr) {
        if (Build.VERSION.SDK_INT < 23 || strArr == null || strArr.length == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (fragment.getActivity().checkSelfPermission(str) != 0) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    @TargetApi(23)
    public static List<String> b(Context context, String... strArr) {
        if (Build.VERSION.SDK_INT < 23 || strArr == null || strArr.length == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (context.checkSelfPermission(str) != 0) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
