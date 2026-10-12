package com.nexon.ngsm;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NgsmFloat extends NgsmSecuData<Float> {
    public NgsmFloat() {
        super(NgsmSecuData.EJavaDataType.J_FLOAT);
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean addData(Float f) {
        return Ngsm.getInst().ngsmNativeAddSecuDataFloat(this.m_shandle, f.floatValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.nexon.ngsm.NgsmSecuData
    public Float getData() {
        return Float.valueOf(Ngsm.getInst().ngsmNativeGetSecuDataFloat(this.m_shandle));
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean setData(Float f) {
        return Ngsm.getInst().ngsmNativeSetSecuDataFloat(this.m_shandle, f.floatValue());
    }

    @Override // com.nexon.ngsm.NgsmSecuData
    public boolean subData(Float f) {
        return Ngsm.getInst().ngsmNativeSubSecuDataFloat(this.m_shandle, f.floatValue());
    }
}
