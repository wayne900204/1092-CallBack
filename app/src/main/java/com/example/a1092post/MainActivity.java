package com.example.a1092post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText account,password,name;
    Button button,getData;
    MyApiInterface myApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        button = findViewById(R.id.enter);
        getData = findViewById(R.id.getData);
        myApiInterface = new API(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                API api = new API(MainActivity.this);
//                api.setLoginApi(account.getText().toString(),password.getText().toString(),name.getText().toString());
                myApiInterface.setLoginApi(account.getText().toString(),password.getText().toString(),name.getText().toString());
            }
        });
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GetData.class));
            }
        });
    }
}


