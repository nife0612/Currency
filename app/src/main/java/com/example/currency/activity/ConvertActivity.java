package com.example.currency.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.currency.Modules;
import com.example.currency.R;

public class ConvertActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        transitAdapter();
    }

    private void transitAdapter(){

        TextView tv_char_code      = findViewById(R.id.char_code);
        TextView tv_name           = findViewById(R.id.name);
        TextView tv_value          = findViewById(R.id.value);
        TextView tv_previous_value = findViewById(R.id.previous_value);
        Button button              = findViewById(R.id.button);
        EditText editText          = findViewById(R.id.editTextNumberDecimal);

        TextView rub_to_currency   = findViewById(R.id.rub_to_currency);
        TextView currency_to_rub   = findViewById(R.id.currency_to_rub);

        Bundle bundle = getIntent().getExtras();

        tv_char_code.setText(bundle.getString("char_code"));
        tv_name.setText(bundle.getString("name"));
        tv_value.setText(bundle.getString("value"));
        tv_previous_value.setText(bundle.getString("previous_value"));

         Modules.textColoring(tv_previous_value, tv_previous_value.getText().toString());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String et_text = editText.getText().toString();

                if(!et_text.isEmpty()){
                    double value = Double.parseDouble(et_text);
                    double currency = Double.parseDouble(tv_value.getText().toString());
                    Double c = value * currency;
                    Double d = value / currency;
                    rub_to_currency.setText("Конвертация  "+Modules.myFormat(c));
                    currency_to_rub.setText(Modules.myFormat(d));
                }
            }
        });

    }



}