package com.google.android.gms.common.net;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.PrivateKey;
import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SSLCertificateSocketFactory extends SSLSocketFactory {
    private static final TrustManager[] zzvf = {new zza()};

    @VisibleForTesting
    private final Context mContext;

    @VisibleForTesting
    private SSLSocketFactory zzvg = null;

    @VisibleForTesting
    private SSLSocketFactory zzvh = null;

    @VisibleForTesting
    private TrustManager[] zzvi = null;

    @VisibleForTesting
    private KeyManager[] zzvj = null;

    @VisibleForTesting
    private byte[] zzvk = null;

    @VisibleForTesting
    private byte[] zzvl = null;

    @VisibleForTesting
    private PrivateKey zzvm = null;

    @VisibleForTesting
    private final int zzvn;

    @VisibleForTesting
    private final boolean zzvo;

    @VisibleForTesting
    private final boolean zzvp;

    @VisibleForTesting
    private final String zzvq;

    private SSLCertificateSocketFactory(Context context, int i, boolean z, boolean z2, String str) {
        this.mContext = context.getApplicationContext();
        this.zzvn = i;
        this.zzvo = z;
        this.zzvp = z2;
        this.zzvq = str;
    }

    public static SocketFactory getDefault(Context context, int i) {
        return new SSLCertificateSocketFactory(context, i, false, true, null);
    }

    public static SSLSocketFactory getDefaultWithCacheDir(int i, Context context, String str) {
        return new SSLCertificateSocketFactory(context, i, true, true, str);
    }

    public static SSLSocketFactory getDefaultWithSessionCache(int i, Context context) {
        return new SSLCertificateSocketFactory(context, i, true, true, null);
    }

    public static SSLSocketFactory getInsecure(Context context, int i, boolean z) {
        return new SSLCertificateSocketFactory(context, i, z, false, null);
    }

    public static void verifyHostname(Socket socket, String str) throws IOException {
        if (!(socket instanceof SSLSocket)) {
            throw new IllegalArgumentException("Attempt to verify non-SSL socket");
        }
        SSLSocket sSLSocket = (SSLSocket) socket;
        sSLSocket.startHandshake();
        SSLSession session = sSLSocket.getSession();
        if (session == null) {
            throw new SSLException("Cannot verify SSL socket without session");
        }
        if (HttpsURLConnection.getDefaultHostnameVerifier().verify(str, session)) {
            return;
        }
        String strValueOf = String.valueOf(str);
        throw new SSLPeerUnverifiedException(strValueOf.length() != 0 ? "Cannot verify hostname: ".concat(strValueOf) : new String("Cannot verify hostname: "));
    }

    @VisibleForTesting
    private static void zza(Socket socket, int i) {
        if (socket != null) {
            try {
                socket.getClass().getMethod("setHandshakeTimeout", Integer.TYPE).invoke(socket, Integer.valueOf(i));
            } catch (IllegalAccessException e) {
                e = e;
                String strValueOf = String.valueOf(socket.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 45).append(strValueOf).append(" does not implement setSocketHandshakeTimeout").toString(), e);
            } catch (NoSuchMethodException e2) {
                e = e2;
                String strValueOf2 = String.valueOf(socket.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf2).length() + 45).append(strValueOf2).append(" does not implement setSocketHandshakeTimeout").toString(), e);
            } catch (InvocationTargetException e3) {
                Throwable cause = e3.getCause();
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
                String strValueOf3 = String.valueOf(socket.getClass());
                throw new RuntimeException(new StringBuilder(String.valueOf(strValueOf3).length() + 46).append("Failed to invoke setSocketHandshakeTimeout on ").append(strValueOf3).toString(), e3);
            }
        }
    }

    @VisibleForTesting
    private static void zza(Socket socket, PrivateKey privateKey) {
        if (socket != null) {
            try {
                socket.getClass().getMethod("setChannelIdPrivateKey", PrivateKey.class).invoke(socket, privateKey);
            } catch (IllegalAccessException e) {
                e = e;
                String strValueOf = String.valueOf(socket.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 42).append(strValueOf).append(" does not implement setChannelIdPrivateKey").toString(), e);
            } catch (NoSuchMethodException e2) {
                e = e2;
                String strValueOf2 = String.valueOf(socket.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf2).length() + 42).append(strValueOf2).append(" does not implement setChannelIdPrivateKey").toString(), e);
            } catch (InvocationTargetException e3) {
                Throwable cause = e3.getCause();
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
                String strValueOf3 = String.valueOf(socket.getClass());
                throw new RuntimeException(new StringBuilder(String.valueOf(strValueOf3).length() + 43).append("Failed to invoke setChannelIdPrivateKey on ").append(strValueOf3).toString(), e3);
            }
        }
    }

    @VisibleForTesting
    private static void zza(Socket socket, byte[] bArr) {
        if (socket != null) {
            try {
                socket.getClass().getMethod("setNpnProtocols", byte[].class).invoke(socket, bArr);
            } catch (IllegalAccessException e) {
                e = e;
                String strValueOf = String.valueOf(socket.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 35).append(strValueOf).append(" does not implement setNpnProtocols").toString(), e);
            } catch (NoSuchMethodException e2) {
                e = e2;
                String strValueOf2 = String.valueOf(socket.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf2).length() + 35).append(strValueOf2).append(" does not implement setNpnProtocols").toString(), e);
            } catch (InvocationTargetException e3) {
                Throwable cause = e3.getCause();
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
                String strValueOf3 = String.valueOf(socket.getClass());
                throw new RuntimeException(new StringBuilder(String.valueOf(strValueOf3).length() + 36).append("Failed to invoke setNpnProtocols on ").append(strValueOf3).toString(), e3);
            }
        }
    }

    private static byte[] zza(byte[]... bArr) {
        if (bArr.length == 0) {
            throw new IllegalArgumentException("items.length == 0");
        }
        int length = 0;
        for (byte[] bArr2 : bArr) {
            if (bArr2.length == 0 || bArr2.length > 255) {
                throw new IllegalArgumentException(new StringBuilder(44).append("s.length == 0 || s.length > 255: ").append(bArr2.length).toString());
            }
            length += bArr2.length + 1;
        }
        byte[] bArr3 = new byte[length];
        int length2 = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length2) {
            byte[] bArr4 = bArr[i];
            int i3 = i2 + 1;
            bArr3[i2] = (byte) bArr4.length;
            int length3 = bArr4.length;
            int i4 = 0;
            while (i4 < length3) {
                bArr3[i3] = bArr4[i4];
                i4++;
                i3++;
            }
            i++;
            i2 = i3;
        }
        return bArr3;
    }

    @VisibleForTesting
    private static void zzb(Socket socket, byte[] bArr) {
        if (socket != null) {
            try {
                socket.getClass().getMethod("setAlpnProtocols", byte[].class).invoke(socket, bArr);
            } catch (IllegalAccessException e) {
                e = e;
                String strValueOf = String.valueOf(socket.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 36).append(strValueOf).append(" does not implement setAlpnProtocols").toString(), e);
            } catch (NoSuchMethodException e2) {
                e = e2;
                String strValueOf2 = String.valueOf(socket.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf2).length() + 36).append(strValueOf2).append(" does not implement setAlpnProtocols").toString(), e);
            } catch (InvocationTargetException e3) {
                Throwable cause = e3.getCause();
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
                String strValueOf3 = String.valueOf(socket.getClass());
                throw new RuntimeException(new StringBuilder(String.valueOf(strValueOf3).length() + 37).append("Failed to invoke setAlpnProtocols on ").append(strValueOf3).toString(), e3);
            }
        }
    }

    @VisibleForTesting
    private final synchronized SSLSocketFactory zzcx() {
        SSLSocketFactory sSLSocketFactory;
        if (this.zzvp) {
            if (this.zzvq != null) {
                if (this.zzvh == null) {
                    this.zzvh = SocketFactoryCreator.getInstance().makeSocketFactoryWithCacheDir(this.mContext, this.zzvj, this.zzvi, this.zzvq);
                }
            } else if (this.zzvh == null) {
                this.zzvh = SocketFactoryCreator.getInstance().makeSocketFactory(this.mContext, this.zzvj, this.zzvi, this.zzvo);
            }
            sSLSocketFactory = this.zzvh;
        } else {
            if (this.zzvg == null) {
                Log.w("SSLCertificateSocketFactory", "Bypassing SSL security checks at caller's request");
                this.zzvg = SocketFactoryCreator.getInstance().makeSocketFactory(this.mContext, this.zzvj, zzvf, this.zzvo);
            }
            sSLSocketFactory = this.zzvg;
        }
        return sSLSocketFactory;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        Socket socketCreateSocket = zzcx().createSocket();
        zza(socketCreateSocket, this.zzvk);
        zzb(socketCreateSocket, this.zzvl);
        zza(socketCreateSocket, this.zzvn);
        zza(socketCreateSocket, this.zzvm);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        Socket socketCreateSocket = zzcx().createSocket(str, i);
        zza(socketCreateSocket, this.zzvk);
        zzb(socketCreateSocket, this.zzvl);
        zza(socketCreateSocket, this.zzvn);
        zza(socketCreateSocket, this.zzvm);
        if (this.zzvp) {
            verifyHostname(socketCreateSocket, str);
        }
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        Socket socketCreateSocket = zzcx().createSocket(str, i, inetAddress, i2);
        zza(socketCreateSocket, this.zzvk);
        zzb(socketCreateSocket, this.zzvl);
        zza(socketCreateSocket, this.zzvn);
        zza(socketCreateSocket, this.zzvm);
        if (this.zzvp) {
            verifyHostname(socketCreateSocket, str);
        }
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        Socket socketCreateSocket = zzcx().createSocket(inetAddress, i);
        zza(socketCreateSocket, this.zzvk);
        zzb(socketCreateSocket, this.zzvl);
        zza(socketCreateSocket, this.zzvn);
        zza(socketCreateSocket, this.zzvm);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        Socket socketCreateSocket = zzcx().createSocket(inetAddress, i, inetAddress2, i2);
        zza(socketCreateSocket, this.zzvk);
        zzb(socketCreateSocket, this.zzvl);
        zza(socketCreateSocket, this.zzvn);
        zza(socketCreateSocket, this.zzvm);
        return socketCreateSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Socket socketCreateSocket = zzcx().createSocket(socket, str, i, z);
        zza(socketCreateSocket, this.zzvk);
        zzb(socketCreateSocket, this.zzvl);
        zza(socketCreateSocket, this.zzvn);
        zza(socketCreateSocket, this.zzvm);
        if (this.zzvp) {
            verifyHostname(socketCreateSocket, str);
        }
        return socketCreateSocket;
    }

    public byte[] getAlpnSelectedProtocol(Socket socket) {
        try {
            return (byte[]) socket.getClass().getMethod("getAlpnSelectedProtocol", new Class[0]).invoke(socket, new Object[0]);
        } catch (IllegalAccessException e) {
            e = e;
            String strValueOf = String.valueOf(socket.getClass());
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 43).append(strValueOf).append(" does not implement getAlpnSelectedProtocol").toString(), e);
        } catch (NoSuchMethodException e2) {
            e = e2;
            String strValueOf2 = String.valueOf(socket.getClass());
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf2).length() + 43).append(strValueOf2).append(" does not implement getAlpnSelectedProtocol").toString(), e);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            String strValueOf3 = String.valueOf(socket.getClass());
            throw new RuntimeException(new StringBuilder(String.valueOf(strValueOf3).length() + 44).append("Failed to invoke getAlpnSelectedProtocol on ").append(strValueOf3).toString(), e3);
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return zzcx().getDefaultCipherSuites();
    }

    public byte[] getNpnSelectedProtocol(Socket socket) {
        try {
            return (byte[]) socket.getClass().getMethod("getNpnSelectedProtocol", new Class[0]).invoke(socket, new Object[0]);
        } catch (IllegalAccessException e) {
            e = e;
            String strValueOf = String.valueOf(socket.getClass());
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 42).append(strValueOf).append(" does not implement getNpnSelectedProtocol").toString(), e);
        } catch (NoSuchMethodException e2) {
            e = e2;
            String strValueOf2 = String.valueOf(socket.getClass());
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf2).length() + 42).append(strValueOf2).append(" does not implement getNpnSelectedProtocol").toString(), e);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            String strValueOf3 = String.valueOf(socket.getClass());
            throw new RuntimeException(new StringBuilder(String.valueOf(strValueOf3).length() + 43).append("Failed to invoke getNpnSelectedProtocol on ").append(strValueOf3).toString(), e3);
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return zzcx().getSupportedCipherSuites();
    }

    public void setAlpnProtocols(byte[][] bArr) {
        this.zzvl = zza(bArr);
    }

    public void setChannelIdPrivateKey(PrivateKey privateKey) {
        this.zzvm = privateKey;
    }

    public void setHostname(Socket socket, String str) {
        try {
            socket.getClass().getMethod("setHostname", String.class).invoke(socket, str);
        } catch (IllegalAccessException e) {
            e = e;
            String strValueOf = String.valueOf(socket.getClass());
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 31).append(strValueOf).append(" does not implement setHostname").toString(), e);
        } catch (NoSuchMethodException e2) {
            e = e2;
            String strValueOf2 = String.valueOf(socket.getClass());
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf2).length() + 31).append(strValueOf2).append(" does not implement setHostname").toString(), e);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            String strValueOf3 = String.valueOf(socket.getClass());
            throw new RuntimeException(new StringBuilder(String.valueOf(strValueOf3).length() + 32).append("Failed to invoke setHostname on ").append(strValueOf3).toString(), e3);
        }
    }

    public void setKeyManagers(KeyManager[] keyManagerArr) {
        this.zzvj = keyManagerArr;
        this.zzvh = null;
        this.zzvg = null;
    }

    public void setNpnProtocols(byte[][] bArr) {
        this.zzvk = zza(bArr);
    }

    public void setSoWriteTimeout(Socket socket, int i) throws SocketException {
        try {
            socket.getClass().getMethod("setSoWriteTimeout", Integer.TYPE).invoke(socket, Integer.valueOf(i));
        } catch (IllegalAccessException e) {
            e = e;
            String strValueOf = String.valueOf(socket.getClass());
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 37).append(strValueOf).append(" does not implement setSoWriteTimeout").toString(), e);
        } catch (NoSuchMethodException e2) {
            e = e2;
            String strValueOf2 = String.valueOf(socket.getClass());
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf2).length() + 37).append(strValueOf2).append(" does not implement setSoWriteTimeout").toString(), e);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof SocketException) {
                throw ((SocketException) cause);
            }
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            String strValueOf3 = String.valueOf(socket.getClass());
            throw new RuntimeException(new StringBuilder(String.valueOf(strValueOf3).length() + 38).append("Failed to invoke setSoWriteTimeout on ").append(strValueOf3).toString(), e3);
        }
    }

    public void setTrustManagers(TrustManager[] trustManagerArr) {
        this.zzvi = trustManagerArr;
        this.zzvh = null;
    }

    public void setUseSessionTickets(Socket socket, boolean z) {
        try {
            socket.getClass().getMethod("setUseSessionTickets", Boolean.TYPE).invoke(socket, Boolean.valueOf(z));
        } catch (IllegalAccessException e) {
            e = e;
            String strValueOf = String.valueOf(socket.getClass());
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 40).append(strValueOf).append(" does not implement setUseSessionTickets").toString(), e);
        } catch (NoSuchMethodException e2) {
            e = e2;
            String strValueOf2 = String.valueOf(socket.getClass());
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf2).length() + 40).append(strValueOf2).append(" does not implement setUseSessionTickets").toString(), e);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            String strValueOf3 = String.valueOf(socket.getClass());
            throw new RuntimeException(new StringBuilder(String.valueOf(strValueOf3).length() + 41).append("Failed to invoke setUseSessionTickets on ").append(strValueOf3).toString(), e3);
        }
    }
}
