package com.naver.glink.android.sdk.ui.profile.image;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Toast;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.d;
import com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView;
import java.io.File;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ImageEditDialogFragmentView extends DialogFragmentView {
    private ImageEditView a;
    private String b;
    private int c;
    private int d;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;
    private View n;
    private View o;
    private View p;
    private ImageEditView.ImageType q;
    private a r;

    public interface a {
        void a();

        void a(String str);
    }

    class b extends AsyncTask<Void, Integer, String> {
        private Bitmap b;

        b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String doInBackground(Void... voidArr) throws Throwable {
            if (this.b == null) {
                return null;
            }
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + com.naver.glink.android.sdk.ui.profile.image.a.b);
            if (!file.exists()) {
                file.mkdirs();
            }
            String str = ImageEditDialogFragmentView.this.b;
            com.naver.glink.android.sdk.ui.profile.image.a.a(str, this.b);
            return str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (ImageEditDialogFragmentView.this.r != null) {
                ImageEditDialogFragmentView.this.r.a(str);
            }
            ImageEditDialogFragmentView.this.dismiss();
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            this.b = ImageEditDialogFragmentView.this.a.getMergedBitmap();
        }
    }

    public ImageEditDialogFragmentView(Context context) {
        super(context);
    }

    public static ImageEditDialogFragmentView a(Context context, String str, a aVar) {
        Bundle bundle = new Bundle();
        ImageEditDialogFragmentView imageEditDialogFragmentView = new ImageEditDialogFragmentView(context);
        imageEditDialogFragmentView.setArguments(bundle);
        imageEditDialogFragmentView.b = str;
        imageEditDialogFragmentView.r = aVar;
        return imageEditDialogFragmentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        Bitmap bitmapA = com.naver.glink.android.sdk.ui.profile.image.a.a(getContext(), str);
        if (bitmapA == null) {
            Toast.makeText(getContext(), getContext().getString(R.string.photo_upload_error), 0).show();
            dismiss();
        } else {
            setBitmapImage(bitmapA);
            this.a.e();
            this.j.invalidate();
            new Handler().postDelayed(new Runnable() { // from class: com.naver.glink.android.sdk.ui.profile.image.ImageEditDialogFragmentView.8
                @Override // java.lang.Runnable
                public void run() {
                    ImageEditDialogFragmentView.this.i.setVisibility(0);
                    ImageEditDialogFragmentView.this.k.performClick();
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        new b().execute(new Void[0]);
    }

    private void setBitmapImage(Bitmap bitmap) {
        this.a.setBitmap(bitmap);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    @Nullable
    public View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_image_edit, (ViewGroup) null, false);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(Context context) {
        super.a(context);
        setBackgroundColor(getResources().getColor(R.color.viewer_bg));
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView
    public void a(DialogInterface dialogInterface) {
        super.a(dialogInterface);
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a(View view, @Nullable Bundle bundle) {
        super.a(view, bundle);
        if (this.b == null) {
            dismiss();
            return;
        }
        this.q = ImageEditView.ImageType.findImageType(ImageEditView.b);
        this.c = this.q.getAspectX();
        this.d = this.q.getAspectY();
        this.i = view.findViewById(R.id.layoutBg);
        this.j = view.findViewById(R.id.layoutImageEdit);
        this.a = (ImageEditView) view.findViewById(R.id.imgDrawingPicture);
        this.a.setType(this.q);
        this.i.setVisibility(4);
        this.k = view.findViewById(R.id.tvSetCrop);
        this.l = view.findViewById(R.id.tvDoCrop);
        this.m = view.findViewById(R.id.tvCancelCrop);
        this.n = view.findViewById(R.id.tvSave);
        this.o = view.findViewById(R.id.tvClose);
        this.p = view.findViewById(R.id.tvRotate);
        final View viewFindViewById = view.findViewById(R.id.layoutBottomCropMenu);
        final View viewFindViewById2 = view.findViewById(R.id.layoutBottomMenu);
        final View viewFindViewById3 = view.findViewById(R.id.layoutTopMenu);
        this.k.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.image.ImageEditDialogFragmentView.1
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                if (!ImageEditDialogFragmentView.this.a.f()) {
                    Toast.makeText(ImageEditDialogFragmentView.this.getContext(), ImageEditDialogFragmentView.this.getContext().getString(R.string.photo_upload_error), 0).show();
                    return;
                }
                ImageEditDialogFragmentView.this.a.setEditMode(3001);
                viewFindViewById.setVisibility(0);
                viewFindViewById2.setVisibility(8);
                viewFindViewById3.setVisibility(4);
            }
        });
        this.l.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.image.ImageEditDialogFragmentView.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                ImageEditDialogFragmentView.this.a.b();
                viewFindViewById.setVisibility(8);
                viewFindViewById2.setVisibility(0);
                viewFindViewById3.setVisibility(0);
                ImageEditDialogFragmentView.this.n.setVisibility(0);
                ImageEditDialogFragmentView.this.a.setEditMode(2000);
            }
        });
        this.m.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.image.ImageEditDialogFragmentView.3
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                if (!ImageEditDialogFragmentView.this.a.d()) {
                    ImageEditDialogFragmentView.this.dismiss();
                    return;
                }
                viewFindViewById.setVisibility(8);
                viewFindViewById2.setVisibility(0);
                viewFindViewById3.setVisibility(0);
                ImageEditDialogFragmentView.this.a.setEditMode(2000);
            }
        });
        this.n.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.image.ImageEditDialogFragmentView.4
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                if (ImageEditDialogFragmentView.this.a.d()) {
                    ImageEditDialogFragmentView.this.e();
                } else {
                    ImageEditDialogFragmentView.this.c();
                }
            }
        });
        this.o.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.profile.image.ImageEditDialogFragmentView.5
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                ImageEditDialogFragmentView.this.dismiss();
            }
        });
        this.p.setOnClickListener(new View.OnClickListener() { // from class: com.naver.glink.android.sdk.ui.profile.image.ImageEditDialogFragmentView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ImageEditDialogFragmentView.this.a.a();
            }
        });
        if (bundle == null) {
            this.a.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.naver.glink.android.sdk.ui.profile.image.ImageEditDialogFragmentView.7
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    ImageEditDialogFragmentView.this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    ImageEditDialogFragmentView.this.a.setCropRectAspects(ImageEditDialogFragmentView.this.c, ImageEditDialogFragmentView.this.d);
                    ImageEditDialogFragmentView.this.b(ImageEditDialogFragmentView.this.b);
                }
            });
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView
    protected void a(WindowManager.LayoutParams layoutParams) {
        super.a(layoutParams);
        if (layoutParams == null) {
            return;
        }
        layoutParams.width = -1;
        layoutParams.height = -1;
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, com.naver.glink.android.sdk.ui.parent.plugfragment.FragmentView
    public void a_() {
        this.a.g();
        super.a_();
    }

    public void c() {
        dismiss();
        if (this.r != null) {
            this.r.a();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, android.content.DialogInterface
    public void cancel() {
        dismiss();
        if (this.r != null) {
            this.r.a();
        }
    }

    @Override // com.naver.glink.android.sdk.ui.parent.plugfragment.DialogFragmentView, android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        if (!d.h() || configuration.orientation == getWindowParam().screenOrientation) {
            return;
        }
        super.onConfigurationChanged(configuration);
    }
}
