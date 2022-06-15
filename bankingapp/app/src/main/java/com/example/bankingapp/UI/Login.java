package com.example.bankingapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankingapp.Db.LoginHelper;
import com.example.bankingapp.R;

public class Login extends AppCompatActivity {
    EditText usermail,password;
    Button signin,signup;
    LoginHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usermail=(EditText)findViewById(R.id.e1);
        password=(EditText)findViewById(R.id.e2);
        signin=(Button)findViewById(R.id.log);
        signup=(Button)findViewById(R.id.sig);
        DB = new LoginHelper(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usermail.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(Login.this,"Please enter all fields",Toast.LENGTH_LONG).show();

                }
                else{
                    Boolean checkuser = DB.checkstrdetails(user,pass);
                    if(checkuser==true)
                    {
                        Toast.makeText(Login.this,"Welcome to android bank",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this,"Invalid credentials!! Or Please do registration.",Toast.LENGTH_LONG).show();
                    }

                }


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewUser.class);
                startActivity(intent);
            }
        });
    }
}