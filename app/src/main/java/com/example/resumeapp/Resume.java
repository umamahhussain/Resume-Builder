package com.example.resumeapp;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Resume extends AppCompatActivity {

    private ImageView profileImage;
    private TextView nameText, emailText, numberText, dobText;
    private TextView summaryText;
    private TextView educationText, experienceText, certificationsText, referencesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        // Initialize UI elements
        profileImage = findViewById(R.id.profile_image);
        nameText = findViewById(R.id.name_text);
        emailText = findViewById(R.id.email_text);
        numberText = findViewById(R.id.number_text);
        dobText = findViewById(R.id.dob_text);
        summaryText = findViewById(R.id.summary_text);
        educationText = findViewById(R.id.education_text);
        experienceText = findViewById(R.id.experience_text);
        certificationsText = findViewById(R.id.certifications_text);
        referencesText = findViewById(R.id.references_text);

        // Load data from SharedPreferences
        loadProfilePicture();
        loadPersonalDetails();
        loadSummary();
        loadEducation();
        loadExperience();
        loadCertifications();
        loadReferences();
    }

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

    private void loadPersonalDetails() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
        setTextOrHide(nameText, sharedPreferences.getString("name", ""));
        setTextOrHide(emailText, sharedPreferences.getString("email", ""));
        setTextOrHide(numberText, sharedPreferences.getString("number", ""));
        setTextOrHide(dobText, sharedPreferences.getString("dob", ""));
    }

    private void loadSummary() {
        SharedPreferences sharedPreferences = getSharedPreferences("SummaryPrefs", MODE_PRIVATE);
        setTextOrHide(summaryText, sharedPreferences.getString("summary_text", ""));
    }

    private void loadEducation() {
        SharedPreferences sharedPreferences = getSharedPreferences("EducationData", MODE_PRIVATE);
        StringBuilder education = new StringBuilder();

        appendIfNotEmpty(education, "School: ", sharedPreferences.getString("school_name", ""));
        appendIfNotEmpty(education, "College: ", sharedPreferences.getString("college_name", ""));
        appendIfNotEmpty(education, "Grade: ", sharedPreferences.getString("college_grade", ""));
        appendIfNotEmpty(education, "University: ", sharedPreferences.getString("university_name", ""));
        appendIfNotEmpty(education, "CGPA: ", sharedPreferences.getString("university_cgpa", ""));
        appendIfNotEmpty(education, "Masters/PhD: ", sharedPreferences.getString("higher_degree", ""));

        setTextOrHide(educationText, education.toString());
    }

    private void loadExperience() {
        SharedPreferences sharedPreferences = getSharedPreferences("ExperienceData", MODE_PRIVATE);
        StringBuilder experience = new StringBuilder();

        appendIfNotEmpty(experience, "", sharedPreferences.getString("exp1_title", ""));
        appendIfNotEmpty(experience, " - ", sharedPreferences.getString("exp1_company", ""));
        appendIfNotEmpty(experience, "Duration: ", sharedPreferences.getString("exp1_duration", ""));
        appendIfNotEmpty(experience, "Description: ", sharedPreferences.getString("exp1_description", ""));
        experience.append("\n\n");

        appendIfNotEmpty(experience, "", sharedPreferences.getString("exp2_title", ""));
        appendIfNotEmpty(experience, " - ", sharedPreferences.getString("exp2_company", ""));
        appendIfNotEmpty(experience, "Duration: ", sharedPreferences.getString("exp2_duration", ""));
        appendIfNotEmpty(experience, "Description: ", sharedPreferences.getString("exp2_description", ""));

        setTextOrHide(experienceText, experience.toString());
    }

    private void loadCertifications() {
        SharedPreferences sharedPreferences = getSharedPreferences("CertificationsData", MODE_PRIVATE);
        StringBuilder certifications = new StringBuilder();

        appendIfNotEmpty(certifications, "", sharedPreferences.getString("cert1_name", ""));
        appendIfNotEmpty(certifications, " - ", sharedPreferences.getString("cert1_org", ""));
        appendIfNotEmpty(certifications, "Issued on: ", sharedPreferences.getString("cert1_date", ""));
        certifications.append("\n\n");

        appendIfNotEmpty(certifications, "", sharedPreferences.getString("cert2_name", ""));
        appendIfNotEmpty(certifications, " - ", sharedPreferences.getString("cert2_org", ""));
        appendIfNotEmpty(certifications, "Issued on: ", sharedPreferences.getString("cert2_date", ""));

        setTextOrHide(certificationsText, certifications.toString());
    }

    private void loadReferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("ReferencesData", MODE_PRIVATE);
        StringBuilder references = new StringBuilder();

        appendIfNotEmpty(references, "", sharedPreferences.getString("ref1_name", ""));
        appendIfNotEmpty(references, " - ", sharedPreferences.getString("ref1_contact", ""));
        references.append("\n\n");

        appendIfNotEmpty(references, "", sharedPreferences.getString("ref2_name", ""));
        appendIfNotEmpty(references, " - ", sharedPreferences.getString("ref2_contact", ""));
        references.append("\n\n");

        appendIfNotEmpty(references, "", sharedPreferences.getString("ref3_name", ""));
        appendIfNotEmpty(references, " - ", sharedPreferences.getString("ref3_contact", ""));

        setTextOrHide(referencesText, references.toString());
    }

    /**
     * Sets text for a TextView or hides it if the value is empty.
     */
    private void setTextOrHide(TextView textView, String value) {
        if (value == null || value.trim().isEmpty() || value.equals("N/A")) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(value);
            textView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Appends text if it's not empty.
     */
    private void appendIfNotEmpty(StringBuilder builder, String label, String value) {
        if (value != null && !value.trim().isEmpty() && !value.equals("N/A")) {
            builder.append(label).append(value).append("\n");
        }
    }
}
