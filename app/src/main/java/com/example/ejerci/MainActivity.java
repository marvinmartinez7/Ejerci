package com.example.ejerci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button volley,restr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volley=(Button)findViewById(R.id.btnVolley);
        restr=(Button)findViewById(R.id.btnretro);

        volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voll();
            }
        });

        restr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit();
            }
        });


    }

    private void Retrofit() {
        Intent Ret=new Intent(this,ActivityRestApi.class);
        startActivity(Ret);
    }

    private void voll() {
        Intent vol=new Intent(this,VolleyActivity.class);
        startActivity(vol);
    }
}