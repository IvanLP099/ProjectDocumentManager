package com.injector.document_manager.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.injector.document_manager.R;

public class PantallaDeCarga extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_de_carga);

        firebaseAuth = FirebaseAuth.getInstance();

        int tiempo = 3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*startActivity(new Intent(PantallaDeCarga.this,MainActivity.class));
                finish();*/
                VerificarUsuario();
            }
        },tiempo);
    }

    private void VerificarUsuario(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        //Si no ha iniciado sesion se redirecciona al menu de opciones de Login y registro
        if(firebaseUser == null){
            startActivity(new Intent(PantallaDeCarga.this, MainActivity.class));
            finish();
        }
        //Caso contrario al menu principal
        else{
            startActivity(new Intent(PantallaDeCarga.this, NavigationDrawer.class));
            finish();
        }
    }
}