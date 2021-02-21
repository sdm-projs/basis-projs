package com.example.carloancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Create Objects
    private EditText input1;
    private EditText input2;
    private EditText input3;
    private SeekBar seekBar;
    private TextView tv_result, textViewProgress;

    // Create onCreate function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Input variables
        input1 = (EditText) findViewById(R.id.uInput1);
        input2 = (EditText) findViewById(R.id.uInput2);
        input3 = (EditText) findViewById(R.id.uInput3);

        // Create a button
        final Button calcButton = (Button) findViewById(R.id.bt_calculate);


        // Create textView to display loan results
        tv_result = (TextView) findViewById(R.id.tv_result);

        // Set button onClick actions
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCalculations();

                buttonEffect(calcButton);
            }
        });

        // Set up seekBar and associated textViewProgress view to show the stages
        // of seek bar settings
        textViewProgress = (TextView) findViewById(R.id.textViewProgress);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(4);
        seekBar.setProgress(0);

        // Control seek bar attributes
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            // When progress on Seek Bar changes, update textViewProgress
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                if (progress == 0)
                    textViewProgress.setText("No Loan");
                else {
                    int loanYears = progress + 1;
                    textViewProgress.setText("" + loanYears + " Years");
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    // Calculation function
    private void makeCalculations() {

        double n1 = Double.valueOf(input1.getText().toString());
        double n2 = Double.valueOf(input2.getText().toString());
        double n3 = Double.valueOf(input3.getText().toString());

        double result = 0.00, interestOnLoan, interestAmt, months;
        int years = seekBar.getProgress(), yearsToMonths, totalMonths;
        // If seek bar == 0, do this
        if (years == 0) {
            yearsToMonths = 0;
            interestOnLoan = n3 / 100.0;
            interestAmt = (n1 - n2) * interestOnLoan;
            totalMonths = yearsToMonths * 0;
            result = n1 - n2;
        }
        // If seek bar == 1-4, do this
        else {
            yearsToMonths = years + 1;
            interestOnLoan = ( n3 / 100.0 ) / 12;
            //interestAmt = (n1 - n2) * interestOnLoan;
            totalMonths = yearsToMonths * 12;
            result = n1 * ( interestOnLoan * (Math.pow((1+interestOnLoan), totalMonths ) ) ) / ( ( Math.pow( (1+interestOnLoan), totalMonths) ) - 1 ) ;
        }

        // Convert result to string to print
        double totalPayment = result * totalMonths;
        String totalPaymentStr = String.format("%.2f", totalPayment);
        String resultStr = String.format("%.2f", result);

        // Set print statements based on results
        if (years == 0)
            tv_result.setText("\nThe results are in:\n $" + resultStr + " with no loan.");
        else
            tv_result.setText("\nThe results are in:\n $" + resultStr + " per month\nover " + totalMonths + " months.\nTotal Payment Amount: $" + totalPaymentStr);
    }

    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0x33333333, PorterDuff.Mode.SRC_ATOP);
                       // v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_BUTTON_RELEASE: {
                       // v.getBackground().setColorFilter();
                    }
                }
                return false;
            }
        });
    }
}



