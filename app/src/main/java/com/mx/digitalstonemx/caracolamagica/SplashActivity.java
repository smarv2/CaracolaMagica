package com.mx.digitalstonemx.caracolamagica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private final int DURACION_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //se oculta el action bar
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //Animaciones
        Animation animacion_desp_arriba = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion_desp_abajo = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);
        Animation animacion_desp_izquieda = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_izquierda);
        Animation animacion_desp_derecha = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_derecha);
        Animation animacion3 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_izquierda);

        ImageView imgvdsLogo = findViewById(R.id.dsLogo);
        imgvdsLogo.setAnimation(animacion_desp_arriba);
        TextView txtdesigned = findViewById(R.id.txtDesigned);
        txtdesigned.setAnimation(animacion_desp_derecha);
        TextView txtmariowolfx = findViewById(R.id.txtMarioWolfX);
        txtmariowolfx.setAnimation(animacion_desp_izquieda);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}