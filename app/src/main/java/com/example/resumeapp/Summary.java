package com.example.resumeapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Summary extends AppCompatActivity {

    private EditText summaryEditText;
    private Button btnSubmit, btnCancel;
    private static final String PREF_NAME = "SummaryPrefs";
    private static final String SUMMARY_KEY = "summary_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // Initialize UI Elements
        summaryEditText = findViewById(R.id.summary);
        btnSubmit = findViewById(R.id.btn_submit);
        btnCancel = findViewById(R.id.btn_cancel);

        // Load saved summary text (if available)
        loadData();

        // Save data on Submit
        btnSubmit.setOnClickListener(v -> {
            saveData();
            Toast.makeText(this, "Summary Saved", Toast.LENGTH_SHORT).show();
            finish(); // Close activity
        });

        // Cancel button (Close activity without saving)
        btnCancel.setOnClickListener(v -> finish());
    }

    // Save summary to SharedPreferences
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SUMMARY_KEY, summaryEditText.getText().toString());
        editor.apply(); // Apply changes
    }

    // Load saved summary when opening the activity
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        summaryEditText.setText(sharedPreferences.getString(SUMMARY_KEY, ""));
    }
}
