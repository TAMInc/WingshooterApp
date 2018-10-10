package com.example.alex.wingshooterspocketapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogRegMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg_main);

        Button btnLogin = findViewById(R.id.btnLog);
        btnLogin.setOnClickListener(this);

        Button btnRegister = findViewById(R.id.btnReg);
        btnRegister.setOnClickListener(this);
    }



    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnLog:
                Intent i1 = new Intent(getApplicationContext(), Home_Screen.class);
                startActivity(i1);
                break;

            case R.id.btnReg:
                Intent i2 = new Intent(getApplicationContext(), LoginRegister.class);
                startActivity(i2);
                break;
        }

    }
}
