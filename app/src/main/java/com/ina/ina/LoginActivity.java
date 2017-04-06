package com.ina.ina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.StringDef;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    TextView tx1;
    Button b1, b2;
    EditText ed1, ed2;
    int counter = 3;
    String loginurl = "";
    Intent i = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void loggingIn(View v){



        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        tx1=(TextView)findViewById(R.id.textView3);


        b1=(Button)findViewById(R.id.button);


        String emaill = ed1.getText().toString();
        String passwordl = ed2.getText().toString();

        if (ed1.getText().toString().equals("admin") && ed2.getText().toString().equals("admin")) {
            setResult(Activity.RESULT_OK, i);

            Intent intent4 = new Intent(this, MainPageActivity.class);
            startActivityForResult(intent4, 4);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
        else {
            loginurl = "http://diet.uoitscheduler.com/login_api?email=" + emaill + "&password=" + passwordl;

            new LoginTest(this).execute();
        }
            /*else {

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

    private class LoginTest extends AsyncTask<URL, Void, String>{
        Context context;

        public LoginTest(Context context){
            this.context = context;
        }

        @Override
        protected String doInBackground(URL... params){

            String line;
            String value = "";
            try{
                URL url = new URL(loginurl);
                HttpURLConnection con;
                con = (HttpURLConnection) url.openConnection();
                int result = con.getResponseCode();
                if (result == HttpURLConnection.HTTP_OK){
                    InputStream in = con.getInputStream();
                    BufferedReader bf = new BufferedReader(new InputStreamReader(in));

                    while((line = bf.readLine()) != null){
                        value += line;
                    }
                }
            }
            catch(IOException e){

            }

            return value;
            }

            @Override
        protected void onPostExecute(String result){

            if(result.equals("-1")){
                //doesn't work
            }
            else{
                setResult(Activity.RESULT_OK, i);

                Intent intent4 = new Intent(context, MainPageActivity.class);
                intent4.putExtra("userid", result);
                //intent4.putExtra("email", email);
                startActivity(intent4);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        }
    }





    public void signup(View v) {
        Intent intent5 = new Intent(this, SignUpActivity.class);
        startActivityForResult(intent5, 5);
    }

    public void cancel(View v){

        finish();

    }
}
