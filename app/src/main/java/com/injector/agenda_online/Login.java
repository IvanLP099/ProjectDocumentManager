package com.injector.agenda_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText correoLogin, passwordLogin;
    Button botonLogin;
    TextView usuarioNuevoLogin;

    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    String correo="", password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correoLogin = findViewById(R.id.txtCorreoLogin);
        passwordLogin = findViewById(R.id.txtPasswordLogin);
        botonLogin = findViewById(R.id.btnLogin);
        usuarioNuevoLogin = findViewById(R.id.txtNuevoUsuarioLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Espere por favor");
        progressDialog.setCanceledOnTouchOutside(false);

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarDatos();
            }
        });

        usuarioNuevoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registro.class));
            }
        });


    }

    private void ValidarDatos() {
        correo = correoLogin.getText().toString();
        password = passwordLogin.getText().toString();

        if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            Toast.makeText(this, "Correo invalido",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Ingrese contraseña",Toast.LENGTH_SHORT).show();
        }else{
            LoginUsuario();
        }

    }

    private void LoginUsuario() {
        progressDialog.setMessage("Iniciando sesión ...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(correo,password)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //Si la sesion se realizo correctamente
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(Login.this, MenuPrincipal.class));
                            Toast.makeText(Login.this,"Bienvenido(a):"+user.getEmail(),Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(Login.this,"Verificar si el correo y contraseña son incorrectos",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}