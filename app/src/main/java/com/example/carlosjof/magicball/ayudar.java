package com.example.carlosjof.magicball;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ayudar extends AppCompatActivity {
    TextView textViewTitulo;
    TextView textViewCuerpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayudar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textViewTitulo = findViewById(R.id.titulo);
        textViewCuerpo = findViewById(R.id.textView2);

        String fuentes = "fonts/fuente.TTF";

        Typeface TF = Typeface.createFromAsset(getAssets(), fuentes);

        textViewTitulo.setTypeface(TF);
        textViewCuerpo.setTypeface(TF);

        Button btn = findViewById(R.id.cerrar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

