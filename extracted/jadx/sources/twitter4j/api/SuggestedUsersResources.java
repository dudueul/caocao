package twitter4j.api;

import twitter4j.Category;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.User;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface SuggestedUsersResources {
    ResponseList<User> getMemberSuggestions(String str) throws TwitterException;

    ResponseList<Category> getSuggestedUserCategories() throws TwitterException;

    ResponseList<User> getUserSuggestions(String str) throws TwitterException;
}
