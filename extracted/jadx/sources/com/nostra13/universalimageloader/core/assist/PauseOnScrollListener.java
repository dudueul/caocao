package com.nostra13.universalimageloader.core.assist;

import android.widget.AbsListView;
import com.nostra13.universalimageloader.core.ImageLoader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class PauseOnScrollListener implements AbsListView.OnScrollListener {
    private final AbsListView.OnScrollListener externalListener;
    private ImageLoader imageLoader;
    private final boolean pauseOnFling;
    private final boolean pauseOnScroll;

    public PauseOnScrollListener(ImageLoader imageLoader, boolean z, boolean z2) {
        this(imageLoader, z, z2, null);
    }

    public PauseOnScrollListener(ImageLoader imageLoader, boolean z, boolean z2, AbsListView.OnScrollListener onScrollListener) {
        this.imageLoader = imageLoader;
        this.pauseOnScroll = z;
        this.pauseOnFling = z2;
        this.externalListener = onScrollListener;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.externalListener != null) {
            this.externalListener.onScroll(absListView, i, i2, i3);
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                this.imageLoader.resume();
                break;
            case 1:
                if (this.pauseOnScroll) {
                    this.imageLoader.pause();
                }
                break;
            case 2:
                if (this.pauseOnFling) {
                    this.imageLoader.pause();
                }
                break;
        }
        if (this.externalListener != null) {
            this.externalListener.onScrollStateChanged(absListView, i);
        }
    }
}
