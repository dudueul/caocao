package com.nostra13.universalimageloader.cache.disc.naming;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class HashCodeFileNameGenerator implements FileNameGenerator {
    @Override // com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator
    public String generate(String str) {
        return String.valueOf(str.hashCode());
    }
}
