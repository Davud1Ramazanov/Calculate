package com.example.lessonproj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button numberButtons[];
    Button divide, multiply, minus, plus, equals, sqrt, square, percentage;
    Double result = 0.0;
    String symbolsText[];
    EditText inputNumbers;
    Boolean isNewNumber = true;
    String oldNumber = "";
    String operators = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumbers = findViewById(R.id.inputNumbers);
        numberButtons = new Button[10];

        for (int i = 0; i < 10; i++) {
            int buttonId = getResources().getIdentifier("number" + i, "id", getPackageName());
            numberButtons[i] = findViewById(buttonId);
        }

        symbolsText = new String[10];

        for (int i = 0; i < 10; i++) {
            final int finalInt = i;
            symbolsText[i] = String.valueOf(i);
            numberButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isNewNumber) {
                        inputNumbers.setText(symbolsText[finalInt]);
                        isNewNumber = false;
                    } else {
                        String currentText = inputNumbers.getText().toString();
                        inputNumbers.setText(currentText + symbolsText[finalInt]);
                    }
                }
            });
        }

        divide = findViewById(R.id.symbol0);
        multiply = findViewById(R.id.symbol1);
        minus = findViewById(R.id.symbol2);
        plus = findViewById(R.id.symbol3);
        equals = findViewById(R.id.symbol4);
        sqrt = findViewById(R.id.symbol5);
        square = findViewById(R.id.symbol8);
        percentage = findViewById(R.id.symbol10);

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator("/");
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator("*");
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator("-");
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator("+");
            }
        });

        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator("√");
            }
        });
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator("X²");
            }
        });
        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperator("%");
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();
            }
        });
    }

    public void setOperator(String newOperator) {
        if (!inputNumbers.getText().toString().isEmpty()) {
            if (!operators.isEmpty() && !isNewNumber) {
                calculateResult();
            }
            oldNumber = inputNumbers.getText().toString();
            operators = newOperator;
            inputNumbers.setText("");
            isNewNumber = true;
        }
    }

    public void clearInput(View view) {
        inputNumbers.setText("");
        oldNumber = "";
        operators = "";
        result = 0.0;
        isNewNumber = true;
    }

    public void calculateResult() {
        String currentText = inputNumbers.getText().toString();
        if (!currentText.isEmpty()) {
            Double newNumber = Double.parseDouble(currentText);

            switch (operators) {
                case "/":
                    result = Double.parseDouble(oldNumber) / newNumber;
                    break;
                case "*":
                    result = Double.parseDouble(oldNumber) * newNumber;
                    break;
                case "-":
                    result = Double.parseDouble(oldNumber) - newNumber;
                    break;
                case "+":
                    result = Double.parseDouble(oldNumber) + newNumber;
                    break;
                case "X²":
                    Double number = 1.0;
                    for (int i = 1; i <= newNumber; i++) {
                        number *= Double.parseDouble(oldNumber);
                    }
                    result = number;
                    break;
                case "√":
                    result = Math.sqrt(result);
                    break;
                case "%":
                    result = Double.parseDouble(oldNumber) % newNumber;
                    break;
            }

            inputNumbers.setText(result.toString());
            operators = "";
            isNewNumber = true;
        }
    }
}
