package com.sectario.appcomunicate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button btnRegistrarse, btnIniciar;
    EditText etxtEmail, etxtPassword;

    FirebaseAuth auth =FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnRegistrarse = findViewById(R.id.btnRegistrarse);
        btnIniciar = findViewById(R.id.btnIniciar);
        etxtPassword = findViewById(R.id.etxtPassword);
        etxtEmail = findViewById(R.id.etxtEmail);

        Intent i = new Intent(this, Register.class);


        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etxtEmail.getText().toString();
                String ctr = etxtPassword.getText().toString();
               // registrar(email, ctr);

                startActivity(i);
            }
        });

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etxtEmail.getText().toString();
                String ctr = etxtPassword.getText().toString();
                ingresar(email, ctr);
            }
        });

    }

    public void ingresar(String usr, String ctr){
        auth.signInWithEmailAndPassword(usr,ctr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(),SolicituddeCitas.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, "datos Incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}