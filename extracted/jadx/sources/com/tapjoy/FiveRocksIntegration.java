package com.tapjoy;

import com.tapjoy.internal.az;
import com.tapjoy.internal.gl;
import com.tapjoy.internal.gm;
import com.tapjoy.internal.go;
import com.tapjoy.internal.gz;
import com.tapjoy.internal.ha;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FiveRocksIntegration {
    private static az a = new az();

    public static void a() {
        gz gzVarA = gz.a();
        if (!gzVarA.c) {
            gzVarA.c = true;
        }
        go goVar = new go() { // from class: com.tapjoy.FiveRocksIntegration.1
            private gm e(final String str) {
                return new gm() { // from class: com.tapjoy.FiveRocksIntegration.1.1
                    @Override // com.tapjoy.internal.gm
                    public final void a(final String str2, String str3) {
                        TJPlacement tJPlacement;
                        synchronized (FiveRocksIntegration.a) {
                            tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                        }
                        if (tJPlacement == null || tJPlacement.a == null) {
                            return;
                        }
                        tJPlacement.a.onPurchaseRequest(tJPlacement, new TJActionRequest() { // from class: com.tapjoy.FiveRocksIntegration.1.1.1
                            @Override // com.tapjoy.TJActionRequest
                            public final void cancelled() {
                            }

                            @Override // com.tapjoy.TJActionRequest
                            public final void completed() {
                            }

                            @Override // com.tapjoy.TJActionRequest
                            public final String getRequestId() {
                                return str2;
                            }

                            @Override // com.tapjoy.TJActionRequest
                            public final String getToken() {
                                return null;
                            }
                        }, str3);
                    }

                    @Override // com.tapjoy.internal.gm
                    public final void a(final String str2, String str3, int i, final String str4) {
                        TJPlacement tJPlacement;
                        synchronized (FiveRocksIntegration.a) {
                            tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                        }
                        if (tJPlacement == null || tJPlacement.a == null) {
                            return;
                        }
                        tJPlacement.a.onRewardRequest(tJPlacement, new TJActionRequest() { // from class: com.tapjoy.FiveRocksIntegration.1.1.2
                            @Override // com.tapjoy.TJActionRequest
                            public final void cancelled() {
                            }

                            @Override // com.tapjoy.TJActionRequest
                            public final void completed() {
                            }

                            @Override // com.tapjoy.TJActionRequest
                            public final String getRequestId() {
                                return str2;
                            }

                            @Override // com.tapjoy.TJActionRequest
                            public final String getToken() {
                                return str4;
                            }
                        }, str3, i);
                    }
                };
            }

            @Override // com.tapjoy.internal.go
            public final void a(String str) {
            }

            @Override // com.tapjoy.internal.go
            public final void a(String str, gl glVar) {
                if (glVar != null) {
                    glVar.a(e(str));
                }
            }

            @Override // com.tapjoy.internal.go
            public final void a(String str, String str2, gl glVar) {
                TJPlacement tJPlacement;
                if (glVar != null) {
                    glVar.a(e(str));
                }
                synchronized (FiveRocksIntegration.a) {
                    tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement != null) {
                    TapjoyConnectCore.viewDidClose(str2);
                    if (tJPlacement.a != null) {
                        tJPlacement.a.onContentDismiss(tJPlacement);
                    }
                }
            }

            @Override // com.tapjoy.internal.go
            public final void b(String str) {
                TJPlacement tJPlacement;
                synchronized (FiveRocksIntegration.a) {
                    tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement == null || tJPlacement.a == null) {
                    return;
                }
                tJPlacement.a.onContentReady(tJPlacement);
            }

            @Override // com.tapjoy.internal.go
            public final void c(String str) {
                TJPlacement tJPlacement;
                synchronized (FiveRocksIntegration.a) {
                    tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement == null || tJPlacement.a == null) {
                    return;
                }
                tJPlacement.a.onContentShow(tJPlacement);
            }

            @Override // com.tapjoy.internal.go
            public final void d(String str) {
            }
        };
        gz.a().p = ha.a(goVar);
    }

    public static void addPlacementCallback(String str, TJPlacement tJPlacement) {
        synchronized (a) {
            a.put(str, tJPlacement);
        }
    }
}
