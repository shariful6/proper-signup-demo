package com.shariful.propersignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {
    EditText userEmail;
    Button forgotPassBtn;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        userEmail=findViewById(R.id.editText);
        forgotPassBtn=findViewById(R.id.button);

        firebaseAuth=FirebaseAuth.getInstance();

        forgotPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())
                     .addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {

                             if (task.isSuccessful())
                             {
                                 Toast.makeText(ForgotPassActivity.this, "Check your email to reset password !!", Toast.LENGTH_SHORT).show();
                             }
                             else
                             {
                                 Toast.makeText(ForgotPassActivity.this, "Failed !!", Toast.LENGTH_SHORT).show();
                             }

                         }

                     });
            }
        });


    }
}
