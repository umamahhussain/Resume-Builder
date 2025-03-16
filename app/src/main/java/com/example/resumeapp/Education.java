package com.example.resumeapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Education extends AppCompatActivity {

    EditText schoolName, schoolYear;
    EditText collegeName, collegeYear;
    Spinner collegeGrade;
    EditText universityName, universityCgpa, universityYear;
    Spinner higherDegree1, higherDegree2;
    EditText higherInstitute1, higherYear1;
    EditText higherInstitute2, higherYear2;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        // Initialize UI Elements
        schoolName = findViewById(R.id.school_name);
        schoolYear = findViewById(R.id.school_year);
        collegeName = findViewById(R.id.college_name);
        collegeGrade = findViewById(R.id.college_grade);
        collegeYear = findViewById(R.id.college_year);
        universityName = findViewById(R.id.university_name);
        universityCgpa = findViewById(R.id.university_cgpa);
        universityYear = findViewById(R.id.university_year);
        higherDegree1 = findViewById(R.id.higher_degree);
        higherInstitute1 = findViewById(R.id.higher_institute);
        higherYear1 = findViewById(R.id.higher_year);
        higherDegree2 = findViewById(R.id.higher_degree2);
        higherInstitute2 = findViewById(R.id.higher_institute2);
        higherYear2 = findViewById(R.id.higher_year2);
        btnSubmit = findViewById(R.id.btn_submit);
        btnCancel = findViewById(R.id.btn_cancel);

        // Load saved data (if available)
        loadData();

        // Save data on Submit
        btnSubmit.setOnClickListener(v -> {
            saveData();  // Save user data to SharedPreferences
            Toast.makeText(this, "Education Details Saved", Toast.LENGTH_SHORT).show();
            finish();    // Close activity
        });

        // Cancel button (Close activity without saving)
        btnCancel.setOnClickListener(v -> finish());
    }

    // Save user input to SharedPreferences
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("EducationData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("school_name", schoolName.getText().toString());
        editor.putString("school_year", schoolYear.getText().toString());

        editor.putString("college_name", collegeName.getText().toString());
        editor.putString("college_grade", collegeGrade.getSelectedItem().toString());
        editor.putString("college_year", collegeYear.getText().toString());

        editor.putString("university_name", universityName.getText().toString());
        editor.putString("university_cgpa", universityCgpa.getText().toString());
        editor.putString("university_year", universityYear.getText().toString());

        editor.putString("higher_degree1", higherDegree1.getSelectedItem().toString());
        editor.putString("higher_institute1", higherInstitute1.getText().toString());
        editor.putString("higher_year1", higherYear1.getText().toString());

        editor.putString("higher_degree2", higherDegree2.getSelectedItem().toString());
        editor.putString("higher_institute2", higherInstitute2.getText().toString());
        editor.putString("higher_year2", higherYear2.getText().toString());

        editor.apply();  // Apply changes
    }

    // Load saved data when opening the activity
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("EducationData", MODE_PRIVATE);

        schoolName.setText(sharedPreferences.getString("school_name", ""));
        schoolYear.setText(sharedPreferences.getString("school_year", ""));

        collegeName.setText(sharedPreferences.getString("college_name", ""));
        collegeYear.setText(sharedPreferences.getString("college_year", ""));

        universityName.setText(sharedPreferences.getString("university_name", ""));
        universityCgpa.setText(sharedPreferences.getString("university_cgpa", ""));
        universityYear.setText(sharedPreferences.getString("university_year", ""));

        higherInstitute1.setText(sharedPreferences.getString("higher_institute1", ""));
        higherYear1.setText(sharedPreferences.getString("higher_year1", ""));

        higherInstitute2.setText(sharedPreferences.getString("higher_institute2", ""));
        higherYear2.setText(sharedPreferences.getString("higher_year2", ""));
    }
}
