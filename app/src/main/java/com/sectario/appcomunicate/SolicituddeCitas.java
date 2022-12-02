package com.sectario.appcomunicate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sectario.appcomunicate.agendarcita.Cita;
import com.sectario.appcomunicate.interfaz.Citaapi;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SolicituddeCitas extends AppCompatActivity {

    Button btnAgendarCita;
    Button btnCancelarCita;
    Button btnConsultarCita;
    Spinner combo_tiposolicitud;
    EditText edtxId;
    EditText edtxPersonName;
    TextView tvfecha;
    TextView tvhora;
    ImageView ivprevius;
    ImageView ivnext;
    ImageButton ibtninformacion;

    //FirebaseAuth auth =FirebaseAuth.getInstance();

    FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudde_citas);

        combo_tiposolicitud = findViewById(R.id.idSpinnerTiposolicitud);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.combo_tiposolicitud, android.R.layout.simple_spinner_item);
        combo_tiposolicitud.setAdapter(adapter);

        btnAgendarCita = findViewById(R.id.btnAgendarCita);
        btnCancelarCita = findViewById(R.id.btnCancelarCita);
        btnConsultarCita = findViewById(R.id.btnConsultarCita);
        edtxId = findViewById(R.id.edtxId);
        edtxPersonName = findViewById(R.id.edtxPersonName);
        tvfecha = findViewById(R.id.tvfecha);
        tvhora = findViewById(R.id.tvhora);
        ivprevius = findViewById(R.id.ivprevius);
        ivnext = findViewById(R.id.ivnext);
        ibtninformacion = findViewById(R.id.ibtninformacion);
        mfirestore= FirebaseFirestore.getInstance();

        Intent i = new Intent(this, InformationActivity.class);
        ivprevius.setOnClickListener(view -> startActivity(i));

        Intent in = new Intent(this, Calendar.class);
        ivnext.setOnClickListener(view -> startActivity(i));

        Intent im = new Intent(this, InformationActivity.class);
        ibtninformacion.setOnClickListener(view -> startActivity(i));


        btnAgendarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agendar();
            }

        });
        btnConsultarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Consultar();

            }
        });
        btnCancelarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });
    }

    public void agendar(){

        Map<String,Object> cita=new HashMap<>();

        cita.put("Id",edtxId.getText().toString());
        cita.put("tiposolicitud",combo_tiposolicitud.getSelectedItem().toString());
        cita.put("nombresyapellidos",edtxPersonName.getText().toString());
        cita.put("fechasolicitud",tvfecha.getText().toString());
        cita.put("horasolicitud",tvhora.getText().toString());


        mfirestore.collection("Solicitudcitas").document(edtxId.getText().toString()).set(cita);

        edtxId.setText("");
        edtxPersonName.setText("");
        tvfecha.setText("");
        tvhora.setText("");

    }

    public void Consultar() {

        Map<String, Object> cita = new HashMap<>();


        // Create a reference to the cities collection
        CollectionReference citaRef = mfirestore.collection("Solicitudcitas");

        // Create a query against the collection.
        Query query = citaRef.whereEqualTo("Id", edtxId.getText().toString());


        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        edtxPersonName.setText(document.getData().get("nombresyapellidos") + "");
                        tvfecha.setText(document.getData().get("fechasolicitud") + "");
                        tvhora.setText(document.getData().get("horasolicitud") + "");
                        Log.d("consultando", document.getId() + " => " + document.getData().get("nombreyapellido"));

                    }
                } else {
                    Log.d("consultando", "Error getting documents: ", task.getException());
                }
            }
        });

    }

    public void cancelar(){

        mfirestore.collection("Solicitudcitas").document(edtxId.getText().toString()).delete();

        edtxId.setText("");
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



