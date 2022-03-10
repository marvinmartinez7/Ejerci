package com.example.ejerci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class VolleyActivity extends AppCompatActivity {
    ListView lista;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter adapterp;
    EditText Buscar;
    Button btnbuscar, btnlistarV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        Buscar = (EditText) findViewById(R.id.txtbuscarV);
        btnbuscar = (Button) findViewById(R.id.btnvolley);
        btnlistarV = (Button) findViewById(R.id.btnlistarV);
        lista = (ListView) findViewById(R.id.listaV);

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();

            }
        });
        btnlistarV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListarVoll();

            }
        });

    }

    private void ListarVoll() {
        Buscar.setText(null);
        RequestQueue cola = Volley.newRequestQueue(this);
        String endpoint = "https://jsonplaceholder.typicode.com/posts";
        titles = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                endpoint,
                null,
                new com.android.volley.Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i =0; i< response.length();i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());

                                titles.add(jsonObject.getString("title"));

                            }catch (Exception e){}

                        }

                        adapterp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, titles);
                        lista.setAdapter(adapterp);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }


        );

        cola.add(request);
    }

    private void Buscar() {

        if(Buscar.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "ingresa un id", Toast.LENGTH_SHORT).show();
            return;
        }


        RequestQueue cola = Volley.newRequestQueue(this);

        String endpoint = "https://jsonplaceholder.typicode.com/posts/"+Buscar.getText().toString();


        titles = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                endpoint,
                null,
                new com.android.volley.Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {



                        try {
                            titles.add(response.getString("title"));

                            adapterp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, titles);

                            lista.setAdapter(adapterp);
                        }catch (Exception e){

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        adapterp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, titles);

                        lista.setAdapter(adapterp);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        adapterp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
                        lista.setAdapter(adapterp);
                        Toast.makeText(getApplicationContext(), "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


        );

        cola.add(request);
    }


}