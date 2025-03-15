package com.example.resumeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView Resume;
    TextView Builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        Animation fade = AnimationUtils.loadAnimation(this,R.anim.fade);
        Resume.startAnimation(fade);
        Builder.startAnimation(fade);

        // Move to Start activity after 4 seconds
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            finish(); // Close the SplashActivity
        }, 50); // 3 seconds delay


    }

    public void init(){
        Resume = findViewById(R.id.resume);
        Builder = findViewById(R.id.builder);
    }
}