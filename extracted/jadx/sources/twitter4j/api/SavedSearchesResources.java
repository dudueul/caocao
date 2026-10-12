package twitter4j.api;

import twitter4j.ResponseList;
import twitter4j.SavedSearch;
import twitter4j.TwitterException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface SavedSearchesResources {
    SavedSearch createSavedSearch(String str) throws TwitterException;

    SavedSearch destroySavedSearch(int i) throws TwitterException;

    ResponseList<SavedSearch> getSavedSearches() throws TwitterException;

    SavedSearch showSavedSearch(int i) throws TwitterException;
}
