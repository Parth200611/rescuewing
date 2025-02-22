package com.mountreachsolution.rescuewings.SAVIOUR;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import com.mountreachsolution.rescuewings.NEEDY.Call;
import com.mountreachsolution.rescuewings.NEEDY.NeedyHomepage;
import com.mountreachsolution.rescuewings.NEEDY.SaftyPre;
import com.mountreachsolution.rescuewings.R;

public class SaviourHomepage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_saviour_homepage);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.blue));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false);
            window.getInsetsController().setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        }

        getWindow().setNavigationBarColor(ContextCompat.getColor(SaviourHomepage.this,R.color.white));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        bottomNavigationView = findViewById(R.id.bottomnevigatiomuserhome);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.svHome);

    }
    HomeFragment homeFragment = new HomeFragment();
    HistoryFragment historyFragment= new HistoryFragment();
    ProfilFargment profilFargment= new ProfilFargment();
    OtherSavioure otherSavioure = new OtherSavioure();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.svHome){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,homeFragment).commit();
        }else if(item.getItemId()==R.id.svHistory){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,historyFragment).commit();
        } else if(item.getItemId()==R.id.svOther){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,otherSavioure).commit();
        }else if(item.getItemId()==R.id.svProfil){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayoutuserhome,profilFargment).commit();
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.svmeny,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.svrequest){
            Intent i = new Intent(SaviourHomepage.this, AllRequest.class);
            startActivity(i);

        }

        return true;
    }
}