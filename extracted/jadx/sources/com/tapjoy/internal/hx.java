package com.tapjoy.internal;

import android.graphics.Bitmap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class hx {
    public static final bi e;
    private static final an f;
    public URL a;
    public Bitmap b;
    public byte[] c;
    public ie d;

    static {
        an arVar = new ar();
        if (!(arVar instanceof as)) {
            arVar = new ap.a((aq) arVar);
        }
        f = arVar;
        e = new bi() { // from class: com.tapjoy.internal.hx.1
            @Override // com.tapjoy.internal.bi
            public final /* synthetic */ Object a(bn bnVar) {
                return new hx(bnVar);
            }
        };
    }

    hx(bn bnVar) {
        if (bnVar.k() == bs.STRING) {
            this.a = bnVar.e();
            return;
        }
        bnVar.h();
        String strL = bnVar.l();
        while (bnVar.j()) {
            if ("url".equals(strL)) {
                this.a = bnVar.e();
            } else {
                bnVar.s();
            }
        }
        bnVar.i();
    }

    public hx(URL url) {
        this.a = url;
    }

    private ByteArrayInputStream a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        jx.a(inputStream, byteArrayOutputStream);
        byteArrayOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        Cif cif = new Cif();
        cif.a(byteArray);
        ie ieVarA = cif.a();
        if (ieVarA.b == 0) {
            this.c = byteArray;
            this.d = ieVarA;
        } else {
            s sVar = s.a;
            this.b = s.a(byteArrayInputStream);
            byteArrayInputStream.reset();
        }
        return byteArrayInputStream;
    }

    public final boolean a() {
        return (this.b == null && this.c == null) ? false : true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.tapjoy.internal.ht.2.<init>(com.tapjoy.internal.ht, java.net.URL, java.io.InputStream, long):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:309)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:224)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:93)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    public final void b() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.hx.b():void");
    }
}
