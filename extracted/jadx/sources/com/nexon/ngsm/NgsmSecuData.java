package com.nexon.ngsm;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class NgsmSecuData<T> {
    protected final long INVALID_SECU_KEY = 0;
    protected long m_shandle;

    protected enum EJavaDataType {
        J_NORMAL(1),
        J_FLOAT(2),
        J_DOUBLE(3);

        private int secuData;

        EJavaDataType(int i) {
            this.secuData = i;
        }

        public int getSecuData() {
            return this.secuData;
        }
    }

    protected NgsmSecuData(EJavaDataType eJavaDataType) {
        this.m_shandle = 0L;
        this.m_shandle = Ngsm.getInst().ngsmNativeCreateSecuData(eJavaDataType.getSecuData());
    }

    public abstract boolean addData(T t);

    protected void finalize() throws Throwable {
        if (this.m_shandle != 0) {
            Ngsm.getInst().ngsmNativeReleaseSecuData(this.m_shandle);
        }
        super.finalize();
    }

    public abstract T getData();

    public void releaseData() {
        Ngsm.getInst().ngsmNativeReleaseSecuData(this.m_shandle);
        this.m_shandle = 0L;
    }

    public abstract boolean setData(T t);

    public abstract boolean subData(T t);
}
