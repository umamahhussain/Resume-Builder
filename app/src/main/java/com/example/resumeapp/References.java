package com.example.resumeapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class References extends AppCompatActivity {

    EditText ref1Name, ref1Contact;
    EditText ref2Name, ref2Contact;
    EditText ref3Name, ref3Contact;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        // Initialize UI Elements
        ref1Name = findViewById(R.id.reference_name);
        ref1Contact = findViewById(R.id.reference_contact);
        ref2Name = findViewById(R.id.reference_name2);
        ref2Contact = findViewById(R.id.reference_contact2);
        ref3Name = findViewById(R.id.reference_name3);
        ref3Contact = findViewById(R.id.reference_contact3);
        btnSubmit = findViewById(R.id.btn_submit);
        btnCancel = findViewById(R.id.btn_cancel);

        // Load saved data (if available)
        loadData();

        // Save data on Submit
        btnSubmit.setOnClickListener(v -> {
            saveData();  // Save user data to SharedPreferences
            Toast.makeText(this, "References Saved", Toast.LENGTH_SHORT).show();
            finish();    // Close activity
        });

        // Cancel button (Close activity without saving)
        btnCancel.setOnClickListener(v -> finish());
    }

    // Save user input to SharedPreferences
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ReferencesData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("ref1_name", ref1Name.getText().toString());
        editor.putString("ref1_contact", ref1Contact.getText().toString());

        editor.putString("ref2_name", ref2Name.getText().toString());
        editor.putString("ref2_contact", ref2Contact.getText().toString());

        editor.putString("ref3_name", ref3Name.getText().toString());
        editor.putString("ref3_contact", ref3Contact.getText().toString());

        editor.apply();  // Apply changes
    }

    // Load saved data when opening the activity
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ReferencesData", MODE_PRIVATE);

        ref1Name.setText(sharedPreferences.getString("ref1_name", ""));
        ref1Contact.setText(sharedPreferences.getString("ref1_contact", ""));

        ref2Name.setText(sharedPreferences.getString("ref2_name", ""));
        ref2Contact.setText(sharedPreferences.getString("ref2_contact", ""));

        ref3Name.setText(sharedPreferences.getString("ref3_name", ""));
        ref3Contact.setText(sharedPreferences.getString("ref3_contact", ""));
    }
}
