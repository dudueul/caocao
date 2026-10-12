package com.facebook.appevents;

import android.content.Context;
import com.facebook.FacebookSdk;
import com.facebook.internal.AttributionIdentifiers;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class AppEventCollection {
    private final HashMap<AccessTokenAppIdPair, SessionEventsState> stateMap = new HashMap<>();

    private synchronized SessionEventsState getSessionEventsState(AccessTokenAppIdPair accessTokenAppIdPair) {
        SessionEventsState sessionEventsState;
        sessionEventsState = this.stateMap.get(accessTokenAppIdPair);
        if (sessionEventsState == null) {
            Context applicationContext = FacebookSdk.getApplicationContext();
            sessionEventsState = new SessionEventsState(AttributionIdentifiers.getAttributionIdentifiers(applicationContext), AppEventsLogger.getAnonymousAppDeviceGUID(applicationContext));
        }
        this.stateMap.put(accessTokenAppIdPair, sessionEventsState);
        return sessionEventsState;
    }

    public synchronized void addEvent(AccessTokenAppIdPair accessTokenAppIdPair, AppEvent appEvent) {
        getSessionEventsState(accessTokenAppIdPair).addEvent(appEvent);
    }

    public synchronized void addPersistedEvents(PersistedEvents persistedEvents) {
        if (persistedEvents != null) {
            for (AccessTokenAppIdPair accessTokenAppIdPair : persistedEvents.keySet()) {
                SessionEventsState sessionEventsState = getSessionEventsState(accessTokenAppIdPair);
                Iterator<AppEvent> it = persistedEvents.get(accessTokenAppIdPair).iterator();
                while (it.hasNext()) {
                    sessionEventsState.addEvent(it.next());
                }
            }
        }
    }

    public synchronized SessionEventsState get(AccessTokenAppIdPair accessTokenAppIdPair) {
        return this.stateMap.get(accessTokenAppIdPair);
    }

    public synchronized int getEventCount() {
        int accumulatedEventCount;
        accumulatedEventCount = 0;
        Iterator<SessionEventsState> it = this.stateMap.values().iterator();
        while (it.hasNext()) {
            accumulatedEventCount += it.next().getAccumulatedEventCount();
        }
        return accumulatedEventCount;
    }

    public synchronized Set<AccessTokenAppIdPair> keySet() {
        return this.stateMap.keySet();
    }
}
