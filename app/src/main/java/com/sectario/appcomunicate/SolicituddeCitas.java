package com.sectario.appcomunicate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SolicituddeCitas extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudde_citas);
        spinner = findViewById(R.id.spinner);

        ArrayList<TipoSolicitud> tipoSolicitud = new ArrayList<>();
        TipoSolicitud t1=new TipoSolicitud();
        t1.setId(1);
        t1.setNombre("Asesoria");
        tipoSolicitud.add(t1);

        TipoSolicitud t2=new TipoSolicitud();
        t2.setId(2);
        t2.setNombre("Matricula");
        tipoSolicitud.add(t2);

        ArrayAdapter<TipoSolicitud> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,tipoSolicitud);

        spinner.setAdapter(adapter);

    }
}