package com.mountreachsolution.rescuewings.NEEDY;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mountreachsolution.rescuewings.R;

public class Call extends AppCompatActivity {
    Button btnPolice, btnAmbulance, btnHospital, btnAnimalHelper, btnFireBrigade, btnDisasterManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_call);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.white));

        btnPolice = findViewById(R.id.btnPolice);
        btnAmbulance = findViewById(R.id.btnAmbulance);
        btnHospital = findViewById(R.id.btnHospital);
        btnAnimalHelper = findViewById(R.id.btnAnimalHelper);
        btnFireBrigade = findViewById(R.id.btnFireBrigade);
        btnDisasterManagement = findViewById(R.id.btnDisasterManagement);

        // Set onClickListeners
        btnPolice.setOnClickListener(v -> makeCall("100"));
        btnAmbulance.setOnClickListener(v -> makeCall("102"));
        btnHospital.setOnClickListener(v -> makeCall("108"));
        btnAnimalHelper.setOnClickListener(v -> makeCall("1962"));
        btnFireBrigade.setOnClickListener(v -> makeCall("101"));
        btnDisasterManagement.setOnClickListener(v -> makeCall("108"));

    }
    private void makeCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }
}