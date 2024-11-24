package com.example.myapplicationlkijuyhgtfrds;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText inputData;
    private TextView resultData;
    private String currentInput = "";  // Holds the current operand
    private String operator = "";     // Holds the current operator
    private String displayExpression = ""; // To hold the full expression (input + operator)
    private List<Double> operands = new ArrayList<>(); // List to store operands
    private List<String> operators = new ArrayList<>(); // List to store operators

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views
        inputData = findViewById(R.id.input_data);
        resultData = findViewById(R.id.result_data);

        // Button click listeners
        findViewById(R.id.but0).setOnClickListener(buttonClickListener);
        findViewById(R.id.but1).setOnClickListener(buttonClickListener);
        findViewById(R.id.but2).setOnClickListener(buttonClickListener);
        findViewById(R.id.but3).setOnClickListener(buttonClickListener);
        findViewById(R.id.but4).setOnClickListener(buttonClickListener);
        findViewById(R.id.but5).setOnClickListener(buttonClickListener);
        findViewById(R.id.but6).setOnClickListener(buttonClickListener);
        findViewById(R.id.but7).setOnClickListener(buttonClickListener);
        findViewById(R.id.but8).setOnClickListener(buttonClickListener);
        findViewById(R.id.but9).setOnClickListener(buttonClickListener);
        findViewById(R.id.but00).setOnClickListener(buttonClickListener);
        findViewById(R.id.decimal).setOnClickListener(buttonClickListener);

        findViewById(R.id.clear).setOnClickListener(clearClickListener);
        findViewById(R.id.del).setOnClickListener(deleteClickListener);
        findViewById(R.id.percent).setOnClickListener(percentClickListener);

        findViewById(R.id.add).setOnClickListener(operatorClickListener);
        findViewById(R.id.minus).setOnClickListener(operatorClickListener);
        findViewById(R.id.multiply).setOnClickListener(operatorClickListener);
        findViewById(R.id.div).setOnClickListener(operatorClickListener);

        findViewById(R.id.equal).setOnClickListener(equalClickListener);
    }

    // Button click listener for numbers and decimal
    private final View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            currentInput += button.getText().toString();
            displayExpression += button.getText().toString(); // Add the number to the full expression
            inputData.setText(displayExpression); // Show the expression on screen
        }
    };

    // Clear button click listener
    private final View.OnClickListener clearClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            currentInput = "";
            operator = "";
            operands.clear(); // Reset operands list
            operators.clear(); // Reset operators list
            displayExpression = ""; // Reset expression
            inputData.setText(""); // Clear screen
            resultData.setText(""); // Clear result
        }
    };

    // Delete button click listener
    private final View.OnClickListener deleteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (currentInput.length() > 0) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                displayExpression = displayExpression.substring(0, displayExpression.length() - 1); // Remove last character from the expression
                inputData.setText(displayExpression);
            }
        }
    };

    // Percent button click listener
    private final View.OnClickListener percentClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                currentInput = String.valueOf(value / 100);
                displayExpression = currentInput; // Update expression display
                inputData.setText(displayExpression);
            }
        }
    };

    // Operator button click listener (+, -, ×, /)
    private final View.OnClickListener operatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            if (!currentInput.isEmpty()) {
                operands.add(Double.parseDouble(currentInput)); // Add current operand to operands list
                operators.add(button.getText().toString()); // Add operator to operators list
                currentInput = ""; // Reset current input
                displayExpression += " " + button.getText().toString() + " "; // Add operator to expression
                inputData.setText(displayExpression); // Display updated expression
            }
        }
    };

    // Equal button click listener to perform calculation
    private final View.OnClickListener equalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!currentInput.isEmpty()) {
                operands.add(Double.parseDouble(currentInput)); // Add the final operand
            }
            if (operands.size() > 1 && operators.size() > 0) {
                double result = operands.get(0);

                // Process each operator and operand pair
                for (int i = 0; i < operators.size(); i++) {
                    double operand = operands.get(i + 1);
                    String operator = operators.get(i);

                    switch (operator) {
                        case "+":
                            result += operand;
                            break;
                        case "-":
                            result -= operand;
                            break;
                        case "×":
                            result *= operand;
                            break;
                        case "/":
                            if (operand != 0) {
                                result /= operand;
                            } else {
                                resultData.setText("Error");
                                return;
                            }
                            break;
                    }
                }

                resultData.setText(String.valueOf(result)); // Display result
                currentInput = String.valueOf(result); // Set result as the next input
                operands.clear(); // Clear operands list
                operators.clear(); // Clear operators list
                displayExpression = String.valueOf(result); // Update the displayed expression with result
                inputData.setText(displayExpression);
            }
        }
    };
}
