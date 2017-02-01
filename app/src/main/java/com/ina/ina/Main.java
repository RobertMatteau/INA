package com.ina.ina;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent result) {
        super.onActivityResult(reqCode, resCode, result);
        if (reqCode == 2){
            if(resCode == Activity.RESULT_OK){
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(getApplicationContext(), "Login Incorrect", Toast.LENGTH_SHORT).show();

            }
        }
    }


    public void loginClick(View v) {
        Intent intent2 = new Intent(this, LoginActivity.class);
        startActivityForResult(intent2, 2);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public void signupClick(View v) {
        Intent intent3 = new Intent(this, SignUpActivity.class);
        startActivityForResult(intent3, 3);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}
