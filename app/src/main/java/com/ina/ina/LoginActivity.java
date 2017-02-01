package com.ina.ina;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView tx1;
    Button b1, b2;
    EditText ed1, ed2;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void loggingIn(View v){

        Intent i = new Intent();

        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        tx1=(TextView)findViewById(R.id.textView3);


        b1=(Button)findViewById(R.id.button);

        if (ed1.getText().toString().equals("admin") && ed2.getText().toString().equals("admin")) {
            setResult(Activity.RESULT_OK, i);

            Intent intent4 = new Intent(this, MainPageActivity.class);
            startActivityForResult(intent4, 4);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        } /*else {

            setResult(Activity.RESULT_CANCELED, i);

            tx1.setVisibility(View.VISIBLE);
            tx1.setBackgroundColor(Color.RED);
            counter--;
            tx1.setText(String.format(counter));

            if (counter == 0)
            {
                b1.setEnabled(false);
            }

        }*/
    }
    public void signup(View v) {
        Intent intent5 = new Intent(this, SignUpActivity.class);
        startActivityForResult(intent5, 5);
    }

    public void cancel(View v){

        finish();

    }
}
