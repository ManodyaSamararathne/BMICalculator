package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // UI Components
    private EditText etWeight, etHeight;
    private Button btnCalculate, btnReset;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Link variables to XML widgets using findViewById
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);
        tvResult = findViewById(R.id.tvResult);

        // Event handling: Calculate button
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
            }
        });

        // Event handling: Reset button
        btnReset.setOnClickListener(v -> resetFields());
    }

    private void calculateBmi() {

        String weightStr = etWeight.getText().toString().trim();
        String heightStr = etHeight.getText().toString().trim();

        // Check whether fields are empty
        if (weightStr.isEmpty() || heightStr.isEmpty()) {

            Toast.makeText(
                    this,
                    "Please enter both weight and height",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        try {

            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);

            // Check whether values are greater than zero
            if (height <= 0 || weight <= 0) {

                Toast.makeText(
                        this,
                        "Values must be greater than zero",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // BMI formula
            double bmi = weight / (height * height);

            // Get BMI category
            String category = getBmiCategory(bmi);

            // Display result
            tvResult.setText(
                    String.format("BMI: %.1f (%s)", bmi, category)
            );

        } catch (NumberFormatException e) {

            Toast.makeText(
                    this,
                    "Please enter valid numbers",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private String getBmiCategory(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    private void resetFields() {

        etWeight.setText("");
        etHeight.setText("");
        tvResult.setText("");
    }
}