package android.support.graphics.drawable;

import android.animation.TypeEvaluator;
import android.support.annotation.RestrictTo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ArgbEvaluator implements TypeEvaluator {
    private static final ArgbEvaluator sInstance = new ArgbEvaluator();

    public static ArgbEvaluator getInstance() {
        return sInstance;
    }

    @Override // android.animation.TypeEvaluator
    public Object evaluate(float f, Object obj, Object obj2) {
        int iIntValue = ((Integer) obj).intValue();
        float f2 = ((iIntValue >> 24) & 255) / 255.0f;
        int iIntValue2 = ((Integer) obj2).intValue();
        float fPow = (float) Math.pow(((iIntValue >> 16) & 255) / 255.0f, 2.2d);
        float fPow2 = (float) Math.pow(((iIntValue >> 8) & 255) / 255.0f, 2.2d);
        float fPow3 = (float) Math.pow((iIntValue & 255) / 255.0f, 2.2d);
        return Integer.valueOf((Math.round((f2 + (((((iIntValue2 >> 24) & 255) / 255.0f) - f2) * f)) * 255.0f) << 24) | (Math.round(((float) Math.pow(fPow + ((((float) Math.pow(((iIntValue2 >> 16) & 255) / 255.0f, 2.2d)) - fPow) * f), 0.45454545454545453d)) * 255.0f) << 16) | (Math.round(((float) Math.pow(fPow2 + ((((float) Math.pow(((iIntValue2 >> 8) & 255) / 255.0f, 2.2d)) - fPow2) * f), 0.45454545454545453d)) * 255.0f) << 8) | Math.round(((float) Math.pow(fPow3 + ((((float) Math.pow((iIntValue2 & 255) / 255.0f, 2.2d)) - fPow3) * f), 0.45454545454545453d)) * 255.0f));
    }
}
