package com.facebook.appevents.codeless;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.ParameterComponent;
import com.facebook.appevents.codeless.internal.PathComponent;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CodelessMatcher {
    private static final String CURRENT_CLASS_NAME = ".";
    private static final String PARENT_CLASS_NAME = "..";
    private static final String TAG = CodelessMatcher.class.getCanonicalName();
    private final Handler uiThreadHandler = new Handler(Looper.getMainLooper());
    private Set<Activity> activitiesSet = new HashSet();
    private Set<ViewMatcher> viewMatchers = new HashSet();
    private HashMap<String, String> delegateMap = new HashMap<>();

    public static class MatchedView {
        private WeakReference<View> view;
        private String viewMapKey;

        public MatchedView(View view, String str) {
            this.view = new WeakReference<>(view);
            this.viewMapKey = str;
        }

        @Nullable
        public View getView() {
            if (this.view == null) {
                return null;
            }
            return this.view.get();
        }

        public String getViewMapKey() {
            return this.viewMapKey;
        }
    }

    protected static class ViewMatcher implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, Runnable {
        private final String activityName;
        private HashMap<String, String> delegateMap;

        @Nullable
        private List<EventBinding> eventBindings;
        private final Handler handler;
        private WeakReference<View> rootView;

        public ViewMatcher(View view, Handler handler, HashMap<String, String> map, String str) {
            this.rootView = new WeakReference<>(view);
            this.handler = handler;
            this.delegateMap = map;
            this.activityName = str;
            this.handler.postDelayed(this, 200L);
        }

        private void attachListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            if (eventBinding == null) {
                return;
            }
            try {
                View view2 = matchedView.getView();
                if (view2 != null) {
                    String viewMapKey = matchedView.getViewMapKey();
                    View.AccessibilityDelegate existingDelegate = ViewHierarchy.getExistingDelegate(view2);
                    boolean z = existingDelegate != null;
                    boolean z2 = z && (existingDelegate instanceof CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate);
                    boolean z3 = z2 && ((CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate) existingDelegate).getSupportCodelessLogging();
                    if (this.delegateMap.containsKey(viewMapKey)) {
                        return;
                    }
                    if (z && z2 && z3) {
                        return;
                    }
                    view2.setAccessibilityDelegate(CodelessLoggingEventListener.getAccessibilityDelegate(eventBinding, view, view2));
                    this.delegateMap.put(viewMapKey, eventBinding.getEventName());
                }
            } catch (FacebookException e) {
                Log.e(CodelessMatcher.TAG, "Failed to attach auto logging event listener.", e);
            }
        }

        public static List<MatchedView> findViewByPath(EventBinding eventBinding, View view, List<PathComponent> list, int i, int i2, String str) {
            String str2 = str + CodelessMatcher.CURRENT_CLASS_NAME + String.valueOf(i2);
            ArrayList arrayList = new ArrayList();
            if (view != null) {
                if (i >= list.size()) {
                    arrayList.add(new MatchedView(view, str2));
                } else {
                    PathComponent pathComponent = list.get(i);
                    if (pathComponent.className.equals(CodelessMatcher.PARENT_CLASS_NAME)) {
                        ViewParent parent = view.getParent();
                        if (parent instanceof ViewGroup) {
                            List<View> listFindVisibleChildren = findVisibleChildren((ViewGroup) parent);
                            int size = listFindVisibleChildren.size();
                            for (int i3 = 0; i3 < size; i3++) {
                                arrayList.addAll(findViewByPath(eventBinding, listFindVisibleChildren.get(i3), list, i + 1, i3, str2));
                            }
                        }
                    } else if (pathComponent.className.equals(CodelessMatcher.CURRENT_CLASS_NAME)) {
                        arrayList.add(new MatchedView(view, str2));
                    } else if (isTheSameView(view, pathComponent, i2)) {
                        if (i == list.size() - 1) {
                            arrayList.add(new MatchedView(view, str2));
                        }
                    }
                }
                if (view instanceof ViewGroup) {
                    List<View> listFindVisibleChildren2 = findVisibleChildren((ViewGroup) view);
                    int size2 = listFindVisibleChildren2.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        arrayList.addAll(findViewByPath(eventBinding, listFindVisibleChildren2.get(i4), list, i + 1, i4, str2));
                    }
                }
            }
            return arrayList;
        }

        private static List<View> findVisibleChildren(ViewGroup viewGroup) {
            ArrayList arrayList = new ArrayList();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    arrayList.add(childAt);
                }
            }
            return arrayList;
        }

        private static boolean isTheSameView(View view, PathComponent pathComponent, int i) {
            if (pathComponent.index != -1 && i != pathComponent.index) {
                return false;
            }
            if (!view.getClass().getCanonicalName().equals(pathComponent.className)) {
                if (!pathComponent.className.matches(".*android\\..*")) {
                    return false;
                }
                String[] strArrSplit = pathComponent.className.split("\\.");
                if (strArrSplit.length <= 0) {
                    return false;
                }
                if (!view.getClass().getSimpleName().equals(strArrSplit[strArrSplit.length - 1])) {
                    return false;
                }
            }
            if ((pathComponent.matchBitmask & PathComponent.MatchBitmaskType.ID.getValue()) > 0 && pathComponent.id != view.getId()) {
                return false;
            }
            if ((pathComponent.matchBitmask & PathComponent.MatchBitmaskType.TEXT.getValue()) > 0 && !pathComponent.text.equals(ViewHierarchy.getTextOfView(view))) {
                return false;
            }
            if ((pathComponent.matchBitmask & PathComponent.MatchBitmaskType.DESCRIPTION.getValue()) > 0) {
                if (!pathComponent.description.equals(view.getContentDescription() == null ? "" : String.valueOf(view.getContentDescription()))) {
                    return false;
                }
            }
            if ((pathComponent.matchBitmask & PathComponent.MatchBitmaskType.HINT.getValue()) > 0 && !pathComponent.hint.equals(ViewHierarchy.getHintOfView(view))) {
                return false;
            }
            if ((pathComponent.matchBitmask & PathComponent.MatchBitmaskType.TAG.getValue()) > 0) {
                if (!pathComponent.tag.equals(view.getTag() == null ? "" : String.valueOf(view.getTag()))) {
                    return false;
                }
            }
            return true;
        }

        private void startMatch() {
            if (this.eventBindings == null || this.rootView.get() == null) {
                return;
            }
            for (int i = 0; i < this.eventBindings.size(); i++) {
                findView(this.eventBindings.get(i), this.rootView.get());
            }
        }

        public void findView(EventBinding eventBinding, View view) {
            if (eventBinding == null || view == null) {
                return;
            }
            if (TextUtils.isEmpty(eventBinding.getActivityName()) || eventBinding.getActivityName().equals(this.activityName)) {
                List<PathComponent> viewPath = eventBinding.getViewPath();
                if (viewPath.size() <= 25) {
                    Iterator<MatchedView> it = findViewByPath(eventBinding, view, viewPath, 0, -1, this.activityName).iterator();
                    while (it.hasNext()) {
                        attachListener(it.next(), view, eventBinding);
                    }
                }
            }
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            startMatch();
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            startMatch();
        }

        @Override // java.lang.Runnable
        public void run() {
            View view;
            FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
            if (appSettingsWithoutQuery == null || !appSettingsWithoutQuery.getCodelessEventsEnabled()) {
                return;
            }
            this.eventBindings = EventBinding.parseArray(appSettingsWithoutQuery.getEventBindings());
            if (this.eventBindings == null || (view = this.rootView.get()) == null) {
                return;
            }
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(this);
                viewTreeObserver.addOnScrollChangedListener(this);
            }
            startMatch();
        }
    }

    public static Bundle getParameters(EventBinding eventBinding, View view, View view2) {
        List<ParameterComponent> viewParameters;
        Bundle bundle = new Bundle();
        if (eventBinding != null && (viewParameters = eventBinding.getViewParameters()) != null) {
            for (ParameterComponent parameterComponent : viewParameters) {
                if (parameterComponent.value != null && parameterComponent.value.length() > 0) {
                    bundle.putString(parameterComponent.name, parameterComponent.value);
                } else if (parameterComponent.path.size() > 0) {
                    for (MatchedView matchedView : parameterComponent.pathType.equals(Constants.PATH_TYPE_RELATIVE) ? ViewMatcher.findViewByPath(eventBinding, view2, parameterComponent.path, 0, -1, view2.getClass().getSimpleName()) : ViewMatcher.findViewByPath(eventBinding, view, parameterComponent.path, 0, -1, view.getClass().getSimpleName())) {
                        if (matchedView.getView() != null) {
                            String textOfView = ViewHierarchy.getTextOfView(matchedView.getView());
                            if (textOfView.length() > 0) {
                                bundle.putString(parameterComponent.name, textOfView);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void matchViews() {
        for (Activity activity : this.activitiesSet) {
            this.viewMatchers.add(new ViewMatcher(activity.getWindow().getDecorView().getRootView(), this.uiThreadHandler, this.delegateMap, activity.getClass().getSimpleName()));
        }
    }

    private void startTracking() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            matchViews();
        } else {
            this.uiThreadHandler.post(new Runnable() { // from class: com.facebook.appevents.codeless.CodelessMatcher.1
                @Override // java.lang.Runnable
                public void run() {
                    CodelessMatcher.this.matchViews();
                }
            });
        }
    }

    public void add(Activity activity) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new FacebookException("Can't add activity to CodelessMatcher on non-UI thread");
        }
        this.activitiesSet.add(activity);
        this.delegateMap.clear();
        startTracking();
    }

    public void remove(Activity activity) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new FacebookException("Can't remove activity from CodelessMatcher on non-UI thread");
        }
        this.activitiesSet.remove(activity);
        this.viewMatchers.clear();
        this.delegateMap.clear();
    }
}
