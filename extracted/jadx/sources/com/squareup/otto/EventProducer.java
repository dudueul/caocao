package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class EventProducer {
    private final int hashCode;
    private final Method method;
    final Object target;
    private boolean valid = true;

    EventProducer(Object obj, Method method) {
        if (obj == null) {
            throw new NullPointerException("EventProducer target cannot be null.");
        }
        if (method == null) {
            throw new NullPointerException("EventProducer method cannot be null.");
        }
        this.target = obj;
        this.method = method;
        method.setAccessible(true);
        this.hashCode = ((method.hashCode() + 31) * 31) + obj.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            EventProducer eventProducer = (EventProducer) obj;
            return this.method.equals(eventProducer.method) && this.target == eventProducer.target;
        }
        return false;
    }

    public int hashCode() {
        return this.hashCode;
    }

    public void invalidate() {
        this.valid = false;
    }

    public boolean isValid() {
        return this.valid;
    }

    public Object produceEvent() throws InvocationTargetException {
        if (!this.valid) {
            throw new IllegalStateException(toString() + " has been invalidated and can no longer produce events.");
        }
        try {
            return this.method.invoke(this.target, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof Error) {
                throw ((Error) e2.getCause());
            }
            throw e2;
        }
    }

    public String toString() {
        return "[EventProducer " + this.method + "]";
    }
}
