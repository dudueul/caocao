package com.tapjoy.internal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class fp extends gt implements Observer {
    private boolean d;
    private final Map b = new HashMap();
    private final fi c = new fi();
    private final fz e = new fz() { // from class: com.tapjoy.internal.fp.1
        @Override // com.tapjoy.internal.fz
        protected final /* synthetic */ TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            return TJPlacementManager.createPlacement(context, "AppLaunch", true, tJPlacementListener);
        }

        @Override // com.tapjoy.internal.fz
        protected final /* bridge */ /* synthetic */ String a(Object obj) {
            return "AppLaunch";
        }

        @Override // com.tapjoy.internal.fz
        protected final boolean a() {
            return super.a() && !hk.c();
        }
    };

    static {
        gt.a = new fp();
    }

    private fp() {
    }

    public static void a() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:12:0x001a  */
    /* JADX WARN: Code duplicated, block: B:8:0x000e  */
    @Override // com.tapjoy.internal.gt
    public final void a(Activity activity) {
        Intent intent;
        boolean z;
        ComponentName component;
        if (activity != null) {
            int taskId = activity.getTaskId();
            if (taskId == -1 || (intent = activity.getIntent()) == null) {
                z = false;
            } else {
                Set<String> categories = intent.getCategories();
                if ((categories != null && categories.contains("android.intent.category.LAUNCHER") && "android.intent.action.MAIN".equals(intent.getAction())) && (component = intent.getComponent()) != null) {
                    Integer num = (Integer) this.b.put(component.getClassName(), Integer.valueOf(taskId));
                    z = num == null || num.intValue() != taskId;
                } else {
                    z = false;
                }
            }
            if (z) {
                this.e.c(null);
            } else if (!this.d) {
                this.e.c(null);
            }
        } else if (!this.d && this.c.a()) {
            this.e.c(null);
        }
        this.d = true;
    }

    @Override // java.util.Observer
    public final void update(Observable observable, Object obj) {
        fs.a aVar = fs.d;
    }
}
