package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText em , pass;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        em = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e_mail = em.getText().toString();
                String pwd = pass.getText().toString();

                if(e_mail.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Enter The fields",Toast.LENGTH_SHORT).show();
                }
                else if (e_mail.isEmpty()) {
                    em.setError("Please Enter One of the fields");
                    em.requestFocus();
                }
                else if(pwd.isEmpty()) {
                    pass.setError("Please Enter the Password");
                    pass.requestFocus();
                }

                else if(!(e_mail.isEmpty() && pwd.isEmpty())){
                    Intent i = new Intent(MainActivity.this,Weather_forcast.class);
                    startActivity(i);
                }
            }
        });

    }
}