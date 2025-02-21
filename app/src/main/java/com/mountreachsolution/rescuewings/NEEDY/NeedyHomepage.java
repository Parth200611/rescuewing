package com.mountreachsolution.rescuewings.NEEDY;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowInsetsController;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mountreachsolution.rescuewings.R;
import com.mountreachsolution.rescuewings.SAVIOUR.SaviourHomepage;

public class NeedyHomepage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_needy_homepage);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false);
            window.getInsetsController().setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        }

        getWindow().setNavigationBarColor(ContextCompat.getColor(NeedyHomepage.this,R.color.white));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        bottomNavigationView = findViewById(R.id.bottomnevigatiomuserhome);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.nvHome);

    }
   NeedyHome needyHome = new NeedyHome();
    Addrequest addrequest = new Addrequest();
    NeedyProfil needyProfil = new NeedyProfil();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nvHome){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,needyHome).commit();
        }else if(item.getItemId()==R.id.nvAddRequest){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,addrequest).commit();
        } else if(item.getItemId()==R.id.nvProfil){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,needyProfil).commit();

        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.needymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.nvCall){
            Intent i = new Intent(NeedyHomepage.this, Call.class);
            startActivity(i);

        } else if (item.getItemId() == R.id.nvPre) {
            Intent i = new Intent(NeedyHomepage.this, SaftyPre.class);
            startActivity(i);

        }

        return true;
    }
}