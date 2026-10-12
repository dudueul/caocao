package com.naver.glink.android.sdk.ui.streaming.b;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager;

/* JADX INFO: compiled from: CameraPreview.java */
/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@TargetApi(21)
class b {
    private static final int a = 1280;
    private static final String b = "CameraPreview";
    private Activity c;
    private TextureView d;
    private HandlerThread e;
    private Handler f;
    private Semaphore g = new Semaphore(1);
    private CameraDevice h;
    private CameraCaptureSession i;
    private a j;

    /* JADX INFO: compiled from: CameraPreview.java */
    private static class a {
        final String a;
        final Size[] b;

        private a(String str, Size[] sizeArr) {
            this.a = str;
            this.b = sizeArr;
        }

        private Matrix a(int i, int i2, int i3) {
            Size sizeA = com.naver.glink.android.sdk.ui.streaming.b.a.a(this.b, i2, i3, 1280, 1280, com.naver.glink.android.sdk.ui.streaming.b.a.a(Arrays.asList(this.b)));
            Matrix matrix = new Matrix();
            RectF rectF = new RectF(0.0f, 0.0f, i2, i3);
            RectF rectF2 = new RectF(0.0f, 0.0f, sizeA.getHeight(), sizeA.getWidth());
            float fCenterX = rectF.centerX();
            float fCenterY = rectF.centerY();
            if (i == 1 || i == 3) {
                rectF2.offset(fCenterX - rectF2.centerX(), fCenterY - rectF2.centerY());
                matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
                float fMax = Math.max(i3 / sizeA.getHeight(), i2 / sizeA.getWidth());
                matrix.postScale(fMax, fMax, fCenterX, fCenterY);
                matrix.postRotate((i - 2) * 90, fCenterX, fCenterY);
            } else if (i == 2) {
                matrix.postRotate(180.0f, fCenterX, fCenterY);
            }
            return matrix;
        }

        static a a(Activity activity) {
            StreamConfigurationMap streamConfigurationMap;
            try {
                CameraManager cameraManager = (CameraManager) activity.getSystemService("camera");
                for (String str : cameraManager.getCameraIdList()) {
                    CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
                    Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                    if (num != null && num.intValue() == 0 && (streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)) != null) {
                        return new a(str, streamConfigurationMap.getOutputSizes(SurfaceTexture.class));
                    }
                }
            } catch (CameraAccessException e) {
                Log.d("live", "", e);
            }
            return null;
        }

        private void a(TextureView textureView) {
            Size size = null;
            try {
                for (Size size2 : this.b) {
                    if ((size == null || size.getWidth() <= size2.getWidth()) && size2.getWidth() <= 1280) {
                        size = new Size(size2.getWidth(), size2.getHeight());
                    }
                }
                if (size != null) {
                    textureView.getSurfaceTexture().setDefaultBufferSize(size.getWidth(), size.getHeight());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void a(Activity activity, TextureView textureView, int i, int i2) {
            if (activity == null || textureView == null) {
                return;
            }
            textureView.setTransform(a(activity.getWindowManager().getDefaultDisplay().getRotation(), i, i2));
            if (Build.VERSION.SDK_INT <= 21) {
                a(textureView);
            }
        }
    }

    b(Activity activity, TextureView textureView) {
        this.c = activity;
        this.d = textureView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CaptureRequest a(Surface surface) throws CameraAccessException {
        CaptureRequest.Builder builderCreateCaptureRequest = this.h.createCaptureRequest(1);
        builderCreateCaptureRequest.addTarget(surface);
        builderCreateCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, 4);
        return builderCreateCaptureRequest.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        if (this.c == null || ActivityCompat.checkSelfPermission(this.c, NXRuntimePermissionManager.CAMERA) != 0) {
            return;
        }
        this.j = a.a(this.c);
        if (this.j != null) {
            try {
                this.j.a(this.c, this.d, i, i2);
                if (this.g.tryAcquire(2500L, TimeUnit.MILLISECONDS)) {
                    ((CameraManager) this.c.getSystemService("camera")).openCamera(this.j.a, new CameraDevice.StateCallback() { // from class: com.naver.glink.android.sdk.ui.streaming.b.b.2
                        @Override // android.hardware.camera2.CameraDevice.StateCallback
                        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
                            b.this.g.release();
                            cameraDevice.close();
                            b.this.h = null;
                        }

                        @Override // android.hardware.camera2.CameraDevice.StateCallback
                        public void onError(@NonNull CameraDevice cameraDevice, int i3) {
                            b.this.g.release();
                            cameraDevice.close();
                            b.this.h = null;
                        }

                        @Override // android.hardware.camera2.CameraDevice.StateCallback
                        public void onOpened(@NonNull CameraDevice cameraDevice) {
                            b.this.g.release();
                            b.this.h = cameraDevice;
                            b.this.c();
                        }
                    }, this.f);
                }
            } catch (Exception e) {
                Log.d(b, "", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        try {
            final Surface surface = new Surface(this.d.getSurfaceTexture());
            this.h.createCaptureSession(Collections.singletonList(surface), new CameraCaptureSession.StateCallback() { // from class: com.naver.glink.android.sdk.ui.streaming.b.b.3
                @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                }

                @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    if (b.this.h == null) {
                        return;
                    }
                    b.this.i = cameraCaptureSession;
                    try {
                        b.this.i.setRepeatingRequest(b.this.a(surface), null, b.this.f);
                    } catch (CameraAccessException e) {
                        Log.d(b.b, "", e);
                    }
                }
            }, null);
        } catch (CameraAccessException e) {
            Log.d(b, "", e);
        }
    }

    private void d() {
        this.e = new HandlerThread("CameraBackground");
        this.e.start();
        this.f = new Handler(this.e.getLooper());
    }

    private void e() {
        this.e.quitSafely();
        try {
            this.e.join();
            this.e = null;
            this.f = null;
        } catch (InterruptedException e) {
            Log.d(b, "", e);
        }
    }

    void a() {
        d();
        if (this.d.isAvailable()) {
            a(this.d.getWidth(), this.d.getHeight());
        } else {
            this.d.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.naver.glink.android.sdk.ui.streaming.b.b.1
                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                    b.this.a(i, i2);
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    return false;
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                    if (b.this.j != null) {
                        b.this.j.a(b.this.c, b.this.d, i, i2);
                    }
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                }
            });
        }
    }

    void b() {
        try {
            try {
                this.g.acquire();
                if (this.i != null) {
                    this.i.close();
                    this.i = null;
                }
                if (this.h != null) {
                    this.h.close();
                    this.h = null;
                }
                this.g.release();
            } catch (InterruptedException e) {
                Log.d(b, "", e);
                this.g.release();
            }
            e();
        } catch (Throwable th) {
            this.g.release();
            throw th;
        }
    }
}
