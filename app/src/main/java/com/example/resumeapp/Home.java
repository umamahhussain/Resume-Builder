package com.example.resumeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    Button prof, exp, edu, cert, ref, sum, det, gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        Animation leftTOright = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        Animation rightTOleft = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        prof.startAnimation(leftTOright);
        det.startAnimation(rightTOleft);
        sum.startAnimation(leftTOright);
        edu.startAnimation(rightTOleft);
        exp.startAnimation(leftTOright);
        cert.startAnimation(rightTOleft);
        ref.startAnimation(leftTOright);
        gen.startAnimation(rightTOleft);

        prof.setOnClickListener(v -> {
            Intent profile = new Intent(this, ProfilePicture.class);
            startActivity(profile);
        });
        det.setOnClickListener(v -> {
            Intent details = new Intent(this, PersonalDetails.class);
            startActivity(details);
        });
        sum.setOnClickListener(v -> {
            Intent summary = new Intent(this, Summary.class);
            startActivity(summary);
        });
        edu.setOnClickListener(v -> {
            Intent education = new Intent(this, Education.class);
            startActivity(education);
        });
        exp.setOnClickListener(v -> {
            Intent experience = new Intent(this, Experience.class);
            startActivity(experience);
        });
        cert.setOnClickListener(v -> {
            Intent certifications = new Intent(this, Certifications.class);
            startActivity(certifications);
        });
        ref.setOnClickListener(v -> {
            Intent references = new Intent(this, References.class);
            startActivity(references);
        });
        gen.setOnClickListener(v -> {
            Intent generate = new Intent(this, Resume.class);
            startActivity(generate);
        });
    }

    public void init()
    {
        prof = findViewById( R.id.btn_profile );
        exp = findViewById( R.id.btn_exp );
        cert = findViewById( R.id.btn_cert );
        edu = findViewById( R.id.btn_edu );
        ref = findViewById( R.id.btn_ref );
        sum = findViewById( R.id.btn_summary );
        det = findViewById( R.id.btn_persDet );
        gen = findViewById( R.id.btn_gen );
    }
}