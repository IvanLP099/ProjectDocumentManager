package com.injector.document_manager.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.injector.document_manager.R;
import com.injector.document_manager.databinding.ActivityNavigationDrawerBinding;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationDrawerBinding binding;
    DrawerLayout drawer;
    NavigationView navigationView;
    NavController navController;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        binding = ActivityNavigationDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigationDrawer.toolbar);
        binding.appBarNavigationDrawer.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = binding.drawerLayout;
        navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_usuario,
                R.id.nav_documento, R.id.nav_configuracion, R.id.nav_termino_politica, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Aquí maneja los clics de los elementos del menú
        if (id == R.id.nav_home) {
            // Navegar al fragmento de inicio
            // Ejemplo:
            navController.navigate(R.id.nav_home);
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).commit();
        } else if (id == R.id.nav_gallery) {
            // Navegar al fragmento de perfil
            // Ejemplo:
            navController.navigate(R.id.nav_gallery);
            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PerfilFragment()).commit();
        } else if (id == R.id.nav_slideshow) {
            // Navegar al fragmento de configuración
            // Ejemplo:
            navController.navigate(R.id.nav_slideshow);
            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConfiguracionFragment()).commit();
        } else if (id == R.id.nav_usuario) {
            // Navegar al fragmento de configuración
            // Ejemplo:
            navController.navigate(R.id.nav_usuario);
            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConfiguracionFragment()).commit();
        } else if (id == R.id.nav_documento) {
            // Navegar al fragmento de configuración
            // Ejemplo:
            navController.navigate(R.id.nav_documento);
            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConfiguracionFragment()).commit();
        } else if (id == R.id.nav_configuracion) {
            // Navegar al fragmento de configuración
            // Ejemplo:
            navController.navigate(R.id.nav_configuracion);
            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConfiguracionFragment()).commit();
        } else if (id == R.id.nav_termino_politica) {
            // Navegar al fragmento de configuración
            // Ejemplo:
            navController.navigate(R.id.nav_termino_politica);
            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConfiguracionFragment()).commit();
        } else if (id == R.id.nav_logout) {
            // Navegar al fragmento de configuración
            // Ejemplo:
            CerrarAplicacion();

            //navController.navigate(R.id.nav_logout);
            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConfiguracionFragment()).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return NavigationUI.onNavDestinationSelected(item, navController)
                    || super.onOptionsItemSelected(item);
        }else if(item.getItemId() == R.id.action_logout){
            CerrarAplicacion();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void CerrarAplicacion() {
        firebaseAuth.signOut();
        startActivity(new Intent(NavigationDrawer.this, MainActivity.class));
        Toast.makeText(this,"Cerraste sesión",Toast.LENGTH_SHORT).show();
    }
}
