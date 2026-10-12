package com.facebook.share;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public interface Sharer {

    public static class Result {
        final String postId;

        public Result(String str) {
            this.postId = str;
        }

        public String getPostId() {
            return this.postId;
        }
    }

    boolean getShouldFailOnDataError();

    void setShouldFailOnDataError(boolean z);
}
