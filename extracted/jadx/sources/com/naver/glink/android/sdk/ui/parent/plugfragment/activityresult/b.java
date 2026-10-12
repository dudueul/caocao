package com.naver.glink.android.sdk.ui.parent.plugfragment.activityresult;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.naver.glink.android.sdk.ui.record.RecordManager;

/* JADX INFO: compiled from: PlugMediaProjectionFragment.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b extends Fragment {
    private static String a = "PlugRecordHelperFragment";
    private a b;

    /* JADX INFO: compiled from: PlugMediaProjectionFragment.java */
    public interface a {
        void a();
    }

    public static Fragment a(Activity activity) {
        b bVarA = null;
        try {
            try {
                bVarA = a();
                if (activity.getFragmentManager().findFragmentByTag(a) == null) {
                    activity.getFragmentManager().beginTransaction().add(bVarA, a).commitAllowingStateLoss();
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Throwable th) {
        }
        return bVarA;
    }

    public static b a() {
        return new b();
    }

    private static void b(Activity activity) {
        Fragment fragmentFindFragmentByTag = activity.getFragmentManager().findFragmentByTag(a);
        if (fragmentFindFragmentByTag != null) {
            activity.getFragmentManager().beginTransaction().remove(fragmentFindFragmentByTag).commit();
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        RecordManager.a(i, i2, intent);
        super.onActivityResult(i, i2, intent);
        b(getActivity());
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = new View(getActivity());
        view.setVisibility(8);
        return view;
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.b != null) {
            this.b.a();
        }
    }
}
