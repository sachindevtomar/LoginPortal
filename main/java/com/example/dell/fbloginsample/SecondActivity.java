package com.example.dell.fbloginsample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivity extends Activity {

    ProfilePictureView profile;
    LoginButton login_button;
    TextView txtStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txtStatus = (TextView) findViewById(R.id.textView_success);
        login_button = (LoginButton) findViewById(R.id.login_button);
        RequestData();
        logOut();
    }



    public void RequestData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                JSONObject json = response.getJSONObject();
                try {
                    if (json != null) {
                        String text = json.getString("name");
                        String email=json.getString("email");
                        txtStatus.setText("Name: "+text+"\nEmail: "+email);
                    }

                } catch (JSONException e) {
                        txtStatus.setText(e.getMessage());
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void logOut(){
        login_button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public void onBackPressed(){
        LoginManager.getInstance().logOut();
        Intent intent=new Intent(SecondActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
