package com.sectario.appcomunicate.interfaz;


import com.sectario.appcomunicate.agendarcita.Cita;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Citaapi {
    @GET("api/cita/{id}")
    public Call<Cita> find(@Path("id") Integer id);

    @POST("api/cita/")
    public Call<Cita> save(@Body Cita c);

    @DELETE("api/cita/{id}")
    public Call<Void> delete(@Path("id") Integer id);

}