package com.example.ejerci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ejerci.Interfaces.interfaces;
import com.example.ejerci.Models.Usuarios;

import java.util.ArrayList;
import java.util.List;

import  retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;
import  retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityRestApi extends AppCompatActivity {
    ListView listView;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter adapter;
    EditText txtbuscar;
    Button btnbuscar, btnlistar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);
        listView = (ListView) findViewById(R.id.listaR);
        txtbuscar = (EditText) findViewById(R.id.textbuscarR);
        btnbuscar = (Button) findViewById(R.id.btnBuscar);
        btnlistar= (Button) findViewById(R.id.btnlistar);

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imprimir();


            }
        });

        btnlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerDatos();

            }
        });

    }
    //////////////////////
    private void imprimir(){

        if(txtbuscar.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresar un id ", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        interfaces interfacesUsers = retrofit.create(interfaces.class);

        titles = new ArrayList<>();


        Call<Usuarios> request = interfacesUsers.getUsuario(txtbuscar.getText().toString());

        request.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {

                if(response.body() != null){
                    Usuarios usuario = response.body();

                    titles.add(usuario.title);

                    adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, titles);

                    listView.setAdapter(adapter);
                }else {
                    Toast.makeText(getApplicationContext(), "No existe ", Toast.LENGTH_SHORT).show();
                    adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
                    listView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No se pudieron cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });





    }

    private void obtenerDatos() {

        txtbuscar.setText(null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        interfaces interfacesUsers = retrofit.create(interfaces.class);

        Call<List<Usuarios>> request = interfacesUsers.getUsusuarios();

        titles = new ArrayList<>();

        request.enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {

                for(Usuarios usuario: response.body()){
                    titles.add(usuario.getTitle());
                }

                adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, titles);

                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {

            }
        });


    }
}