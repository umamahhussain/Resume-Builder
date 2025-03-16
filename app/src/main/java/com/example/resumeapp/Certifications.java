package com.example.resumeapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Certifications extends AppCompatActivity {

    EditText cert1Name, cert1Org, cert1Date;
    EditText cert2Name, cert2Org, cert2Date;
    EditText cert3Name, cert3Org, cert3Date;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);

        // Initialize UI Elements
        cert1Name = findViewById(R.id.certification1_name);
        cert1Org = findViewById(R.id.certification1_org);
        cert1Date = findViewById(R.id.certification1_date);
        cert2Name = findViewById(R.id.certification2_name);
        cert2Org = findViewById(R.id.certification2_org);
        cert2Date = findViewById(R.id.certification2_date);
        btnSubmit = findViewById(R.id.btn_submit);
        btnCancel = findViewById(R.id.btn_cancel);

        // Load saved data (if available)
        loadData();

        // Save data on Submit
        btnSubmit.setOnClickListener(v -> {
            saveData();  // Save user data to SharedPreferences
            Toast.makeText(this, "Certifications Saved", Toast.LENGTH_SHORT).show();
            finish();    // Close activity
        });

        // Cancel button (Close activity without saving)
        btnCancel.setOnClickListener(v -> finish());
    }

    // Save user input to SharedPreferences
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("CertificationsData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("cert1_name", cert1Name.getText().toString());
        editor.putString("cert1_org", cert1Org.getText().toString());
        editor.putString("cert1_date", cert1Date.getText().toString());

        editor.putString("cert2_name", cert2Name.getText().toString());
        editor.putString("cert2_org", cert2Org.getText().toString());
        editor.putString("cert2_date", cert2Date.getText().toString());

        editor.apply();  // Apply changes
    }

    // Load saved data when opening the activity
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("CertificationsData", MODE_PRIVATE);

        cert1Name.setText(sharedPreferences.getString("cert1_name", ""));
        cert1Org.setText(sharedPreferences.getString("cert1_org", ""));
        cert1Date.setText(sharedPreferences.getString("cert1_date", ""));

        cert2Name.setText(sharedPreferences.getString("cert2_name", ""));
        cert2Org.setText(sharedPreferences.getString("cert2_org", ""));
        cert2Date.setText(sharedPreferences.getString("cert2_date", ""));


    }
}
