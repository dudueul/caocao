package com.unity3d.player;

import android.os.Build;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class b extends SSLSocketFactory {
    private static volatile SSLSocketFactory c;
    private static volatile X509TrustManager d;
    private static final Object e = new Object[0];
    private static final Object f = new Object[0];
    private static final boolean g;
    private final SSLSocketFactory a;
    private final a b;

    class a implements HandshakeCompletedListener {
        @Override // javax.net.ssl.HandshakeCompletedListener
        public final void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
            SSLSession session = handshakeCompletedEvent.getSession();
            session.getCipherSuite();
            session.getProtocol();
            try {
                session.getPeerPrincipal().getName();
            } catch (SSLPeerUnverifiedException e) {
            }
        }
    }

    /* JADX INFO: renamed from: com.unity3d.player.b$b, reason: collision with other inner class name */
    public static abstract class AbstractC0076b implements X509TrustManager {
        protected X509TrustManager a = b.c();

        @Override // javax.net.ssl.X509TrustManager
        public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            this.a.checkClientTrusted(x509CertificateArr, str);
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            this.a.checkServerTrusted(x509CertificateArr, str);
        }

        @Override // javax.net.ssl.X509TrustManager
        public final X509Certificate[] getAcceptedIssuers() {
            return this.a.getAcceptedIssuers();
        }
    }

    static {
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 20) {
            z = true;
        }
        g = z;
    }

    private b(AbstractC0076b[] abstractC0076bArr) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, abstractC0076bArr, null);
        this.a = sSLContext.getSocketFactory();
        this.b = null;
    }

    private Socket a(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            if (g) {
                ((SSLSocket) socket).setEnabledProtocols(((SSLSocket) socket).getSupportedProtocols());
            }
            if (this.b != null) {
                ((SSLSocket) socket).addHandshakeCompletedListener(this.b);
            }
        }
        return socket;
    }

    public static SSLSocketFactory a(AbstractC0076b abstractC0076b) {
        try {
            return abstractC0076b == null ? b() : new b(new AbstractC0076b[]{abstractC0076b});
        } catch (Exception e2) {
            g.Log(5, "CustomSSLSocketFactory: Failed to create SSLSocketFactory (" + e2.getMessage() + ")");
            return null;
        }
    }

    private static SSLSocketFactory b() {
        SSLSocketFactory bVar;
        synchronized (e) {
            if (c != null) {
                bVar = c;
            } else {
                bVar = new b(null);
                c = bVar;
            }
        }
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static X509TrustManager c() {
        synchronized (f) {
            if (d != null) {
                return d;
            }
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                    if (trustManager instanceof X509TrustManager) {
                        X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
                        d = x509TrustManager;
                        return x509TrustManager;
                    }
                }
            } catch (Exception e2) {
                g.Log(5, "CustomSSLSocketFactory: Failed to find X509TrustManager (" + e2.getMessage() + ")");
            }
            return null;
        }
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket() {
        return a(this.a.createSocket());
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i) {
        return a(this.a.createSocket(str, i));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return a(this.a.createSocket(str, i, inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i) {
        return a(this.a.createSocket(inetAddress, i));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return a(this.a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return a(this.a.createSocket(socket, str, i, z));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }
}
