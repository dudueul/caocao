package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Bus {
    public static final String DEFAULT_IDENTIFIER = "default";
    private final ThreadEnforcer enforcer;
    private final ThreadLocal<ConcurrentLinkedQueue<EventWithHandler>> eventsToDispatch;
    private final ConcurrentMap<Class<?>, Set<Class<?>>> flattenHierarchyCache;
    private final HandlerFinder handlerFinder;
    private final ConcurrentMap<Class<?>, Set<EventHandler>> handlersByType;
    private final String identifier;
    private final ThreadLocal<Boolean> isDispatching;
    private final ConcurrentMap<Class<?>, EventProducer> producersByType;

    static class EventWithHandler {
        final Object event;
        final EventHandler handler;

        public EventWithHandler(Object obj, EventHandler eventHandler) {
            this.event = obj;
            this.handler = eventHandler;
        }
    }

    public Bus() {
        this(DEFAULT_IDENTIFIER);
    }

    public Bus(ThreadEnforcer threadEnforcer) {
        this(threadEnforcer, DEFAULT_IDENTIFIER);
    }

    public Bus(ThreadEnforcer threadEnforcer, String str) {
        this(threadEnforcer, str, HandlerFinder.ANNOTATED);
    }

    Bus(ThreadEnforcer threadEnforcer, String str, HandlerFinder handlerFinder) {
        this.handlersByType = new ConcurrentHashMap();
        this.producersByType = new ConcurrentHashMap();
        this.eventsToDispatch = new ThreadLocal<ConcurrentLinkedQueue<EventWithHandler>>() { // from class: com.squareup.otto.Bus.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public ConcurrentLinkedQueue<EventWithHandler> initialValue() {
                return new ConcurrentLinkedQueue<>();
            }
        };
        this.isDispatching = new ThreadLocal<Boolean>() { // from class: com.squareup.otto.Bus.2
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ThreadLocal
            public Boolean initialValue() {
                return false;
            }
        };
        this.flattenHierarchyCache = new ConcurrentHashMap();
        this.enforcer = threadEnforcer;
        this.identifier = str;
        this.handlerFinder = handlerFinder;
    }

    public Bus(String str) {
        this(ThreadEnforcer.MAIN, str);
    }

    private void dispatchProducerResultToHandler(EventHandler eventHandler, EventProducer eventProducer) {
        Object objProduceEvent = null;
        try {
            objProduceEvent = eventProducer.produceEvent();
        } catch (InvocationTargetException e) {
            throwRuntimeException("Producer " + eventProducer + " threw an exception.", e);
        }
        if (objProduceEvent == null) {
            return;
        }
        dispatch(objProduceEvent, eventHandler);
    }

    private Set<Class<?>> getClassesFor(Class<?> cls) {
        LinkedList linkedList = new LinkedList();
        HashSet hashSet = new HashSet();
        linkedList.add(cls);
        while (!linkedList.isEmpty()) {
            Class cls2 = (Class) linkedList.remove(0);
            hashSet.add(cls2);
            Class superclass = cls2.getSuperclass();
            if (superclass != null) {
                linkedList.add(superclass);
            }
        }
        return hashSet;
    }

    private static void throwRuntimeException(String str, InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause == null) {
            throw new RuntimeException(str + ": " + invocationTargetException.getMessage(), invocationTargetException);
        }
        throw new RuntimeException(str + ": " + cause.getMessage(), cause);
    }

    protected void dispatch(Object obj, EventHandler eventHandler) {
        try {
            eventHandler.handleEvent(obj);
        } catch (InvocationTargetException e) {
            throwRuntimeException("Could not dispatch event: " + obj.getClass() + " to handler " + eventHandler, e);
        }
    }

    protected void dispatchQueuedEvents() {
        if (this.isDispatching.get().booleanValue()) {
            return;
        }
        this.isDispatching.set(true);
        while (true) {
            try {
                EventWithHandler eventWithHandlerPoll = this.eventsToDispatch.get().poll();
                if (eventWithHandlerPoll == null) {
                    return;
                }
                if (eventWithHandlerPoll.handler.isValid()) {
                    dispatch(eventWithHandlerPoll.event, eventWithHandlerPoll.handler);
                }
            } finally {
                this.isDispatching.set(false);
            }
        }
    }

    protected void enqueueEvent(Object obj, EventHandler eventHandler) {
        this.eventsToDispatch.get().offer(new EventWithHandler(obj, eventHandler));
    }

    Set<Class<?>> flattenHierarchy(Class<?> cls) {
        Set<Class<?>> set = this.flattenHierarchyCache.get(cls);
        if (set != null) {
            return set;
        }
        Set<Class<?>> classesFor = getClassesFor(cls);
        Set<Class<?>> setPutIfAbsent = this.flattenHierarchyCache.putIfAbsent(cls, classesFor);
        return setPutIfAbsent == null ? classesFor : setPutIfAbsent;
    }

    Set<EventHandler> getHandlersForEventType(Class<?> cls) {
        return this.handlersByType.get(cls);
    }

    EventProducer getProducerForEventType(Class<?> cls) {
        return this.producersByType.get(cls);
    }

    public void post(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Event to post must not be null.");
        }
        this.enforcer.enforce(this);
        boolean z = false;
        Iterator<Class<?>> it = flattenHierarchy(obj.getClass()).iterator();
        while (it.hasNext()) {
            Set<EventHandler> handlersForEventType = getHandlersForEventType(it.next());
            if (handlersForEventType != null && !handlersForEventType.isEmpty()) {
                z = true;
                Iterator<EventHandler> it2 = handlersForEventType.iterator();
                while (it2.hasNext()) {
                    enqueueEvent(obj, it2.next());
                }
            }
        }
        if (!z && !(obj instanceof DeadEvent)) {
            post(new DeadEvent(this, obj));
        }
        dispatchQueuedEvents();
    }

    public void register(Object obj) {
        CopyOnWriteArraySet copyOnWriteArraySet;
        if (obj == null) {
            throw new NullPointerException("Object to register must not be null.");
        }
        this.enforcer.enforce(this);
        Map<Class<?>, EventProducer> mapFindAllProducers = this.handlerFinder.findAllProducers(obj);
        for (Class<?> cls : mapFindAllProducers.keySet()) {
            EventProducer eventProducer = mapFindAllProducers.get(cls);
            EventProducer eventProducerPutIfAbsent = this.producersByType.putIfAbsent(cls, eventProducer);
            if (eventProducerPutIfAbsent != null) {
                throw new IllegalArgumentException("Producer method for type " + cls + " found on type " + eventProducer.target.getClass() + ", but already registered by type " + eventProducerPutIfAbsent.target.getClass() + ".");
            }
            Set<EventHandler> set = this.handlersByType.get(cls);
            if (set != null && !set.isEmpty()) {
                Iterator<EventHandler> it = set.iterator();
                while (it.hasNext()) {
                    dispatchProducerResultToHandler(it.next(), eventProducer);
                }
            }
        }
        Map<Class<?>, Set<EventHandler>> mapFindAllSubscribers = this.handlerFinder.findAllSubscribers(obj);
        for (Class<?> cls2 : mapFindAllSubscribers.keySet()) {
            Set<EventHandler> setPutIfAbsent = this.handlersByType.get(cls2);
            if (setPutIfAbsent == null && (setPutIfAbsent = this.handlersByType.putIfAbsent(cls2, (copyOnWriteArraySet = new CopyOnWriteArraySet()))) == null) {
                setPutIfAbsent = copyOnWriteArraySet;
            }
            if (!setPutIfAbsent.addAll(mapFindAllSubscribers.get(cls2))) {
                throw new IllegalArgumentException("Object already registered.");
            }
        }
        for (Map.Entry<Class<?>, Set<EventHandler>> entry : mapFindAllSubscribers.entrySet()) {
            EventProducer eventProducer2 = this.producersByType.get(entry.getKey());
            if (eventProducer2 != null && eventProducer2.isValid()) {
                for (EventHandler eventHandler : entry.getValue()) {
                    if (!eventProducer2.isValid()) {
                        break;
                    } else if (eventHandler.isValid()) {
                        dispatchProducerResultToHandler(eventHandler, eventProducer2);
                    }
                }
            }
        }
    }

    public String toString() {
        return "[Bus \"" + this.identifier + "\"]";
    }

    public void unregister(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Object to unregister must not be null.");
        }
        this.enforcer.enforce(this);
        for (Map.Entry<Class<?>, EventProducer> entry : this.handlerFinder.findAllProducers(obj).entrySet()) {
            Class<?> key = entry.getKey();
            EventProducer producerForEventType = getProducerForEventType(key);
            EventProducer value = entry.getValue();
            if (value == null || !value.equals(producerForEventType)) {
                throw new IllegalArgumentException("Missing event producer for an annotated method. Is " + obj.getClass() + " registered?");
            }
            this.producersByType.remove(key).invalidate();
        }
        for (Map.Entry<Class<?>, Set<EventHandler>> entry2 : this.handlerFinder.findAllSubscribers(obj).entrySet()) {
            Set<EventHandler> handlersForEventType = getHandlersForEventType(entry2.getKey());
            Set<EventHandler> value2 = entry2.getValue();
            if (handlersForEventType == null || !handlersForEventType.containsAll(value2)) {
                throw new IllegalArgumentException("Missing event handler for an annotated method. Is " + obj.getClass() + " registered?");
            }
            for (EventHandler eventHandler : handlersForEventType) {
                if (value2.contains(eventHandler)) {
                    eventHandler.invalidate();
                }
            }
            handlersForEventType.removeAll(value2);
        }
    }
}
