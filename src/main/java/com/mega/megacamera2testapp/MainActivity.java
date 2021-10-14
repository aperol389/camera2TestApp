package com.mega.megacamera2testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MegaCamera2TestApp";

    private TextureView mTextureView_0;
    private TextureView mTextureView_1;
    private TextureView mTextureView_2;
    private TextureView mTextureView_3;
    private TextureView mTextureView_4;
    private TextureView mTextureView_5;
    private TextureView mTextureView_6;
    private TextureView mTextureView_7;

    private TextView mTextView_camerasInfo;

    private View mExitBtn;

    private View mPhotoBtn0;

    private Size mPreviewSize_0;
    private Size mPreviewSize_1;
    private Size mPreviewSize_2;
    private Size mPreviewSize_3;
    private Size mPreviewSize_4;
    private Size mPreviewSize_5;
    private Size mPreviewSize_6;
    private Size mPreviewSize_7;

    private Handler mBackgroundHandler;

    private ImageReader mImageReader;

    private File mFile;

    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener
            = new ImageReader.OnImageAvailableListener() {
        @Override
        public void onImageAvailable(ImageReader reader) {
        }
    };

    private TextureView.SurfaceTextureListener mSurfaceTextureListener
            = new TextureView.SurfaceTextureListener() {

        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface,
                                              int width, int height) {
            try {
                openCamera();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface,
                                                int width, int height) {}

        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {return false;}

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {}
    };

    private class ButtonClickListener implements View.OnClickListener {
        public void onClick(View v) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextureView_0 = findViewById(R.id.camera_0);
        mTextureView_1 = findViewById(R.id.camera_1);
        mTextureView_2 = findViewById(R.id.camera_2);
        mTextureView_3 = findViewById(R.id.camera_3);
        mTextureView_4 = findViewById(R.id.camera_4);
        mTextureView_5 = findViewById(R.id.camera_5);
        mTextureView_6 = findViewById(R.id.camera_6);
        mTextureView_7 = findViewById(R.id.camera_7);

        mTextView_camerasInfo = findViewById(R.id.camerasInfo);

        mExitBtn = findViewById(R.id.exitAppButton);
        mExitBtn.setOnClickListener(new ButtonClickListener());
    }

    @Override
    public void onResume() {
        super.onResume();
        mTextureView_0.setSurfaceTextureListener(mSurfaceTextureListener);
    }

    private void setUpCaptureRequestBuilder(@NotNull CaptureRequest.Builder builder) {
        builder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
    }

    private void startPreview(@org.jetbrains.annotations.NotNull CameraDevice cameraDevice) {
        String cameraId = cameraDevice.getId();
        final CaptureRequest.Builder previewBuilder;
        Surface previewSurface;
        switch (cameraId) {
            case "0":
                mTextureView_0.getSurfaceTexture().setDefaultBufferSize(mPreviewSize_0.getWidth(),
                        mPreviewSize_0.getHeight());
                previewSurface = new Surface(mTextureView_0.getSurfaceTexture());
                break;
            case "1":
                mTextureView_1.getSurfaceTexture().setDefaultBufferSize(mPreviewSize_1.getWidth(),
                        mPreviewSize_1.getHeight());
                previewSurface = new Surface(mTextureView_1.getSurfaceTexture());
                break;
            case "2":
                mTextureView_2.getSurfaceTexture().setDefaultBufferSize(mPreviewSize_2.getWidth(),
                        mPreviewSize_2.getHeight());
                previewSurface = new Surface(mTextureView_2.getSurfaceTexture());
                break;
            case "3":
                mTextureView_3.getSurfaceTexture().setDefaultBufferSize(mPreviewSize_3.getWidth(),
                        mPreviewSize_3.getHeight());
                previewSurface = new Surface(mTextureView_3.getSurfaceTexture());
                break;
            case "4":
                mTextureView_4.getSurfaceTexture().setDefaultBufferSize(mPreviewSize_4.getWidth(),
                        mPreviewSize_4.getHeight());
                previewSurface = new Surface(mTextureView_4.getSurfaceTexture());
                break;
            case "5":
                mTextureView_5.getSurfaceTexture().setDefaultBufferSize(mPreviewSize_5.getWidth(),
                        mPreviewSize_5.getHeight());
                previewSurface = new Surface(mTextureView_5.getSurfaceTexture());
                break;
            case "6":
                mTextureView_6.getSurfaceTexture().setDefaultBufferSize(mPreviewSize_6.getWidth(),
                        mPreviewSize_6.getHeight());
                previewSurface = new Surface(mTextureView_6.getSurfaceTexture());
                break;
            case "7":
                mTextureView_7.getSurfaceTexture().setDefaultBufferSize(mPreviewSize_7.getWidth(),
                        mPreviewSize_7.getHeight());
                previewSurface = new Surface(mTextureView_7.getSurfaceTexture());
                break;
            default:
                Log.d(TAG,"can not out of 15, default as camera_0");
                mTextureView_0.getSurfaceTexture().setDefaultBufferSize(mPreviewSize_0.getWidth(),
                        mPreviewSize_0.getHeight());
                previewSurface = new Surface(mTextureView_0.getSurfaceTexture());
                break;
        }

        try {
            previewBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            previewBuilder.addTarget(previewSurface);
            cameraDevice.createCaptureSession(Collections.singletonList(previewSurface),
                new CameraCaptureSession.StateCallback() {

                    @Override
                    public void onConfigured(@NonNull CameraCaptureSession session) {
                        try {
                            setUpCaptureRequestBuilder(previewBuilder);
                            session.setRepeatingRequest(previewBuilder.build(),
                                    null, mBackgroundHandler);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                    }
                }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {

        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            startPreview(cameraDevice);
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            cameraDevice.close();
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int error) {
            cameraDevice.close();
        }
    };

    private void openCamera() throws CameraAccessException {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        CameraCharacteristics characteristics;
        StreamConfigurationMap map;
        String camera;

        int camera_num = manager.getCameraIdList().length;
        Log.d(TAG,"number of cameras:" + camera_num);
        mTextView_camerasInfo.append("\ntotal cameras: " + camera_num + "\n");

        for (int i=0; i<camera_num; i++) {
            camera = manager.getCameraIdList()[i];
            characteristics = manager.getCameraCharacteristics(camera);
            map = characteristics
                    .get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            int size_num = map.getOutputSizes(SurfaceTexture.class).length;
            mTextView_camerasInfo.append("  camera_ID: " + i  + "  num of output sizes: " + size_num + "\n");

            for (int j=0; j<size_num; j++) {
                mTextView_camerasInfo.append("    size_index: " + j);
                mTextView_camerasInfo.append("  width: " +
                        map.getOutputSizes(SurfaceTexture.class)[j].getWidth() +
                        "  height: " +
                        map.getOutputSizes(SurfaceTexture.class)[j].getHeight() + "\n");
            }
            switch (i) {
                case 0:
                    mPreviewSize_0 = map.getOutputSizes(SurfaceTexture.class)[0];
                    break;
                case 1:
                    mPreviewSize_1 = map.getOutputSizes(SurfaceTexture.class)[0];
                    break;
                case 2:
                    mPreviewSize_2 = map.getOutputSizes(SurfaceTexture.class)[0];
                    break;
                case 3:
                    mPreviewSize_3 = map.getOutputSizes(SurfaceTexture.class)[0];
                    break;
                case 4:
                    mPreviewSize_4 = map.getOutputSizes(SurfaceTexture.class)[0];
                    break;
                case 5:
                    mPreviewSize_5 = map.getOutputSizes(SurfaceTexture.class)[0];
                    break;
                case 6:
                    mPreviewSize_6 = map.getOutputSizes(SurfaceTexture.class)[0];
                    break;
                case 7:
                    mPreviewSize_7 = map.getOutputSizes(SurfaceTexture.class)[0];
                    break;
                default:
                    Log.d(TAG, "can not out of camera_15");
            }

            manager.openCamera(camera, mStateCallback, null);
        }
    }
}