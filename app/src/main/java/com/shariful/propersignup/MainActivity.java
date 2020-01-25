package com.shariful.propersignup;

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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText emailET,passwordET;
    Button signUpBtn,loginBtn,forgetpassBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        emailET=findViewById(R.id.emailETID);
        passwordET=findViewById(R.id.passwordETID);
        signUpBtn=findViewById(R.id.signUpBtnID);
        loginBtn=findViewById(R.id.loginBtnID);
        forgetpassBtn=findViewById(R.id.forgetPassBtnID);



        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //signup code here
                String email=emailET.getText().toString();
                String password=passwordET.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    mAuth.getCurrentUser().sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful())
                                                    {
                                                        Toast.makeText(MainActivity.this, "SignUp successfull..Please check your email !!", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(MainActivity.this, "failed !!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Failed to Signup !!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login code here

                String email=emailET.getText().toString();
                String password=passwordET.getText().toString();
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful())
                               {
                                   if (mAuth.getCurrentUser().isEmailVerified())
                                   {
                                       Toast.makeText(MainActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                                   }
                                   else
                                   {
                                       Toast.makeText(MainActivity.this, "Verify your email", Toast.LENGTH_SHORT).show();
                                   }

                               }
                               else
                               {
                                   Toast.makeText(MainActivity.this, "Failed to login !!", Toast.LENGTH_SHORT).show();
                               }
                            }
                        });

            }
        });

        forgetpassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //signup code here
                Intent intent = new Intent(MainActivity.this,ForgotPassActivity.class);
                startActivity(intent);
            }
        });

    }

  /*  @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }  */

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();
        if (firebaseUser !=null && firebaseUser.isEmailVerified())
        {
            Toast.makeText(this, "Login Successfull ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Please verify your Email first !!!", Toast.LENGTH_SHORT).show();
        }


    }

}
