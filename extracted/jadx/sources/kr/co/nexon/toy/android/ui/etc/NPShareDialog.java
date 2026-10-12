package kr.co.nexon.toy.android.ui.etc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.npaccount.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kr.co.nexon.android.sns.facebook.NPFacebook;
import kr.co.nexon.mdev.android.util.NXActivityUtil;
import kr.co.nexon.mdev.android.view.dialog.NXPDialogFragment;
import kr.co.nexon.mdev.android.view.listener.NXClickListener;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.npaccount.auth.result.NXToyShareResult;
import kr.co.nexon.toy.android.ui.NPDialogBase;
import kr.co.nexon.toy.android.ui.etc.view.NXPShareView;
import kr.co.nexon.toy.listener.NPListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class NPShareDialog extends NPDialogBase {
    public static final String KEY_CONTENT = "content";
    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";
    public static final String TAG = "NPShareDialog";
    private String content;
    private NPListener resultListener;
    private NXPShareTypeListAdapter shareListAdapter;
    private NXClickListener shareSnsClickListener = new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPShareDialog.1
        @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
        protected void onSwallowClick(View view) {
            String packageName;
            int i = Integer.parseInt(view.getTag().toString());
            if (i == ShareType.SMS.getType()) {
                NPShareDialog.this.SMS(i);
                return;
            }
            if (i == ShareType.EMAIL.getType()) {
                NPShareDialog.this.Email(i);
                return;
            }
            if (i == ShareType.BAND.getType()) {
                packageName = ShareType.BAND.getPackageName();
            } else if (i == ShareType.LINE.getType()) {
                packageName = ShareType.LINE.getPackageName();
            } else if (i == ShareType.TWITTER.getType()) {
                packageName = ShareType.TWITTER.getPackageName();
            } else {
                packageName = i == ShareType.FACEBOOK.getType() ? ShareType.FACEBOOK.getPackageName() : "";
            }
            NPShareDialog.this.share(i, packageName);
        }
    };
    private List<ShareType> shareTypeButtonList;
    private NXPShareView shareView;
    private String title;
    private String url;

    private class NXPShareTypeListAdapter extends ArrayAdapter<ShareType> {
        private int layoutResId;

        public NXPShareTypeListAdapter(Context context, int i, List<ShareType> list) {
            super(context, i, list);
            this.layoutResId = i;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ShareType item = getItem(i);
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(this.layoutResId, viewGroup, false);
            }
            Button button = (Button) view;
            if (button != null && item != null) {
                button.setBackgroundResource(item.getResId());
                button.setTag(Integer.valueOf(item.getType()));
                button.setOnClickListener(NPShareDialog.this.shareSnsClickListener);
                button.setText(item.toString());
            }
            return view;
        }
    }

    public enum ShareType {
        BAND(3, "com.nhn.android.band", R.drawable.btn_share_band_selector),
        LINE(2, "jp.naver.line.android", R.drawable.btn_share_line_selector),
        TWITTER(1, "com.twitter.android", R.drawable.btn_share_tw_selector),
        FACEBOOK(0, "com.facebook.katana", R.drawable.btn_share_fb_selector),
        SMS(4, "sms", R.drawable.btn_share_sms_selector),
        EMAIL(5, "email", R.drawable.btn_share_email_selector);

        String packageName;
        int resId;
        int type;

        ShareType(int i, String str, int i2) {
            this.type = i;
            this.packageName = str;
            this.resId = i2;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public int getResId() {
            return this.resId;
        }

        public int getType() {
            return this.type;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Email(int i) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:"));
        intent.putExtra("android.intent.extra.TEXT", "[" + this.title + "]\n" + this.content + "\n" + this.url);
        startActivity(intent);
        if (this.resultListener != null) {
            NXToyShareResult nXToyShareResult = new NXToyShareResult(0, "", "");
            nXToyShareResult.result.shareAppNo = i;
            this.resultListener.onResult(nXToyShareResult);
        }
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SMS(int i) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
        intent.putExtra("sms_body", "[" + this.title + "]\n" + this.content + "\n" + this.url);
        startActivity(intent);
        if (this.resultListener != null) {
            NXToyShareResult nXToyShareResult = new NXToyShareResult(0, "", "");
            nXToyShareResult.result.shareAppNo = i;
            this.resultListener.onResult(nXToyShareResult);
        }
        dismiss();
    }

    public static NPShareDialog newInstance(Activity activity, String str, String str2, String str3) {
        NPShareDialog nPShareDialog = new NPShareDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(NXPDialogFragment.KEY_THEME, getToyDefaultTheme(activity));
        bundle.putString("title", str);
        bundle.putString("content", str2);
        bundle.putString("url", str3);
        nPShareDialog.setArguments(bundle);
        return nPShareDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void share(int i, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", "[" + this.title + "]\n" + this.content + "\n" + this.url);
        intent.setPackage(str);
        if (!NXActivityUtil.isIntentAvailable(getActivity(), intent)) {
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str));
            intent2.addFlags(536870912);
            getActivity().startActivity(intent2);
        } else if (str.equals(ShareType.FACEBOOK.getPackageName())) {
            new NPFacebook(getActivity()).share(getActivity(), getDialog().onSaveInstanceState(), this.title, this.content, this.url);
        } else {
            startActivity(intent);
        }
        if (this.resultListener != null) {
            NXToyShareResult nXToyShareResult = new NXToyShareResult(0, "", "");
            nXToyShareResult.result.shareAppNo = i;
            this.resultListener.onResult(nXToyShareResult);
        }
        dismiss();
    }

    @Override // kr.co.nexon.toy.android.ui.NPDialogBase
    public View createView() {
        NXPShareView nXPShareView = (NXPShareView) View.inflate(this.applicationContext, R.layout.nxp_share_view, null);
        this.title = getArguments().getString("title");
        this.content = getArguments().getString("content");
        this.shareTypeButtonList = new ArrayList();
        this.shareTypeButtonList.add(ShareType.LINE);
        this.shareTypeButtonList.add(ShareType.BAND);
        this.shareTypeButtonList.add(ShareType.SMS);
        this.shareTypeButtonList.add(ShareType.EMAIL);
        this.shareTypeButtonList.add(ShareType.FACEBOOK);
        this.shareTypeButtonList.add(ShareType.TWITTER);
        Collections.sort(this.shareTypeButtonList, new Comparator<ShareType>() { // from class: kr.co.nexon.toy.android.ui.etc.NPShareDialog.2
            @Override // java.util.Comparator
            public int compare(ShareType shareType, ShareType shareType2) {
                return shareType.getType() - shareType2.getType();
            }
        });
        NXToyLocaleManager nXToyLocaleManager = NXToyLocaleManager.getInstance(this.applicationContext);
        this.url = getArguments().getString("url");
        nXPShareView.setTitle(NXToyCommonPreferenceController.getInstance().getServiceTitle());
        nXPShareView.setDescriptionText(nXToyLocaleManager.getString(R.string.npshare_text));
        this.shareListAdapter = new NXPShareTypeListAdapter(getActivity(), R.layout.nxp_share_button, this.shareTypeButtonList);
        nXPShareView.setListAdapter(this.shareListAdapter);
        nXPShareView.setOnCloseButtonClickListener(new NXClickListener() { // from class: kr.co.nexon.toy.android.ui.etc.NPShareDialog.3
            @Override // kr.co.nexon.mdev.android.view.listener.NXClickListener
            public void onSwallowClick(View view) {
                NPShareDialog.this.dismiss();
            }
        });
        return nXPShareView;
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog = getDialog();
        if (dialog != null) {
            this.shareView = (NXPShareView) createView();
            dialog.setContentView(this.shareView);
        }
    }

    public void setResultListener(NPListener nPListener) {
        this.resultListener = nPListener;
    }
}
