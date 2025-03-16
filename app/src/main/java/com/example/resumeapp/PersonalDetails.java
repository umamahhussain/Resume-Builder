package com.example.resumeapp;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class PersonalDetails extends AppCompatActivity {

    EditText etName, etEmail, etNumber, etDob;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        // Initialize UI Elements
        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        etNumber = findViewById(R.id.number);
        etDob = findViewById(R.id.dob);
        btnSubmit = findViewById(R.id.btn_submit);
        btnCancel = findViewById(R.id.btn_cancel);

        // Load saved data (if available)
        loadData();

        // Date Picker for DOB
        etDob.setOnClickListener(v -> showDatePicker());

        // Save data on Submit
        btnSubmit.setOnClickListener(v -> {
            saveData();  // Save user data to SharedPreferences
            Toast.makeText(this, "Personal Details Saved", Toast.LENGTH_SHORT).show();
            finish();    // Close activity
        });

        // Cancel button (Close activity without saving)
        btnCancel.setOnClickListener(v -> finish());
    }

    // Save user input to SharedPreferences
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", etName.getText().toString());
        editor.putString("email", etEmail.getText().toString());
        editor.putString("number", etNumber.getText().toString());
        editor.putString("dob", etDob.getText().toString());
        editor.apply();  // Apply changes
    }

    // Load saved data when opening the activity
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
        etName.setText(sharedPreferences.getString("name", ""));
        etEmail.setText(sharedPreferences.getString("email", ""));
        etNumber.setText(sharedPreferences.getString("number", ""));
        etDob.setText(sharedPreferences.getString("dob", ""));
    }

    // Date Picker for DOB selection
    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String dob = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    etDob.setText(dob);
                }, year, month, day);

        datePickerDialog.show();
    }
}
