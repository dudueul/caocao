package com.navercorp.volleyextensions.volleyer;

import com.android.volley.RequestQueue;
import com.navercorp.volleyextensions.volleyer.builder.DeleteBuilder;
import com.navercorp.volleyextensions.volleyer.builder.GetBuilder;
import com.navercorp.volleyextensions.volleyer.builder.PostBuilder;
import com.navercorp.volleyextensions.volleyer.builder.PutBuilder;
import com.navercorp.volleyextensions.volleyer.factory.DefaultVolleyerConfigurationFactory;
import com.navercorp.volleyextensions.volleyer.util.Assert;
import com.navercorp.volleyextensions.volleyer.util.VolleyerLog;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Volleyer {
    private static volatile Volleyer defaultVolleyer = new DummyVolleyer();
    private static final Map<RequestQueue, Volleyer> volleyers = new WeakHashMap();
    private volatile VolleyerConfiguration configuration = DefaultVolleyerConfigurationFactory.create();
    private final RequestQueue requestQueue;

    public class Settings {
        private VolleyerConfiguration configuration;
        private boolean isDefaultVolleyer;

        private Settings() {
            this.isDefaultVolleyer = false;
        }

        private void setConfigurationToVolleyerIfNotNull() {
            if (this.configuration == null) {
                return;
            }
            Volleyer.this.configuration = this.configuration;
        }

        private void setDefaultVolleyerIfTrue() {
            if (this.isDefaultVolleyer) {
                Volleyer unused = Volleyer.defaultVolleyer = Volleyer.this;
            }
        }

        public void done() {
            setConfigurationToVolleyerIfNotNull();
            setDefaultVolleyerIfTrue();
        }

        public Settings setAsDefault() {
            this.isDefaultVolleyer = true;
            return this;
        }

        public Settings setConfiguration(VolleyerConfiguration volleyerConfiguration) {
            Assert.notNull(volleyerConfiguration, "VolleyerConfiguration");
            this.configuration = volleyerConfiguration;
            return this;
        }
    }

    Volleyer(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    private static boolean isDefaultVolleyerDummy() {
        return DummyVolleyer.class == defaultVolleyer.getClass();
    }

    private static void logErrorIfDefaultNotSet() {
        if (isDefaultVolleyerDummy()) {
            VolleyerLog.error("Default volleyer is not set. You have to set the default volleyer as first like this code. : \nvolleyer(requestQueue).settings().setAsDefault().done(); ", new Object[0]);
        }
    }

    public static Volleyer volleyer() {
        logErrorIfDefaultNotSet();
        return defaultVolleyer;
    }

    public static synchronized Volleyer volleyer(RequestQueue requestQueue) {
        Volleyer volleyer;
        Assert.notNull(requestQueue, "RequestQueue");
        Volleyer volleyer2 = volleyers.get(requestQueue);
        if (volleyer2 != null) {
            volleyer = volleyer2;
        } else {
            Volleyer volleyer3 = new Volleyer(requestQueue);
            volleyers.put(requestQueue, volleyer3);
            volleyer = volleyer3;
        }
        return volleyer;
    }

    public DeleteBuilder delete(String str) {
        return new DeleteBuilder(this.requestQueue, this.configuration, str);
    }

    public GetBuilder get(String str) {
        return new GetBuilder(this.requestQueue, this.configuration, str);
    }

    public PostBuilder post(String str) {
        return new PostBuilder(this.requestQueue, this.configuration, str);
    }

    public PutBuilder put(String str) {
        return new PutBuilder(this.requestQueue, this.configuration, str);
    }

    public Settings settings() {
        return new Settings();
    }
}
