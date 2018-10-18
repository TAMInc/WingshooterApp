package com.example.alex.wingshooterspocketapplication;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Home_Screen extends AppCompatActivity implements View.OnClickListener
{
    public TextView txtUserLoggedIn;

    String URL = "http://www.wingshooters.org.za/.cm4all/iproc.php/WINGS-MAG-No4-2017.pdf?cdp=a";

    public String userName = LogRegMainActivity.userName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);

        Button btnNAct = findViewById(R.id.btnNewActivity);
        btnNAct.setOnClickListener(this);

        Button btnHSeasons = findViewById(R.id.btnHuntingSeasons);
        btnHSeasons.setOnClickListener(this);

        Button btnGBID = findViewById(R.id.btnGamebird);
        btnGBID.setOnClickListener(this);

        Button btnMyHunts = findViewById(R.id.btnMyHunts);
        btnMyHunts.setOnClickListener(this);

        Button btnMag = findViewById(R.id.btnMagazine);
        btnMag.setOnClickListener(this);

        txtUserLoggedIn = findViewById(R.id.txtHomeScreen);
        txtUserLoggedIn.setText("Welcome " + userName);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnNewActivity:
                NewHunt();
                break;

            case R.id.btnHuntingSeasons:
               HSProvinceSelection();
                break;

            case R.id.btnGamebird:
                gamebirdidmain();
                break;

            case R.id.btnMyHunts:
                StartHunt();
                break;

            case R.id.btnMagazine:
                new DownloadTask(this, URL);
                break;


        }

    }

    public void NewHunt ()
    {
        Intent intent = new Intent(this,NewHunt.class);
        startActivity(intent);
        finish();
    }

    public void HSProvinceSelection()
    {
        Intent intent = new Intent(this, HSProvinceSelection.class);
        startActivity(intent);
        finish();

    }

    public void gamebirdidmain()
    {
        Intent intent = new Intent(this, BirdIDFV.class);
        startActivity(intent);
        finish();
    }

    public void StartHunt()
    {
        Intent intent = new Intent(this, StartHunt.class);
        startActivity(intent);
        finish();
    }


}
