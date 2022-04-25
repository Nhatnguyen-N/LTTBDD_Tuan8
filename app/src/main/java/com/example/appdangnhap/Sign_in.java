package com.example.appdangnhap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_in  extends AppCompatActivity {
    public FirebaseAuth fAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        EditText editEmail = findViewById(R.id.editEmail);
        EditText editPassword= findViewById(R.id.editPassword);

        fAuth = FirebaseAuth.getInstance();

        Button btnSignIn_SignIn= findViewById(R.id.btnSign_in_signIn);

        btnSignIn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(Sign_in.this,face.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("emailUser",email);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Sign_in.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}
