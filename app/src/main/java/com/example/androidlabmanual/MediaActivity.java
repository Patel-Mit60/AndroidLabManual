package com.example.androidlabmanual;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MediaActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 100;

    private ImageView imageView;
    private VideoView videoView;
    private Button btnAudio;
    private MediaPlayer mediaPlayer;

    private ActivityResultLauncher<Intent> photoLauncher;
    private ActivityResultLauncher<Intent> videoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_media);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageView = findViewById(R.id.imageView);
        videoView = findViewById(R.id.videoView);
        Button btnPhoto = findViewById(R.id.btnCapturePhoto);
        Button btnVideo = findViewById(R.id.btnRecordVideo);
        btnAudio = findViewById(R.id.btnPlayAudio);

        // Register Activity Result Launchers
        photoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bundle extras = result.getData().getExtras();
                        if (extras != null) {
                            Bitmap bitmap = (Bitmap) extras.get("data");
                            if (bitmap != null) {
                                imageView.setImageBitmap(bitmap);
                                imageView.setBackground(null); // Remove grey background
                                Toast.makeText(this, "Photo captured!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );

        videoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri videoUri = result.getData().getData();
                        if (videoUri != null) {
                            videoView.setVideoURI(videoUri);
                            videoView.start();
                            Toast.makeText(this, "Playing video", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        btnPhoto.setOnClickListener(v -> {
            if (checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)) {
                openCamera();
            }
        });

        btnVideo.setOnClickListener(v -> {
            if (checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)) {
                openVideoRecorder();
            }
        });

        btnAudio.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                btnAudio.setText("Play Default Ringtone");
            } else {
                mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                    btnAudio.setText("Stop Audio");
                }
            }
        });
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoLauncher.launch(intent);
    }

    private void openVideoRecorder() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        videoLauncher.launch(intent);
    }

    private boolean checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted. Click again to open camera.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
