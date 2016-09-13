package edu.orangecoastcollege.cs273.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Nguyen Hoang C02288487
 * TipCalculator
 */

public class MainActivity extends AppCompatActivity {

    private static NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static NumberFormat percent = NumberFormat.getPercentInstance();

    // Associate the controller with the needed views
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView percentTipsTextView;
    private TextView tipAmountTextView;
    private TextView totalAmountTextView;
    private SeekBar percentTipSeekBar;

    // Associate the controller with the needed model
    RestaurantBill currentBill = new RestaurantBill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the controller with the widgets in our app
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTipsTextView = (TextView) findViewById(R.id.percentTipstextView);
        tipAmountTextView = (TextView) findViewById(R.id.tipAmountTextView);
        totalAmountTextView = (TextView) findViewById(R.id.totalAmountTextView);
        percentTipSeekBar = (SeekBar) findViewById(R.id.percentTipsSeekBar);

        // Define a listener of the amountEditText (onTextChanged)
        amountEditText.addTextChangedListener(amountTextChangedListenerTextWatcher); // any TextWatcher name here

        // Define a lisener for the percentSeekBar (onProgressCHanged);
        percentTipSeekBar.setOnSeekBarChangeListener(pecentChangedListener);
    }

    private TextWatcher amountTextChangedListenerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Try to get the amount from amountEditText
            try{
                if (charSequence.length() > 0){
                    double amount = Double.parseDouble(charSequence.toString()) / 100.0;
                    currentBill.setmAmount(amount);
                }
                else{
                    currentBill.setmAmount(0.0);
                }
            }
            catch (NumberFormatException e)
            {
                amountEditText.setText("");
            }

            // No exception, input is valid:
            // 1) Set the bill amount (amountTextView)
            amountTextView.setText(currency.format(currentBill.getmAmount()));
            updateViews();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Do nothing
        }
    };

    private SeekBar.OnSeekBarChangeListener pecentChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) { // i is the number of progress
            // Update the model with the new tip amount
            currentBill.setmTipPercent(i / 100.0);

            // Update the percentTextView
            percentTipsTextView.setText(percent.format(currentBill.getmTipPercent()));

            // Update the views
            updateViews();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void updateViews()
    {
        currentBill.recalculateAmount();

        // 2)  Set the tip amount (tipTextView)
        //tipTextView.setText(String.valueOf(currentBill.getMtipAmount()));
        tipAmountTextView.setText(currency.format(currentBill.getMtipAmount()));

        // 3) Set the total amount (totalAmountTextView)
        totalAmountTextView.setText(currency.format(currentBill.getmTotalAmount()));
    }

}
