package com.example.resumeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfilePicture extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAPTURE_IMAGE_REQUEST = 2;

    ImageView profileImage;
    ImageButton btnEditPic;
    Button btnSelectGallery, btnCapturePhoto, btnRemovePic, btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);

        // Initialize UI elements
        profileImage = findViewById(R.id.profile_image);
        btnEditPic = findViewById(R.id.btn_edit_pic);
        btnSelectGallery = findViewById(R.id.btn_select_gallery);
        btnCapturePhoto = findViewById(R.id.btn_capture_photo);
        btnRemovePic = findViewById(R.id.btn_remove_pic);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

        // Load saved profile picture (if available)
        loadProfilePicture();

        // Open gallery to select an image
        btnSelectGallery.setOnClickListener(v -> openGallery());

        // Open camera to capture a new image
        btnCapturePhoto.setOnClickListener(v -> openCamera());

        // Remove profile picture
        btnRemovePic.setOnClickListener(v -> {
            profileImage.setImageResource(R.drawable.person);
            saveProfilePicture(null);
            Toast.makeText(this, "Profile picture removed", Toast.LENGTH_SHORT).show();
        });

        // Save selected/captured profile picture
        btnSave.setOnClickListener(v -> {
            profileImage.setDrawingCacheEnabled(true);
            profileImage.buildDrawingCache();
            Bitmap bitmap = Bitmap.createBitmap(profileImage.getDrawingCache());
            profileImage.setDrawingCacheEnabled(false); // Disable drawing cache to free memory

            saveProfilePicture(bitmap);

            Toast.makeText(this, "Profile picture saved", Toast.LENGTH_SHORT).show();
            finish();
        });


        // Cancel and go back
        btnCancel.setOnClickListener(v -> finish());

        // Clicking the edit button also opens the gallery
        btnEditPic.setOnClickListener(v -> openGallery());
    }

    // Open the image gallery
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Open the camera to capture a photo
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST && data != null) {
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                        profileImage.setImageBitmap(bitmap);
                        saveProfilePicture(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (requestCode == CAPTURE_IMAGE_REQUEST && data != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                profileImage.setImageBitmap(bitmap);
                saveProfilePicture(bitmap);
            }
        }
    }

    // Save the selected image to SharedPreferences
    private void saveProfilePicture(Bitmap bitmap) {
        SharedPreferences sharedPreferences = getSharedPreferences("ProfileData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
            editor.putString("profile_image", encodedImage);
        } else {
            editor.remove("profile_image");
        }

        editor.apply();
    }

    // Load the saved profile picture from SharedPreferences
    private void loadProfilePicture() {
        SharedPreferences sharedPreferences = getSharedPreferences("ProfileData", MODE_PRIVATE);
        String encodedImage = sharedPreferences.getString("profile_image", null);

        if (encodedImage != null) {
            byte[] decodedBytes = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            profileImage.setImageBitmap(bitmap);
        } else {
            profileImage.setImageResource(R.drawable.person);
        }
    }
}
