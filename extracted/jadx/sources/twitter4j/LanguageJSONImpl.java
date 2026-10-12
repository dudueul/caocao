package twitter4j;

import twitter4j.api.HelpResources;
import twitter4j.conf.Configuration;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class LanguageJSONImpl implements HelpResources.Language {
    private String code;
    private String name;
    private String status;

    LanguageJSONImpl(JSONObject jSONObject) throws TwitterException {
        init(jSONObject);
    }

    static ResponseList<HelpResources.Language> createLanguageList(HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        return createLanguageList(httpResponse.asJSONArray(), httpResponse, configuration);
    }

    static ResponseList<HelpResources.Language> createLanguageList(JSONArray jSONArray, HttpResponse httpResponse, Configuration configuration) throws TwitterException {
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
        }
        try {
            int length = jSONArray.length();
            ResponseListImpl responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                LanguageJSONImpl languageJSONImpl = new LanguageJSONImpl(jSONObject);
                responseListImpl.add(languageJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(languageJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, jSONArray);
            }
            return responseListImpl;
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    private void init(JSONObject jSONObject) throws TwitterException {
        try {
            this.name = jSONObject.getString("name");
            this.code = jSONObject.getString("code");
            this.status = jSONObject.getString("status");
        } catch (JSONException e) {
            throw new TwitterException(String.valueOf(e.getMessage()) + ":" + jSONObject.toString(), e);
        }
    }

    @Override // twitter4j.api.HelpResources.Language
    public String getCode() {
        return this.code;
    }

    @Override // twitter4j.api.HelpResources.Language
    public String getName() {
        return this.name;
    }

    @Override // twitter4j.api.HelpResources.Language
    public String getStatus() {
        return this.status;
    }
}
