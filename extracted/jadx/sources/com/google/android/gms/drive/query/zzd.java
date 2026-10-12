package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.query.internal.zzj;
import com.google.android.gms.drive.query.internal.zzx;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class zzd implements zzj<String> {
    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ String zza(com.google.android.gms.drive.metadata.zzb zzbVar, Object obj) {
        return String.format("contains(%s,%s)", zzbVar.getName(), obj);
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ String zza(zzx zzxVar, MetadataField metadataField, Object obj) {
        return String.format("cmp(%s,%s,%s)", zzxVar.getTag(), metadataField.getName(), obj);
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ String zza(zzx zzxVar, List<String> list) {
        StringBuilder sb = new StringBuilder(String.valueOf(zzxVar.getTag()).concat("("));
        String str = "";
        Iterator<String> it = list.iterator();
        while (true) {
            String str2 = str;
            if (!it.hasNext()) {
                return sb.append(")").toString();
            }
            String next = it.next();
            sb.append(str2);
            sb.append(next);
            str = ",";
        }
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ String zza(String str) {
        return String.format("not(%s)", str);
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ String zzbb() {
        return "ownedByMe()";
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ String zzbc() {
        return "all()";
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ String zzc(MetadataField metadataField, Object obj) {
        return String.format("has(%s,%s)", metadataField.getName(), obj);
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ String zze(MetadataField metadataField) {
        return String.format("fieldOnly(%s)", metadataField.getName());
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ String zzg(String str) {
        return String.format("fullTextSearch(%s)", str);
    }
}
