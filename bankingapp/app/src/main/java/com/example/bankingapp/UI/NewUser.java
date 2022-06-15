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

public class NewUser extends AppCompatActivity {
    EditText name,phone,mail,cr_pass,cf_pass;
    Button submit,clear;
    LoginHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        name=(EditText)findViewById(R.id.et1);
        phone=(EditText)findViewById(R.id.et2);
        mail=(EditText)findViewById(R.id.et3);
        cr_pass=(EditText)findViewById(R.id.et4);
        cf_pass=(EditText)findViewById(R.id.et5);


        submit=(Button)findViewById(R.id.sub);
        clear=(Button)findViewById(R.id.clr);

        DB = new LoginHelper(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = name.getText().toString();
                Integer phone_no = new Integer(phone.getText().toString());
                String mail_id = mail.getText().toString();
                String crpass = cr_pass.getText().toString();
                String cfpass = cf_pass.getText().toString();

                if(user.equals("")||phone_no.equals("")||mail_id.equals("")||crpass.equals("")||cfpass.equals("")){
                    Toast.makeText(NewUser.this,"Please enter all fields",Toast.LENGTH_LONG).show();
                }
                else{
                    Boolean checkuser = DB.checkstrdetails(mail_id,cfpass);
                    if(checkuser==false)
                    {
                        Boolean insert = DB.insertData(user,phone_no,mail_id,cfpass);
                        if(insert==true)
                        {
                            Toast.makeText(NewUser.this,"Registration successful",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(NewUser.this,"Registration is not successful",Toast.LENGTH_LONG).show();
                        }
                    }

                }
                if(crpass != cfpass){
                    Toast.makeText(NewUser.this,"Password not matched",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(NewUser.this,"Password matched",Toast.LENGTH_LONG).show();
                }



            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText(" ");
                phone.setText(" ");
                mail.setText(" ");
                cr_pass.setText(" ");
                cf_pass.setText(" ");
            }
        });
    }
}