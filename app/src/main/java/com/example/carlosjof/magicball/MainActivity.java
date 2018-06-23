package com.example.carlosjof.magicball;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewRespuesta;
    String[] respuestas;
    Random r;

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int move = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Utilizamos el manejador de movimiento.
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        assert sensorManager != null;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor == null)
            finish();

        //Instanciamos el Evento del sensor.
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                if (x < -5 && move == 0 || x > 5 && move == 1) {
                    move++;

                    respuestas = getResources().getStringArray(R.array.respuesta);

                    textViewRespuesta.setText(respuestas[r.nextInt(21)]);


                    Animation zoom_in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_in);
                    textViewRespuesta.startAnimation(zoom_in);
                }
                if (move == 2) {
                    revelacion();
                    move = 0;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        start();

        r = new Random();

        textViewRespuesta = findViewById(R.id.txtrespuesta);

        final String font_path = "fonts/fuente.TTF";

        Typeface TF = Typeface.createFromAsset(getAssets(), font_path);

        textViewRespuesta.setTypeface(TF);

        Button btn = findViewById(R.id.btnotro);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ayudar.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void revelacion() {

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.aparicion);
        mediaPlayer.start();
    }

    private void start() {
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void stop() {
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onResume() {
        start();
        super.onResume();
    }


}
