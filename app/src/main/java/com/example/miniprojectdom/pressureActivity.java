package com.example.miniprojectdom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;

public class pressureActivity extends AppCompatActivity {
    Spinner spinner1;
    Spinner spinner2;
    EditText editText;
    Button btnSave;
    TextView result;
    String[] unitPressure = {"Pa","bar","atm"};
    HashMap<String, BigDecimal> map = new HashMap<String, BigDecimal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);

        getSupportActionBar().setTitle("Pressure Converter");

        editText = findViewById(R.id.editPressure);
        spinner1 = findViewById(R.id.spFromPressure);
        spinner2 = findViewById(R.id.spToPressure);
        result = findViewById(R.id.stText);
        btnSave = findViewById(R.id.btnSaveEnergy);

        map.put("Pa/Pa", new BigDecimal(1.0));
        map.put("Pa/bar", new BigDecimal(0.00001));
        map.put("Pa/atm", new BigDecimal(0.00000987));


        map.put("bar/Pa", new BigDecimal(100000));
        map.put("bar/bar", new BigDecimal(1.0));
        map.put("bar/atm", new BigDecimal(0.98692));


        map.put("atm/Pa", new BigDecimal(101325));
        map.put("atm/bar", new BigDecimal(0.980665));
        map.put("atm/atm", new BigDecimal(0.967841));

        ArrayAdapter ad =  new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,unitPressure);
        spinner1.setAdapter(ad);

        ArrayAdapter ad2 =  new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,unitPressure);
        spinner2.setAdapter(ad2);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double amount = 0.0;
                Double totale = 0.0;
                amount = Double.parseDouble(editText.getText().toString());
                String units = spinner1.getSelectedItem().toString()+"/"+ spinner2.getSelectedItem().toString();
                totale = map.get(units).doubleValue() * amount;
                totale = Double.parseDouble(new DecimalFormat("#########.############").format(totale));
                result.setText("The result is: " + totale.toString() + " " + spinner2.getSelectedItem().toString());
                editText.setText("");
            }
        });
    }
}

