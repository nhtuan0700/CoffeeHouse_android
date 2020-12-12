package com.example.coffeehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_signin extends AppCompatActivity {
    Button btnSignIn350, btnSignUp350;
    EditText edEmail350, edPassword350;
    private FirebaseAuth mAuth350;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth350 = FirebaseAuth.getInstance();
        addControls();
    }

    public void addControls(){
        edEmail350 = findViewById(R.id.ed_email);
        edPassword350 = findViewById(R.id.ed_password);
        btnSignIn350 = findViewById(R.id.btn_signin);
        btnSignUp350 = findViewById(R.id.btn_signup);

        findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSignIn350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        btnSignUp350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),activity_signup.class);
                startActivity(intent);
            }
        });
    }
    public void logIn(){
        String email = edEmail350.getText().toString();
        String password = edPassword350.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập email và password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth350.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(activity_signin.this, "Login Success!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(activity_signin.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}