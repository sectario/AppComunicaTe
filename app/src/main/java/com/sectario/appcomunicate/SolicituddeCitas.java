package com.sectario.appcomunicate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sectario.appcomunicate.agendarcita.Cita;
import com.sectario.appcomunicate.interfaz.Citaapi;

import java.util.Calendar;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SolicituddeCitas extends AppCompatActivity {

    Spinner combo_tiposolicitud;
    EditText edtxPersonName;
    TextView tvfecha;
    TextView tvhora;
    Button btnAgendarCita;
    Button btnCancelarCita;
    Button btnConsultarCita;
    ImageView ivprevius;
    ImageView ivnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudde_citas);

        combo_tiposolicitud = findViewById(R.id.idSpinnerTiposolicitud);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.combo_tiposolicitud, android.R.layout.simple_spinner_item);
        combo_tiposolicitud.setAdapter(adapter);

        edtxPersonName = findViewById(R.id.edtxPersonName);
        tvfecha = findViewById(R.id.tvfecha);
        tvhora = findViewById(R.id.tvhora);
        btnAgendarCita = findViewById(R.id.btnAgendarCita);
        btnCancelarCita = findViewById(R.id.btnCancelarCita);
        btnConsultarCita = findViewById(R.id.btnConsultarCita);
        ivprevius = findViewById(R.id.ivprevius);
        ivnext = findViewById(R.id.ivnext);

        Intent i = new Intent(this, InformationActivity.class);
        ivprevius.setOnClickListener(view -> startActivity(i));

        Intent in = new Intent(this, Calendar.class);
        ivnext.setOnClickListener(view -> startActivity(i));


        btnAgendarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= edtxPersonName.getText().toString();
                String tiposolicitud = combo_tiposolicitud.getSelectedItem().toString();
                String nombresyapellidos = edtxPersonName.getText().toString();
                String fechasolicitud = tvfecha.getText().toString();
                String horasolicitud = tvhora.getText().toString();
                Cita c= new Cita(nombresyapellidos,tiposolicitud, fechasolicitud,horasolicitud);
                agendarcita(c);
            }

        });
        btnConsultarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarPorId(Integer.parseInt(edtxPersonName.getText().toString()));

            }
        });
        btnCancelarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(Integer.parseInt(edtxPersonName.getText().toString()));
            }
        });
    }

    public void agendarcita(Cita c){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://appcomunicate-4d969-default-rtdb.firebaseio.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Citaapi citaapi = retrofit.create(Citaapi.class);
        Call<Cita> call = citaapi.save(c);

        call.enqueue(new Callback<Cita>() {
            @Override
            public void onResponse(Call<Cita> call, Response<Cita> response) {

                try {
                    if (response.isSuccessful()) {

                        Toast.makeText(SolicituddeCitas.this, "Cita Agendada", Toast.LENGTH_SHORT).show();
                        limpiarCampos();

                    }
                }
                catch (Exception e){

                    Toast.makeText(SolicituddeCitas.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Cita> call, Throwable t) {
                Toast.makeText(SolicituddeCitas.this, "No conecta servicio", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buscarPorId(Integer id) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://150.136.144.25:8080/adoptacan/").addConverterFactory(GsonConverterFactory.create()).build();

        Citaapi citaapi = retrofit.create(Citaapi.class);
        Call<Cita> call = citaapi.find(id);
        call.enqueue(new Callback<Cita>() {
            @Override
            public void onResponse(Call<Cita> call, Response<Cita> response) {

                try {
                    if (response.isSuccessful()) {
                        Cita cita = response.body();
                        edtxPersonName.setText(cita.getNombresyapellidos());
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SolicituddeCitas.this,R.array.combo_tiposolicitud, android.R.layout.simple_spinner_item);
                        combo_tiposolicitud.setAdapter(adapter);
                        tvfecha.setText(cita.getFechasolicitud());
                        tvhora.setText(cita.getHorasolicitud());

                    }
                } catch (Exception e) {

                    Toast.makeText(SolicituddeCitas.this, "No hay cita agendada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cita> call, Throwable t) {

            }
        });
    }

    public void eliminar(Integer id) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://150.136.144.25:8080/adoptacan/").addConverterFactory(GsonConverterFactory.create()).build();

        Citaapi citaapi = retrofit.create(Citaapi.class);
        Call<Void> call = citaapi.delete(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                try {
                    if (response.isSuccessful()) {

                        Toast.makeText(SolicituddeCitas.this, "Cita Eliminada", Toast.LENGTH_SHORT).show();
                        limpiarCampos();

                    }
                } catch (Exception e) {

                    Toast.makeText(SolicituddeCitas.this, "No se encontro cita agendada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


    public void limpiarCampos(){

        edtxPersonName.setText("");
        combo_tiposolicitud.setAdapter(null);
        tvfecha.setText("");
        tvhora.setText("");

    }

    public void seleccionarFecha(View view) {

        java.util.Calendar cal = java.util.Calendar.getInstance();
        int anio = cal.get(java.util.Calendar.YEAR);
        int mes = cal.get(java.util.Calendar.MONTH);
        int dia = cal.get(java.util.Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(SolicituddeCitas.this, R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                String fecha = i2 + "/" + i1 + "/" + i;
                tvfecha.setText(fecha);

            }
        } , 2022, mes, dia); dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(229,168,123)));
        dpd.show();

    }

    public void seleccionarHora(View view) {
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int min  = c.get(Calendar.MINUTE);

        TimePickerDialog tmd = new TimePickerDialog(SolicituddeCitas.this,R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                tvhora.setText( i + ":" + i1);

            }
        },0, 0,false);tmd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(229,168,123)));
        tmd.show();
    }



}



