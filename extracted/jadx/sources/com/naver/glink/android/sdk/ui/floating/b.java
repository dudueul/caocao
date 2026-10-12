package com.naver.glink.android.sdk.ui.floating;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.l;
import com.naver.glink.android.sdk.a.p;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.PermissionDialogFragmentView;
import com.naver.glink.android.sdk.ui.record.c;
import com.naver.glink.android.sdk.ui.record.e;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;

/* JADX INFO: compiled from: WidgetControlHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private static final String b = "W_T";
    private static final String c = "R_T";
    private static final String d = "R_S_T";
    private static final l a = l.a(b.class.getSimpleName());
    private static b e = new b();

    /* JADX INFO: compiled from: WidgetControlHelper.java */
    public interface a {
        void a();

        void a(MotionEvent motionEvent);

        void a(MotionEvent motionEvent, int i, int i2);

        void b(MotionEvent motionEvent);
    }

    private b() {
    }

    public static b a() {
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity) {
        com.naver.glink.android.sdk.ui.streaming.b.a((Context) activity, true);
        WidgetFloatingLayout.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Activity activity, final com.naver.glink.android.sdk.ui.floating.a aVar, final FragmentManager fragmentManager) {
        aVar.a(new com.naver.glink.android.sdk.ui.floating.a.b() { // from class: com.naver.glink.android.sdk.ui.floating.b.2
            @Override // com.naver.glink.android.sdk.ui.floating.a.b
            public void a() {
                WidgetFloatingLayout.f();
            }
        });
        aVar.a(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.floating.b.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                aVar.dismiss();
                int id = view.getId();
                if (id == R.id.float_item_cafe) {
                    Glink.startHome(activity);
                    WidgetFloatingLayout.c();
                    return;
                }
                if (id == R.id.float_item_capture) {
                    com.naver.glink.android.sdk.b.d();
                    WidgetFloatingLayout.c();
                } else {
                    if (id == R.id.float_item_video) {
                        b.this.c(activity, fragmentManager);
                        return;
                    }
                    if (id == R.id.float_item_live) {
                        b.this.a(activity);
                        WidgetFloatingLayout.c();
                    } else if (id == R.id.float_item_trash) {
                        d.g(activity);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Activity activity, final e eVar) {
        eVar.a(new a() { // from class: com.naver.glink.android.sdk.ui.floating.b.5
            @Override // com.naver.glink.android.sdk.ui.floating.b.a
            public void a() {
                if (WidgetTrashLayout.b()) {
                    return;
                }
                WidgetTrashLayout.a(activity);
            }

            @Override // com.naver.glink.android.sdk.ui.floating.b.a
            public void a(MotionEvent motionEvent) {
                if (WidgetTrashLayout.b()) {
                    if (WidgetTrashLayout.a((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                        eVar.dismissAllowingStateLoss();
                        d.f(d.r());
                    }
                    WidgetTrashLayout.a();
                }
            }

            @Override // com.naver.glink.android.sdk.ui.floating.b.a
            public void a(MotionEvent motionEvent, int i, int i2) {
            }

            @Override // com.naver.glink.android.sdk.ui.floating.b.a
            public void b(MotionEvent motionEvent) {
                if (WidgetTrashLayout.b()) {
                    if (WidgetTrashLayout.a((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                        WidgetTrashLayout.setBackgroundVisible(0);
                    } else {
                        WidgetTrashLayout.setBackgroundVisible(4);
                    }
                }
            }
        });
    }

    static void a(View view) {
        if (view != null) {
            view.setBackgroundResource(R.drawable.pl_btn_wdplug_normal);
        }
    }

    private void b(final Activity activity, final FragmentManager fragmentManager) {
        a.a("setWidgetFloatingListener", new Object[0]);
        WidgetFloatingLayout.setListener(new a() { // from class: com.naver.glink.android.sdk.ui.floating.b.1
            @Override // com.naver.glink.android.sdk.ui.floating.b.a
            public void a() {
                if (WidgetTrashLayout.b()) {
                    return;
                }
                WidgetTrashLayout.a(activity);
            }

            @Override // com.naver.glink.android.sdk.ui.floating.b.a
            public void a(MotionEvent motionEvent) {
                if (WidgetTrashLayout.b()) {
                    if (WidgetTrashLayout.a((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                        WidgetFloatingLayout.c();
                        Toast.makeText(activity, activity.getResources().getText(R.string.widget_trash), 0).show();
                    }
                    WidgetTrashLayout.a();
                }
            }

            @Override // com.naver.glink.android.sdk.ui.floating.b.a
            public void a(MotionEvent motionEvent, int i, int i2) {
                com.naver.glink.android.sdk.ui.floating.a aVarA = com.naver.glink.android.sdk.ui.floating.a.a(i, i2);
                if (aVarA == null) {
                    return;
                }
                b.this.a(activity, aVarA, fragmentManager);
                if (WidgetFloatingLayout.g()) {
                    WidgetFloatingLayout.e();
                }
                if (activity != null) {
                    if (17 >= Build.VERSION.SDK_INT || !activity.isDestroyed()) {
                        fragmentManager.beginTransaction().add(aVarA, b.b).commit();
                    }
                }
            }

            @Override // com.naver.glink.android.sdk.ui.floating.b.a
            public void b(MotionEvent motionEvent) {
                if (WidgetTrashLayout.b()) {
                    if (WidgetTrashLayout.a((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                        WidgetTrashLayout.setBackgroundVisible(0);
                    } else {
                        WidgetTrashLayout.setBackgroundVisible(4);
                    }
                }
            }
        });
    }

    private boolean b(final Activity activity) {
        if (activity == null) {
            return false;
        }
        List<String> listAsList = Arrays.asList(NXRuntimePermissionManager.WRITE_EXTERNAL_STORAGE, NXRuntimePermissionManager.RECORD_AUDIO);
        final ArrayList arrayList = new ArrayList();
        for (String str : listAsList) {
            if (ContextCompat.checkSelfPermission(activity, str) != 0) {
                arrayList.add(str);
            }
        }
        String string = activity.getString(R.string.permission_storage_record);
        String string2 = activity.getString(R.string.permission_record);
        if (arrayList.size() == 0) {
            return true;
        }
        if (arrayList.size() == listAsList.size()) {
            PermissionDialogFragmentView.a(activity, string).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.floating.b.6
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c, android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), 8080);
                }
            }).a();
        } else if (!com.naver.glink.android.sdk.ui.write.b.a(activity, NXRuntimePermissionManager.WRITE_EXTERNAL_STORAGE)) {
            com.naver.glink.android.sdk.ui.write.b.a(activity, NXRuntimePermissionManager.WRITE_EXTERNAL_STORAGE, string, 9222);
        } else if (!com.naver.glink.android.sdk.ui.write.b.a(activity, NXRuntimePermissionManager.RECORD_AUDIO)) {
            if (com.naver.glink.android.sdk.ui.write.b.a(activity, NXRuntimePermissionManager.RECORD_AUDIO)) {
                Toast.makeText(activity, string2, 0).show();
            } else {
                PermissionDialogFragmentView.a(activity, string).a(new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.floating.b.7
                    @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c, android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), 8080);
                    }
                }).a();
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final Activity activity, final FragmentManager fragmentManager) {
        if (d.q() && activity != null && fragmentManager.findFragmentByTag(d) == null && fragmentManager.findFragmentByTag(c) == null) {
            com.naver.glink.android.sdk.ui.record.d dVarA = com.naver.glink.android.sdk.ui.record.d.a();
            dVarA.a(new com.naver.glink.android.sdk.ui.record.d.a() { // from class: com.naver.glink.android.sdk.ui.floating.b.4
                @Override // com.naver.glink.android.sdk.ui.record.d.a
                public void a(c cVar) {
                    WidgetFloatingLayout.c();
                    e eVarA = e.a(cVar);
                    b.this.a(activity, eVarA);
                    fragmentManager.beginTransaction().add(eVarA, b.c).commit();
                }
            });
            fragmentManager.beginTransaction().add(dVarA, d).commit();
            b(activity);
            WidgetFloatingLayout.c();
        }
    }

    public void a(Activity activity, FragmentManager fragmentManager) {
        a.a("showFloatingButton", new Object[0]);
        if (p.h(activity) && d.p().c != 0 && WidgetFloatingLayout.c(activity)) {
            b(activity, fragmentManager);
        }
    }

    public void b() {
        WidgetFloatingLayout.c();
    }

    public boolean c() {
        return WidgetFloatingLayout.g();
    }
}
