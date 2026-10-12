package com.naver.glink.android.sdk.ui.streaming;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.naver.glink.android.sdk.Glink;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.api.streaming.AuthorizeResponse;
import com.naver.glink.android.sdk.api.streaming.c;
import com.naver.glink.android.sdk.login.LoginHelper;
import com.naver.glink.android.sdk.ui.AlertDialogFragmentView;
import com.naver.glink.android.sdk.ui.streaming.streamer.StreamerSettings;
import com.naver.glink.android.sdk.ui.streaming.streamer.d;
import com.naver.glink.android.sdk.ui.streaming.streamer.e;
import com.naver.glink.android.sdk.ui.streaming.streamer.f;
import com.naver.kaleido.KaleidoManager;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;
import com.naver.vapp.broadcast.publisher.RTMPPublisher;

/* JADX INFO: compiled from: StreamingHelper.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class b {
    private static final String a = "streaming-setting-fragment";
    private static final String b = "streamer-widget-fragment";
    private static final String c = "streaming-upload-fragment";
    private static final String d = "streaming-chatting-fragment";
    private static final String e = "streamer-fragment";
    private static final String f = "self-camera-fragment";

    public static void a(FragmentManager fragmentManager) {
        d dVar = (d) fragmentManager.findFragmentByTag(a);
        if (dVar != null) {
            dVar.dismiss();
        }
    }

    public static void a(FragmentManager fragmentManager, String str) {
        if (b() && fragmentManager.findFragmentByTag(b) == null) {
            f.a(str).show(fragmentManager, b);
        }
    }

    public static void a(Context context, StreamerSettings streamerSettings) {
        FragmentManager fragmentManagerB = b(context);
        if (fragmentManagerB != null && fragmentManagerB.findFragmentByTag(c) == null) {
            e.a(streamerSettings).show(fragmentManagerB, c);
        }
    }

    public static void a(Context context, StreamerSettings streamerSettings, boolean z) {
        FragmentManager fragmentManagerB;
        if (b() && (fragmentManagerB = b(context)) != null && streamerSettings != null && fragmentManagerB.findFragmentByTag(a) == null) {
            d.a(streamerSettings, z).show(fragmentManagerB, a);
        }
    }

    public static void a(final Context context, final boolean z) {
        a aVarC = c();
        if (!aVarC.a) {
            Toast.makeText(context, aVarC.b, 1).show();
        } else if (LoginHelper.a().isLogin(context)) {
            c.a().showProgress(true).execute(context, new RequestListener<AuthorizeResponse>() { // from class: com.naver.glink.android.sdk.ui.streaming.b.3
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull AuthorizeResponse authorizeResponse) {
                    if (!authorizeResponse.authority) {
                        Toast.makeText(context, R.string.no_permissions_live, 1).show();
                    } else if (!authorizeResponse.agree) {
                        b.b(context, authorizeResponse.agreementUrl, z);
                    } else {
                        b.a(context, new StreamerSettings(authorizeResponse), z);
                    }
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    Toast.makeText(context, plugError.errorMessage, 1).show();
                    b.a(z);
                }
            });
        } else {
            LoginHelper.a().builder(context, new Glink.OnLoggedInListener() { // from class: com.naver.glink.android.sdk.ui.streaming.b.2
                @Override // com.naver.glink.android.sdk.Glink.OnLoggedInListener
                public void onLoggedIn(boolean z2) {
                    if (z2) {
                        b.a(context, z);
                    } else {
                        b.a(z);
                    }
                }
            }).b(context.getResources().getString(R.string.require_login_message), new AlertDialogFragmentView.c() { // from class: com.naver.glink.android.sdk.ui.streaming.b.1
                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c
                public void a(DialogInterface dialogInterface) {
                    b.a(z);
                }

                @Override // com.naver.glink.android.sdk.ui.AlertDialogFragmentView.c, android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == -2) {
                        b.a(z);
                    }
                }
            }).a();
        }
    }

    public static void a(boolean z) {
        if (z) {
            com.naver.glink.android.sdk.d.f(com.naver.glink.android.sdk.d.r());
        }
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static boolean a(Context context) {
        return d(b(context)) != null;
    }

    public static FragmentManager b(Context context) {
        if (context instanceof Activity) {
            return ((Activity) context).getFragmentManager();
        }
        return null;
    }

    public static void b(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag(f) == null) {
            com.naver.glink.android.sdk.ui.streaming.b.c.c().show(fragmentManager, f);
        }
    }

    public static void b(FragmentManager fragmentManager, String str) {
        if (fragmentManager.findFragmentByTag(d) == null) {
            com.naver.glink.android.sdk.ui.streaming.streamer.b.a(str).show(fragmentManager, d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, String str, boolean z) {
        FragmentManager fragmentManagerB = b(context);
        if (fragmentManagerB == null || fragmentManagerB.findFragmentByTag("streamer-agree-fragment") != null) {
            return;
        }
        fragmentManagerB.beginTransaction().add(com.naver.glink.android.sdk.ui.streaming.streamer.a.a(str, z), "streamer-agree-fragment").commit();
    }

    public static boolean b() {
        try {
            RTMPPublisher.class.getClass();
            KaleidoManager.class.getClass();
            return true;
        } catch (Exception e2) {
            return false;
        } catch (NoClassDefFoundError e3) {
            return false;
        }
    }

    public static a c() {
        if (!a() || !b()) {
            return a.a(null);
        }
        try {
            System.loadLibrary("rtmp-publisher-lib");
            return a.a();
        } catch (UnsatisfiedLinkError e2) {
            return a.a(com.naver.glink.android.sdk.d.a(R.string.not_supported_rtmp_abi));
        }
    }

    public static void c(FragmentManager fragmentManager) {
        com.naver.glink.android.sdk.ui.streaming.b.c cVar = (com.naver.glink.android.sdk.ui.streaming.b.c) fragmentManager.findFragmentByTag(f);
        if (cVar != null) {
            cVar.dismiss();
        }
    }

    public static com.naver.glink.android.sdk.ui.streaming.streamer.c d(FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            return null;
        }
        return (com.naver.glink.android.sdk.ui.streaming.streamer.c) fragmentManager.findFragmentByTag(e);
    }

    public static StreamerSettings e(FragmentManager fragmentManager) {
        com.naver.glink.android.sdk.ui.streaming.streamer.c cVarD = d(fragmentManager);
        if (cVarD != null) {
            return cVarD.c();
        }
        return null;
    }

    public static com.naver.glink.android.sdk.ui.streaming.streamer.c f(FragmentManager fragmentManager) {
        com.naver.glink.android.sdk.ui.streaming.streamer.c cVarD = d(fragmentManager);
        if (cVarD != null) {
            return cVarD;
        }
        com.naver.glink.android.sdk.ui.streaming.streamer.c cVarA = com.naver.glink.android.sdk.ui.streaming.streamer.c.a();
        fragmentManager.beginTransaction().add(cVarA, e).commit();
        return cVarA;
    }

    public static void g(FragmentManager fragmentManager) {
        com.naver.glink.android.sdk.ui.streaming.streamer.c cVarD = d(fragmentManager);
        if (cVarD != null) {
            fragmentManager.beginTransaction().remove(cVarD).commit();
        }
        c(fragmentManager);
        i(fragmentManager);
        h(fragmentManager);
        a(fragmentManager);
    }

    private static void h(FragmentManager fragmentManager) {
        f fVar = (f) fragmentManager.findFragmentByTag(b);
        if (fVar != null) {
            fVar.dismiss();
        }
    }

    private static void i(FragmentManager fragmentManager) {
        com.naver.glink.android.sdk.ui.streaming.streamer.b bVar = (com.naver.glink.android.sdk.ui.streaming.streamer.b) fragmentManager.findFragmentByTag(d);
        if (bVar != null) {
            bVar.dismiss();
        }
    }
}
