package com.ina.ina;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SignUpActivity extends AppCompatActivity{




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

    }



    public void diseases(View v)
    {
        Intent intent2 = new Intent(this, DiseaseActivity.class);
        startActivityForResult(intent2, 2);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }



    public void signUp(View v)
    {
        Intent intent2 = new Intent(this, LoginActivity.class);
        startActivityForResult(intent2, 2);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}
