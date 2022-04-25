package com.example.appdangnhap;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import butterknife.BindView;

public class Register extends AppCompatActivity {
    FirebaseAuth fAuth;
    @BindView(R.id.editYourName) EditText editYourName;
    @BindView(R.id.editRegister_Email) EditText editRegister_Email ;
    @BindView(R.id.eidtTypePass) EditText editTypePass;
    @BindView(R.id.editConfirmPass) EditText editConfirmPass ;
    @BindView(R.id.btnRegister_Register) Button btnRegister_Register ;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    final StorageReference storageRef = storage.getReference();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regiter);

        fAuth = FirebaseAuth.getInstance();

        EditText editYourName = findViewById(R.id.editYourName);
        EditText editRegister_Email = findViewById(R.id.editRegister_Email);
        EditText editTypePass= findViewById(R.id.eidtTypePass);
        EditText editConfirmPass=  findViewById(R.id.editConfirmPass);
        Button btnRegister_Register = findViewById(R.id.btnRegister_Register);

        btnRegister_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editYourName.getText().length()==0){
                    editYourName.setError("Please Enter your name");
                } else if (editRegister_Email.getText().length() == 0){
                    editRegister_Email.setError("Please Enter your email");
                }else if (editTypePass.getText().length()==0){
                    editTypePass.setError("Please Enter your Password");
                }else{
                    Register();
                }
            }
        });


    }
    private void Register(){
        final String email = editYourName.getText().toString().trim();
        final String yourName= editYourName.getText().toString().trim();
        String typePassword = editTypePass.getText().toString().trim();
        String confirmPass = editConfirmPass.getText().toString().trim();

        fAuth.createUserWithEmailAndPassword(email,typePassword)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(email,typePassword);
                            FirebaseDatabase.getInstance().getReference().push().setValue(user);
                            /*FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(Register.this,"Register successfully",Toast.LENGTH_SHORT).show();
                                        editYourName.setText("");
                                        editYourName.requestFocus();
                                        editRegister_Email.setText("");
                                        editTypePass.setText("");
                                        editConfirmPass.setText("");
                                    }else{
                                        Toast.makeText(Register.this,"Fail",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });*/

                        }else {
                            Toast.makeText(Register.this, "Unsuccessfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
