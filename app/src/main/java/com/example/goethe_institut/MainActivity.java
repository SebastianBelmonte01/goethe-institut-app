package com.example.goethe_institut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btnSend = findViewById(R.id.btnSend);

        DataBaseHelper db = new DataBaseHelper(MainActivity.this);

        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(db.isLoged(username.getText().toString(), Integer.parseInt(password.getText().toString()))){
                    Intent intent = new Intent(MainActivity.this,PrincipalMenu.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}