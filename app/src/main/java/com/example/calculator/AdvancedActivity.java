package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdvancedActivity extends AppCompatActivity {
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

    private Button buttonSin;
    private Button buttonCos;
    private Button buttonTan;
    private Button buttonLn;
    private Button buttonSqrt;
    private Button buttonPower2;
    private Button buttonPower;
    private Button buttonLog;

    private Button buttonBackspace;
    private Button buttonClear;
    private Button buttonChangeSign;

    private TextView currentInputView;
    private TextView registryView;

    private double currentResultDouble = 0;
    private boolean firstOp = true;
    private boolean displayingResult = false;

    private char lastOp = '+';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
        bindUIButtons();
        bindUIViews();

        setNumerButtonsOnClickListeners();
        setInputManagementButtonsOnClickListeners();
        setOperationsButtonsOnClickListeners();
        setFunctionsButtonsOnClickListeners();

        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
        }
    }

    private void onDigitInput(int digit){
        if (displayingResult){
            currentInputView.setText("");
            displayingResult = false;
        }
        currentInputView.setText(currentInputView.getText() + String.valueOf(digit));
    }

    private void performOperationDouble(char op){
        double inputNumber = Double.valueOf((String) currentInputView.getText());

        if (firstOp & "scntrp".indexOf(op) == -1) {
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
            case 's': // Sinus
                currentInputView.setText(Double.toString(Math.sin(inputNumber)));

                if (displayingResult){
                    currentResultDouble = Math.sin(inputNumber);
                }
                break;
            case 'c': // Cosinus
                currentInputView.setText(Double.toString(Math.cos(inputNumber)));

                if (displayingResult){
                    currentResultDouble = Math.cos(inputNumber);
                }
                break;
            case 't': // Tangens
                currentInputView.setText(Double.toString(Math.tan(inputNumber)));

                if (displayingResult){
                    currentResultDouble = Math.tan(inputNumber);
                }
                break;
            case 'n': // Natural logarithm
                currentInputView.setText(Double.toString(Math.log(inputNumber)));

                if (displayingResult){
                    currentResultDouble = Math.log(inputNumber);
                }
                break;
            case 'r': // Square root
                currentInputView.setText(Double.toString(Math.sqrt(inputNumber)));

                if (displayingResult){
                    currentResultDouble = Math.sqrt(inputNumber);
                }
                break;
            case 'p': // Power of two
                currentInputView.setText(Double.toString(Math.pow(inputNumber, 2)));

                if (displayingResult){
                    currentResultDouble = Math.pow(inputNumber, 2);
                }
                break;
            case 'q': // Power of y
                currentResultDouble = Math.pow(currentResultDouble, inputNumber);
                break;
            case 'l': // Logarithm
                currentResultDouble = Math.log(currentResultDouble) / Math.log(inputNumber);
                break;
            default:
                currentResultDouble += inputNumber;
                break;
        }
    }

    private void validateOperation(char op){
        if (displayingResult && "scntrp".indexOf(op) == -1){
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

            if ("scntrp".indexOf(op) == -1){
                performOperationDouble(lastOp);
            }
            else{
                performOperationDouble(op);
            }
        }

        if (firstOp) firstOp = false;

        registryView.setText(Double.toString(currentResultDouble));

        if ("scntrp".indexOf(op) == -1){
            lastOp = op;
            currentInputView.setText("");
        }

        if (op == 'q'){
            registryView.setText(registryView.getText() + "^");
        }
        else if (op == 'l'){
            registryView.setText(registryView.getText() + " " + "log");
        }
        else if ("scntrp".indexOf(op) != -1){
            registryView.setText(registryView.getText());
        }
        else {
            registryView.setText(registryView.getText() + " " + lastOp);
        }
    }

    private void finalizeResult(){
        validateOperation(lastOp);

        currentInputView.setText(Double.toString(currentResultDouble));
        registryView.setText(Double.toString(currentResultDouble));

        displayingResult = true;
    }

    private void reset(){
        currentResultDouble = 0;
        firstOp = true;
        lastOp = '+';
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

        buttonSin = (Button) findViewById(R.id.buttonSin);
        buttonCos = (Button) findViewById(R.id.buttonCos);
        buttonTan = (Button) findViewById(R.id.buttonTan);
        buttonLn = (Button) findViewById(R.id.buttonLn);
        buttonSqrt = (Button) findViewById(R.id.buttonSqrt);
        buttonPower2 = (Button) findViewById(R.id.buttonPower2);
        buttonPower = (Button) findViewById(R.id.buttonPower);
        buttonLog = (Button) findViewById(R.id.buttonLog);

        buttonBackspace = (Button) findViewById(R.id.buttonBackspace);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonChangeSign = (Button) findViewById(R.id.buttonChangeSign);
    }

    private void bindUIViews(){
        currentInputView = (TextView) findViewById(R.id.currentInputView);
        registryView = (TextView) findViewById(R.id.registryView);
    }


    private void setFunctionsButtonsOnClickListeners(){
        buttonSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOperation('s');
            }
        });

        buttonCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOperation('c');
            }
        });

        buttonTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOperation('t');
            }
        });

        buttonLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOperation('n');
            }
        });

        buttonSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOperation('r');
            }
        });

        buttonPower2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOperation('p');
            }
        });

        buttonPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOperation('q');
            }
        });

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOperation('l');
            }
        });
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

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putDouble("currentResultDouble", currentResultDouble);
        state.putBoolean("firstOp", firstOp);
        state.putBoolean("displayingResult", displayingResult);
        state.putChar("lastOp", lastOp);

        state.putString("currentInput", (String) currentInputView.getText());
        state.putString("currentRegistry", (String) registryView.getText());
    }

    private void restoreState(Bundle state){
        currentResultDouble = state.getDouble("currentResultDouble");
        firstOp = state.getBoolean("firstOp");
        displayingResult = state.getBoolean("displayingResult");
        lastOp = state.getChar("lastOp");

        currentInputView.setText(state.getString("currentInput"));
        registryView.setText(state.getString("currentRegistry"));
    }
}
