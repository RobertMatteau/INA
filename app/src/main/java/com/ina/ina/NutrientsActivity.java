package com.ina.ina;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class NutrientsActivity extends AppCompatActivity {

    double vita, vitd,vite,vitk,vitc,thia,ribo,nia,vitb6,fol,vitb12,panth,chol,calc,copp,fluor,iron,magne,manga,phos,sele,zinc,pota,sodi,carb,tpro,tfib;
    String t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27;
    int i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15,i16,i17,i18,i19,i20,i21,i22,i23,i24,i25,i26,i27;

    TextView pv1,pv2,pv3,pv4,pv5,pv6,pv7,pv8,pv9,pv10,pv11,pv12,pv13,pv14,pv15,pv16,pv17,pv18,pv19,pv20,pv21,pv22,pv23,pv24,pv25,pv26,pv27;
    ProgressBar p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrients);

        Intent get = getIntent();

        vita = get.getDoubleExtra("vita",0.0);
        vitb6 =get.getDoubleExtra("vitb6",0.0);
        vitb12 =get.getDoubleExtra("vitb12",0.0);
        vitc = get.getDoubleExtra("vitc",0.0);
        vitd = get.getDoubleExtra("vitd",0.0);
        vite =get.getDoubleExtra("vite",0.0);
        vitk = get.getDoubleExtra("vitk",0.0);
        thia =get.getDoubleExtra("thia",0.0);
        ribo =get.getDoubleExtra("ribo",0.0);
        nia = get.getDoubleExtra("nia",0.0);
        fol = get.getDoubleExtra("fol",0.0);
        panth =get.getDoubleExtra("panth",0.0);
        chol = get.getDoubleExtra("chol",0.0);
        calc = get.getDoubleExtra("calc",0.0);
        copp = get.getDoubleExtra("copp",0.0);
        fluor = get.getDoubleExtra("fluor",0.0);
        iron =get.getDoubleExtra("iron",0.0);
        magne =get.getDoubleExtra("magne",0.0);
        manga =get.getDoubleExtra("manga",0.0);
        phos = get.getDoubleExtra("phos",0.0);
        sele = get.getDoubleExtra("sele",0.0);
        zinc = get.getDoubleExtra("zinc",0.0);
        pota = get.getDoubleExtra("pota",0.0);
        sodi = get.getDoubleExtra("sodi",0.0);
        carb = get.getDoubleExtra("carb",0.0);
        tpro = get.getDoubleExtra("tpro",0.0);
        tfib =get.getDoubleExtra("tfib",0.0);

        /*t1 =Double.toString(vita);
        t2 = Double.toString(vitb6);
        t3 = Double.toString(vitb12);
        t4 = Double.toString(vitc);
        t5 = Double.toString(vitd);
        t6 = Double.toString(vite);
        t7 = Double.toString(vitk);
        t8 = Double.toString(thia);
        t9 = Double.toString(ribo);
        t10 = Double.toString(nia);
        t11 = Double.toString(fol);
        t12 = Double.toString(panth);
        t13 = Double.toString(chol);
        t14 = Double.toString(calc);
        t15 = Double.toString(copp);
        t16 = Double.toString(fluor);
        t17 = Double.toString(iron);
        t18 = Double.toString(magne);
        t19 = Double.toString(manga);
        t20 = Double.toString(phos);
        t21 = Double.toString(sele);
        t22 = Double.toString(zinc);
        t23 = Double.toString(pota);
        t24 = Double.toString(sodi);
        t25 = Double.toString(carb);
        t26 = Double.toString(tpro);
        t27 = Double.toString(tfib);*/

        i1 = (int)vita ;
        i2 = (int)vitb6 ;
        i3 = (int)vitb12 ;
        i4 = (int)vitc ;
        i5 = (int)vitd ;
        i6 = (int)vite ;
        i7 = (int)vitk ;
        i8 = (int)thia ;
        i9 = (int)ribo ;
        i10 = (int)nia ;
        i11 = (int)fol ;
        i12 = (int)panth ;
        i13 = (int)chol ;
        i14 = (int)calc ;
        i15 = (int)copp ;
        i16 = (int)fluor ;
        i17 = (int)iron ;
        i18 = (int)magne ;
        i19 = (int)manga ;
        i20 = (int)phos ;
        i21 = (int)sele ;
        i22 = (int)zinc ;
        i23 = (int)pota ;
        i24 = (int)sodi ;
        i25 = (int)carb ;
        i26 = (int)tpro ;
        i27 = (int)tfib ;
        //*100
        
        t1 = Integer.toString(i1);
        t2 = Integer.toString(i2);
        t3 = Integer.toString(i3);
        t4 = Integer.toString(i4);
        t5 = Integer.toString(i5);
        t6 = Integer.toString(i6);
        t7 = Integer.toString(i7);
        t8 = Integer.toString(i8);
        t9 = Integer.toString(i9);
        t10 = Integer.toString(i10);
        t11 = Integer.toString(i11);
        t12 = Integer.toString(i12);
        t13 = Integer.toString(i13);
        t14 = Integer.toString(i14);
        t15 = Integer.toString(i15);
        t16 = Integer.toString(i16);
        t17 = Integer.toString(i17);
        t18 = Integer.toString(i18);
        t19 = Integer.toString(i19);
        t20 = Integer.toString(i20);
        t21 = Integer.toString(i21);
        t22 = Integer.toString(i22);
        t23 = Integer.toString(i23);
        t24 = Integer.toString(i24);
        t25 = Integer.toString(i25);
        t26 = Integer.toString(i26);
        t27 = Integer.toString(i27);




        pv1 = (TextView)findViewById(R.id.pv1);
        pv2 = (TextView)findViewById(R.id.pv2);
        pv3 = (TextView)findViewById(R.id.pv3);
        pv4 = (TextView)findViewById(R.id.pv4);
        pv5 = (TextView)findViewById(R.id.pv5);
        pv6 = (TextView)findViewById(R.id.pv6);
        pv7 = (TextView)findViewById(R.id.pv7);
        pv8 = (TextView)findViewById(R.id.pv8);
        pv9 = (TextView)findViewById(R.id.pv9);
        pv10 = (TextView)findViewById(R.id.pv10);
        pv11 = (TextView)findViewById(R.id.pv11);
        pv12 = (TextView)findViewById(R.id.pv12);
        pv13 = (TextView)findViewById(R.id.pv13);
        pv14 = (TextView)findViewById(R.id.pv14);
        pv15 = (TextView)findViewById(R.id.pv15);
        pv16 = (TextView)findViewById(R.id.pv16);
        pv17 = (TextView)findViewById(R.id.pv17);
        pv18 = (TextView)findViewById(R.id.pv18);
        pv19 = (TextView)findViewById(R.id.pv19);
        pv20 = (TextView)findViewById(R.id.pv20);
        pv21 = (TextView)findViewById(R.id.pv21);
        pv22 = (TextView)findViewById(R.id.pv22);
        pv23 = (TextView)findViewById(R.id.pv23);
        pv24 = (TextView)findViewById(R.id.pv24);
        pv25 = (TextView)findViewById(R.id.pv25);
        pv26 = (TextView)findViewById(R.id.pv26);
        pv27 = (TextView)findViewById(R.id.pv27);

        p1 = (ProgressBar)findViewById(R.id.p1);
        p2 = (ProgressBar)findViewById(R.id.p2);
        p3 = (ProgressBar)findViewById(R.id.p3);
        p4 = (ProgressBar)findViewById(R.id.p4);
        p5 = (ProgressBar)findViewById(R.id.p5);
        p6 = (ProgressBar)findViewById(R.id.p6);
        p7 = (ProgressBar)findViewById(R.id.p7);
        p8 = (ProgressBar)findViewById(R.id.p8);
        p9 = (ProgressBar)findViewById(R.id.p9);
        p10 = (ProgressBar)findViewById(R.id.p10);
        p11 = (ProgressBar)findViewById(R.id.p11);
        p12 = (ProgressBar)findViewById(R.id.p12);
        p13 = (ProgressBar)findViewById(R.id.p13);
        p14 = (ProgressBar)findViewById(R.id.p14);
        p15 = (ProgressBar)findViewById(R.id.p15);
        p16 = (ProgressBar)findViewById(R.id.p16);
        p17 = (ProgressBar)findViewById(R.id.p17);
        p18 = (ProgressBar)findViewById(R.id.p18);
        p19 = (ProgressBar)findViewById(R.id.p19);
        p20 = (ProgressBar)findViewById(R.id.p20);
        p21 = (ProgressBar)findViewById(R.id.p21);
        p22 = (ProgressBar)findViewById(R.id.p22);
        p23 = (ProgressBar)findViewById(R.id.p23);
        p24 = (ProgressBar)findViewById(R.id.p24);
        p25 = (ProgressBar)findViewById(R.id.p25);
        p26 = (ProgressBar)findViewById(R.id.p26);
        p27 = (ProgressBar)findViewById(R.id.p27);



        pv1.setText(t1);
        pv2.setText(t2);
        pv3.setText(t3);
        pv4.setText(t4);
        pv5.setText(t5);
        pv6.setText(t6);
        pv7.setText(t7);
        pv8.setText(t8);
        pv9.setText(t9);
        pv10.setText(t10);
        pv11.setText(t11);
        pv12.setText(t12);
        pv13.setText(t13);
        pv14.setText(t14);
        pv15.setText(t15);
        pv16.setText(t16);
        pv17.setText(t17);
        pv18.setText(t18);
        pv19.setText(t19);
        pv20.setText(t20);
        pv21.setText(t21);
        pv22.setText(t22);
        pv23.setText(t23);
        pv24.setText(t24);
        pv25.setText(t25);
        pv26.setText(t26);
        pv27.setText(t27);

        p1.setProgress(i1);
        p2.setProgress(i2);
        p3.setProgress(i3);
        p4.setProgress(i4);
        p5.setProgress(i5);
        p6.setProgress(i6);
        p7.setProgress(i7);
        p8.setProgress(i8);
        p9.setProgress(i9);
        p10.setProgress(i10);
        p11.setProgress(i11);
        p12.setProgress(i12);
        p13.setProgress(i13);
        p14.setProgress(i14);
        p15.setProgress(i15);
        p16.setProgress(i16);
        p17.setProgress(i17);
        p18.setProgress(i18);
        p19.setProgress(i19);
        p20.setProgress(i20);
        p21.setProgress(i21);
        p22.setProgress(i22);
        p23.setProgress(i23);
        p24.setProgress(i24);
        p25.setProgress(i25);
        p26.setProgress(i26);
        p27.setProgress(i27);




        p1.setMax(500);
        p2.setMax(500);
        p3.setMax(500);
        p4.setMax(500);
        p5.setMax(500);
        p6.setMax(500);
        p7.setMax(500);
        p8.setMax(500);
        p9.setMax(500);
        p10.setMax(500);
        p11.setMax(500);
        p12.setMax(500);
        p13.setMax(500);
        p14.setMax(500);
        p15.setMax(500);
        p16.setMax(500);
        p17.setMax(500);
        p18.setMax(500);
        p19.setMax(500);
        p20.setMax(500);
        p21.setMax(500);
        p22.setMax(500);
        p23.setMax(500);
        p24.setMax(500);
        p25.setMax(500);
        p26.setMax(500);
        p27.setMax(500);


    }
}
