package com.sectario.appcomunicate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.sectario.appcomunicate.Fragments.Calendar;
import com.sectario.appcomunicate.Fragments.Information;
import com.sectario.appcomunicate.Fragments.Login;
import com.sectario.appcomunicate.Fragments.SolicituddeCitas;

public class MainActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    // Variables para inicio
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main1);
       Drawer_Constant();


    }
private void Drawer_Constant(){
    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    drawerLayout = findViewById(R.id.drawer);
    navigationView = findViewById(R.id.navigation_view);

    //Se establece evento Onclic al navegation view
    navigationView.setNavigationItemSelectedListener(this);

    actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open,R.string.close);
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
    actionBarDrawerToggle.syncState();

    //Cargar Información general
    fragmentManager = getSupportFragmentManager();
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.container, new Login());
    fragmentTransaction.commit();



}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Cerrar automaticamente al seleccionar
        drawerLayout.closeDrawer(GravityCompat.START);

        //programación onclic
        if (item.getItemId() == R.id.info_general){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new Information());
            fragmentTransaction.commit();
        }
        if (item.getItemId() == R.id.calendario){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new Calendar());
            fragmentTransaction.commit();
        }
        if (item.getItemId() == R.id.cerrar_sesion){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new Login());
            fragmentTransaction.commit();
        }
        if (item.getItemId() == R.id.solicitud_cita){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new SolicituddeCitas());
            fragmentTransaction.commit();
        }


        return false;
    }
}


