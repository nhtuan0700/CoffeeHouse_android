package com.example.coffeehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.UserProfileChangeRequest;

public class activity_signup extends AppCompatActivity {
    Button btnSignUp350;
    EditText edFName350, edLName350, edEmail350, edPassword350;
    private FirebaseAuth mAuth350;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth350 = FirebaseAuth.getInstance();
        addControls();

    }

    public void addControls() {
        edFName350 = findViewById(R.id.ed_fName);
        edLName350 = findViewById(R.id.ed_lName);
        edEmail350 = findViewById(R.id.ed_email);
        edPassword350 = findViewById(R.id.ed_password);
        btnSignUp350 = findViewById(R.id.btn_signup);

        btnSignUp350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void signUp() {
        String fName = edFName350.getText().toString();
        String lName = edLName350.getText().toString();
        String email = edEmail350.getText().toString();
        String password = edPassword350.getText().toString();

        if(fName.isEmpty() || lName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền các thông tin ở trên", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth350.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(activity_signup.this, "SignUp Success!", Toast.LENGTH_SHORT).show();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(fName + " " + lName)
                                    .build();

                            mAuth350.getCurrentUser().updateProfile(profileUpdates);
                            Intent intent = new Intent(getApplicationContext(),activity_signin.class);
                            startActivity(intent);
                            finish();
                        } else {
                            try
                            {
                                throw task.getException();
                            }
                            // if user enters wrong password.
                            catch (FirebaseAuthInvalidCredentialsException malformedEmail)
                            {
                                edPassword350.setError("Mật khẩu từ 6 ký tự trở lên!");
                                edPassword350.requestFocus();
                            }
                            catch (FirebaseAuthUserCollisionException existEmail)
                            {
                                edEmail350.setError("Email đã tồn tại!");
                                edEmail350.requestFocus();
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(activity_signup.this, "SignUp Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }

}