package com.facebook.appevents.codeless.internal;

import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ViewHierarchy {
    private static final int ADAPTER_VIEW_ITEM_BITMASK = 9;
    private static final int BUTTON_BITMASK = 2;
    private static final int CHECKBOX_BITMASK = 15;
    private static final String CHILDREN_VIEW_KEY = "childviews";
    private static final String CLASS_NAME_KEY = "classname";
    private static final String CLASS_TYPE_BITMASK_KEY = "classtypebitmask";
    private static final int CLICKABLE_VIEW_BITMASK = 5;
    private static final String DESC_KEY = "description";
    private static final String DIMENSION_HEIGHT_KEY = "height";
    private static final String DIMENSION_KEY = "dimension";
    private static final String DIMENSION_LEFT_KEY = "left";
    private static final String DIMENSION_SCROLL_X_KEY = "scrollx";
    private static final String DIMENSION_SCROLL_Y_KEY = "scrolly";
    private static final String DIMENSION_TOP_KEY = "top";
    private static final String DIMENSION_VISIBILITY_KEY = "visibility";
    private static final String DIMENSION_WIDTH_KEY = "width";
    private static final String GET_ACCESSIBILITY_METHOD = "getAccessibilityDelegate";
    private static final String HINT_KEY = "hint";
    private static final String ID_KEY = "id";
    private static final int IMAGEVIEW_BITMASK = 1;
    private static final int INPUT_BITMASK = 11;
    private static final int LABEL_BITMASK = 10;
    private static final int PICKER_BITMASK = 12;
    private static final int RADIO_GROUP_BITMASK = 14;
    private static final int RATINGBAR_BITMASK = 16;
    private static final int REACT_NATIVE_BUTTON_BITMASK = 6;
    private static final int SWITCH_BITMASK = 13;
    private static final String TAG = ViewHierarchy.class.getCanonicalName();
    private static final String TAG_KEY = "tag";
    private static final int TEXTVIEW_BITMASK = 0;
    private static final String TEXT_KEY = "text";

    public static List<View> getChildrenOfView(View view) {
        ArrayList arrayList = new ArrayList();
        if (view != null && (view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                arrayList.add(viewGroup.getChildAt(i));
            }
        }
        return arrayList;
    }

    private static int getClassTypeBitmask(View view) {
        int i = view instanceof ImageView ? 0 | 2 : 0;
        if (isClickableView(view)) {
            i |= 32;
        }
        if (isAdapterViewItem(view)) {
            i |= 512;
        }
        if (view instanceof TextView) {
            int i2 = i | 1024 | 1;
            if (view instanceof Button) {
                i2 |= 4;
                if (view instanceof Switch) {
                    i2 |= 8192;
                } else if (view instanceof CheckBox) {
                    i2 |= 32768;
                }
            }
            return view instanceof EditText ? i2 | 2048 : i2;
        }
        if ((view instanceof Spinner) || (view instanceof DatePicker)) {
            return i | 4096;
        }
        if (view instanceof RatingBar) {
            return i | 65536;
        }
        if (view instanceof RadioGroup) {
            return i | 16384;
        }
        return ((view instanceof ViewGroup) && view.getClass().getName().equals("com.facebook.react.views.view.ReactViewGroup") && getExistingDelegate(view) != null) ? i | 64 : i;
    }

    public static JSONObject getDictionaryOfView(View view) {
        JSONObject jSONObject = new JSONObject();
        try {
            String textOfView = getTextOfView(view);
            String hintOfView = getHintOfView(view);
            Object tag = view.getTag();
            CharSequence contentDescription = view.getContentDescription();
            jSONObject.put(CLASS_NAME_KEY, view.getClass().getCanonicalName());
            jSONObject.put(CLASS_TYPE_BITMASK_KEY, getClassTypeBitmask(view));
            jSONObject.put("id", view.getId());
            if (SensitiveUserDataUtils.isSensitiveUserData(view)) {
                jSONObject.put(TEXT_KEY, "");
            } else {
                jSONObject.put(TEXT_KEY, textOfView);
            }
            jSONObject.put(HINT_KEY, hintOfView);
            if (tag != null) {
                jSONObject.put(TAG_KEY, tag.toString());
            }
            if (contentDescription != null) {
                jSONObject.put("description", contentDescription.toString());
            }
            jSONObject.put(DIMENSION_KEY, getDimensionOfView(view));
            JSONArray jSONArray = new JSONArray();
            List<View> childrenOfView = getChildrenOfView(view);
            for (int i = 0; i < childrenOfView.size(); i++) {
                jSONArray.put(getDictionaryOfView(childrenOfView.get(i)));
            }
            jSONObject.put(CHILDREN_VIEW_KEY, jSONArray);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for view.", e);
        }
        return jSONObject;
    }

    private static JSONObject getDimensionOfView(View view) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("top", view.getTop());
            jSONObject.put("left", view.getLeft());
            jSONObject.put("width", view.getWidth());
            jSONObject.put("height", view.getHeight());
            jSONObject.put(DIMENSION_SCROLL_X_KEY, view.getScrollX());
            jSONObject.put(DIMENSION_SCROLL_Y_KEY, view.getScrollY());
            jSONObject.put(DIMENSION_VISIBILITY_KEY, view.getVisibility());
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for dimension.", e);
        }
        return jSONObject;
    }

    @Nullable
    public static View.AccessibilityDelegate getExistingDelegate(View view) {
        try {
            return (View.AccessibilityDelegate) view.getClass().getMethod(GET_ACCESSIBILITY_METHOD, new Class[0]).invoke(view, new Object[0]);
        } catch (IllegalAccessException e) {
            return null;
        } catch (NoSuchMethodException e2) {
            return null;
        } catch (NullPointerException e3) {
            return null;
        } catch (SecurityException e4) {
            return null;
        } catch (InvocationTargetException e5) {
            return null;
        }
    }

    public static String getHintOfView(View view) {
        CharSequence hint = null;
        if (view instanceof TextView) {
            hint = ((TextView) view).getHint();
        } else if (view instanceof EditText) {
            hint = ((EditText) view).getHint();
        }
        return hint == null ? "" : hint.toString();
    }

    @Nullable
    public static ViewGroup getParentOfView(View view) {
        if (view == null) {
            return null;
        }
        ViewParent parent = view.getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) parent;
    }

    public static String getTextOfView(View view) {
        CharSequence charSequenceValueOf = null;
        if (view instanceof TextView) {
            charSequenceValueOf = ((TextView) view).getText();
            if (view instanceof Switch) {
                charSequenceValueOf = ((Switch) view).isChecked() ? "1" : "0";
            }
        } else if (view instanceof Spinner) {
            Object selectedItem = ((Spinner) view).getSelectedItem();
            if (selectedItem != null) {
                charSequenceValueOf = selectedItem.toString();
            }
        } else if (view instanceof DatePicker) {
            DatePicker datePicker = (DatePicker) view;
            charSequenceValueOf = String.format("%04d-%02d-%02d", Integer.valueOf(datePicker.getYear()), Integer.valueOf(datePicker.getMonth()), Integer.valueOf(datePicker.getDayOfMonth()));
        } else if (view instanceof TimePicker) {
            TimePicker timePicker = (TimePicker) view;
            charSequenceValueOf = String.format("%02d:%02d", Integer.valueOf(timePicker.getCurrentHour().intValue()), Integer.valueOf(timePicker.getCurrentMinute().intValue()));
        } else if (view instanceof RadioGroup) {
            RadioGroup radioGroup = (RadioGroup) view;
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            int childCount = radioGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = radioGroup.getChildAt(i);
                if (childAt.getId() == checkedRadioButtonId && (childAt instanceof RadioButton)) {
                    charSequenceValueOf = ((RadioButton) childAt).getText();
                    break;
                }
            }
        } else if (view instanceof RatingBar) {
            charSequenceValueOf = String.valueOf(((RatingBar) view).getRating());
        }
        return charSequenceValueOf == null ? "" : charSequenceValueOf.toString();
    }

    private static boolean isAdapterViewItem(View view) {
        ViewParent parent = view.getParent();
        return parent != null && ((parent instanceof AdapterView) || (parent instanceof NestedScrollingChild));
    }

    public static boolean isClickableView(View view) {
        try {
            Field declaredField = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField != null) {
                declaredField.setAccessible(true);
            }
            Object obj = declaredField.get(view);
            if (obj == null) {
                return false;
            }
            Field declaredField2 = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener");
            return (declaredField2 != null ? (View.OnClickListener) declaredField2.get(obj) : null) != null;
        } catch (Exception e) {
            Log.e(TAG, "Failed to check if the view is clickable.", e);
            return false;
        }
    }
}
