package com.bumptech.glide.manager;

import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class EmptyRequestManagerTreeNode implements RequestManagerTreeNode {
    EmptyRequestManagerTreeNode() {
    }

    @Override // com.bumptech.glide.manager.RequestManagerTreeNode
    public Set<RequestManager> getDescendants() {
        return Collections.emptySet();
    }
}
