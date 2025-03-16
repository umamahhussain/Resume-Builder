package com.example.resumeapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Experience extends AppCompatActivity {

    EditText exp1Title, exp1Company, exp1Duration, exp1Description;
    EditText exp2Title, exp2Company, exp2Duration, exp2Description;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        // Initialize UI Elements
        exp1Title = findViewById(R.id.experience1_title);
        exp1Company = findViewById(R.id.experience1_company);
        exp1Duration = findViewById(R.id.experience1_duration);
        exp1Description = findViewById(R.id.experience1_description);
        exp2Title = findViewById(R.id.experience2_title);
        exp2Company = findViewById(R.id.experience2_company);
        exp2Duration = findViewById(R.id.experience2_duration);
        exp2Description = findViewById(R.id.experience2_description);
        btnSubmit = findViewById(R.id.btn_submit);
        btnCancel = findViewById(R.id.btn_cancel);

        // Load saved data (if available)
        loadData();

        // Save data on Submit
        btnSubmit.setOnClickListener(v -> {
            saveData();  // Save user data to SharedPreferences
            Toast.makeText(this, "Experience Saved", Toast.LENGTH_SHORT).show();
            finish();    // Close activity
        });

        // Cancel button (Close activity without saving)
        btnCancel.setOnClickListener(v -> finish());
    }

    // Save user input to SharedPreferences
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ExperienceData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("exp1_title", exp1Title.getText().toString());
        editor.putString("exp1_company", exp1Company.getText().toString());
        editor.putString("exp1_duration", exp1Duration.getText().toString());
        editor.putString("exp1_description", exp1Description.getText().toString());

        editor.putString("exp2_title", exp2Title.getText().toString());
        editor.putString("exp2_company", exp2Company.getText().toString());
        editor.putString("exp2_duration", exp2Duration.getText().toString());
        editor.putString("exp2_description", exp2Description.getText().toString());

        editor.apply();  // Apply changes
    }

    // Load saved data when opening the activity
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ExperienceData", MODE_PRIVATE);

        exp1Title.setText(sharedPreferences.getString("exp1_title", ""));
        exp1Company.setText(sharedPreferences.getString("exp1_company", ""));
        exp1Duration.setText(sharedPreferences.getString("exp1_duration", ""));
        exp1Description.setText(sharedPreferences.getString("exp1_description", ""));

        exp2Title.setText(sharedPreferences.getString("exp2_title", ""));
        exp2Company.setText(sharedPreferences.getString("exp2_company", ""));
        exp2Duration.setText(sharedPreferences.getString("exp2_duration", ""));
        exp2Description.setText(sharedPreferences.getString("exp2_description", ""));
    }
}
