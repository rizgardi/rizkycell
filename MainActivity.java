package com.example.contoh;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.contoh.helper.Database;
import com.example.contoh.model.User;

public class MainActivity extends AppCompatActivity {
    Button btnlogin ;
    EditText email, password;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new Database ( this );
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnlogin = findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user= new User(null, null,email.getText().toString(), password.getText().toString() );
                User currentUser = db.login(user);
                if (currentUser != null){
                    Toast.makeText(getApplicationContext(),"login sukses",Toast.LENGTH_SHORT).show();
                    moveToDashboard(view);
                }
            }
        });
    }
    public void onLoginClick (View View){
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void moveToDashboard (View View){
        startActivity(new Intent(this,Dashboard.class));
    }
}