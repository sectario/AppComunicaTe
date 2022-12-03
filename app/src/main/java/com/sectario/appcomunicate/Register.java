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

public class Register extends AppCompatActivity {

    EditText editTextTextPassword, editTextPhone, editTextTextEmailAddress2, edtxPersonName, edtxIdUsuario;
    Button btnRegistrarse2;
    FirebaseAuth auth =FirebaseAuth.getInstance();
    FirebaseFirestore firestore= FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtxIdUsuario = findViewById(R.id.edtxIdUsuario);
        edtxPersonName = findViewById(R.id.edtxPersonName);
        editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        btnRegistrarse2 = findViewById(R.id.btnRegistrarse2);

        Intent i = new Intent(this, Login.class);

        btnRegistrarse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextTextEmailAddress2.getText().toString();
                String ctr = editTextTextPassword.getText().toString();
                String phone = editTextPhone.getText().toString();
                String id = edtxIdUsuario.getText().toString();
                String name = edtxPersonName.getText().toString();
                registrar(email, ctr, phone, id, name);

                startActivity(i);
            }
        });
    }

    public void registrar(String email, String contraseña, String phone, String _id, String name) {

        auth.createUserWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    String id= auth.getCurrentUser().getUid();
                    Map<String, Object> datos= new HashMap<>();
                    datos.put("email",email);
                    datos.put("phone",phone);
                    datos.put("id", _id);
                    datos.put("name",name);

                    firestore.collection("usuarios").document(email).set(datos).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                Toast.makeText(Register.this, "registrado", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Register.this, "Problemas", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }
}