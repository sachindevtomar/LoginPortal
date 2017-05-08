package com.example.dell.fbloginsample;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView txtStatus;
    LoginButton login_button;
    Button button;
    CallbackManager callbackManager;
    DatabaseHelper myDb;
    EditText text_email,text_pass;
    int count=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        myDb =new DatabaseHelper(this);

        initializeControls();

        LoginManager.getInstance().logOut();
        login_button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        loginWithFB();
                    }
                }
        );
    }

    private void initializeControls() {
        callbackManager = CallbackManager.Factory.create();
        text_email=(EditText)findViewById(R.id.editText);
        text_pass=(EditText)findViewById(R.id.editText2);
        login_button = (LoginButton) findViewById(R.id.login_button);
        button=(Button)findViewById(R.id.button);
    }

    private void loginWithFB() {

        //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_friends", "email", "public_profile"));


        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Intent intent=new Intent("com.example.dell.fbloginsample.SecondActivity");
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                txtStatus.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            callbackManager.onActivityResult(requestCode, resultCode, data);

        }

    public void newUser(View v){
        Intent intent=new Intent("com.example.dell.fbloginsample.Registration");
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        Intent a=new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void login(View v){
        Cursor res = myDb.getAllData(text_email.getText().toString());
        if (res.getCount() != 0) {
            res.moveToNext();
            Intent intent=new Intent(MainActivity.this,Login.class);
            intent.putExtra("email",text_email.getText().toString());
            intent.putExtra("pass",text_pass.getText().toString());
            intent.putExtra("name",res.getString(1));
            startActivity(intent);
        }
        else
        {
            Toast.makeText(MainActivity.this, "Wrong Email or Password", Toast.LENGTH_LONG).show();
            count--;
            if(count==0)
            {
                button.setEnabled(false);
                Toast.makeText(MainActivity.this, "Portal is Locked", Toast.LENGTH_LONG).show();
            }
        }
    }
}
