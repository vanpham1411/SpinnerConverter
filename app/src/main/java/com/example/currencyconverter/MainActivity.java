package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    String[] currencies = { "USD", "GBP", "AUD", "VND", "EUR","LAK", "RUB", "KRW", "JPY", "CNY"};
    double[] rates = {1.0, 0.76771, 1.4031, 23158.03, 0.84349, 9242.07, 76.1526, 1130.75, 104.74, 6.678 };
    Spinner from;
    Spinner to;
    double result;
    TextView txtResult;
    double currencyFrom, currencyTo;
    EditText amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = findViewById(R.id.txtResult);
        from = (Spinner) findViewById(R.id.spinner_from);
        to = (Spinner) findViewById(R.id.spinner_to);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,R.layout.spinnerstyle,R.id.my_custom_spinner,currencies);
        from.setAdapter(aa);
        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currencyFrom = rates[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currencyTo = rates[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        to.setAdapter(aa);
        amount = findViewById(R.id.edt_amount);
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(amount.getText().equals("")){
                    amount.setText("0");
                    txtResult.setText("");

                }
                else{
                    result = (Double)(Double.parseDouble(amount.getText().toString()) /currencyFrom*currencyTo);
                    Log.v("afterTextChanged",amount.getText().toString());
                    txtResult.setText(new DecimalFormat("##.##").format(result));
                }
            }
        });


    }
}