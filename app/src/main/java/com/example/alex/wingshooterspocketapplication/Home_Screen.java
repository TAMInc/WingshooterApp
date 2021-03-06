package com.example.alex.wingshooterspocketapplication;

import android.content.Intent;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.io.File;


public class Home_Screen extends AppCompatActivity implements View.OnClickListener
{
    public TextView txtUserLoggedIn;
    public static int otherName;

    public PDFView pdfView1;
    public ConstraintLayout viewPdf1;
    public ConstraintLayout butView1;

    public PDFView pdfView2;
    public ConstraintLayout viewPdf2;
    public ConstraintLayout butView2;

    public PDFView pdfView3;
    public ConstraintLayout viewPdf3;
    public ConstraintLayout butView3;

    public String userName = LogRegMainActivity.userName;
    public String[] urlLinks = {"","",""};
    /*public String URL1;
    public String URL2;
    public String URL3;*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getSupportActionBar().setTitle("Home");

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

        Button btnNoti = findViewById(R.id.btnNotification);
        btnNoti.setOnClickListener(this);


        Button btnCPdf1 = findViewById(R.id.closePdf1);
        btnCPdf1.setOnClickListener(this);

        Button btnCPdf2 = findViewById(R.id.closePdf2);
        btnCPdf2.setOnClickListener(this);

        Button btnCPdf3 = findViewById(R.id.closePdf3);
        btnCPdf3.setOnClickListener(this);

        Button btnDmag = findViewById(R.id.btnDMag1);
        btnDmag.setOnClickListener(this);

        Button btnDnot = findViewById(R.id.btnDNot);
        btnDnot.setOnClickListener(this);

        Button btnDSeas = findViewById(R.id.btnDHS);
        btnDSeas.setOnClickListener(this);

        txtUserLoggedIn = findViewById(R.id.txtHomeScreen);
        txtUserLoggedIn.setText("Welcome " + userName);

        pdfView1=findViewById(R.id.pdfv1);
        viewPdf1 = findViewById(R.id.pdView1);
        butView1 = findViewById(R.id.conLayBtn);

        pdfView2=findViewById(R.id.pdfv2);
        viewPdf2 = findViewById(R.id.pdView2);
        butView2= findViewById(R.id.conLayBtn);

        pdfView3=findViewById(R.id.pdfv3);
        viewPdf3 = findViewById(R.id.pdView3);
        butView3 = findViewById(R.id.conLayBtn);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = rootRef.child("URLDOWNLOADS");
        ValueEventListener valueEventListener = new ValueEventListener()
        {
            final String TAG = "";
            int count = 0;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot ds : dataSnapshot.getChildren() )
                {
                    String key = ds.getKey();
                    String url = ds.child("URL").getValue(String.class);
                    urlLinks[count] = url;
                    Log.d(TAG, key + " / " + url + " / " + Integer.toString(count));
                    count = count + 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Log.d(TAG, databaseError.getMessage());
            }
        };
        ref.addListenerForSingleValueEvent(valueEventListener);
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
               downMag();
                break;

            case  R.id.btnNotification:
                downNot();
                break;

            case R.id.closePdf1:
                closepdf();
                break;

            case R.id.closePdf2:
                closepdf();
                break;

            case R.id.closePdf3:
                closepdf();
                break;

            case R.id.btnDMag1:
                dltPdfM();
                break;

            case R.id.btnDNot:
                dltPdfN();
                break;

            case R.id.btnDHS:
                dltPdfHS();
                break;

        }

    }

    public void NewHunt ()
    {
        Intent intent = new Intent(this,NewHunt.class);
        startActivity(intent);

    }


    public void gamebirdidmain()
    {
        Intent intent = new Intent(this, BirdIDFV.class);
        startActivity(intent);

    }

    public void StartHunt()
    {
        Intent intent = new Intent(this, PreviousHunts.class);
        startActivity(intent);

    }

    public void HSProvinceSelection()
    {
        File fileP = new File("/sdcard/WINGPOCKAPP FILES/WingShootersHuntSeasons.pdf");


        if (fileP.exists()) {
            Uri path3 = Uri.fromFile(fileP);
            pdfView3.fromUri(path3).load();
            viewPdf3.setVisibility(View.VISIBLE);
            butView3.setVisibility(View.INVISIBLE);

        }

        else {
            new DownloadTask(Home_Screen.this, urlLinks[2]);
            otherName = 3;
        }


    }

    public void downMag()
    {
        File file = new File("/sdcard/WINGPOCKAPP FILES/WingShootersMagazine.pdf");

        if (file.exists()) {
            Uri path1 = Uri.fromFile(file);
            pdfView1.fromUri(path1).load();
            viewPdf1.setVisibility(View.VISIBLE);
            butView1.setVisibility(View.INVISIBLE);

        }

        else {
        new DownloadTask(Home_Screen.this, urlLinks[0]);
        otherName = 2;
        }

    }

    public void downNot()
    {
        File fileN = new File("/sdcard/WINGPOCKAPP FILES/WingShootersNotifications.pdf");

        if (fileN.exists()) {
            Uri path2 = Uri.fromFile(fileN);
            pdfView2.fromUri(path2).load();
            viewPdf2.setVisibility(View.VISIBLE);
            butView2.setVisibility(View.INVISIBLE);

        }

        else {
            new DownloadTask(Home_Screen.this, urlLinks[1]);
            otherName = 1;
        }
    }



    public void closepdf()
    {


        Intent intent = new Intent(this,Home_Screen.class);
        startActivity(intent);

        finish();
    }

    public void dltPdfM()
    {
        Intent intent = new Intent(this,Home_Screen.class);
        startActivity(intent);
        File file = new File("/sdcard/WINGPOCKAPP FILES/WingShootersMagazine.pdf");
        file.delete();
        finish();


    }

    public void dltPdfN()
    {
        Intent intent = new Intent(this,Home_Screen.class);
        startActivity(intent);
        File fileN = new File("/sdcard/WINGPOCKAPP FILES/WingShootersNotifications.pdf");
        fileN.delete();
        finish();


    }

    public void dltPdfHS()
    {
        Intent intent = new Intent(this,Home_Screen.class);
        startActivity(intent);
        File fileP = new File("/sdcard/WINGPOCKAPP FILES/WingShootersHuntSeasons.pdf");
        fileP.delete();
        finish();


    }






}
