package com.example.contoh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contoh.helper.Database;
import com.example.contoh.model.User;

public class RegisterActivity extends AppCompatActivity {
    Button registerAction;
    EditText emailRegister, passwordRegister, nameRegister;
    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new Database(this);
        nameRegister=findViewById(R.id.nameRegister);
        emailRegister=findViewById(R.id.emailRegister);
        passwordRegister=findViewById(R.id.passwordRegister);
        registerAction=findViewById(R.id.registerAction);
        registerAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user= new User(null, nameRegister.getText().toString(),emailRegister.getText().toString(), passwordRegister.getText().toString() );
                db.register(user);
                Toast.makeText(getApplicationContext(),"Register Sukses",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onRegisterClick (View View){
        startActivity(new Intent(this,MainActivity.class));
    }
}