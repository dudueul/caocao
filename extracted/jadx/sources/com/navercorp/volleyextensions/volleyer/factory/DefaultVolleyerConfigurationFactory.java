package com.navercorp.volleyextensions.volleyer.factory;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.navercorp.volleyextensions.volleyer.VolleyerConfiguration;
import com.navercorp.volleyextensions.volleyer.request.creator.DefaultRequestCreator;
import com.navercorp.volleyextensions.volleyer.request.creator.RequestCreator;
import com.navercorp.volleyextensions.volleyer.request.executor.DefaultRequestExecutor;
import com.navercorp.volleyextensions.volleyer.request.executor.RequestExecutor;
import com.navercorp.volleyextensions.volleyer.response.parser.NetworkResponseParser;
import com.navercorp.volleyextensions.volleyer.util.VolleyerLog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DefaultVolleyerConfigurationFactory {
    private static Response.ErrorListener defaultErrorListener = new Response.ErrorListener() { // from class: com.navercorp.volleyextensions.volleyer.factory.DefaultVolleyerConfigurationFactory.1
        @Override // com.android.volley.Response.ErrorListener
        public void onErrorResponse(VolleyError volleyError) {
            VolleyerLog.debug(volleyError, "ERROR : ", new Object[0]);
        }
    };

    private static class ConfigurationHolder {
        private static final VolleyerConfiguration configuration = new VolleyerConfiguration(DefaultVolleyerConfigurationFactory.createRequestCreator(), DefaultVolleyerConfigurationFactory.createRequestExecutor(), DefaultVolleyerConfigurationFactory.createNetworkResponseParser(), DefaultVolleyerConfigurationFactory.createErrorListener());

        private ConfigurationHolder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static VolleyerConfiguration getConfiguration() {
            return configuration;
        }
    }

    public static VolleyerConfiguration create() {
        return ConfigurationHolder.getConfiguration();
    }

    public static Response.ErrorListener createErrorListener() {
        return defaultErrorListener;
    }

    public static NetworkResponseParser createNetworkResponseParser() {
        return DefaultNetworkResponseParserFactory.create();
    }

    public static RequestCreator createRequestCreator() {
        return new DefaultRequestCreator();
    }

    public static RequestExecutor createRequestExecutor() {
        return new DefaultRequestExecutor();
    }
}
