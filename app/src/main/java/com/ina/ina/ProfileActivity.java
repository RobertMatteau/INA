package com.ina.ina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    ArrayList<String> selection = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
    }

    public void selectItem(View view) {
        boolean checked = ((CheckBox) view).isChecked();


    }
    public void allergies(View v)
    {
        Intent intent2 = new Intent(this, DiseaseActivity.class);
        startActivityForResult(intent2, 2);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
    public void save(View v)
    {
        Intent intent2 = new Intent(this, MainPageActivity.class);
        startActivityForResult(intent2, 2);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}
