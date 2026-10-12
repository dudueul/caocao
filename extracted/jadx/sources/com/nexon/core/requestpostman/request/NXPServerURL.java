package com.nexon.core.requestpostman.request;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class NXPServerURL {
    private static Environment defaultEnvironment = Environment.Live;

    public enum Environment {
        Live("Live"),
        Pre("Pre"),
        QA("QA"),
        Alpha("Alpha");

        private String value;

        Environment(String str) {
            this.value = str;
        }

        public static Environment getEnvironmentFromString(@NonNull String str) throws IllegalArgumentException {
            if (Live.getValue().equalsIgnoreCase(str)) {
                return Live;
            }
            if (Pre.getValue().equalsIgnoreCase(str)) {
                return Pre;
            }
            if (QA.getValue().equalsIgnoreCase(str)) {
                return QA;
            }
            if (Alpha.getValue().equalsIgnoreCase(str)) {
                return Alpha;
            }
            throw new IllegalArgumentException("Invalid argument. arg : " + str);
        }

        public String getValue() {
            return this.value;
        }
    }

    @NonNull
    protected static Environment getDefaultEnvironment() {
        return defaultEnvironment;
    }

    @NonNull
    public abstract Environment getEnvironment();

    @NonNull
    public abstract String getUrl();
}
