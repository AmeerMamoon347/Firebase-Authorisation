package com.example.firebaseauthorisation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    TextView name,email,password;
    Button btn_Sign;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        Intent intent = getIntent();
        btn_Sign = findViewById(R.id.sign_btn);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();

        btn_Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if(Email.isEmpty() || Password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please provide ateast email and password!",
                            Toast.LENGTH_SHORT).show();
                } else

                createEmailPassword(Email,Password);

            }
        });

    }

    public void createEmailPassword(String email, String password)
    {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Log.d("Account:","Created");
                                    Toast.makeText(getApplicationContext(), "You have been registered successfully!",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignUpActivity.this,MainActivity.class));

//                                    FirebaseUser user = auth.getCurrentUser();
//                                    user.updateEmail(email);
//                                    user.updatePassword(password);
                                } else
                                {
                                    Log.d("Failed to created: ",task.getException().toString());
                                    Toast.makeText(getApplicationContext(), "Failed in signing! something is wrong",
                                            Toast.LENGTH_SHORT).show();

                                }

                            }
                        }
                        );
    }

}