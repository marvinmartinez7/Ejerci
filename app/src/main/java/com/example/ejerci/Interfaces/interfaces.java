package com.example.ejerci.Interfaces;
import com.example.ejerci.Models.Usuarios;
import  java.util.List;

import retrofit2.Call;
import  retrofit2.http.Path;
import retrofit2.http.PATCH;
import retrofit2.http.GET;

public interface interfaces {
      String Ruta0 = "/posts";
      String Ruta1 = "/posts/{value}";

      @GET(Ruta0)
      Call<List<Usuarios>>getUsusuarios();

      @GET(Ruta1)
      Call<Usuarios> getUsuario(@Path("value") String value);
}
