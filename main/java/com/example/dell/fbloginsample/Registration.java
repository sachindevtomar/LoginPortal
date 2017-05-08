package com.example.dell.fbloginsample;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends Activity {
    DatabaseHelper myDb;
    EditText name,email,mobile,pass,cpass;
    CheckBox agree;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        myDb =new DatabaseHelper(this);
        name=(EditText)findViewById(R.id.editText_name);
        email=(EditText)findViewById(R.id.editText_email);
        mobile=(EditText)findViewById(R.id.editText_mobile);
        pass=(EditText)findViewById(R.id.editText_pass);
        cpass=(EditText)findViewById(R.id.editText_cpass);
        register=(Button)findViewById(R.id.button_register);
        agree=(CheckBox)findViewById(R.id.checkBox_agree);
        AddData();
    }

    public void AddData()
    {
        register.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        if(!email.getText().toString().trim().equals("")||!name.getText().toString().trim().equals("")||!pass.getText().toString().trim().equals("")||!cpass.getText().toString().trim().equals("")||!mobile.getText().toString().trim().equals(""))
                        {
                            if(pass.getText().toString().equals(cpass.getText().toString()))
                            {
                                if(agree.isChecked()) {
                                    boolean isInserted = myDb.insertData(email.getText().toString(), name.getText().toString(), pass.getText().toString(), mobile.getText().toString());
                                    if (isInserted == true)
                                        Toast.makeText(Registration.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(Registration.this, "Email is already registered", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(Registration.this, "Agree Terms And Conditions", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(Registration.this, "Password do not match", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(Registration.this, "Every Field is Required", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }
}

