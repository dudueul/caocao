package com.squareup.otto;

import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
interface HandlerFinder {
    public static final HandlerFinder ANNOTATED = new HandlerFinder() { // from class: com.squareup.otto.HandlerFinder.1
        @Override // com.squareup.otto.HandlerFinder
        public Map<Class<?>, EventProducer> findAllProducers(Object obj) {
            return AnnotatedHandlerFinder.findAllProducers(obj);
        }

        @Override // com.squareup.otto.HandlerFinder
        public Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object obj) {
            return AnnotatedHandlerFinder.findAllSubscribers(obj);
        }
    };

    Map<Class<?>, EventProducer> findAllProducers(Object obj);

    Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object obj);
}
