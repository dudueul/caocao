package com.nexon.platform.store.billing;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
abstract class OrderState {
    OrderState() {
    }

    abstract void process(Order order);
}
