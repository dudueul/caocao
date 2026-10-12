package bolts;

import android.net.Uri;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface AppLinkResolver {
    Task<AppLink> getAppLinkFromUrlInBackground(Uri uri);
}
