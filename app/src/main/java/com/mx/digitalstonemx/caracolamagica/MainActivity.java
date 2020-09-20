package com.mx.digitalstonemx.caracolamagica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mx.digitalstonemx.utils.Constantes;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    /*public static String FACEBOOK_URL = "https://www.facebook.com/MarioWolfXOficial";
    public static String FACEBOOK_PAGE_ID = "MarioWolfXOficial";*/

    private ImageButton btnPreguntar;
    private ImageButton btnSalir;
    private ImageButton btnSeguir;
    private TextView textViewPregunta;
    //private final String GREETER = "Hola desde el otro lado";
    private String PREGUNTA = "";
    //Se declara la longitud d elas respuestas aleatorias.
    int longitudMp = 6;
    int indice = 0;

    //Se crea vector con la posicion de respuestas aleatoras mas las fijas como sonidos de inicio, cierre , instrucciones y esater eggs
    MediaPlayer vectormp [] = new MediaPlayer[longitudMp + 6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //se oculta el action bar
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //se setea el icono de la aplicacion.
        /*getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);*/

        //Se crea vector de Sonidos
        //Se agregan respuestas
        vectormp[indice] = MediaPlayer.create(this, R.raw.caracola_respuesta_aguelita_soy_tu_nieto);
        vectormp[++indice] = MediaPlayer.create(this, R.raw.caracola_respuesta_si);
        vectormp[++indice] = MediaPlayer.create(this, R.raw.caracola_respuesta_nel_perro);
        vectormp[++indice] = MediaPlayer.create(this, R.raw.caracola_respuesta_nio);
        vectormp[++indice] = MediaPlayer.create(this, R.raw.caracola_respuesta_pa_que_quiere_saber_eso_jejeje);
        vectormp[++indice] = MediaPlayer.create(this, R.raw.caracola_respuesta_prueba_preguntando_de_nuevo);

        //Se agregan los sonidos de las instrucciones
        vectormp[++indice] = MediaPlayer.create(this, R.raw.caracola_instruccion_debes_preguntar_correctamente);
        vectormp[++indice] = MediaPlayer.create(this, R.raw.caracola_instruccion_escribe_una_pregunta);

        //Se agregan sonido de inicio
        vectormp[++indice] = MediaPlayer.create(this, R.raw.caracola_inicio);

        //Se agregan sonido de cierre
        vectormp[++indice] = MediaPlayer.create(this, R.raw.caracola_cierre);

        //Se agregan easter eggs
        vectormp[++indice] = MediaPlayer.create(this, R.raw.caracola_z_respuesta_easter_egg1);
        vectormp[++indice] = MediaPlayer.create(this, R.raw.caracola_z_respuesta_easter_egg2);

        //Bienvenida al programa
        //Toast.makeText(MainActivity.this, "Hola :)", Toast.LENGTH_SHORT).show();
        vectormp[longitudMp + Constantes.BIENVENIDA].start();

        //Se agrega funcionalidad al boton Preguntar
        btnPreguntar = (ImageButton) findViewById(R.id.botonPregunta);
        btnPreguntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewPregunta = (TextView) findViewById(R.id.editTextPregunta);
                PREGUNTA =  textViewPregunta.getText().toString();

                //valida que la pregunta no sea vacia
                if(PREGUNTA != null && !PREGUNTA.equalsIgnoreCase("")){

                    //valida si la pregunta tiene signo de puntuacion.
                    if(validaPregunta(PREGUNTA)){
                        //Valida si es un easter egg
                        if(validaEasterEgg(PREGUNTA.toLowerCase(), vectormp)){
                            
                        } else {
                            Random rand = new Random();
                            int numeroRandom = rand.nextInt(longitudMp);
                            vectormp[numeroRandom].start();
                        }
                        textViewPregunta.setText("");
                    } else {
                        vectormp[longitudMp].start();
                        //Toast.makeText(MainActivity.this, "Debes escribir tu Pregunta Correctamente.", Toast.LENGTH_SHORT).show();
                        //textViewPregunta.setText("");
                    }

                } else {
                    vectormp[longitudMp + Constantes.RESPUESTA_DEBES_PREGUNTAR].start();
                    //Toast.makeText(MainActivity.this, "Debes escribir tu Pregunta.", Toast.LENGTH_SHORT).show();
                }

            /*Intent intent = new Intent(MainActivity.this, secondActivity.class);
            intent.putExtra("greeter", GREETER);
            intent.putExtra("pregunta", PREGUNTA);
            startActivity(intent);*/
            }
        });

        //Se agrega funcionalidad al boton Salir
        btnSalir = (ImageButton) findViewById(R.id.botonSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Bye :(", Toast.LENGTH_SHORT).show();
                vectormp[longitudMp + Constantes.SALIR].start();
                finish();

            }
        });

        //Se agrega funcionalidad al boton Seguir
        btnSeguir = (ImageButton) findViewById(R.id.botonSeguir);
        btnSeguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String facebookId = "fb://page/2389396411112671";
                String urlPage = "https://www.facebook.com/MarioWolfXOficial";

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId )));
                } catch (Exception e) {
                    //Abre url de pagina.
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
                }

            }
        });
    }

    /**
     * @param pregunta
     * @param vectormp
     * @return @code String
     */
    private boolean validaEasterEgg(String pregunta, MediaPlayer[] vectormp) {
        boolean esEasterEgg = false;

        if(pregunta.contains("angel") && pregunta.contains("crack") && pregunta.contains("free fire")){
            vectormp[longitudMp + Constantes.RESPUESA_EASTER_EGG1].start();
            esEasterEgg = true;
        } else {
            if(pregunta.contains("juan") && pregunta.contains("crack") && pregunta.contains("free fire")){
                vectormp[longitudMp + Constantes.RESPUESA_EASTER_EGG2].start();
                esEasterEgg = true;
            }
        }
        return esEasterEgg;
    }

    /**
     * @param cadena
     * @return @code String
     */
    public boolean validaPregunta (String cadena){
        boolean continua = false;
        String textoBuscado = "?";
        // Contador de ocurrencias
        int contador = 0;

        while (cadena.indexOf(textoBuscado) > -1) {
            cadena = cadena.substring(cadena.indexOf(
                    textoBuscado) + textoBuscado.length(), cadena.length());
            contador++;
        }
        if(contador == 1){
            continua = true;
        }
        //se cambia a true por que se quejan mucho
        continua = true;
        return continua;
    }

}
