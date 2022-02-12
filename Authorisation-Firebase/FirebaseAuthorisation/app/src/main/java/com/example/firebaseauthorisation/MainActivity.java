package com.example.firebaseauthorisation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView signUpActivity,tv_HomePage;
    EditText enter_Email,enterPW;
    Button btn_login;

    FirebaseAuth auth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser !=null)
        {
            currentUser.reload();

            Intent intent = new Intent(MainActivity.this,HomePage.class);
            intent.putExtra("already","Already logged in !");

            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        signUpActivity = findViewById(R.id.Sign_up);
        enter_Email = findViewById(R.id.enter_Email);
        enterPW = findViewById(R.id.enter_PW);
        btn_login = findViewById(R.id.login_btn);

        tv_HomePage = findViewById(R.id.tv_Homepage);

        signUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = enter_Email.getText().toString();
                String PW = enterPW.getText().toString();

                if(email.isEmpty() || PW.isEmpty() )
                {
                    Toast.makeText(getApplicationContext(), "Please! Provide both email and password!",
                            Toast.LENGTH_SHORT).show();
                } else
                {
                    signInWithEmailPassword(email,PW);
                }

            }
        });

    }

    public void signInWithEmailPassword(String email,String password)
    {
       auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,
               new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful())
                       {
                           Log.d("Successfully ","Signed");
                           Toast.makeText(getApplicationContext(), "You have been logged in successfully !",
                                   Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(MainActivity.this,HomePage.class));
//                           FirebaseUser user = auth.getCurrentUser();

                       } else
                       {
                           Log.d("Error: ",task.getException().toString());
                           Toast.makeText(getApplicationContext(), "Email or password may be wrong!",
                                   Toast.LENGTH_SHORT).show();
                       }
                   }
               });
    }



}