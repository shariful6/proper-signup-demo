package com.shariful.propersignup;

import android.app.Application;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
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
