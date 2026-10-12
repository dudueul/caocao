package com.nexon.ngsm;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NgsmDouble extends NgsmSecuData<Double> {
    public NgsmDouble() {
        super(NgsmSecuData.EJavaDataType.J_DOUBLE);
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean addData(Double d) {
        return Ngsm.getInst().ngsmNativeAddSecuDataDouble(this.m_shandle, d.doubleValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.nexon.ngsm.NgsmSecuData
    public Double getData() {
        return Double.valueOf(Ngsm.getInst().ngsmNativeGetSecuDataDouble(this.m_shandle));
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean setData(Double d) {
        return Ngsm.getInst().ngsmNativeSetSecuDataDouble(this.m_shandle, d.doubleValue());
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean subData(Double d) {
        return Ngsm.getInst().ngsmNativeSubSecuDataDouble(this.m_shandle, d.doubleValue());
    }
}
