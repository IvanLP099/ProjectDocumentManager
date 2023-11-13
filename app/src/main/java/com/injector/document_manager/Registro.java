package com.injector.document_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class Registro extends AppCompatActivity {

    EditText nombreEt, correoEt, contraseniaEt, confirmaContraseniaEt;
    Button registrarUsuarioBt;
    TextView tengoCuentaTv;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    String nombre = " ", correo = " ", password = " ", confirmarPassword = " ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Registrar");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView botonToolbar = toolbar.findViewById(R.id.botonImageToolbar);

        //Agregar funcionalidad a la imagen para retroceder
        botonToolbar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                onBackPressed();
            }
        });


        nombreEt = findViewById(R.id.txtNombre);
        correoEt = findViewById(R.id.txtCorreo);
        contraseniaEt = findViewById(R.id.txtContrasenia);
        confirmaContraseniaEt = findViewById(R.id.txtConfirmarContrasenia);
        registrarUsuarioBt = findViewById(R.id.btnRegistrarUsuario);
        tengoCuentaTv = findViewById(R.id.txtTengoCuenta);

        Toast.makeText(Registro.this,"Really",Toast.LENGTH_SHORT).show();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(Registro.this);
        progressDialog.setTitle("Espere por favor");
        progressDialog.setCanceledOnTouchOutside(false);

        registrarUsuarioBt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                validarDatos();
            }
        });

        tengoCuentaTv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registro.this, Login.class));
            }

        });


    }

    //@Override
    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    private void validarDatos(){
        nombre = nombreEt.getText().toString();
        correo = correoEt.getText().toString();
        password = contraseniaEt.getText().toString();
        confirmarPassword = confirmaContraseniaEt.getText().toString();

        if(TextUtils.isEmpty(nombre)){
            Toast.makeText(this,"Ingrese nombre: ",Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            Toast.makeText(this,"Ingrese correo",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Ingrese contraseña",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(confirmarPassword)){
            Toast.makeText(this,"Confirmar Contraseña",Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirmarPassword)){
            Toast.makeText(this,"Las conaseñas no coinciden",Toast.LENGTH_SHORT).show();
        }
        else{
            CrearCuenta();
        }
    }

    private void CrearCuenta() {
        progressDialog.setMessage("Creando cuenta ...");
        progressDialog.show();

        //Crear cuenta de usuario en Firebase
        firebaseAuth.createUserWithEmailAndPassword(correo, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    GuardarInformacion();
                }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Registro.this, ""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void GuardarInformacion() {
        progressDialog.setMessage("Informacion guardada");
        progressDialog.dismiss();

        //Obtener la identificacion del usuario actual
        String id = firebaseAuth.getUid();

        HashMap<String, String> datos = new HashMap<>();
        datos.put("id", id);
        datos.put("correo", correo);
        datos.put("nombres",nombre);
        datos.put("password",password);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios");
        databaseReference.child(id)
                .setValue(datos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(Registro.this, "Cuenta creada con exito",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Registro.this, MenuPrincipal.class ));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Registro.this, ""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }



}