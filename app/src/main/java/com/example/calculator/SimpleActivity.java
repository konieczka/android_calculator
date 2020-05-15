package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleActivity extends AppCompatActivity {
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    private Button buttonAdd;
    private Button buttonSubtract;
    private Button buttonMultiply;
    private Button buttonDivide;
    private Button buttonPoint;
    private Button buttonEquals;

    private Button buttonBackspace;
    private Button buttonClear;
    private Button buttonChangeSign;

    private TextView currentInputView;
    private TextView registryView;

    private double currentResultDouble = 0;
    private int currentResultInt = 0;
    private boolean floatingPoint = false;
    private boolean firstOp = true;
    private boolean displayingResult = false;

    private char lastOp = '+';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        bindUIButtons();
        bindUIViews();

        setNumerButtonsOnClickListeners();
        setInputManagementButtonsOnClickListeners();
        setOperationsButtonsOnClickListeners();

    }

    private void onDigitInput(int digit){
        if (displayingResult){
            currentInputView.setText("");
            displayingResult = false;
        }
        currentInputView.setText(currentInputView.getText() + String.valueOf(digit));
    }

    private void switchToDouble(){
        currentResultDouble = currentResultInt;
        floatingPoint = true;
    }

    private void performOperationInt(char op){
        int inputNumber = Integer.valueOf((String) currentInputView.getText());

        if (firstOp) {
            currentResultInt = inputNumber;
            return;
        }

        switch (op){
            case '-':
                currentResultInt -= inputNumber;
                break;
            case '/':
                if (currentResultInt % inputNumber == 0){
                    currentResultInt /= inputNumber;
                }
                else {
                    switchToDouble();
                    currentResultDouble /= inputNumber;
                }
                break;
            case '*':
                currentResultInt *= inputNumber;
                break;
            default:
                currentResultInt += inputNumber;
                break;
        }
    }

    private void performOperationDouble(char op){
        double inputNumber = Double.valueOf((String) currentInputView.getText());

        if (firstOp) {
            currentResultDouble = inputNumber;
            return;
        }

        switch (op){
            case '-':
                currentResultDouble -= inputNumber;
                break;
            case '/':
                currentResultDouble /= inputNumber;
                break;
            case '*':
                currentResultDouble *= inputNumber;
                break;
            default:
                currentResultDouble += inputNumber;
                break;
        }
    }

    private void validateOperation(char op){
        if (displayingResult){
            currentInputView.setText("");
            displayingResult = false;
        }
        else{
            String currentInput = (String) currentInputView.getText();

            if (currentInput.length() == 0){
                currentInputView.setText("Invalid input");
                displayingResult = true;
                return;
            }

            if (!floatingPoint && currentInput.indexOf('.') > 0){
                switchToDouble();
            }

            if (floatingPoint){
                performOperationDouble(op);
            }
            else {
                performOperationInt(op);
            }
        }

        if (firstOp) firstOp = false;

        lastOp = op;
        currentInputView.setText("");

        if (floatingPoint){
            registryView.setText(Double.toString(currentResultDouble));
        }
        else {
            registryView.setText(Integer.toString(currentResultInt));
        }

        registryView.setText(registryView.getText() + " " + lastOp);
    }

    private void finalizeResult(){
        if (floatingPoint){
            validateOperation(lastOp);
        }
        else{
            validateOperation(lastOp);
        }

        if (floatingPoint){
            currentInputView.setText(Double.toString(currentResultDouble));
            registryView.setText(Double.toString(currentResultDouble));
        }
        else{
            currentInputView.setText(Integer.toString(currentResultInt));
            registryView.setText(Integer.toString(currentResultInt));
        }

        displayingResult = true;
    }

    private void reset(){
        currentResultDouble = 0;
        currentResultInt = 0;
        firstOp = true;
        lastOp = '+';
        floatingPoint = true;
        displayingResult = false;
        registryView.setText("0");
    }

    private void bindUIButtons(){
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonPoint = (Button) findViewById(R.id.buttonPoint);
        buttonEquals = (Button) findViewById(R.id.buttonEquals);

        buttonBackspace = (Button) findViewById(R.id.buttonBackspace);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonChangeSign = (Button) findViewById(R.id.buttonChangeSign);
    }

    private void bindUIViews(){
        currentInputView = (TextView) findViewById(R.id.currentInputView);
        registryView = (TextView) findViewById(R.id.registryView);
    }

    private void setOperationsButtonsOnClickListeners(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOperation('+');
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 validateOperation('-');
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOperation('*');
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOperation('/');
            }
        });

        buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentInput = (String) currentInputView.getText();
                if (currentInput.length() == 0){
                    currentInputView.setText("0.");
                }
                else{
                    if (currentInput.indexOf('.') == -1){
                        currentInputView.setText(currentInput+ ".");
                    }
                }
            }
        });

        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizeResult();
            }
        });
    }

    private void setInputManagementButtonsOnClickListeners(){
        buttonBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentInput = (String) currentInputView.getText();

                if (currentInput.length() > 0){
                    currentInputView.setText(currentInput.substring(0, currentInput.length() - 1));
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentInputView.getText().length()) > 0){
                    currentInputView.setText("");
                }
                else {
                    reset();
                }
            }
        });

        buttonChangeSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentInput = (String) currentInputView.getText();

                if (currentInput.length() > 0){
                    if (currentInput.indexOf('-') == -1){
                        currentInputView.setText("-" + currentInput);
                    }
                    else {
                        currentInputView.setText(currentInput.substring(1,currentInput.length()));
                    }
                }
            }
        });
    }

    private void setNumerButtonsOnClickListeners(){
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(currentInputView.getText().length() == 0)){
                    onDigitInput(0);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDigitInput(1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDigitInput(2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDigitInput(3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDigitInput(4);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDigitInput(5);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDigitInput(6);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDigitInput(7);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDigitInput(8);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDigitInput(9);
            }
        });
    }
}
