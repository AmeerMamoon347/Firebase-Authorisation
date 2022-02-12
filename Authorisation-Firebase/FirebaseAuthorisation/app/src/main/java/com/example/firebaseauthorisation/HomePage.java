package com.example.firebaseauthorisation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    TextView tv_Homepage;
    Button signOut;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        signOut = findViewById(R.id.btn_SignOut);

        tv_Homepage = findViewById(R.id.tv_Homepage);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

            Intent intent = getIntent();
            String s = intent.getStringExtra("already");

            if(s!=null)
            {
                tv_Homepage.setText(s);
            }

            signOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   auth = FirebaseAuth.getInstance();
                   auth.signOut();
                   startActivity(new Intent(HomePage.this,MainActivity.class));
                   finish();
                   Log.d("Message: ","Successfully sign out");

                }
            });

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null)
            {
                String email = user.getEmail();
                String name = user.getDisplayName();
                String uid = user.getUid();
                boolean isEmailVerif = user.isEmailVerified();

                Log.d("Info: ",email+" "+name+" "+uid);

                if(isEmailVerif)
                {
                    Log.d("Email: ","Verified");
                } else
                {
                    Log.d("Email: ","Not Verified");
                }

            }



    }


}